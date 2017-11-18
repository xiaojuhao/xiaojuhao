webpackJsonp([16],{

/***/ 619:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(682),
  /* template */
  __webpack_require__(733),
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

/***/ 639:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
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

/***/ 640:
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
      this.$emit("input", this.selectedCode, this.$props.context);
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

/***/ 641:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(640),
  /* template */
  __webpack_require__(642),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 642:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('el-select', {
    attrs: {
      "placeholder": "请选择",
      "filterable": "",
      "clearable": "",
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

/***/ 682:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_MaterialSelection__ = __webpack_require__(641);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_MaterialSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__common_MaterialSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_config_vue__ = __webpack_require__(639);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1__common_config_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__common_bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
                storageLifeNum: ''

            },
            rules: {},
            loadingState: false,
            splitMaterials: [],
            highThreshold: 0,
            lowThreashold: 0
        };
    },
    methods: {
        onSubmit() {
            this.loadingState = true;
            let self = this;
            this.form.splitMaterialsStr = JSON.stringify(this.splitMaterials);
            this.form.warningThreshold = JSON.stringify({ high: this.highThreshold, low: this.lowThreashold });
            __WEBPACK_IMPORTED_MODULE_3__common_bus__["a" /* api */].addMaterials(this.form).then(resp => {
                self.$message.success("操作成功");
                self.$router.go(-1);
            }).always(() => {
                this.loadingState = false;
            });
        },
        onCancel() {
            this.$router.go(-1);
        },
        addSplitMaterial() {
            this.splitMaterials.push({
                id: 0,
                materialName: '',
                materialCode: '',
                splitAmt: 0
            });
        },
        onSelectMaterial(value, ctx) {
            ctx.materialCode = value;
        }
    },
    computed: {},
    created() {},
    mounted() {
        let form = this.$data.form;
        __WEBPACK_IMPORTED_MODULE_2_jquery___default.a.ajax({
            url: __WEBPACK_IMPORTED_MODULE_1__common_config_vue___default.a.server + '/busi/queryMaterialById',
            data: { id: this.$data.form.id },
            dataType: 'jsonp'
        }).then(resp => {
            console.log(resp);
            if (resp.code == 200 && resp.value) {
                let re = /(\d+)(\w)/ig;
                let v = resp.value;
                form.materialName = v.materialName;
                form.materialCode = v.materialCode;
                form.stockUnit = v.stockUnit;
                form.searchKey = v.searchKey;
                form.canSplit = v.canSplit;
                form.utilizationRatio = v.utilizationRatio;
                if (v.warningThreshold) {
                    let threshold = JSON.parse(v.warningThreshold);
                    this.lowThreashold = threshold.low;
                    this.highThreshold = threshold.high;
                }

                let r = re.exec(v.storageLife);
                if (r) {
                    form.storageLifeNum = r[1];
                    form.storageLifeUnit = r[2];
                }
                __WEBPACK_IMPORTED_MODULE_3__common_bus__["a" /* api */].queryMaterialSplitByMaterialCode(this.form.materialCode).then(list => {
                    console.log(list);
                    this.splitMaterials = list;
                });
            }
        });
    },
    components: {
        MaterialSelection: __WEBPACK_IMPORTED_MODULE_0__common_MaterialSelection___default.a
    }
});

/***/ }),

/***/ 733:
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
      "label": "天",
      "value": "D"
    }
  }), _vm._v(" "), _c('el-option', {
    attrs: {
      "label": "月",
      "value": "M"
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
      "label": "预警值"
    }
  }, [_c('el-row', {
    attrs: {
      "gutter": 6
    }
  }, [_c('el-col', {
    attrs: {
      "span": 6
    }
  }, [_c('el-input', {
    model: {
      value: (_vm.highThreshold),
      callback: function($$v) {
        _vm.highThreshold = $$v
      },
      expression: "highThreshold"
    }
  }, [_c('template', {
    attrs: {
      "slot": "prepend"
    },
    slot: "prepend"
  }, [_vm._v("高峰")])], 2)], 1), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 6
    }
  }, [_c('el-input', {
    model: {
      value: (_vm.lowThreashold),
      callback: function($$v) {
        _vm.lowThreashold = $$v
      },
      expression: "lowThreashold"
    }
  }, [_c('template', {
    attrs: {
      "slot": "prepend"
    },
    slot: "prepend"
  }, [_vm._v("低峰")])], 2)], 1)], 1)], 1), _vm._v(" "), _c('el-form-item', {
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
  })], 1), _vm._v(" "), _c('el-form-item', {
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
  })], 1), _vm._v(" "), (_vm.form.canSplit == 'Y') ? _c('el-form-item', {
    attrs: {
      "label": "拆分原料"
    }
  }, [_c('ul', _vm._l((_vm.splitMaterials), function(ss) {
    return _c('li', {
      staticStyle: {
        "list-style-type": "none"
      }
    }, [_c('el-row', [_c('el-col', {
      attrs: {
        "span": 6
      }
    }, [_c('MaterialSelection', {
      attrs: {
        "value": ss.materialCode,
        "context": ss
      },
      on: {
        "input": _vm.onSelectMaterial
      }
    })], 1), _vm._v(" "), _c('el-col', {
      attrs: {
        "span": 6
      }
    }, [_c('el-input', {
      model: {
        value: (ss.splitAmt),
        callback: function($$v) {
          _vm.$set(ss, "splitAmt", $$v)
        },
        expression: "ss.splitAmt"
      }
    }, [_c('template', {
      attrs: {
        "slot": "prepend"
      },
      slot: "prepend"
    }, [_vm._v("每")]), _vm._v(" "), _c('template', {
      attrs: {
        "slot": "append"
      },
      slot: "append"
    }, [_vm._v("单位")])], 2)], 1)], 1)], 1)
  })), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.addSplitMaterial
    }
  }, [_vm._v("增加原料")])], 1) : _vm._e(), _vm._v(" "), _c('el-row', [_c('el-col', [_c('el-form-item', [_c('el-button', {
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