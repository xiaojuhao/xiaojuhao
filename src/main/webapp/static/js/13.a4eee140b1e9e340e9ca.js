webpackJsonp([13],{

/***/ 1002:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-d379d40c]{margin-bottom:20px}.handle-select[data-v-d379d40c]{width:120px}.handle-input[data-v-d379d40c]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 1059:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
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
        _vm.queryCond.cabinCode = v;
      }
    }
  }), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "180px"
    },
    attrs: {
      "placeholder": "搜索条件"
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
  }, [_vm._v("搜索")]), _vm._v("支持原料名称、配音搜索\n            ")], 1), _vm._v(" "), _c('el-col', {
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
      "data": _vm.tableData,
      "border": "",
      "element-loading-text": "拼命加载中",
      "element-loading-spinner": "el-icon-loading",
      "element-loading-background": "rgb(0, 0, 0, 0.8)"
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "仓库",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "申请日期",
      "width": "120",
      "formatter": _vm.formatRequireDate
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "需求量",
      "width": "100"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_vm._v("\n                " + _vm._s(scope.row.requireAmt) + _vm._s(scope.row.stockUnit) + "\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购规格",
      "width": "160"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-select', {
          staticStyle: {
            "width": "140px"
          },
          attrs: {
            "slot": "append",
            "size": "small"
          },
          on: {
            "change": function($event) {
              _vm.onSelectSpec(scope.row, scope.row.specCode)
            }
          },
          slot: "append",
          model: {
            value: (scope.row.specCode),
            callback: function($$v) {
              _vm.$set(scope.row, "specCode", $$v)
            },
            expression: "scope.row.specCode"
          }
        }, _vm._l((scope.row.specCodeSel), function(item) {
          return _c('el-option', {
            key: item.specCode,
            attrs: {
              "label": item.specName,
              "value": item.specCode
            }
          })
        }))]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购量",
      "width": "160"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.specAmt),
            callback: function($$v) {
              _vm.$set(scope.row, "specAmt", $$v)
            },
            expression: "scope.row.specAmt"
          }
        }, [_c('template', {
          attrs: {
            "slot": "append"
          },
          slot: "append"
        }, [_vm._v(_vm._s(scope.row.specUnit))])], 2)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "采购价",
      "width": "160"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-input', {
          attrs: {
            "size": "small"
          },
          model: {
            value: (scope.row.specPrice),
            callback: function($$v) {
              _vm.$set(scope.row, "specPrice", $$v)
            },
            expression: "scope.row.specPrice"
          }
        }, [_c('template', {
          attrs: {
            "slot": "append"
          },
          slot: "append"
        }, [_vm._v("元/" + _vm._s(scope.row.specUnit))])], 2)]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "供应商",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-select', {
          staticStyle: {
            "width": "140px"
          },
          attrs: {
            "slot": "append"
          },
          slot: "append",
          model: {
            value: (scope.row.supplierCode),
            callback: function($$v) {
              _vm.$set(scope.row, "supplierCode", $$v)
            },
            expression: "scope.row.supplierCode"
          }
        }, _vm._l((scope.row.supplierSel), function(item) {
          return _c('el-option', {
            key: item.supplierCode,
            attrs: {
              "label": item.supplierName,
              "value": item.supplierCode
            }
          })
        }))]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "remark",
      "label": "需求备注",
      "width": "120"
    }
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
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1105:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1002);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("06f84f78", content, true);

/***/ }),

/***/ 630:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1105)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(924),
  /* template */
  __webpack_require__(1059),
  /* scopeId */
  "data-v-d379d40c",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 681:
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

/***/ 683:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(681),
  /* template */
  __webpack_require__(684),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 684:
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

/***/ 692:
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

/***/ 696:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(692),
  /* template */
  __webpack_require__(699),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 699:
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

/***/ 924:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__ = __webpack_require__(683);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_MyCabinSelect__ = __webpack_require__(696);
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
//
//
//
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
            userRole: this.$store.state.userRole
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
        formatRequireDate(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* util */].formatDate(row.requireDate);
        },
        formatSpec(row) {
            if (row.specUnit == '无') {
                return '无';
            }
            return row.specQty + row.stockUnit + "/" + row.specUnit;
        },
        onSelectSpec() {},
        queryData() {
            this.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialRequire({
                pageSize: this.queryCond.pageSize,
                pageNo: this.queryCond.pageNo,
                materialCode: this.queryCond.materialCode,
                cabinCode: this.queryCond.cabinCode
            }).then(page => {
                this.tableData = page.values;
                this.queryCond.totalRows = page.totalRows;
                this.tableData.forEach(it => {
                    this.initItem(it);
                });
            }).fail(resp => {
                this.$message.error("请求出错");
            }).always(resp => {
                this.loadingState = false;
            });
        },
        initItem(item) {
            setTimeout(() => {
                //规格选项
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].querySpecDetailByMaterialCode(item.materialCode).then(list => {
                    if (list && list.length > 0) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specCode', list[0].specCode);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specUnit', list[0].specUnit);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specQty', list[0].transRate);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'brandName', list[0].brandName);
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'homeplace', list[0].homeplace);
                    }
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'specCodeSel', list);
                });
                //供应商选项
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialSupplerByCode({ materialCode: item.materialCode }).then(list => {
                    if (list && list.length > 0) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'supplierCode', list[0].supplierCode);
                    }
                    __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'supplierSel', list);
                });
            }, 0);
        },
        search() {
            this.tableData = [];
            this.queryData();
        },
        edit(index, item) {
            this.keepParam();
            this.$router.push({ path: "/materialManagePage", query: { mid: item && item.id } });
        },
        delMaterial(index, item) {
            let tips = "是否删除" + item.materialName + "?";
            this.$confirm(tips, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].deleteMaterials(item.materialCode).then(() => {
                    this.queryData();
                }).fail(resp => {
                    this.$message.error(resp.message);
                });
            });
        }
    }
});

/***/ })

});