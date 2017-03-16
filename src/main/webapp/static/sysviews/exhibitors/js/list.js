var exhibitorsApp = angular.module('exhibitorsApp', ['tm.pagination']);
pageService(exhibitorsApp);//初始化分页service
exhibitorsApp.controller("exhibitorsController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/pageQuery");
	};
	$scope.guestDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
	
	/** 修改密码 **/
	$scope.editPassword = function(id, userName){
		alert(id+" "+userName);
		$scope.passUserName = userName;
		$scope.passId = id;
	};
	$scope.savePassword = function(){
		var saveurl = CONTEXT_PATH+DISPATCHER_PATH+"/member/savePassword";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/index";
		var param = {id:$scope.passId, password:$scope.passPassword};
		delnoty(saveurl, reurl, param, "密码修改成功", $http);
	};
    	$(document).ready(function() {
			$("#checkAll").click(function() {
				$("input[name='ids']").prop("checked", this.checked);
//				$("input[name='ids']").click(function() {
//					var $temids = $("input[name='ids']");
//					$("#checkAll").prop("checked" , $temids.length == $temids.filter(":checked").length ? true :false);
//				});
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
			//			    	   alert(ids_selected);
			   }else{
				   ids_selected += (","+$(this).val());
			//			    	   alert(ids_selected);
			       }
			  });
			var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/deleteList";
			var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/exhibitors/index";
			var param = {ids:ids_selected};
			delnoty(delurl, reurl,param,"删除",$http);
		}
			

});
