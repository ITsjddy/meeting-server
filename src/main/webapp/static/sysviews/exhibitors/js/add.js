var exhibitorsApp = angular.module('exhibitorsApp', []);
exhibitorsApp.controller("exhibitorsController", function($scope,$http) {
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/getLanguage";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
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
		})
	}
	$scope.exhibitorsSave = function(){
		//校验
		if(!$scope.check()){
			return false;
		}
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/save";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/index";
		$scope.exhibitors.language = 'zh';
		var param = {memberExhibitors: $scope.exhibitors, arrayExhibitors : $scope.lexhibitors};
		delnoty(delurl, reurl,param,"保存",$http);
	}
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.lexhibitors[i].language = value : $scope.lexhibitors[i].language = '';
    }
    
	/* 删除语言内容 */
    $scope.exhibitorsDiv = function(i) {
    	if(window.confirm('确定删除此语言?')){
    		$scope.languageData[i].check = false;
        	$scope.languageData[i].languageModel = false;
        	$scope.lguest[i].language = '';
        	parent.noty({dismissQueue: true, force: true, layout: 'topRight', theme: 'defaultTheme', text: $scope.languageData[i].name+'删除成功！', type: 'success',timeout: 2000});
        }
    }
    $scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/index";
	}
    /* 校验 */
    $scope.check = function() {
    	//邀请码校验
		var invitationCodeValue = $scope.exhibitors.invitationCode;
		var checkZz = /^[a-zA-Z0-9]{3,15}$/;
		if(!checkZz.test(invitationCodeValue)){
			messagenoty('邀请码 只能由数字、字母组成，长度要在3到15之间！', 'error', 'center', '1500');
			invitationCode.setCustomValidity("邀请码 只能由数字、字母组成，长度要在3到15之间！"); 
			return false;
		}
		if($scope.checkUserName(invitationCodeValue)){
			messagenoty('邀请码重复！', 'error', 'center', '1500');
			invitationCode.setCustomValidity("邀请码重复！");
			return false;
		}
		//用户名校验
		var userNameValue = $scope.exhibitors.userName;
		if('' != userNameValue){
			if(!checkZz.test(userNameValue)){
				messagenoty('用户名 只能由数字、字母组成，长度要在3到15之间！', 'error', 'center', '1500');
				userName.setCustomValidity("用户名 只能由数字、字母组成，长度要在3到15之间！"); 
				return false;
			}
			if($scope.checkUserName(userNameValue)){
				messagenoty('用户名重复！', 'error', 'center', '1500');
				userName.setCustomValidity("用户名重复！");
				return false;
			}
		}
    	return true;
    }
	/* 校验邀请码/用户名是否重复 */
    $scope.checkUserName = function(value) {
    	var cheResult = false;
    	$.ajax({
    		async:false,
    		cache:false,
    		type:"POST",
    		dataType : 'json',
    		data: {id : $scope.exhibitors.memberId, value : value},  //传参  
    		url: CONTEXT_PATH+DISPATCHER_PATH+"/member/checkRepeat",
    		error:function(json){
    			if('403' == json.status){
    				alert('无权限');
    			}
    		},
    		timeout:60000,
    		success:function(result){
    			cheResult = result;
    		}
    	});
    	return cheResult;
    }
    
    $scope.getLanguage();
	
});