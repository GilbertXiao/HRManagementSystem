<template>

    <div v-if="currentSession" class="sessionTitle">{{currentSession.name}}</div>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: 'titleCurrentSession',
        data() {
            return {
            }
        },
        computed: mapState([
            'currentSession',
        ]),

    }
</script>

<style lang="scss" scoped>
    .sessionTitle{
        background-color: #dcdcdc;
        height: 6%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>
