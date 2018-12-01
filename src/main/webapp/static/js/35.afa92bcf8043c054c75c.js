webpackJsonp([35],{

/***/ 1059:
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
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "供应商"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.showAddNewSupplierDialog
    }
  }, [_vm._v("绑定供应商")]), _vm._v(" "), _c('br'), _vm._v(" "), _vm._l((_vm.addedSuppliers), function(item) {
    return _c('span', {
      key: item.supplierCode
    }, [_vm._v("\n                    " + _vm._s(item.supplierName) + "(" + _vm._s(item.supplierCode) + ")   \n                ")])
  })], 2), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "门店图像"
    }
  }, [_c('el-upload', {
    staticClass: "avatar-uploader",
    attrs: {
      "action": _vm.actionUrl,
      "show-file-list": false,
      "on-success": _vm.handleAvatarSuccess,
      "before-upload": _vm.beforeAvatarUpload
    }
  }, [(_vm.form.storeImg) ? _c('img', {
    staticClass: "avatar",
    attrs: {
      "src": _vm.form.storeImg
    }
  }) : _c('i', {
    staticClass: "el-icon-plus avatar-uploader-icon"
  })])], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("保存")]), _vm._v(" "), _c('el-button', {
    on: {
      "click": _vm.onCancel
    }
  }, [_vm._v("返回")])], 1)], 1)], 1), _vm._v(" "), _c('el-dialog', {
    staticClass: "dialog",
    attrs: {
      "title": "供应商信息"
    },
    model: {
      value: (_vm.addNewSupplierDialogShow),
      callback: function($$v) {
        _vm.addNewSupplierDialogShow = $$v
      },
      expression: "addNewSupplierDialogShow"
    }
  }, [_c('el-row', [_c('el-col', [_c('el-input', {
    attrs: {
      "placeholder": "供应商名称"
    },
    model: {
      value: (_vm.supplierNameSearchWord),
      callback: function($$v) {
        _vm.supplierNameSearchWord = $$v
      },
      expression: "supplierNameSearchWord"
    }
  }), _vm._v(" "), _c('br'), _c('br'), _vm._v(" "), _vm._l((_vm.filteredSupplierList), function(item) {
    return _c('el-checkbox', {
      key: item.supplierCode,
      on: {
        "change": function($event) {
          _vm.supplierCheckChange(item)
        }
      },
      model: {
        value: (item.checked),
        callback: function($$v) {
          _vm.$set(item, "checked", $$v)
        },
        expression: "item.checked"
      }
    }, [_vm._v("\n                    " + _vm._s(item.supplierName) + "\n                ")])
  })], 2)], 1)], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 1126:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(996);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("06301404", content, true);

/***/ }),

/***/ 675:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1126)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(981),
  /* template */
  __webpack_require__(1059),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 693:
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

/***/ 694:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(693),
  /* template */
  null,
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 981:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_config_vue__ = __webpack_require__(694);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_config_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_vue__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
                defaultWarehouse: '',
                storeImg: '',
                supplierCodes: ''
            },
            actionUrl: __WEBPACK_IMPORTED_MODULE_1__common_config_vue___default.a.server + '/file/upload',
            addNewSupplierDialogShow: false,
            allSuppliers: [],
            supplierNameSearchWord: ''
        };
    },
    methods: {
        onSubmit() {
            this.form.supplierCodes = Array.from(this.addedSupplierCodeSet).join(',');
            __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].saveStore(this.form).then(value => {
                Object.assign(this.form, value);
                this.$message("保存成功");
            });
        },
        onCancel() {
            this.$router.go(-1);
        },
        handleAvatarSuccess(res, file) {
            this.form.storeImg = __WEBPACK_IMPORTED_MODULE_1__common_config_vue___default.a.server + '/file/show?image=' + res.value.filename;
        },
        beforeAvatarUpload(file) {
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isLt2M;
        },
        showAddNewSupplierDialog() {
            this.loadingState = true;
            this.queryAllSuppliers(() => {
                this.loadingState = false;
                this.addNewSupplierDialogShow = true;
            });
        },
        queryAllSuppliers(cb) {
            let dbSupplierSet = new Set();
            if (this.form.supplierCodes) {
                this.form.supplierCodes.split(",").forEach(it => dbSupplierSet.add(it));
            }
            if (this.allSuppliers.length > 0) {
                this.allSuppliers.forEach(it => {
                    if (dbSupplierSet.has(it.supplierCode)) {
                        it.checked = true;
                    } else {
                        it.checked = false;
                    }
                });
                cb && cb();
                return;
            }
            __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].querySupplierPage({
                pageSize: 2000
            }).then(page => {
                page.values.forEach(item => {
                    if (dbSupplierSet.has(item.supplierCode)) {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'checked', true);
                    } else {
                        __WEBPACK_IMPORTED_MODULE_3_vue___default.a.set(item, 'checked', false);
                    }
                });
                this.allSuppliers = page.values;
                cb && cb();
            });
        },
        supplierCheckChange(item) {
            var hasIn = this.addedSupplierCodeSet.has(item.supplierCode);
            //添加
            if (item.checked && hasIn == false) {
                this.addedSupplierCodeSet.add(item.supplierCode);
            }
            //删除
            else if (!item.checked && hasIn) {
                    this.addedSupplierCodeSet.delete(item.supplierCode);
                }
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
            this.form.supplierCodes = v.supplierCodes;
            this.queryAllSuppliers(); //查询出所有的供应商
        });
    },
    computed: {
        filteredSupplierList: function () {
            if (this.supplierNameSearchWord) {
                return this.allSuppliers.filter(it => {
                    return it.supplierName.indexOf(this.supplierNameSearchWord) >= 0;
                });
            } else {
                return this.allSuppliers;
            }
        },
        addedSuppliers: function () {
            return this.allSuppliers.filter(it => {
                return it.checked;
            });
        },
        addedSupplierCodeSet: function () {
            let set = new Set();
            this.allSuppliers.forEach(it => {
                if (it.checked) {
                    set.add(it.supplierCode);
                }
            });
            return set;
        }
    }
});

/***/ }),

/***/ 996:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".avatar-uploader .el-upload{border:1px dashed #d9d9d9;border-radius:6px;cursor:pointer;position:relative;overflow:hidden;width:178px;height:178px}.avatar-uploader .el-upload:hover{border-color:#20a0ff}.avatar-uploader-icon{font-size:28px;color:#8c939d;line-height:178px;text-align:center}.avatar-uploader-icon,.avatar .el-upload{width:178px;height:178px}", ""]);

// exports


/***/ })

});