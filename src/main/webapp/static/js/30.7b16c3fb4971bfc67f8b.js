webpackJsonp([30],{

/***/ 1027:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-7e431b7d]{margin-bottom:20px}.handle-select[data-v-7e431b7d]{width:120px}.handle-input[data-v-7e431b7d]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 1099:
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
  }, [_c('el-select', {
    staticStyle: {
      "width": "100px"
    },
    attrs: {
      "clearable": "",
      "placeholder": "费用类型"
    },
    model: {
      value: (_vm.queryCond.type),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "type", $$v)
      },
      expression: "queryCond.type"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "报销",
      "value": "reim"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "备用金",
      "value": "fund"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "付款申请",
      "value": "expense"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "工资",
      "value": "salary"
    }
  })], 1), _vm._v(" "), _c('el-select', {
    staticStyle: {
      "width": "100px"
    },
    attrs: {
      "clearable": "",
      "placeholder": "状态"
    },
    model: {
      value: (_vm.queryCond.status),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "status", $$v)
      },
      expression: "queryCond.status"
    }
  }, _vm._l((_vm.paymentStatusSels), function(item) {
    return _c('el-option', {
      key: item.unitCode,
      attrs: {
        "label": item.unitName,
        "value": item.unitCode
      }
    })
  })), _vm._v(" "), _c('el-date-picker', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "type": "date",
      "placeholder": "起始日期"
    },
    model: {
      value: (_vm.queryCond.startDateD),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "startDateD", $$v)
      },
      expression: "queryCond.startDateD"
    }
  }), _vm._v("\n                -\n                "), _c('el-date-picker', {
    staticStyle: {
      "width": "130px"
    },
    attrs: {
      "type": "date",
      "placeholder": "结束日期"
    },
    model: {
      value: (_vm.queryCond.endDateD),
      callback: function($$v) {
        _vm.$set(_vm.queryCond, "endDateD", $$v)
      },
      expression: "queryCond.endDateD"
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
  }, [_vm._v("申请新单")])], 1)])], 1)], 1), _vm._v(" "), _c('el-table', {
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
      "prop": "payNo",
      "label": "申请单号",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "departmentName",
      "label": "申请部门",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "typeName",
      "label": "分类",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "payables",
      "label": "申请金额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "paidAmt",
      "label": "已付金额",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "状态",
      "width": "100",
      "formatter": _vm.formatStatus
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "申请日期",
      "width": "120",
      "formatter": _vm.formatCreateTime
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
              _vm.edit(scope.$index, scope.row)
            }
          }
        }, [_vm._v("\n                    编辑\n                ")]), _vm._v(" "), _c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.delMaterial(scope.$index, scope.row)
            }
          }
        }, [_vm._v("\n                    删除\n                ")])]
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
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1158:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1027);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("162ad292", content, true);

/***/ }),

/***/ 644:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1158)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(951),
  /* template */
  __webpack_require__(1099),
  /* scopeId */
  "data-v-7e431b7d",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 696:
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

/***/ 698:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(696),
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

/***/ 951:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__ = __webpack_require__(698);
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
//
//
//
//
//
//
//
//
//
//
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
            loadingState: false,
            queryCond: {
                pageNo: 1,
                pageSize: 10,
                totalRows: 0,
                materialCode: '',
                searchKey: '',
                type: '',
                status: '',
                startDateD: null,
                endDateD: null
            },
            paymentStatusSels: [],
            queryList: [],
            userRole: this.$store.state.userRole
        };
    },
    mounted() {
        this.loadParam();
        this.queryData();
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryUnitByGroup('payment_status').then(vals => this.paymentStatusSels = vals);
    },
    methods: {
        handleCurrentChange(val) {
            this.queryCond.pageNo = val;
            this.queryData();
        },
        keepParam() {
            this.$store.commit("setQueryCond", this.queryCond);
        },
        loadParam() {
            Object.assign(this.queryCond, this.$store.state.queryCond);
        },
        formatStatus(row) {
            switch (row.status) {
                case "0":
                    return "暂存";
                case "1":
                    return "待审核";
                case "2":
                    return "审核通过";
                case "3":
                    return "审核驳回";
                case "4":
                    return "待支付";
                case "5":
                    return "已支付";
                default:
                    return "未知";
            }
        },
        formatCreateTime(row) {
            return __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDate(row.createdTime);
        },
        queryData() {
            this.loadingState = true;
            this.queryCond.startDate = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.queryCond.startDateD);
            this.queryCond.endDate = __WEBPACK_IMPORTED_MODULE_0__common_bus__["c" /* util */].formatDateT(this.queryCond.endDateD);
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryPayments(this.queryCond).then(page => {
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
        edit(index, item) {
            this.keepParam();
            this.$router.push({ path: "/paymentInputDetail", query: { id: item && item.id, payno: item && item.payNo } });
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