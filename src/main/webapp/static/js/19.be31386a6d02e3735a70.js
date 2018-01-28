webpackJsonp([19],{

/***/ 1062:
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
      "width": "180px"
    },
    attrs: {
      "placeholder": "原料名称或配音"
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
  }, [_vm._v("下载EXCEL")])], 1)], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "currSpecAndUnit",
      "label": "采购库存",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "currStockAndUnit",
      "label": "食材库存",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "inStock",
      "label": "入库数量",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "sale",
      "label": "销售数量",
      "width": "120"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(-1 * scope.row.sale) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "inStockLoss",
      "label": "入库损耗数量",
      "width": "160"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(-1 * scope.row.inStockLoss) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "claimLoss",
      "label": "报损数量",
      "width": "160"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(-1 * scope.row.claimLoss) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "correctDelta",
      "label": "盘点损耗",
      "width": "160"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(-1 * scope.row.correctDelta) + "\n            ")]
      }
    }])
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1128:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(998);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("ac945486", content, true);

/***/ }),

/***/ 655:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1128)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(961),
  /* template */
  __webpack_require__(1062),
  /* scopeId */
  "data-v-2bd6cce0",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 687:
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

/***/ 689:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(687),
  /* template */
  __webpack_require__(690),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 690:
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

/***/ 961:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__ = __webpack_require__(689);
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
                endDate: null
            },
            showOutStock: false
        };
    },
    mounted() {
        this.loadParam();
    },
    methods: {
        keepParam() {
            this.$store.commit("setQueryCond", this.queryCond);
        },
        loadParam() {
            Object.assign(this.queryCond, this.$store.state.queryCond);
        },
        getData() {
            this.keepParam();
            let self = this;
            self.$data.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].stockReport({
                cabinCode: this.queryCond.cabinCode,
                searchKey: this.queryCond.searchKey,
                startDate: __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.queryCond.startDate),
                endDate: __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.queryCond.endDate)
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
            Object.keys(this.queryCond).forEach(key => {
                let val = this.queryCond[key];
                if (val) {
                    param = param + "&" + key + "=" + val;
                }
            });
            window.open(__WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* config */].server + "/report/stockReport?download=excel" + param);
        },
        handleSelect(item) {
            this.$data.query.name = item.value;
        }
    }
});

/***/ }),

/***/ 998:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-2bd6cce0]{margin-bottom:20px}.handle-select[data-v-2bd6cce0]{width:120px}.handle-input[data-v-2bd6cce0]{width:300px;display:inline-block}", ""]);

// exports


/***/ })

});