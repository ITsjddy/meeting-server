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
