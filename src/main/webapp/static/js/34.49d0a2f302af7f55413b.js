webpackJsonp([34],{

/***/ 1001:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".pop-message[data-v-c7af30d0]{position:fixed;top:0;width:100%;height:100%;z-index:99998;background:gray}.pop-message-sub[data-v-c7af30d0]{width:60%;height:75%;margin-left:40px;z-index:99999;background:#fff;border:1px solid}.info-row[data-v-c7af30d0]{background:red}", ""]);

// exports


/***/ }),

/***/ 1060:
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
      "border": "",
      "row-style": _vm.rowStyle
    }
  }, [_c('el-table-column', {
    attrs: {
      "width": "55"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-checkbox', {
          attrs: {
            "checked": scope.row.isSelected,
            "disabled": scope.row.status != '1'
          },
          on: {
            "change": function($event) {
              _vm.doCheck(this, scope.row)
            }
          },
          model: {
            value: (scope.row.isSelected),
            callback: function($$v) {
              _vm.$set(scope.row, "isSelected", $$v)
            },
            expression: "scope.row.isSelected"
          }
        })]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "150"
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
      "label": "状态",
      "width": "130",
      "formatter": _vm.formatStatus
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
      "data": _vm.selectItems,
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

/***/ 1106:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1001);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("2ec458c0", content, true);

/***/ }),

/***/ 656:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1106)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(951),
  /* template */
  __webpack_require__(1060),
  /* scopeId */
  "data-v-c7af30d0",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 951:
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
//
//
//
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
            isShowMessage: false,
            selectItems: []

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
        formatStatus(row) {
            switch (row.status) {
                case "0":
                    return "待提交";
                case "1":
                    return "待入库";
                case "2":
                    return "已入库";
            }
        },
        onSubmit() {
            this.isShowMessage = false;
            this.loadingState = true;
            let param = {
                dataJson: JSON.stringify(this.selectItems),
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
        determineShow(row) {
            console.log("determineShow", row);
            return true;
        },
        onBack() {
            this.$router.go(-1);
        },
        showMessage() {
            if (this.isShowMessage) this.isShowMessage = false;else this.isShowMessage = true;
        },
        cancelMsg() {
            this.isShowMessage = false;
        },
        rowStyle(row) {
            if (row.isSelected == true) return 'background:#E0E0E0';
        },
        doCheck() {
            this.selectItems = [];
            this.details.forEach(it => {
                if (it.isSelected) this.selectItems.push(it);
            });
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryInventoryApplyDetailByApplyNum(this.applyNum).then(list => {
            this.details = list;
            list.sort((a, b) => {
                if (a.status == '1') {
                    return 0;
                } else {
                    return 1;
                }
            });
            list.forEach(it => {
                __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(it, 'isSelected', false);
            });
        });
    }
});

/***/ })

});