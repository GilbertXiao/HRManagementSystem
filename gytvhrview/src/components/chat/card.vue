<template>
    <div id="card">
        <header>
            <img class="avatar" v-bind:src="user.userface" v-bind:alt="user.name">
            <p class="name">{{user.name}}</p>
        </header>
        <footer>
            <input class="search" type="text" v-model="$store.state.filterKey" placeholder="search user..." @keydown.enter="filterHr">
        </footer>
    </div>
</template>

<script>


    export default {
        name: 'card',
        data() {
            return {
                user: this.$store.state.user,
            }
        },
        methods:{
            filterHr:function () {
                alert("1111");
                this.getRequest('/chat/hr?keyword='+this.$store.state.filterKey).then(resp => {
                    if (resp) {
                        this.$store.commit('INIT_HR',resp);
                    }
                })
            }
        }
    }
</script>

<style lang="scss" scoped>
    #card {
        padding: 12px;

        .avatar {
            width: 40px;
            height: 40px;
            vertical-align: middle; /*这个是图片和文字居中对齐*/
        }

        .name {
            display: inline-block;
            padding: 10px;
            margin-bottom: 15px;
            font-size: 16px;
        }

        .search {
            background: #26292E;
            height: 30px;
            line-height: 30px;
            padding: 0 10px;
            border: 1px solid #3a3a3a;
            border-radius: 4px;
            outline: none; /*鼠标点击后不会出现蓝色边框*/
            color: #FFF;
        }
    }
</style>
