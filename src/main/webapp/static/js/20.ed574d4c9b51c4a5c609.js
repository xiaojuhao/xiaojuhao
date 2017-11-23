webpackJsonp([20],{

/***/ 639:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(786)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(707),
  /* template */
  __webpack_require__(754),
  /* scopeId */
  "data-v-36b93086",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 707:
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


/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            loadingState: false,
            queryList: [],
            allWarehouse: []
        };
    },
    methods: {
        queryData() {
            this.queryList = [];
            this.totalRows = 0;
            let self = this;
            self.$data.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getAllStoreList().then(values => {
                self.queryList = values;
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                self.$data.loadingState = false;
            });
        },
        edit(index, item) {
            this.$router.push({ path: "/storeManagePage", query: { storeCode: item && item.storeCode } });
        },
        formatWarehouse(item) {
            let wh = this.warehouseMap[item.defaultWarehouse];
            return wh && wh.warehouseName;
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getAllWarehouseList().then(list => {
            this.$data.allWarehouse = list;
        });
        this.queryData();
    },
    computed: {
        warehouseMap() {
            let map = {};
            this.$data.allWarehouse.forEach(item => {
                map[item.warehouseCode] = item;
            });
            return map;
        }
    }
});

/***/ }),

/***/ 718:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-36b93086]{margin-bottom:20px}.handle-select[data-v-36b93086]{width:120px}.handle-input[data-v-36b93086]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 754:
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
  }, [_vm._v("刷新数据")]), _vm._v(" "), _c('div', {
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
  }, [_vm._v("增加新门店")])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "storeCode",
      "label": "门店编码",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "storeName",
      "label": "门店名称",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "outCode",
      "label": "外部系统编码",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "storeManager",
      "label": "负责人",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "storeAddr",
      "label": "门店地址",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "managerPhone",
      "label": "负责人手机",
      "width": "130"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "managerEmail",
      "label": "负责人邮箱",
      "width": "220"
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

/***/ 786:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(718);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("2e18af7c", content, true);

/***/ })

});