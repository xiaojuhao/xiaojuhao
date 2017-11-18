webpackJsonp([10],{

/***/ 632:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(761)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(691),
  /* template */
  __webpack_require__(736),
  /* scopeId */
  "data-v-510faf67",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 644:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(646),
  /* template */
  __webpack_require__(652),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 646:
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

/***/ 652:
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

/***/ 654:
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

/***/ 655:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(654),
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
      key: item.recipesCode,
      attrs: {
        "label": item.recipesName,
        "value": item.recipesCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 691:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection__ = __webpack_require__(644);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_StoreSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__ = __webpack_require__(655);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_vue__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_vue__);
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
    data() {
        return {
            timeout: null,
            storeCode: '',
            recipesList: [],
            currSelectAlts: [],
            loadingState: false,
            currDate: '20171113',
            storage: {
                code: '',
                type: '', //1:仓库 2:门店
                name: ''
            },
            allMaterialSupplier: []
        };
    },
    methods: {
        onClear() {
            let self = this;
            this.$confirm('是否清空当前页内容?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                self.recipesList = [];
            });
        },
        onSubmit() {
            let self = this;
            this.$confirm('是否提交入库?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.loadingState = true;
                this.submitToServer();
                self.recipesList = [];
                setTimeout(() => {
                    this.loadingState = false;
                }, 2000);
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消入库'
                });
            });
        },
        submitToServer() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* http */].post("/busi/batchInstock", {
                dataJson: JSON.stringify(this.recipesList)
            }).then(resp => {
                console.log(resp);
            });
        },
        removeRows(index) {
            this.$data.recipesList.splice(index, 1);
        },
        querySearchAsync(queryString, cb) {
            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
                queryString = __WEBPACK_IMPORTED_MODULE_3_jquery___default.a.trim(queryString);
                let counter = 0;
                let result = this.allMaterialSupplier.map(item => {
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'value', item.materialName + "-" + item.supplierName);
                    return item;
                }).filter(item => {
                    counter++;
                    return counter <= 20 && item.value.indexOf(queryString) >= 0;
                });

                this.$data.currSelectAlts = result;
                cb(result);
            });
        },
        addAltsToList() {
            let date = new Date();
            let today = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
            if (this.recipesList.length > 10) {
                this.$message.error("添加记录太多,请先入库");
                return;
            }
            let self = this;
            this.currSelectAlts.forEach(item => {
                if (!this.recipesListMap[item.id]) {
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'amt', 0);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'price', 0);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'cabinCode', this.storage.code);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'cabinName', this.storage.name);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'cabinType', this.storage.type);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'productDate', today);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'keepDays', '1天');
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'materialCode', item.materialCode);
                    self.recipesList.push(item);
                }
            });
        },
        handleSelect(item) {
            this.currSelectAlts = [item];
            this.storeCode = '';
            this.addAltsToList();
        },
        onEnterKeyPressed() {
            if (this.loadingState) {
                return;
            }
            this.loadingState = true;
            setTimeout(() => {
                this.addAltsToList();
                this.loadingState = false;
            }, 0);
        },
        calcTotalPrice(amt, price) {
            var total = 0.00;
            if (amt && price) {
                total = amt * price;
                return total.toFixed(2);
            }
            return total;
        }
    },
    mounted() {
        if (new RegExp("^WH").test(this.$route.query.CODE)) {
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getWarehouseByCode(this.$route.query.CODE).then(val => {
                this.storage.code = val.warehouseCode;
                this.storage.type = "1";
                this.storage.name = val.warehouseName;
            });
        } else if (new RegExp("^MD").test(this.$route.query.CODE)) {
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryStoreByCode(this.$route.query.CODE).then(val => {
                this.storage.code = val.storeCode;
                this.storage.type = "2";
                this.storage.name = val.storeName;
            });
        }

        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryAllMaterialSuppler().then(values => {
            this.allMaterialSupplier = values;
        });
        //添加回车监听
        __WEBPACK_IMPORTED_MODULE_3_jquery___default()("#handbox").keyup(event => {
            if (event.keyCode == 13) {
                this.onEnterKeyPressed();
            }
        });
    },
    computed: {
        recipesListMap() {
            let map = {};
            this.recipesList.forEach(item => map[item.id] = item);
            return map;
        }
    },
    components: {
        StoreSelection: __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection___default.a,
        RecipesSelection: __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default.a
    }
});

/***/ }),

/***/ 707:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".form-box[data-v-510faf67]{margin-top:20px;width:100%}.border-table[data-v-510faf67]{border-collapse:collapse;border:none}.border-table td[data-v-510faf67],.border-table th[data-v-510faf67]{border:1px solid #000}.el-input[data-v-510faf67]{width:150px}.el-row[data-v-510faf67]{margin-bottom:5px;&:last-child{margin-bottom:0}}.bg-purple[data-v-510faf67]{background:#d3dce6}.grid-content[data-v-510faf67]{min-height:1px;text-align:center}.head-row[data-v-510faf67]{height:30px}", ""]);

// exports


/***/ }),

/***/ 736:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState),
      expression: "loadingState"
    }]
  }, [_c('div', {
    staticClass: "handbox"
  }, [_c('el-autocomplete', {
    attrs: {
      "id": "handbox",
      "fetch-suggestions": _vm.querySearchAsync,
      "placeholder": "请输入内容"
    },
    on: {
      "select": _vm.handleSelect
    },
    model: {
      value: (_vm.storeCode),
      callback: function($$v) {
        _vm.storeCode = $$v
      },
      expression: "storeCode"
    }
  }), _vm._v(" "), _c('span', [_vm._v("搜索内容后回车或选中添加记录")]), _vm._v(" "), _c('span', {
    staticStyle: {
      "float": "right",
      "position": "relative",
      "margin-right": "20%",
      "margin-top": "10px"
    }
  }, [_vm._v("\n                " + _vm._s(_vm.storage.code) + " " + _vm._s(_vm.storage.name) + "\n            ")])], 1), _vm._v(" "), _c('div', {
    staticClass: "form-box"
  }, [_c('el-row', [_c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple head-row"
  }, [_vm._v("品名")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple head-row"
  }, [_vm._v("供应商")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 2
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple head-row"
  }, [_vm._v("数量")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 2
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple head-row"
  }, [_vm._v("单价")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 2
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple head-row"
  }, [_vm._v("单位")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 3
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple head-row"
  }, [_vm._v("生产日期")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 2
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple head-row"
  }, [_vm._v("保质期")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 2
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple head-row"
  }, [_vm._v("金额")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 3
    }
  }, [_c('div', {
    staticClass: "grid-content bg-purple head-row"
  }, [_vm._v("仓库/门店")])])], 1), _vm._v(" "), _vm._l((_vm.recipesList), function(item, index) {
    return _c('el-row', [_c('el-col', {
      staticClass: "grid-content",
      attrs: {
        "span": 4
      }
    }, [_vm._v(_vm._s(item.materialName))]), _vm._v(" "), _c('el-col', {
      staticClass: "grid-content",
      attrs: {
        "span": 4
      }
    }, [_vm._v(_vm._s(item.supplierName))]), _vm._v(" "), _c('el-col', {
      staticClass: "grid-content",
      attrs: {
        "span": 2
      }
    }, [_c('el-input', {
      staticStyle: {
        "width": "100%"
      },
      attrs: {
        "size": "mini"
      },
      model: {
        value: (item.amt),
        callback: function($$v) {
          _vm.$set(item, "amt", $$v)
        },
        expression: "item.amt"
      }
    })], 1), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 2
      }
    }, [_c('el-input', {
      staticStyle: {
        "width": "100%"
      },
      attrs: {
        "size": "mini"
      },
      model: {
        value: (item.unitPrice),
        callback: function($$v) {
          _vm.$set(item, "unitPrice", $$v)
        },
        expression: "item.unitPrice"
      }
    })], 1), _vm._v(" "), _c('el-col', {
      staticClass: "grid-content",
      attrs: {
        "span": 2
      }
    }, [_vm._v(_vm._s(item.stockUnit))]), _vm._v(" "), _c('el-col', {
      staticClass: "grid-content",
      attrs: {
        "span": 3
      }
    }, [_vm._v(_vm._s(item.productDate))]), _vm._v(" "), _c('el-col', {
      staticClass: "grid-content",
      attrs: {
        "span": 2
      }
    }, [_vm._v(_vm._s(item.keepDays))]), _vm._v(" "), _c('el-col', {
      staticClass: "grid-content",
      attrs: {
        "span": 2
      }
    }, [_c('el-input', {
      staticStyle: {
        "width": "100%"
      },
      attrs: {
        "readonly": "",
        "value": _vm.calcTotalPrice(item.amt, item.unitPrice),
        "size": "mini"
      }
    })], 1), _vm._v(" "), _c('el-col', {
      staticClass: "grid-content",
      attrs: {
        "span": 2
      }
    }, [_vm._v(_vm._s(item.cabinName))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 1
      }
    }, [_c('el-button', {
      attrs: {
        "icon": "delete",
        "size": "mini"
      },
      on: {
        "click": function($event) {
          _vm.removeRows(index)
        }
      }
    })], 1)], 1)
  }), _vm._v(" "), _c('el-row', {
    staticStyle: {
      "margin-top": "20px"
    }
  }, [_c('el-col', {
    attrs: {
      "offset": 6
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary",
      "disabled": _vm.recipesList.length == 0
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("提交入库")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onClear
    }
  }, [_vm._v("清空")])], 1)], 1)], 2)])
},staticRenderFns: []}

/***/ }),

/***/ 761:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(707);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("c7bd170a", content, true);

/***/ })

});