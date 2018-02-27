webpackJsonp([8],{

/***/ 1023:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-6a0349ca]{margin-bottom:20px}.handle-select[data-v-6a0349ca]{width:120px}.handle-input[data-v-6a0349ca]{width:300px;display:inline-block}.dialog[data-v-6a0349ca]{width:100%}", ""]);

// exports


/***/ }),

/***/ 1092:
/***/ (function(module, exports) {

module.exports={render:function (){
var this$1 = this;
var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState2),
      expression: "loadingState2"
    }],
    staticClass: "table",
    attrs: {
      "element-loading-text": "正在同步",
      "element-loading-spinner": "el-icon-loading"
    }
  }, [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("销售订单信息")])], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "handle-box"
  }, [_c('el-input', {
    staticStyle: {
      "width": "150px"
    },
    attrs: {
      "placeholder": "菜单名称或拼音或编码"
    },
    model: {
      value: (_vm.searchKey),
      callback: function($$v) {
        _vm.searchKey = $$v
      },
      expression: "searchKey"
    }
  }), _vm._v(" "), _c('MyStoreSelect', {
    on: {
      "input": function (v) {
        this$1.storeCode = v
      }
    }
  }), _vm._v(" "), _c('el-date-picker', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "type": "date",
      "placeholder": "起始日期"
    },
    model: {
      value: (_vm.saleDateStart),
      callback: function($$v) {
        _vm.saleDateStart = $$v
      },
      expression: "saleDateStart"
    }
  }), _vm._v("\n        -\n        "), _c('el-date-picker', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "type": "date",
      "placeholder": "结束日期"
    },
    model: {
      value: (_vm.saleDateEnd),
      callback: function($$v) {
        _vm.saleDateEnd = $$v
      },
      expression: "saleDateEnd"
    }
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索")]), _vm._v(" "), _c('div', {
    staticStyle: {
      "position": "relative",
      "float": "right"
    }
  }, [_c('el-button', {
    attrs: {
      "round": ""
    },
    on: {
      "click": _vm.syncSaleData
    }
  }, [_vm._v("同步数据")]), _vm._v(" "), _c('el-date-picker', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "type": "date",
      "placeholder": "同步日期"
    },
    model: {
      value: (_vm.syncDate),
      callback: function($$v) {
        _vm.syncDate = $$v
      },
      expression: "syncDate"
    }
  })], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "data": _vm.tableData,
      "border": "",
      "element-loading-text": "拼命加载中",
      "element-loading-spinner": "el-icon-loading",
      "element-loading-background": "rgb(0, 0, 0, 0.8)"
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "storeName",
      "label": "门店",
      "width": "160"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "recipesName",
      "label": "菜品",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "saleDate",
      "label": "销售时间",
      "width": "120",
      "formatter": _vm.formatSaleDate
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "saleNum",
      "label": "销售数量",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "totalPrice",
      "label": "总价",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(scope.row.totalPrice) + " 元\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "remark",
      "label": "备注",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "180"
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
              _vm.showMaterialDetail(scope.row)
            }
          }
        }, [_vm._v("原料详情")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.saleChart(scope.row)
            }
          }
        }, [_vm._v("折线图")])]
      }
    }])
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "pagination"
  }, [_c('el-pagination', {
    attrs: {
      "current-page": _vm.pageNo,
      "layout": "prev, pager, next",
      "total": _vm.totalRows,
      "page-size": _vm.pageSize
    },
    on: {
      "current-change": _vm.handleCurrentChange,
      "update:currentPage": function($event) {
        _vm.pageNo = $event
      }
    }
  })], 1), _vm._v(" "), _c('el-dialog', {
    staticClass: "dialog",
    attrs: {
      "title": _vm.dialogTitle
    },
    model: {
      value: (_vm.dialogOrderMaterialShow),
      callback: function($$v) {
        _vm.dialogOrderMaterialShow = $$v
      },
      expression: "dialogOrderMaterialShow"
    }
  }, [_c('el-table', {
    attrs: {
      "data": _vm.orderMaterials,
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "消耗数量"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.materialTotalAmt) + _vm._s(scope.row.materialUnit) + "\n                ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "remark",
      "label": "备注"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                    " + _vm._s(scope.row.recipesName) + _vm._s(scope.row.saleNum) + "份\n                ")]
      }
    }])
  })], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1155:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1023);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("56e3645e", content, true);

/***/ }),

/***/ 639:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1155)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(949),
  /* template */
  __webpack_require__(1092),
  /* scopeId */
  "data-v-6a0349ca",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 708:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(710),
  /* template */
  __webpack_require__(714),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 709:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//


/* harmony default export */ __webpack_exports__["default"] = ({
    props: ["excludes", "value", "context"],
    data() {
        return {
            allValues: [],
            valuesShow: [],
            selectedCode: ''
        };
    },
    mounted() {
        this.initData();
    },
    watch: {
        value(nval, oval) {
            this.initData();
        }
    },
    methods: {
        setValue() {
            this.$emit("input", this.selectedCode, this.$props.context);
        },
        initData() {
            __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].queryAllRecipes().then(value => {
                this.allValues = value;
                this.selectedCode = this.$props.value;
            });
        },
        filterMethod(input) {
            setTimeout(() => {
                this.valuesShow = this.allValues.filter(item => {
                    var key = [item.recipesCode, item.recipesName, item.searchKey].join(',');
                    return __WEBPACK_IMPORTED_MODULE_0__bus__["c" /* util */].matchSearch(key, input);
                });
            }, 10);
        },
        visualChange(visible) {
            if (visible) {
                this.$data.valuesShow = this.$data.allValues.filter(item => {
                    if (this.excludesMap[item.recipesCode]) {
                        return false;
                    }
                    return true;
                });
            }
        }
    },
    computed: {
        excludesMap() {
            let map = {};
            this.$props.excludes && this.$props.excludes.forEach(item => {
                map[item] = 1;
            });
            return map;
        }
    }
});

/***/ }),

/***/ 710:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//
//
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
  props: ["excludes", "value", "context"],
  data() {
    return {
      allValues: [],
      valuesShow: [],
      selectedCode: ''
    };
  },
  mounted() {
    this.initData();
  },
  watch: {
    value(nval, oval) {
      this.initData();
    }
  },
  methods: {
    setValue() {
      this.$emit("input", this.selectedCode, this.$props.context);
    },
    initData() {
      let $data = this.$data;
      __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].getAllStoreList().then(page => {
        $data.allValues = page.values;
        $data.selectedCode = this.$props.value;
      });
    },
    enterkey(e) {},
    filterMethod(input) {
      let $data = this.$data;
      setTimeout(() => {
        $data.valuesShow = $data.allValues.filter(item => {
          var key = [item.storeCode, item.storeName, item.searchKey].join(',');
          if (key.indexOf(input) >= 0) {
            return true;
          }
          return false;
        });
      }, 10);
    },
    visualChange(visible) {
      if (visible) {
        this.$data.valuesShow = this.$data.allValues.filter(item => {
          if (this.excludesMap[item.storeCode]) {
            return false;
          }
          return true;
        });
      }
    }
  },
  computed: {
    excludesMap() {
      let map = {};
      this.$props.excludes && this.$props.excludes.forEach(item => {
        map[item] = 1;
      });
      return map;
    }
  }
});

/***/ }),

/***/ 712:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(709),
  /* template */
  __webpack_require__(713),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 713:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "选择菜品",
      "filterable": "",
      "clearable": "",
      "filter-method": _vm.filterMethod
    },
    on: {
      "change": _vm.setValue,
      "visible-change": _vm.visualChange
    },
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.valuesShow), function(item) {
    return _c('el-option', {
      key: item.recipesCode,
      attrs: {
        "label": item.recipesName,
        "value": item.recipesCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 714:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "选择门店",
      "filterable": "",
      "clearable": "",
      "filter-method": _vm.filterMethod
    },
    on: {
      "change": _vm.setValue,
      "visible-change": _vm.visualChange
    },
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.valuesShow), function(item) {
    return _c('el-option', {
      key: item.storeCode,
      attrs: {
        "label": item.storeName,
        "value": item.storeCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 787:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(263);
//
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
    props: ["excludes", "value", "context"],
    data() {
        return {
            allValues: [],
            valuesShow: [],
            selectedCode: ''
        };
    },
    mounted() {
        this.initData();
    },
    watch: {
        value(nval, oval) {
            this.initData();
        }
    },
    methods: {
        setValue() {
            this.$emit("input", this.selectedCode, this.$props.context);
        },
        initData() {
            __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].queryMyStores().then(list => {
                list.forEach(item => {
                    let ii = {};
                    ii.code = item.storeCode;
                    ii.name = item.storeName;
                    ii.type = "门店";
                    this.allValues.push(ii);
                });
            });
        },
        filterMethod(input) {
            let $data = this.$data;
            setTimeout(() => {
                $data.valuesShow = $data.allValues.filter(item => {
                    var key = [item.code, item.name, item.searchKey].join(',');
                    if (key.indexOf(input) >= 0) {
                        return true;
                    }
                    return false;
                });
            }, 10);
        },
        visualChange(visible) {
            if (visible) {
                this.$data.valuesShow = this.$data.allValues.filter(item => {
                    if (this.excludesMap[item.code]) {
                        return false;
                    }
                    return true;
                });
            }
        }
    },
    computed: {
        excludesMap() {
            let map = {};
            this.$props.excludes && this.$props.excludes.forEach(item => {
                map[item] = 1;
            });
            return map;
        }
    }
});

/***/ }),

/***/ 879:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(787),
  /* template */
  __webpack_require__(882),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 882:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "选择门店",
      "filterable": "",
      "clearable": "",
      "filter-method": _vm.filterMethod
    },
    on: {
      "change": _vm.setValue,
      "visible-change": _vm.visualChange
    },
    nativeOn: {
      "keyup": function($event) {
        if (!('button' in $event) && _vm._k($event.keyCode, "enter", 13, $event.key)) { return null; }
        _vm.enterkey($event)
      }
    },
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.valuesShow), function(item) {
    return _c('el-option', {
      key: item.code,
      attrs: {
        "label": item.name,
        "value": item.code
      }
    }, [_c('span', {
      staticStyle: {
        "float": "left"
      }
    }, [_vm._v(_vm._s(item.name))]), _vm._v(" "), _c('span', {
      staticStyle: {
        "float": "right",
        "color": "#8492a6",
        "font-size": "13px"
      }
    }, [_vm._v(_vm._s(item.type))])])
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 949:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection__ = __webpack_require__(708);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_StoreSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__ = __webpack_require__(712);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__common_MyStoreSelect__ = __webpack_require__(879);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__common_MyStoreSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3__common_MyStoreSelect__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
    components: {
        StoreSelection: __WEBPACK_IMPORTED_MODULE_1__common_StoreSelection___default.a,
        RecipesSelection: __WEBPACK_IMPORTED_MODULE_2__common_RecipesSelection___default.a,
        MyStoreSelect: __WEBPACK_IMPORTED_MODULE_3__common_MyStoreSelect___default.a
    },
    data() {
        return {
            tableData: [],
            pageNo: 1,
            pageSize: 10,
            totalRows: 0,
            storeCode: '',
            saleDateStart: new Date(),
            saleDateEnd: new Date(),
            syncDate: null,
            recipesCode: '',
            loadingState: false,
            loadingState2: false,
            dialogOrderMaterialShow: false,
            dialogTitle: '',
            orderMaterials: [],
            searchKey: ''
        };
    },
    methods: {
        handleCurrentChange(val) {
            this.pageNo = val;
            this.getData();
        },
        formatSaleDate(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDate(row.saleDate);
        },
        keepParam() {
            let p = {
                pageNo: this.pageNo,
                pageSize: this.pageSize,
                totalRows: this.totalRows,
                recipesCode: this.recipesCode,
                saleDateStart: this.saleDateStart,
                saleDateEnd: this.saleDateEnd,
                searchKey: this.searchKey
            };
            this.$store.commit("setQueryCond", p);
        },
        loadParam() {
            Object.assign(this.$data, this.$store.state.queryCond);
        },
        getData() {
            this.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryWmsOrder({
                pageNo: this.pageNo,
                pageSize: this.pageSize,
                storeCode: this.storeCode,
                recipesCode: this.recipesCode,
                saleDateStart: __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.saleDateStart),
                saleDateEnd: __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.saleDateEnd),
                searchKey: this.searchKey
            }).then(page => {
                this.tableData = page.values;
                this.totalRows = page.totalRows;
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(resp => {
                this.loadingState = false;
            });
        },
        syncSaleData() {
            this.loadingState2 = true;

            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].syncOrders({
                date: __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.syncDate),
                storeCode: this.storeCode
            }).then(val => {
                this.$message("同步成功");
                this.saleDateStart = this.syncDate;
                this.saleDateEnd = this.syncDate;
                this.getData();
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.loadingState2 = false;
            });
        },
        search() {
            this.pageNo = 1;
            this.tableData = [];
            this.getData();
        },
        saleChart(item) {
            this.keepParam();
            this.$router.push({
                path: "/recipesDailyChart",
                query: {
                    code: item.recipesCode,
                    store: item.storeCode
                }
            });
        },
        showMaterialDetail(item) {
            this.dialogOrderMaterialShow = true;
            this.dialogTitle = "原料明细-" + item.storeName + "-" + item.recipesName;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryOrderMaterials({
                orderId: item.id,
                recipesCode: item.recipesCode,
                storeCode: item.storeCode
            }).then(list => {
                this.orderMaterials = list;
            });
        }
    },
    mounted() {
        this.loadParam();
        this.getData();
    }
});

/***/ })

});