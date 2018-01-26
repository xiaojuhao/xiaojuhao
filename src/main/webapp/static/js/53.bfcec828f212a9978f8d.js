webpackJsonp([53],{

/***/ 1053:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_vm._v("\n        报损单状态\n        "), _c('el-select', {
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
  }, [_vm._v("搜索采购单")])], 1), _vm._v(" "), _vm._l((_vm.data), function(item) {
    return _c('el-card', {
      key: item.id,
      staticClass: "card",
      attrs: {
        "body-style": {
          padding: '0px'
        }
      }
    }, [_c('div', {
      staticStyle: {
        "position": "relative",
        "margin": "20px"
      }
    }, [_c('span', [_c('strong', [_vm._v("门店：")]), _vm._v(_vm._s(item.cabinName))]), _vm._v(" "), _c('span', [_c('strong', [_vm._v("原料：")]), _vm._v(_vm._s(item.materialName))]), _vm._v(" "), _c('br'), _vm._v(" "), _c('br'), _vm._v(" "), _c('span', [_c('strong', [_vm._v("报损时间：")]), _vm._v(_vm._s(item.createTime))]), _vm._v(" "), _c('br'), _vm._v(" "), _c('br'), _vm._v(" "), _c('span', [_c('strong', [_vm._v("损失：")]), _vm._v(_vm._s(item.stockAmt) + _vm._s(item.stockUnit))]), _vm._v(" "), _c('span', [_c('strong', [_vm._v("报损人: ")]), _vm._v(_vm._s(item.creatorName))])]), _vm._v(" "), _c('div', {
      staticStyle: {
        "position": "relative"
      }
    }, _vm._l((item.images), function(img) {
      return _c('img', {
        staticClass: "image",
        attrs: {
          "src": _vm.server + '/file/show?image=' + img
        }
      })
    }))])
  }), _vm._v(" "), _c('div', [_c('el-button', {
    attrs: {
      "type": "primary",
      "disabled": _vm.isDisabled
    },
    on: {
      "click": _vm.getData
    }
  }, [_vm._v("加载更多")])], 1)], 2)
},staticRenderFns: []}

/***/ }),

/***/ 1120:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(990);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("1eae793c", content, true);

/***/ }),

/***/ 636:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1120)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(945),
  /* template */
  __webpack_require__(1053),
  /* scopeId */
  "data-v-16245336",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 945:
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


/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            tableData: [],
            pageSize: 10,
            pageNo: 1,
            server: __WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* config */].server,
            query: {
                status: ''
            },
            isDisabled: false
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
        getData() {
            let param = {
                pageNo: this.pageNo,
                pageSize: this.pageSize
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMyLossApplyDetail(param).then(list => {
                if (!list || !list.length) {
                    this.isDisabled = true;
                } else {
                    list.forEach(item => this.tableData.push(item));
                    this.pageNo = this.pageNo + 1;
                }
            });
        },
        search() {
            this.tableData = [];
            this.pageNo = 1;
            this.isDisabled = false;
            this.getData();
        }
    }
});

/***/ }),

/***/ 990:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-16245336]{margin-bottom:20px}.image[data-v-16245336]{width:130px;height:100px;margin:10px}.card[data-v-16245336]{width:50%;margin:10px}", ""]);

// exports


/***/ })

});