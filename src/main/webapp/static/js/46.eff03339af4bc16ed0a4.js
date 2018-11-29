webpackJsonp([46],{

/***/ 1018:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".el-input[data-v-638b4d56]{width:80%}", ""]);

// exports


/***/ }),

/***/ 1084:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_vm._v("个人信息管理")])], 1)], 1), _vm._v(" "), _c('div', {
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
      "label-width": "100px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "登录名"
    }
  }, [_vm._v("\n                " + _vm._s(_vm.form.userCode) + "\n            ")]), _vm._v(" "), _c('el-form-item', {
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
      "label": "确认登录密码"
    }
  }, [_c('el-input', {
    attrs: {
      "type": "password"
    },
    model: {
      value: (_vm.form.passwordConfirm),
      callback: function($$v) {
        _vm.$set(_vm.form, "passwordConfirm", $$v)
      },
      expression: "form.passwordConfirm"
    }
  })], 1), _vm._v(" "), (_vm.form.isSu == '0') ? _c('el-form-item', {
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
      key: item.storeCode,
      attrs: {
        "disabled": "",
        "label": item.storeCode,
        "checked": item.checked
      }
    }, [_vm._v(_vm._s(item.storeName))])
  }))], 1) : _vm._e(), _vm._v(" "), (_vm.form.isSu == '0') ? _c('el-form-item', {
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
      key: item.warehouseCode,
      attrs: {
        "disabled": "",
        "label": item.warehouseCode,
        "checked": item.checked
      }
    }, [_vm._v(_vm._s(item.warehouseName))])
  }))], 1) : _vm._e(), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("修改")]), _vm._v(" "), _c('el-button', {
    on: {
      "click": _vm.onCancel
    }
  }, [_vm._v("返回")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1148:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1018);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("a1cff366", content, true);

/***/ }),

/***/ 666:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1148)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(972),
  /* template */
  __webpack_require__(1084),
  /* scopeId */
  "data-v-638b4d56",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 972:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
                userCode: '',
                userName: '',
                password: '      ',
                passwordConfirm: '      ',
                userRoleStr: '',
                status: '',
                userMobile: '',
                isSu: '0'
            },
            userRole: [],
            auth_stores: [],
            auth_warehouse: [],
            myAllStoresX: [],
            myAllWarehouseX: [],
            allRoles: []
        };
    },
    methods: {
        onSubmit() {
            if (this.form.password != this.form.passwordConfirm) {
                this.$message.error("登录密码和确认密码不一致,请重新输入");
                return;
            }
            this.$data.loading = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].modifyMyProfile(this.$data.form).then(val => {
                this.$message("修改成功");
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
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].loginInfo().then(u => {
            this.form.userCode = u.userCode;
            this.form.userName = u.userName;
            this.form.userMobile = u.userMobile;
            this.form.status = u.status;
            this.form.isSu = u.isSu || '0';
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
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryRolesPage({ pageSize: 1000 }).then(page => {
            this.allRoles = page.values;
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].getUserRoles(this.$route.query.userCode).then(list => {
            list.forEach(item => {
                this.userRole.push(item.roleCode);
            });
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

/***/ })

});