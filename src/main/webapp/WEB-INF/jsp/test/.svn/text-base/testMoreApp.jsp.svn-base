<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>tableTest</title>
	<script src="${ctx }/static/sysviews/test/js/edit.js"></script>
</head>
<body ng-app="testApp" ng-controller="testController">
  <div id="app1div" ng-app="myApp1" ng-controller="personCtrl">
        <p>personCtrl</p>
        First name: <input type="text" ng-model="firstName"><br>
        Last Name: <input type="text" ng-model="lastName"><br>
        Full Name: {{fullName()}}
    </div>

    <br><br>
    <div id="app2div" ng-app="myApp2" ng-controller="personCtrl">
        <p>personCtrl</p>
        First name: <input type="text" ng-model="firstName"><br>
        Last Name: <input type="text" ng-model="lastName"><br>
        Full Name: {{fullName()}}
    </div>

    <script>
        var app1 = angular.module('myApp1', []);
        var app2 = angular.module('myApp2', []);

        app1.controller('personCtrl', function($scope){
            $scope.firstName = 'Aaron';
            $scope.lastName = 'S';
            $scope.fullName = function(){ return $scope.firstName + $scope.lastName; }
        });

        app2.controller('personCtrl', function($scope){
            $scope.firstName = 'Andy';
            $scope.lastName = 'W';
            $scope.fullName = function(){ return $scope.firstName + $scope.lastName; }
        });
        angular.bootstrap(document.getElementById("app2div"), ['myApp2']);
    </script>
</body>
</html>