var publicshCommentApp = angular.module('publicshCommentApp', []);
publicshCommentApp.controller("publicshCommentController", function($scope,$http) {
	$scope.publicshCommentQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/publicshComment/queryBySubjectId";
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