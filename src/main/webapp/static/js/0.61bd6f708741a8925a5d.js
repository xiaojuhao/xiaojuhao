webpackJsonp([0],{

/***/ 532:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
	methods: {
		change() {
			// if(this.$store.state.sideBarOppened){
			// 	this.$store.state.sideBarOppened = false;
			// }else{
			// 	this.$store.state.sideBarOppened = true;
			// }
			//console.log(this.$store.state.sideBarOppened)
			//this.$store.commit('toggleSideBarOppened')
			this.$store.dispatch('changeState', true);
		}
	}
});

/***/ }),

/***/ 551:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('button', {
    on: {
      "click": _vm.change
    }
  }, [_vm._v("点击")])])
},staticRenderFns: []}

/***/ }),

/***/ 59:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(198)(
  /* script */
  __webpack_require__(532),
  /* template */
  __webpack_require__(551),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ })

});