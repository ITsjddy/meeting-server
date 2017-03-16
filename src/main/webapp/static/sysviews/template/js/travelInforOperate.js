var guestApp = angular.module('guestApp', []);
guestApp.controller("guestController", function($scope, $http) {
    var mainUrl = CONTEXT_PATH + DISPATCHER_PATH + "/travelInforTemplate/";
    
    $scope.guestQueryById = function() {
        var url = mainUrl + "queryfields";
        $http({
            method: 'POST',
            url: url,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
            }
        }).success(function(resultMap) {
            $scope.guest = resultMap;
        }).error(function(error) {
            alert(error);
            if ('403' == error.status) {
                alert('无权限');
            }
        });
    }

    initTemplate();

    $scope.guestSave = function() {
        $scope.template.fieldName = $('#endepartname').val();
        var delurl = mainUrl + "saveEdit";
        var reurl = mainUrl + "index";
        var param = $scope.template;
        console.log(param);
        delnoty(delurl, reurl, param, "保存", $http);
    }

    $scope.changeConHallId = function() {
        var tem = $('#conHallId').val();
        $('#endepartname').val(tem);
    }

    $scope.index = function() {
        window.location.href = mainUrl + "index";
    }

    function initTemplate() {
        var spValue = $("#id").val();
        $http.get(mainUrl + "queryById?id=" + spValue).success(function(response) {
            $scope.template = response;
        });
    }

    $scope.guestQueryById();

});