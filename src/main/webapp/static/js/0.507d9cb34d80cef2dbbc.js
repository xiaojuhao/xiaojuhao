webpackJsonp([0],{

/***/ 169:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(814)

var Component = __webpack_require__(263)(
  /* script */
  __webpack_require__(700),
  /* template */
  __webpack_require__(772),
  /* scopeId */
  "data-v-091fd157",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 700:
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
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            messages: [//messages
            {
                id: 1,
                type: "库存警告",
                message: '常州仓库三文鱼库存不足100，请及时补货'
            }, {
                id: 2,
                type: "库存警告",
                message: '常州仓库三文鱼库存不足100，请及时补货'
            }, {
                id: 4,
                type: "库存警告",
                message: '常州仓库三文鱼库存不足100，请及时补货'
            }, {
                id: 5,
                type: "库存警告",
                message: '常州仓库三文鱼库存不足100，请及时补货'
            }, {
                id: 6,
                type: "库存警告",
                message: '常州仓库三文鱼库存不足100，请及时补货'
            }, {
                id: 31,
                type: "库存警告",
                message: '常州仓库三文鱼库存不足100，请及时补货'
            }, {
                id: 32,
                type: "库存警告",
                message: '常州仓库三文鱼库存不足100，请及时补货'
            }]
        };
    }
});

/***/ }),

/***/ 732:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".container[data-v-091fd157]{width:80%;border:0 solid blue;margin:0 auto}.header-line[data-v-091fd157]{margin:auto;margin-left:40px;margin-bottom:10px}.query[data-v-091fd157]{margin-left:20px}.spaceline[data-v-091fd157]{display:inline-block;width:20px}.newline[data-v-091fd157]{margin-top:10px}tr[data-v-091fd157]{height:30px}", ""]);

// exports


/***/ }),

/***/ 772:
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
    return _c('tr', [_c('td', {
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
    }, [_vm._v(_vm._s(item.type))]), _vm._v(" "), _c('td', [_vm._v("测试测试！！！" + _vm._s(item.message))]), _vm._v(" "), _c('td', [_vm._v("2017-11-11")])])
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

/***/ }),

/***/ 814:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(732);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(265)("500599c0", content, true);

/***/ })

});