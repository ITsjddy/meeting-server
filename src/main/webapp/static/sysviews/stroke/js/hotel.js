var guestApp = angular.module('strokeApp', []);
guestApp.controller("strokeController", function($scope,$http) {
	$scope.guestQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/memberHotel/queryAll";
		var id = $("#id").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.lhotel = resultMap.hotelList;
			$scope.hotel = resultMap.memberHotel;
			console.info($scope.lhotel);
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.strokeSave = function(){
		var id = $("#id").val();
//		alert(id);
		$scope.hotel.checkInTime=$("#checkInTime").val();
		$scope.hotel.checkOutTime=$("#checkOutTime").val();
		var date1 = $("#checkInTime").val();
		var date2=  $("#checkOutTime").val();
		 var oDate1 = new Date(date1);
	       var oDate2 = new Date(date2);
		if(oDate1.getTime() > oDate2.getTime()){
			messagenoty('开始时间大于结束时间，请重新选择！', 'error', 'center', '1500');
      	return false;
      } 
		$scope.hotel.memberId=$("#id").val();
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/memberHotel/update";
		var reurl = "";
//		$scope.stroke.language = 'zh';
//		$scope.stroke.groupId = $('#groupidId').val();
		var param = $scope.hotel;
		delnoty(delurl, reurl,param,"保存",$http);
//		var url = CONTEXT_PATH+DISPATCHER_PATH+"/memberHotel/update";
//		$http({
//			method : 'POST',
//			url : url,
////			async:false,   angularjs 本身结构不支持异步请求
//			params :$scope.hotel,
//			headers : {
//				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
//			}
//		}).success(function(resultMap) {
//			alert("保存成功!");
////			if(null != resultMap && resultMap.success == true){
////				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/memberHotel/index?id="+id;
////			}else{
////				alert('信息修改失败，请重试！');
////			}
//		}).error(function(error) {
//			alert(error.status);
//			if('403' == error.status){
//				alert('无权限');
//			}
//		});
	}
	$scope.index = function(){
		var id = $("#id").val();
//		alert(id);
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/memberHotel/index?id="+id;
	}
	$scope.guestQueryById();
});
