import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

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
    if (to.path != '/' && !window.sessionStorage.getItem("user")) {
        //要去的页面不是登录页，并且还没有登录
        next("/");
        return;
    }
    initMenu(router, store);
    next();
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
