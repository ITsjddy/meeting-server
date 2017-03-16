var questionnaireApp = angular.module('questionnaireApp', ['tm.pagination']);
pageService(questionnaireApp);//初始化分页service
questionnaireApp.controller("questionnaireController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/questionnaire/queryList");
	
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/questionnaire/queryList");
	};
});
