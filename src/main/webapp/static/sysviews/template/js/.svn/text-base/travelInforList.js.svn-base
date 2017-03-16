var guestApp = angular.module('guestApp', ['tm.pagination']);
pageService(guestApp); //初始化分页service
guestApp.controller("guestController", function($scope, $http, pageService) {
	
    var mainUrl = CONTEXT_PATH + DISPATCHER_PATH + "/travelInforTemplate/";
    
    pageService.queryForList($scope, $http, mainUrl + "pageQuery");

    $scope.searchByParam = function() {
        pageService.reGetDataForList($scope, $http, mainUrl + "pageQuery");
    };

    $scope.guestDelete = function(id) {
        var delurl = mainUrl + "deleteById";
        var reurl = mainUrl + "index";
        var param = {
            id: id
        };
        delnoty(delurl, reurl, param, "删除", $http);
    }

});