webpackJsonp([5],{

/***/ 621:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(689),
  /* template */
  __webpack_require__(764),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 645:
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

/***/ 646:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(645),
  /* template */
  __webpack_require__(647),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 647:
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

/***/ 648:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(650),
  /* template */
  __webpack_require__(653),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 649:
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
            let $data = this.$data;
            __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].queryAllRecipes().then(value => {
                $data.allValues = value;
                $data.selectedCode = this.$props.value;
            });
        },
        filterMethod(input) {
            let $data = this.$data;
            setTimeout(() => {
                $data.valuesShow = $data.allValues.filter(item => {
                    var key = [item.recipesCode, item.recipesName, item.searchKey].join(',');
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

/***/ 650:
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

/***/ 651:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(649),
  /* template */
  __webpack_require__(652),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 652:
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

/***/ 653:
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

/***/ 661:
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

/***/ 664:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(661),
  /* template */
  __webpack_require__(666),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 666:
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

/***/ 689:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_StoreSelection__ = __webpack_require__(648);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_StoreSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__common_StoreSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__ = __webpack_require__(646);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__ = __webpack_require__(651);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__common_WarehouseSelection__ = __webpack_require__(664);
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

/***/ 764:
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