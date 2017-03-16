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
	$scope.strokeSave = function(){
		var id = $("#member").val();
		$scope.stroke.language = 'zh';
		$scope.stroke.startStrokedate=$("#startStrokedate").val();
		$scope.stroke.stopStrokedate=$("#stopStrokedate").val();
		var date1 = $("#startStrokedate").val();
		var date2=  $("#stopStrokedate").val();
		 var oDate1 = new Date(date1);
	       var oDate2 = new Date(date2);
		if(oDate1.getTime() > oDate2.getTime()){
			messagenoty('开始时间大于结束时间，请重新选择！', 'error', 'center', '1500');
      	return false;
      } 
		$scope.stroke.memberId=$("#member").val();
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/update";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/index?id="+id;
		$scope.stroke.language = 'zh';
		$scope.stroke.groupId = $('#groupidId').val();
		var param = {memberStroke : $scope.stroke, arrayStroke : $scope.lguest};
		delnoty(delurl, reurl,param,"保存",$http);
//		var url = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/update";
//			console.log($scope.lguest);
//		$http({
//			method : 'POST',
//			url : url,
////			async:false,   angularjs 本身结构不支持异步请求
//			params :{memberStroke : $scope.stroke, arrayStroke : $scope.lguest},
//			headers : {
//				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
//			}
//		}).success(function(resultMap) {
//			if(null != resultMap && resultMap.success == true){
//				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/index?id="+id;
//			}else{
//				alert('信息修改失败，请重试！');
//			}
//		}).error(function(error) {
//			alert(error.status);
//			if('403' == error.status){
//				alert('无权限');
//			}
//		});
	}
	$scope.index = function(){
		var id = $("#member").val();
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
    
    
    $scope.tab=function(date1,date2){
    	var flage =true;
        var oDate1 = new Date(date1);
        var oDate2 = new Date(date2);
        if(oDate1.getTime() > oDate2.getTime()){
//            console.log('第一个大');
        	alert(1);
            flage=false;
        } 
        alert(2);
        return flage;
    }
	$scope.strokeQueryById();
	  $scope.getLanguage();
});
