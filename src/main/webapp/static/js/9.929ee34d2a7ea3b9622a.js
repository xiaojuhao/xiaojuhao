webpackJsonp([9],{

/***/ 611:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(666)

var Component = __webpack_require__(261)(
  /* script */
  __webpack_require__(661),
  /* template */
  __webpack_require__(664),
  /* scopeId */
  "data-v-67028873",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 644:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(261)(
  /* script */
  __webpack_require__(645),
  /* template */
  __webpack_require__(649),
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
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(262);
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

/***/ 649:
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

/***/ 652:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(262);
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
      this.$emit("input", this.$props.context, this.selectedCode);
    },
    initData() {
      let $data = this.$data;
      __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].queryAllRecipes().then(value => {
        $data.allValues = value;
        $data.selectedCode = this.$props.value;
      });
    },
    enterkey(e) {},
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

/***/ 654:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(261)(
  /* script */
  __webpack_require__(652),
  /* template */
  __webpack_require__(655),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 655:
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
      key: item.recipesCode,
      attrs: {
        "label": item.recipesName,
        "value": item.recipesCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 661:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(262);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection__ = __webpack_require__(644);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_StoreSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__ = __webpack_require__(654);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__);
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
            storeCode: '',
            recipesList: [],
            allRecipes: [],
            loadingState: false
        };
    },
    methods: {
        onCancel() {
            this.recipesList = [];
        },
        onSubmit() {
            this.$data.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* http */].jsonp2("/busi/outstockByRecipes", {
                storeCode: this.$data.storeCode,
                recipesJson: JSON.stringify(this.$data.recipesList)
            }).then(value => {
                this.$message("提交成功");
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.$data.loadingState = false;
            });
        },
        addRows() {
            this.$data.recipesList.push({
                recipesCode: '',
                recipesName: '',
                amt: 0
            });
        },
        removeRows(index) {
            this.$data.recipesList.splice(index, 1);
        },
        addNewRecipes(ctx, recipesCode) {
            let item = this.recipesMap[recipesCode];
            if (!item) return;
            Object.keys(item).forEach(key => ctx[key] = item[key]);
        },
        selectStore(ctx, val) {
            this.$data.storeCode = val;
        }
    },
    mounted() {
        let self = this;
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryAllRecipes().then(values => {
            self.$data.allRecipes = values;
        });
    },
    computed: {
        recipesMap() {
            let map = {};
            this.$data.allRecipes.forEach(item => {
                map[item.recipesCode] = item;
            });
            return map;
        },
        addedRecipesCode() {
            let ll = [];
            Object.keys(this.$data.recipesList).forEach(i => {
                ll.push(this.$data.recipesList[i].recipesCode);
            });
            return ll;
        }
    },
    components: {
        StoreSelection: __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection___default.a,
        RecipesSelection: __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default.a
    }
});

/***/ }),

/***/ 662:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".form-box[data-v-67028873]{margin-top:20px}.border-table[data-v-67028873]{border-collapse:collapse;border:none}.border-table td[data-v-67028873],.border-table th[data-v-67028873]{border:1px solid #000}.el-input[data-v-67028873]{width:150px}.el-row[data-v-67028873]{margin-bottom:5px;&:last-child{margin-bottom:0}}", ""]);

// exports


/***/ }),

/***/ 664:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "form-box"
  }, [_c('el-form', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState),
      expression: "loadingState"
    }],
    ref: "form",
    attrs: {
      "label-width": "80px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "门店"
    }
  }, [_c('el-row', [_c('el-col', {
    attrs: {
      "span": 12
    }
  }, [_c('StoreSelection', {
    attrs: {
      "value": _vm.storeCode
    },
    on: {
      "input": _vm.selectStore
    }
  })], 1), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 12
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "plus"
    },
    on: {
      "click": _vm.addRows
    }
  })], 1)], 1)], 1), _vm._v(" "), _c('el-form-item', _vm._l((_vm.recipesList), function(item, index) {
    return _c('div', [_c('el-row', [_c('el-col', {
      attrs: {
        "span": 10
      }
    }, [_c('RecipesSelection', {
      attrs: {
        "value": item.recipesCode,
        "context": item,
        "excludes": _vm.addedRecipesCode
      },
      on: {
        "input": _vm.addNewRecipes
      }
    })], 1), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 8
      }
    }, [_c('el-input', {
      attrs: {
        "placeholder": "请输入份数"
      },
      model: {
        value: (item.amt),
        callback: function($$v) {
          _vm.$set(item, "amt", $$v)
        },
        expression: "item.amt"
      }
    }, [_c('template', {
      attrs: {
        "slot": "prepend"
      },
      slot: "prepend"
    }, [_vm._v("份数")])], 2)], 1), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 6
      }
    }, [_c('el-button', {
      attrs: {
        "type": "error",
        "size": "small",
        "icon": "delete"
      },
      on: {
        "click": function($event) {
          _vm.removeRows(index)
        }
      }
    })], 1)], 1)], 1)
  })), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("提交")]), _vm._v(" "), _c('el-button', {
    on: {
      "click": _vm.onCancel
    }
  }, [_vm._v("清空")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 666:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(662);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(263)("5f38c596", content, true);

/***/ })

});