import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Index from "../components/Index";
import BookInfo from "../components/BookInfo";
Vue.use(Router)

export default new Router({
  base:'/bookstore',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path:'/',
      name:'Index',
      component:Index
    },
    {
      path:'/bookinfo/:id',
      name:'BookInfo',
      component: BookInfo
    }
  ]
})
