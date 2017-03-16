var publicshCommentApp = angular.module('publicshCommentApp', []);
publicshCommentApp.controller("publicshCommentController", function($scope,$http) {
	$scope.publicshCommentQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/queryById";
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
			$scope.publicshCom = resultMap;
//			console.log("路径："+$scope.publicshCom["fileUpload"][0].bigFileurl);
			console.log($scope.publicshCom);
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.publicshCommentSave = function(status){
//		alert(status);
//		alert($("#auditReasontext").val().length);
		if(status=="3" && $("#auditReasontext").val().length==0){
			messagenoty('请填写审核未通过原因原因！', 'error', 'center', '1500');
//			auditReason.setCustomValidity("邀请码重复！");
			return false;
		}
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/audited";
		var id = $("#id").val();
		var auditReason = $scope.publicshCom.auditReason;
		var param = {id:id,status:status,auditReason:auditReason};
		$http({
			method : 'POST',
			url : url,
			params :param,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			var type = $scope.publicshCom.publicsh.type;
			var status = $scope.publicshCom.publicsh.status;
			if(type == 1 ){
				if(status == 1){
					window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/index";
				}else {
					window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/checkList";
				}
				
			}else{
				if(status == 1){
					window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/indexs";
				}else {
					window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/checkListofpl";
				}
			}
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('错误');
			}
		});
	}
	
    $scope.index = function(){
    	var type = $scope.publicshCom.publicsh.type;
    	var status = $scope.publicshCom.publicsh.status;
    	if(type == 1 ){
    		if(status == 1){
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/index";
			}else{
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/checkList";
			}
		}else{
			if(status == 1){
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/indexs";
			}else{
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/checkListofpl";
			}
		}
	}
    $scope.publicshCommentQueryById();
});
