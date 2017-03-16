var audienceApp = angular.module('audienceApp', []);
audienceApp.controller("audienceController", function($scope,$http) {
	$scope.audienceQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/audience/queryById";
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
			$scope.audience = resultMap;
			console.log($scope.audience);
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/audience/getLanguage";
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
			$scope.laudience = resultMap.lAudience;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	$scope.audienceSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/audience/update";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/audience/index";
		var param = {memberAudience : $scope.audience, arrayAudience : $scope.laudience};
		delnoty(delurl, reurl,param,"修改",$http);
	}
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.laudience[i].language = value : $scope.laudience[i].language = '';
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
    
    $scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/audience/index";
	}
    $scope.audienceQueryById();
    $scope.getLanguage();
});
