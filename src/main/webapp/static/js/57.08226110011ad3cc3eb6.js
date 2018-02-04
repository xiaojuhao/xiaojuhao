webpackJsonp([57],{

/***/ 1000:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".pop-message[data-v-2e5b4b2e]{position:fixed;top:0;width:100%;height:100%;z-index:99998;background:gray}.pop-message-sub[data-v-2e5b4b2e]{width:60%;height:75%;margin-left:40px;z-index:99999;background:#fff;border:1px solid}.info-row[data-v-2e5b4b2e]{background:red}", ""]);

// exports


/***/ }),

/***/ 1064:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
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
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("采购单支付")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v(_vm._s(_vm.applyNum))])], 1)], 1), _vm._v(" "), _c('el-form', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState),
      expression: "loadingState"
    }],
    ref: "form",
    attrs: {
      "label-width": "90px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "门店/仓库"
    }
  }, [_vm._v("\n                " + _vm._s(_vm.apply.cabinName) + "\n            ")]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "供应商"
    }
  }, [_vm._v("\n                " + _vm._s(_vm.supplier.supplierName) + "\n            ")]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "应付金额"
    }
  }, [_vm._v("\n                " + _vm._s(_vm.apply.payables) + "元\n            ")]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "实付金额"
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "150px"
    },
    model: {
      value: (_vm.apply.paidAmt),
      callback: function($$v) {
        _vm.$set(_vm.apply, "paidAmt", $$v)
      },
      expression: "apply.paidAmt"
    }
  }, [_c('template', {
    attrs: {
      "slot": "append"
    },
    slot: "append"
  }, [_vm._v("元")])], 2)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "支付方式"
    }
  }, [_c('el-radio-group', {
    attrs: {
      "disabled": ""
    },
    model: {
      value: (_vm.apply.paidWay),
      callback: function($$v) {
        _vm.$set(_vm.apply, "paidWay", $$v)
      },
      expression: "apply.paidWay"
    }
  }, [_c('el-radio', {
    attrs: {
      "label": "alipay"
    }
  }, [_vm._v("支付宝")]), _vm._v(" "), _c('el-radio', {
    attrs: {
      "label": "weixin"
    }
  }, [_vm._v("微信")]), _vm._v(" "), _c('el-radio', {
    attrs: {
      "label": "bank"
    }
  }, [_vm._v("银行")])], 1)], 1), _vm._v(" "), _c('el-form-item', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (_vm.apply.paidWay == 'bank'),
      expression: "apply.paidWay == 'bank'"
    }],
    attrs: {
      "label": "银行名称"
    }
  }, [_vm._v("\n                " + _vm._s(_vm.apply.paytoBank) + "\n            ")]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "收款账户"
    }
  }, [_vm._v("\n                " + _vm._s(_vm.apply.paytoAccount) + "\n            ")]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "收款人"
    }
  }, [_vm._v("\n                " + _vm._s(_vm.apply.paytoAccountName) + "\n            ")]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "备注"
    }
  }, [_c('el-input', {
    model: {
      value: (_vm.apply.paidRemark),
      callback: function($$v) {
        _vm.$set(_vm.apply, "paidRemark", $$v)
      },
      expression: "apply.paidRemark"
    }
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-top": "10px",
      "margin-left": "300px"
    }
  }, [_c('el-button', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (_vm.apply.paidStatus == '0'),
      expression: "apply.paidStatus == '0'"
    }],
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.comfirmPay
    }
  }, [_vm._v("确认支付")]), _vm._v(" "), _c('span', {
    staticStyle: {
      "margin-right": "20px"
    }
  }), _vm._v(" "), _c('el-button', {
    on: {
      "click": _vm.onBack
    }
  }, [_vm._v("返回")])], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1131:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1000);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("77335106", content, true);

/***/ }),

/***/ 627:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1131)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(935),
  /* template */
  __webpack_require__(1064),
  /* scopeId */
  "data-v-2e5b4b2e",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 935:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_vue__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            apply: {},
            applyDetails: [],
            supplier: {},
            loadingState: false
        };
    },
    methods: {
        initSupplier() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].querySupplierByCode(this.apply.supplierCode).then(sp => {
                this.supplier = sp;
                __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(this.apply, 'paidWay', sp.payWay);
                if (sp.payWay == 'bank') {
                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(this.apply, 'paytoBank', sp.bankName);
                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(this.apply, 'paytoAccount', sp.bankAccount);
                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(this.apply, 'paytoAccountName', sp.bankAccountName);
                }
                if (sp.payWay == 'alipay') {
                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(this.apply, 'paytoAccount', sp.alipayAccount);
                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(this.apply, 'paytoAccountName', sp.alipayAccountName);
                }
                if (sp.payWay == 'weixin') {
                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(this.apply, 'paytoAccount', sp.weixinAccount);
                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(this.apply, 'paytoAccountName', sp.weixinAccountName);
                }
                //默认设置支付金额为应付款额
                this.apply.paidAmt = this.apply.payables;
            }).fail(resp => {
                this.$message.error(resp.message);
            });
        },
        comfirmPay() {
            let self = this;
            this.$confirm('是否确认提交支付信息?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.paidInventory();
            }).catch(() => {
                //取消
            });
        },
        paidInventory() {
            this.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].paidInventory(this.apply).then(resp => {
                this.$message.success("操作成功");
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.loadingState = false;
            });
            this.onBack();
        },
        onBack() {
            this.$router.go(-1);
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryInventoryApplyPage({
            applyNum: this.applyNum
        }).then(page => {
            this.apply = page.values[0];
            this.initSupplier();
        }).fail(resp => {
            this.$message.error(resp.message);
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryInventoryApplyDetailByApplyNum(this.applyNum).then(list => {
            this.applyDetails = list;
        });
    }
});

/***/ })

});