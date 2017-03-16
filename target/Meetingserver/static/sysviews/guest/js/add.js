var guestApp = angular.module('guestApp', ['tm.pagination']);
guestApp.controller("guestController", function($scope,$http) {
	
	/*证件类型*/
	$scope.idTypes = [
		{uneIdent:'shenfenzheng', name:'身份证'},
		{uneIdent:'huzhao', name:'护照'}
	];
	
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/guest/getLanguage";
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
	
	$scope.guestSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/guest/save";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";
		$scope.guest.language = 'zh';
		var param = {memberGuest : $scope.guest, arrayGuest : $scope.lguest};
		delnoty(delurl, reurl,param,"保存",$http);
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
    
    $scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";
	}
    
    $scope.getLanguage();
    
});
