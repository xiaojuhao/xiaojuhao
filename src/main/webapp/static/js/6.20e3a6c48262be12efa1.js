webpackJsonp([6],{

/***/ 610:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(675)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(668),
  /* template */
  __webpack_require__(673),
  /* scopeId */
  "data-v-6eff4f49",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 643:
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

/***/ 644:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(643),
  /* template */
  null,
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 648:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(650),
  /* template */
  __webpack_require__(653),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 650:
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
      let $data = this.$data;
      __WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].getAllStoreList().then(value => {
        $data.allValues = value;
        $data.selectedCode = this.$props.value;
      });
    },
    enterkey(e) {},
    filterMethod(input) {
      let $data = this.$data;
      setTimeout(() => {
        $data.valuesShow = $data.allValues.filter(item => {
          var key = [item.storeCode, item.storeName, item.searchKey].join(',');
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
          if (this.excludesMap[item.storeCode]) {
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

/***/ 653:
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
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.valuesShow), function(item) {
    return _c('el-option', {
      key: item.storeCode,
      attrs: {
        "label": item.storeName,
        "value": item.storeCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 661:
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


/* harmony default export */ __webpack_exports__["default"] = ({
	data() {
		return {
			allValues: [],
			valuesShow: [],
			selectedCode: ''
		};
	},
	mounted() {
		let $data = this.$data;
		__WEBPACK_IMPORTED_MODULE_0__bus__["a" /* api */].getAllWarehouseList().then(list => {
			$data.allValues = list;
		});
	},
	methods: {
		setValue() {
			this.$emit("setValue", this.selectedCode);
		},
		filterMethod(input) {
			let $data = this.$data;
			setTimeout(() => {
				$data.valuesShow = $data.allValues.filter(item => {
					var key = item.searchKey;
					if (!key || key.indexOf(input) >= 0) {
						return true;
					}
					return false;
				});
			}, 10);
		},
		visualChange(visible) {
			if (visible) this.$data.valuesShow = this.$data.allValues;
		}
	}
});

/***/ }),

/***/ 664:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(661),
  /* template */
  __webpack_require__(666),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 666:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('el-select', {
    attrs: {
      "placeholder": "请选择"
    },
    on: {
      "change": _vm.setValue,
      "visible-change": _vm.visualChange
    },
    model: {
      value: (_vm.selectedCode),
      callback: function($$v) {
        _vm.selectedCode = $$v
      },
      expression: "selectedCode"
    }
  }, _vm._l((_vm.allValues), function(item) {
    return _c('el-option', {
      key: item.warehouseCode,
      attrs: {
        "label": item.warehouseName,
        "value": item.warehouseCode
      }
    })
  }))], 1)
},staticRenderFns: []}

/***/ }),

/***/ 668:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue__ = __webpack_require__(644);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__common_config_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_StoreSelection__ = __webpack_require__(648);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_StoreSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__common_StoreSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__common_WarehouseSelection__ = __webpack_require__(664);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__common_WarehouseSelection___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3__common_WarehouseSelection__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__common_bus__ = __webpack_require__(263);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
    data() {
        return {
            tableData: [],
            cur_page: 1,
            pageSize: 5,
            totalRows: 0,
            multipleSelection: [],
            loadingState: false,
            query: {
                materialCode: '',
                stockType: '2',
                storeCode: ''
            },
            warehouseSelection: [],
            materialSelection: [],
            showOutStock: false,
            radio: ''
        };
    },
    components: {
        StoreSelection: __WEBPACK_IMPORTED_MODULE_2__common_StoreSelection___default.a,
        WarehouseSelection: __WEBPACK_IMPORTED_MODULE_3__common_WarehouseSelection___default.a
    },
    created() {},
    mounted() {
        this.getData();
        var $this = this;
        __WEBPACK_IMPORTED_MODULE_1_jquery___default.a.ajax({
            url: __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default.a.server + "/warehouse/queryWarehouses",
            dataType: 'jsonp'
        }).then(resp => {
            $this.warehouseSelection = resp.value.values;
        });
    },
    activated() {},
    computed: {
        data() {
            const self = this;
            return self.tableData.filter(function (d) {
                return d;
            });
        }
    },
    methods: {
        handleCurrentChange(val) {
            this.cur_page = val;
            this.getData();
        },
        getData() {
            let self = this;
            self.$data.loadingState = true;
            let param = {
                pageSize: self.$data.pageSize,
                pageNo: self.$data.cur_page,
                materialCode: self.$data.query.materialCode,
                warehouseCode: self.$data.query.warehouseCode,
                stockType: self.$data.query.stockType
            };
            __WEBPACK_IMPORTED_MODULE_4__common_bus__["a" /* api */].queryMaterialsStockPage(param).then(page => {
                self.tableData = page.values;
                self.totalRows = page.totalRows;
            }).fail(function (resp) {
                self.$message.error("请求出错");
            }).always(function (resp) {
                self.$data.loadingState = false;
            });
        },
        search() {
            this.tableData = [];
            this.getData();
        },
        formatStockType(row, column) {
            return row.stockType == 1 ? "总库" : "分库";
        },
        filterTag(value, row) {
            return row.tag === value;
        },
        outstock(index, item) {
            // this.$message('编辑第'+(index+1)+'行');
            //console.log(row)
            this.$router.push({ path: "/outStock", query: { stockId: item.id } });
            // this.$data.showOutStock=true;
        },
        instock(index, item) {
            this.$router.push({ path: "/inStock", query: { stockId: item.id } });
        },
        handleEdit(index, row) {
            this.$message('编辑第' + (index + 1) + '行');
        },
        handleDelete(index, row) {
            this.$message.error('删除第' + (index + 1) + '行');
        },
        delAll() {
            const self = this,
                  length = self.multipleSelection.length;
            let str = '';
            self.del_list = self.del_list.concat(self.multipleSelection);
            for (let i = 0; i < length; i++) {
                str += self.multipleSelection[i].name + ' ';
            }
            self.$message.error('删除了' + str);
            self.multipleSelection = [];
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        handleSelect(item) {
            this.$data.query.name = item.value;
        },
        querySearch(queryString, cb) {
            var data = [];
            __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default.a.search({ w: queryString }, resp => {
                data = resp.values;
            });
            cb(data);
        },
        remoteMethod(query) {
            if (query !== '') {
                this.loading = true;
                setTimeout(() => {
                    this.loading = false;
                    __WEBPACK_IMPORTED_MODULE_0__common_config_vue___default.a.search({ w: query }, resp => {
                        this.materialSelection = resp.value;
                    });
                }, 200);
            } else {
                this.materialSelection = [];
            }
        }
    }
});

/***/ }),

/***/ 671:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".handle-box[data-v-6eff4f49]{margin-bottom:20px;margin-top:20px}.handle-select[data-v-6eff4f49]{width:120px}.handle-input[data-v-6eff4f49]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 673:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "handle-box"
  }, [_c('el-row', {
    attrs: {
      "gutter": 4
    }
  }, [_c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('StoreSelection')], 1), _vm._v(" "), _c('el-col', {
    attrs: {
      "span": 4
    }
  }, [_c('WarehouseSelection')], 1), _vm._v(" "), _c('el-button', {
    attrs: {
      "type": "primary",
      "icon": "search"
    },
    on: {
      "click": _vm.search
    }
  }, [_vm._v("搜索")])], 1)], 1), _vm._v(" "), _c('el-table', {
    directives: [{
      name: "loading",
      rawName: "v-loading",
      value: (_vm.loadingState),
      expression: "loadingState"
    }],
    staticStyle: {
      "width": "100%"
    },
    attrs: {
      "data": _vm.data,
      "border": "",
      "element-loading-text": "拼命加载中",
      "element-loading-spinner": "el-icon-loading",
      "element-loading-background": "rgb(0, 0, 0, 0.8)"
    }
  }, [_c('el-table-column', {
    attrs: {
      "type": "expand"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(props) {
        return [_vm._v("\n             【待实现】展示" + _vm._s(props.row.materialName) + "最近几条出库记录\n            ")]
      }
    }])
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "materialCode",
      "label": "原料编码",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "materialName",
      "label": "原料名称",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "currStock",
      "label": "当前库存",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "usedStock",
      "label": "已用数量",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "stockUnit",
      "label": "库存单位",
      "width": "100"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "cabinName",
      "label": "仓库",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "modifier",
      "label": "修改人",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "fixed": "right",
      "width": "150"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "primary"
          },
          on: {
            "click": function($event) {
              _vm.outstock(scope.$index, scope.row)
            }
          }
        }, [_vm._v("出库")])]
      }
    }])
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "pagination"
  }, [_c('el-pagination', {
    attrs: {
      "layout": "prev, pager, next",
      "total": _vm.totalRows,
      "page-size": _vm.pageSize
    },
    on: {
      "current-change": _vm.handleCurrentChange
    }
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 675:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(671);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("786f65ab", content, true);

/***/ })

});