webpackJsonp([51],{

/***/ 1017:
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
  }), _vm._v(" 基础信息管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("门店管理")])], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "form-box"
  }, [_c('el-form', {
    ref: "form",
    attrs: {
      "label-width": "100px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "门店名称"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "门店名称"
    },
    model: {
      value: (_vm.form.storeName),
      callback: function($$v) {
        _vm.$set(_vm.form, "storeName", $$v)
      },
      expression: "form.storeName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "门店编码"
    }
  }, [_c('el-input', {
    attrs: {
      "disabled": "",
      "placeholder": "门店编码"
    },
    model: {
      value: (_vm.form.storeCode),
      callback: function($$v) {
        _vm.$set(_vm.form, "storeCode", $$v)
      },
      expression: "form.storeCode"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "外部系统编码"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "外部系统编码"
    },
    model: {
      value: (_vm.form.outCode),
      callback: function($$v) {
        _vm.$set(_vm.form, "outCode", $$v)
      },
      expression: "form.outCode"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "门店地址"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "门店地址"
    },
    model: {
      value: (_vm.form.storeAddr),
      callback: function($$v) {
        _vm.$set(_vm.form, "storeAddr", $$v)
      },
      expression: "form.storeAddr"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "负责人"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "负责人"
    },
    model: {
      value: (_vm.form.storeManager),
      callback: function($$v) {
        _vm.$set(_vm.form, "storeManager", $$v)
      },
      expression: "form.storeManager"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "负责人手机"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "负责人手机"
    },
    model: {
      value: (_vm.form.managerPhone),
      callback: function($$v) {
        _vm.$set(_vm.form, "managerPhone", $$v)
      },
      expression: "form.managerPhone"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "负责人邮箱"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "负责人邮箱"
    },
    model: {
      value: (_vm.form.managerEmail),
      callback: function($$v) {
        _vm.$set(_vm.form, "managerEmail", $$v)
      },
      expression: "form.managerEmail"
    }
  })], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("提交")]), _vm._v(" "), _c('el-button', {
    on: {
      "click": _vm.onCancel
    }
  }, [_vm._v("取消")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 660:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(953),
  /* template */
  __webpack_require__(1017),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 675:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__bus__ = __webpack_require__(263);



/* harmony default export */ __webpack_exports__["default"] = ({
	server: __WEBPACK_IMPORTED_MODULE_1__bus__["c" /* config */].server,
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

/***/ 676:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(675),
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
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_config_vue__ = __webpack_require__(676);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_config_vue__);
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




/* harmony default export */ __webpack_exports__["default"] = ({
    data: function () {
        return {
            form: {
                id: '',
                storeCode: this.$route.query.storeCode,
                storeName: '',
                storeAddr: '',
                storeManager: '',
                managerPhone: '',
                managerEmail: '',
                defaultWarehouse: ''
            },
            warehouseSelection: []

        };
    },
    methods: {
        onSubmit() {
            __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].saveStore(this.form).then(respVal => {
                this.$message("新增成功");
                this.$router.go(-1);
            });
        },
        onCancel() {
            this.$router.go(-1);
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].queryStoreByCode(this.form.storeCode).then(v => {
            this.form.id = v.id;
            this.form.storeCode = v.storeCode;
            this.form.storeName = v.storeName;
            this.form.storeAddr = v.storeAddr;
            this.form.storeManager = v.storeManager;
            this.form.managerPhone = v.managerPhone;
            this.form.managerEmail = v.managerEmail;
            this.form.defaultWarehouse = v.defaultWarehouse;
            this.form.outCode = v.outCode;
        });
        __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].getAllWarehouseList().then(val => {
            this.warehouseSelection = val;
        });
    }
});

/***/ })

});