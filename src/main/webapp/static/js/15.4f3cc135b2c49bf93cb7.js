webpackJsonp([15],{

/***/ 1027:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(917),
  /* template */
  __webpack_require__(1034),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 1032:
/***/ (function(module, exports) {

module.exports={render:function (){
var this$1 = this;
var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('div', {
    staticStyle: {
      "position": "relative",
      "float": "left"
    }
  }, [_c('SupplierSelect', {
    on: {
      "input": function (v) {
        this$1.queryCond.supplierCode = v;
      }
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

/***/ 1034:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "供应商",
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

/***/ 1094:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(976);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("76f3cd70", content, true);

/***/ }),

/***/ 658:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1094)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(958),
  /* template */
  __webpack_require__(1032),
  /* scopeId */
  "data-v-0c8bf1aa",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 917:
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
            __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].querySupplierPage({
                pageSize: 2000
            }).then(page => {
                page.values.forEach(item => {
                    let ii = {};
                    ii.code = item.supplierCode;
                    ii.name = item.supplierName;
                    ii.type = "供应商";
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

/***/ 958:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus_js__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_SupplierSelect__ = __webpack_require__(1027);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_SupplierSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_SupplierSelect__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
        SupplierSelect: __WEBPACK_IMPORTED_MODULE_1__common_SupplierSelect___default.a
    },
    data() {
        return {
            pageNo: 1,
            pageSize: 10,
            totalRows: 0,
            loadingState: false,
            queryCond: {
                supplierCode: '',
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
                status: this.queryCond.status
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

/***/ 976:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-0c8bf1aa]{margin-bottom:20px}.handle-select[data-v-0c8bf1aa]{width:120px}.handle-input[data-v-0c8bf1aa]{width:300px;display:inline-block}.el-row[data-v-0c8bf1aa]{margin-bottom:4px;&:last-child{margin-bottom:0}}", ""]);

// exports


/***/ })

});