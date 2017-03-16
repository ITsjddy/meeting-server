var exhibitorsApp = angular.module('exhibitorsApp', []);
exhibitorsApp.controller("exhibitorsController", function($scope,$http) {
	
	$scope.exhibitorsQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/queryById";
		var id = $("#id").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.exhibitors = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/getLanguage";
		var id = $("#id").val();
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			params :{id : id},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.languageData = resultMap.memberLanguage;
			$scope.lexhibitors = resultMap.lExhibitors;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	$scope.exhibitorsSave = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/update";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			params :{memberExhibitors : $scope.exhibitors, arrayExhibitors : $scope.lexhibitors},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/index";
			}else{
				alert('信息修改失败，请重试！');
			}
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.exhibitorsSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/update";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/index";
		var param = {memberExhibitors : $scope.exhibitors, arrayExhibitors : $scope.lexhibitors};
		delnoty(delurl, reurl,param,"修改",$http);
	}
	
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.lexhibitors[i].language = value : $scope.lexhibitors[i].language = '';
    }
    
	/* 删除语言内容 */
    $scope.delGuestDiv = function(i) {
    	$scope.languageData[i].check = false;
    	$scope.languageData[i].languageModel = false;
    	$scope.lexhibitors[i].language = '';
    }
    
    $scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/index";
	}
    
    $scope.exhibitorsQueryById();
    $scope.getLanguage();
    
});
