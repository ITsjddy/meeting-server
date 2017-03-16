var publicshCommentApp = angular.module('publicshCommentApp', ['tm.pagination']);
pageService(publicshCommentApp);//初始化分页service
publicshCommentApp.controller("publicshCommentController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/pageQuery?type=1");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/pageQuery?type=1");
	};
	$scope.checkList = function(status){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/pageQueryByStatus";
		var type = "1";
		var param = {type:type};
		$http({
			method : 'POST',
			url : url,
			params : param,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.publics = resultMap;
			window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/checkList";
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/index";
	}
});
