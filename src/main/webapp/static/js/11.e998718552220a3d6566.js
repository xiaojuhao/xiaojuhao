webpackJsonp([11],{

/***/ 1012:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".form-box[data-v-510faf67]{margin-top:20px;width:100%}.border-table[data-v-510faf67]{border-collapse:collapse;border:none}.border-table td[data-v-510faf67],.border-table th[data-v-510faf67]{border:1px solid #000}.el-row[data-v-510faf67]{margin-bottom:5px;&:last-child{margin-bottom:0}}.data-picker[data-v-510faf67]{width:120px}.bg-purple[data-v-510faf67]{background:#d3dce6}.grid-content[data-v-510faf67]{min-height:1px;text-align:center}.head-row[data-v-510faf67]{height:30px}", ""]);

// exports


/***/ }),

/***/ 1080:
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
  }, [_vm._v("\n            " + _vm._s(_vm.storage.code) + " " + _vm._s(_vm.storage.name) + "\n        ")])], 1), _vm._v(" "), _c('div', {
    staticClass: "form-box"
  }, [_c('el-table', {
    staticStyle: {
      "width": "120%"
    },
    attrs: {
      "data": _vm.materialList,
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierName",
      "label": "供应商",
      "width": "130"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "数量",
      "width": "120"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.amt),
            callback: function($$v) {
              _vm.$set(scope.row, "amt", $$v)
            },
            expression: "scope.row.amt"
          }
        })]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "单价",
      "width": "130"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.unitPrice),
            callback: function($$v) {
              _vm.$set(scope.row, "unitPrice", $$v)
            },
            expression: "scope.row.unitPrice"
          }
        })]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "生产日期",
      "width": "130"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-date-picker', {
          staticClass: "data-picker",
          attrs: {
            "size": "small",
            "type": "date",
            "placeholder": "选择日期"
          },
          model: {
            value: (scope.row.productDate),
            callback: function($$v) {
              _vm.$set(scope.row, "productDate", $$v)
            },
            expression: "scope.row.productDate"
          }
        })]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "保质期",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          staticStyle: {
            "width": "120px"
          },
          model: {
            value: (scope.row.storageLifeNum),
            callback: function($$v) {
              _vm.$set(scope.row, "storageLifeNum", $$v)
            },
            expression: "scope.row.storageLifeNum"
          }
        }, [_c('el-select', {
          staticStyle: {
            "width": "60px"
          },
          attrs: {
            "slot": "append"
          },
          slot: "append",
          model: {
            value: (scope.row.storageLifeUnit),
            callback: function($$v) {
              _vm.$set(scope.row, "storageLifeUnit", $$v)
            },
            expression: "scope.row.storageLifeUnit"
          }
        }, [_c('el-option', {
          attrs: {
            "label": "天",
            "value": "D"
          }
        }), _vm._v(" "), _c('el-option', {
          attrs: {
            "label": "月",
            "value": "M"
          }
        })], 1)], 1)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "price",
      "label": "总金额",
      "width": "120",
      "formatter": _vm.calcTotalPrice
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.removeRows(scope.$index)
            }
          }
        }, [_vm._v("删除")])]
      }
    }])
  })], 1), _vm._v(" "), _c('el-row', {
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
      "disabled": _vm.materialList.length == 0
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
  }, [_vm._v("清空")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1144:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1012);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("c7bd170a", content, true);

/***/ }),

/***/ 671:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1144)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(975),
  /* template */
  __webpack_require__(1080),
  /* scopeId */
  "data-v-510faf67",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 707:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(709),
  /* template */
  __webpack_require__(713),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 708:
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

/***/ 709:
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
      this.$emit("input", this.selectedCode, this.$props.context);
    },
    initData() {
      let $data = this.$data;
      __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].getAllStoreList().then(page => {
        $data.allValues = page.values;
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

/***/ 711:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(708),
  /* template */
  __webpack_require__(712),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 712:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "选择菜品",
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

/***/ 713:
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

/***/ 975:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection__ = __webpack_require__(707);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_StoreSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__ = __webpack_require__(711);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_vue__ = __webpack_require__(16);
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
//
//
//






/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            timeout: null,
            storeCode: '',
            materialList: [],
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
                self.materialList = [];
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
                self.materialList = [];
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
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["d" /* http */].post("/busi/batchInstock", {
                dataJson: JSON.stringify(this.materialList)
            }).then(resp => {});
        },
        removeRows(index) {
            this.$data.materialList.splice(index, 1);
        },
        querySearchAsync(queryString, cb) {
            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
                queryString = __WEBPACK_IMPORTED_MODULE_3_jquery___default.a.trim(queryString);
                let counter = 1;
                let result = this.allMaterialSupplier.map(item => {
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'value', item.materialName + "-" + item.supplierName);
                    return item;
                }).filter(item => {
                    if (counter <= 20 && item.value.indexOf(queryString) >= 0) {
                        counter++;
                        return true;
                    } else {
                        return false;
                    }
                });

                this.$data.currSelectAlts = result;
                cb(result);
            });
        },
        addAltsToList() {
            let date = new Date();
            let today = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
            if (this.materialList.length > 10) {
                this.$message.error("添加记录太多,请先入库");
                return;
            }
            let self = this;
            this.currSelectAlts.forEach(item => {
                if (!this.recipesListMap[item.id]) {
                    let mm = this.$store.getters.allMaterialsMap.get(item.materialCode);
                    if (mm) {
                        let re = /(\d+)(\w)/ig;
                        let r = re.exec(mm.storageLife);
                        if (r) {
                            __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'storageLifeUnit', r[2]);
                            __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'storageLifeNum', r[1]);
                        }
                    }
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'amt', 0);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'price', 0);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'unitPrice', 0);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'cabinCode', this.storage.code);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'cabinName', this.storage.name);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'cabinType', this.storage.type);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'productDate', today);
                    __WEBPACK_IMPORTED_MODULE_4_vue___default.a.set(item, 'materialCode', item.materialCode);
                    self.materialList.push(item);
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
        calcTotalPrice(row) {
            var total = 0.00;
            if (row.amt && row.unitPrice) {
                total = row.amt * row.unitPrice;
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
            this.materialList.forEach(item => map[item.id] = item);
            return map;
        }
    },
    components: {
        StoreSelection: __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection___default.a,
        RecipesSelection: __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default.a
    }
});

/***/ })

});