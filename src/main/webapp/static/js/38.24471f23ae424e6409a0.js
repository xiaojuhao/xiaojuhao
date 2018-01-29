webpackJsonp([38],{

/***/ 1004:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".form-box[data-v-402d7fae]{width:100%}.span-title[data-v-402d7fae]{display:-moz-inline-box;display:inline-block;width:80px;text-align:right;margin-right:10px}.span-content[data-v-402d7fae]{display:-moz-inline-box;display:inline-block;width:200px}.el-input[data-v-402d7fae]{width:200px}", ""]);

// exports


/***/ }),

/***/ 1068:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 费用管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("费用明细")])], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "form-box"
  }, [_c('el-form', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState),
      expression: "loadingState"
    }],
    ref: "form",
    attrs: {
      "label-width": "0"
    }
  }, [_c('el-form-item', [_c('span', {
    staticClass: "span-title"
  }, [_vm._v("\n                    申请单号\n                ")]), _vm._v(" "), _c('el-input', {
    attrs: {
      "disabled": "",
      "placeholder": "自动生成"
    },
    model: {
      value: (_vm.form.payNo),
      callback: function($$v) {
        _vm.$set(_vm.form, "payNo", $$v)
      },
      expression: "form.payNo"
    }
  }), _vm._v(" "), _c('span', {
    staticClass: "span-title"
  }, [_vm._v("\n                    申请部门\n                ")]), _vm._v(" "), _c('el-select', {
    model: {
      value: (_vm.form.department),
      callback: function($$v) {
        _vm.$set(_vm.form, "department", $$v)
      },
      expression: "form.department"
    }
  }, _vm._l((_vm.mycabins), function(item) {
    return _c('el-option', {
      key: item.cabinCode,
      attrs: {
        "label": item.cabinName,
        "value": item.cabinCode
      }
    })
  })), _vm._v(" "), _c('span', {
    staticClass: "span-title"
  }, [_vm._v("\n                    申请单状态\n                ")]), _vm._v(" "), _c('el-select', {
    attrs: {
      "disabled": "",
      "clearable": "",
      "placeholder": "状态"
    },
    model: {
      value: (_vm.form.status),
      callback: function($$v) {
        _vm.$set(_vm.form, "status", $$v)
      },
      expression: "form.status"
    }
  }, _vm._l((_vm.paymentStatusSels), function(item) {
    return _c('el-option', {
      key: item.unitCode,
      attrs: {
        "label": item.unitName,
        "value": item.unitCode
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', [_c('span', {
    staticClass: "span-title"
  }, [_vm._v("\n                    费用类型\n                ")]), _vm._v(" "), _c('el-select', {
    attrs: {
      "disabled": _vm.isEditPage
    },
    model: {
      value: (_vm.form.type),
      callback: function($$v) {
        _vm.$set(_vm.form, "type", $$v)
      },
      expression: "form.type"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "采购单",
      "value": "inventory"
    }
  }), _vm._v(" "), _c('el-option', {
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
  })], 1), _vm._v(" "), _c('span', {
    staticClass: "span-title"
  }, [_vm._v("\n                    申请金额\n                ")]), _vm._v(" "), _c('span', {
    staticClass: "span-content"
  }, [_c('el-input', {
    staticStyle: {
      "width": "120px"
    },
    model: {
      value: (_vm.form.payables),
      callback: function($$v) {
        _vm.$set(_vm.form, "payables", $$v)
      },
      expression: "form.payables"
    }
  }, [_c('template', {
    attrs: {
      "slot": "append"
    },
    slot: "append"
  }, [_vm._v("元")])], 2)], 1), _vm._v(" "), _c('span', {
    staticClass: "span-title"
  }, [_vm._v("\n                    已付金额\n                ")]), _vm._v(" "), _c('span', {
    staticClass: "span-content"
  }, [_vm._v("\n                    " + _vm._s(_vm.form.paidAmt) + " 元\n                ")])], 1), _vm._v(" "), _c('el-form-item', [_c('div', {
    staticStyle: {
      "text-align": "center",
      "width": "60%"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary "
    },
    on: {
      "click": function($event) {}
    }
  }, [_vm._v("提交")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary "
    },
    on: {
      "click": _vm.onCancle
    }
  }, [_vm._v("返回")])], 1)])], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1134:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1004);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("75c91e4b", content, true);

/***/ }),

/***/ 645:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1134)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(953),
  /* template */
  __webpack_require__(1068),
  /* scopeId */
  "data-v-402d7fae",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 692:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__bus__ = __webpack_require__(263);



/* harmony default export */ __webpack_exports__["default"] = ({
	server: __WEBPACK_IMPORTED_MODULE_1__bus__["b" /* config */].server,
	getAllStore: function (cb) {
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: this.server + "/store/getAllStore",
			dataType: 'jsonp'
		}).then(resp => {
			cb && cb(resp);
		});
	},
	queryMaterialsStockById: function (stockId, cb) {
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: this.server + "/busi/queryMaterialsStockById",
			data: { 'id': stockId },
			dataType: 'jsonp'
		}).then(resp => {
			cb && cb(resp);
		});
	},
	getWarehouse: function (param, cb) {
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: this.server + "/warehouse/queryWarehouses",
			data: param,
			dataType: 'jsonp'
		}).then(resp => {
			cb && cb(resp);
		});
	},
	getWarehouseByCode: function (code, cb) {
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: this.server + "/warehouse/queryWarehouses",
			data: { warehouseCode: code },
			dataType: 'jsonp'
		}).then(resp => {
			cb && cb(resp);
		});
	},
	login: function (data, cb, fcb) {
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: this.server + "/user/login",
			data: data,
			dataType: 'jsonp'
		}).then(resp => {
			cb && cb(resp);
		}).fail(resp => {
			fcb && fcb(resp);
		});
	},
	queryUsers: function (userDO, cb, fcb) {
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: this.server + "/user/queryUsers",
			data: userDO,
			dataType: 'jsonp'
		}).then(resp => {
			cb && cb(resp);
		}).fail(resp => {
			fcb && fcb(resp);
		});
	},
	search: function (param, cb) {
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: this.server + "/s/w",
			data: param,
			dataType: 'jsonp'
		}).then(resp => {
			cb && cb(resp);
		});
	}
});

/***/ }),

/***/ 693:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(692),
  /* template */
  null,
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 953:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue__ = __webpack_require__(693);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__common_config_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            form: {
                payNo: this.$route.query.payno,
                status: '0',
                payables: 0,
                paidAmt: 0
            },
            rules: {},
            loadingState: false,
            stockUnits: [],
            categorySel: [],
            mycabins: [],
            paymentStatusSels: []
        };
    },
    methods: {
        onCancle() {
            this.$router.go(-1);
        }
    },
    mounted() {
        if (this.$route.query.payno) {
            __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].queryPaymentByPayNo(this.$route.query.id, this.$route.query.payno).then(val => {
                Object.assign(this.form, val);
            }).fail(resp => {
                this.$message.error(resp.message);
            });
        }
        __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].queryMycabins().then(list => {
            this.mycabins = list;
        });
        __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].queryUnitByGroup('payment_status').then(vals => this.paymentStatusSels = vals);
    },
    computed: {
        isEditPage() {
            return !!this.$route.query.payno;
        }
    }
});

/***/ })

});