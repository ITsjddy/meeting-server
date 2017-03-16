angular.module("demo-statechange").controller("demo-statechange_navCtrl", [ "$q", "$scope", "$stateParams", "$location", "$state", function($q, $scope, $stateParams, $location, $state) {
	var states = [ {
		name : "全部申请",
		value : "1",
		substates : [ {
			name : "全部",
			value : "1"
		}, {
			name : "审批中",
			value : "2"
		}, {
			name : "已完成",
			value : "3"
		}, {
			name : "草稿",
			value : "4"
		} ]
	}, {
		name : "待我审批",
		value : "2",
		substates : []
	}, {
		name : "我提交的",
		value : "3",
		substates : [ {
			name : "全部",
			value : "1"
		}, {
			name : "审批中",
			value : "2"
		}, {
			name : "已完成",
			value : "3"
		}, {
			name : "草稿",
			value : "4"
		} ]
	} ];

	var initState = function(p){
		var pattern = p || "1.1;";
		var patternTmps = pattern.split(";");
		var statesTmps = patternTmps[0].split(".");
		goToState(statesTmps[0], statesTmps[1]);
	}
	var findActive = function(list) {
		if (!list) {
			return null;
		}
		for (var i = 0; i < list.length; i++) {
			if (list[i].flag == true)
				return list[i];
		}
		return list[0];
	}
	var findActiveIndex = function(list) {
		if (!list) {
			return 0;
		}
		for (var i = 0; i < list.length; i++) {
			if (list[i].flag == true)
				return i;
		}
		return 0;
	}
	var goToSubState = function(j) {
		var subStates = $scope.activeState.substates;
		var activeSubState = findActive(subStates);
		if (subStates && j != undefined && j > 0 && j <= subStates.length) {
			activeSubState.flag = false;
			subStates[j - 1].flag = true;
		} else {
			if (activeSubState)
				activeSubState.flag = true;
		}
	}
	var goToState = function(i, j) {
		console.log("once");
		$scope.activeState = states[i - 1];
		goToSubState(j);
	}
	
	$scope.states = states;
	
	$scope.goToUrl = function(i, j) {
		var _i = i;
		var _j = j || (findActiveIndex(states[i-1])+1);
		$location.path("/statechange/filter/"+_i+"."+_j+";")
	}

	initState($stateParams.pattern)
	
} ])
.controller("demo-statechange_listCtrl", [ "$q", "$scope", "$stateParams", "$location", function($q, $scope, $stateParams,$location) {
	$scope.list = [ 1, 2, 3 ]
	console.log("controller reEnterd,scope change")
	
	console.log("enterd:"+$scope)
	
	$scope.$on('$iqStateEnter', function(a,newStateParams,c) {

		console.log("when state enter fired:"+$scope)
	})
	

	$scope.gotoUrl = function(url){
		$location.path(url)
	}
	
} ])