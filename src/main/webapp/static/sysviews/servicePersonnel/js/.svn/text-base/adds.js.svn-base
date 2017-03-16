var guestApp = angular.module('servicePersonnelApp', []);
guestApp.controller("servicePersonnelController", function($scope,$http) {
	$scope.cheResult = false;
	$scope.servicePersonnelSave = function(){
		if(!$scope.check()){
			return false;
		}
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/save";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
//		$scope.service.language = 'zh';
		$scope.service.groupId = $('#groupidId').val();
		var param = $scope.service;
		delnoty(delurl, reurl,param,"保存",$http);
//		var url = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/save";
//		$http({
//			method : 'POST',
//			url : url,
////			async:false,  angularjs ����ṹ��֧���첽����
//			params :$scope.service,
//			headers : {
//				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
//			}
//		}).success(function(resultMap) {
//			if(null != resultMap && resultMap.success == true){
//				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
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
	
	

	/* 校验 */
    $scope.check = function() {
    	//邀请码校验
		var invitationCodeValue = $scope.service.invitationCode;
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
		var userNameValue = $scope.service.userName;
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
    		data: {id : $scope.service.memberId, value : value},  //传参  
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
    
    $scope.delGroup = function(){
        $('#groupidId').val('');
        $('#groupnameId').val('');
	};
	
	window.addEventListener('message',function(e){
        var data = e.data;
        $scope.getClickItem(data);
        $("#myGroup").modal('hide');
    },false);
	
	$scope.getClickItem = function(data){
        $('#groupidId').val(data.groupId);
        $('#groupnameId').val(data.groupName);
	};
	
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
	}
});
