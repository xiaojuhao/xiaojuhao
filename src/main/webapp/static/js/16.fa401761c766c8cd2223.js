webpackJsonp([16],{

/***/ 1020:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-662a6dd7]{margin-bottom:20px}.el-select[data-v-662a6dd7]{width:120px}.el-input[data-v-662a6dd7]{width:180px;display:inline-block}.el-row[data-v-662a6dd7]{margin-bottom:4px;&:last-child{margin-bottom:0}}.container[data-v-662a6dd7]{position:relative;width:100%}", ""]);

// exports


/***/ }),

/***/ 1087:
/***/ (function(module, exports) {

module.exports={render:function (){
var this$1 = this;
var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "container"
  }, [_c('div', [_c('div', {
    staticClass: "handle-box"
  }, [_c('el-row', {
    attrs: {
      "gutter": 10
    }
  }, [_c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_c('MyStoreSelect', {
    on: {
      "input": function (v) {
        this$1.queryCond.storeCode = v;
      }
    }
  }), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "140px"
    },
    attrs: {
      "placeholder": "菜名"
    },
    model: {
      value: (_vm.queryCond.recipesName),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "recipesName", $$v)
      },
      expression: "queryCond.recipesName"
    }
  }), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "140px"
    },
    attrs: {
      "placeholder": "菜名"
    },
    model: {
      value: (_vm.queryCond.recipesType),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "recipesType", $$v)
      },
      expression: "queryCond.recipesType"
    }
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.testRecipes
    }
  }, [_vm._v("测试")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.syncMenu
    }
  }, [_vm._v("同步")])], 1)], 1)], 1), _vm._v(" "), _c('el-table', {
    staticStyle: {
      "width": "100%"
    },
    attrs: {
      "data": _vm.tableData,
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "dishes_id",
      "label": "外部编码"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "dishes_name",
      "label": "菜品名称"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "dishes_type_name",
      "label": "菜品类型"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "price",
      "label": "价格"
    }
  })], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1150:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1020);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("077a0f03", content, true);

/***/ }),

/***/ 653:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1150)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(959),
  /* template */
  __webpack_require__(1087),
  /* scopeId */
  "data-v-662a6dd7",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 787:
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
            __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].queryMyStores().then(list => {
                list.forEach(item => {
                    let ii = {};
                    ii.code = item.storeCode;
                    ii.name = item.storeName;
                    ii.type = "门店";
                    this.allValues.push(ii);
                });
            });
        },
        filterMethod(input) {
            let $data = this.$data;
            setTimeout(() => {
                $data.valuesShow = $data.allValues.filter(item => {
                    var key = [item.code, item.name, item.searchKey].join(',');
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
                    if (this.excludesMap[item.code]) {
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

/***/ 879:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(787),
  /* template */
  __webpack_require__(882),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 882:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "选择门店",
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
      key: item.code,
      attrs: {
        "label": item.name,
        "value": item.code
      }
    }, [_c('span', {
      staticStyle: {
        "float": "left"
      }
    }, [_vm._v(_vm._s(item.name))]), _vm._v(" "), _c('span', {
      staticStyle: {
        "float": "right",
        "color": "#8492a6",
        "font-size": "13px"
      }
    }, [_vm._v(_vm._s(item.type))])])
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 959:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus_js__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyStoreSelect__ = __webpack_require__(879);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyStoreSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MyStoreSelect__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_vue__);
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
    components: {
        MyStoreSelect: __WEBPACK_IMPORTED_MODULE_1__common_MyStoreSelect___default.a
    },
    data() {
        return {
            queryCond: {
                storeCode: '',
                recipesName: '',
                recipesType: ''
            },
            queryList: []
        };
    },
    computed: {
        tableData() {
            return this.queryList.filter(t => {
                return t.dishes_name.indexOf(this.queryCond.recipesName) >= 0 && t.dishes_type_name.indexOf(this.queryCond.recipesType) >= 0;
            });
        }
    },
    methods: {
        testRecipes() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus_js__["a" /* api */].getRecipesFromRemote(this.queryCond).then(value => {
                console.log(value);
                this.queryList = value.allDishes;
                this.queryList.sort((a, b) => a.dishes_id - b.dishes_id);
            }).fail(resp => {
                this.$message.error(resp.message);
            });
        },
        syncMenu() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus_js__["a" /* api */].syncRecipes(this.queryCond).then(() => {
                this.$message("同步菜单成功");
            });
        }
    }
});

/***/ })

});