var guestApp = angular.module('servicePersonnelApp', []);
guestApp.controller("servicePersonnelController", function($scope,$http) {
	
    $scope.searchName = '';
	
	var setting = {  
	    data: {
	        key: {
	            name : "departname"
	        },
	        simpleData: {
	            enable: true,
	            idKey: "id",
	            pIdKey:"parentid"
	        }
	    },
	    callback: {
	    	onDblClick: zTreeOnClick
	    }
	};
	
	$scope.keyupSearch = function(txtObj){
		$scope.searchName = txtObj;
		if (txtObj.length == 0) {
			$scope.queryGroupTree(); 
        }
	}
	  
	$scope.search = function(){
		var txtObj = $scope.searchName;
        if (txtObj.length > 0) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var nodeList = zTree.getNodesByParamFuzzy("departname", txtObj);
            $.fn.zTree.init($("#treeDemo"), setting, nodeList);
        } else {
        	$scope.queryGroupTree();                
        } 
	}
	
	$scope.queryGroupTree = function(){
		$('#treeDemo').empty();
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/queryGroupTree";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(data) {
			var zTree = $.fn.zTree.init($("#treeDemo"), setting, data);
//			zTree.getNodesByFilter(filter);
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
		var groupId = treeNode.id;
		var groupName = treeNode.departname;
		var data = {"groupId":groupId,"groupName":groupName};
		window.parent.postMessage(data, '*');
	}
    
    $scope.queryGroupTree();
    
});
