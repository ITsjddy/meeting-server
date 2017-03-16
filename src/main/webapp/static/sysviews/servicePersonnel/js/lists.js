var guestApp = angular.module('servicePersonnelApp', ['tm.pagination']);
pageService(guestApp);//��ʼ����ҳservice
guestApp.controller("servicePersonnelController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/pageQuery");
	};
	
	
	/** 修改密码 **/
	$scope.editPassword = function(id, userName){
		$scope.passUserName = userName;
		$scope.passId = id;
	};
	$scope.savePassword = function(){
		var saveurl = CONTEXT_PATH+DISPATCHER_PATH+"/member/savePassword";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
		var param = {id:$scope.passId, password:$scope.passPassword};
		delnoty(saveurl, reurl, param, "密码修改成功", $http);
	};
	
	
	$scope.guestDelete = function(id){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/deleteById";
		$http({
			method : 'POST',
			url : url,
//			async:false, angularjs 本身结构不支持异步请求
			params : {id:id},
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				alert('删除成功');
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
			}else{
				alert('信息修改失败，请重试！');
			}
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
});
