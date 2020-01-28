<template>
    <div id="list">
        <ul>
            <li v-for="item in hr" :class="{ active: currentSession?item.username === currentSession.username:false }"
                v-on:click="changeCurrentSession(item)">
                <!--   :class="[item.id === currentSession ? 'active':'']" -->
                <img class="avatar" :src="item.userface" :alt="item.name">
                <el-badge :is-dot="isDot[user.username+'#'+item.username]" ><p class="name">{{item.name}}</p></el-badge>
            </li>
        </ul>
    </div>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: 'list',
        data() {
            return {
            }
        },
        computed: mapState([
            'user',
            'hr',
            'currentSession',
            'isDot'
        ]),
        methods: {
            //更改现在选择的聊天对象
            changeCurrentSession: function (selectUser) {
                this.$store.commit('changeCurrentSession', selectUser)
            }
        }
    }
</script>

<style lang="scss" scoped>
    #list {
        ul{
            padding-left: 0px;
        }

        li {
            padding: 16px 15px 16px 0px;
            border-bottom: 1px solid #292C33;
            cursor:pointer;
            list-style: none;
            text-align: left;
            padding-left: 20px;
            &:hover {
                background-color: rgba(255, 255, 255, 0.03);
            }
        }

        li.active { /*注意这个是.不是冒号:*/
            background-color: rgba(255, 255, 255, 0.1);
        }

        .avatar {
            border-radius: 2px;
            width: 30px;
            height: 30px;
            vertical-align: middle;
        }

        .name {
            display: inline-block;
            margin-left: 15px;
            margin-top: 0;
            margin-bottom: 0;
        }
    }
</style>
