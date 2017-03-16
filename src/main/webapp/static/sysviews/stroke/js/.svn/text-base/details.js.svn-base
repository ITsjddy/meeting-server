var strokeApp = angular.module('strokeApp', []);
strokeApp.controller("strokeController", function($scope,$http) {
	$scope.strokeQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/queryById";
		var id = $("#id").val();
//		alert(id);
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.stroke = resultMap;
			console.log($scope.stroke);
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.strokeSavest = function(){
		var id = $("#member").val();
//		alert($("#startStroketime").val());
		$scope.stroke.language = 'zh';
		$scope.stroke.startStrokedate=$("#startStrokedate").val();
		$scope.stroke.startStroketime=$("#startStroketime").val();
		$scope.stroke.stopStrokedate=$("#stopStrokedate").val();
		$scope.stroke.stopStroketime=$("#stopStroketime").val();
//		alert(id);
		$scope.stroke.memberId=$("#member").val();
//		alert($("#member").val());
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/update";
			console.log($scope.lguest);
		$http({
			method : 'POST',
			url : url,
//			async:false,   angularjs 本身结构不支持异步请求
			params :{memberStroke : $scope.stroke, arrayStroke : $scope.lguest},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/index?id="+id;
			}else{
				alert('信息修改失败，请重试！');
			}
		}).error(function(error) {
			alert(error.status);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.index = function(){
		var id = $("#member").val();
//		alert(id);
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/index?id="+id;
	}
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/getLanguage";
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
			$scope.lguest = resultMap.lGuest;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	/* 删除语言内容 */
    $scope.delGuestDiv = function(i) {
    	$scope.languageData[i].check = false;
    	$scope.languageData[i].languageModel = false;
    	$scope.conHallList[i].language = '';
    }
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.lguest[i].language = value : $scope.lguest[i].language = '';
//    	alert( $scope.lguest[i].language);
    }
	$scope.strokeQueryById();
	  $scope.getLanguage();
});
