webpackJsonp([14],{

/***/ 623:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(771)

var Component = __webpack_require__(261)(
  /* script */
  __webpack_require__(682),
  /* template */
  __webpack_require__(748),
  /* scopeId */
  "data-v-a7e18560",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 651:
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

/***/ 653:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(261)(
  /* script */
  __webpack_require__(651),
  /* template */
  __webpack_require__(656),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 656:
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
      key: item.materialCode,
      attrs: {
        "label": item.materialName + '-' + item.materialCode,
        "value": item.materialCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 682:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(262);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__ = __webpack_require__(653);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__);
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
    data: function () {
        return {
            form: {
                id: '',
                recipesCode: this.$route.query.code,
                recipesName: '',
                formula: []
            },
            allMaterials: [],
            loadingState: false
        };
    },
    methods: {
        onSubmit() {
            this.$data.loadingState = true;
            var $data = this.$data;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].addRecipes({
                recipesCode: $data.form.recipesCode,
                recipesName: $data.form.recipesName,
                formulaJson: JSON.stringify($data.form.formula)
            }).then(resp => {
                this.$message("操作成功");
                this.$router.go(-1);
            }).always(() => {
                this.$data.loadingState = false;
            });
        },
        onCancel() {
            this.$router.go(-1);
        },
        removeFormula(index) {
            this.$data.form.formula.splice(index, 1);
        },
        addFormulaItem() {
            this.$data.form.formula.push({
                id: 0,
                materialUnit: '',
                materialAmt: 0,
                materialCode: ''
            });
        },
        addNewFormula(ctx, val) {
            let item = this.$store.getters.allMaterialsMap.get(val);
            Object.keys(item).forEach(key => {
                ctx[key] = item[key];
            });
            ctx.materialUnit = item.stockUnit;
        }
    },
    mounted() {
        this.$store.commit('ensureLoadAll');
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryRecipesByCode(this.$data.form.recipesCode).then(resp => {
            this.$data.form.recipesName = resp.recipesName;
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryRecipesFormula(this.$data.form.recipesCode).then(values => {
            this.$data.form.formula = values;
        });
    },
    computed: {
        addedMaterials() {
            let ll = [];
            this.$data.form.formula.forEach(item => ll.push(item.materialCode));
            return ll;
        }
    },
    components: {
        MaterialSelection: __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default.a
    }
});

/***/ }),

/***/ 717:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".span-center[data-v-a7e18560]{display:inline-block;width:100%;font-weight:700}.grid-content[data-v-a7e18560]{min-height:1px}.el-row[data-v-a7e18560]{margin-bottom:4px;&:last-child{margin-bottom:0}}.el-col[data-v-a7e18560]{border-radius:4px}", ""]);

// exports


/***/ }),

/***/ 748:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 基础信息管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("菜品管理")])], 1)], 1), _vm._v(" "), _c('div', {
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
      "label": "菜品名称"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "菜品名称"
    },
    model: {
      value: (_vm.form.recipesName),
      callback: function($$v) {
        _vm.$set(_vm.form, "recipesName", $$v)
      },
      expression: "form.recipesName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "菜品代码"
    }
  }, [_c('el-input', {
    attrs: {
      "disabled": "",
      "placeholder": "菜品代码"
    },
    model: {
      value: (_vm.form.recipesCode),
      callback: function($$v) {
        _vm.$set(_vm.form, "recipesCode", $$v)
      },
      expression: "form.recipesCode"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "配方"
    }
  }, [_c('el-row', {
    attrs: {
      "gutter": 5
    }
  }, [_c('el-col', {
    attrs: {
      "span": 12
    }
  }, [_c('span', {
    staticClass: "span-center"
  }, [_vm._v("原料")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('span', {
    staticClass: "span-center"
  }, [_vm._v("数量")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 3
    }
  }, [_c('span', {
    staticClass: "span-center"
  }, [_vm._v("单位")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 3
    }
  }, [_c('span', {
    staticClass: "span-center"
  }, [_vm._v("操作")])])], 1), _vm._v(" "), _vm._l((_vm.form.formula), function(ff, index) {
    return _c('el-row', {
      attrs: {
        "gutter": 5
      }
    }, [_c('el-col', {
      attrs: {
        "span": 12
      }
    }, [_c('MaterialSelection', {
      attrs: {
        "value": ff.materialCode,
        "context": ff,
        "excludes": _vm.addedMaterials
      },
      on: {
        "input": _vm.addNewFormula
      }
    })], 1), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 4
      }
    }, [_c('el-input', {
      attrs: {
        "placeholder": "数量"
      },
      model: {
        value: (ff.materialAmt),
        callback: function($$v) {
          _vm.$set(ff, "materialAmt", $$v)
        },
        expression: "ff.materialAmt"
      }
    })], 1), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 3
      }
    }, [_c('div', {
      staticClass: "grid-content"
    }, [_vm._v(_vm._s(ff.materialUnit))])]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 3
      }
    }, [_c('div', {
      staticClass: "grid-content"
    }, [_c('el-button', {
      attrs: {
        "type": "danger",
        "icon": "delete"
      },
      on: {
        "click": function($event) {
          _vm.removeFormula(index)
        }
      }
    })], 1)])], 1)
  }), _vm._v(" "), _c('el-row', [_c('el-col', {
    attrs: {
      "span": 24
    }
  }, [_c('el-button', {
    attrs: {
      "type": "success"
    },
    on: {
      "click": _vm.addFormulaItem
    }
  }, [_vm._v("增加原料")])], 1)], 1)], 2), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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
  }, [_vm._v("取消")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 771:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(717);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(263)("f68f545a", content, true);

/***/ })

});