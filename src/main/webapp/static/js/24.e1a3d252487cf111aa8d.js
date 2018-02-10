webpackJsonp([24],{

/***/ 1053:
/***/ (function(module, exports) {

module.exports={render:function (){
var this$1 = this;
var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('MyCabinSelect', {
    on: {
      "input": function (val) {
        this$1.queryCond.cabinCode = val;
      }
    }
  }), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "120px"
    },
    attrs: {
      "placeholder": "原料名称搜索"
    },
    model: {
      value: (_vm.queryCond.materialName),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "materialName", $$v)
      },
      expression: "queryCond.materialName"
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
      value: (_vm.startDate),
      callback: function($$v) {
        _vm.startDate = $$v
      },
      expression: "startDate"
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
      value: (_vm.endDate),
      callback: function($$v) {
        _vm.endDate = $$v
      },
      expression: "endDate"
    }
  }), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索")])], 1), _vm._v(" "), _vm._l((_vm.data), function(item) {
    return _c('el-card', {
      key: item.id,
      staticClass: "card",
      attrs: {
        "body-style": {
          padding: '0px'
        }
      }
    }, [_c('div', {
      staticStyle: {
        "position": "relative",
        "margin": "0px"
      }
    }, [_c('span', [_c('strong', [_vm._v("仓库：")]), _vm._v(_vm._s(item.cabinName))]), _vm._v(" "), _c('span', [_c('strong', [_vm._v("原料：")]), _vm._v(_vm._s(item.materialName))]), _vm._v(" "), _c('span', [_c('strong', [_vm._v("损失：")]), _vm._v(_vm._s(item.stockAmt) + _vm._s(item.stockUnit))]), _vm._v(" "), _c('span', [_c('strong', [_vm._v("报损人: ")]), _vm._v(_vm._s(item.creatorName))]), _vm._v(" "), _c('span', [_c('strong', [_vm._v("报损时间：")]), _vm._v(_vm._s(item.createTime))]), _vm._v(" "), _c('br'), _vm._v(" "), _c('span', [_c('strong', [_vm._v("损耗原因：")]), _vm._v(_vm._s(item.remark))])]), _vm._v(" "), _c('div', {
      staticStyle: {
        "position": "relative"
      }
    }, _vm._l((item.images), function(img) {
      return _c('img', {
        staticClass: "image",
        attrs: {
          "src": _vm.server + '/file/show?image=' + img
        }
      })
    }))])
  }), _vm._v(" "), _c('div', [_c('el-button', {
    attrs: {
      "type": "primary",
      "disabled": _vm.isDisabled
    },
    on: {
      "click": function($event) {
        _vm.getData(2)
      }
    }
  }, [_vm._v("加载更多")])], 1)], 2)
},staticRenderFns: []}

/***/ }),

/***/ 1120:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(988);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("1eae793c", content, true);

/***/ }),

/***/ 636:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1120)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(943),
  /* template */
  __webpack_require__(1053),
  /* scopeId */
  "data-v-16245336",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 683:
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

/***/ 684:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(683),
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
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "placeholder": "仓库或门店",
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

/***/ 943:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__ = __webpack_require__(684);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
        MyCabinSelect: __WEBPACK_IMPORTED_MODULE_1__common_MyCabinSelect___default.a
    },
    data() {
        return {
            tableData: [],
            pageSize: 10,
            pageNo: 1,
            server: __WEBPACK_IMPORTED_MODULE_0__common_bus__["b" /* config */].server,
            queryCond: {
                cabinCode: '',
                materialName: ''
            },
            isDisabled: false,
            startDate: null,
            endDate: null
        };
    },
    mounted() {
        this.getData(1);
    },
    computed: {
        data() {
            const self = this;
            return self.tableData.filter(function (d) {
                return d;
            });
        }
    },
    methods: {
        getData(type) {
            if (type == 1) {
                this.pageNo = 1;
            }
            let param = {
                pageNo: this.pageNo,
                pageSize: this.pageSize
            };
            this.queryCond.startCreatedTime = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.startDate);
            this.queryCond.endCreatedTime = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.endDate);
            Object.assign(param, this.queryCond);
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMyLossApplyDetail(param).then(list => {
                if (!list || !list.length) {
                    this.isDisabled = true;
                } else {
                    list.forEach(item => this.tableData.push(item));
                    this.pageNo = this.pageNo + 1;
                }
            });
        },
        search() {
            this.tableData = [];
            this.pageNo = 1;
            this.isDisabled = false;
            this.getData();
        }
    }
});

/***/ }),

/***/ 988:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-16245336]{margin-bottom:20px}.image[data-v-16245336]{width:130px;height:100px;margin:10px}.card[data-v-16245336]{width:90%;margin:10px}", ""]);

// exports


/***/ })

});