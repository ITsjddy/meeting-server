var guestApp = angular.module('guestApp', ['tm.pagination']);
pageService(guestApp);//初始化分页service
guestApp.controller("guestController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/member/pageQueryGuest");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/member/pageQueryGuest");
	};
	$scope.memberDelete = function(id){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/member/deleteById";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			params : {id:id},
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				alert('删除成功！');
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/member/index";
			}else{
				alert('删除错误，请重试！');
			}
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
});
