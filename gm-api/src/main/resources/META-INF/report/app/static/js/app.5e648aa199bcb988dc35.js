webpackJsonp([6],[,function(t,n,e){"use strict";var o=e(0),a=e(21);o.default.use(a.a);var i=new a.a.Store({state:{loginForm:{username:"",password:""},loginStatus:!1},getter:{userLoginStatus:function(t){return console.log("入参zzzzz：",t.loginStatus),t.loginStatus}},mutations:{SET_LOGINSTATUS:function(t,n){t.loginStatus=n,console.log("入参：",t.loginStatus)}},actions:{setloginStatus:function(t,n){var e=(t.dispatch,t.commit);t.state;e("SET_LOGINSTATUS",n)}}});n.a=i},,,function(t,n,e){"use strict";var o=e(0),a=e(19);o.default.use(a.a);var i=function(t){return e.e(4).then(function(){var n=[e(25)];t.apply(null,n)}.bind(this)).catch(e.oe)},r=function(t){return e.e(1).then(function(){var n=[e(28)];t.apply(null,n)}.bind(this)).catch(e.oe)},u=function(t){return e.e(2).then(function(){var n=[e(27)];t.apply(null,n)}.bind(this)).catch(e.oe)},c=function(t){return e.e(0).then(function(){var n=[e(24)];t.apply(null,n)}.bind(this)).catch(e.oe)},l=function(t){return e.e(3).then(function(){var n=[e(26)];t.apply(null,n)}.bind(this)).catch(e.oe)},s=new a.a({routes:[{path:"/main",component:i},{path:"/reportList",component:r},{path:"/reportInfo",component:u},{path:"/personal",component:l},{path:"/login",component:c},{path:"/",redirect:"main"}]});s.beforeEach(function(t,n,e){"/login"===t.path&&localStorage.removeItem("Authorization"),localStorage.getItem("Authorization")||"/login"===t.path?e():(e({path:"/login",query:{redirect:t.fullPath}}),window.scrollTo(0,0))}),s.afterEach(function(){window.scrollTo(0,0)}),n.a=s},function(t,n){!function(t,n){var e=t.documentElement,o="orientationchange"in window?"orientationchange":"resize",a=function(){var t=e.clientWidth;t&&(e.style.fontSize=t/375*20+"px")};t.addEventListener&&(n.addEventListener(o,a,!1),t.addEventListener("DOMContentLoaded",a,!1))}(document,window)},function(t,n){},function(t,n,e){function o(t){e(13)}var a=e(8)(e(11),e(18),o,null,null);t.exports=a.exports},,,,function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default={name:"app"}},function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var o=e(0),a=e(7),i=e.n(a),r=e(4),u=e(2),c=e.n(u),l=e(6),s=(e.n(l),e(1)),p=e(5);e.n(p);o.default.use(c.a),o.default.config.productionTip=!1,new o.default({el:"#app",router:r.a,store:s.a,template:"<App/>",components:{App:i.a}})},function(t,n){},,,,,function(t,n){t.exports={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{attrs:{id:"app"}},[e("router-view")],1)},staticRenderFns:[]}}],[12]);
//# sourceMappingURL=app.5e648aa199bcb988dc35.js.map