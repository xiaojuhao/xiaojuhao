webpackJsonp([33],{

/***/ 1040:
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
      "label-width": "90px"
    }
  }, [_c('el-form-item', {
    staticStyle: {
      "width": "60%"
    },
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
    staticStyle: {
      "width": "60%"
    },
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
    staticStyle: {
      "width": "60%"
    },
    attrs: {
      "label": "默认利用率"
    }
  }, [_c('el-slider', {
    attrs: {
      "min": 0,
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
  })], 1)], 1), _vm._v(" "), _c('span', {
    staticClass: "span-title",
    staticStyle: {
      "text-align": "right",
      "margin-right": "10px",
      "width": "40px"
    }
  }, [_vm._v("\n                    分类\n                ")]), _vm._v(" "), _c('el-select', {
    staticStyle: {
      "width": "80px"
    },
    attrs: {
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.category),
      callback: function($$v) {
        _vm.$set(_vm.form, "category", $$v)
      },
      expression: "form.category"
    }
  }, _vm._l((_vm.categorySel), function(item) {
    return _c('el-option', {
      key: item.unitCode,
      attrs: {
        "label": item.unitName,
        "value": item.unitCode
      }
    })
  })), _vm._v(" "), _c('span', {
    staticClass: "span-title",
    staticStyle: {
      "text-align": "right",
      "margin-right": "10px"
    }
  }, [_vm._v("\n                    库存单位\n                ")]), _vm._v(" "), _c('el-select', {
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
  }, _vm._l((_vm.stockUnits), function(item) {
    return _c('el-option', {
      key: item.unitCode,
      attrs: {
        "label": item.unitName,
        "value": item.unitCode
      }
    })
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "供应商"
    }
  }, _vm._l((_vm.form.suppliers), function(item) {
    return _c('div', [_vm._v("\n                    " + _vm._s(item.supplierCode) + " " + _vm._s(item.supplierName) + "\n                ")])
  })), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "采购规格"
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary",
      "size": "mini",
      "icon": "plus"
    },
    on: {
      "click": _vm.addSpec
    }
  }, [_vm._v("添加规格")]), _vm._v("\n                备注：入库数量在采购入库的时用来计算入库的原料数量，谨慎填写\n                "), _vm._l((_vm.specList), function(spec, index) {
    return _c('div', {
      staticStyle: {
        "border": "dotted #D4D4D4 0px",
        "margin-top": "5px"
      }
    }, [_c('el-row', [_c('el-col', [_c('span', {
      staticClass: "span-title"
    }, [_vm._v("规格名称")]), _vm._v(" "), _c('span', {
      staticClass: "span-content"
    }, [_c('el-input', {
      staticStyle: {
        "width": "160px"
      },
      attrs: {
        "size": "small",
        "placeholder": "规格名称"
      },
      model: {
        value: (spec.specName),
        callback: function($$v) {
          _vm.$set(spec, "specName", $$v)
        },
        expression: "spec.specName"
      }
    })], 1), _vm._v(" "), _c('span', {
      staticClass: "span-title"
    }, [_vm._v("规格单位")]), _vm._v(" "), _c('span', {
      staticClass: "span-content"
    }, [_c('el-select', {
      staticStyle: {
        "width": "100px"
      },
      attrs: {
        "size": "small",
        "placeholder": "规格单位"
      },
      model: {
        value: (spec.specUnit),
        callback: function($$v) {
          _vm.$set(spec, "specUnit", $$v)
        },
        expression: "spec.specUnit"
      }
    }, _vm._l((_vm.purchaseUnits), function(item) {
      return _c('el-option', {
        key: item.unitCode,
        attrs: {
          "label": item.unitName,
          "value": item.unitCode
        }
      })
    }))], 1), _vm._v(" "), _c('span', {
      staticClass: "span-title"
    }, [_vm._v("入库数量")]), _vm._v(" "), _c('span', {
      staticClass: "span-content"
    }, [_c('el-input', {
      staticStyle: {
        "width": "100px"
      },
      attrs: {
        "size": "small",
        "placeholder": "入库"
      },
      model: {
        value: (spec.transRate),
        callback: function($$v) {
          _vm.$set(spec, "transRate", $$v)
        },
        expression: "spec.transRate"
      }
    }, [_c('template', {
      attrs: {
        "slot": "append"
      },
      slot: "append"
    }, [_vm._v(_vm._s(_vm.form.stockUnit))])], 2)], 1)])], 1), _vm._v(" "), _c('el-row', [_c('el-col', [_c('span', {
      staticClass: "span-title"
    }, [_vm._v("参考净重")]), _vm._v(" "), _c('span', {
      staticClass: "span-content"
    }, [_c('el-input', {
      staticStyle: {
        "width": "160px"
      },
      attrs: {
        "size": "small",
        "placeholder": "净重"
      },
      model: {
        value: (spec.weight),
        callback: function($$v) {
          _vm.$set(spec, "weight", $$v)
        },
        expression: "spec.weight"
      }
    }, [_c('template', {
      attrs: {
        "slot": "append"
      },
      slot: "append"
    }, [_vm._v("千克")])], 2)], 1), _vm._v(" "), _c('span', {
      staticClass: "span-title "
    }, [_vm._v("利用率")]), _vm._v(" "), _c('span', {
      staticClass: "span-content"
    }, [_c('el-input', {
      staticStyle: {
        "width": "90px"
      },
      attrs: {
        "size": "small",
        "placeholder": "利用率"
      },
      model: {
        value: (spec.utilizationRatio),
        callback: function($$v) {
          _vm.$set(spec, "utilizationRatio", $$v)
        },
        expression: "spec.utilizationRatio"
      }
    }, [_c('template', {
      attrs: {
        "slot": "append"
      },
      slot: "append"
    }, [_vm._v("%")])], 2)], 1), _vm._v(" "), _c('span', {
      staticClass: "span-title "
    }, [_vm._v("品牌")]), _vm._v(" "), _c('span', {
      staticClass: "span-content"
    }, [_c('el-input', {
      staticStyle: {
        "width": "100px"
      },
      attrs: {
        "size": "small",
        "placeholder": "品牌"
      },
      model: {
        value: (spec.brandName),
        callback: function($$v) {
          _vm.$set(spec, "brandName", $$v)
        },
        expression: "spec.brandName"
      }
    })], 1)])], 1), _vm._v(" "), _c('el-row', [_c('el-col', [_c('span', {
      staticClass: "span-title "
    }, [_vm._v("产地")]), _vm._v(" "), _c('span', {
      staticClass: "span-content"
    }, [_c('el-input', {
      staticStyle: {
        "width": "160px"
      },
      attrs: {
        "size": "small",
        "placeholder": "产地"
      },
      model: {
        value: (spec.homeplace),
        callback: function($$v) {
          _vm.$set(spec, "homeplace", $$v)
        },
        expression: "spec.homeplace"
      }
    })], 1), _vm._v(" "), _c('span', {
      staticClass: "span-title "
    }), _vm._v(" "), _c('span', {
      staticClass: "span-content"
    }, [_c('el-button', {
      attrs: {
        "type": "danger ",
        "size": "mini ",
        "icon": "delete "
      },
      on: {
        "click": function($event) {
          _vm.removeSpec(index)
        }
      }
    }, [_vm._v("删除")])], 1)])], 1)], 1)
  })], 2), _vm._v(" "), _c('el-form-item', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (false),
      expression: "false "
    }],
    attrs: {
      "label": "子原料 "
    }
  }, [_c('el-button', {
    attrs: {
      "type": "primary ",
      "size": "mini ",
      "icon": "plus "
    }
  }, [_vm._v("添加子原料")]), _vm._v(" "), _vm._l((_vm.subList), function(sub) {
    return _c('el-row', [_c('el-input', {
      staticStyle: {
        "width": "160px"
      },
      attrs: {
        "size": "small ",
        "placeholder": "子原料名称 "
      },
      model: {
        value: (sub.name),
        callback: function($$v) {
          _vm.$set(sub, "name ", $$v)
        },
        expression: "sub.name "
      }
    }), _vm._v(" "), _c('el-input', {
      staticStyle: {
        "width": "160px"
      },
      attrs: {
        "size": "small ",
        "placeholder": "拆分数量 "
      },
      model: {
        value: (sub.splitNum),
        callback: function($$v) {
          _vm.$set(sub, "splitNum ", $$v)
        },
        expression: "sub.splitNum "
      }
    }, [_c('el-select', {
      staticStyle: {
        "width": "80px"
      },
      attrs: {
        "slot": "append ",
        "placeholder": "单位 "
      },
      slot: "append ",
      model: {
        value: (sub.stockUnit),
        callback: function($$v) {
          _vm.$set(sub, "stockUnit ", $$v)
        },
        expression: "sub.stockUnit "
      }
    }, [_c('el-option', {
      attrs: {
        "label": "天 ",
        "value": "D "
      }
    }), _vm._v(" "), _c('el-option', {
      attrs: {
        "label": "月 ",
        "value": "M "
      }
    })], 1)], 1)], 1)
  })], 2), _vm._v(" "), _c('el-row', [_c('el-col', [_c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "primary "
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("提交")]), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary "
    },
    on: {
      "click": _vm.onCancel
    }
  }, [_vm._v("取消")])], 1)], 1)], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1102:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(982);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("70eb3c30", content, true);

/***/ }),

/***/ 631:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1102)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(935),
  /* template */
  __webpack_require__(1040),
  /* scopeId */
  "data-v-18ddeff4",
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

/***/ 935:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue__ = __webpack_require__(686);
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
                storageLifeNum: '',
                specDetail: '',
                category: '',
                suppliers: []
            },
            rules: {},
            loadingState: false,
            splitMaterials: [],
            specList: [],
            subList: [],
            purchaseUnits: [],
            stockUnits: [],
            categorySel: []
        };
    },
    methods: {
        onSubmit() {
            this.loadingState = true;
            this.form.specDetail = JSON.stringify(this.specList);
            __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].addMaterials(this.form).then(resp => {
                this.$message.success("操作成功 ");
                this.$router.go(-1);
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.loadingState = false;
            });
        },
        onCancel() {
            this.$router.go(-1);
        },
        addSpec() {
            this.specList.push({ utilizationRatio: this.form.utilizationRatio });
        },
        removeSpec(index) {
            this.specList.splice(index, 1);
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].queryMaterialById(this.form.id).then(v => {
            let re = /(\d+)(\w)/ig;
            this.form.materialName = v.materialName;
            this.form.materialCode = v.materialCode;
            this.form.stockUnit = v.stockUnit;
            this.form.searchKey = v.searchKey;
            this.form.specUnit = v.specUnit;
            this.form.specQty = v.specQty || 0;
            this.form.utilizationRatio = v.utilizationRatio;
            this.form.category = v.category;
            let r = re.exec(v.storageLife);
            if (r) {
                this.form.storageLifeNum = r[1];
                this.form.storageLifeUnit = r[2];
            }

            __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].querySpecDetailByMaterialCode(v.materialCode).then(specs => {
                this.specList = specs;
            });
            __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].querySuppliersByMaterialCodes({
                materialCodes: this.form.materialCode
            }).then(list => {
                this.form.suppliers = list;
            });
        });
        __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].queryUnitByGroup('purchase_unit_group').then(units => this.purchaseUnits = units);
        __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].queryUnitByGroup('stock_unit_group').then(units => this.stockUnits = units);
        __WEBPACK_IMPORTED_MODULE_2__common_bus__["a" /* api */].queryUnitByGroup('material_category').then(cates => this.categorySel = cates);
    }
});

/***/ }),

/***/ 982:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".form-box[data-v-18ddeff4]{width:100%}.span-title[data-v-18ddeff4]{display:-moz-inline-box;display:inline-block;width:66px}.span-content[data-v-18ddeff4]{display:-moz-inline-box;display:inline-block;width:165px}", ""]);

// exports


/***/ })

});