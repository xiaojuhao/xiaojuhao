webpackJsonp([50],{

/***/ 1058:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingShow),
      expression: "loadingShow"
    }],
    staticClass: "container",
    attrs: {
      "element-loading-text": "正在处理....."
    }
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
      "type": _vm.btnType,
      "size": "small"
    },
    on: {
      "click": _vm.btnClick
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
    }, [_vm._v(_vm._s(item.oriStockAmt) + " " + _vm._s(item.stockUnit))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 5
      }
    }, [_c('el-input', {
      attrs: {
        "size": "mini"
      },
      model: {
        value: (item.stockAmt),
        callback: function($$v) {
          _vm.$set(item, "stockAmt", $$v)
        },
        expression: "item.stockAmt"
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
    }, [_vm._v(_vm._s(item.oriStockAmt))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 5
      }
    }, [_vm._v(_vm._s(item.stockAmt))]), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 4
      }
    }, [_vm._v(_vm._s(item.stockAmt - item.oriStockAmt))]), _vm._v(" "), _c('el-col', {
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
      "label": "规格名称",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "规格",
      "formatter": _vm.formatSpec,
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "利用率",
      "width": "80"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.utilizationRatio) + "%\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "specAmt",
      "label": "剩余",
      "width": "140"
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
      "formatter": _vm.calcStockAmt,
      "width": "180"
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

/***/ 1122:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(996);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("1023638e", content, true);

/***/ }),

/***/ 641:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1122)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(948),
  /* template */
  __webpack_require__(1058),
  /* scopeId */
  "data-v-2be93b99",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 948:
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
//
//
//
//
//




/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            cabin: {},
            checkMain: {},
            waitingList: [],
            doneList: [],
            detailInputDialogShow: false,
            loadingShow: false,
            dialogTitle: '',
            currentItem: ''
        };
    },

    methods: {
        btnClick() {
            if (this.checkMain.status == '1') {
                this.finishCheck();
            } else {
                this.startCheck();
            }
        },
        formatSpec(item) {
            return item.transRate + item.stockUnit + "/" + item.specUnit;
        },
        calcStockAmt(item) {
            let stockAmt = item.leftAmt * item.transRate * item.utilizationRatio / 100;
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
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(s, 'utilizationRatio', it.utilizationRatio);
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
                        __WEBPACK_IMPORTED_MODULE_1_vue___default.a.set(ss, 'utilizationRatio', 100);
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
                    realStock += it.stockAmt;
                }
            });
            this.currentItem.stockAmt = realStock;
            this.detailInputDialogShow = false;
        },
        confirm(row, index) {
            this.loadingShow = true;
            this.waitingList.splice(index, 1).forEach(item => {
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].doCheckStock({
                    id: item.id,
                    mainId: item.mainId,
                    cabinCode: item.cabinCode,
                    materialCode: item.materialCode,
                    stockAmt: item.stockAmt,
                    detail: JSON.stringify(item.specList)
                }).then(value => {
                    //将盘点好的记录添加到已盘点队列中
                    this.doneList.unshift(item);
                }).fail(resp => {
                    this.$message.error(resp.message);
                }).always(() => {
                    this.loadingShow = false;
                });
            });
        },
        queryCheckDetail() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryCheckDetail(this.checkMain.id, this.checkMain.cabinCode).then(list => {
                list && list.forEach(it => {
                    if (it.status == 0) {
                        this.waitingList.push(it);
                    } else {
                        this.doneList.push(it);
                    }
                });
            });
        },
        startCheck() {
            this.loadingShow = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].startCheck(this.cabin.code).then(val => {
                this.checkMain = val;
                this.queryCheckDetail();
            }).always(() => {
                this.loadingShow = false;
            });
        },
        finishCheck() {
            this.loadingShow = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].finishCheck(this.checkMain.id, this.checkMain.cabinCode).then(val => {
                this.checkMain = {};
                this.waitingList = [];
                this.doneList = [];
            }).always(() => {
                this.loadingShow = false;
            });
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getCabinByCode(this.$route.query.CODE).then(val => {
            this.cabin = val;
        });
        //查询当前是否有正在盘点的任务
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].currentStockCheck(this.$route.query.CODE).then(main => {
            this.checkMain = main;
            this.queryCheckDetail();
        });
        //给列表加上高亮事件
        __WEBPACK_IMPORTED_MODULE_2_jquery___default()(".container").on("mouseover", ".row-item", function (i) {
            __WEBPACK_IMPORTED_MODULE_2_jquery___default()(i.currentTarget).addClass("selectedRow");
        }).on("mouseout", ".row-item", function (e) {
            __WEBPACK_IMPORTED_MODULE_2_jquery___default()(e.currentTarget).removeClass("selectedRow");
        });
    },
    computed: {
        btnText() {
            if (this.checkMain.status == '1') {
                return "结束盘点";
            } else {
                return "开始盘点";
            }
        },
        btnType() {
            if (this.checkMain.status == '1') {
                return 'danger';
            } else {
                return "primary";
            }
        }
    }
});

/***/ }),

/***/ 996:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".container[data-v-2be93b99],.el-col[data-v-2be93b99],.el-row[data-v-2be93b99]{height:100%}.doneList[data-v-2be93b99],.waitingList[data-v-2be93b99]{border:1px solid;width:100%;height:100%;display:inline-block;overflow-y:scroll;overflow-x:hidden}.selectedRow[data-v-2be93b99]{background:#eaeaea}.list-item[data-v-2be93b99]{margin-right:10px}.list-enter-active[data-v-2be93b99],.list-leave-active[data-v-2be93b99]{transition:all .5s}.list-enter[data-v-2be93b99],.list-leave-to[data-v-2be93b99]{opacity:0;transform:translateX(30px)}", ""]);

// exports


/***/ })

});