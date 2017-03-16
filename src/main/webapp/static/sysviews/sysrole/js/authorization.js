var sysRoleApp = angular.module('sysRoleApp', []);
sysRoleApp.controller("sysRoleController", function($scope,$http) {
	$scope.getAuthorizationById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/getAuthorization";
		var id = $("#id").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			var zhi = JSON.stringify(resultMap);
			var json = eval('(' + zhi + ')');
			json['plugins'] = [ "wholerow", "checkbox" ];
			$('#jstree2').jstree(json);
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.authorizationSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/authorizationSave";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/index";
		var id = $("#id").val();
		var authchecked = getAuthchecked();
		var param = {authchecked:authchecked,id:id};
		delnoty(delurl, reurl,param,"授权",$http);
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/index";
	}
	function getAuthchecked(){
		var nodes=$("#jstree2").jstree("get_checked");
		var authchecked = "";
		for(var i=0;i<nodes.length;i++){
			authchecked = authchecked + nodes[i] + ",";
		}
		if(authchecked.length > 0){
			authchecked = authchecked.substring(0,authchecked.length-1);
		}
		return authchecked;
	}
	
	$scope.getAuthorizationById();
});
