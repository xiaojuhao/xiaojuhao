webpackJsonp([13],{

/***/ 1016:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-d379d40c]{margin-bottom:20px}.handle-select[data-v-d379d40c]{width:120px}.handle-input[data-v-d379d40c]{width:300px;display:inline-block}.bg-selected[data-v-d379d40c]{background:red}", ""]);

// exports


/***/ }),

/***/ 1081:
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
        _vm.queryCond.cabinCode = v;
      }
    }
  }), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "180px"
    },
    attrs: {
      "placeholder": "搜索条件"
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
  }, [_vm._v("搜索")])], 1), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 8
    }
  }, [_c('div', {
    staticStyle: {
      "position": "relative",
      "float": "right"
    }
  })])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "border": "",
      "element-loading-text": "拼命加载中",
      "element-loading-spinner": "el-icon-loading",
      "element-loading-background": "rgb(0, 0, 0, 0.8)",
      "row-style": _vm.rowStyle
    },
    on: {
      "select": _vm.handleSelect,
      "select-all": _vm.handleSelectAll
    }
  }, [_c('el-table-column', {
    attrs: {
      "type": "selection",
      "width": "55"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "仓库",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "申请日期",
      "width": "120",
      "formatter": _vm.formatRequireDate
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "currStock",
      "label": "当前库存",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "需求量",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(scope.row.requireAmt) + _vm._s(scope.row.stockUnit) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "规格单位",
      "width": "160"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-select', {
          staticStyle: {
            "width": "140px"
          },
          attrs: {
            "slot": "append",
            "size": "small"
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
      "label": "规格",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(scope.row.specQty) + _vm._s(scope.row.stockUnit) + "/" + _vm._s(scope.row.specUnit) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购量",
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
      "label": "采购价",
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
      "label": "采购类型",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-select', {
          staticStyle: {
            "width": "80px"
          },
          attrs: {
            "slot": "append",
            "placeholder": "请选择"
          },
          slot: "append",
          model: {
            value: (scope.row.purchaseType),
            callback: function($$v) {
              _vm.$set(scope.row, "purchaseType", $$v)
            },
            expression: "scope.row.purchaseType"
          }
        }, [_c('el-option', {
          attrs: {
            "label": "采购",
            "value": "1"
          }
        }), _vm._v(" "), _c('el-option', {
          attrs: {
            "label": "调拨",
            "value": "2"
          }
        })], 1)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "供应商/仓库",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-select', {
          directives: [{
            name: "show",
            rawName: "v-show",
            value: (scope.row.purchaseType == '1'),
            expression: "scope.row.purchaseType == '1'"
          }],
          staticStyle: {
            "width": "140px"
          },
          on: {
            "change": function($event) {
              _vm.onSelectSupplier(scope.row)
            }
          },
          model: {
            value: (scope.row.supplierCode),
            callback: function($$v) {
              _vm.$set(scope.row, "supplierCode", $$v)
            },
            expression: "scope.row.supplierCode"
          }
        }, _vm._l((scope.row.supplierSel), function(item) {
          return _c('el-option', {
            key: item.supplierCode,
            attrs: {
              "label": item.supplierName,
              "value": item.supplierCode
            }
          })
        })), _vm._v(" "), _c('el-select', {
          directives: [{
            name: "show",
            rawName: "v-show",
            value: (scope.row.purchaseType == '2'),
            expression: "scope.row.purchaseType == '2'"
          }],
          staticStyle: {
            "width": "140px"
          },
          on: {
            "change": function($event) {
              _vm.onSelectCabin(scope.row)
            }
          },
          model: {
            value: (scope.row.fromCabinCode),
            callback: function($$v) {
              _vm.$set(scope.row, "fromCabinCode", $$v)
            },
            expression: "scope.row.fromCabinCode"
          }
        }, _vm._l((_vm.cabinSels), function(item) {
          return _c('el-option', {
            key: item.cabinCode,
            attrs: {
              "label": item.cabinName,
              "value": item.cabinCode
            }
          })
        }))]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "remark",
      "label": "需求备注",
      "width": "120"
    }
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "pagination"
  }, [_c('el-pagination', {
    attrs: {
      "layout": "prev, pager, next",
      "total": _vm.queryCond.totalRows,
      "page-size": _vm.queryCond.pageSize,
      "current-page": _vm.queryCond.pageNo
    },
    on: {
      "current-change": _vm.handleCurrentChange,
      "update:currentPage": function($event) {
        _vm.$set(_vm.queryCond, "pageNo", $event)
      }
    }
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
      "disabled": _vm.selectItems.length == 0
    },
    on: {
      "click": function($event) {
        _vm.submitSelectedData(1)
      }
    }
  }, [_vm._v("\n                暂存\n            ")]), _vm._v(" "), _c('span', {
    staticStyle: {
      "margin-right": "20px"
    }
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "disabled": _vm.selectItems.length == 0
    },
    on: {
      "click": _vm.showConfirmDialog
    }
  }, [_vm._v("\n                确认提交\n            ")]), _vm._v(" "), _c('span', {
    staticStyle: {
      "margin-right": "20px"
    }
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "danger",
      "disabled": _vm.selectItems.length == 0
    },
    on: {
      "click": _vm.submitCancel
    }
  }, [_vm._v("\n                删除需求\n            ")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "visible": _vm.isShowMessage,
      "title": "确认入库信息"
    },
    on: {
      "update:visible": function($event) {
        _vm.isShowMessage = $event
      }
    }
  }, [_c('el-table', {
    staticStyle: {
      "width": "100%"
    },
    attrs: {
      "data": _vm.selectItems,
      "max-height": "400",
      "row-class-name": "info-row"
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierName",
      "label": "供应商",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "规格数量"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.specAmt) + _vm._s(scope.row.specUnit) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "入库数量",
      "width": ""
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.specAmt * scope.row.specQty) + " " + _vm._s(scope.row.stockUnit) + "\n                ")]
      }
    }])
  })], 1), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-top": "10px",
      "text-align": "center"
    }
  }, [_c('el-button', {
    on: {
      "click": function () {
        this$1.isShowMessage = false
      }
    }
  }, [_vm._v("关闭")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "disabled": _vm.selectItems.length == 0
    },
    on: {
      "click": function($event) {
        _vm.submitSelectedData(2)
      }
    }
  }, [_vm._v("\n                提交采购\n            ")])], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1132:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1016);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("06f84f78", content, true);

/***/ }),

/***/ 632:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1132)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(934),
  /* template */
  __webpack_require__(1081),
  /* scopeId */
  "data-v-d379d40c",
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
            console.log(this.$store.state.allMaterials);
            this.allValues = this.$store.state.allMaterials;
            this.selectedCode = this.$props.value;
        },
        enterkey(e) {},
        filterMethod(input) {
            let $data = this.$data;
            setTimeout(() => {
                $data.valuesShow = $data.allValues.filter(item => {
                    var key = [item.materialCode, item.materialName, item.searchKey].join(',');
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
                    if (this.excludesMap[item.materialCode]) {
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

/***/ 690:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(687),
  /* template */
  __webpack_require__(692),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 691:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(688),
  /* template */
  __webpack_require__(693),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 692:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "请选择",
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
      key: item.materialCode,
      attrs: {
        "label": item.materialName + '-' + item.materialCode,
        "value": item.materialCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 693:
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

/***/ 934:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__ = __webpack_require__(690);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect__ = __webpack_require__(691);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect__);
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
        MaterialSelection: __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default.a,
        MyCabinSelect: __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect___default.a
    },
    data() {
        return {
            queryCond: {
                pageNo: 1,
                pageSize: 10,
                totalRows: 0,
                materialCode: '',
                cabinCode: '',
                searchKey: '',
                status: '0'
            },
            tableData: [],
            loadingState: false,
            userRole: this.$store.state.userRole,
            isShowMessage: false,
            selectItems: [],
            cabinSels: [{ cabinCode: 'WH0001', cabinName: '上海仓库' }, { cabinCode: 'WH0002', cabinName: '常州魏村仓库' }, { cabinCode: 'WH0003', cabinName: '常州小句号管理有限公司' }]
        };
    },
    mounted() {
        this.loadParam();
        this.queryData();
    },
    methods: {
        handleCurrentChange(val) {
            this.queryCond.pageNo = val;
            this.queryData();
        },
        keepParam() {
            let p = {
                pageNo: this.queryCond.pageNo,
                pageSize: this.queryCond.pageSize,
                totalRows: this.queryCond.totalRows,
                materialCode: this.queryCond.materialCode,
                status: this.queryCond.status
            };
            this.$store.commit("setQueryCond", p);
        },
        loadParam() {
            Object.assign(this.queryCond, this.$store.state.queryCond);
        },
        formatRequireDate(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDate(row.requireDate);
        },
        onSelectSpec(item) {
            item.specCodeSel && item.specCodeSel.forEach(it => {
                if (it.specCode == item.specCode) {
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specName', it.specName);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specUnit', it.specUnit);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'stockUnit', it.stockUnit);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specQty', it.transRate);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'brandName', it.brandName);
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'homeplace', it.homeplace);
                }
            });
            this.calcSpecAmt(item);
        },
        onSelectSupplier(item) {
            item.supplierSel && item.supplierSel.forEach(it => {
                if (it.supplierCode == item.supplierCode) {
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'supplierName', it.supplierName);
                }
            });
        },
        onSelectCabin(item) {
            this.cabinSels.forEach(it => {
                if (it.cabinCode == item.fromCabinCode) {
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'fromCabinName', it.cabinName);
                }
            });
        },
        queryData() {
            this.selectItems = [];
            this.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialRequire({
                pageSize: this.queryCond.pageSize,
                pageNo: this.queryCond.pageNo,
                materialCode: this.queryCond.materialCode,
                cabinCode: this.queryCond.cabinCode,
                status: this.queryCond.status,
                searchKey: this.queryCond.searchKey
            }).then(page => {
                this.tableData = page.values;
                this.queryCond.totalRows = page.totalRows;
                this.initTableData(this.tableData);
            }).fail(resp => {
                this.$message.error("请求出错");
            }).always(resp => {
                this.loadingState = false;
            });
        },
        initTableData(tableData) {
            tableData.forEach(it => __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'specCodeSel', []));
            tableData.forEach(it => __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'supplierSel', []));
            let codes = [];
            this.tableData.forEach(it => {
                codes.push(it.materialCode);
            });
            //------初始化当前库存----------
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryStockByMaterialCodes({
                materialCodes: codes.join(',')
            }).then(list => {
                let map = new Map();
                list.forEach(it => {
                    map.set(it.cabinCode + "_" + it.materialCode, it);
                });
                tableData.forEach(it => {
                    let stock = map.get(it.cabinCode + "_" + it.materialCode);
                    if (stock) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'currStock', stock.currStock);
                    } else {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'currStock', 0);
                    }
                });
            }).fail(resp => {
                //console.log(resp)
            });
            //-------初始化原料规格---------
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
                //遍历每条记录，设置规则
                tableData.forEach(it => {
                    if (map.get(it.materialCode)) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'specCodeSel', map.get(it.materialCode));
                    }
                    //如果记录本身没有规格信息，就将第一个设置为默认规格
                    if (!it.specCode && it.specCodeSel.length > 0) {
                        console.log('dfadfsfsff');
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'specCode', it.specCodeSel[0].specCode);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'specUnit', it.specCodeSel[0].specUnit);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'stockUnit', it.specCodeSel[0].stockUnit);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'specQty', it.specCodeSel[0].transRate);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'brandName', it.specCodeSel[0].brandName);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'homeplace', it.specCodeSel[0].homeplace);
                    }
                    if (!it.specQty && it.specUnit == it.stockUnit) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'specQty', 1);
                    }
                });
            });
            //-----初始化原料供应商---------
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].querySuppliersByMaterialCodes({
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
                //遍历每条记录，设置规则
                tableData.forEach(it => {
                    if (map.get(it.materialCode)) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'supplierSel', map.get(it.materialCode));
                    }
                    //如果记录本身没有供应商信息，就将第一个设置为供应商
                    if (!it.supplierCode && it.supplierSel.length > 0) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'supplierCode', it.supplierSel[0].supplierCode);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'supplierName', it.supplierSel[0].supplierName);
                    }
                });
            });
            //计算推荐采购量
            tableData.forEach(it => this.calcSpecAmt(it));
        },
        search() {
            this.tableData = [];
            this.queryData();
        },
        rowStyle(row) {
            if (row.isSelected == true) return 'background:#E0E0E0';
        },
        calcSpecAmt(item) {
            if (!item.specQty) {
                __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specQty', 1);
            }
            let specAmt = item.requireAmt / item.specQty;
            if (specAmt) {
                specAmt = specAmt.toFixed(3);
            }
            __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specAmt', specAmt);
        },
        handleSelect(sels, item) {
            this.selectItems = sels;
            __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'isSelected', false);
            setTimeout(() => sels.forEach(it => __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'isSelected', true)), 0);
        },
        handleSelectAll(sels) {
            this.selectItems = sels;
            setTimeout(() => {
                this.tableData.forEach(it => __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'isSelected', false));
                sels.forEach(it => __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(it, 'isSelected', true));
            }, 0);
        },
        showConfirmDialog() {
            this.isShowMessage = true;
        },
        submitSelectedData(type) {
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].handleRequire({
                requires: JSON.stringify(this.selectItems),
                handleType: type
            }).then(resp => {
                this.$message("提交成功");
                this.queryData();
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.isShowMessage = false;
            });
        },
        submitCancel() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].cancelRequire({
                items: JSON.stringify(this.selectItems)
            }).then(resp => {
                this.$message("处理成功");
                this.queryData();
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.isShowMessage = false;
            });
        }
    }
});

/***/ })

});