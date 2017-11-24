webpackJsonp([25],{

/***/ 637:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(810)

var Component = __webpack_require__(263)(
  /* script */
  __webpack_require__(706),
  /* template */
  __webpack_require__(779),
  /* scopeId */
  "data-v-7f0d9018",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 706:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(264);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            loading: false,
            form: {
                userCode: this.$route.query.userCode,
                userName: '',
                password: '',
                userRole: '',
                status: '',
                password: '',
                userMobile: ''
            },
            auth_stores: [],
            auth_warehouse: [],
            myAllStoresX: [],
            myAllWarehouseX: []
        };
    },
    methods: {
        onSubmit() {
            this.$data.loading = true;
            this.form.authStores = this.auth_stores.filter(item => {
                if (this.allStoreMap.get(item)) return true;
                return false;
            }).join(',');
            this.form.authWarehouse = this.auth_warehouse.filter(item => {
                if (this.myAllWarehouseMap.get(item)) return true;
                return false;
            }).join(',');
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].saveUser(this.$data.form).then(val => {
                this.$message("提交成功");
            }).fail(resp => {
                this.$message.error(resp.message);
            }).always(() => {
                this.$data.loading = false;
            });
        },
        onCancel() {
            this.$router.go(-1);
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getUserByCode(this.$data.form.userCode).then(u => {
            this.$data.form.userName = u.userName;
            this.$data.form.userRole = u.userRole;
            this.$data.form.userMobile = u.userMobile;
            this.$data.form.status = u.status;
            if (u.authStores) {
                this.auth_stores = u.authStores.split(',');
            }
            if (u.authWarehouse) {
                this.auth_warehouse = u.authWarehouse.split(',');
            }
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMyStores().then(list => {
            this.myAllStoresX = list;
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryMyWarehouse().then(list => {
            this.myAllWarehouseX = list;
        });
    },
    computed: {
        myAllStores() {
            return this.myAllStoresX.filter(item => {
                return true;
            });
        },
        myAllWarehouse() {
            return this.myAllWarehouseX.filter(item => {
                return true;
            });
        },
        allStoreMap() {
            let map = new Map();
            this.myAllStores.forEach(item => {
                map.set(item.storeCode, item);
            });
            return map;
        },
        myAllWarehouseMap() {
            let map = new Map();
            this.myAllWarehouse.forEach(item => {
                map.set(item.warehouseCode, item);
            });
            return map;
        }
    }
});

/***/ }),

/***/ 740:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".el-input[data-v-7f0d9018]{width:50%}", ""]);

// exports


/***/ }),

/***/ 779:
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
  }), _vm._v(" 系统管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("用户管理")])], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "form-box"
  }, [_c('el-form', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loading),
      expression: "loading"
    }],
    ref: "form",
    attrs: {
      "label-width": "80px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "登录名"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "登录名"
    },
    model: {
      value: (_vm.form.userCode),
      callback: function($$v) {
        _vm.$set(_vm.form, "userCode", $$v)
      },
      expression: "form.userCode"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "用户名称"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "用户名称"
    },
    model: {
      value: (_vm.form.userName),
      callback: function($$v) {
        _vm.$set(_vm.form, "userName", $$v)
      },
      expression: "form.userName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "手机号"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "手机号"
    },
    model: {
      value: (_vm.form.userMobile),
      callback: function($$v) {
        _vm.$set(_vm.form, "userMobile", $$v)
      },
      expression: "form.userMobile"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "角色"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "150px"
    },
    attrs: {
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.userRole),
      callback: function($$v) {
        _vm.$set(_vm.form, "userRole", $$v)
      },
      expression: "form.userRole"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "管理员",
      "value": "1"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "店长",
      "value": "2"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "店员",
      "value": "3"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "仓管",
      "value": "4"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "登录密码"
    }
  }, [_c('el-input', {
    attrs: {
      "type": "password"
    },
    model: {
      value: (_vm.form.password),
      callback: function($$v) {
        _vm.$set(_vm.form, "password", $$v)
      },
      expression: "form.password"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "状态"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "150px"
    },
    attrs: {
      "placeholder": "请选择"
    },
    model: {
      value: (_vm.form.status),
      callback: function($$v) {
        _vm.$set(_vm.form, "status", $$v)
      },
      expression: "form.status"
    }
  }, [_c('el-option', {
    attrs: {
      "label": "有效",
      "value": "1"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "无效",
      "value": "2"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "授权门店"
    }
  }, [_c('el-checkbox-group', {
    model: {
      value: (_vm.auth_stores),
      callback: function($$v) {
        _vm.auth_stores = $$v
      },
      expression: "auth_stores"
    }
  }, _vm._l((_vm.myAllStores), function(item) {
    return _c('el-checkbox', {
      attrs: {
        "label": item.storeCode,
        "checked": item.checked
      }
    }, [_vm._v(_vm._s(item.storeName))])
  }))], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "授权仓库"
    }
  }, [_c('el-checkbox-group', {
    model: {
      value: (_vm.auth_warehouse),
      callback: function($$v) {
        _vm.auth_warehouse = $$v
      },
      expression: "auth_warehouse"
    }
  }, _vm._l((_vm.myAllWarehouse), function(item) {
    return _c('el-checkbox', {
      attrs: {
        "label": item.warehouseCode,
        "checked": item.checked
      }
    }, [_vm._v(_vm._s(item.warehouseName))])
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

/***/ 810:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(740);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(265)("66251d81", content, true);

/***/ })

});