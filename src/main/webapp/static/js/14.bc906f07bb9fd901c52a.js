webpackJsonp([14],{

/***/ 626:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(772)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(688),
  /* template */
  __webpack_require__(748),
  /* scopeId */
  "data-v-79beffd9",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 645:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//
//
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
  props: ["excludes", "value", "context"],
  data() {
    return {
      allValues: [],
      valuesShow: [],
      selectedCode: ''
    };
  },
  mounted() {
    this.initData();
  },
  watch: {
    value(nval, oval) {
      this.initData();
    }
  },
  methods: {
    setValue() {
      this.$emit("input", this.$props.context, this.selectedCode);
    },
    initData() {
      this.allValues = this.$store.state.allMaterials;
      this.selectedCode = this.$props.value;
      // let $data = this.$data;
      // api.queryAllMaterials()
      // .then((value)=>{
      //   $data.allValues = value.values;
      //   $data.selectedCode = this.$props.value;
      // });
    },
    enterkey(e) {
      console.log(e);
    },
    filterMethod(input) {
      let $data = this.$data;
      setTimeout(() => {
        $data.valuesShow = $data.allValues.filter(item => {
          var key = [item.materialCode, item.materialName, item.searchKey].join(',');
          if (key.indexOf(input) >= 0) {
            return true;
          }
          return false;
        });
      }, 10);
    },
    visualChange(visible) {
      if (visible) {
        this.$data.valuesShow = this.$data.allValues.filter(item => {
          if (this.excludesMap[item.materialCode]) {
            return false;
          }
          return true;
        });
      }
    }
  },
  computed: {
    excludesMap() {
      let map = {};
      this.$props.excludes && this.$props.excludes.forEach(item => {
        map[item] = 1;
      });
      return map;
    }
  }
});

/***/ }),

/***/ 649:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(645),
  /* template */
  __webpack_require__(650),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 650:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('el-select', {
    attrs: {
      "placeholder": "请选择",
      "filterable": "",
      "filter-method": _vm.filterMethod
    },
    on: {
      "change": _vm.setValue,
      "visible-change": _vm.visualChange
    },
    nativeOn: {
      "keyup": function($event) {
        if (!('button' in $event) && _vm._k($event.keyCode, "enter", 13, $event.key)) { return null; }
        _vm.enterkey($event)
      }
    },
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.valuesShow), function(item) {
    return _c('el-option', {
      key: item.materialCode,
      attrs: {
        "label": item.materialName + '-' + item.materialCode,
        "value": item.materialCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 688:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__ = __webpack_require__(649);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection__);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
                supplierCode: this.$route.query.supplierCode,
                supplierName: '',
                shortName: '',
                supplierTel: '',
                supplierPhone: '',
                supplierEmail: '',
                payWay: 'alipay',
                payMode: '',
                payAccount: '',
                materialJson: ''
            },
            materials: [],
            loadingState: false
        };
    },
    methods: {
        onSubmit() {
            this.form.materialJson = JSON.stringify(this.materials);
            if (this.form.payWay != 'bank') {
                this.form.bankName = this.form.payWay;
            }
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].saveSupplierInfo(this.form).then(val => {
                this.form.supplierCode = val.supplierCode;
                this.$message("提交成功");
            }).fail(resp => {
                this.$message.error(resp.message);
            });
        },
        onCancel() {
            this.$router.go(-1);
        },
        removeMaterials(index) {
            this.materials.splice(index, 1);
        },
        addMaterialItem() {
            this.materials.push({
                materialCode: ''
            });
        },
        materialSelCallback(ctx, val) {
            let item = this.$store.getters.allMaterialsMap.get(val);
            Object.keys(item).forEach(key => {
                ctx[key] = item[key];
            });
            ctx.materialUnit = item.stockUnit;
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].querySupplierByCode(this.form.supplierCode).then(sp => {
            this.form.supplierName = sp.supplierName;
            this.form.supplierTel = sp.supplierTel;
            this.form.shortName = sp.shortName;
            this.form.supplierPhone = sp.supplierPhone;
            this.form.supplierEmail = sp.supplierEmail;
            this.form.supplierAddr = sp.supplierAddr;
            this.form.payMode = sp.payMode;
            this.form.payWay = sp.payWay;
            this.form.payAccount = sp.payAccount;
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMaterialSupplerByCode({
            supplierCode: this.form.supplierCode
        }).then(values => {
            values.forEach(item => {
                this.materials.push({ materialCode: item.materialCode });
            });
        });
    },
    computed: {
        addedMaterials() {
            let ll = [];
            this.materials.forEach(item => ll.push(item.materialCode));
            return ll;
        }
    },
    components: {
        MaterialSelection: __WEBPACK_IMPORTED_MODULE_1__common_MaterialSelection___default.a
    }
});

/***/ }),

/***/ 717:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".span-center[data-v-79beffd9]{display:inline-block;width:100%;font-weight:700}.grid-content[data-v-79beffd9]{min-height:1px}.el-row[data-v-79beffd9]{margin-bottom:4px;&:last-child{margin-bottom:0}}.el-col[data-v-79beffd9]{border-radius:4px}", ""]);

// exports


/***/ }),

/***/ 748:
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
  }), _vm._v(" 基础信息管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("供应商管理")])], 1)], 1), _vm._v(" "), _c('div', {
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
      "label-width": "100px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "供应商名称"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "供应商名称"
    },
    model: {
      value: (_vm.form.supplierName),
      callback: function($$v) {
        _vm.$set(_vm.form, "supplierName", $$v)
      },
      expression: "form.supplierName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "简称"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "简称"
    },
    model: {
      value: (_vm.form.shortName),
      callback: function($$v) {
        _vm.$set(_vm.form, "shortName", $$v)
      },
      expression: "form.shortName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "供应商代码"
    }
  }, [_c('el-input', {
    attrs: {
      "disabled": "",
      "placeholder": "供应商代码"
    },
    model: {
      value: (_vm.form.supplierCode),
      callback: function($$v) {
        _vm.$set(_vm.form, "supplierCode", $$v)
      },
      expression: "form.supplierCode"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "供应商地址"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "供应商手机"
    },
    model: {
      value: (_vm.form.supplierAddr),
      callback: function($$v) {
        _vm.$set(_vm.form, "supplierAddr", $$v)
      },
      expression: "form.supplierAddr"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "供应商手机"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "供应商手机"
    },
    model: {
      value: (_vm.form.supplierPhone),
      callback: function($$v) {
        _vm.$set(_vm.form, "supplierPhone", $$v)
      },
      expression: "form.supplierPhone"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "供应商电话"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "供应商电话"
    },
    model: {
      value: (_vm.form.supplierTel),
      callback: function($$v) {
        _vm.$set(_vm.form, "supplierTel", $$v)
      },
      expression: "form.supplierTel"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "供应商邮箱"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "供应商邮箱"
    },
    model: {
      value: (_vm.form.supplierEmail),
      callback: function($$v) {
        _vm.$set(_vm.form, "supplierEmail", $$v)
      },
      expression: "form.supplierEmail"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "结账模式"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "150px"
    },
    attrs: {
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.payMode),
      callback: function($$v) {
        _vm.$set(_vm.form, "payMode", $$v)
      },
      expression: "form.payMode"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "现结",
      "value": "ByNow"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "周结",
      "value": "ByWeek"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "月结",
      "value": "ByMonth"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "支付方式"
    }
  }, [
    [_c('el-radio-group', {
      model: {
        value: (_vm.form.payWay),
        callback: function($$v) {
          _vm.$set(_vm.form, "payWay", $$v)
        },
        expression: "form.payWay"
      }
    }, [_c('el-radio', {
      attrs: {
        "label": "alipay"
      }
    }, [_vm._v("支付宝")]), _vm._v(" "), _c('el-radio', {
      attrs: {
        "label": "bank"
      }
    }, [_vm._v("银行")])], 1)]
  ], 2), _vm._v(" "), (_vm.form.payWay == 'bank') ? _c('el-form-item', {
    attrs: {
      "label": "银行信息"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "银行信息"
    },
    model: {
      value: (_vm.form.bankName),
      callback: function($$v) {
        _vm.$set(_vm.form, "bankName", $$v)
      },
      expression: "form.bankName"
    }
  })], 1) : _vm._e(), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "账户信息"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "账户信息"
    },
    model: {
      value: (_vm.form.payAccount),
      callback: function($$v) {
        _vm.$set(_vm.form, "payAccount", $$v)
      },
      expression: "form.payAccount"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "供应原料"
    }
  }, [_c('el-row', {
    attrs: {
      "gutter": 5
    }
  }, [_c('el-col', {
    attrs: {
      "span": 12
    }
  }, [_c('span', {
    staticClass: "span-center"
  }, [_vm._v("原料")])]), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 3
    }
  }, [_c('span', {
    staticClass: "span-center"
  }, [_vm._v("操作")])])], 1), _vm._v(" "), _vm._l((_vm.materials), function(ff, index) {
    return _c('el-row', {
      attrs: {
        "gutter": 5
      }
    }, [_c('el-col', {
      attrs: {
        "span": 12
      }
    }, [_c('MaterialSelection', {
      attrs: {
        "value": ff.materialCode,
        "context": ff,
        "excludes": _vm.addedMaterials
      },
      on: {
        "input": _vm.materialSelCallback
      }
    })], 1), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 3
      }
    }, [_c('div', {
      staticClass: "grid-content"
    }, [_c('el-button', {
      attrs: {
        "icon": "delete",
        "size": "small"
      },
      on: {
        "click": function($event) {
          _vm.removeMaterials(index)
        }
      }
    })], 1)])], 1)
  }), _vm._v(" "), _c('el-row', [_c('el-col', {
    attrs: {
      "span": 24
    }
  }, [_c('el-button', {
    attrs: {
      "type": "success"
    },
    on: {
      "click": _vm.addMaterialItem
    }
  }, [_vm._v("增加原料")])], 1)], 1)], 2), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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

/***/ 772:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(717);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("762e2cf2", content, true);

/***/ })

});