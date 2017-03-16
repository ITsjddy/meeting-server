var guestApp = angular.module('guestApp', []);
guestApp.controller("guestController", function($scope,$http) {
	$scope.guestSave = function(){
		$scope.template.fieldName=$('#endepartname').val();
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/serversersonalsemplate/save";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/serversersonalsemplate/index";
		var param = $scope.template;
		console.log(param);
		delnoty(delurl, reurl,param,"保存",$http);
	}
	$scope.createExcel = function(getArrIds){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/serversersonalsemplate/createlist?selectname="+getArrIds;
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/serversersonalsemplate/index";
	}
	$scope.save = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/serversersonalsemplate/importExcelDept";
		var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('logo', file); 
         $http({
              method:'POST',
              url:delurl,
              data: fd,
              headers: {'Content-Type':undefined},
              transformRequest: angular.identity 
         }).success(function(resultMap) {
            	//上传成功的操作
                //alert(resultMap.info);    	 
        	 $('#excelinfo').html(resultMap.info);
        	 $('#importinfo').attr("style", "display:block;");
        	 });
     }
	
	$scope.getSelectedItem = function(){
		var getArrIds = new Array();
		var arrIdsInput = window.frames["selectMember"].document.getElementsByName("ids");
		if(arrIdsInput){
			for(var i=0;i<arrIdsInput.length;i++){
				getArrIds.push(arrIdsInput[i].value);
			}
		}
		console.log(getArrIds);
		$scope.createExcel(getArrIds); 
		$("#myModal").modal('hide');
		
	};
});