webpackJsonp([25],{

/***/ 627:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(766)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(691),
  /* template */
  __webpack_require__(738),
  /* scopeId */
  "data-v-212529a6",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 691:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            message: "暂时没有系统设置需求"
        };
    },
    mounted() {
        const h = this.$createElement;
        this.$notify({
            title: '系统提示',
            message: h('i', { style: 'color: teal' }, '暂时没有系统设置需求'),
            offset: 60
        });
    }
});

/***/ }),

/***/ 707:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-212529a6]{margin-bottom:0}.handle-select[data-v-212529a6]{width:120px}.handle-input[data-v-212529a6]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 738:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_vm._v("\n    " + _vm._s(_vm.message) + "\n")])
},staticRenderFns: []}

/***/ }),

/***/ 766:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(707);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("6990fb62", content, true);

/***/ })

});