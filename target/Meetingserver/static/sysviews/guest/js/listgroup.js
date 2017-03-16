var guestApp = angular.module('guestApp', ['tm.pagination']);
pageService(guestApp);//初始化分页service
guestApp.controller("guestController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/guest/pageQueryForGroup?groupid="+$('#groupid').val());
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/guest/pageQueryForGroup?groupid="+$('#groupid').val());
	};
	$scope.guestDelete = function(id){
		$.Zebra_Dialog('确认要删除吗？', {
			'type':     'question',
			'title':    '删除',
			'buttons':  [
			    {caption: '确定', callback: function() {
			    	var url = CONTEXT_PATH+DISPATCHER_PATH+"/guest/deleteById";
					$http({
						method : 'POST',
						url : url,
//						async:false,  angularjs 本身结构不支持异步请求
						params : {id:id},
					}).success(function(resultMap) {
						if(null != resultMap && resultMap.success == true){
							alertmessage('删除成功');
							window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";	
						}else{
							alertmessage('删除错误，请重试！');
						}
					}).error(function(error) {
						if('403' == error.status){
							alertmessage('无权限');
						}
					});
				 }
				},
				{caption: '取消', callback: function() {
				}}
			]				            
		});		
	}
});
