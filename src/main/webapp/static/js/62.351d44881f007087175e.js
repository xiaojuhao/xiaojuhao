webpackJsonp([62],{

/***/ 1054:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("库存报损")])], 1)], 1), _vm._v(" "), _vm._m(0), _vm._v(" "), _c('section', [_vm._l((_vm.mywarehouse), function(item) {
    return _c('router-link', {
      key: item.id,
      attrs: {
        "to": 'baosunPage?CODE=' + item.warehouseCode
      }
    }, [_c('el-card', {
      staticClass: "card",
      attrs: {
        "body-style": {
          padding: '0px'
        }
      }
    }, [_c('img', {
      staticClass: "image",
      attrs: {
        "src": item.headImg
      }
    }), _vm._v(" "), _c('div', {
      staticStyle: {
        "padding": "14px"
      }
    }, [_c('div', {
      staticClass: "bottom clearfix align-center"
    }, [_c('span', [_vm._v(_vm._s(item.warehouseName))])])])])], 1)
  }), _vm._v(" "), _vm._l((_vm.myStores), function(item) {
    return _c('router-link', {
      key: item.id,
      attrs: {
        "to": 'baosunPage?CODE=' + item.storeCode
      }
    }, [_c('el-card', {
      staticClass: "card",
      attrs: {
        "body-style": {
          padding: '0px'
        }
      }
    }, [_c('img', {
      staticClass: "image",
      attrs: {
        "src": item.storeImg
      }
    }), _vm._v(" "), _c('div', {
      staticStyle: {
        "padding": "14px"
      }
    }, [_c('div', {
      staticClass: "bottom clearfix align-center"
    }, [_c('span', [_vm._v(_vm._s(item.storeName))])])])])], 1)
  })], 2), _vm._v(" "), _c('footer')])
},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('header', [_c('div', {
    staticClass: "title"
  }, [_vm._v("请选择对应的仓库或门店进行操作")])])
}]}

/***/ }),

/***/ 1121:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(989);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("69e3e22f", content, true);

/***/ }),

/***/ 616:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1121)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(922),
  /* template */
  __webpack_require__(1054),
  /* scopeId */
  "data-v-1874e315",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 922:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
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
            message: 'messsage',
            mywarehouse: [],
            myStores: [],
            currentDate: '2017-11-12'
        };
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMyWarehouse().then(list => {
            this.$data.mywarehouse = list;
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMyStores().then(list => {
            this.$data.myStores = list;
        });
    }
});

/***/ }),

/***/ 989:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".title[data-v-1874e315]{width:100%;margin-left:60px}header[data-v-1874e315],section[data-v-1874e315]{width:80%}.card[data-v-1874e315]{width:200px;height:200px;float:left;margin-left:10px;margin-top:10px}.time[data-v-1874e315]{font-size:13px;color:#999}.bottom[data-v-1874e315]{margin-top:13px;line-height:12px}.button[data-v-1874e315]{padding:0;float:right}.image[data-v-1874e315]{width:100%;height:140px;display:inline}.clearfix[data-v-1874e315]:after,.clearfix[data-v-1874e315]:before{display:table;content:\"\"}.clearfix[data-v-1874e315]:after{clear:both}.align-center[data-v-1874e315]{text-align:center}", ""]);

// exports


/***/ })

});