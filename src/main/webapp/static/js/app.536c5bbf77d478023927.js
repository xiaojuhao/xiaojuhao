webpackJsonp([31],{

/***/ 258:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vuex__ = __webpack_require__(257);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__App__ = __webpack_require__(603);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__App___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__App__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__router__ = __webpack_require__(305);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_axios__ = __webpack_require__(263);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_axios___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_axios__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__store_store__ = __webpack_require__(306);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_element_ui__ = __webpack_require__(547);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_element_ui___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_element_ui__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_element_ui_lib_theme_default_index_css__ = __webpack_require__(560);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_element_ui_lib_theme_default_index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7_element_ui_lib_theme_default_index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_babel_polyfill__ = __webpack_require__(167);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_babel_polyfill___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_babel_polyfill__);





//import {store} from './store/store'


 // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题

//import config from 'components/common/config.vue'
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_6_element_ui___default.a);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* default */]);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.prototype.$axios = __WEBPACK_IMPORTED_MODULE_4_axios___default.a;
new __WEBPACK_IMPORTED_MODULE_0_vue___default.a({
    router: __WEBPACK_IMPORTED_MODULE_3__router__["a" /* default */],
    store: __WEBPACK_IMPORTED_MODULE_5__store_store__["a" /* default */],
    render: h => h(__WEBPACK_IMPORTED_MODULE_2__App___default.a)
}).$mount('#app');

/***/ }),

/***/ 260:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return config; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "c", function() { return http; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery__ = __webpack_require__(262);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_jquery__);


const config = {
	//server:'http://47.104.25.105:80/xiaojuhao/'
	//server:'http://localhost:8080/'
	server: "http://114.67.230.153/xiaojuhao/"
};
const http = {
	jsonp(uri, data) {
		var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: config.server + uri,
			data: data,
			dataType: 'jsonp'
		}).then(resp => df.resolve(resp)).fail(resp => df.reject(resp));
		return df.promise();
	},
	jsonp2(uri, data) {
		var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: config.server + uri,
			data: data,
			dataType: 'jsonp'
		}).then(resp => {
			if (resp.code == "200") {
				df.resolve(resp.value);
			} else {
				df.reject(resp);
			}
		});

		return df.promise();
	},
	post(uri, data) {
		var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: config.server + uri,
			data: data,
			dataType: 'jsonp'
		}).then(resp => {
			if (resp.code == "200") {
				df.resolve(resp.value);
			} else {
				df.reject(resp);
			}
		});
		return df.promise();
	}
};





const api = {
	signin(data) {
		var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
		try {
			__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
				url: config.server + '/user/login',
				data: data,
				dataType: 'jsonp'
			}).then(resp => {
				df.resolve(resp);
			});
		} catch (e) {
			df.reject({
				code: 400,
				message: '网络异常'
			});
		}

		return df.promise();
	},
	saveUser(data) {
		return http.jsonp2("/user/saveUser", data);
	},
	resetPassword(userCode) {
		return http.jsonp2("/user/resetPassword", { userCode: userCode });
	},
	getUserByCode(userCode) {
		return http.jsonp2("/user/queryUserByCode", { userCode: userCode });
	},
	getAllStoreList() {
		var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
		http.jsonp('/store/getAllStore', { pageSize: 1000 }).then(resp => {
			if (resp.code == "200") {
				var values = resp.value && resp.value.values;
				df.resolve(values);
			} else {
				df.reject(resp);
			}
		}).fail(resp => df.reject(resp));
		return df.promise();
	},
	queryStoreByCode(code) {
		return http.jsonp2("/store/getStoreByCode", { storeCode: code });
	},
	queryMyStores() {
		return http.jsonp2("/store/getMyStore", {});
	},
	outstock(data) {
		var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
		http.jsonp('/busi/outstock', data).then(resp => {
			if (resp.code == "200") {
				df.resolve(resp.value);
			} else {
				df.reject(resp);
			}
		}).fail(resp => {
			df.reject(resp);
		});
		return df.promise();
	},
	instock(data) {
		var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
		http.jsonp('/busi/instock', data).then(resp => {
			if (resp.code == "200") {
				df.resolve(resp.value);
			} else {
				df.reject(resp);
			}
		}).fail(resp => {
			df.reject(resp);
		});
		return df.promise();
	},
	addRecipes(data) {
		var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
		__WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
			url: config.server + "/recipes/addRecipes",
			data: data,
			dataType: 'jsonp'
		}).then(resp => {
			if (resp.code == "200") {
				df.resolve(resp.value);
			} else {
				df.reject(resp);
			}
		}).fail(resp => {
			df.reject(resp);
		});
		return df.promise();
	},
	queryRecipesPage(data) {
		return http.jsonp2("/recipes/queryRecipes", data);
	},
	queryAllRecipes() {
		return http.jsonp2("/recipes/queryAllRecipes", { pageSize: 1000 });
	},
	queryRecipesByCode(code) {
		return http.jsonp2("/recipes/queryRecipesByCode", { recipesCode: code });
	},
	queryMaterialsStockPage(data) {
		return http.jsonp2("/busi/queryMaterialsStock", data);
	},
	queryAllFenkuMaterialsStock(materialCode) {
		let data = {
			materialCode: materialCode,
			pageSize: 1000,
			stockType: '2'
		};
		var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
		http.jsonp2("/busi/queryMaterialsStock", data).then(page => {
			df.resolve(page.values);
		}).fail(resp => {
			df.reject(resp);
		});

		return df.promise();
	},
	queryMaterialsStockHistory(data) {
		return http.jsonp2("/busi/queryMaterialsStockHistory", data);
	},
	queryMaterialsStockById(id) {
		return http.jsonp2("/busi/queryMaterialsStockById", { id: id });
	},
	queryAllMaterials() {
		let data = {
			pageNo: 1,
			pageSize: 2000
		};
		return http.jsonp2("/busi/queryMaterials", data);
	},
	queryRecipesFormula(recipesCode) {
		let data = { recipesCode: recipesCode };
		return http.jsonp2("/recipes/queryRecipesFormula", data);
	},
	getAllWarehouse() {
		return http.jsonp2("/warehouse/queryWarehouses", { pageSize: 1000 });
	},
	getWarehouseByCode(code) {
		return http.jsonp2("/warehouse/queryWarehouseByCode", { warehouseCode: code });
	},
	queryMyWarehouse() {
		return http.jsonp2("/warehouse/queryMyWarehouse", {});
	},
	saveSupplierInfo(param) {
		return http.jsonp2("/supplier/saveSupplier", param);
	},
	querySupplierPage(param) {
		return http.jsonp2("/supplier/querySupplierPage", param);
	},
	querySupplierByCode(code) {
		return http.jsonp2("/supplier/querySupplierByCode", { supplierCode: code });
	},
	queryMaterialSupplerByCode(param) {
		return http.jsonp2("/busi/queryMaterialSupplerByCode", param);
	},
	queryAllMaterialSuppler() {
		return http.jsonp2("/busi/queryAllMaterialSuppler", {});
	}
};
/* harmony export (immutable) */ __webpack_exports__["a"] = api;


/***/ }),

/***/ 304:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
	mounted() {
		this.$store.dispatch('loadAllData');
	}
});

/***/ }),

/***/ 305:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_router__ = __webpack_require__(605);



__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]);

/* harmony default export */ __webpack_exports__["a"] = (new __WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]({
    routes: [{
        path: '/',
        redirect: '/login'
    }, {
        path: '/login',
        component: resolve => __webpack_require__.e/* require */(26).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(617)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
    }, {
        path: '/home',
        component: resolve => __webpack_require__.e/* require */(2).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(612)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe),
        children: [{
            path: '/indexPage',
            component: resolve => __webpack_require__.e/* require */(0).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(169)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/outStock',
            component: resolve => __webpack_require__.e/* require */(11).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(609)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/inStock',
            component: resolve => __webpack_require__.e/* require */(16).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(616)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/userManage',
            component: resolve => __webpack_require__.e/* require */(15).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(628)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/userManagePage',
            component: resolve => __webpack_require__.e/* require */(12).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(629)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/roleManage',
            component: resolve => __webpack_require__.e/* require */(0/* duplicate */).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(169)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // vue-datasource组件
        }, {
            path: '/sysConfig',
            component: resolve => __webpack_require__.e/* require */(23).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(627)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/materialManage',
            component: resolve => __webpack_require__.e/* require */(6).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(618)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // Vue-Quill-Editor组件
        }, {
            path: '/materialManagePage',
            component: resolve => __webpack_require__.e/* require */(29).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(619)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // Vue-Quill-Editor组件
        }, {
            path: '/recipesManage',
            component: resolve => __webpack_require__.e/* require */(4).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(622)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // Vue-Quill-Editor组件
        }, {
            path: '/recipesManagePage',
            component: resolve => __webpack_require__.e/* require */(14).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(623)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // Vue-Quill-Editor组件
        }, {
            path: '/warehouseManage',
            component: resolve => __webpack_require__.e/* require */(20).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(636)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // Vue-Core-Image-Upload组件
        }, {
            path: '/warehouseManagePage',
            component: resolve => __webpack_require__.e/* require */(27).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(637)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // Vue-Core-Image-Upload组件
        }, {
            path: '/storeManage',
            component: resolve => __webpack_require__.e/* require */(21).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(634)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // vue-schart组件
        }, {
            path: '/storeManagePage',
            component: resolve => __webpack_require__.e/* require */(28).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(635)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // vue-schart组件
        }, {
            path: '/supplierManage',
            component: resolve => __webpack_require__.e/* require */(24).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(625)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // vue-schart组件
        }, {
            path: '/supplierManagePage',
            component: resolve => __webpack_require__.e/* require */(13).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(626)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // vue-schart组件
        }, {
            path: '/inventoryOut',
            component: resolve => __webpack_require__.e/* require */(1).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(633)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe),
            children: [{
                path: "/byMaterial",
                component: resolve => __webpack_require__.e/* require */(8).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(610)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
            }, {
                path: "/byRecipes",
                component: resolve => __webpack_require__.e/* require */(9).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(611)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
            }]
        }, {
            path: '/inventoryIn',
            component: resolve => __webpack_require__.e/* require */(3).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(631)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 拖拽列表组件
        }, {
            path: '/inventoryInIndex',
            component: resolve => __webpack_require__.e/* require */(22).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(630)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 拖拽列表组件
        }, {
            path: '/inventoryInPage',
            component: resolve => __webpack_require__.e/* require */(10).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(632)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 拖拽列表组件
        }, {
            path: '/pandian',
            component: resolve => __webpack_require__.e/* require */(5).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(621)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 拖拽列表组件
        }, {
            path: '/baosun',
            component: resolve => __webpack_require__.e/* require */(19).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(613)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/diaobo',
            component: resolve => __webpack_require__.e/* require */(18).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(614)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 拖拽列表组件
        }, {
            path: '/diaobopage',
            component: resolve => __webpack_require__.e/* require */(17).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(615)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 拖拽列表组件
        }, {
            path: '/waitingTask',
            component: resolve => __webpack_require__.e/* require */(0/* duplicate */).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(169)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 拖拽列表组件
        }, {
            path: '/myTask',
            component: resolve => __webpack_require__.e/* require */(7).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(620)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 拖拽列表组件
        }, {
            path: '/reportOfStock',
            component: resolve => __webpack_require__.e/* require */(25).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(624)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }]
    }]
}));

/***/ }),

/***/ 306:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vuex__ = __webpack_require__(257);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_components_common_bus__ = __webpack_require__(260);



__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* default */]);

const state = {
    sideBarOppened: false,
    allMaterials: [],
    allStores: [],
    allRecipes: [],
    allWarehouses: [],
    allSuppliers: []
};

const getters = {
    allMaterialsMap: state => {
        var map = new Map();
        state.allMaterials.forEach(item => {
            map.set(item.materialCode, item);
        });
        return map;
    },
    allStoresMap: state => {
        var map = new Map();
        state.allStores.forEach(item => {
            map.set(item.storeCode, item);
        });
        return map;
    },
    allRecipesMap: state => {
        var map = new Map();
        state.allRecipes.forEach(item => {
            map.set(item.recipesCode, item);
        });
        return map;
    },
    allWarehousesMap: state => {
        var map = new Map();
        state.allWarehouses.forEach(item => {
            map.set(item.warehouseCode, item);
        });
        return map;
    },
    allSuppliersMap: state => {
        var map = new Map();
        state.allSuppliers.forEach(item => {
            map.set(item.suppliersCode, item);
        });
        return map;
    }
};

const mutations = {
    loadAllMaterials(state) {
        __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].queryAllMaterials().then(page => {
            state.allMaterials = page.values;
        });
    },
    loadAllStores(state) {
        __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].getAllStoreList().then(list => {
            state.allStores = list;
        });
    },
    loadAllRecipes(state) {
        __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].queryAllRecipes().then(page => {
            state.allRecipes = page.values;
        });
    },
    loadAllWarehouses(state) {
        __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].getAllWarehouse().then(page => {
            state.allWarehouses = page.values;
        });
    },
    loadAllSuppliers(state) {
        __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].querySupplierPage({
            pageSize: 1000
        }).then(page => {
            state.allSuppliers = page.values;
        });
    },
    ensureLoadAll(state) {
        if (!state.allMaterials || state.allMaterials.length == 0) {
            __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].queryAllMaterials().then(page => {
                state.allMaterials = page.values;
            });
        }
        if (!state.allStores || state.allStores.length == 0) {
            __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].getAllStoreList().then(list => {
                state.allStores = list;
            });
        }
        if (!state.allRecipes || state.allRecipes.length == 0) {
            __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].queryAllRecipes().then(page => {
                state.allRecipes = page.values;
            });
        }
        if (!state.allWarehouses || state.allWarehouses.length == 0) {
            __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].getAllWarehouse().then(page => {
                state.allWarehouses = page.values;
            });
        }
        if (!state.allSuppliers || state.allSuppliers.length == 0) {
            __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].querySupplierPage({
                pageSize: 1000
            }).then(page => {
                state.allSuppliers = page.values;
            });
        }
    }
};

const actions = {
    loadAllData({ commit }) {
        setTimeout(() => {
            commit('loadAllMaterials');
        }, 0);
        setTimeout(() => {
            commit('loadAllStores');
        }, 0);
        setTimeout(() => {
            commit('loadAllRecipes');
        }, 0);
        setTimeout(() => {
            commit('loadAllWarehouses');
        }, 0);
        setTimeout(() => {
            commit('loadAllSuppliers');
        }, 0);
    },
    ensureDataLoad({ commit }) {
        setTimeout(() => commit('ensureLoadAll'), 0);
    }
};

const modules = {};

const store = new __WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* default */].Store({
    state,
    getters,
    mutations,
    actions,
    modules
});

//export {store}
//export const store = storex
/* harmony default export */ __webpack_exports__["a"] = (store);

/***/ }),

/***/ 539:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports
exports.i(__webpack_require__(541), "");
exports.i(__webpack_require__(540), "");

// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 540:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, ".header{background-color:#242f42}.login-wrap{background:#324157}.plugins-tips{background:#eef1f6}.el-upload--text em,.plugins-tips a{color:#20a0ff}.pure-button{background:#20a0ff}", ""]);

// exports


/***/ }),

/***/ 541:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(102)(undefined);
// imports


// module
exports.push([module.i, "*{margin:0;padding:0}#app,.wrapper,body,html{width:100%;height:100%;overflow:hidden}body{font-family:Helvetica Neue,Helvetica,microsoft yahei,arial,STHeiTi,sans-serif}a{text-decoration:none}.content{background:none repeat scroll 0 0 #fff;position:absolute;left:250px;right:0;top:70px;bottom:0;width:auto;padding:40px;box-sizing:border-box;overflow-y:scroll}.crumbs{margin-bottom:20px}.pagination{margin:20px 0;text-align:right}.plugins-tips{padding:20px 10px;margin-bottom:20px}.el-button+.el-tooltip{margin-left:10px}.el-table tr:hover{background:#f6faff}.mgb20{margin-bottom:20px}.move-enter-active,.move-leave-active{transition:opacity .5s}.move-enter,.move-leave{opacity:0}.form-box{width:600px}.form-box .line{text-align:center}.el-time-panel__content:after,.el-time-panel__content:before{margin-top:-7px}.ms-doc .el-checkbox__input.is-disabled+.el-checkbox__label{color:#333;cursor:pointer}.pure-button{width:150px;height:40px;line-height:40px;text-align:center;color:#fff;border-radius:3px}.g-core-image-corp-container .info-aside{height:45px}.el-upload--text{background-color:#fff;border:1px dashed #d9d9d9;border-radius:6px;box-sizing:border-box;width:360px;height:180px;cursor:pointer;position:relative;overflow:hidden}.el-upload--text .el-icon-upload{font-size:67px;color:#97a8be;margin:40px 0 16px;line-height:50px}.el-upload--text{color:#97a8be;font-size:14px;text-align:center}.el-upload--text em{font-style:normal}.ql-container{min-height:400px}.ql-snow .ql-tooltip{transform:translateX(117.5px) translateY(10px)!important}.editor-btn{margin-top:20px}", ""]);

// exports


/***/ }),

/***/ 560:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 603:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(606)

var Component = __webpack_require__(259)(
  /* script */
  __webpack_require__(304),
  /* template */
  __webpack_require__(604),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 604:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "app"
    }
  }, [_c('router-view')], 1)
},staticRenderFns: []}

/***/ }),

/***/ 606:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(539);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(261)("2bfad938", content, true);

/***/ }),

/***/ 608:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(167);
module.exports = __webpack_require__(258);


/***/ })

},[608]);