var guestApp = angular.module('guestApp', []);
guestApp.controller("guestController", function($scope,$http) {
	
    $scope.searchName = '';
	
	var setting = {  
	    data: {
	        key: {
	            name : "name"
	        },
	        simpleData: {
	            enable: true,
	            idKey: "id",
	            pIdKey:"pid"
	        }
	    },
	    callback: {
	    	onDblClick: zTreeOnClick
	    }
	};
	
	$scope.keyupSearch = function(txtObj){
		$scope.searchName = txtObj;
		if (txtObj.length == 0) {
			$scope.queryServicePersonneTree(); 
        }
	}
	  
	$scope.search = function(){
		var txtObj = $scope.searchName;
        if (txtObj.length > 0) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var nodeList = zTree.getNodesByParamFuzzy("name", txtObj);
            $.fn.zTree.init($("#treeDemo"), setting, nodeList);
        } else {
        	$scope.queryServicePersonneTree();                
        } 
	}
	
	$scope.queryServicePersonneTree = function(){
		$('#treeDemo').empty();
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/guest/queryServicePersonneTree";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(data) {
			var zTree = $.fn.zTree.init($("#treeDemo"), setting, data);
			zTree.getNodesByFilter(filter);
        	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        	//treeObj.expandAll(true);
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	function zTreeOnClick(event, treeId, treeNode){
		var serId = treeNode.id;
		var serName = treeNode.name;
		var type = treeNode.type;
		var i = $('#i').val();
		if('2' == type){
			var data = {"serId":serId,"serName":serName,"i":i};
			window.parent.postMessage(data, '*');
		} else {
			//alert('请选择服务人员！');
		}
	}
    
    $scope.queryServicePersonneTree();
    
});
