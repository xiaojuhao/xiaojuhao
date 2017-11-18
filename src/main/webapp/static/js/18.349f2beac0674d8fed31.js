webpackJsonp([18],{

/***/ 615:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(777)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(676),
  /* template */
  __webpack_require__(754),
  /* scopeId */
  "data-v-f1f382f0",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 638:
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

/***/ 639:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(638),
  /* template */
  null,
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 676:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue__ = __webpack_require__(639);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__common_config_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            diaoboAmt: 0,
            allCabins: [],
            toCabinCode: ''
        };
    },
    methods: {
        onSubmit() {
            __WEBPACK_IMPORTED_MODULE_1__common_bus__["a" /* api */].diaobo({
                materialCode: this.item.materialCode,
                diaoboAmt: this.diaoboAmt,
                fromCabCode: this.item.cabinCode,
                toCabCode: this.toCabinCode
            }).then(val => {
                this.$message("提交成功");
                this.$router.go(-1);
            }).fail(resp => {
                this.$message.error(resp.message);
            });
        },
        onBack() {
            this.$router.go(-1);
        },
        initData() {
            var stockId = this.$route.query.stockId;
            __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default.a.queryMaterialsStockById(stockId, resp => {
                this.item = resp.value;
            });
        }
    },
    computed: {},
    mounted() {
        this.initData();
        __WEBPACK_IMPORTED_MODULE_1__common_bus__["a" /* api */].getAllStoreList().then(stores => {
            stores.forEach(s => {
                let c = {};
                c.cabinCode = s.storeCode;
                c.cabinName = s.storeName;
                c.type = "门店";
                this.allCabins.push(c);
            });
        });

        __WEBPACK_IMPORTED_MODULE_1__common_bus__["a" /* api */].getAllWarehouseList().then(wares => {
            wares.forEach(w => {
                let c = {};
                c.cabinCode = w.warehouseCode;
                c.cabinName = w.warehouseName;
                c.type = "仓库";
                this.allCabins.push(c);
            });
        });
    },
    activated() {}
});

/***/ }),

/***/ 722:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".table-simple[data-v-f1f382f0]{font-size:0}.table-simple label[data-v-f1f382f0]{width:90px;color:#99a9bf;background-color:red}.table-simple .el-form-item[data-v-f1f382f0]{margin-right:0;margin-bottom:0;width:50%}.table-simple .el-form-item2[data-v-f1f382f0]{width:90%}.el-form-item-button[data-v-f1f382f0]{margin-top:10px;margin-left:20%;width:90%}", ""]);

// exports


/***/ }),

/***/ 754:
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
  }), _vm._v(" 进销存管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("调拨")])], 1)], 1), _vm._v(" "), _c('div', {
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
      "label": "仓库/门店"
    }
  }, [_c('span', [_vm._v(_vm._s(_vm.item.cabinName))])]), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "调拨数量"
    }
  }, [_c('el-input', {
    model: {
      value: (_vm.diaoboAmt),
      callback: function($$v) {
        _vm.diaoboAmt = $$v
      },
      expression: "diaoboAmt"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "拨入"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.toCabinCode),
      callback: function($$v) {
        _vm.toCabinCode = $$v
      },
      expression: "toCabinCode"
    }
  }, _vm._l((_vm.allCabins), function(item) {
    return _c('el-option', {
      key: item.cabinCode,
      attrs: {
        "label": item.cabinName,
        "value": item.cabinCode
      }
    }, [_c('span', {
      staticStyle: {
        "float": "left"
      }
    }, [_vm._v(_vm._s(item.cabinName))]), _vm._v(" "), _c('span', {
      staticStyle: {
        "float": "right",
        "color": "#8492a6",
        "font-size": "13px"
      }
    }, [_vm._v(_vm._s(item.type))])])
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

/***/ 777:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(722);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("8a0e5354", content, true);

/***/ })

});