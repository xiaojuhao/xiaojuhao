webpackJsonp([31],{

/***/ 637:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(817)

var Component = __webpack_require__(263)(
  /* script */
  __webpack_require__(713),
  /* template */
  __webpack_require__(779),
  /* scopeId */
  "data-v-23f5ab62",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 713:
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
//
//
//
//
//
//
//
//
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
                materialCode: ''
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
        handleCurrentChange(val) {
            this.cur_page = val;
            this.getData();
        },
        getData() {
            let self = this;
            self.$data.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialsStockPage({
                pageSize: self.$data.pageSize,
                pageNo: self.$data.cur_page,
                materialCode: self.$data.materialCode,
                storeCode: 'M000',
                stockType: '1'
            }).then(page => {
                console.log(page);
                page.values.forEach(item => item.fenku = []);
                self.tableData = page.values;
                self.totalRows = page.totalRows;
            }).fail(resp => {
                self.$message.error("请求出错");
            }).always(() => {
                self.$data.loadingState = false;
            });
        },
        search() {
            this.tableData = [];
            this.cur_page = 1;
            this.getData();
        },
        handleSelect(item) {
            this.$data.query.name = item.value;
        },
        querySearch(queryString, cb) {
            var data = [];
            data.push({ id: 1, value: 'aaaaa' });
            data.push({ id: 2, value: 'bbbbb' });
            data.push({ id: 3, value: 'ccccc' });
            console.log(this.$data.query);
            cb(data);
        },
        expand(row, expanded) {
            if (!expanded) {
                return;
            }
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryAllFenkuMaterialsStock(row.materialCode).then(list => {
                row.fenku = list;
            });
        },
        getUtilizationRatio(row) {
            return 100;
        },
        exportxls() {
            this.$message("导出EXCEL报表");
        }
    }
});

/***/ }),

/***/ 737:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-23f5ab62]{margin-bottom:20px}.handle-select[data-v-23f5ab62]{width:120px}.handle-input[data-v-23f5ab62]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 779:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('el-autocomplete', {
    staticClass: "inline-input",
    attrs: {
      "fetch-suggestions": _vm.querySearch,
      "placeholder": "原料编码",
      "trigger-on-focus": false
    },
    on: {
      "select": _vm.handleSelect
    },
    model: {
      value: (_vm.query.materialCode),
      callback: function($$v) {
        _vm.$set(_vm.query, "materialCode", $$v)
      },
      expression: "query.materialCode"
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
    staticStyle: {
      "width": "100%"
    },
    attrs: {
      "data": _vm.data,
      "border": ""
    },
    on: {
      "expand": _vm.expand
    }
  }, [_c('el-table-column', {
    attrs: {
      "type": "expand"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(props) {
        return [_c('el-table', {
          attrs: {
            "data": props.row.fenku,
            "show-header": "false"
          }
        }, [_c('el-table-column', {
          attrs: {
            "prop": "cabinCode",
            "label": "仓库编码",
            "width": "100"
          }
        }), _vm._v(" "), _c('el-table-column', {
          attrs: {
            "prop": "cabinName",
            "label": "仓库名称",
            "width": "120"
          }
        }), _vm._v(" "), _c('el-table-column', {
          attrs: {
            "prop": "currStock",
            "label": "当前库存",
            "width": "120"
          }
        }), _vm._v(" "), _c('el-table-column', {
          attrs: {
            "prop": "usedStock",
            "label": "已用数量",
            "width": "120"
          }
        })], 1)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "220"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "currStock",
      "label": "当前库存(总)",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "usedStock",
      "label": "已使用量(总)",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "利用率(%)",
      "width": "120",
      "formatter": _vm.getUtilizationRatio
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "stockUnit",
      "label": "库存单位",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "modifier",
      "label": "修改人",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "100"
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
              _vm.exportxls(scope.$index, scope.row)
            }
          }
        }, [_vm._v("导出报表")])]
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

/***/ 817:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(737);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(265)("e24d6b56", content, true);

/***/ })

});