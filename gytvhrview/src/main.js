import Vue from 'vue'
import App from './App.vue'
import router from "./router";

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from "./store";

import {getRequest, postRequest, deleteRequest, putRequest, uploadFileRequest, postKeyValueRequest} from "./utils/api"
import {initMenu,isNotNullORBlank} from "./utils/utils";
import 'font-awesome/css/font-awesome.min.css'


Vue.config.productionTip = false
Vue.use(ElementUI)

Vue.prototype.postKeyValueRequest=postKeyValueRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.uploadFileRequest = uploadFileRequest;
Vue.prototype.isNotNullORBlank = isNotNullORBlank;

router.beforeEach((to, from, next) => {
    if (to.name == 'Login') {
        next();
        return;
    }
    var name = store.state.user.name;
    if (name == '未登录') {
        if (to.meta.requireAuth || to.name == null) {
            next({path: '/', query: {redirect: to.path}})
        } else {
            next();
        }
    } else {
        initMenu(router, store);
        if(to.path=='/chat'){
            store.commit("updateMsgList", []);
        }
        next();
    }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
