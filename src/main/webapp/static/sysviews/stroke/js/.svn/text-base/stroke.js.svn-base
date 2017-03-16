var strokeApp = angular.module('stroketApp', ['tm.pagination']);
pageService(strokeApp);//初始化分页service
strokeApp.controller("strokeController", function($scope,$http,pageService) {
	var id =$("#id").val();
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/stroke/pageQuery?id="+id);
	$scope.searchByParam = function(){
//		alert(1);
//		alert($("#stroke_date").val());
		$scope.searchParam.stroke_date=$("#stroke_date").val();
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/stroke/pageQuery?id="+id);
	};
	$scope.strokeDelete = function(id,memberId){
//		alert(memberId);
		$.Zebra_Dialog('确认要删除吗？', {
			'type':     'question',
			'title':    '删除',
			'buttons':  [
			    {caption: '确定', callback: function() {
			    	var url = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/deleteById";
					$http({
						method : 'POST',
						url : url,
//						async:false,  angularjs 本身结构不支持异步请求
						params : {id:id},
					}).success(function(resultMap) {
						if(null != resultMap && resultMap.success == true){
							alertmessage('删除成功');
							window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/stroke/index?id="+memberId;	
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
