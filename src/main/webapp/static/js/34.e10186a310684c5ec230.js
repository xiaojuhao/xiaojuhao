webpackJsonp([34],{

/***/ 1000:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".pop-message[data-v-c7af30d0]{position:fixed;top:0;width:100%;height:100%;z-index:99998;background:gray}.pop-message-sub[data-v-c7af30d0]{width:60%;height:75%;margin-left:40px;z-index:99999;background:#fff;border:1px solid}.info-row[data-v-c7af30d0]{background:red}", ""]);

// exports


/***/ }),

/***/ 1057:
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
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "supplierName",
      "label": "供应商",
      "width": "130"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "门店/仓库",
      "width": "130"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购规格",
      "width": "100",
      "formatter": _vm.formatSpec
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购数量",
      "width": "100",
      "formatter": _vm.formatPurchaseAmt
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "实际数量",
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
      "prop": "specPrice",
      "label": "采购价",
      "width": "80"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "totalPrice",
      "label": "总价",
      "width": "80"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "生产日期",
      "width": "130",
      "formatter": _vm.formateProdDate
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
      "prop": "supplierName",
      "label": "供应商",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "规格数量",
      "width": "",
      "formatter": _vm.formatSpec
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "realStockAmt",
      "label": "接收数量",
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

/***/ 1103:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1000);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("2ec458c0", content, true);

/***/ }),

/***/ 656:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1103)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(950),
  /* template */
  __webpack_require__(1057),
  /* scopeId */
  "data-v-c7af30d0",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 950:
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
        formatSpec(row) {
            if (row.specUnit != '无') {
                return row.specAmt + row.specUnit;
            } else {
                return row.stockAmt + row.stockUnit;
            }
        },
        formatPurchaseAmt(row) {
            return row.stockAmt + row.stockUnit;
        },
        formateProdDate(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* util */].formatDate(row.prodDate);
        },
        onSubmit() {
            this.isShowMessage = false;
            this.loadingState = true;
            let param = {
                dataJson: JSON.stringify(this.details),
                applyNum: this.applyNum
            };
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].confirmInventory(param).then(resp => {
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
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryInventoryApplyDetailByApplyNum(this.applyNum).then(list => {
            this.details = list;
        });
    }
});

/***/ })

});