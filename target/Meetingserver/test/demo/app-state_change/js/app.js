angular.module("demo-statechange",[])
.config(function($provide,$stateProvider, $locationProvider,$controllerProvider, $urlRouterProvider, $ocLazyLoadProvider) {
	
	$ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: true
    });
    $stateProvider
    .state("index.statechange",{
		url:"/statechange",
		views:{
			"":{
				templateUrl:"apps/demo/app-state_change/index.html"
			},
        	"top@index.statechange":{
        		controller:'demo-statechange_navCtrl',
				templateUrl: "apps/demo/app-state_change/top.html"
			},
        	"main@index.statechange":{						//切换左侧导航栏
        		controller:'demo-statechange_listCtrl',
				templateUrl: "apps/demo/app-state_change/list_page.html"
			}
		},
    	data:{
    		pageTitle:"示例-状态改变"
    	}
    })
    .state("index.statechange.page",{
		url:"/page",
		views:{
			"@index":{
				templateUrl:"apps/demo/app-state_change/page.html"
			}
		},
    	data:{
    		pageTitle:"示例-状态改变"
    	}
    })
    .state("index.statechange.filter",{
		url:"/filter/:pattern",
		views:{
			"":{
				templateUrl:"apps/demo/app-state_change/index.html"
			},
        	"top@index.statechange":{
        		controller:'demo-statechange_navCtrl',
				templateUrl: "apps/demo/app-state_change/top.html"
			},
        	"main@index.statechange":{						//切换左侧导航栏
        		controller:'demo-statechange_listCtrl',
				templateUrl: "apps/demo/app-state_change/list_page.html"
			}
		},
		onEnter:function($rootScope,$window,$stateParams,$state){
			$rootScope.$broadcast("$iqStateEnter",$state);
			//$window.scrollTo(0,0);
		},
    	data:{
    		pageTitle:"示例-状态改变"
    	}
    })
    
});