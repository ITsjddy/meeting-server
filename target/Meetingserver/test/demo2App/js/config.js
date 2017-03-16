(function () {

var basePath = "apps/demo2App/"
function config($provide,$stateProvider, $locationProvider,$controllerProvider, $urlRouterProvider, $ocLazyLoadProvider) {
	//$locationProvider.html5Mode(true);
	
    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: true
    });
	
    $stateProvider

    .state('index.demo2.apply', {
        url: "/apply/:appId",
		views:{
        	"@index":{
				templateUrl: basePath + "applyPage.html"
			}
		}
    })
    .state('index.demo2.autoTable', {
        url: "/list2",
		views:{
        	"@index":{
				templateUrl: basePath + "autolist.html"
			}
		}
    });
		
}

angular.module('demo2', []);
angular
    .module('demo2')
    .config(config)
})();