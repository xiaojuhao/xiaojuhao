webpackJsonp([0],{

/***/ 1043:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(951);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("500599c0", content, true);

/***/ }),

/***/ 266:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1043)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(912),
  /* template */
  __webpack_require__(995),
  /* scopeId */
  "data-v-091fd157",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 912:
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


/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            messages: []
        };
    },
    methods: {
        formatDate(item) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* util */].formatDate(item.gmtCreated);
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getNoticePage({}).then(page => {
            this.messages = page.values;
        });
    }
});

/***/ }),

/***/ 951:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".container[data-v-091fd157]{width:80%;border:0 solid blue;margin:0 auto}.header-line[data-v-091fd157]{margin:auto;margin-left:40px;margin-bottom:10px}.query[data-v-091fd157]{margin-left:20px}.spaceline[data-v-091fd157]{display:inline-block;width:20px}.newline[data-v-091fd157]{margin-top:10px}tr[data-v-091fd157]{height:30px}", ""]);

// exports


/***/ }),

/***/ 995:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "container",
    attrs: {
      "id": "app"
    }
  }, [_vm._m(0), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-left": "40px",
      "margin-bottom": "40px"
    }
  }, [_c('table', {
    staticStyle: {
      "border-collapse": "collapse",
      "min-width": "80%"
    },
    attrs: {
      "border": "1",
      "bordercolor": "#a0c6e5"
    }
  }, _vm._l((_vm.messages), function(item) {
    return _c('tr', {
      key: item.id
    }, [_c('td', {
      staticStyle: {
        "width": "50px"
      },
      attrs: {
        "align": "center"
      }
    }, [_vm._v(_vm._s(item.id))]), _vm._v(" "), _c('td', {
      staticStyle: {
        "width": "100px"
      }
    }, [_vm._v("告警信息")]), _vm._v(" "), _c('td', [_vm._v(_vm._s(item.content))]), _vm._v(" "), _c('td', [_vm._v(_vm._s(_vm.formatDate(item)))])])
  }))]), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-left": "40px",
      "margin-bottom": "40px"
    }
  })])
},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "header-line"
  }, [_c('h4', [_vm._v("系统消息通知栏")])])
}]}

/***/ })

});