webpackJsonp([17],{

/***/ 1044:
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
  }, [_c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_c('MyCabinSelect', {
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
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索")])], 1)], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "currstock",
      "label": "当前库存",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "sale",
      "label": "销售数量",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "inStock",
      "label": "入库数量",
      "width": "160"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "inStockLoss",
      "label": "入库损耗数量",
      "width": "160"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "claimLoss",
      "label": "报损数量",
      "width": "160"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "correctDelta",
      "label": "盘点损耗",
      "width": "160"
    }
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1104:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(986);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("ac945486", content, true);

/***/ }),

/***/ 653:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1104)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(953),
  /* template */
  __webpack_require__(1044),
  /* scopeId */
  "data-v-2bd6cce0",
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

/***/ 689:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(686),
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

/***/ 953:
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
                searchKey: ''
            },
            showOutStock: false
        };
    },
    mounted() {
        this.loadParam();
        this.getData();
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
                searchKey: this.queryCond.searchKey
            }).then(page => {
                self.tableData = page.values;
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
        handleSelect(item) {
            this.$data.query.name = item.value;
        }
    }
});

/***/ }),

/***/ 986:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-2bd6cce0]{margin-bottom:20px}.handle-select[data-v-2bd6cce0]{width:120px}.handle-input[data-v-2bd6cce0]{width:300px;display:inline-block}", ""]);

// exports


/***/ })

});