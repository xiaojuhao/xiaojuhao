webpackJsonp([68],{

/***/ 175:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_router__ = __webpack_require__(607);



__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]);

/* harmony default export */ __webpack_exports__["a"] = (new __WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]({
    routes: [{
        path: '/',
        redirect: '/login'
    }, {
        path: '/login',
        component: resolve => __webpack_require__.e/* require */(56).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(628)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
    }, {
        path: '/home',
        component: resolve => __webpack_require__.e/* require */(6).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(615)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe),
        children: [{
            path: '/',
            component: resolve => __webpack_require__.e/* require */(0).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(266)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/outStock',
            component: resolve => __webpack_require__.e/* require */(14).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(612)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/inStock',
            component: resolve => __webpack_require__.e/* require */(39).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(623)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/userManage',
            component: resolve => __webpack_require__.e/* require */(36).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(664)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/userProfile',
            component: resolve => __webpack_require__.e/* require */(46).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(666)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/userManagePage',
            component: resolve => __webpack_require__.e/* require */(47).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(665)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/roleManage',
            component: resolve => __webpack_require__.e/* require */(51).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(658)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/roleEdit',
            component: resolve => __webpack_require__.e/* require */(52).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(657)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/sysConfig',
            component: resolve => __webpack_require__.e/* require */(48).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(663)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/materialManage',
            component: resolve => __webpack_require__.e/* require */(32).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(631)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/materialManagePage',
            component: resolve => __webpack_require__.e/* require */(38).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(632)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/materialRequireManage',
            component: resolve => __webpack_require__.e/* require */(13).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(633)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/materialWarningSet',
            component: resolve => __webpack_require__.e/* require */(12).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(634)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/recipesManage',
            component: resolve => __webpack_require__.e/* require */(15).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(651)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) //
        }, {
            path: '/recipesSync',
            component: resolve => __webpack_require__.e/* require */(16).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(653)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) //
        }, {
            path: '/recipesManagePage',
            component: resolve => __webpack_require__.e/* require */(30).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(652)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/warehouseManage',
            component: resolve => __webpack_require__.e/* require */(41).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(676)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/warehouseManagePage',
            component: resolve => __webpack_require__.e/* require */(34).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(677)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) //
        }, {
            path: '/storeManage',
            component: resolve => __webpack_require__.e/* require */(42).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(674)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/storeManagePage',
            component: resolve => __webpack_require__.e/* require */(35).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(675)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/supplierManage',
            component: resolve => __webpack_require__.e/* require */(49).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(661)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/supplierManagePage',
            component: resolve => __webpack_require__.e/* require */(29).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(662)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/inventoryOut',
            component: resolve => __webpack_require__.e/* require */(3).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(673)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe),
            children: [{
                path: "/byMaterial",
                component: resolve => __webpack_require__.e/* require */(9).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(613)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
            }, {
                path: "/byRecipes",
                component: resolve => __webpack_require__.e/* require */(10).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(614)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
            }]
        }, {
            path: '/inventoryIn',
            component: resolve => __webpack_require__.e/* require */(45).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(667)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/inventoryInNew',
            component: resolve => __webpack_require__.e/* require */(17).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(671)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/inventoryInModify',
            component: resolve => __webpack_require__.e/* require */(18).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(670)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/inventoryInConfirm',
            component: resolve => __webpack_require__.e/* require */(44).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(668)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/inventoryInIndex',
            component: resolve => __webpack_require__.e/* require */(43).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(669)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/inventoryInPage',
            component: resolve => __webpack_require__.e/* require */(11).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(672)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/inventoryDetail',
            component: resolve => __webpack_require__.e/* require */(59).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(624)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/inventoryPaidNew',
            component: resolve => __webpack_require__.e/* require */(28).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(626)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/inventoryPaid',
            component: resolve => __webpack_require__.e/* require */(58).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(625)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/inventoryPaidPage',
            component: resolve => __webpack_require__.e/* require */(57).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(627)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/pandian',
            component: resolve => __webpack_require__.e/* require */(55).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(640)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/pandianPage',
            component: resolve => __webpack_require__.e/* require */(4).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(642)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/pandianNewPage',
            component: resolve => __webpack_require__.e/* require */(54).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(641)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/baosun',
            component: resolve => __webpack_require__.e/* require */(63).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(616)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/baosunPage',
            component: resolve => __webpack_require__.e/* require */(5).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(618)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/baosunDetail',
            component: resolve => __webpack_require__.e/* require */(40).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(617)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/diaobo',
            component: resolve => __webpack_require__.e/* require */(62).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(619)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/diaoboPage',
            component: resolve => __webpack_require__.e/* require */(33).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(622)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/diaoboHandle',
            component: resolve => __webpack_require__.e/* require */(61).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(620)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/diaoboHandlePage',
            component: resolve => __webpack_require__.e/* require */(60).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(621)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/waitingTask',
            component: resolve => __webpack_require__.e/* require */(0/* duplicate */).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(266)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/myTask',
            component: resolve => __webpack_require__.e/* require */(7).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(638)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/myPurchaseOrder',
            component: resolve => __webpack_require__.e/* require */(23).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(637)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/myLossApply',
            component: resolve => __webpack_require__.e/* require */(24).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(636)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/myAllocate',
            component: resolve => __webpack_require__.e/* require */(25).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(635)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe) // 
        }, {
            path: '/reportOfStock',
            component: resolve => __webpack_require__.e/* require */(20).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(655)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/reportStatistics',
            component: resolve => __webpack_require__.e/* require */(19).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(656)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/reportInventory',
            component: resolve => __webpack_require__.e/* require */(21).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(654)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/materialCheckReport',
            component: resolve => __webpack_require__.e/* require */(27).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(629)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/materialCheckReportDetail',
            component: resolve => __webpack_require__.e/* require */(26).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(630)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/purchaseOrder',
            component: resolve => __webpack_require__.e/* require */(53).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(648)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/purchaseOrderPage',
            component: resolve => __webpack_require__.e/* require */(22).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(649)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/stockHistory',
            component: resolve => __webpack_require__.e/* require */(50).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(660)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/orderManage',
            component: resolve => __webpack_require__.e/* require */(8).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(639)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/stockDailyCharts',
            component: resolve => __webpack_require__.e/* require */(1).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(659)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/recipesDailyChart',
            component: resolve => __webpack_require__.e/* require */(2).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(650)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/paymentPay',
            component: resolve => __webpack_require__.e/* require */(65).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(646)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/paymentInput',
            component: resolve => __webpack_require__.e/* require */(31).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(644)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/paymentInputDetail',
            component: resolve => __webpack_require__.e/* require */(37).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(645)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/paymentApprove',
            component: resolve => __webpack_require__.e/* require */(66).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(643)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }, {
            path: '/paymentReport',
            component: resolve => __webpack_require__.e/* require */(64).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(647)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe)
        }]
    }]
}));

/***/ }),

/***/ 176:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vuex__ = __webpack_require__(260);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_components_common_bus__ = __webpack_require__(263);



__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* default */]);

const state = {
    sideBarOppened: false,
    allMaterials: [],
    allStores: [],
    allRecipes: [],
    allWarehouses: [],
    allSuppliers: [],
    loginCookie: '',
    userRole: '',
    queryCond: {}
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
        __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].getAllStoreList().then(page => {
            state.allStores = page.values;
        });
    },
    loadAllRecipes(state) {
        __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].queryAllRecipes().then(page => {
            state.allRecipes = page.values;
        });
    },
    loadAllWarehouses(state) {
        __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].getAllWarehouseList().then(list => {
            state.allWarehouses = list;
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
            __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].getAllStoreList().then(page => {
                state.allStores = page.values;
            });
        }
        if (!state.allRecipes || state.allRecipes.length == 0) {
            __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].queryAllRecipes().then(page => {
                state.allRecipes = page.values;
            });
        }
        if (!state.allWarehouses || state.allWarehouses.length == 0) {
            __WEBPACK_IMPORTED_MODULE_2_components_common_bus__["a" /* api */].getAllWarehouseList().then(page => {
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
    },
    setLoginCookie(state, cookieValue) {
        state.loginCookie = cookieValue;
    },
    setUserRole(state, role) {
        state.userRole = role;
    },
    setQueryCond(state, cond) {
        state.queryCond = cond;
    }
};

const actions = {
    loadAllData({ commit }) {
        setTimeout(() => {
            commit('loadAllMaterials');
            commit('loadAllStores');
            commit('loadAllRecipes');
            commit('loadAllWarehouses');
            commit('loadAllSuppliers');
        }, 0);
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

/***/ 261:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vuex__ = __webpack_require__(260);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__App__ = __webpack_require__(605);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__App___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__App__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__router__ = __webpack_require__(175);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_axios__ = __webpack_require__(289);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_axios___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_axios__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__store_store__ = __webpack_require__(176);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_element_ui__ = __webpack_require__(222);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_element_ui___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_element_ui__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_element_ui_lib_theme_default_index_css__ = __webpack_require__(562);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_element_ui_lib_theme_default_index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7_element_ui_lib_theme_default_index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_babel_polyfill__ = __webpack_require__(167);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_babel_polyfill___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_babel_polyfill__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_vue_simple_uploader__ = __webpack_require__(608);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_vue_simple_uploader___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_9_vue_simple_uploader__);





//import {store} from './store/store'


 // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题

//import config from 'components/common/config.vue'

__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_6_element_ui___default.a);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* default */]);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.use(__WEBPACK_IMPORTED_MODULE_9_vue_simple_uploader___default.a);
__WEBPACK_IMPORTED_MODULE_0_vue___default.a.prototype.$axios = __WEBPACK_IMPORTED_MODULE_4_axios___default.a;
new __WEBPACK_IMPORTED_MODULE_0_vue___default.a({
    router: __WEBPACK_IMPORTED_MODULE_3__router__["a" /* default */],
    store: __WEBPACK_IMPORTED_MODULE_5__store_store__["a" /* default */],
    render: h => h(__WEBPACK_IMPORTED_MODULE_2__App___default.a)
}).$mount('#app');

/***/ }),

/***/ 263:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return config; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "d", function() { return http; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery__ = __webpack_require__(265);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__store_store__ = __webpack_require__(176);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__router_index__ = __webpack_require__(175);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_element_ui__ = __webpack_require__(222);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_element_ui___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_element_ui__);





const config = {
    //server:'http://47.104.25.105:80/xiaojuhao/'
    //server:'http://localhost:8080/'
    server: function () {
        if (window.location.href.indexOf("localhost") > 0) {
            return "http://localhost:8080";
        }
        let host = window.location.host;
        // return process.env.REMOTE_SERVER
        return "//" + host;
    }()
};
const util = {
    today() {
        let now = new Date();
        return new Date(now.getFullYear(), now.getMonth(), now.getDate());
    },
    formatDate(ms) {
        if (!ms) {
            return "";
        }
        let date = new Date(ms);
        return this.formatDateT(date);
    },
    formatDateT(date) {
        if (!date) return "";
        let year = date.getFullYear();
        let mon = date.getMonth() + 1;
        let day = date.getDate();
        let str = year;
        if (mon < 10) {
            str = str + "-0" + mon;
        } else {
            str = str + "-" + mon;
        }
        if (day < 10) {
            str = str + "-0" + day;
        } else {
            str = str + "-" + day;
        }
        return str;
    },
    formatDateTime(ms) {
        if (!ms) {
            return "";
        }
        let date = new Date(ms);
        return this.formatDateTimeT(date);
    },
    formatDateTimeT(date) {
        if (!date) return "";
        let year = date.getFullYear();
        let mon = date.getMonth() + 1;
        let day = date.getDate();
        let hours = date.getHours();
        let minutes = date.getMinutes();
        let seconds = date.getSeconds();
        let str = year;
        if (mon < 10) {
            str = str + "-0" + mon;
        } else {
            str = str + "-" + mon;
        }
        if (day < 10) {
            str = str + "-0" + day;
        } else {
            str = str + "-" + day;
        }
        if (hours < 10) {
            str = str + " 0" + hours;
        } else {
            str = str + " " + hours;
        }
        if (minutes < 10) {
            str = str + ":0" + minutes;
        } else {
            str = str + ":" + minutes;
        }
        if (seconds < 10) {
            str = str + ":0" + seconds;
        } else {
            str = str + ":" + seconds;
        }
        return str;
    },
    matchSearch(target, input) {
        target = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.trim(target);
        input = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.trim(input);
        if (!target) {
            return false;
        }
        let arr = input.split(" ");
        for (let i = 0; i < arr.length; i++) {
            let str = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.trim(arr[i]);
            if (!str) continue;
            if (target.indexOf(str) < 0) {
                return false;
            }
        }
        return true;
    }
};
/* harmony export (immutable) */ __webpack_exports__["c"] = util;

const http = {
    post(uri, data) {
        if (!data) {
            data = {};
        }
        data.requestLoginCookie = __WEBPACK_IMPORTED_MODULE_1__store_store__["a" /* default */].state.loginCookie;
        var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
        __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
            url: config.server + uri,
            data: data,
            method: 'POST',
            crossDomain: true,
            dataType: 'json'
        }).then(resp => {
            if (resp.code == "R401") {
                __WEBPACK_IMPORTED_MODULE_2__router_index__["a" /* default */].push("/login");
                return;
            }
            if (resp.code == "200") {
                df.resolve(resp.value);
            } else {
                df.reject(resp);
            }
        }).fail(resp => {});
        return df.promise();
    }
};





const api = {
    getMenu() {
        return http.post("/menu", {});
    },
    getMenu2() {
        return http.post("/menu2", {});
    },
    signin(data) {
        var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
        try {
            __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.ajax({
                url: config.server + '/user/login',
                data: data,
                crossDomain: true,
                method: 'POST',
                dataType: 'json'
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
    getMaterialFromStoreByCode(materialCode) {
        if (__WEBPACK_IMPORTED_MODULE_1__store_store__["a" /* default */].getters.allMaterialsMap) {
            return __WEBPACK_IMPORTED_MODULE_1__store_store__["a" /* default */].getters.allMaterialsMap.get(materialCode);
        }
        return null;
    },
    getSupplierFromStoreByCode(supplierCode) {
        if (__WEBPACK_IMPORTED_MODULE_1__store_store__["a" /* default */].getters.allSuppliersMap) {
            return __WEBPACK_IMPORTED_MODULE_1__store_store__["a" /* default */].getters.allSuppliersMap.get(supplierCode);
        }
        return null;
    },
    saveUser(data) {
        return http.post("/user/saveUser", data);
    },
    modifyMyProfile(data) {
        return http.post("/user/modifyMyProfile", data);
    },
    resetPassword(userCode) {
        return http.post("/user/resetPassword", { userCode: userCode });
    },
    getUserByCode(userCode) {
        return http.post("/user/queryUserByCode", { userCode: userCode });
    },
    queryUsersPage(data) {
        return http.post("/user/queryUsers", data);
    },
    loginInfo() {
        return http.post("/user/loginInfo");
    },
    getAllStoreList() {
        return http.post("/store/getAllStore", { pageSize: 1000 });
    },
    queryStoreByCode(code) {
        return http.post("/store/getStoreByCode", { storeCode: code });
    },
    queryMyStores() {
        return http.post("/store/getMyStore", {});
    },
    queryMycabins() {
        return http.post("/user/mycabins");
    },
    saveStore(data) {
        return http.post("/store/saveStore", data);
    },
    outstock(data) {
        var df = __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
        http.post('/busi/outstock', data).then(resp => {
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
        http.post('/busi/instock', data).then(resp => {
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
        return http.post("/recipes/addRecipes", data);
    },
    queryRecipesPage(data) {
        return http.post("/recipes/queryRecipes", data);
    },
    queryAllRecipes() {
        return http.post("/recipes/queryAllRecipes", { pageSize: 1000 });
    },
    queryRecipesByCode(code) {
        return http.post("/recipes/queryRecipesByCode", { recipesCode: code });
    },
    queryMaterialsStockPage(data) {
        return http.post("/busi/queryMaterialsStock", data);
    },
    queryStockByMaterialCodes(data) {
        return http.post("/busi/queryStockByMaterialCodes", data);
    },
    correctStock(data) {
        return http.post("/inventoryApply/correctStock", data);
    },
    queryMaterialsStockHistoryPage(data) {
        return http.post("/busi/queryMaterialsStockHistory", data);
    },
    queryMaterialsStockById(id) {
        return http.post("/busi/queryMaterialsStockById", { id: id });
    },
    queryAllMaterials() {
        let data = {
            pageNo: 1,
            pageSize: 2000,
            status: '1'
        };
        return http.post("/busi/queryMaterials", data);
    },
    queryMaterialsPage(data) {
        return http.post("/busi/queryMaterials", data);
    },
    queryMaterialById(id) {
        return http.post("/busi/queryMaterialById", { id: id });
    },
    addMaterials(data) {
        return http.post("/busi/addMaterials", data);
    },
    deleteMaterials(materialCode) {
        return http.post("/busi/deleteMaterials", { materialCode: materialCode });
    },
    queryMaterialSplitByMaterialCode(materialCode) {
        return http.post("/busi/queryMaterialSplitByMaterialCode", { materialCode: materialCode });
    },
    queryRecipesFormula(recipesCode) {
        let data = { recipesCode: recipesCode };
        return http.post("/recipes/queryRecipesFormula", data);
    },
    getAllWarehouseList() {
        let df = new __WEBPACK_IMPORTED_MODULE_0_jquery___default.a.Deferred();
        http.post("/warehouse/queryWarehouses", { pageSize: 1000 }).then(page => {
            df.resolve(page.values);
        }).fail(resp => {
            df.reject(resp);
        });
        return df;
    },
    queryWarehousesPage(param) {
        return http.post("/warehouse/queryWarehouses", param);
    },
    getWarehouseByCode(code) {
        return http.post("/warehouse/queryWarehouseByCode", { warehouseCode: code });
    },
    queryMyWarehouse() {
        return http.post("/warehouse/queryMyWarehouse", {});
    },
    saveWarehouse(data) {
        return http.post("/warehouse/saveWarehouse", data);
    },
    saveSupplierInfo(param) {
        return http.post("/supplier/saveSupplier", param);
    },
    querySupplierPage(param) {
        return http.post("/supplier/querySupplierPage", param);
    },
    querySupplierByCode(code) {
        return http.post("/supplier/querySupplierByCode", { supplierCode: code });
    },
    queryMaterialSupplerByCode(param) {
        return http.post("/busi/queryMaterialSupplerByCode", param);
    },
    querySuppliersByMaterialCodes(data) {
        return http.post("/busi/querySuppliersByMaterialCodes", data);
    },
    queryAllMaterialSuppler() {
        return http.post("/busi/queryAllMaterialSuppler", {});
    },
    commitPurchaseOrder(data) {
        return http.post("/inventoryApply/commitPurchaseOrder", data);
    },
    deleteInventory(applyNum) {
        return http.post("/inventoryApply/deleteInventory", { applyNum: applyNum });
    },
    queryInventoryApplyPage(data) {
        return http.post("/inventoryApply/queryInventoryApply", data);
    },
    queryMyPurchaseOrderPage(data) {
        return http.post("/inventoryApply/queryMyPurchaseOrder", data);
    },
    queryMyLossApply(data) {
        return http.post("/inventoryApply/queryMyLossApply", data);
    },
    queryMyLossApplyDetail(data) {
        return http.post("/inventoryApply/queryMyLossApplyDetail", data);
    },
    queryMyAllocate(data) {
        return http.post("/inventoryApply/queryMyAllocate", data);
    },
    queryInventoryApplyDetailByApplyNum(orderNum) {
        return http.post("/inventoryApply/queryPurchaseOrderDetail", { applyNum: orderNum });
    },
    confirmInventory(data) {
        return http.post("/inventoryApply/confirmInventory", data);
    },
    confirmInventoryDetail(data) {
        return http.post("/inventoryApply/confirmInventoryDetail", data);
    },
    modifyInventoryDetail(data) {
        return http.post("/inventoryApply/modifyInventoryDetail", data);
    },
    deleteInventoryDetail(data) {
        return http.post("/inventoryApply/deleteInventoryDetail", data);
    },
    paidInventory(data) {
        return http.post("/inventoryApply/paidInventory", data);
    },
    queryInventoryDetailPage(data) {
        return http.post("/inventoryApply/queryInventoryDetailPage", data);
    },
    commitDiaobo(data) {
        return http.post("/diaobo/commit", data);
    },
    confirmDiaobo(data) {
        return http.post("/diaobo/confirm", data);
    },
    getCabinByCode(code) {
        return http.post("/busi/getCabinByCode", { cabinCode: code });
    },
    claimLoss(data) {
        return http.post("/inventoryApply/claimLoss", data);
    },
    startCorrect(cabinCode) {
        return http.post("/inventoryApply/startCorrect", { cabinCode: cabinCode });
    },
    finishCorrect(cabinCode) {
        return http.post("/inventoryApply/finishCorrect", { cabinCode: cabinCode });
    },
    getNoticePage(data) {
        return http.post("/notice/latest", data);
    },
    saveRole(data) {
        return http.post("/role/saveRole", data);
    },
    queryRoleById(id) {
        return http.post("/role/queryRoleById", { id, id });
    },
    queryRolesPage(data) {
        return http.post("/role/queryRoles", data);
    },
    queryRoleMenuByRoleId(roleId) {
        return http.post("/role/queryRoleMenuByRoleId", { roleId: roleId });
    },
    getUserRoles(userCode) {
        return http.post("/role/getUserRoles", { userCode: userCode });
    },
    menuTree() {
        return http.post("/menuTree", {});
    },
    syncRecipes(data) {
        return http.post("/remote/syncRecipes", data);
    },
    syncOrders(data) {
        return http.post("/remote/syncOrders", data);
    },
    queryWmsOrder(data) {
        return http.post("/busi/queryWmsOrder", data);
    },
    lastSevenDaysSaleData(data) {
        return http.post("/order/lastSevenDaysSaleData", data);
    },
    queryRecentDaysTendency(data) {
        return http.post("/busi/queryRecentDaysTendency", data);
    },
    queryOrderMaterials(data) {
        return http.post("/busi/queryOrderMaterials", data);
    },
    querySpecDetailByMaterialCode(materialCode) {
        return http.post("/spec/queryDetail", { materialCode: materialCode, pageSize: 100 });
    },
    querySpecsByMaterialCodes(data) {
        return http.post("/spec/querySpecsByMaterialCodes", data);
    },
    queryUnitByGroup(groupCode) {
        return http.post("/unit/queryUnitByGroup", { groupCode: groupCode });
    },
    saveWarning(data) {
        return http.post("/storeManage/saveWarning", data);
    },
    queryMaterialRequire(data) {
        return http.post("/require/query", data);
    },
    handleRequire(data) {
        return http.post("/require/handleRequire", data);
    },
    cancelRequire(data) {
        return http.post("/require/cancelRequire", data);
    },
    createMaterialRequre(data) {
        return http.post("/require/createRequire", data);
    },
    currentStockCheck(cabinCode) {
        return http.post("/check/current", { cabinCode: cabinCode });
    },
    startCheck(cabinCode) {
        return http.post("/check/startCheck", { cabinCode: cabinCode });
    },
    finishCheck(id, cabinCode) {
        return http.post("/check/finishCheck", { id: id, cabinCode: cabinCode });
    },
    queryCheckDetail(id, cabinCode) {
        return http.post("/check/queryDetail", { id: id, cabinCode: cabinCode });
    },
    doCheckStock(data) {
        return http.post("/check/checkDetail", data);
    },
    cancelCheckDetail(data) {
        return http.post("/check/cancelCheckDetail", data);
    },
    queryPayments(data) {
        return http.post("/payment/queryPayments", data);
    },
    queryPaymentByPayNo(id, payNo) {
        return http.post("/payment/queryPaymentByPayNo", { id: id, payNo: payNo });
    },
    queryMaterialCheckMain(data) {
        return http.post("/check/queryCheckMain", data);
    },
    queryMaterialCheckDetail(data) {
        return http.post("/check/queryCheckDetail", data);
    },
    stockReport(data) {
        return http.post("/report/stockReport", data);
    },
    inventoryReport(data) {
        return http.post("/report/inventoryReport", data);
    },
    getRecentSupplier(cabinCode, materialCode) {
        return http.post("/recent/getRecent", { group: cabinCode, code: materialCode });
    },
    paidInventoryDetail(data) {
        return http.post("/inventoryApply/paidInventoryDetail", data);
    },
    getRecipesFromRemote(data) {
        return http.post("/remote/getRecipes", data);
    }
};
/* harmony export (immutable) */ __webpack_exports__["a"] = api;


/***/ }),

/***/ 308:
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
		//this.$store.dispatch('loadAllData');
	}
});

/***/ }),

/***/ 541:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports
exports.i(__webpack_require__(543), "");
exports.i(__webpack_require__(542), "");

// module
exports.push([module.i, "", ""]);

// exports


/***/ }),

/***/ 542:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, ".header{background-color:#242f42}.login-wrap{background:#324157}.plugins-tips{background:#eef1f6}.el-upload--text em,.plugins-tips a{color:#20a0ff}.pure-button{background:#20a0ff}", ""]);

// exports


/***/ }),

/***/ 543:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(103)(undefined);
// imports


// module
exports.push([module.i, "*{margin:0;padding:0}#app,.wrapper,body,html{width:100%;height:100%;overflow:hidden}body{font-family:Helvetica Neue,Helvetica,microsoft yahei,arial,STHeiTi,sans-serif}a{text-decoration:none}.content{background:none repeat scroll 0 0 #fff;position:absolute;left:250px;right:0;top:70px;bottom:0;width:auto;padding:40px;box-sizing:border-box;overflow-y:scroll}.crumbs{margin-bottom:20px}.pagination{margin:20px 0;text-align:right}.plugins-tips{padding:20px 10px;margin-bottom:20px}.el-button+.el-tooltip{margin-left:10px}.el-table tr:hover{background:#f6faff}.mgb20{margin-bottom:20px}.move-enter-active,.move-leave-active{transition:opacity .5s}.move-enter,.move-leave{opacity:0}.form-box{width:600px}.form-box .line{text-align:center}.el-time-panel__content:after,.el-time-panel__content:before{margin-top:-7px}.ms-doc .el-checkbox__input.is-disabled+.el-checkbox__label{color:#333;cursor:pointer}.pure-button{width:150px;height:40px;line-height:40px;text-align:center;color:#fff;border-radius:3px}.g-core-image-corp-container .info-aside{height:45px}.el-upload--text{background-color:#fff;border:1px dashed #d9d9d9;border-radius:6px;box-sizing:border-box;width:360px;height:180px;cursor:pointer;position:relative;overflow:hidden}.el-upload--text .el-icon-upload{font-size:67px;color:#97a8be;margin:40px 0 16px;line-height:50px}.el-upload--text{color:#97a8be;font-size:14px;text-align:center}.el-upload--text em{font-style:normal}.ql-container{min-height:400px}.ql-snow .ql-tooltip{transform:translateX(117.5px) translateY(10px)!important}.editor-btn{margin-top:20px}", ""]);

// exports


/***/ }),

/***/ 562:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 605:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(609)

var Component = __webpack_require__(262)(
  /* script */
  __webpack_require__(308),
  /* template */
  __webpack_require__(606),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 606:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "app"
    }
  }, [_c('router-view')], 1)
},staticRenderFns: []}

/***/ }),

/***/ 609:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(541);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(264)("2bfad938", content, true);

/***/ }),

/***/ 611:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(167);
module.exports = __webpack_require__(261);


/***/ })

},[611]);