webpackJsonp([25],{

/***/ 1034:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".form-box[data-v-f1f382f0]{margin-top:20px;width:100%}.border-table[data-v-f1f382f0]{border-collapse:collapse;border:none}.border-table td[data-v-f1f382f0],.border-table th[data-v-f1f382f0]{border:1px solid #000}.el-row[data-v-f1f382f0]{margin-bottom:5px;&:last-child{margin-bottom:0}}.data-picker[data-v-f1f382f0]{width:120px}.bg-purple[data-v-f1f382f0]{background:#d3dce6}.grid-content[data-v-f1f382f0]{min-height:1px;text-align:center}.head-row[data-v-f1f382f0]{height:30px}", ""]);

// exports


/***/ }),

/***/ 1105:
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
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("调拨单录入")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v(_vm._s(_vm.outCabinName))])], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "handbox"
  }, [_c('el-row', [_c('el-col', {
    attrs: {
      "span": 7
    }
  }, [_vm._v("\n                拨入单位：\n                "), _c('MyCabinSelect', {
    on: {
      "input": function (val) {
        this$1.inCabinCode = val;
      }
    }
  })], 1), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_vm._v("\n                添加原料：\n                "), _c('el-autocomplete', {
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
  }), _vm._v(" "), _c('span', [_vm._v("搜索内容后回车或选中添加记录")])], 1)], 1)], 1), _vm._v(" "), _c('div', {
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
      "prop": "cabinName",
      "label": "拨出仓库",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "当前库存",
      "width": "120"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.currStock) + " " + _vm._s(scope.row.stockUnit) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "调拨规格",
      "width": "120"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-select', {
          staticStyle: {
            "width": "100px"
          },
          attrs: {
            "size": "small"
          },
          on: {
            "change": function($event) {
              _vm.onSelectSpec(scope.row, scope.row.specCode)
            }
          },
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
      "label": "拨出数量",
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
        }, [_vm._v(_vm._s(scope.row.specUnit))])], 2)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "单价",
      "width": "160"
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
        }, [_vm._v("元/" + _vm._s(scope.row.specUnit))])], 2)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "总价",
      "width": "160",
      "formatter": _vm.calcTotalPrice
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购库存",
      "width": "160",
      "formatter": _vm.calcStockAmt
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "利用率",
      "width": "100",
      "prop": "utilizationRatio"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.utilizationRatio) + "%\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "食材库存",
      "width": "160",
      "formatter": _vm.calcInStockAmt
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "备注",
      "width": "250"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.remark),
            callback: function($$v) {
              _vm.$set(scope.row, "remark", $$v)
            },
            expression: "scope.row.remark"
          }
        })]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": ""
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
  }, [_vm._v("提交调拨")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onClear
    }
  }, [_vm._v("清空")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1160:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1034);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("8a0e5354", content, true);

/***/ }),

/***/ 622:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1160)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(928),
  /* template */
  __webpack_require__(1105),
  /* scopeId */
  "data-v-f1f382f0",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 686:
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

/***/ 688:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(686),
  /* template */
  __webpack_require__(689),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 689:
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

/***/ 928:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__ = __webpack_require__(688);
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
            outCabinCode: this.$route.query.CODE,
            outCabinName: '',
            inCabinCode: '',
            cabinMaterialStock: []
        };
    },
    methods: {
        formatSpec(row) {
            if (row.specUnit == '无') return row.stockUnit;
            return row.transRate + row.stockUnit;
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
            if (!this.inCabinCode) {
                this.$message.error("请拨入单位");
                return;
            }
            let check = true;
            this.materialList.forEach(item => {
                if (item.cabinCode != this.outCabinCode) {
                    check = false;
                }
            });
            if (!check) {
                this.$message.error("拨出仓库和数据不一致");
                return;
            }
            let self = this;
            this.$confirm('是否提交入库?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.submitToServer();
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消入库'
                });
            });
        },
        submitToServer() {
            this.loadingState = true;
            let param = {
                inCabinCode: this.inCabinCode,
                outCabinCode: this.outCabinCode,
                dataJson: JSON.stringify(this.materialList)
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].commitDiaobo(param).then(val => {
                this.$message("提交成功");
                this.materialList = [];
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.loadingState = false;
            });
        },
        onSelectSpec(item) {
            item.specCodeSel && item.specCodeSel.forEach(it => {
                if (it.specCode == item.specCode) {
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specName', it.specName);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specUnit', it.specUnit);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'stockUnit', it.stockUnit);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'transRate', it.transRate);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'brandName', it.brandName);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'homeplace', it.homeplace);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'utilizationRatio', it.utilizationRatio);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'selectedSpec', it);
                }
            });
            //this.calcSpecAmt(item);
            this.initSpecPrice(item);
        },
        removeRows(index) {
            this.$data.materialList.splice(index, 1);
        },
        querySearchAsync(queryString, cb) {
            if (!this.outCabinCode) {
                this.$message.error("请选选择拨出单位");
                return;
            }
            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
                queryString = __WEBPACK_IMPORTED_MODULE_2_jquery___default.a.trim(queryString);
                let counter = 1;
                let result = this.cabinMaterialStock.map(item => {
                    let sk = item.materialName + "," + item.cabinName + "," + item.searchKey;
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "sk", sk);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'value', item.materialName);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'totalPrice', 0);
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
            if (this.materialList.length > 20) {
                this.$message.error("添加记录太多,请先提交");
                return;
            }
            let self = this;
            this.currSelectAlts.forEach(item => {
                if (!this.materialListMap[item.materialCode]) {
                    let mm = this.$store.getters.allMaterialsMap.get(item.materialCode);
                    if (mm) {
                        let re = /(\d+)(\w)/ig;
                        let r = re.exec(mm.storageLife);
                        if (r) {
                            __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'storageLifeUnit', r[2]);
                            __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'storageLifeNum', r[1]);
                        }
                    }
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'outAmt', 0);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'outCabinCode', this.outCabinCode);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'materialCode', item.materialCode);
                    self.materialList.push(item);
                }
            });
            this.initTableDate(this.materialList);
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
        calcStockAmt(row) {
            return (row.transRate * row.specAmt).toFixed(2) + row.stockUnit;
        },
        calcInStockAmt(row) {
            return (row.transRate * row.specAmt * row.utilizationRatio / 100).toFixed(2) + row.stockUnit;
        },
        onSelectOutCabin(val) {
            this.outCabinCode = val;
            let param = {
                cabinCode: val,
                pageSize: 1000
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialsStockPage(param).then(page => {
                this.cabinMaterialStock = page.values;
                if (!this.cabinMaterialStock || this.cabinMaterialStock.length == 0) {
                    this.$message.error("仓库没有库存数据");
                }
            });
        },
        initTableDate(tableData) {
            let codes = [];
            tableData.forEach(it => {
                codes.push(it.materialCode);
            });
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].querySpecsByMaterialCodes({
                materialCodes: codes.join(',')
            }).then(list => {
                //将list按mateiralCode分组
                let map = new Map();
                list.forEach(it => {
                    let mc = it.materialCode;
                    if (!map.get(mc)) {
                        map.set(mc, []);
                    }
                    map.get(mc).push(it);
                });
                for (let entry of map) {
                    let entryVals = entry[1];
                    let oneVal = entryVals[0];
                    let s = {};
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(s, 'specCode', 'MS000000');
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(s, 'specName', oneVal.stockUnit);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(s, 'specUnit', oneVal.stockUnit);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(s, 'stockUnit', oneVal.stockUnit);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(s, 'transRate', 1);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(s, 'utilizationRatio', 100);
                    entryVals.push(s);
                }
                //遍历每条记录，设置规则
                tableData.forEach(it => {
                    if (map.get(it.materialCode)) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'specCodeSel', map.get(it.materialCode));
                    }
                    //如果记录本身没有规格信息，就将第一个设置为默认规格
                    if (!it.specCode && it.specCodeSel.length > 0) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'specCode', it.specCodeSel[0].specCode);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'specUnit', it.specCodeSel[0].specUnit);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'stockUnit', it.specCodeSel[0].stockUnit);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'transRate', it.specCodeSel[0].transRate);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'brandName', it.specCodeSel[0].brandName);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'homeplace', it.specCodeSel[0].homeplace);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'utilizationRatio', it.specCodeSel[0].utilizationRatio);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'selectedSpec', it.specCodeSel[0]);
                        this.initSpecPrice(it);
                    }
                    if (!it.transRate && it.specUnit == it.stockUnit) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'transRate', 1);
                    }
                });
            });
        },
        initSpecInfo(item) {},
        initSpecPrice(item) {
            __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specPrice', 0);
            let cabinCode = item.cabinCode;
            if (item.selectedSpec && item.selectedSpec.priceInfo) {
                let pi = JSON.parse(item.selectedSpec.priceInfo);
                if (pi && pi[cabinCode]) {
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specPrice', pi[cabinCode]);
                }
            }
        }
    },
    mounted() {
        this.onSelectOutCabin(this.outCabinCode);
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getCabinByCode(this.outCabinCode).then(vo => {
            this.outCabinName = vo.name;
        });
        //添加回车监听
        __WEBPACK_IMPORTED_MODULE_2_jquery___default()("#handbox").keyup(event => {
            if (event.keyCode == 13) {
                this.onEnterKeyPressed();
            }
        });
    },
    computed: {
        materialListMap() {
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