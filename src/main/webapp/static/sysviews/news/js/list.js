var newsApp = angular.module('newsApp', ['tm.pagination']);
pageService(newsApp);//初始化分页service
checkboxDeleteService(newsApp);//初始化多选删除service
newsApp.controller("newsController", function($scope,$http,pageService,checkboxDeleteService) {
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/news/multiDelete";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/news/toList";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
	//获取所有新闻分类
	$scope.getCategory = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getCategoryData";
		$http({
			method : 'POST',
			url : url,
		}).success(function(resultMap) {
			$scope.categoryList = resultMap.data;
			var categoryAll = {id:"all",name:"所有分类"};
			$scope.categoryList.push(categoryAll);
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getCategory();
	//获取栏目数据
//	$scope.getColumnData = function(){
//		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getColumnData";
//		var param = {language:"zh",categoryId:$scope.searchParam.categoryId};
//		$http({
//			method : 'POST',
//			url : url,
//			params :param,
//		}).success(function(resultMap) {
//			$scope.columnList = resultMap.data;
//		}).error(function(error) {
//			if('403' == error.status){
//				alert('无权限');
//			}
//		});
//	}
//	$scope.getColumnData();
//	var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getCategoryData";
//	var param = {};
//	$.ajax({
//		async:false,
//		cache:false,
//		type:"POST",
//		dataType : 'json',
//		data: param,  //传参  
//		url: url,
//		error:function(resultMap){
//			if('403' == json.status){
//				alert('无权限');
//			}
//		},
//		timeout:60000,
//		success:function(resultMap){
//			$scope.categoryList = resultMap.data;
//			$scope.searchParam.categoryId = $scope.categoryList[0].id;//默认第一个分类
//			alert("fun");
//		}
//	});
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/list");
	};
	//获取所有语言数据
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getLanguage";
		$http({
			method : 'POST',
			url : url,
		}).success(function(resultMap) {
			$scope.languageList = resultMap.data;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getLanguage();
	//改变不同语言的list
	$scope.changeListType  = function(uneIdent){
		$scope.searchParam.languageNow = uneIdent;
		$scope.searchByParam();
	}
	//改变分类
	$scope.changeCategory = function(categoryId){
		$scope.searchParam.categoryId = categoryId;
		$scope.searchByParam();
	}
	//把新闻置顶
	$scope.toTop = function (id,language){
		var param = {id:id,language:language};
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/toTop";
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+language;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	//删除按钮
	$scope.deleteItem = function(id,language){
		var param = {id:id};
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/news/delete";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+language;
		delnoty(delurl, reurl,param,"删除",$http);
	}
	//上移下移
	$scope.moveIt = function(downOrUp,id,language){
		var param = {downOrUp:downOrUp,id:id};
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/moveIt";
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+language;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	//排序方式改变函数
	$scope.changeSortType = function (){
		$scope.searchByParam();
	}
	//发布函数
	$scope.publish = function (id,language){
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/publishNewsById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+language;
		var param = {id:id};
		delnoty(delurl, reurl,param,"发布",$http);
	}
	//撤回函数
	$scope.withdraw = function (id,language){
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/withdraw";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+language;
		var param = {id:id};
		delnoty(delurl, reurl,param,"撤回",$http);
	}
	//更新排序方式
	$scope.updateSortType = function(sortType,language){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/updateSortType";
		var param = {sortType:sortType};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+language;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	//获取排序方式
	$scope.getSortType = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getSortType";
		$http({
			method : 'POST',
			url : url,
		}).success(function(resultMap) {
			$scope.sortType = resultMap.data;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getSortType();
});
