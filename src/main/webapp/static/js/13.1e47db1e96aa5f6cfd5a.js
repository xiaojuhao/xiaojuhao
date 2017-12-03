webpackJsonp([13],{

/***/ 622:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(862)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(701),
  /* template */
  __webpack_require__(823),
  /* scopeId */
  "data-v-f1f382f0",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 667:
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

/***/ 674:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(667),
  /* template */
  __webpack_require__(675),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 675:
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

/***/ 701:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__ = __webpack_require__(674);
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
                inCabinCode: this.inCabinCode,
                outCabinCode: this.outCabinCode,
                dataJson: JSON.stringify(this.materialList)
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].commitDiaobo(param).then(val => {});
        },
        removeRows(index) {
            this.$data.materialList.splice(index, 1);
        },
        querySearchAsync(queryString, cb) {
            if (!this.outCabinCode) {
                this.$message.error("请选选择拨出单位");
                return;
            }
            console.log(this.cabinMaterialStock);
            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
                queryString = __WEBPACK_IMPORTED_MODULE_2_jquery___default.a.trim(queryString);
                let counter = 0;
                let result = this.cabinMaterialStock.map(item => {
                    let sk = item.materialName + "," + item.cabinName + "," + item.searchKey;
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "sk", sk);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'value', item.materialName + "-" + item.cabinName);
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
            if (this.materialList.length > 10) {
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
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "specUnit", mm.specUnit);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "specQty", mm.specQty);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, "stockUnit", mm.stockUnit);
                    }
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'outAmt', 0);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'outCabinCode', this.outCabinCode);
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
        },
        onSelectOutCabin(val) {
            this.outCabinCode = val;
            let param = {
                cabinCode: val,
                pageSize: 1000
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialsStockPage(param).then(page => {
                console.log(this.cabinMaterialStock);
                this.cabinMaterialStock = page.values;
                if (!this.cabinMaterialStock || this.cabinMaterialStock.length == 0) {
                    this.$message.error("仓库没有库存数据");
                }
            });
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

/***/ }),

/***/ 774:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".form-box[data-v-f1f382f0]{margin-top:20px;width:100%}.border-table[data-v-f1f382f0]{border-collapse:collapse;border:none}.border-table td[data-v-f1f382f0],.border-table th[data-v-f1f382f0]{border:1px solid #000}.el-row[data-v-f1f382f0]{margin-bottom:5px;&:last-child{margin-bottom:0}}.data-picker[data-v-f1f382f0]{width:120px}.bg-purple[data-v-f1f382f0]{background:#d3dce6}.grid-content[data-v-f1f382f0]{min-height:1px;text-align:center}.head-row[data-v-f1f382f0]{height:30px}", ""]);

// exports


/***/ }),

/***/ 823:
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
      "prop": "currStock",
      "label": "当前库存",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "拨出数量",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.outAmt),
            callback: function($$v) {
              _vm.$set(scope.row, "outAmt", $$v)
            },
            expression: "scope.row.outAmt"
          }
        })]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "stockUnit",
      "label": "单位",
      "width": "100"
    }
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

/***/ 862:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(774);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("8a0e5354", content, true);

/***/ })

});