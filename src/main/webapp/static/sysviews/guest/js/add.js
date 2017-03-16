var guestApp = angular.module('guestApp', ['tm.pagination']);
guestApp.controller("guestController", function($scope,$http) {
	
	//上传 -- Mr.Yi
	$("#uploadify").uploadify({
		'uploader'       : CONTEXT_PATH+'/static/upload/js/uploadify.swf',
		'script'         : LINK_PATH+'/uploadIMG/upload;jsessionid=${pageContext.session.id}',
		'cancelImg'      : CONTEXT_PATH+'/static/upload/js/cancel.png',
		'buttonImg'		 : CONTEXT_PATH+'/static/upload/js/xzButton.gif',
		'queueID'        : 'fileQueue',
		'auto'           : true,
		'multi'          : false,
		'fileDataName'   : 'uploadify',
		'wmode'			 : 'transparent',
		'scriptData'	 : {folderName:'guest'},	//传参
		'simUploadLimit' : 6,
		'fileExt'		 : '*.png;*.gif;*.jpg;*.bmp;*.jpeg',
		'fileDesc'		 : '图片(*.png;*.gif;*.jpg;*.bmp;*.jpeg)',
		'onSelectOnce' : function(event,data,data)
        {
          filesSelected:true;
        },
        'onComplete' : function(event,queueId,fileObj,response,data)
        {
        	$('#imaggo').val(response);
        	var p = CONTEXT_PATH + response;
        	var str = p.replace("Meetingserver","");
            var $image=$("<img id='imgvo' src=" + str + "  width='70px' height='70px'/>");
            $("#imagesTO").html($image);
        } 
	});
	
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
			$scope.idTypes = resultMap.lIdType;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	$scope.guestSave = function(){
//		校验
		if(!$scope.check()){
			return false;
		}
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/guest/save";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";
		$scope.guest.language = 'zh';
		$scope.guest.groupId = $('#groupidId').val();
		$scope.guest.avatar = $('#imaggo').val();
		var param = {memberGuest : $scope.guest, arrayGuest : $scope.lguest};
		delnoty(delurl, reurl,param,"保存",$http);
	}

	/* 校验 */
    $scope.check = function() {
    	//邀请码校验
		var invitationCodeValue = $scope.guest.invitationCode;
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
		var userNameValue = $scope.guest.userName;
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
    		data: {id : $scope.guest.memberId, value : value},  //传参  
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
        	messagenoty($scope.languageData[i].name+'删除成功！', 'success', 'bottomRight', '1500');
        }
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
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";
	}
    
    $scope.getLanguage();
    
});
