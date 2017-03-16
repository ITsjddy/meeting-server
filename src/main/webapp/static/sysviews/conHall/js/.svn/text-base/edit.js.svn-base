var conHallApp = angular.module('conHallApp', []);
conHallApp.controller("conHallController", function($scope,$http) {
	$scope.getEntityById = function(){
		var url = LINK_PATH+"/conHall/getEntityById";
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
			$scope.conHall = resultMap.data;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var id = $("#uniqueCode").val();
		var param = {id:id};
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/conHall/getLanguage";
		$http({
			method : 'POST',
			url : url,
			params :param,
//			async:false,  angularjs 本身结构不支持异步请求
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.languageData = resultMap.conHallLanguage;
			$scope.conHallList = resultMap.conHallList;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	
	}
	//=================获取会场类型数据===============
	$scope.getConHallTypeList = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/conHall/getConHallTypeList";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.conHallTypeList = resultMap.data;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getConHallTypeList();
	$scope.editSave = function(){
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/conHall/editSave";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/conHall/toList";
		var param ={conHallMain : $scope.conHall, conHallList : $scope.conHallList};
		delnoty(delurl, reurl,param,"修改",$http);
	}
	
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.conHallList[i].language = value : $scope.conHallList[i].language = '';
    }
    
	/* 删除语言内容 */
    $scope.delGuestDiv = function(i) {
    	$scope.languageData[i].check = false;
    	$scope.languageData[i].languageModel = false;
    	$scope.conHallList[i].language = '';
    }
    
    $scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/conHall/toList";
	}
    
    $scope.getEntityById();
    $scope.getLanguage();
});
