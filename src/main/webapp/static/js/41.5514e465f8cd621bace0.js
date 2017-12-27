webpackJsonp([41],{

/***/ 1024:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "container"
  }, [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("盘点库存")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v(_vm._s(_vm.cabin.name))])], 1)], 1), _vm._v(" "), _c('div', [_c('el-button', {
    attrs: {
      "type": "primary",
      "size": "small"
    },
    on: {
      "click": _vm.switchStatus
    }
  }, [_vm._v(_vm._s(_vm.btnText))])], 1), _vm._v(" "), _c('el-row', [_c('el-col', {
    staticStyle: {
      "margin-right": "10px"
    },
    attrs: {
      "span": 13
    }
  }, [_c('div', {
    staticStyle: {
      "text-align": "center"
    }
  }, [_c('h4', [_vm._v("待盘点")])]), _vm._v(" "), _c('div', {
    staticClass: "waitingList"
  }, [_c('div', [_c('el-row', {
    staticStyle: {
      "margin-bottom": "10px"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_vm._v("原料名称")]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_vm._v("库存记录")]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_vm._v("盘点数量")]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 6,
      "offset": 1
    }
  }, [_vm._v("操作")])], 1), _vm._v(" "), _c('transition-group', {
    attrs: {
      "name": "list",
      "tag": "p"
    }
  }, _vm._l((_vm.waitingList), function(item, index) {
    return _c('el-row', {
      key: item.id,
      staticClass: "list-item row-item"
    }, [_c('el-col', {
      attrs: {
        "span": 5
      }
    }, [_vm._v(_vm._s(item.materialName))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 5
      }
    }, [_vm._v(_vm._s(item.currStock))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 5
      }
    }, [_c('el-input', {
      attrs: {
        "size": "mini"
      },
      model: {
        value: (item.realStock),
        callback: function($$v) {
          _vm.$set(item, "realStock", $$v)
        },
        expression: "item.realStock"
      }
    }, [_c('template', {
      attrs: {
        "slot": "append"
      },
      slot: "append"
    }, [_c('span', {
      staticStyle: {
        "display": "inline-block",
        "width": "20px"
      }
    }, [_vm._v(_vm._s(item.stockUnit))])])], 2)], 1), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 6,
        "offset": 1
      }
    }, [_c('el-button', {
      attrs: {
        "size": "small"
      },
      on: {
        "click": function($event) {
          _vm.inputdetail(item, index)
        }
      }
    }, [_vm._v("详细")]), _vm._v(" "), _c('el-button', {
      attrs: {
        "size": "small"
      },
      on: {
        "click": function($event) {
          _vm.confirm(item, index)
        }
      }
    }, [_vm._v("确认")])], 1)], 1)
  }))], 1)])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 10
    }
  }, [_c('div', {
    staticStyle: {
      "text-align": "center"
    }
  }, [_c('h4', [_vm._v("已盘点")])]), _vm._v(" "), _c('div', {
    staticClass: "doneList"
  }, [_c('div', [_c('el-row', {
    staticStyle: {
      "margin-bottom": "10px"
    }
  }, [_c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_vm._v("原料名称")]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_vm._v("库存记录")]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_vm._v("盘点数量")]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_vm._v("差额")]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 5
    }
  }, [_vm._v("库存单位")])], 1), _vm._v(" "), _c('transition-group', {
    attrs: {
      "name": "list",
      "tag": "p"
    }
  }, _vm._l((_vm.doneList), function(item, index) {
    return _c('el-row', {
      key: item.id,
      staticClass: "list-item row-item"
    }, [_c('el-col', {
      attrs: {
        "span": 5
      }
    }, [_vm._v(_vm._s(item.materialName))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 5
      }
    }, [_vm._v(_vm._s(item.currStock))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 5
      }
    }, [_vm._v(_vm._s(item.realStock))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 4
      }
    }, [_vm._v(_vm._s(item.realStock - item.currStock))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 5
      }
    }, [_vm._v(_vm._s(item.stockUnit))])], 1)
  }))], 1)])])], 1), _vm._v(" "), _c('el-dialog', {
    staticClass: "dialog",
    attrs: {
      "title": _vm.dialogTitle
    },
    model: {
      value: (_vm.detailInputDialogShow),
      callback: function($$v) {
        _vm.detailInputDialogShow = $$v
      },
      expression: "detailInputDialogShow"
    }
  }, [_c('el-table', {
    attrs: {
      "data": _vm.currentItem.specList,
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "specName",
      "label": "规格名称"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "规格",
      "formatter": _vm.formatSpec
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "specAmt",
      "label": "剩余"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "mini"
          },
          model: {
            value: (scope.row.leftAmt),
            callback: function($$v) {
              _vm.$set(scope.row, "leftAmt", $$v)
            },
            expression: "scope.row.leftAmt"
          }
        }, [_c('template', {
          attrs: {
            "slot": "append"
          },
          slot: "append"
        }, [_c('span', {
          staticStyle: {
            "display": "inline-block",
            "width": "20px"
          }
        }, [_vm._v(_vm._s(scope.row.specUnit))])])], 2)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "折算数量",
      "formatter": _vm.calcStockAmt
    }
  })], 1), _vm._v(" "), _c('div', {
    staticStyle: {
      "text-align": "center",
      "margin-top": "10px"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary",
      "size": "small"
    },
    on: {
      "click": _vm.confirmItem
    }
  }, [_vm._v("确 定")])], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1075:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(972);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("1023638e", content, true);

/***/ }),

/***/ 638:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1075)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(932),
  /* template */
  __webpack_require__(1024),
  /* scopeId */
  "data-v-2be93b99",
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
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_jquery__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            cabin: {},
            waitingList: [],
            doneList: [],
            detailInputDialogShow: false,
            dialogTitle: '',
            currentItem: ''
        };
    },

    methods: {
        switchStatus() {
            if (this.cabin.status == "2") {
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].finishCorrect(this.$route.query.CODE).always(() => {
                    this.getData();
                });
            } else {
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].startCorrect(this.$route.query.CODE).always(() => {
                    this.getData();
                });
            }
        },
        formatSpec(item) {
            return item.transRate + item.stockUnit + "/" + item.specUnit;
        },
        calcStockAmt(item) {
            let stockAmt = item.leftAmt * item.transRate;
            __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(item, 'stockAmt', stockAmt);
            return item.stockAmt + item.stockUnit;
        },
        inputdetail(row, index) {
            this.currentItem = row;
            this.dialogTitle = row.materialName + " 系统库存 " + row.realStock + " " + row.stockUnit;
            this.detailInputDialogShow = true;
            if (!row.specList) {
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].querySpecDetailByMaterialCode(row.materialCode).then(list => {
                    let specList = [];
                    let hadSS = false;
                    //规格
                    list && list.forEach(it => {
                        let s = {};
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(s, 'leftAmt', 0);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(s, 'specCode', it.specCode);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(s, 'specName', it.specName);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(s, 'specUnit', it.specUnit);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(s, 'stockUnit', it.stockUnit);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(s, 'transRate', it.transRate);
                        if (it.transRate == 1 && it.stockUnit == it.specUnit) {
                            hadSS = true;
                        }
                        specList.push(s);
                    });
                    //本身
                    if (!hadSS) {
                        let ss = {};
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(ss, 'leftAmt', 0);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(ss, 'specCode', row.materialCode);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(ss, 'specName', row.materialName);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(ss, 'specUnit', row.stockUnit);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(ss, 'stockUnit', row.stockUnit);
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(ss, 'transRate', 1);
                        specList.push(ss);
                    }

                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(row, 'specList', specList);
                }).fail(resp => {
                    this.$message.error(resp.message);
                });
            }
        },
        confirmItem() {
            let realStock = 0;
            this.currentItem.specList && this.currentItem.specList.forEach(it => {
                if (it.stockAmt) {
                    realStock = realStock + it.stockAmt;
                }
            });
            this.currentItem.realStock = realStock;
            this.detailInputDialogShow = false;
        },
        confirm(row, index) {
            this.waitingList.splice(index, 1).forEach(item => {
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].correctStock({
                    id: item.id,
                    materialCode: item.materialCode,
                    realStock: item.realStock
                }).then(value => {
                    this.doneList.unshift(item);
                }).fail(resp => {
                    this.$message.error(resp.message);
                });
            });
        },
        getData() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getCabinByCode(this.$route.query.CODE).then(val => {
                this.cabin = val;
            });
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialsStockPage({
                cabinCode: this.$route.query.CODE,
                pageSize: 1000
            }).then(page => {
                this.waitingList = [];
                this.doneList = [];
                page.values.forEach(item => {
                    __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(item, "realStock", item.currStock < 0 ? 0 : item.currStock);
                    if (item.status == '3') {
                        this.doneList.push(item);
                    } else if (item.status == '2') {
                        this.waitingList.push(item);
                    }
                });
            });
        }
    },
    mounted() {
        this.getData();
        let $this = this;
        __WEBPACK_IMPORTED_MODULE_2_jquery___default()(".container").on("mouseover", ".row-item", function (i) {
            __WEBPACK_IMPORTED_MODULE_2_jquery___default()(i.currentTarget).addClass("selectedRow");
        }).on("mouseout", ".row-item", function (e) {
            __WEBPACK_IMPORTED_MODULE_2_jquery___default()(e.currentTarget).removeClass("selectedRow");
        });
    },
    computed: {
        btnText() {
            if (this.cabin && "2" == this.cabin.status) {
                return "结束盘点";
            } else {
                return "开始盘点";
            }
        }
    }
});

/***/ }),

/***/ 972:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".container[data-v-2be93b99],.el-col[data-v-2be93b99],.el-row[data-v-2be93b99]{height:100%}.doneList[data-v-2be93b99],.waitingList[data-v-2be93b99]{border:1px solid;width:100%;height:100%;display:inline-block;overflow-y:scroll;overflow-x:hidden}.selectedRow[data-v-2be93b99]{background:#eaeaea}.list-item[data-v-2be93b99]{margin-right:10px}.list-enter-active[data-v-2be93b99],.list-leave-active[data-v-2be93b99]{transition:all .5s}.list-enter[data-v-2be93b99],.list-leave-to[data-v-2be93b99]{opacity:0;transform:translateX(30px)}", ""]);

// exports


/***/ })

});