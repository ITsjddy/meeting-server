var strokeApp = angular.module('strokeApp', []);
strokeApp.controller("strokeController", function($scope,$http) {
	$scope.strokeSave = function(){
//		alert(1);
		var id = $("#id").val();
		$scope.stroke.language = 'zh';
		$scope.stroke.startStrokedate=$("#startStrokedate").val();
		$scope.stroke.stopStrokedate=$("#stopStrokedate").val();
		$scope.stroke.memberId=$("#id").val();
		
		var date1 = $("#startStrokedate").val();
		var date2=  $("#stopStrokedate").val();
		 var oDate1 = new Date(date1);
	       var oDate2 = new Date(date2);
		if(oDate1.getTime() > oDate2.getTime()){
			messagenoty('开始时间大于结束时间，请重新选择！', 'error', 'center', '1500');
      	return false;
      } 
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/save";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/index?id="+id;
		$scope.stroke.groupId = $('#groupidId').val();
		var param = {strokeMain : $scope.stroke, strokeList : $scope.lguest};
		delnoty(delurl, reurl,param,"保存",$http);
//		var url = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/save";
//		$http({
//			method : 'POST',
//			url : url,
////			async:false,  angularjs 本身结构不支持异步请求
//			params :{strokeMain : $scope.stroke, strokeList : $scope.lguest},
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
//		alert(id);
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/index?id="+id;
	}
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/getLanguage";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
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
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.lguest[i].language = value : $scope.lguest[i].language = '';
    }
	/* 删除语言内容 */
    $scope.delGuestDiv = function(i) {
    	if(window.confirm('确定删除此语言?')){
    		$scope.languageData[i].check = false;
        	$scope.languageData[i].languageModel = false;
        	$scope.lguest[i].language = '';
        	parent.noty({dismissQueue: true, force: true, layout: 'topRight', theme: 'defaultTheme', text: $scope.languageData[i].name+'删除成功！', type: 'success',timeout: 2000});
        }
    }
    $scope.getLanguage();
});
