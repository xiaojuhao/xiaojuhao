webpackJsonp([4],{

/***/ 505:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(526),
  /* template */
  __webpack_require__(552),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 525:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            name: 'unknown'
        };
    },
    computed: {
        username() {
            let username = localStorage.getItem('ms_username');
            return username ? username : this.name;
        }
    },
    methods: {
        handleCommand(command) {
            if (command == 'loginout') {
                localStorage.removeItem('ms_username');
                this.$router.push('/login');
            }
        }
    }
});

/***/ }),

/***/ 526:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__Header_vue__ = __webpack_require__(545);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__Header_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__Header_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__Sidebar_vue__ = __webpack_require__(546);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__Sidebar_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__Sidebar_vue__);
//
//
//
//
//
//
//
//
//
//



/* harmony default export */ __webpack_exports__["default"] = ({
    components: {
        vHead: __WEBPACK_IMPORTED_MODULE_0__Header_vue___default.a, vSidebar: __WEBPACK_IMPORTED_MODULE_1__Sidebar_vue___default.a
    }
});

/***/ }),

/***/ 527:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_axios__ = __webpack_require__(199);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_axios___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_axios__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//



/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            items: [{
                icon: 'el-icon-menu',
                index: 'indexPage',
                title: '首页'
            }, {
                icon: 'el-icon-setting',
                index: '2',
                title: '系统管理',
                subs: [{
                    index: 'userManage',
                    title: '用户管理'
                }, {
                    index: 'roleManage',
                    title: '角色管理'
                }, {
                    index: 'sysConfig',
                    title: '系统设置'
                }]
            }, {
                icon: 'el-icon-setting',
                index: '3',
                title: '基础信息管理',
                subs: [{
                    index: 'materialManage',
                    title: '原料管理'
                }, {
                    index: 'dishesManage',
                    title: '菜品管理'
                }, {
                    index: 'wharehouseManage',
                    title: '仓库管理'
                }, {
                    index: 'storeManage',
                    title: '门店管理'
                }]
            }, {
                icon: 'el-icon-setting',
                index: '4',
                title: '进销存管理',
                subs: [{
                    index: 'inventoryManage',
                    title: '出库入库'
                }, {
                    index: 'pandian',
                    title: '库存盘点'
                }, {
                    index: 'diaobo',
                    title: '库存调拨'
                }]
            }, {
                icon: 'el-icon-setting',
                index: '5',
                title: '任务管理',
                subs: [{
                    index: 'waitingTask',
                    title: '待处理任务'
                }, {
                    index: 'myTask',
                    title: '我的申请'
                }]
            }]
        };
    },
    created: function () {
        // var jsonp = require('jsonp');
        // var datax = this;
        // jsonp('http://localhost:8080/menu',null,function(err,data){
        //     console.log(data.value)
        //     console.log(datax.items)
        //     datax.items = data.value
        //     console.log(datax.$router)
        // })
    },
    computed: {
        onRoutes() {
            return this.$route.path.replace('/', '');
        }
    }
});

/***/ }),

/***/ 538:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(87)(undefined);
// imports


// module
exports.push([module.i, ".header[data-v-1e5b606c]{position:relative;box-sizing:border-box;width:100%;height:70px;font-size:22px;line-height:70px;color:#fff}.header .logo[data-v-1e5b606c]{float:left;width:250px;text-align:center}.user-info[data-v-1e5b606c]{float:right;padding-right:50px;font-size:16px;color:#fff}.user-info .el-dropdown-link[data-v-1e5b606c]{position:relative;display:inline-block;padding-left:50px;color:#fff;cursor:pointer;vertical-align:middle}.user-info .user-logo[data-v-1e5b606c]{position:absolute;left:0;top:15px;width:40px;height:40px;border-radius:50%}.el-dropdown-menu__item[data-v-1e5b606c]{text-align:center}", ""]);

// exports


/***/ }),

/***/ 541:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(87)(undefined);
// imports


// module
exports.push([module.i, ".sidebar[data-v-65efe922]{display:block;position:absolute;width:250px;left:0;top:70px;bottom:0;background:#2e363f}.sidebar>ul[data-v-65efe922]{height:100%}", ""]);

// exports


/***/ }),

/***/ 544:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "static/img/img.2aab7b4.jpg";

/***/ }),

/***/ 545:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(561)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(525),
  /* template */
  __webpack_require__(549),
  /* scopeId */
  "data-v-1e5b606c",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 546:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(564)

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(527),
  /* template */
  __webpack_require__(556),
  /* scopeId */
  "data-v-65efe922",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 549:
/***/ (function(module, exports, __webpack_require__) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "header"
  }, [_c('div', {
    staticClass: "logo"
  }, [_vm._v("后台管理系统")]), _vm._v(" "), _c('div', {
    staticClass: "user-info"
  }, [_c('el-dropdown', {
    attrs: {
      "trigger": "click"
    },
    on: {
      "command": _vm.handleCommand
    }
  }, [_c('span', {
    staticClass: "el-dropdown-link"
  }, [_c('img', {
    staticClass: "user-logo",
    attrs: {
      "src": __webpack_require__(544)
    }
  }), _vm._v("\n                " + _vm._s(_vm.username) + "\n            ")]), _vm._v(" "), _c('el-dropdown-menu', {
    attrs: {
      "slot": "dropdown"
    },
    slot: "dropdown"
  }, [_c('el-dropdown-item', {
    attrs: {
      "command": "loginout"
    }
  }, [_vm._v("退出")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 552:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "wrapper"
  }, [_c('v-head'), _vm._v(" "), _c('v-sidebar'), _vm._v(" "), _c('div', {
    staticClass: "content"
  }, [_c('transition', {
    attrs: {
      "name": "move",
      "mode": "out-in"
    }
  }, [_c('router-view')], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 556:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "sidebar"
  }, [_c('el-menu', {
    staticClass: "el-menu-vertical-demo",
    attrs: {
      "default-active": _vm.onRoutes,
      "theme": "dark",
      "unique-opened": "",
      "router": ""
    }
  }, [_vm._l((_vm.items), function(item) {
    return [(item.subs) ? [_c('el-submenu', {
      attrs: {
        "index": item.index
      }
    }, [_c('template', {
      attrs: {
        "slot": "title"
      },
      slot: "title"
    }, [_c('i', {
      class: item.icon
    }), _vm._v(_vm._s(item.title))]), _vm._v(" "), _vm._l((item.subs), function(subItem, i) {
      return _c('el-menu-item', {
        key: i,
        attrs: {
          "index": subItem.index
        }
      }, [_vm._v(_vm._s(subItem.title) + "\n                    ")])
    })], 2)] : [_c('el-menu-item', {
      attrs: {
        "index": item.index
      }
    }, [_c('i', {
      class: item.icon
    }), _vm._v(_vm._s(item.title) + "\n                ")])]]
  })], 2)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 561:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(538);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("3ad29ecc", content, true);

/***/ }),

/***/ 564:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(541);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(198)("2516572a", content, true);

/***/ })

});