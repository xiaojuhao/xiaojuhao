webpackJsonp([29],{

/***/ 619:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(259)(
  /* script */
  __webpack_require__(678),
  /* template */
  __webpack_require__(726),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 638:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery__ = __webpack_require__(262);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__bus__ = __webpack_require__(260);



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

var Component = __webpack_require__(259)(
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

/***/ 678:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue__ = __webpack_require__(639);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__common_config_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery__ = __webpack_require__(262);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_jquery__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
                stockUnit: '',
                canSplit: '',
                searchKey: '',
                formulaStr: '',
                storageLifeUnit: 'D',
                storageLifeNum: '',
                splitMaterials: [{ id: 1, name: 'aaaaaaa' }, { id: 2, name: 'bbbbbbb' }]
            },
            rules: {},
            loadingState: false
        };
    },
    methods: {
        onSubmit() {
            this.$data.loadingState = true;
            let self = this;
            this.$data.form.formulaStr = JSON.stringify(this.$data.form.formula);
            __WEBPACK_IMPORTED_MODULE_1_jquery___default.a.ajax({
                url: __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default.a.server + '/busi/addMaterials',
                data: this.$data.form,
                dataType: 'jsonp'
            }).then(resp => {
                self.$message.success("操作成功");
                self.$router.go(-1);
            }).always(() => {
                this.$data.loadingState = false;
            });
        },
        onCancel() {
            this.$router.go(-1);
        }
    },
    computed: {},
    created() {},
    mounted() {
        let form = this.$data.form;
        __WEBPACK_IMPORTED_MODULE_1_jquery___default.a.ajax({
            url: __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default.a.server + '/busi/queryMaterialById',
            data: { id: this.$data.form.id },
            dataType: 'jsonp'
        }).then(resp => {
            if (resp.code == 200 && resp.value) {
                let re = /(\d+)(\w)/ig;
                let v = resp.value;
                form.materialName = v.materialName;
                form.materialCode = v.materialCode;
                form.stockUnit = v.stockUnit;
                form.searchKey = v.searchKey;
                form.canSplit = v.canSplit;
                form.utilizationRatio = v.utilizationRatio;
                let r = re.exec(v.storageLife);
                if (r) {
                    form.storageLifeNum = r[1];
                    form.storageLifeUnit = r[2];
                }
                console.log(form.storageLifeNum);
            }
        });
    }
});

/***/ }),

/***/ 726:
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
      "label": "小时",
      "value": "H"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "天",
      "value": "D"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "月",
      "value": "M"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "年",
      "value": "Y"
    }
  })], 1)], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "单位"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "160px"
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
      "label": "是否可拆"
    }
  }, [_c('el-switch', {
    attrs: {
      "on-text": "是",
      "off-text": "否",
      "on-value": "Y",
      "off-value": "N"
    },
    model: {
      value: (_vm.form.canSplit),
      callback: function($$v) {
        _vm.$set(_vm.form, "canSplit", $$v)
      },
      expression: "form.canSplit"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
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