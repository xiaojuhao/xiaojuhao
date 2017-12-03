webpackJsonp([5],{

/***/ 630:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(710),
  /* template */
  __webpack_require__(807),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 658:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(263);
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
  props: ["excludes", "value", "context"],
  data() {
    return {
      allValues: [],
      valuesShow: [],
      selectedCode: ''
    };
  },
  mounted() {
    this.initData();
  },
  watch: {
    value(nval, oval) {
      this.initData();
    }
  },
  methods: {
    setValue() {
      this.$emit("input", this.selectedCode, this.$props.context);
    },
    initData() {
      this.allValues = this.$store.state.allMaterials;
      this.selectedCode = this.$props.value;
      // let $data = this.$data;
      // api.queryAllMaterials()
      // .then((value)=>{
      //   $data.allValues = value.values;
      //   $data.selectedCode = this.$props.value;
      // });
    },
    enterkey(e) {
      console.log(e);
    },
    filterMethod(input) {
      let $data = this.$data;
      setTimeout(() => {
        $data.valuesShow = $data.allValues.filter(item => {
          var key = [item.materialCode, item.materialName, item.searchKey].join(',');
          if (key.indexOf(input) >= 0) {
            return true;
          }
          return false;
        });
      }, 10);
    },
    visualChange(visible) {
      if (visible) {
        this.$data.valuesShow = this.$data.allValues.filter(item => {
          if (this.excludesMap[item.materialCode]) {
            return false;
          }
          return true;
        });
      }
    }
  },
  computed: {
    excludesMap() {
      let map = {};
      this.$props.excludes && this.$props.excludes.forEach(item => {
        map[item] = 1;
      });
      return map;
    }
  }
});

/***/ }),

/***/ 659:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(658),
  /* template */
  __webpack_require__(660),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 660:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('el-select', {
    attrs: {
      "placeholder": "请选择",
      "filterable": "",
      "clearable": "",
      "filter-method": _vm.filterMethod
    },
    on: {
      "change": _vm.setValue,
      "visible-change": _vm.visualChange
    },
    nativeOn: {
      "keyup": function($event) {
        if (!('button' in $event) && _vm._k($event.keyCode, "enter", 13, $event.key)) { return null; }
        _vm.enterkey($event)
      }
    },
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.valuesShow), function(item) {
    return _c('el-option', {
      key: item.materialCode,
      attrs: {
        "label": item.materialName + '-' + item.materialCode,
        "value": item.materialCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 661:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(663),
  /* template */
  __webpack_require__(666),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 662:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//


/* harmony default export */ __webpack_exports__["default"] = ({
    props: ["excludes", "value", "context"],
    data() {
        return {
            allValues: [],
            valuesShow: [],
            selectedCode: ''
        };
    },
    mounted() {
        this.initData();
    },
    watch: {
        value(nval, oval) {
            this.initData();
        }
    },
    methods: {
        setValue() {
            this.$emit("input", this.selectedCode, this.$props.context);
        },
        initData() {
            __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].queryAllRecipes().then(value => {
                this.allValues = value;
                this.selectedCode = this.$props.value;
            });
        },
        filterMethod(input) {
            setTimeout(() => {
                this.valuesShow = this.allValues.filter(item => {
                    var key = [item.recipesCode, item.recipesName, item.searchKey].join(',');
                    return __WEBPACK_IMPORTED_MODULE_0__bus__["c" /* util */].matchSearch(key, input);
                });
            }, 10);
        },
        visualChange(visible) {
            if (visible) {
                this.$data.valuesShow = this.$data.allValues.filter(item => {
                    if (this.excludesMap[item.recipesCode]) {
                        return false;
                    }
                    return true;
                });
            }
        }
    },
    computed: {
        excludesMap() {
            let map = {};
            this.$props.excludes && this.$props.excludes.forEach(item => {
                map[item] = 1;
            });
            return map;
        }
    }
});

/***/ }),

/***/ 663:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(263);
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
  props: ["excludes", "value", "context"],
  data() {
    return {
      allValues: [],
      valuesShow: [],
      selectedCode: ''
    };
  },
  mounted() {
    this.initData();
  },
  watch: {
    value(nval, oval) {
      this.initData();
    }
  },
  methods: {
    setValue() {
      this.$emit("input", this.$props.context, this.selectedCode);
    },
    initData() {
      let $data = this.$data;
      __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].getAllStoreList().then(value => {
        $data.allValues = value;
        $data.selectedCode = this.$props.value;
      });
    },
    enterkey(e) {},
    filterMethod(input) {
      let $data = this.$data;
      setTimeout(() => {
        $data.valuesShow = $data.allValues.filter(item => {
          var key = [item.storeCode, item.storeName, item.searchKey].join(',');
          if (key.indexOf(input) >= 0) {
            return true;
          }
          return false;
        });
      }, 10);
    },
    visualChange(visible) {
      if (visible) {
        this.$data.valuesShow = this.$data.allValues.filter(item => {
          if (this.excludesMap[item.storeCode]) {
            return false;
          }
          return true;
        });
      }
    }
  },
  computed: {
    excludesMap() {
      let map = {};
      this.$props.excludes && this.$props.excludes.forEach(item => {
        map[item] = 1;
      });
      return map;
    }
  }
});

/***/ }),

/***/ 664:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(662),
  /* template */
  __webpack_require__(665),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 665:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('el-select', {
    attrs: {
      "placeholder": "请选择",
      "filterable": "",
      "clearable": "",
      "filter-method": _vm.filterMethod
    },
    on: {
      "change": _vm.setValue,
      "visible-change": _vm.visualChange
    },
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.valuesShow), function(item) {
    return _c('el-option', {
      key: item.recipesCode,
      attrs: {
        "label": item.recipesName,
        "value": item.recipesCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 666:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('el-select', {
    attrs: {
      "placeholder": "请选择",
      "filterable": "",
      "filter-method": _vm.filterMethod
    },
    on: {
      "change": _vm.setValue,
      "visible-change": _vm.visualChange
    },
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.valuesShow), function(item) {
    return _c('el-option', {
      key: item.storeCode,
      attrs: {
        "label": item.storeName,
        "value": item.storeCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 676:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(263);
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
			allValues: [],
			valuesShow: [],
			selectedCode: ''
		};
	},
	mounted() {
		let $data = this.$data;
		__WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].getAllWarehouseList().then(list => {
			$data.allValues = list;
		});
	},
	methods: {
		setValue() {
			this.$emit("setValue", this.selectedCode);
		},
		filterMethod(input) {
			let $data = this.$data;
			setTimeout(() => {
				$data.valuesShow = $data.allValues.filter(item => {
					var key = item.searchKey;
					if (!key || key.indexOf(input) >= 0) {
						return true;
					}
					return false;
				});
			}, 10);
		},
		visualChange(visible) {
			if (visible) this.$data.valuesShow = this.$data.allValues;
		}
	}
});

/***/ }),

/***/ 679:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(676),
  /* template */
  __webpack_require__(681),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 681:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('el-select', {
    attrs: {
      "placeholder": "请选择"
    },
    on: {
      "change": _vm.setValue,
      "visible-change": _vm.visualChange
    },
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.allValues), function(item) {
    return _c('el-option', {
      key: item.warehouseCode,
      attrs: {
        "label": item.warehouseName,
        "value": item.warehouseCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 710:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_StoreSelection__ = __webpack_require__(661);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_StoreSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__common_StoreSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__ = __webpack_require__(659);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__ = __webpack_require__(664);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__common_WarehouseSelection__ = __webpack_require__(679);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__common_WarehouseSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3__common_WarehouseSelection__);
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
            msg: 'ASSDDSDS vcvcvc',
            excludes: ["aaaa", "bbbb", "ccccc"]
        };
    },
    components: {
        StoreSelection: __WEBPACK_IMPORTED_MODULE_0__common_StoreSelection___default.a,
        MaterialSelection: __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default.a,
        RecipesSelection: __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default.a,
        WarehouseSelection: __WEBPACK_IMPORTED_MODULE_3__common_WarehouseSelection___default.a
    },
    methods: {
        click() {
            console.log(this.$store.state.allMaterials);
            console.log(this.$store.state.allRecipes);
            console.log(this.$store.state.allWarehouses);
            console.log(this.$store.state.allStores);
        },
        setValue(code) {
            this.$data.excludes.push(code);
        }
    },
    mounted() {
        this.$store.dispatch('loadAllData');
    }
});

/***/ }),

/***/ 807:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('MaterialSelection', {
    attrs: {
      "excludes": _vm.excludes,
      "initValue": "M00003"
    },
    on: {
      "setValue": _vm.setValue
    }
  }), _vm._v(" "), _c('MaterialSelection', {
    attrs: {
      "excludes": _vm.excludes,
      "initValue": "M00003"
    },
    on: {
      "setValue": _vm.setValue
    }
  }), _vm._v(" "), _c('MaterialSelection', {
    attrs: {
      "excludes": _vm.excludes
    },
    on: {
      "setValue": _vm.setValue
    }
  }), _vm._v(" "), _c('MaterialSelection', {
    attrs: {
      "excludes": _vm.excludes
    },
    on: {
      "setValue": _vm.setValue
    }
  }), _vm._v(" "), _c('el-button', {
    on: {
      "click": _vm.click
    }
  }, [_vm._v("点击")])], 1)
},staticRenderFns: []}

/***/ })

});