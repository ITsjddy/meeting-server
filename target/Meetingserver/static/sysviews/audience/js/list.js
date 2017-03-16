var audienceApp = angular.module('audienceApp', ['tm.pagination']);
pageService(audienceApp);//初始化分页service
audienceApp.controller("audienceController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/audience/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/audience/pageQuery");
	};
	$scope.guestDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/audience/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/audience/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
    	$(document).ready(function() {
			$("#checkAll").click(function() {
				$("input[name='ids']").prop("checked", this.checked);
//				alert("aaaa");
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
//				   console.log($(this).val());
			   }else{
				   ids_selected += (","+$(this).val());
//				   console.log(ids_selected);
			       }
			  });
			var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/audience/deleteList";
			var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/audience/index";
			var param = {ids:ids_selected};
			delnoty(delurl, reurl,param,"删除  ",$http);
		}

});
