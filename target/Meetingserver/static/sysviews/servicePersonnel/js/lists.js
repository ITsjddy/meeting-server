var guestApp = angular.module('servicePersonnelApp', ['tm.pagination']);
pageService(guestApp);//��ʼ����ҳservice
guestApp.controller("servicePersonnelController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/pageQuery");
	};
	$scope.guestDelete = function(id){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/deleteById";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs ����ṹ��֧���첽����
			params : {id:id},
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				alert('ɾ���ɹ���');
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
			}else{
				alert('ɾ�����������ԣ�');
			}
		}).error(function(error) {
			if('403' == error.status){
				alert('��Ȩ��');
			}
		});
	}
});
