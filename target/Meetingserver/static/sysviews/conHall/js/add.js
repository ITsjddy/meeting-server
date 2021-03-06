var conHallApp = angular.module('conHallApp', []);
conHallApp.controller("conHallController", function($scope,$http) {
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/conHall/toList";
	}
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/conHall/getLanguage";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.languageData = resultMap.conHallLanguage;
			$scope.conHallList = resultMap.conHallList;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.addSave = function(){
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/conHall/addSave";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/conHall/toList";
		$scope.conHall.language = 'zh';
		var param = {conHallMain : $scope.conHall, conHallList : $scope.conHallList};
		delnoty(delurl, reurl,param,"新增",$http);
	}
	
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.conHallList[i].language = value : $scope.conHallList[i].language = '';
    }
    
	/* 删除语言内容 */
    $scope.delGuestDiv = function(i) {
    	$scope.languageData[i].check = false;
    	$scope.languageData[i].languageModel = false;
    	$scope.conHallList[i].language = '';
    	//$scope.languageData[i].check = false;
    	//$scope.conHallList[i].language = '';
    	//$scope.languageOnChange(false,'',i);
//    	$.Zebra_Dialog('确认要删除吗？', {
//			'type':     'question',
//			'title':    '删除',
//			'buttons':  [
//			    {caption: '确定', callback: function() {
//			    	$scope.languageData[i].check = false;
//			    	$scope.conHallList[i].language = '';
//			    	alertmessage('删除成功');
//				 }
//				},
//				{caption: '取消', callback: function() {
//				}}
//			]				            
//		});
    }
    $scope.getLanguage();
});
