webpackJsonp([22],{

/***/ 623:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(828)

var Component = __webpack_require__(263)(
  /* script */
  __webpack_require__(698),
  /* template */
  __webpack_require__(790),
  /* scopeId */
  "data-v-53985ce8",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 652:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery__ = __webpack_require__(266);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__bus__ = __webpack_require__(264);



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

/***/ 653:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(263)(
  /* script */
  __webpack_require__(652),
  /* template */
  null,
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 698:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue__ = __webpack_require__(653);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__common_config_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery__ = __webpack_require__(266);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_bus__ = __webpack_require__(264);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            item: {},
            inStockAmt: 0,
            warehouseCode: '',
            warehouseSelection: []
        };
    },
    methods: {
        onSubmit() {
            var self = this;
            __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].instock({
                materialCode: self.item.materialCode,
                instockAmt: self.inStockAmt,
                warehouseCode: self.warehouseCode
            }).then(resp => {
                self.$message("处理成功");
                self.$router.go(-1);
            }).fail(resp => {
                self.$message.error(resp.message);
            });
        },
        onBack() {
            this.$router.go(-1);
        },
        initData() {
            var stockId = this.$route.query.stockId;
            var $data = this;
            __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default.a.queryMaterialsStockById(stockId, resp => {
                $data.item = resp.value;
            });
        }
    },
    computed: {
        stockTypeName: function () {
            if (this.item.stockType && this.item.stockType == 1) {
                return '总库';
            } else {
                return '分库';
            }
        }
    },
    mounted() {
        this.initData();
        __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default.a.getWarehouse({}, resp => {
            console.log(resp);
            this.warehouseSelection = resp.value.values;
        });
    },
    activated() {}
});

/***/ }),

/***/ 748:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".table-simple[data-v-53985ce8]{font-size:0}.table-simple label[data-v-53985ce8]{width:90px;color:#99a9bf;background-color:red}.table-simple .el-form-item[data-v-53985ce8]{margin-right:0;margin-bottom:0;width:50%}.table-simple .el-form-item2[data-v-53985ce8]{width:90%}.el-form-item-button[data-v-53985ce8]{margin-top:10px;margin-left:20%;width:90%}.input-width-short[data-v-53985ce8]{width:150px}", ""]);

// exports


/***/ }),

/***/ 790:
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
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("入库")])], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "form-box"
  }, [_c('el-form', {
    ref: "form",
    staticClass: "table-simple",
    attrs: {
      "inline": true,
      "label-width": "80px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "原料名称"
    }
  }, [_c('span', [_vm._v(_vm._s(_vm.item.materialName))])]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "原料编码"
    }
  }, [_c('span', [_vm._v(_vm._s(_vm.item.materialCode))])]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "当前库存"
    }
  }, [_c('span', [_vm._v(_vm._s(_vm.item.currStock))])]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "已用数量"
    }
  }, [_c('span', [_vm._v(_vm._s(_vm.item.usedStock))])]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "库存类型"
    }
  }, [_c('span', [_vm._v(_vm._s(_vm.stockTypeName))])]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "是否拆分"
    }
  }, [_c('span', [_vm._v("否")])]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "入库数量"
    }
  }, [_c('el-input', {
    staticClass: "input-width-short",
    model: {
      value: (_vm.inStockAmt),
      callback: function($$v) {
        _vm.inStockAmt = $$v
      },
      expression: "inStockAmt"
    }
  }, [_c('template', {
    attrs: {
      "slot": "append"
    },
    slot: "append"
  }, [_vm._v(_vm._s(_vm.item.stockUnit))])], 2)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "仓库"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.warehouseCode),
      callback: function($$v) {
        _vm.warehouseCode = $$v
      },
      expression: "warehouseCode"
    }
  }, _vm._l((_vm.warehouseSelection), function(item) {
    return _c('el-option', {
      key: item.warehouseCode,
      attrs: {
        "label": item.warehouseName,
        "value": item.warehouseCode
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    staticClass: "el-form-item-button"
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("提交")]), _vm._v(" "), _c('span', {
    staticStyle: {
      "margin-right": "20px"
    }
  }), _vm._v(" "), _c('el-button', {
    on: {
      "click": _vm.onBack
    }
  }, [_vm._v("取消")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 828:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(748);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(265)("417be5c0", content, true);

/***/ })

});