webpackJsonp([24],{

/***/ 1046:
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
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "已删除",
      "value": "3"
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
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "type": "date",
      "placeholder": "起始日期"
    },
    model: {
      value: (_vm.startDate),
      callback: function($$v) {
        _vm.startDate = $$v
      },
      expression: "startDate"
    }
  }), _vm._v("\n            -\n            "), _c('el-date-picker', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "type": "date",
      "placeholder": "结束日期"
    },
    model: {
      value: (_vm.endDate),
      callback: function($$v) {
        _vm.endDate = $$v
      },
      expression: "endDate"
    }
  }), _vm._v(" "), _c('br'), _vm._v(" "), _c('el-select', {
    staticStyle: {
      "width": "100px"
    },
    attrs: {
      "clearable": "",
      "placeholder": "分类"
    },
    model: {
      value: (_vm.queryCond.category),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "category", $$v)
      },
      expression: "queryCond.category"
    }
  }, _vm._l((_vm.categorySel), function(item) {
    return _c('el-option', {
      key: item.unitCode,
      attrs: {
        "label": item.unitName,
        "value": item.unitCode
      }
    })
  })), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "200px"
    },
    attrs: {
      "placeholder": "调拨单号"
    },
    model: {
      value: (_vm.queryCond.applyNum),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "applyNum", $$v)
      },
      expression: "queryCond.applyNum"
    }
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.download
    }
  }, [_vm._v("\n                导出EXCEL\n            ")])], 1), _vm._v(" "), _c('el-table', {
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
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "label": "门店/仓库",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.cabinName) + "\n                ")]
      }
    }])
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
      "prop": "materialName",
      "label": "原料名称",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "realSpecAmt",
      "label": "数量",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.realSpecAmt) + " " + _vm._s(scope.row.specUnit) + "\n                ")]
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
      "prop": "basePrice",
      "label": "基价",
      "width": "80"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('font', {
          attrs: {
            "color": "blue"
          }
        }, [_vm._v(_vm._s(scope.row.basePrice) + "元")])]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "totalPrice",
      "label": "总价",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.totalPrice) + " 元\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "creatorName",
      "label": "录入",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "录入时间",
      "width": "180",
      "formatter": _vm.formateCreatedTime
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
  })], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1114:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(984);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("33c12fb4", content, true);

/***/ }),

/***/ 635:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1114)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(944),
  /* template */
  __webpack_require__(1046),
  /* scopeId */
  "data-v-01e3b8e2",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 685:
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

/***/ 686:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(685),
  /* template */
  __webpack_require__(687),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 687:
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

/***/ 944:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect__ = __webpack_require__(686);
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
                applyNum: '',
                applyType: 'allocation', //只查询采购单
                status: '', //已入库
                category: '',
                onlymy: true
            },
            details: [],
            loadingState: false,
            startDate: null,
            endDate: null,
            categorySel: []
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
        formatePaidTime(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateTime(row.paidTime);
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
        formatPaidStatus(row) {
            switch (row.paidStatus) {
                case "0":
                    return "未支付";
                case "1":
                    return "已支付";
                case "2":
                    return "支付失败";
                case "3":
                    return "无需支付";
            }
        },
        formatPaidStatusType(row) {
            if (row.paidStatus == "0") {
                return 'danger';
            }
            return 'gray';
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
        download() {
            this.queryCond.startCreatedTime = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.startDate);
            this.queryCond.endCreatedTime = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.endDate);
            let param = Object.keys(this.queryCond).map(k => k + "=" + this.queryCond[k]).join('&');
            window.open(__WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* config */].server + "/inventoryApply/queryInventoryDetailPage?download=excelInventory&" + param);
        },
        getData() {
            this.queryCond.startCreatedTime = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.startDate);
            this.queryCond.endCreatedTime = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.endDate);
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryInventoryDetailPage(this.queryCond).then(page => {
                this.details = page.values;
                this.queryCond.totalRows = page.totalRows;
            });
        },
        search() {
            this.getData();
        }
    },
    mounted() {
        this.getData();
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryUnitByGroup('material_category').then(cates => this.categorySel = cates);
    },
    computed: {}
});

/***/ }),

/***/ 984:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".pagination[data-v-01e3b8e2]{margin:20px,0;text-align:left}.pop-message[data-v-01e3b8e2]{position:fixed;top:0;width:100%;height:100%;z-index:99998;background:gray}.pop-message-sub[data-v-01e3b8e2]{width:60%;height:75%;margin-left:40px;z-index:99999;background:#fff;border:1px solid}.info-row[data-v-01e3b8e2]{background:red}", ""]);

// exports


/***/ })

});