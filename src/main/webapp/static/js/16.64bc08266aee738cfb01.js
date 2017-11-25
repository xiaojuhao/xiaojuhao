webpackJsonp([16],{

/***/ 623:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(821)

var Component = __webpack_require__(263)(
  /* script */
  __webpack_require__(696),
  /* template */
  __webpack_require__(788),
  /* scopeId */
  "data-v-7cc52745",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 651:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(264);
//
//
//
//
//
//
//
//
//
//
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
      this.allValues = this.$store.state.allMaterials;
      this.selectedCode = this.$props.value;
      // let $data = this.$data;
      // api.queryAllMaterials()
      // .then((value)=>{
      //   $data.allValues = value.values;
      //   $data.selectedCode = this.$props.value;
      // });
    },
    enterkey(e) {
      console.log(e);
    },
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

/***/ 652:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(263)(
  /* script */
  __webpack_require__(651),
  /* template */
  __webpack_require__(653),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 653:
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

/***/ 696:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(264);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__ = __webpack_require__(652);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
        MaterialSelection: __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default.a
    },
    data() {
        return {
            tableData: [],
            pageNo: 1,
            pageSize: 5,
            totalRows: 0,
            loadingState: false,
            queryCond: {
                materialCode: ''
            },
            queryList: [],
            userRole: this.$store.state.userRole
        };
    },
    mounted() {
        this.queryData();
    },
    methods: {
        handleCurrentChange(val) {
            this.pageNo = val;
            this.queryData();
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
            let self = this;
            self.$data.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialsPage({
                pageSize: self.$data.pageSize,
                pageNo: self.$data.pageNo,
                materialCode: self.queryCond.materialCode
            }).then(page => {
                this.queryList = page.values;
                this.totalRows = page.totalRows;
            }).fail(function (resp) {
                self.$message.error("请求出错");
            }).always(function (resp) {
                self.$data.loadingState = false;
            });
        },
        search() {
            this.queryList = [];
            this.queryData();
        },
        edit(index, item) {
            this.$router.push({ path: "/materialManagePage", query: { mid: item && item.id } });
        }
    }
});

/***/ }),

/***/ 747:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-7cc52745]{margin-bottom:20px}.handle-select[data-v-7cc52745]{width:120px}.handle-input[data-v-7cc52745]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 788:
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
  }, [_c('MaterialSelection', {
    on: {
      "input": function (val) {
        this$1.queryCond.materialCode = val;
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
      "prop": "materialCode",
      "label": "原料编码",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "200"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "规格",
      "width": "100",
      "formatter": _vm.formatSpec
    }
  }), _vm._v(" "), (_vm.userRole == '1') ? _c('el-table-column', {
    attrs: {
      "prop": "utilizationRatio",
      "label": "利用率(%)",
      "width": "120"
    }
  }) : _vm._e(), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "storageLife",
      "label": "保质期",
      "width": "120",
      "formatter": _vm.formatStorageLife
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "stockUnit",
      "label": "库存单位",
      "width": ""
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "100"
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

/***/ 821:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(747);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(265)("fde64e5e", content, true);

/***/ })

});