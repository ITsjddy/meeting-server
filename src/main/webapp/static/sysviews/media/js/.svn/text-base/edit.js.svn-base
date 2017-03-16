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
		//校验
		if(!$scope.check()){
			return false;
		}
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
	
	/* 校验 */
    $scope.check = function() {
    	//邀请码校验
		var invitationCodeValue = $scope.media.invitationCode;
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
		var userNameValue = $scope.media.userName;
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
    		data: {id : $scope.media.memberId, value : value},  //传参  
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
	
	$scope.mediaQueryById();
	$scope.getLanguage();
});
