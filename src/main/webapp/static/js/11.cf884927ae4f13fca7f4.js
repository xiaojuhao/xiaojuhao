webpackJsonp([11],{

/***/ 621:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(779)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(685),
  /* template */
  __webpack_require__(753),
  /* scopeId */
  "data-v-6eb70d84",
  /* cssModules */
  null
)

module.exports = Component.exports


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
            __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].queryMyWarehouse().then(list => {
                list.forEach(item => {
                    let ii = {};
                    ii.code = item.warehouseCode;
                    ii.name = item.warehouseName;
                    ii.type = "仓库";
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

/***/ 656:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(650),
  /* template */
  __webpack_require__(658),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 658:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "选择仓库或门店",
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

/***/ 685:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__ = __webpack_require__(656);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_vue__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_vue__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            cabinCode: '',
            allMaterialSupplier: []
        };
    },
    methods: {
        formatSpec(row) {
            if (row.specUnit == '无') return row.stockUnit;
            return row.specQty + row.stockUnit;
        },
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
            if (!this.cabinCode) {
                this.$message.error("请先选择采购仓库或门店");
                return;
            }
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
                    this.$message("提交采购单成功");
                }, 1000);
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消入库'
                });
            });
        },
        submitToServer() {
            let param = {
                cabinCode: this.cabinCode,
                dataJson: JSON.stringify(this.materialList)
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].commitPurchaseOrder(param).then(val => {});
        },
        removeRows(index) {
            this.$data.materialList.splice(index, 1);
        },
        querySearchAsync(queryString, cb) {
            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
                queryString = __WEBPACK_IMPORTED_MODULE_2_jquery___default.a.trim(queryString);
                let counter = 0;
                let result = this.allMaterialSupplier.map(item => {
                    let sk = item.materialName + "," + item.supplierName + "," + item.searchKey;
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "sk", sk);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'value', item.materialName + "-" + item.supplierName);
                    return item;
                }).filter(item => {
                    counter++;
                    return counter <= 20 && item.sk.indexOf(queryString) >= 0;
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
                            __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'storageLifeUnit', r[2]);
                            __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'storageLifeNum', r[1]);
                        }
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "specUnit", mm.specUnit);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "specQty", mm.specQty);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "stockUnit", mm.stockUnit);
                    }
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specAmt', 0);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'totalPrice', 0);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specPrice', 0);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'cabinCode', this.cabinCode);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'prodDate', today);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'materialCode', item.materialCode);
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
            if (row.specAmt && row.specPrice) {
                total = row.specAmt * row.specPrice;
                return total.toFixed(2);
            }
            return total;
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryAllMaterialSuppler().then(values => {
            this.allMaterialSupplier = values;
        });
        //添加回车监听
        __WEBPACK_IMPORTED_MODULE_2_jquery___default()("#handbox").keyup(event => {
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
        MyCabinSelect: __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect___default.a
    }
});

/***/ }),

/***/ 720:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".form-box[data-v-6eb70d84]{margin-top:20px;width:100%}.border-table[data-v-6eb70d84]{border-collapse:collapse;border:none}.border-table td[data-v-6eb70d84],.border-table th[data-v-6eb70d84]{border:1px solid #000}.el-row[data-v-6eb70d84]{margin-bottom:5px;&:last-child{margin-bottom:0}}.data-picker[data-v-6eb70d84]{width:120px}.bg-purple[data-v-6eb70d84]{background:#d3dce6}.grid-content[data-v-6eb70d84]{min-height:1px;text-align:center}.head-row[data-v-6eb70d84]{height:30px}", ""]);

// exports


/***/ }),

/***/ 753:
/***/ (function(module, exports) {

module.exports={render:function (){
var this$1 = this;
var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState),
      expression: "loadingState"
    }]
  }, [_c('div', {
    staticClass: "handbox"
  }, [_c('span', [_vm._v("\n        采购单位："), _c('MyCabinSelect', {
    on: {
      "input": function (val) {
        this$1.cabinCode = val;
      }
    }
  })], 1), _vm._v("\n            \n        采购原料：\n        "), _c('el-autocomplete', {
    attrs: {
      "id": "handbox",
      "fetch-suggestions": _vm.querySearchAsync,
      "placeholder": "搜索原料"
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
  }), _vm._v(" "), _c('span', [_vm._v("搜索内容后回车或选中添加记录")])], 1), _vm._v(" "), _c('div', {
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
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierName",
      "label": "供应商",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "数量",
      "width": "140"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.specAmt),
            callback: function($$v) {
              _vm.$set(scope.row, "specAmt", $$v)
            },
            expression: "scope.row.specAmt"
          }
        }, [_c('template', {
          attrs: {
            "slot": "append"
          },
          slot: "append"
        }, [_vm._v(_vm._s(scope.row.specUnit == '无' ? scope.row.stockUnit : scope.row.specUnit))])], 2)]
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
            value: (scope.row.specPrice),
            callback: function($$v) {
              _vm.$set(scope.row, "specPrice", $$v)
            },
            expression: "scope.row.specPrice"
          }
        }, [_c('template', {
          attrs: {
            "slot": "append"
          },
          slot: "append"
        }, [_vm._v("元")])], 2)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "规格",
      "formatter": _vm.formatSpec
    }
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
            value: (scope.row.prodDate),
            callback: function($$v) {
              _vm.$set(scope.row, "prodDate", $$v)
            },
            expression: "scope.row.prodDate"
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
      "prop": "totalPrice",
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
  }, [_vm._v("提交采购单")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onClear
    }
  }, [_vm._v("清空")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 779:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(720);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("7cee35fa", content, true);

/***/ })

});