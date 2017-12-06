webpackJsonp([41],{

/***/ 621:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(847)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(701),
  /* template */
  __webpack_require__(804),
  /* scopeId */
  "data-v-48ba7c10",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 701:
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
//


/* harmony default export */ __webpack_exports__["default"] = ({
    data: function () {
        return {
            applyNum: this.$route.query.applyNum,
            details: [],
            loadingState: false,
            splitMaterials: [],
            isShowMessage: false

        };
    },
    methods: {
        formatStockAmt(row) {
            return row.stockAmt + row.stockUnit;
        },
        formatPurchaseAmt(row) {
            return row.stockAmt + row.stockUnit;
        },
        formatCreateDate(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* util */].formatDate(row.gmtCreated);
        },
        onSubmit() {
            this.isShowMessage = false;
            this.loadingState = true;
            let param = {
                dataJson: JSON.stringify(this.details),
                applyNum: this.applyNum
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].confirmDiaobo(param).then(resp => {
                this.$message.success("操作成功");
                this.$router.go(-1);
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.loadingState = false;
            });
        },
        onBack() {
            this.$router.go(-1);
        },
        showMessage() {
            if (this.isShowMessage) this.isShowMessage = false;else this.isShowMessage = true;
        },
        cancelMsg() {
            this.isShowMessage = false;
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryInventoryApplyByApplyNum(this.applyNum).then(list => {
            this.details = list;
        });
    }
});

/***/ }),

/***/ 757:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".pop-message[data-v-48ba7c10]{position:fixed;top:80px;width:100%;height:100%;z-index:99998}.pop-message-sub[data-v-48ba7c10]{width:60%;height:75%;margin-left:40px;z-index:99999;background:#fff;border:1px solid}.info-row[data-v-48ba7c10]{background:red}", ""]);

// exports


/***/ }),

/***/ 804:
/***/ (function(module, exports) {

module.exports={render:function (){
var this$1 = this;
var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticStyle: {
      "position": "absolute"
    }
  }, [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("入库")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("单号" + _vm._s(_vm.applyNum))])], 1)], 1), _vm._v(" "), _c('el-table', {
    staticStyle: {
      "width": "150%"
    },
    attrs: {
      "data": _vm.details,
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "拨入单位",
      "width": "130"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "fromCabinName",
      "label": "拨出单位",
      "width": "130"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "拨出数量",
      "width": "100",
      "formatter": _vm.formatStockAmt
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "实际入库数量",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.realStockAmt),
            callback: function($$v) {
              _vm.$set(scope.row, "realStockAmt", $$v)
            },
            expression: "scope.row.realStockAmt"
          }
        })]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作日期",
      "width": "130",
      "formatter": _vm.formatCreateDate
    }
  })], 1), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-top": "10px",
      "margin-left": "300px"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.showMessage
    }
  }, [_vm._v("确认入库")]), _vm._v(" "), _c('span', {
    staticStyle: {
      "margin-right": "20px"
    }
  }), _vm._v(" "), _c('el-button', {
    on: {
      "click": _vm.onBack
    }
  }, [_vm._v("取消")])], 1)], 1), _vm._v(" "), _c('el-dialog', {
    attrs: {
      "visible": _vm.isShowMessage,
      "title": "确认入库信息"
    },
    on: {
      "update:visible": function($event) {
        _vm.isShowMessage = $event
      }
    }
  }, [_c('el-table', {
    staticStyle: {
      "width": "100%"
    },
    attrs: {
      "data": _vm.details,
      "max-height": "400",
      "row-class-name": "info-row"
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "拨出数量",
      "width": "",
      "formatter": _vm.formatStockAmt
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "realStockAmt",
      "label": "实际入库数量",
      "width": ""
    }
  })], 1), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-top": "10px",
      "margin-left": "300px"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("确认提交")]), _vm._v(" "), _c('span', {
    staticStyle: {
      "margin-right": "20px"
    }
  }), _vm._v(" "), _c('el-button', {
    on: {
      "click": function () {
        this$1.isShowMessage = false
      }
    }
  }, [_vm._v("取消")])], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 847:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(757);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("c46da208", content, true);

/***/ })

});