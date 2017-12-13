webpackJsonp([36],{

/***/ 1018:
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
  }), _vm._v(" 系统管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("角色管理")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("角色编辑")])], 1)], 1), _vm._v(" "), _c('div', {
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
      "label": "角色名称"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "角色名称"
    },
    model: {
      value: (_vm.form.roleName),
      callback: function($$v) {
        _vm.$set(_vm.form, "roleName", $$v)
      },
      expression: "form.roleName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "角色编码"
    }
  }, [_c('el-input', {
    attrs: {
      "disabled": _vm.codeDisabled,
      "placeholder": "角色编码"
    },
    model: {
      value: (_vm.form.roleCode),
      callback: function($$v) {
        _vm.$set(_vm.form, "roleCode", $$v)
      },
      expression: "form.roleCode"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "状态"
    }
  }, [_c('el-select', {
    staticStyle: {
      "width": "160px"
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
      "value": "0"
    }
  })], 1)], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "菜单授权"
    }
  }, [_c('el-tree', {
    ref: "tree",
    attrs: {
      "data": _vm.menuTree,
      "show-checkbox": "",
      "node-key": "id",
      "default-checked-keys": _vm.checkedKeys
    }
  })], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
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
  }, [_vm._v("返回")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1061:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(969);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("14e31d23", content, true);

/***/ }),

/***/ 641:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1061)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(930),
  /* template */
  __webpack_require__(1018),
  /* scopeId */
  "data-v-4f053439",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 930:
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


/* harmony default export */ __webpack_exports__["default"] = ({
    data: function () {
        return {
            form: {
                id: this.$route.query.ID,
                roleName: '',
                roleCode: '',
                status: '',
                menuCodesStr: ''
            },
            loadingState: false,
            menuTree: [],
            checkedKeys: []
        };
    },
    methods: {
        onSubmit() {
            this.form.menuCodesStr = this.$refs.tree.getCheckedKeys().join(',');
            this.loadingState = true;
            __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].saveRole(this.form).then(role => {
                this.$message("保存成功");
                this.form.id = role.id;
                this.form.roleName = role.roleName;
                this.form.roleCode = role.roleCode;
            }).always(() => {
                setTimeout(() => this.loadingState = false, 500);
            });
        },
        onCancel() {
            this.$router.go(-1);
        }
    },
    mounted() {
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryRoleById(this.$route.query.ID).then(role => {
            this.form.roleName = role.roleName;
            this.form.status = role.status;
            this.form.roleCode = role.roleCode;
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].menuTree().then(menus => {
            this.menuTree = menus;
        });
        __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].queryRoleMenuByRoleId(this.$route.query.ID).then(list => {
            list.forEach(item => this.checkedKeys.push(item.menuCode));
        });
    },
    computed: {
        codeDisabled() {
            if (this.form.roleCode) return true;
            return false;
        }
    }
});

/***/ }),

/***/ 969:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".span-center[data-v-4f053439]{display:inline-block;width:100%;font-weight:700}.grid-content[data-v-4f053439]{min-height:1px}.el-row[data-v-4f053439]{margin-bottom:4px;&:last-child{margin-bottom:0}}.el-col[data-v-4f053439]{border-radius:4px}", ""]);

// exports


/***/ })

});