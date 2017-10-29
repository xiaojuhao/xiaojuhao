webpackJsonp([11],{

/***/ 509:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(197)(
  /* script */
  __webpack_require__(531),
  /* template */
  __webpack_require__(559),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 531:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: true,
                type: ['步步高'],
                resource: '小天才',
                desc: ''
            }
        };
    },
    methods: {
        onSubmit() {
            this.$message.success('提交成功！');
        }
    }
});

/***/ }),

/***/ 559:
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
      value: (_vm.form.name),
      callback: function($$v) {
        _vm.$set(_vm.form, "name", $$v)
      },
      expression: "form.name"
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
      value: (_vm.form.name),
      callback: function($$v) {
        _vm.$set(_vm.form, "name", $$v)
      },
      expression: "form.name"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "单位"
    }
  }, [_c('el-select', {
    attrs: {
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.region),
      callback: function($$v) {
        _vm.$set(_vm.form, "region", $$v)
      },
      expression: "form.region"
    }
  }, [_c('el-option', {
    key: "bbk",
    attrs: {
      "label": "个",
      "value": "bbk"
    }
  }), _vm._v(" "), _c('el-option', {
    key: "xtc",
    attrs: {
      "label": "斤",
      "value": "xtc"
    }
  }), _vm._v(" "), _c('el-option', {
    key: "imoo",
    attrs: {
      "label": "克",
      "value": "imoo"
    }
  }), _vm._v(" "), _c('el-option', {
    key: "imoo",
    attrs: {
      "label": "箱",
      "value": "imoo"
    }
  })], 1)], 1), _vm._v(" "), _c('el-row', [_c('el-col', [_c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("新增")]), _vm._v(" "), _c('el-button', [_vm._v("取消")])], 1)], 1)], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ })

});