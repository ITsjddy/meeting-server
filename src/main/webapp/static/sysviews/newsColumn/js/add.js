var newsColumnApp = angular.module('newsColumnApp', []);
newsColumnApp.controller("newsColumnController", function($scope,$http) {
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/newsColumn/toList";
	}
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/newsColumn/getLanguage";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.languageData = resultMap.newsColumnLanguage;
			$scope.newsColumnList = resultMap.newsColumnList;
			$scope.columnListInfo = resultMap.columnListInfo;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getLanguage();
	//==============改变类型函数===========
	$scope.changeType = function(){
		if($scope.newsColumn.type == 'category'){
			$scope.showDiv = false;
		}else{
			$scope.showDiv = true;
		}
	}
	//==============保存方法==============
	$scope.addSave = function(){
		$scope.newsColumn.language = 'zh';
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/newsColumn/addSave";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/newsColumn/toList";
		var param ={newsColumnMain : $scope.newsColumn, newsColumnList : $scope.newsColumnList};
		delnoty(delurl, reurl,param,"新增",$http);
	}
	
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.newsColumnList[i].language = value : $scope.newsColumnList[i].language = '';
    }
    
	/* 删除语言内容 */
    $scope.delGuestDiv = function(i) {
    	$scope.languageData[i].check = false;
    	$scope.languageData[i].languageModel = false;
    	$scope.newsColumnList[i].language = '';
    }
});
