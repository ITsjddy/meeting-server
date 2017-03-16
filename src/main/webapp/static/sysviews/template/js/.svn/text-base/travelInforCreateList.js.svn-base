var guestApp = angular.module('guestApp', ['tm.pagination']);

pageService(guestApp); //初始化分页service

guestApp.controller("guestController", function($scope, $http, pageService) {
	var mainUrl = CONTEXT_PATH + DISPATCHER_PATH + "/travelInforTemplate/";
	var templateNameList = ['数据导入唯一标识'];
	pageService.queryForList($scope, $http, mainUrl + "pageQuery");
	$scope.guestSave = function() {
		$scope.template.fieldName = $('#endepartname').val();
		var delurl = mainUrl + "save";
		var reurl = mainUrl + "index";
		var param = $scope.template;
		delnoty(delurl, reurl, param, "保存", $http);
	}

	
	$scope.createExcel = function() {
		initToList();
		$('#myModal').modal('show');
	}

	$scope.confirmButton = function() {
		$('#myModal').modal('hide');
		window.location.href = mainUrl + "createlist?templateNameList=" + templateNameList;
		initToList();

	}
	
	$scope.closeButton = function() {
		initToList();
		$('#myModal').modal('hide');
	}

	$scope.index = function() {
		window.location.href = mainUrl + "index";
	}

	function initToList() {
		templateNameList = ['数据导入唯一标识'];
	}

	$scope.templateOnChange = function(checkId, template) {
		updateSelected(checkId, template.templateName);
	}

	function updateSelected(action, id) {
		if (action == true && templateNameList.indexOf(id) == -1) {
			templateNameList.push(id);
		}
		if (action == false && templateNameList.indexOf(id) != -1) {
			var idx = templateNameList.indexOf(id);
			templateNameList.splice(idx, 1);
		}
	}

	$scope.save = function() {
		var delurl = mainUrl + "importExcelDept";
		var fd = new FormData();
		var file = document.querySelector('input[type=file]').files[0];
		fd.append('logo', file);
		$http({
			method: 'POST',
			url: delurl,
			data: fd,
			headers: {
				'Content-Type': undefined
			},
			transformRequest: angular.identity
		}).success(function(resultMap) {
			//上传成功的操作
			//alert(resultMap.info);    	 
			$('#excelinfo').html(resultMap.info);
			$('#importinfo').attr("style", "display:block;");
		});
	}
});