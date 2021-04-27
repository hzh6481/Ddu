import Vue from 'vue'
import HelloWorld from '@/components/HelloWorld'
import Main from '@/components/Main'
import VueRouter from "vue-router";


Vue.use(VueRouter)
let routes = [
  {
    path: '/',
    name: 'HelloWorld',
    component: HelloWorld,
    hidden: true
  }, {
    // 首页
    path: '/main',
    name: '',
    iconCls: 'a fa-address-card',
    component: Main,
    hidden: true
  }, {
    path: '/',
    component: Main,
    name: '系统管理',
    children: [
      {
        path: '/User',
        name: '用户管理',
        iconCls: 'a fa-address-card',
        component: () => import('../views/buss/User')
      }, {
        path: '/Role',
        name: '角色管理',
        iconCls: 'a fa-address-card',
        component: () => import('../views/buss/Role')
      }, {
        path: '/UserInput',
        name: '用户新增',
        iconCls: 'a fa-address-card',
        query:{
          test:'111'
        },
        component: () => import('../views/buss/UserInput')
      }
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  routes
});
export default router;

