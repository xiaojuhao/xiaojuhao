webpackJsonp([12],{

/***/ 1019:
/***/ (function(module, exports) {

module.exports={render:function (){
var this$1 = this;
var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('el-row', {
    attrs: {
      "gutter": 10
    }
  }, [_c('el-col', {
    attrs: {
      "span": 16
    }
  }, [_c('MyCabinSelect', {
    on: {
      "input": function (v) {
        this$1.queryCond.cabinCode = v;
      }
    }
  }), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "180px"
    },
    attrs: {
      "placeholder": "原料名称或配音"
    },
    model: {
      value: (_vm.queryCond.searchKey),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "searchKey", $$v)
      },
      expression: "queryCond.searchKey"
    }
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索")])], 1), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 8
    }
  }, [_c('div', {
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
  }, [_vm._v("增加原料")])], 1)])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "materialName",
      "label": "原料名称",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "仓库",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "warningValue1",
      "label": "闲时预警"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "warningValue2",
      "label": "忙时预警"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "150"
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
              _vm.showWarningSetPage(scope.$index, scope.row)
            }
          }
        }, [_vm._v("\n                    设置预警\n                ")])]
      }
    }])
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "pagination"
  }, [_c('el-pagination', {
    attrs: {
      "layout": "prev, pager, next",
      "total": _vm.queryCond.totalRows,
      "page-size": _vm.queryCond.pageSize,
      "current-page": _vm.queryCond.pageNo
    },
    on: {
      "current-change": _vm.handleCurrentChange,
      "update:currentPage": function($event) {
        _vm.$set(_vm.queryCond, "pageNo", $event)
      }
    }
  })], 1), _vm._v(" "), _c('el-dialog', {
    staticClass: "dialog",
    attrs: {
      "title": _vm.dialogTitle
    },
    model: {
      value: (_vm.dialogWaringSetShow),
      callback: function($$v) {
        _vm.dialogWaringSetShow = $$v
      },
      expression: "dialogWaringSetShow"
    }
  }, [_c('el-form', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState),
      expression: "loadingState"
    }],
    attrs: {
      "label-width": "90px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "原料名称"
    }
  }, [_vm._v(_vm._s(_vm.material.materialName))]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "仓库"
    }
  }, [_vm._v(_vm._s(_vm.material.cabinName) + "\n            ")]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "闲时预警值"
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "100px"
    },
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.material.warningValue1),
      callback: function($$v) {
        _vm.$set(_vm.material, "warningValue1", $$v)
      },
      expression: "material.warningValue1"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "忙时预警值"
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "100px"
    },
    attrs: {
      "size": "small"
    },
    model: {
      value: (_vm.material.warningValue2),
      callback: function($$v) {
        _vm.$set(_vm.material, "warningValue2", $$v)
      },
      expression: "material.warningValue2"
    }
  })], 1)], 1), _vm._v(" "), _c('div', {
    staticStyle: {
      "text-align": "center",
      "margin-top": "10px"
    }
  }, [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    },
    on: {
      "click": _vm.saveWaringValue
    }
  }, [_vm._v("\n                保存预警设置\n            ")])], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1069:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(968);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("2e2168a4", content, true);

/***/ }),

/***/ 630:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1069)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(923),
  /* template */
  __webpack_require__(1019),
  /* scopeId */
  "data-v-26f3bc86",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 682:
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
            console.log(this.$store.state.allMaterials);
            this.allValues = this.$store.state.allMaterials;
            this.selectedCode = this.$props.value;
        },
        enterkey(e) {},
        filterMethod(input) {
            let $data = this.$data;
            setTimeout(() => {
                $data.valuesShow = $data.allValues.filter(item => {
                    var key = [item.materialCode, item.materialName, item.searchKey].join(',');
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
                    if (this.excludesMap[item.materialCode]) {
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

/***/ 684:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(682),
  /* template */
  __webpack_require__(685),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 685:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "请选择",
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
      key: item.materialCode,
      attrs: {
        "label": item.materialName + '-' + item.materialCode,
        "value": item.materialCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 698:
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
            __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].queryMyWarehouse().then(list => {
                list.forEach(item => {
                    let ii = {};
                    ii.code = item.warehouseCode;
                    ii.name = item.warehouseName;
                    ii.type = "仓库";
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

/***/ 703:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(698),
  /* template */
  __webpack_require__(704),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 704:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      "display": "inline"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "选择仓库或门店",
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

/***/ 923:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__ = __webpack_require__(684);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect__ = __webpack_require__(703);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_vue__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
        MaterialSelection: __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default.a,
        MyCabinSelect: __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect___default.a
    },
    data() {
        return {
            tableData: [],
            loadingState: false,
            queryCond: {
                pageNo: 1,
                pageSize: 10,
                totalRows: 0,
                materialCode: '',
                cabinCode: '',
                searchKey: ''
            },
            queryList: [],
            userRole: this.$store.state.userRole,
            dialogWaringSetShow: false,
            dialogTitle: '预警设置',
            material: {}
        };
    },
    mounted() {
        this.loadParam();
        this.queryData();
    },
    methods: {
        handleCurrentChange(val) {
            this.queryCond.pageNo = val;
            this.queryData();
        },
        keepParam() {
            let p = {
                pageNo: this.queryCond.pageNo,
                pageSize: this.queryCond.pageSize,
                totalRows: this.queryCond.totalRows,
                materialCode: this.queryCond.materialCode
            };
            this.$store.commit("setQueryCond", p);
        },
        loadParam() {
            Object.assign(this.queryCond, this.$store.state.queryCond);
        },
        formatStorageLife(row) {
            let re = /(\d+)(\w)/ig;
            let r = re.exec(row.storageLife);
            let ret = "";
            if (r) {
                ret = r[1];
                switch (r[2]) {
                    case "D":
                        ret = ret + "天";
                        break;
                    case "M":
                        ret = ret + "月";
                        break;
                }
            }
            return ret;
        },
        formatSpec(row) {
            if (row.specUnit == '无') {
                return '无';
            }
            return row.specQty + row.stockUnit + "/" + row.specUnit;
        },
        queryData() {
            this.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialsStockPage({
                pageSize: this.queryCond.pageSize,
                pageNo: this.queryCond.pageNo,
                materialCode: this.queryCond.materialCode,
                cabinCode: this.queryCond.cabinCode,
                searchKey: this.queryCond.searchKey,
                status: '1'
            }).then(page => {
                this.queryList = page.values;
                this.queryCond.totalRows = page.totalRows;
            }).fail(resp => {
                this.$message.error("请求出错");
            }).always(resp => {
                this.loadingState = false;
            });
        },
        search() {
            this.queryList = [];
            this.queryData();
        },
        showWarningSetPage(index, item) {
            this.dialogWaringSetShow = true;
            this.material = item;
        },
        saveWaringValue() {
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].saveWarning(this.material).then(resp => {
                this.$message("设置成功");
                setTimeout(() => {
                    this.dialogWaringSetShow = false;
                }, 500);
                this.queryData();
            }).fail(resp => {
                this.$message.error(resp.message);
            });
        }
    }
});

/***/ }),

/***/ 968:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-26f3bc86]{margin-bottom:20px}.handle-select[data-v-26f3bc86]{width:120px}.handle-input[data-v-26f3bc86]{width:300px;display:inline-block}", ""]);

// exports


/***/ })

});