<template>
  <div id="app">
<!--    <img src="./assets/logo.png">-->
    <router-view :key="key"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  computed:{
    key(){
      return this.$route?this.$route.name+ +new Date():this.$route+ +new Date()
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
