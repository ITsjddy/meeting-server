
var folderApp = angular.module('folderApp', ['tm.pagination']);
pageService(folderApp);//初始化分页service
//checkboxDeleteService(folderApp);
//,checkboxDeleteService
folderApp.controller("folderController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/folder/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/folder/list");
	};
	$scope.folderDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/folder/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/folder/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
/*	$scope.idSelect = function(id,select){
		return checkboxDeleteService.idSelect($scope,id,select);
	}
	$scope.checkAll = function(c){
		return checkboxDeleteService.idSelect($scope,c);
	}
	$scope.deletes = function(){
		var deurl = CONTEXT_PATH+DISPATCHER_PATH+"/folder/deleteList";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/folder/index";
		return checkboxDeleteService.idSelect($scope,$http,deurl,reurl);
	}*/
	
	$(document).ready(function() {
		$("#checkAll").click(function() {
			$("input[name='ids']").prop("checked", this.checked);
//			alert("aaaa");
		});
	});
	$(document).on("click", "input[name='ids']", function(){ 
		var $temids = $("input[name='ids']");
		$("#checkAll").prop("checked" , $temids.length == $temids.filter(":checked").length ? true : false);
	}); 
	$scope.deleteAll = function(id){
		var ids_selected = "";
		  $('input:checkbox[name=ids]:checked').each(function(i){
		   if(0==i){
			   ids_selected = $(this).val();
//			   console.log($(this).val());
		   }else{
			   ids_selected += (","+$(this).val());
//			   console.log(ids_selected);
		       }
		  });
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/folder/deleteList";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/folder/index";
		var param = {ids:ids_selected};
		delnoty(delurl, reurl,param,"删除  ",$http);
	}

});

    