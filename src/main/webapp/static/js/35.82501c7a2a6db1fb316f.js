webpackJsonp([35],{

/***/ 1029:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("库存流水")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v(_vm._s(_vm.query.cabinName))])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "element-loading-background": "rgb(0, 0, 0, 0.8)"
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "id",
      "label": "顺序",
      "width": "80"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "仓库",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "opType",
      "label": "操作类型",
      "width": "120",
      "formatter": _vm.formatOpType
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "amt",
      "label": "库存变化",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "stockUnit",
      "label": "库存单位",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "operator",
      "label": "操作人",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "gmtCreated",
      "label": "操作时间",
      "width": "180",
      "formatter": _vm.formatDate
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "remark",
      "label": "备注",
      "width": "300"
    }
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "pagination"
  }, [_c('el-pagination', {
    attrs: {
      "current-page": _vm.cur_page,
      "layout": "prev, pager, next",
      "total": _vm.totalRows,
      "page-size": _vm.pageSize
    },
    on: {
      "current-change": _vm.handleCurrentChange,
      "update:currentPage": function($event) {
        _vm.cur_page = $event
      }
    }
  })], 1), _vm._v(" "), _c('div', {
    staticStyle: {
      "text-align": "center"
    }
  }, [_c('el-button', {
    on: {
      "click": _vm.returnBack
    }
  }, [_vm._v("返回")])], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1072:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(979);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("25c82977", content, true);

/***/ }),

/***/ 644:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1072)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(933),
  /* template */
  __webpack_require__(1029),
  /* scopeId */
  "data-v-6f683492",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 933:
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


/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            tableData: [],
            cur_page: 1,
            pageSize: 10,
            totalRows: 0,
            loadingState: false,
            query: {
                cabinCode: this.$route.query.cabin,
                materialCode: this.$route.query.mcode,
                cabinName: ''
            },
            showOutStock: false
        };
    },
    mounted() {
        this.getData();
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getCabinByCode(this.query.cabinCode).then(vo => {
            this.query.cabinName = vo.name;
        });
    },
    methods: {
        returnBack() {
            this.$router.go(-1);
        },
        handleCurrentChange(val) {
            this.cur_page = val;
            this.getData();
        },
        formatOpType(row) {
            switch (row.opType) {
                case "in_stock":
                    return "采购入库";
                case "in_stock_loss":
                    return "采购损耗";
                case "out_stock":
                    return "出库";
                case "correct":
                    return "盘点库存";
                case "claim_loss":
                    return "报损";
                case "allocation":
                    return "库存调拨";
                case "correct_delta":
                    return "盘点差额";
                default:
                    return row.opType;
            }
        },
        formatDate(row) {
            let date = new Date(row.gmtCreated);
            return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + (date.getHours() + 1) + ":" + date.getMinutes() + ":" + date.getSeconds();
        },
        getData() {
            let self = this;
            self.$data.loadingState = true;
            let param = {
                pageSize: self.pageSize,
                pageNo: self.cur_page,
                materialCode: self.query.materialCode,
                cabinCode: self.query.cabinCode
            };

            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialsStockHistoryPage(param).then(page => {
                self.tableData = page.values;
                self.totalRows = page.totalRows;
            }).fail(function (resp) {
                self.$message.error("请求出错");
            }).always(function (resp) {
                self.$data.loadingState = false;
            });
        },
        search() {
            this.cur_page = 1;
            this.tableData = [];
            this.getData();
        }
    }
});

/***/ }),

/***/ 979:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-6f683492]{margin-bottom:20px}.handle-select[data-v-6f683492]{width:120px}.handle-input[data-v-6f683492]{width:300px;display:inline-block}", ""]);

// exports


/***/ })

});