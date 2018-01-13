webpackJsonp([35],{

/***/ 1036:
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
  }), _vm._v(" 进销存")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("原料报损")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v(_vm._s(_vm.form.cabinName))]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v(_vm._s(_vm.form.materialName))])], 1)], 1), _vm._v(" "), _c('div', {
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
    attrs: {
      "disabled": ""
    },
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
      "label": "报损类型"
    }
  }, [_c('el-radio', {
    attrs: {
      "label": "1"
    },
    model: {
      value: (_vm.baosunType),
      callback: function($$v) {
        _vm.baosunType = $$v
      },
      expression: "baosunType"
    }
  }, [_vm._v("食材报损")]), _vm._v(" "), _c('el-radio', {
    attrs: {
      "label": "2"
    },
    model: {
      value: (_vm.baosunType),
      callback: function($$v) {
        _vm.baosunType = $$v
      },
      expression: "baosunType"
    }
  }, [_vm._v("采购原料报损")])], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "报损额"
    }
  }, [_c('el-select', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (_vm.baosunType == '2'),
      expression: "baosunType == '2'"
    }],
    staticStyle: {
      "width": "120px"
    },
    attrs: {
      "placeholder": "请选择"
    },
    on: {
      "change": _vm.onSelectSpec
    },
    model: {
      value: (_vm.form.specCode),
      callback: function($$v) {
        _vm.$set(_vm.form, "specCode", $$v)
      },
      expression: "form.specCode"
    }
  }, _vm._l((_vm.materialSpecSelect), function(item) {
    return _c('el-option', {
      key: item.specCode,
      attrs: {
        "label": item.specName,
        "value": item.specCode
      }
    })
  })), _vm._v(" "), _c('el-input', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (_vm.baosunType == '2'),
      expression: "baosunType == '2'"
    }],
    staticStyle: {
      "width": "120px"
    },
    on: {
      "change": _vm.onSpecAmtChange
    },
    model: {
      value: (_vm.form.specAmt),
      callback: function($$v) {
        _vm.$set(_vm.form, "specAmt", $$v)
      },
      expression: "form.specAmt"
    }
  }, [_c('template', {
    attrs: {
      "slot": "append"
    },
    slot: "append"
  }, [_vm._v("\n                        " + _vm._s(_vm.form.specUnit) + "\n                    ")])], 2), _vm._v(" "), _c('el-input', {
    staticStyle: {
      "width": "120px"
    },
    model: {
      value: (_vm.form.lossAmt),
      callback: function($$v) {
        _vm.$set(_vm.form, "lossAmt", $$v)
      },
      expression: "form.lossAmt"
    }
  }, [_c('template', {
    attrs: {
      "slot": "append"
    },
    slot: "append"
  }, [_vm._v("\n                        " + _vm._s(_vm.form.stockUnit) + "\n                    ")])], 2), _vm._v(" "), _c('div', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (_vm.baosunType == '2'),
      expression: "baosunType == '2'"
    }]
  }, [_vm._v(_vm._s(this.form.calcFormula))])], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "备注"
    }
  }, [_c('el-input', {
    attrs: {
      "placehoder": "备注"
    },
    model: {
      value: (_vm.form.remark),
      callback: function($$v) {
        _vm.$set(_vm.form, "remark", $$v)
      },
      expression: "form.remark"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "添加图片"
    }
  }, [_c('el-upload', {
    staticClass: "upload-demo",
    attrs: {
      "action": _vm.actionUrl,
      "on-remove": _vm.handleRemove,
      "accept": "image/*",
      "on-success": _vm.handleSuccess,
      "file-list": _vm.fileList,
      "data": _vm.form
    }
  }, [_c('el-button', {
    attrs: {
      "size": "small",
      "type": "primary"
    }
  }, [_vm._v("添加文件")]), _vm._v(" "), _c('div', {
    staticClass: "el-upload__tip",
    attrs: {
      "slot": "tip"
    },
    slot: "tip"
  }, [_vm._v("只能上传jpg/png文件，且不超过2MB")])], 1)], 1), _vm._v(" "), _c('el-row', [_c('el-col', [_c('el-form-item', [_c('el-button', {
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

/***/ }),

/***/ 1099:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(979);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("7ab20a51", content, true);

/***/ }),

/***/ 617:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1099)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(920),
  /* template */
  __webpack_require__(1036),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 685:
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

/***/ 686:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(685),
  /* template */
  null,
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 920:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue__ = __webpack_require__(686);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__common_config_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_vue__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
                id: this.$route.query.id,
                cabinCode: '',
                cabinName: '',
                materialName: '',
                materialCode: '',
                stockUnit: '',
                lossAmt: 0,
                busiNo: '',
                remark: '',
                specCode: '',
                specAmt: 0,
                specUnit: '',
                utilizationRatio: 100,
                transRate: 1,
                calcFormula: ''
            },
            rules: {},
            actionUrl: __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default.a.server + '/file/upload',
            fileList: [],
            loadingState: false,
            splitMaterials: [],
            baosunType: '1',
            materialSpecSelect: []

        };
    },
    methods: {
        onSubmit() {
            this.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_1__common_bus__["a" /* api */].claimLoss(this.form).then(resp => {
                this.$message.success("操作成功");
                this.$router.go(-1);
            }).always(() => {
                this.loadingState = false;
            });
        },
        onCancel() {
            this.$router.go(-1);
        },
        handleRemove(file, fileList) {},
        handleSuccess(resp, fileList) {
            this.form.busiNo = resp.value.busiNo;
        },
        querySpecDetailByMaterialCode() {
            __WEBPACK_IMPORTED_MODULE_1__common_bus__["a" /* api */].querySpecDetailByMaterialCode(this.form.materialCode).then(vals => {
                this.materialSpecSelect = vals;
            });
        },
        onSelectSpec() {
            this.materialSpecSelect.forEach(it => {
                if (it.specCode == this.form.specCode) {
                    __WEBPACK_IMPORTED_MODULE_2_vue___default.a.set(this.form, 'specUnit', it.specUnit);
                    __WEBPACK_IMPORTED_MODULE_2_vue___default.a.set(this.form, 'utilizationRatio', it.utilizationRatio);
                    __WEBPACK_IMPORTED_MODULE_2_vue___default.a.set(this.form, 'transRate', it.transRate);
                }
            });
            this.onSpecAmtChange();
        },
        onSpecAmtChange() {
            this.form.calcFormula = '';
            if (this.form.specAmt && this.form.transRate && this.form.utilizationRatio) {
                this.form.lossAmt = this.form.specAmt * this.form.transRate * this.form.utilizationRatio / 100;
                this.form.calcFormula = "计算公式:" + this.form.specAmt + '*' + this.form.transRate + '*' + this.form.utilizationRatio + '/100 =' + this.form.lossAmt;
            }
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_1__common_bus__["a" /* api */].queryMaterialsStockById(this.form.id).then(v => {
            let re = /(\d+)(\w)/ig;
            this.form.materialName = v.materialName;
            this.form.materialCode = v.materialCode;
            this.form.cabinCode = v.cabinCode;
            this.form.cabinName = v.cabinName;
            this.form.stockUnit = v.stockUnit;
            let r = re.exec(v.storageLife);
            if (r) {
                this.form.storageLifeNum = r[1];
                this.form.storageLifeUnit = r[2];
            }
            this.querySpecDetailByMaterialCode();
        });
    }
});

/***/ }),

/***/ 979:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".el-upload{height:100px;display:inline-block;text-align:center;cursor:pointer;height:40px;width:100px;border:0;display:inline}.el-upload__tip{display:inline}", ""]);

// exports


/***/ })

});