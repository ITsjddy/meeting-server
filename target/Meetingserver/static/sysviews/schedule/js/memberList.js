var memberListApp = angular.module('memberListApp', ['tm.pagination']);
pageService(memberListApp);//初始化分页service
memberListApp.controller("memberListController", function($scope,$http,pageService) {
	$scope.selected = [];
	$scope.myValue = "";
	var type = $("#type").val();
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/memberList?type="+type);
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/memberList?type="+type);
	};
	$scope.checkAll = false;
	$scope.checkAllItem = function($event){
		var checkBox = $event.target;
		if(checkBox.checked){
			angular.forEach($scope.items, function(data,index,array){
				//data等价于array[index]
				//console.log(data.a+'='+array[index].a);
				updateSelected("add",data.id);
			});
		}else{
			angular.forEach($scope.items, function(data,index,array){
				//data等价于array[index]
				//console.log(data.a+'='+array[index].a);
				updateSelected("remove",data.id);
			});
		}
	};
//	$scope.changeMyPage = function (){
//		var selectItems = new Array();
//		if($("#arrIds").val()){
//			var ids = $("#arrIds").val();
//			selectItems = ids.split(",");
//			$scope.selected = selectItems;
//		}
//	}
//	var arr = $("#arrIds").val().split(",");
//	console.log($("#arrIds").val())
//	angular.forEach(arr, function(data,index,array){
//		$scope.selected.push(data);
//	});
//	console.log($scope.selected);
	var updateSelected = function(action, id) {
		if (action == 'add' && $scope.selected.indexOf(id) == -1) {
			$scope.selected.push(id);
		}
		if (action == 'remove' && $scope.selected.indexOf(id) != -1) {
			var idx = $scope.selected.indexOf(id);
			$scope.selected.splice(idx, 1);
		}
		$('input[name="checkIt"]').each(function(){ 
			   if(!$(this).prop("checked")){
				   $scope.checkAll = false;
				   return false;
			   }else{
				   $scope.checkAll = true;
			   }
	     });
	}
	$scope.updateSelection = function($event, id) {
		var checkbox = $event.target;
		var action = (checkbox.checked ? 'add' : 'remove');
		updateSelected(action, id);
	}
	$scope.isSelected = function(id) {
		return $scope.selected.indexOf(id) >= 0;
	}
	$scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
	    //下面是在table render完成后执行的js
		var selectItems = new Array();
		if($("#arrIds").val()){
			var ids = $("#arrIds").val();
			selectItems = ids.split(",");
			$scope.selected = selectItems;
		}
		var checkNum = $('input[name="checkIt"]');//当前页所有的选择框
		if(checkNum){
			$('input[name="checkIt"]').each(function(){ 
				   var val = $(this).val();
				   if($scope.selected.indexOf(val) < 0){
					   $scope.checkAll = false;
					   return false;
				   }else{
					   $scope.checkAll = true;
				   }
		     });
		}
	});
});
memberListApp.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
});