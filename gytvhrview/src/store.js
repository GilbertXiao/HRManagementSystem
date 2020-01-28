import Vue from 'vue'
import Vuex from 'vuex'
import {getRequest} from './utils/api'
import './utils/sockjs'
import './utils/stomp'
import { Notification } from 'element-ui';

Vue.use(Vuex)
const now = new Date();

const store = new Vuex.Store({
    state: {
        user: {
            name: window.sessionStorage.getItem('user' || '[]') == null ? '未登录' : JSON.parse(window.sessionStorage.getItem('user' || '[]')).name,
            userface: window.sessionStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('user' || '[]')).userface,
            username: window.sessionStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('user' || '[]')).username,
            roles: window.sessionStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.sessionStorage.getItem('user' || '[]')).roles
        },
        routes: [],
        sessions: {},
        //sessions: {},
        hr: [],
        currentSession: null,
        filterKey: '',
        stomp: null,
        isDot:{}

    },
    mutations: {
        initMenu(state, menus) {
            state.routes = menus;
        },
        login(state, user) {
            state.user = user;
            window.sessionStorage.setItem('user', JSON.stringify(user));

        },
        logout(state) {
            window.sessionStorage.removeItem('user');
            window.sessionStorage.removeItem('jobLevels');
            window.sessionStorage.removeItem('nations');
            window.sessionStorage.removeItem('positions');
            window.sessionStorage.removeItem('politicsStatus');
            window.sessionStorage.removeItem('departments');
            state.routes = [];
        },
        changeCurrentSession(state, selectSession) {
            state.currentSession = selectSession;
            Vue.set(state.isDot, state.user.username + '#' + selectSession.username, false);
        },
        addMessage(state, msg) {
            let mss=state.sessions[state.user.username+'#'+msg.to];
            if(!mss){
                // state.sessions[state.currentHr.username+'#'+msg.to] = [];
                Vue.set(state.sessions, state.user.username + '#' + msg.to, []);
            }
            state.sessions[state.user.username+'#'+msg.to].push({
                content: msg.content,
                date: new Date(),
                self: !msg.notSelf
            })
        },
        INIT_DATA(state) {
            //浏览器本地的历史聊天记录可以在这里完成
            let data = localStorage.getItem('vue-chat-session');
            //console.log(data)
            if (data) {
                state.sessions = JSON.parse(data);
            }
        },
        INIT_HR(state, data) {
            state.hr = data;
        }


    },
    actions: {
        connect(context){
            context.state.stomp=Stomp.over(new SockJS('/ws/hr'));
            context.state.stomp.connect({},success=>{
                //收消息
                context.state.stomp.subscribe('/user/queue/chat',msg=>{
                    let receiveMsg = JSON.parse(msg.body);
                    //收到对方发过来的内容。比如：对方是admin,己方是libai,libai收到admin的内容是
                    // {\"to\":\"libai\",\"from\":\"admin\",\"fromNickName\":\"系统管理员\",\"content\":\"666666\",\"date\":\"2020-01-27\"}
                    if(!context.state.currentSession||receiveMsg.from!=context.state.currentSession.username){
                        Notification.info({
                            title: '【'+receiveMsg.fromNickName+'】发来一条消息',
                            message: receiveMsg.content.length>10?receiveMsg.content.substr(0,10)+'...':receiveMsg.content,
                            position: 'bottom-right'
                        });
                        Vue.set(context.state.isDot, context.state.user.username + '#' + receiveMsg.from, true);
                    }
                    receiveMsg.notSelf=true;
                    receiveMsg.to=receiveMsg.from;
                    //console.log('Javaboy=>'+JSON.stringify(msg.body));
                    context.commit('addMessage',receiveMsg);
                })
            },err=>{

            });
        },
        initData(context) {
            context.commit('INIT_DATA');
            getRequest('/chat/hr').then(resp => {
                if (resp) {
                    context.commit('INIT_HR',resp);
                }
            })
        }
    }
});

store.watch(function (state) {
    return state.sessions
}, function (val) {
    //console.log('CHANGE: ', val);
    localStorage.setItem('vue-chat-session', JSON.stringify(val));
}, {
    deep: true/*这个貌似是开启watch监测的判断,官方说明也比较模糊*/
})

export default store;
