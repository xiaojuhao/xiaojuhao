webpackJsonp([21],{

/***/ 1011:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-48067fff]{margin-bottom:20px}.handle-select[data-v-48067fff]{width:120px}.handle-input[data-v-48067fff]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 1077:
/***/ (function(module, exports) {

module.exports={render:function (){
var this$1 = this;
var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('el-row', {
    attrs: {
      "gutter": 10
    }
  }, [_c('el-col', [_c('MyCabinSelect', {
    on: {
      "input": function (v) {
        this$1.queryCond.cabinCode = v;
      }
    }
  }), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "placeholder": "原料名称"
    },
    model: {
      value: (_vm.queryCond.searchKey),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "searchKey", $$v)
      },
      expression: "queryCond.searchKey"
    }
  }), _vm._v(" "), _c('el-date-picker', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "type": "date",
      "placeholder": "起始日期"
    },
    model: {
      value: (_vm.queryCond.startDate),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "startDate", $$v)
      },
      expression: "queryCond.startDate"
    }
  }), _vm._v("\n                -\n                "), _c('el-date-picker', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "type": "date",
      "placeholder": "结束日期"
    },
    model: {
      value: (_vm.queryCond.endDate),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "endDate", $$v)
      },
      expression: "queryCond.endDate"
    }
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": function($event) {
        _vm.search()
      }
    }
  }, [_vm._v("搜索")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": function($event) {
        _vm.downloadExcel()
      }
    }
  }, [_vm._v("下载EXCEL")]), _vm._v(" "), _c('br'), _vm._v(" "), _c('el-select', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "clearable": "",
      "placeholder": "类型"
    },
    model: {
      value: (_vm.queryCond.applyType),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "applyType", $$v)
      },
      expression: "queryCond.applyType"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "采购单",
      "value": "purchase"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "调拨单",
      "value": "allocation"
    }
  })], 1), _vm._v(" "), _c('span', {
    staticStyle: {
      "margin-top": "10px"
    }
  }, [_c('el-checkbox', {
    model: {
      value: (_vm.queryCond.groupBySupplier),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "groupBySupplier", $$v)
      },
      expression: "queryCond.groupBySupplier"
    }
  }, [_vm._v("按供应商分组")])], 1)], 1)], 1)], 1), _vm._v(" "), _c('el-table', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState),
      expression: "loadingState"
    }],
    staticStyle: {
      "width": "100%"
    },
    attrs: {
      "data": _vm.tableData,
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "220"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "仓库",
      "width": "220"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierName",
      "label": "供应商",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "inventoryAmt",
      "label": "采购数量",
      "width": "120"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(scope.row.inventoryAmt) + " " + _vm._s(scope.row.stockUnit) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "totalPrice",
      "label": "采购金额",
      "width": "120"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(scope.row.totalPrice) + " 元\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "totalPaidAmt",
      "label": "支付金额",
      "width": "160"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(scope.row.totalPaidAmt) + " 元\n            ")]
      }
    }])
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1141:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1011);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("8495d8f8", content, true);

/***/ }),

/***/ 654:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1141)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(960),
  /* template */
  __webpack_require__(1077),
  /* scopeId */
  "data-v-48067fff",
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

/***/ 960:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__ = __webpack_require__(685);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
        MyCabinSelect: __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect___default.a
    },
    data() {
        return {
            tableData: [],
            loadingState: false,
            queryCond: {
                cabinCode: '',
                searchKey: '',
                startDate: null,
                endDate: null,
                groupBySupplier: false,
                applyType: ''
            },
            showOutStock: false
        };
    },
    mounted() {},
    methods: {
        getData() {
            let self = this;
            self.$data.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].inventoryReport({
                cabinCode: this.queryCond.cabinCode,
                searchKey: this.queryCond.searchKey,
                startDate: __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.queryCond.startDate),
                endDate: __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.queryCond.endDate),
                groupBySupplier: this.queryCond.groupBySupplier,
                applyType: this.queryCond.applyType
            }).then(values => {
                self.tableData = values;
            }).fail(resp => {
                self.$message.error("请求出错");
            }).always(() => {
                self.$data.loadingState = false;
            });
        },
        search() {
            this.tableData = [];
            this.queryCond.pageNo = 1;
            this.getData();
        },
        downloadExcel() {
            let param = '';
            let cond = {
                cabinCode: this.queryCond.cabinCode,
                searchKey: this.queryCond.searchKey,
                startDate: __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.queryCond.startDate),
                endDate: __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.queryCond.endDate),
                groupBySupplier: this.queryCond.groupBySupplier,
                applyType: this.queryCond.applyType
            };
            Object.keys(cond).forEach(key => {
                let val = cond[key];
                if (val) {
                    param = param + "&" + key + "=" + val;
                }
            });
            window.open(__WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* config */].server + "/report/inventoryReport?download=excel" + param);
        },
        handleSelect(item) {
            this.$data.query.name = item.value;
        }
    }
});

/***/ })

});