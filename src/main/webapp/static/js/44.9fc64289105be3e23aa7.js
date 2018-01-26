webpackJsonp([44],{

/***/ 1061:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('el-select', {
    model: {
      value: (_vm.query.status),
      callback: function($$v) {
        _vm.$set(_vm.query, "status", $$v)
      },
      expression: "query.status"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "配送中",
      "value": "4"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "已入库",
      "value": "5"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "撤销",
      "value": "6"
    }
  })], 1), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索采购单")])], 1), _vm._v(" "), _c('el-table', {
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
      "data": _vm.data,
      "border": "",
      "element-loading-text": "拼命加载中",
      "element-loading-spinner": "el-icon-loading",
      "element-loading-background": "rgb(0, 0, 0, 0.8)"
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "门店",
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
      "label": "创建日期",
      "width": "120",
      "formatter": _vm.formatGmtCreated
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "proposer",
      "label": "申请人",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "status",
      "label": "状态",
      "width": "80",
      "formatter": _vm.formatStatus
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "applyType",
      "label": "类型",
      "width": "100",
      "formatter": _vm.formatApplyType
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "支付状态",
      "width": "100",
      "formatter": _vm.formatPayStatus
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "支付时间",
      "width": "120",
      "formatter": _vm.formatPayTime
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "payRemark",
      "label": "支付备注",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "remark",
      "label": "采购备注",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "applyNum",
      "label": "采购单号",
      "width": "350"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "260"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [(scope.row.status == '4') ? _c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.confirmOrder(scope.row)
            }
          }
        }, [_vm._v("\n                    入库\n                ")]) : _vm._e(), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.showDetail(scope.row)
            }
          }
        }, [_vm._v("明细")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.printBill(scope.row)
            }
          }
        }, [_vm._v("打印")]), _vm._v(" "), (scope.row.status == '4') ? _c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.deleteBill(scope.row)
            }
          }
        }, [_vm._v("作废")]) : _vm._e()]
      }
    }])
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "pagination"
  }, [_c('el-pagination', {
    attrs: {
      "layout": "prev, pager, next",
      "total": _vm.totalRows,
      "page-size": _vm.pageSize
    },
    on: {
      "current-change": _vm.handleCurrentChange
    }
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1127:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(997);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("2fe3be7f", content, true);

/***/ }),

/***/ 666:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1127)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(972),
  /* template */
  __webpack_require__(1061),
  /* scopeId */
  "data-v-29ce9838",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 972:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            tableData: [],
            pageNo: 1,
            pageSize: 10,
            totalRows: 0,
            loadingState: false,
            query: {
                status: '4'
            },
            showOutStock: false
        };
    },
    mounted() {
        this.getData();
    },
    computed: {
        data() {
            const self = this;
            return self.tableData.filter(function (d) {
                return d;
            });
        }
    },
    methods: {
        formatStatus(row) {
            switch (row.status) {
                case "4":
                    return '配送中';
                case "5":
                    return "已入库";
                case "6":
                    return "撤销";
                default:
                    return '未知';
            }
        },
        formatApplyType(row) {
            switch (row.applyType) {
                case "purchase":
                    return "采购单";
                case "allocate_in":
                    return "拨入";
                case "allocate_out":
                    return "拨出";
                case "claim_loss":
                    return "报损";
                default:
                    return "未知";
            }
        },
        formatPayStatus(row) {
            switch (row.paidStatus) {
                case "0":
                    return "待支付";
                case "1":
                    return "支付成功";
                case "2":
                    return "支付失败";
                default:
                    return "未知状态";
            }
        },
        formatPayTime(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDate(row.paidTime);
        },
        formatGmtCreated(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDate(row.gmtCreated);
        },
        handleCurrentChange(val) {
            this.pageNo = val;
            this.getData();
        },
        getData() {
            let param = {
                status: this.query.status,
                pageNo: this.pageNo,
                pageSize: this.pageSize,
                applyTypes: 'purchase'
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryInventoryApplyPage(param).then(page => {
                this.totalRows = page.totalRows;
                this.tableData = page.values;
            });
        },
        search() {
            this.tableData = [];
            this.getData();
        },
        confirmOrder(item) {
            this.$router.push({ path: "/inventoryInConfirm", query: { applyNum: item && item.applyNum } });
        },
        printBill(item) {
            window.open(__WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* config */].server + "/print?applyNum=" + item.applyNum);
        },
        showDetail(item) {
            this.$router.push({ path: "/inventoryDetail", query: { applyNum: item && item.applyNum } });
        },
        deleteBill(item) {
            this.$confirm('是否删除采购单?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].deleteInventory(item.applyNum).then(val => {
                    this.$message("删除成功");
                    this.getData();
                }).fail(resp => {
                    this.$message.error(resp.message);
                });
            });
        }
    }
});

/***/ }),

/***/ 997:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-29ce9838]{margin-bottom:20px}.handle-select[data-v-29ce9838]{width:120px}.handle-input[data-v-29ce9838]{width:300px;display:inline-block}table.gridtable[data-v-29ce9838]{width:80%;height:8px;font-family:verdana,arial,sans-serif;color:#333;border-width:1px;border-color:#666;border-collapse:collapse}table.gridtable th[data-v-29ce9838]{height:8px;padding:8px;border:1px solid #666;background-color:#dedede}table.gridtable td[data-v-29ce9838]{height:8px;padding:8px;border:1px solid #666;background-color:#fff}", ""]);

// exports


/***/ })

});