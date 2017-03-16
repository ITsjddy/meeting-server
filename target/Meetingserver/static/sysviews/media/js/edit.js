var mediaApp = angular.module('mediaApp', []);
mediaApp.controller("mediaController", function($scope,$http) {

	/*证件类型*/
	$scope.idTypes = [
		{uneIdent:'shenfenzheng', name:'身份证'},
		{uneIdent:'huzhao', name:'护照'}
	];
	$scope.mediaQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/media/queryById";
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
			$scope.media = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}

	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/media/getLanguage";
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
			$scope.lmedia = resultMap.lMedia;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.mediaSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/media/update";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/media/index";
		var param = {memberMedia : $scope.media, arrayMedia : $scope.lmedia};
		delnoty(delurl, reurl,param,"修改",$http);
	}

	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.lmedia[i].language = value : $scope.lmedia[i].language = '';
    }
    
	/* 删除语言内容 */
    $scope.delMediaDiv = function(i) {
    	if(window.confirm('确定删除此语言?')){
    	$scope.languageData[i].check = false;
    	$scope.languageData[i].languageModel = false;
    	$scope.lmedia[i].language = '';
    	parent.noty({dismissQueue: true, force: true, layout: 'topRight', theme: 'defaultTheme', text: $scope.languageData[i].name+'删除成功！', type: 'success',timeout: 2000});	
    }
    }
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/media/index";
	}
	$scope.mediaQueryById();
	$scope.getLanguage();
});
