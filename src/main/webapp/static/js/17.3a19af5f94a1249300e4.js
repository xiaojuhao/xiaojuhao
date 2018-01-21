webpackJsonp([17],{

/***/ 1028:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".pagination[data-v-bf244b10]{margin:20px,0;text-align:left}.pop-message[data-v-bf244b10]{position:fixed;top:0;width:100%;height:100%;z-index:99998;background:gray}.pop-message-sub[data-v-bf244b10]{width:60%;height:75%;margin-left:40px;z-index:99999;background:#fff;border:1px solid}.info-row[data-v-bf244b10]{background:red}", ""]);

// exports


/***/ }),

/***/ 1098:
/***/ (function(module, exports) {

module.exports={render:function (){
var this$1 = this;
var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticStyle: {
      "position": "absolute"
    }
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('el-select', {
    staticStyle: {
      "width": "100px"
    },
    attrs: {
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
  })], 1), _vm._v(" "), _c('el-select', {
    staticStyle: {
      "width": "100px"
    },
    attrs: {
      "clearable": "",
      "placeholder": "状态"
    },
    model: {
      value: (_vm.queryCond.status),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "status", $$v)
      },
      expression: "queryCond.status"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "待入库",
      "value": "1"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "已入库",
      "value": "2"
    }
  })], 1), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "placeholder": "供应商或拨出仓库"
    },
    model: {
      value: (_vm.queryCond.fromSrc),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "fromSrc", $$v)
      },
      expression: "queryCond.fromSrc"
    }
  }), _vm._v(" "), _c('MyCabinSelect', {
    on: {
      "input": function (val) {
        this$1.queryCond.inCabinCode = val;
      }
    }
  }), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "120px"
    },
    attrs: {
      "placeholder": "原料名称搜索"
    },
    model: {
      value: (_vm.queryCond.searchKey),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "searchKey", $$v)
      },
      expression: "queryCond.searchKey"
    }
  }), _vm._v(" "), _c('el-date-picker', {
    attrs: {
      "type": "datetimerange",
      "range-separator": " ~ ",
      "placeholder": "选择时间范围"
    },
    model: {
      value: (_vm.queryCond.timerange),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "timerange", $$v)
      },
      expression: "queryCond.timerange"
    }
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索")])], 1), _vm._v(" "), _c('el-table', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState),
      expression: "loadingState"
    }],
    ref: "tableRef",
    staticStyle: {
      "width": "150%"
    },
    attrs: {
      "data": _vm.details,
      "border": "",
      "row-style": _vm.rowStyle,
      "show-summary": "",
      "summary-method": _vm.getSummaries
    },
    on: {
      "select-all": _vm.handleSelectAll,
      "select": _vm.handleSelect
    }
  }, [_c('el-table-column', {
    attrs: {
      "width": "65",
      "type": "selection",
      "selectable": _vm.checkSelectable
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "供应商/仓库",
      "width": "120"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.applyType == 'purchase' ? scope.row.supplierName : scope.row.fromCabinName) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "门店/仓库",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                     " + _vm._s(scope.row.cabinName) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "realSpecAmt",
      "label": "采购数量",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          on: {
            "change": function($event) {
              _vm.onSpecAmtChange(scope.row)
            }
          },
          model: {
            value: (scope.row.realSpecAmt),
            callback: function($$v) {
              _vm.$set(scope.row, "realSpecAmt", $$v)
            },
            expression: "scope.row.realSpecAmt"
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
      "prop": "realStockAmt",
      "label": "食材库存",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.realStockAmt),
            callback: function($$v) {
              _vm.$set(scope.row, "realStockAmt", $$v)
            },
            expression: "scope.row.realStockAmt"
          }
        }, [_c('template', {
          attrs: {
            "slot": "append"
          },
          slot: "append"
        }, [_vm._v(_vm._s(scope.row.stockUnit))])], 2)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "totalPrice",
      "label": "总价",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.totalPrice),
            callback: function($$v) {
              _vm.$set(scope.row, "totalPrice", $$v)
            },
            expression: "scope.row.totalPrice"
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
      "prop": "specPrice",
      "label": "单价",
      "width": "80"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.specPrice) + "元\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "类型",
      "width": "100",
      "formatter": _vm.formatApplyType
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "状态",
      "width": "100",
      "formatter": _vm.formatStatus
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "录入时间",
      "width": "180",
      "formatter": _vm.formateCreatedTime
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "creatorName",
      "label": "录入人",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "applyNum",
      "label": "单号",
      "width": "180"
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
  })], 1), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-top": "10px",
      "margin-left": "300px"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.showMessage
    }
  }, [_vm._v("确认入库")]), _vm._v(" "), _c('span', {
    staticStyle: {
      "margin-right": "20px"
    }
  })], 1)], 1), _vm._v(" "), _c('el-dialog', {
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
      "data": _vm.selectedItems,
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
      "label": "入库仓库"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "规格数量"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.realSpecAmt) + " " + _vm._s(scope.row.specUnit) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "接收数量"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.realStockAmt) + " " + _vm._s(scope.row.stockUnit) + "\n                ")]
      }
    }])
  })], 1), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-top": "10px",
      "margin-left": "300px"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("确认提交")]), _vm._v(" "), _c('span', {
    staticStyle: {
      "margin-right": "20px"
    }
  }), _vm._v(" "), _c('el-button', {
    on: {
      "click": function () {
        this$1.isShowMessage = false
      }
    }
  }, [_vm._v("关闭")])], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1154:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1028);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("134a538f", content, true);

/***/ }),

/***/ 669:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1154)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(973),
  /* template */
  __webpack_require__(1098),
  /* scopeId */
  "data-v-bf244b10",
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

/***/ 973:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect__ = __webpack_require__(688);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
        MyCabinSelect: __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect___default.a
    },
    data: function () {
        return {
            queryCond: {
                totalRows: 0,
                pageSize: 20,
                pageNo: 1,
                fromSrc: '',
                applyType: 'purchase',
                status: '1',
                timerange: [__WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].today(), new Date()]
            },
            details: [],
            loadingState: false,
            isShowMessage: false,
            selectedItems: []
        };
    },
    methods: {
        formatSpec(row) {
            if (row.specUnit != '无') {
                return row.specAmt + row.specUnit;
            } else {
                return row.stockAmt + row.stockUnit;
            }
        },
        formateCreatedTime(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateTime(row.gmtCreated);
        },
        formatStatus(row) {
            switch (row.status) {
                case "0":
                    return "待提交";
                case "1":
                    return "待入库";
                case "2":
                    return "已入库";
                case "3":
                    return "作废";
            }
        },
        formatApplyType(row) {
            switch (row.applyType) {
                case 'purchase':
                    return '采购单';
                case 'allocation':
                    return '调拨单';
                default:
                    return '未知';
            }
        },
        handleCurrentChange(pageNo) {
            this.queryCond.pageNo = pageNo;
            this.getData();
        },
        handleSelectAll(selectedItems) {
            this.selectedItems = selectedItems;
            this.reCalcSelected();
        },
        handleSelect(selectedItems, thisRow) {
            this.selectedItems = selectedItems;
            this.reCalcSelected();
        },
        checkSelectable(row, index) {
            return row.status == '1';
        },
        reCalcSelected() {
            this.details.forEach(it => __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(it, 'isSelected', false));
            this.selectedItems.forEach(it => {
                __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(it, 'isSelected', true && this.checkSelectable(it));
            });
        },
        checked() {
            setTimeout(() => {
                this.details.forEach(row => {
                    this.$refs.tableRef.toggleRowSelection(row, !!row.isSelected);
                }, 0);
                this.reCalcSelected();
            });
        },
        getSummaries(param) {
            let { columns, data } = param;
            let sums = [];
            columns.forEach((col, idx) => {
                if (idx == 0) {
                    sums[idx] = "总价";
                }
                if (col.property == 'totalPrice') {
                    //计算统计值
                    sums[idx] = this.sumTotalPrice;
                    return;
                }
                //sums[idx] = "N/A";
            });
            return sums;
        },
        onSubmit() {
            this.isShowMessage = false;
            this.loadingState = true;
            let param = {
                dataJson: JSON.stringify(this.selectedItems)
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].confirmInventoryDetail(param).then(resp => {
                this.$message.success("操作成功");
                this.getData();
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.loadingState = false;
            });
        },
        onBack() {
            this.$router.go(-1);
        },
        showMessage() {
            this.isShowMessage = !this.isShowMessage;
        },
        cancelMsg() {
            this.isShowMessage = false;
        },
        rowStyle(row) {
            if (row.isSelected == true) return 'background:#E0E0E0';
        },
        onSpecAmtChange(row) {
            let realStockAmt = row.realSpecAmt * row.transRate * row.utilizationRatio / 100;
            __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(row, 'realStockAmt', realStockAmt);
        },
        getData() {
            this.queryCond.startCreatedTime = '';
            this.queryCond.endCreatedTime = '';
            if (this.queryCond.timerange && this.queryCond.timerange[0]) {
                this.queryCond.startCreatedTime = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateTimeT(this.queryCond.timerange[0]);
            }
            if (this.queryCond.timerange && this.queryCond.timerange[1]) {
                this.queryCond.endCreatedTime = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateTimeT(this.queryCond.timerange[1]);
            }
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryInventoryDetailPage(this.queryCond).then(page => {
                this.details = page.values;
                this.queryCond.totalRows = page.totalRows;
                this.selectedItems = [];
                this.details.forEach(it => {
                    let selecStatus = this.checkSelectable(it);
                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(it, 'isSelected', selecStatus);
                    if (selecStatus) this.selectedItems.push(it);
                });
                this.checked();
            });
        },
        search() {
            this.getData();
        }
    },
    mounted() {
        this.getData();
    },
    computed: {
        sumTotalPrice() {
            let sum = this.selectedItems.reduce((sum, item) => {
                return sum + Number(item.totalPrice);
            }, 0);
            return sum.toFixed(2);
        }
    }
});

/***/ })

});