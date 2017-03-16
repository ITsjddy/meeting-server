(function () {

var basePath = "apps/demoApp/"
function config($provide,$stateProvider, $locationProvider,$controllerProvider, $urlRouterProvider, $ocLazyLoadProvider) {
	//$locationProvider.html5Mode(true);
	
    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });
	var getUrl = function(name){
		return "apps/demoApp/"+name+".html";
	}
    $stateProvider
    .state("index.demo",{
		url:"/demo",
		views:{
			"":{
				templateUrl:"apps/demoApp/list.html"
			},
        	"ui-left-nav@index":{						//切换左侧导航栏
				templateUrl: "apps/demoApp/nav.html"
			}
		},
		onEnter:function($window){
			$window.scrollTo(0,0);
		},
    	data:{
    		pageTitle:"示例"
    	}
    })
    .state('index.demo.directive', {
        url: "/directive/:name",
        views : {
            "":{
            	//iq_table.html  iq_table
            	//iq_pager.html  iqPagerList iqPagerListPager iqPagerMore
            	//iq_tree.html iq_tree
            	templateUrl: function(params) {
	                return getUrl(params.name);
	            }
            }
    
        }
    })
    .state('index.demo.partStyle', {
        url: "/part/:name",
        views : {
            "ui-main@index":{
            	//iq_table.html  iq_table
            	//iq_pager.html  iqPagerList iqPagerListPager iqPagerMore
            	//iq_tree.html iq_tree
            	templateUrl: function(params) {
	                return getUrl(params.name);
	            }
            }
    
        }
    })
    .state("index.demo.summernote", {
    	url: "/summernote",
    	views : {
    		"":{
            	templateUrl: function() {
	                return getUrl("summernote");
	            }
    		}
    	},
	    resolve: {
	        loadPlugin: function ($ocLazyLoad) {
	            return $ocLazyLoad.load([
	                {
	                    name: 'summernote',
	                    files: ['../lib/plugins/summernote/summernote.css','../lib/plugins/summernote/summernote-bs3.css','../lib/plugins/summernote/summernote.js','../lib/plugins/summernote/angular-summernote.min.js','../lib/plugins/summernote/summernote-zh-CN.js']
	                }
	            ]);
	        }
	    }
    
    })
		
}
angular
    .module('demo')
    .config(config)
})();