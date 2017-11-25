webpackJsonp([33],{

/***/ 625:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(800)

var Component = __webpack_require__(263)(
  /* script */
  __webpack_require__(698),
  /* template */
  __webpack_require__(763),
  /* scopeId */
  "data-v-16245336",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 698:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(264);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            cur_page: 1,
            pageSize: 5,
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
            return "报损单";
        },
        handleCurrentChange(val) {
            this.cur_page = val;
            this.getData();
        },
        getData() {
            let param = {
                status: this.query.status
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMyLossApply(param).then(page => {
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
        }
    }
});

/***/ }),

/***/ 726:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-16245336]{margin-bottom:20px}.handle-select[data-v-16245336]{width:120px}.handle-input[data-v-16245336]{width:300px;display:inline-block}table.gridtable[data-v-16245336]{width:80%;height:8px;font-family:verdana,arial,sans-serif;color:#333;border-width:1px;border-color:#666;border-collapse:collapse}table.gridtable th[data-v-16245336]{height:8px;padding:8px;border:1px solid #666;background-color:#dedede}table.gridtable td[data-v-16245336]{height:8px;padding:8px;border:1px solid #666;background-color:#fff}", ""]);

// exports


/***/ }),

/***/ 763:
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
      "prop": "cabinCode",
      "label": "单位编码",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "单位名称",
      "width": "150"
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
      "prop": "applyNum",
      "label": "采购单号",
      "width": "350"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "150"
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
              _vm.printBill(scope.row)
            }
          }
        }, [_vm._v("打印")])]
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

/***/ 800:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(726);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(265)("1eae793c", content, true);

/***/ })

});