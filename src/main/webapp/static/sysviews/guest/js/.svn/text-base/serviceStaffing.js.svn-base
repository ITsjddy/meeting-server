var guestApp = angular.module('guestApp', []);
guestApp.controller("guestController", function($scope,$http) {
	
	var treeDialog;
	
	$scope.guestQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/guest/queryById";
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
			$scope.guest = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	$scope.queryServicePersonnel = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/guest/queryServicePersonnel";
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
			$scope.lguestSer = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	$scope.servicePersonnelSave = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/guest/servicePersonnelSave";
		var guestId = $scope.guest.id;
		var lguestSer = $scope.lguestSer;
		var result = $scope.checkRepeat(lguestSer);
		if(result){
			alert('服务人员有重复！');
			return false;
		}
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			params :{lguestSer:lguestSer,guestId:guestId},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";
			}else{
				alert('信息修改失败，请重试！');
			}
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	$scope.checkRepeat = function(lguestSer){
		var result = false;
		if(null != lguestSer && lguestSer.length > 0){
			for (var i = 0; i < lguestSer.length; i++) {
				var serId1 = lguestSer[i].servicePersonnelId;
				if(null != serId1 && '' != serId1){
					var zhi = 0;
					for (var j = 0; j < lguestSer.length; j++) {
						var serId2 = lguestSer[j].servicePersonnelId;
						if(null != serId2 && '' != serId2 && serId1 == serId2){
							zhi ++;
							if(zhi > 1){
								result = true;
								break;
							}
						}
					}
				}
				
			}
			
		}
		return result;
	}
	
	$scope.getClickItem = function(data){
        $scope.lguestSer[data.i].servicePersonnelName = data.serName;
        $scope.lguestSer[data.i].servicePersonnelId = data.serId;
        $('#serid'+data.i).val(data.serId);
        $('#sername'+data.i).val(data.serName);
	};
	
	window.addEventListener('message',function(e){
        var data = e.data;
        $scope.getClickItem(data);
        treeDialog.close();
        //$('#myServicePersonnel').modal('hide');
    },false);
	
	$scope.delService = function(i){
        $scope.lguestSer[i].servicePersonnelName = '';
        $scope.lguestSer[i].servicePersonnelId = '';
        $('#serid'+i).val('');
        $('#sername'+i).val('');
	};
	
	$scope.servicePerTree = function(i){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/guest/servicePersonnelTree?i="+i;
		treeDialog =new $.Zebra_Dialog('', {
			'source': {'iframe': {
				'id' : 'pro',
				'name': 'pro',
			    'src':  url,
			    'height': 400
			 }},
		    'type' : '',
		    'width': 500,
		    'title': '服务人员tree列表',
		    'buttons': 'none'
		});
	}; 
	
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";
	}
	
	$scope.guestQueryById();
	$scope.queryServicePersonnel();
});
