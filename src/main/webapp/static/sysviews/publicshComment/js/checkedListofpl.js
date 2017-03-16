var publicshCommentApp = angular.module('publicshCommentApp', ['tm.pagination']);
pageService(publicshCommentApp);//初始化分页service
publicshCommentApp.controller("publicshCommentController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/pageQueryByStatus?type=2");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/pageQueryByStatus?type=2");
	};
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/indexs";
	}
});