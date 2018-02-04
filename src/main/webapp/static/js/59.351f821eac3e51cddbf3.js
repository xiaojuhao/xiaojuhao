webpackJsonp([59],{

/***/ 1003:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".pop-message[data-v-3f62ffc4]{position:fixed;top:0;width:100%;height:100%;z-index:99998;background:gray}.pop-message-sub[data-v-3f62ffc4]{width:60%;height:75%;margin-left:40px;z-index:99999;background:#fff;border:1px solid}.info-row[data-v-3f62ffc4]{background:red}", ""]);

// exports


/***/ }),

/***/ 1067:
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
      "label": "供应商",
      "width": "130",
      "formatter": _vm.formatFrom
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
      "label": "实际入库",
      "width": "150",
      "formatter": _vm.formatRealAmt
    }
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
    on: {
      "click": _vm.onBack
    }
  }, [_vm._v("取消")])], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1134:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1003);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("44e11746", content, true);

/***/ }),

/***/ 624:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1134)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(932),
  /* template */
  __webpack_require__(1067),
  /* scopeId */
  "data-v-3f62ffc4",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 932:
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
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDate(row.prodDate);
        },
        formatRealAmt(row) {
            return row.realStockAmt + row.stockUnit;
        },
        formatFrom(row) {
            switch (row.applyType) {
                case "purchase":
                    return row.supplierName;
                case "allocation":
                    return row.fromCabinName;
            }
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