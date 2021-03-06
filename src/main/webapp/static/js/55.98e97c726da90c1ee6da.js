webpackJsonp([55],{

/***/ 1078:
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
  }), _vm._v(" 基础信息管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("仓库管理")])], 1)], 1), _vm._v(" "), _c('div', {
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
      "placeholder": "仓库名称"
    },
    model: {
      value: (_vm.form.warehouseName),
      callback: function($$v) {
        _vm.$set(_vm.form, "warehouseName", $$v)
      },
      expression: "form.warehouseName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "门店地址"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "仓库地址"
    },
    model: {
      value: (_vm.form.warehouseAddr),
      callback: function($$v) {
        _vm.$set(_vm.form, "warehouseAddr", $$v)
      },
      expression: "form.warehouseAddr"
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
      value: (_vm.form.warehouseManager),
      callback: function($$v) {
        _vm.$set(_vm.form, "warehouseManager", $$v)
      },
      expression: "form.warehouseManager"
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
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "配送门店"
    }
  }, [_c('el-checkbox-group', {
    model: {
      value: (_vm.relatedStores),
      callback: function($$v) {
        _vm.relatedStores = $$v
      },
      expression: "relatedStores"
    }
  }, _vm._l((_vm.allStores), function(item) {
    return _c('el-checkbox', {
      key: item.storeCode,
      attrs: {
        "label": item.storeCode,
        "checked": item.checked
      }
    }, [_vm._v(_vm._s(item.storeName))])
  }))], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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

/***/ 670:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(969),
  /* template */
  __webpack_require__(1078),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 683:
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

/***/ 684:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(683),
  /* template */
  null,
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 969:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_config_vue__ = __webpack_require__(684);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_config_vue__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
                warehouseCode: this.$route.query.warehouseCode,
                warehouseName: '',
                warehouseAddr: '',
                warehouseManager: '',
                managerPhone: '',
                managerEmail: '',
                relatedStoresStr: ''
            },
            relatedStores: []

        };
    },
    methods: {
        onSubmit() {
            this.form.relatedStoresStr = this.relatedStores.join(',');
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].saveWarehouse(this.form).then(respVal => {
                this.$message("新增成功");
                this.$router.go(-1);
            }).fail(resp => {
                this.$message.error(resp.message);
            });
        },
        onCancel() {
            this.$router.go(-1);
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getWarehouseByCode(this.$data.form.warehouseCode).then(v => {
            this.$data.form.id = v.id;
            this.$data.form.warehouseCode = v.warehouseCode;
            this.$data.form.warehouseName = v.warehouseName;
            this.$data.form.warehouseAddr = v.warehouseAddr;
            this.$data.form.warehouseManager = v.warehouseManager;
            this.$data.form.managerPhone = v.managerPhone;
            this.$data.form.managerEmail = v.managerEmail;
            this.relatedStores = v.relatedStore && v.relatedStore.split(',') || [];
        });
    },
    computed: {
        allStores() {
            return this.$store.state.allStores.filter(item => {
                if (this.relatedStoresMap.get(item.storeCode)) {
                    item.checked = true;
                } else {
                    item.checked = false;
                }
                return true;
            });
        },
        relatedStoresMap() {
            let map = new Map();
            this.relatedStores && this.relatedStores.forEach(item => {
                map.set(item, 1);
            });
            return map;
        }
    }
});

/***/ })

});