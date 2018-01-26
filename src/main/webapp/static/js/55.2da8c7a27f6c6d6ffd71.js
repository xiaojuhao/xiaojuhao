webpackJsonp([55],{

/***/ 1020:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".login-wrap[data-v-6d7059c0]{position:relative;width:100%;height:100%}.ms-title[data-v-6d7059c0]{position:absolute;top:50%;width:100%;margin-top:-230px;text-align:center;font-size:30px;color:#fff}.ms-login[data-v-6d7059c0]{position:absolute;left:50%;top:50%;width:300px;height:160px;margin:-150px 0 0 -190px;padding:40px;border-radius:5px;background:#fff}.login-btn[data-v-6d7059c0]{text-align:center}.login-btn button[data-v-6d7059c0]{width:100%;height:36px}", ""]);

// exports


/***/ }),

/***/ 1089:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "login-wrap"
  }, [_c('div', {
    staticClass: "ms-title"
  }, [_vm._v("后台管理系统")]), _vm._v(" "), _c('div', {
    staticClass: "ms-login"
  }, [_c('el-form', {
    ref: "ruleForm",
    staticClass: "demo-ruleForm",
    attrs: {
      "model": _vm.ruleForm,
      "rules": _vm.rules,
      "label-width": "0px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "prop": "username"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "username"
    },
    model: {
      value: (_vm.ruleForm.username),
      callback: function($$v) {
        _vm.$set(_vm.ruleForm, "username", $$v)
      },
      expression: "ruleForm.username"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "password"
    }
  }, [_c('el-input', {
    attrs: {
      "type": "password",
      "placeholder": "password"
    },
    nativeOn: {
      "keyup": function($event) {
        if (!('button' in $event) && _vm._k($event.keyCode, "enter", 13, $event.key)) { return null; }
        _vm.submitForm('ruleForm')
      }
    },
    model: {
      value: (_vm.ruleForm.password),
      callback: function($$v) {
        _vm.$set(_vm.ruleForm, "password", $$v)
      },
      expression: "ruleForm.password"
    }
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "login-btn"
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.submitForm('ruleForm')
      }
    }
  }, [_vm._v("登录")])], 1), _vm._v(" "), _c('p', {
    staticStyle: {
      "font-size": "12px",
      "line-height": "30px",
      "color": "#999"
    }
  }, [_vm._v("Tips : " + _vm._s(_vm.tips))])], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 1150:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(1020);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("2ed0e602", content, true);

/***/ }),

/***/ 628:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(1150)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(937),
  /* template */
  __webpack_require__(1089),
  /* scopeId */
  "data-v-6d7059c0",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 937:
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


/* harmony default export */ __webpack_exports__["default"] = ({
    data: function () {
        return {
            ruleForm: {
                username: '',
                password: ''
            },
            rules: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
            },
            tips: '输入用户名和密码'
        };
    },
    methods: {
        submitForm(formName) {
            const self = this;
            self.$refs[formName].validate(valid => {
                if (valid) {
                    self.$data.tips = "登录中.....";
                    var data = {
                        userCode: self.$data.ruleForm.username,
                        password: self.$data.ruleForm.password
                    };
                    __WEBPACK_IMPORTED_MODULE_0__common_bus__["a" /* api */].signin(data).then(resp => {
                        if (resp.code != 200) {
                            self.$data.tips = resp.message;
                        } else {
                            self.$data.tips = "登录成功";
                            var userinfo = resp.value;
                            this.$store.commit('setLoginCookie', userinfo.loginCookie);
                            this.$store.commit('setUserRole', userinfo.userRole);
                            localStorage.setItem('ms_username', userinfo.userName);
                            this.$store.dispatch('loadAllData');
                            self.$router.push('/home');
                        }
                    }).fail(resp => {
                        self.$data.tips = "登录失败,请检查用户名和密码";
                    });
                    return true;
                } else {
                    return false;
                }
            });
        }
    },
    mounted() {
        //console.log(process.env)
        //console.log(this)
    }
});

/***/ })

});