webpackJsonp([49],{

/***/ 1051:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('div', {
    staticStyle: {
      "position": "relative",
      "float": "left"
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "180px"
    },
    attrs: {
      "placeholder": "供应商名称"
    },
    model: {
      value: (_vm.queryCond.supplierName),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "supplierName", $$v)
      },
      expression: "queryCond.supplierName"
    }
  })], 1), _vm._v(" "), _c('el-select', {
    staticStyle: {
      "width": "120px"
    },
    attrs: {
      "clearable": "",
      "placeholder": "有效性"
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
      "label": "有效",
      "value": "1"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "无效",
      "value": "2"
    }
  })], 1), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索")]), _vm._v(" "), _c('div', {
    staticStyle: {
      "position": "relative",
      "float": "right"
    }
  }, [_c('el-button', {
    attrs: {
      "round": ""
    },
    on: {
      "click": function($event) {
        _vm.edit()
      }
    }
  }, [_vm._v("添加供应商")])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "data": _vm.queryList,
      "border": "",
      "element-loading-text": "拼命加载中",
      "element-loading-spinner": "el-icon-loading",
      "element-loading-background": "rgb(0, 0, 0, 0.8)"
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "supplierCode",
      "label": "供应商编码",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierName",
      "label": "供应商名称",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierFullName",
      "label": "供应商全称",
      "width": "300"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierTel",
      "label": "电话",
      "width": "130"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierPhone",
      "label": "手机",
      "width": "130"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierEmail",
      "label": "邮箱",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "status",
      "label": "状态",
      "width": "100",
      "formatter": _vm.formatStatus
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierAddr",
      "label": "地址",
      "width": "300"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "120"
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
              _vm.edit(scope.$index, scope.row)
            }
          }
        }, [_vm._v("编辑")])]
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

/***/ 1118:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(988);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("76f3cd70", content, true);

/***/ }),

/***/ 661:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1118)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(967),
  /* template */
  __webpack_require__(1051),
  /* scopeId */
  "data-v-0c8bf1aa",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 967:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus_js__ = __webpack_require__(263);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            pageNo: 1,
            pageSize: 10,
            totalRows: 0,
            loadingState: false,
            queryCond: {
                supplierCode: '',
                supplierName: '',
                status: '1'
            },
            queryList: []
        };
    },
    mounted() {
        this.queryData();
    },
    methods: {
        handleCurrentChange(val) {
            this.pageNo = val;
            this.queryData();
        },
        queryData() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus_js__["a" /* api */].querySupplierPage({
                pageNo: this.pageNo,
                pageSize: this.pageSize,
                supplierCode: this.queryCond.supplierCode,
                status: this.queryCond.status,
                supplierName: this.queryCond.supplierName
            }).then(page => {
                this.totalRows = page.totalRows;
                this.queryList = page.values;
            });
        },
        search() {
            this.queryList = [];
            this.queryData();
        },
        edit(index, item) {
            this.$router.push({ path: "/supplierManagePage", query: { supplierCode: item && item.supplierCode } });
        },
        formatStatus(row) {
            switch (row.status) {
                case "1":
                    return '有效';
                case "2":
                    return '无效';
                default:
                    return '未知';
            }
        }
    }
});

/***/ }),

/***/ 988:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-0c8bf1aa]{margin-bottom:20px}.handle-select[data-v-0c8bf1aa]{width:120px}.handle-input[data-v-0c8bf1aa]{width:300px;display:inline-block}.el-row[data-v-0c8bf1aa]{margin-bottom:4px;&:last-child{margin-bottom:0}}", ""]);

// exports


/***/ })

});