webpackJsonp([25],{

/***/ 625:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(756)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(687),
  /* template */
  __webpack_require__(728),
  /* scopeId */
  "data-v-0c8bf1aa",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 687:
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
//
//
//
//
//


/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            pageNo: 1,
            pageSize: 5,
            totalRows: 0,
            loadingState: false,
            queryCond: {
                materialCode: ''
            },
            queryList: []
        };
    },
    created() {},
    mounted() {
        this.queryData();
    },
    activated() {},
    computed: {},
    methods: {
        handleCurrentChange(val) {
            this.pageNo = val;
            this.queryData();
        },
        queryData() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus_js__["a" /* api */].querySupplierPage({
                pageNo: this.pageNo,
                pageSize: this.pageSize
            }).then(page => {
                this.totalRows = page.totalRows;
                this.queryList = page.values;
            });
        },
        search() {
            this.queryList = [];
            this.queryData();
        },
        querySearch(queryString, cb) {
            var data = [];
            data.push({ id: 1, value: 'aaaaa' });
            data.push({ id: 2, value: 'bbbbb' });
            data.push({ id: 3, value: 'ccccc' });
            console.log(this.$data.query);
            cb(data);
        },
        edit(index, item) {
            this.$router.push({ path: "/supplierManagePage", query: { supplierCode: item && item.supplierCode } });
        },
        expand(row, expanded) {},
        handleSelect() {}
    }
});

/***/ }),

/***/ 701:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-0c8bf1aa]{margin-bottom:20px}.handle-select[data-v-0c8bf1aa]{width:120px}.handle-input[data-v-0c8bf1aa]{width:300px;display:inline-block}.el-row[data-v-0c8bf1aa]{margin-bottom:4px;&:last-child{margin-bottom:0}}", ""]);

// exports


/***/ }),

/***/ 728:
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
      "placeholder": "供应商",
      "trigger-on-focus": false
    },
    on: {
      "select": _vm.handleSelect
    },
    model: {
      value: (_vm.queryCond.materialCode),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "materialCode", $$v)
      },
      expression: "queryCond.materialCode"
    }
  }), _vm._v(" "), _c('el-button', {
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
    },
    on: {
      "expand": _vm.expand
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
      "width": "100"
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

/***/ 756:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(701);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("76f3cd70", content, true);

/***/ })

});