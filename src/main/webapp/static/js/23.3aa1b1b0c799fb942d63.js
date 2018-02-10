webpackJsonp([23],{

/***/ 1018:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".form-box[data-v-56e49eb3]{margin-top:20px;width:100%}.border-table[data-v-56e49eb3]{border-collapse:collapse;border:none}.border-table td[data-v-56e49eb3],.border-table th[data-v-56e49eb3]{border:1px solid #000}.el-row[data-v-56e49eb3]{margin-bottom:5px;&:last-child{margin-bottom:0}}.data-picker[data-v-56e49eb3]{width:120px}.bg-purple[data-v-56e49eb3]{background:#d3dce6}.grid-content[data-v-56e49eb3]{min-height:1px;text-align:center}.head-row[data-v-56e49eb3]{height:30px}", ""]);

// exports


/***/ }),

/***/ 1086:
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
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("采购单录入")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v(_vm._s(_vm.cabinName))])], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "handbox"
  }, [_c('span', [_vm._v("\n        采购单位：" + _vm._s(_vm.cabinName) + "\n        ")]), _vm._v("      采购原料：\n        "), _c('el-autocomplete', {
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
      "width": "180"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierName",
      "label": "供应商",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购规格",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-select', {
          staticStyle: {
            "width": "140px"
          },
          attrs: {
            "slot": "append"
          },
          on: {
            "change": function($event) {
              _vm.onSelectSpec(scope.row, scope.row.specCode)
            }
          },
          slot: "append",
          model: {
            value: (scope.row.specCode),
            callback: function($$v) {
              _vm.$set(scope.row, "specCode", $$v)
            },
            expression: "scope.row.specCode"
          }
        }, _vm._l((scope.row.specCodeSel), function(item) {
          return _c('el-option', {
            key: item.specCode,
            attrs: {
              "label": item.specName,
              "value": item.specCode
            }
          })
        }))]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购数量",
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
      "label": "采购单价",
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
      "label": "基价",
      "formatter": _vm.formatBasePrice,
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "totalPrice",
      "label": "总价",
      "width": "120",
      "formatter": _vm.calcTotalPrice
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "规格",
      "formatter": _vm.formatSpec,
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购入库",
      "formatter": _vm.formatStockAmt,
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "食材入库",
      "formatter": _vm.formatInStockAmt,
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "brandName",
      "label": "品牌",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "生产日期",
      "width": "150"
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

/***/ 1150:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1018);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("c4f48eb8", content, true);

/***/ }),

/***/ 649:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1150)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(956),
  /* template */
  __webpack_require__(1086),
  /* scopeId */
  "data-v-56e49eb3",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 684:
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

/***/ 685:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(684),
  /* template */
  __webpack_require__(686),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 686:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "placeholder": "仓库或门店",
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

/***/ 956:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__ = __webpack_require__(685);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_vue__ = __webpack_require__(16);
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
//
//
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
            cabinCode: this.$route.query.CODE,
            cabinName: '',
            allMaterialSupplier: []
        };
    },
    methods: {
        formatBasePrice(row) {
            return row.selectedSpec && row.selectedSpec.basePrice + "元";
        },
        formatSpec(row) {
            if (row.transRate && row.stockUnit && row.specUnit) {
                if (row.stockUnit != row.specUnit) return row.transRate + row.stockUnit + "/" + row.specUnit;else return row.stockUnit;
            }
        },
        formatStockAmt(row) {
            let amt = eval(row.specAmt + "*" + row.transRate).toFixed(2);
            let intamt = parseInt(amt);
            if (amt == intamt) {
                return intamt + row.stockUnit;
            }
            return amt + row.stockUnit;
        },
        formatInStockAmt(row) {
            let amt = (eval(row.specAmt + "*" + row.transRate) * row.utilizationRatio / 100).toFixed(2);
            let intamt = parseInt(amt);
            if (amt == intamt) {
                return intamt + row.stockUnit;
            }
            return amt + row.stockUnit;
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
            this.$confirm('是否提交采购单?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.loadingState = true;
                let param = {
                    cabinCode: this.cabinCode,
                    dataJson: JSON.stringify(this.materialList)
                };
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].commitPurchaseOrder(param).then(val => {
                    self.materialList = [];
                    this.$message("提交采购单成功");
                }).fail(resp => {
                    this.$message.error(resp.message);
                }).always(() => {
                    this.loadingState = false;
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消入库'
                });
            });
        },
        removeRows(index) {
            this.$data.materialList.splice(index, 1);
        },
        querySearchAsync(queryString, cb) {
            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
                queryString = __WEBPACK_IMPORTED_MODULE_2_jquery___default.a.trim(queryString);
                let counter = 1;
                let result = this.allMaterialSupplier.map(item => {
                    let sk = item.materialName + "," + item.supplierName + "," + item.searchKey;
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "sk", sk);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'value', item.supplierName + "-" + item.materialName);
                    return item;
                }).filter(item => {
                    if (counter <= 20 && item.sk.indexOf(queryString) >= 0) {
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
            if (this.materialList.length > 20) {
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
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "specUnit", '');
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "transRate", 0);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "stockUnit", mm.stockUnit);
                    }
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specAmt', 0);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'totalPrice', 0);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specPrice', 0);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'cabinCode', this.cabinCode);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'prodDate', today);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'materialCode', item.materialCode);
                    this.setSpecCode(item);
                    self.materialList.push(item);
                    this.initSpecPrice(item);
                }
            });
        },
        setSpecCode(item) {
            setTimeout(() => {
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].querySpecDetailByMaterialCode(item.materialCode).then(list => {
                    if (list && list.length > 0) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specCode', list[0].specCode);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'psecName', list[0].specName);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specUnit', list[0].specUnit);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'transRate', list[0].transRate);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'brandName', list[0].brandName);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'homeplace', list[0].homeplace);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'utilizationRatio', list[0].utilizationRatio);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'selectedSpec', list[0]);
                        this.initSpecPrice(item);
                    }
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specCodeSel', list);
                });
            }, 0);
        },
        onSelectSpec(item, selectedCode) {
            item.specCodeSel.forEach(it => {
                if (it.specCode == selectedCode) {
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specCode', it.specCode);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specName', it.specName);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specUnit', it.specUnit);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'transRate', it.transRate);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'brandName', it.brandName);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'homeplace', it.homeplace);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'utilizationRatio', it.utilizationRatio);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'selectedSpec', it);
                    this.initSpecPrice(item);
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
                return total.toFixed(2) + "元";
            }
            return total + "元";
        },
        initSpecPrice(item) {
            __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specPrice', 0);
            let cabinCode = item.cabinCode;
            if (item.selectedSpec && item.selectedSpec.priceInfo) {
                try {
                    let pi = JSON.parse(item.selectedSpec.priceInfo);
                    if (pi && pi[cabinCode]) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specPrice', pi[cabinCode]);
                    }
                } catch (e) {
                    console.log(e);
                }
            }
        }
    },
    mounted() {
        this.$store.dispatch('loadAllData');
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryAllMaterialSuppler().then(values => {
            this.allMaterialSupplier = values;
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getCabinByCode(this.cabinCode).then(vo => {
            this.cabinName = vo.name;
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

/***/ })

});