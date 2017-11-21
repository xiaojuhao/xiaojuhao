webpackJsonp([10],{

/***/ 623:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(776)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(688),
  /* template */
  __webpack_require__(748),
  /* scopeId */
  "data-v-48e8fabe",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 644:
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
            let $data = this.$data;
            __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].queryAllRecipes().then(value => {
                $data.allValues = value;
                $data.selectedCode = this.$props.value;
            });
        },
        filterMethod(input) {
            let $data = this.$data;
            setTimeout(() => {
                $data.valuesShow = $data.allValues.filter(item => {
                    var key = [item.recipesCode, item.recipesName, item.searchKey].join(',');
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

/***/ 647:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(644),
  /* template */
  __webpack_require__(648),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 648:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('el-select', {
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

/***/ 688:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus_js__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_RecipesSelection__ = __webpack_require__(647);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_RecipesSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_RecipesSelection__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
        RecipesSelection: __WEBPACK_IMPORTED_MODULE_1__common_RecipesSelection___default.a
    },
    data() {
        return {
            tableData: [],
            pageNo: 1,
            pageSize: 5,
            totalRows: 0,
            loadingState: false,
            queryCond: {
                recipesCode: ''
            },
            queryList: []
        };
    },
    created() {},
    mounted() {
        this.queryData();
    },
    activated() {},
    computed: {},
    methods: {
        handleCurrentChange(val) {
            this.pageNo = val;
            this.queryData();
        },
        queryData() {
            let self = this;
            self.$data.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus_js__["a" /* api */].queryRecipesPage({
                recipesCode: this.queryCond.recipesCode
            }).then(page => {
                self.$data.totalRows = page.totalRows;
                Object.keys(page.values).forEach(index => {
                    let item = page.values[index];
                    item.formulas = '';
                    self.$data.queryList.push(item);
                });
            }).always(() => {
                self.$data.loadingState = false;
            });
        },
        search() {
            this.queryList = [];
            this.queryData();
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        handleSelect(item) {
            this.$data.query.name = item.value;
        },
        querySearch(queryString, cb) {
            var data = [];
            data.push({ id: 1, value: 'aaaaa' });
            data.push({ id: 2, value: 'bbbbb' });
            data.push({ id: 3, value: 'ccccc' });
            console.log(this.$data.query);
            cb(data);
        },
        edit(index, item) {
            this.$router.push({ path: "/recipesManagePage", query: { code: item && item.recipesCode } });
        },
        expand(row, expanded) {
            if (expanded) {
                __WEBPACK_IMPORTED_MODULE_0__common_bus_js__["a" /* api */].queryRecipesFormula(row.recipesCode).then(values => {
                    row.formulas = values;
                });
            }
        }
    }
});

/***/ }),

/***/ 715:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-48e8fabe]{margin-bottom:20px}.handle-select[data-v-48e8fabe]{width:120px}.handle-input[data-v-48e8fabe]{width:300px;display:inline-block}.el-row[data-v-48e8fabe]{margin-bottom:4px;&:last-child{margin-bottom:0}}", ""]);

// exports


/***/ }),

/***/ 748:
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
      "span": 4
    }
  }, [_c('RecipesSelection', {
    on: {
      "input": function (v) {
        this$1.queryCond.recipesCode = v;
      }
    }
  })], 1), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索")])], 1), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 16
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
  }, [_vm._v("增加菜品")])], 1)])], 1)], 1), _vm._v(" "), _c('el-table', {
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
    },
    on: {
      "expand": _vm.expand
    }
  }, [_c('el-table-column', {
    attrs: {
      "type": "expand"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(props) {
        return _vm._l((props.row.formulas), function(item) {
          return _c('el-row', [_c('el-col', {
            attrs: {
              "span": 3
            }
          }, [_vm._v(_vm._s(item.materialName))]), _vm._v(" "), _c('el-col', {
            attrs: {
              "span": 1
            }
          }, [_vm._v(_vm._s(item.materialAmt))]), _vm._v(" "), _c('el-col', {
            attrs: {
              "span": 1
            }
          }, [_vm._v(_vm._s(item.materialUnit))])], 1)
        })
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "recipesCode",
      "label": "菜品编码",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "recipesName",
      "label": "菜品名称",
      "width": "250"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "outCode",
      "label": "外部系统编号",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "120"
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
              _vm.edit(scope.$index, scope.row)
            }
          }
        }, [_vm._v("编辑")])]
      }
    }])
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "pagination"
  }, [_c('el-pagination', {
    attrs: {
      "layout": "prev, pager, next",
      "total": _vm.totalRows,
      "page-size": _vm.pageSize
    },
    on: {
      "current-change": _vm.handleCurrentChange
    }
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 776:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(715);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("43c1387e", content, true);

/***/ })

});