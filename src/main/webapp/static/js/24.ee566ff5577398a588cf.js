webpackJsonp([24],{

/***/ 654:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(839)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(734),
  /* template */
  __webpack_require__(797),
  /* scopeId */
  "data-v-40ccdf9f",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 734:
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


/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            loadingState: false,
            queryList: []
        };
    },
    methods: {
        queryData() {
            let self = this;
            self.queryList = [];
            self.$data.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getAllWarehouseList().then(list => {
                self.queryList = list;
            }).fail(function (resp) {
                self.$message.error("请求出错");
            }).always(function (resp) {
                self.$data.loadingState = false;
            });
        },
        edit(index, item) {
            this.$router.push({ path: "/warehouseManagePage", query: { warehouseCode: item && item.warehouseCode } });
        }
    },
    mounted() {
        this.queryData();
    }
});

/***/ }),

/***/ 751:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-40ccdf9f]{margin-bottom:10px}.handle-select[data-v-40ccdf9f]{width:120px}.handle-input[data-v-40ccdf9f]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 797:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('el-button', {
    attrs: {
      "round": ""
    },
    on: {
      "click": function($event) {
        _vm.queryData()
      }
    }
  }, [_vm._v("刷新列表")]), _vm._v(" "), _c('div', {
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
  }, [_vm._v("增加仓库")])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "warehouseCode",
      "label": "仓库编码",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "warehouseName",
      "label": "仓库名称",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "warehouseManager",
      "label": "负责人",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "warehouseAddr",
      "label": "仓库地址",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "managerPhone",
      "label": "负责人手机",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "managerEmail",
      "label": "负责人邮箱",
      "width": "250"
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
              _vm.edit(scope.$index, scope.row)
            }
          }
        }, [_vm._v("编辑")])]
      }
    }])
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 839:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(751);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("064ab776", content, true);

/***/ })

});