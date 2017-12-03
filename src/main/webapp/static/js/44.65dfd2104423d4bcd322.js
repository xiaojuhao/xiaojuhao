webpackJsonp([44],{

/***/ 626:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(706),
  /* template */
  __webpack_require__(788),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 656:
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

/***/ 657:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(656),
  /* template */
  null,
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 706:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue__ = __webpack_require__(657);
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
//




/* harmony default export */ __webpack_exports__["default"] = ({
    data: function () {
        return {
            form: {
                id: this.$route.query.mid,
                materialName: '',
                materialCode: '',
                utilizationRatio: 100,
                specUnit: 'NONE',
                specQty: 0,
                stockUnit: '',
                searchKey: '',
                storageLifeUnit: 'D',
                storageLifeNum: ''
            },
            rules: {},
            loadingState: false,
            splitMaterials: []
        };
    },
    methods: {
        onSubmit() {
            this.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].addMaterials(this.form).then(resp => {
                this.$message.success("操作成功");
                this.$router.go(-1);
            }).always(() => {
                this.loadingState = false;
            });
        },
        onCancel() {
            this.$router.go(-1);
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].queryMaterialById(this.form.id).then(v => {
            let re = /(\d+)(\w)/ig;
            console.log(v);
            this.form.materialName = v.materialName;
            this.form.materialCode = v.materialCode;
            this.form.stockUnit = v.stockUnit;
            this.form.searchKey = v.searchKey;
            this.form.specUnit = v.specUnit;
            this.form.specQty = v.specQty || 0;
            this.form.utilizationRatio = v.utilizationRatio;
            let r = re.exec(v.storageLife);
            if (r) {
                this.form.storageLifeNum = r[1];
                this.form.storageLifeUnit = r[2];
            }
        });
    }
});

/***/ }),

/***/ 788:
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
  }), _vm._v(" 基础信息管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("原料管理")])], 1)], 1), _vm._v(" "), _c('div', {
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
      "label-width": "80px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "原料名称"
    }
  }, [_c('el-input', {
    model: {
      value: (_vm.form.materialName),
      callback: function($$v) {
        _vm.$set(_vm.form, "materialName", $$v)
      },
      expression: "form.materialName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "原料编码"
    }
  }, [_c('el-input', {
    attrs: {
      "disabled": "",
      "placehoder": "自动生成"
    },
    model: {
      value: (_vm.form.materialCode),
      callback: function($$v) {
        _vm.$set(_vm.form, "materialCode", $$v)
      },
      expression: "form.materialCode"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "利用率(%)"
    }
  }, [_c('el-slider', {
    attrs: {
      "min": 50,
      "max": 100,
      "show-input": true
    },
    model: {
      value: (_vm.form.utilizationRatio),
      callback: function($$v) {
        _vm.$set(_vm.form, "utilizationRatio", $$v)
      },
      expression: "form.utilizationRatio"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "保质期"
    }
  }, [_c('el-input', {
    staticStyle: {
      "width": "160px"
    },
    model: {
      value: (_vm.form.storageLifeNum),
      callback: function($$v) {
        _vm.$set(_vm.form, "storageLifeNum", $$v)
      },
      expression: "form.storageLifeNum"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "80px"
    },
    attrs: {
      "slot": "append",
      "placeholder": "请选择"
    },
    slot: "append",
    model: {
      value: (_vm.form.storageLifeUnit),
      callback: function($$v) {
        _vm.$set(_vm.form, "storageLifeUnit", $$v)
      },
      expression: "form.storageLifeUnit"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "天",
      "value": "D"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "月",
      "value": "M"
    }
  })], 1)], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "采购单位"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "80px"
    },
    attrs: {
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.specUnit),
      callback: function($$v) {
        _vm.$set(_vm.form, "specUnit", $$v)
      },
      expression: "form.specUnit"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "无",
      "value": "无"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "包",
      "value": "包"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "箱",
      "value": "箱"
    }
  })], 1), _vm._v(" "), (_vm.form.specUnit != '无') ? _c('span', [_c('el-input', {
    staticStyle: {
      "width": "140px"
    },
    model: {
      value: (_vm.form.specQty),
      callback: function($$v) {
        _vm.$set(_vm.form, "specQty", $$v)
      },
      expression: "form.specQty"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "80px"
    },
    attrs: {
      "slot": "append",
      "placeholder": "请选择"
    },
    slot: "append",
    model: {
      value: (_vm.form.stockUnit),
      callback: function($$v) {
        _vm.$set(_vm.form, "stockUnit", $$v)
      },
      expression: "form.stockUnit"
    }
  }, [_c('el-option', {
    key: "A",
    attrs: {
      "label": "个",
      "value": "个"
    }
  }), _vm._v(" "), _c('el-option', {
    key: "KG",
    attrs: {
      "label": "千克",
      "value": "千克"
    }
  }), _vm._v(" "), _c('el-option', {
    key: "G",
    attrs: {
      "label": "克",
      "value": "克"
    }
  })], 1)], 1), _vm._v(" "), _c('span', [_vm._v("说明:规格单位，如10KG/包，20个/箱等")])], 1) : _vm._e()], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "使用单位"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "80px"
    },
    attrs: {
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.stockUnit),
      callback: function($$v) {
        _vm.$set(_vm.form, "stockUnit", $$v)
      },
      expression: "form.stockUnit"
    }
  }, [_c('el-option', {
    key: "A",
    attrs: {
      "label": "个",
      "value": "个"
    }
  }), _vm._v(" "), _c('el-option', {
    key: "KG",
    attrs: {
      "label": "千克",
      "value": "千克"
    }
  }), _vm._v(" "), _c('el-option', {
    key: "G",
    attrs: {
      "label": "克",
      "value": "克"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "搜索短语"
    }
  }, [_c('el-input', {
    model: {
      value: (_vm.form.searchKey),
      callback: function($$v) {
        _vm.$set(_vm.form, "searchKey", $$v)
      },
      expression: "form.searchKey"
    }
  })], 1), _vm._v(" "), _c('el-row', [_c('el-col', [_c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("提交")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onCancel
    }
  }, [_vm._v("取消")])], 1)], 1)], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ })

});