var arrIds = new Array();
var scheduleApp = angular.module('scheduleApp', []);
scheduleApp.controller("scheduleController", function($scope,$http) {
	//=====================多语言函数==========================
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/schedule/toList";
	}
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/schedule/getLanguage";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.languageData = resultMap.scheduleLanguage;
			$scope.scheduleList = resultMap.scheduleList;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	//=================获取会场数据===============
	$scope.getConHallList = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/getConHallList";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.conHallList = resultMap.data;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getConHallList();
	//====================会场改变函数===================
	$scope.changeConHallId = function (){
		if(!$scope.schedule.conHallId||$scope.schedule.conHallId==''){
			$scope.conHallPerson = '';
			$scope.conHallPersonMoblile = '';
			return false;
		}
		var conHallId = $scope.schedule.conHallId;
		angular.forEach($scope.conHallList, function(data,index,array){
			if(data.id==conHallId){
				$scope.conHallPerson = data.venueContact;
				$scope.conHallPersonMoblile = data.venueMobile;
			}
		});
	}
	//============保存函数=================
	$scope.addSave = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/addSave";
		$scope.schedule.language = 'zh';
		$scope.schedule.memberIds = arrIds;
		$scope.schedule.startDate = $("#startDate").val();//时间类型数据绑定有问题，手工赋值
		$scope.schedule.endDate = $("#endDate").val();
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/addSave";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/schedule/toList";
		var param ={scheduleMain : $scope.schedule, scheduleList : $scope.scheduleList};
		delnoty(delurl, reurl,param,"新增",$http);
	}
	
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.scheduleList[i].language = value : $scope.scheduleList[i].language = '';
    }
    
	/* 删除语言内容 */
    $scope.delGuestDiv = function(i) {
    	$scope.languageData[i].check = false;
    	$scope.languageData[i].languageModel = false;
    	$scope.scheduleList[i].language = '';
    }
    $scope.getLanguage();
	//===============操作选嘉宾的函数==================================
	$scope.getSelectedItem = function(){
		arrIds = new Array();
		var arrIdsInput = window.frames["selectMember"].document.getElementsByName("ids");
		if(arrIdsInput){
			for(var i=0;i<arrIdsInput.length;i++){
				arrIds.push(arrIdsInput[i].value);
			}
		}
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/getMemberByIds";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			params :{ids:arrIds},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				$scope.guests = resultMap.data;
			}else{
			}
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
		$("#myModal").modal('hide');
//		$('#myModal').modal({backdrop: 'static', keyboard: false});
//		backdrop:static时,空白处不关闭.
//		keyboard:false时,esc键盘不关闭.
	};
	//打开模态块触发事件
//	show.bs.modal         show 方法调用之后立即触发该事件。如果是通过点击某个作为触发器的元素，则此元素可以通过事件的relatedTarget 属性进行访问。    
//	shown.bs.modal    此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发。如果是通过点击某个作为触发器的元素，则此元素可以通过事件 的 relatedTarget 属性进行访问。    
//	hide.bs.modal        hide 方法调用之后立即触发该事件。    
//	hidden.bs.modal    此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。    
//	loaded.bs.modal    从远端的数据源加载完数据之后触发该事件。 
	$('#myModal').on('show.bs.modal', function () {
//		$("#selectMember").attr("src",CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/toMemberList");
		selectMember.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/toMemberList?type=guest";//动态设置ifream的src，每次请求都是最新的
		$("#selectMember").load(function() { 
			window.frames["selectMember"].document.getElementById('arrIds').value = arrIds.join(","); 
		}); 
		//$scope.sleep(1000);
		//<input id="arrIds" type="hidden"  value="">
		//var ifreamBody = window.frames["selectMember"].document.body;
		//ifreamBody.innerHTML="<input id='arrIds' type='hidden'  value='"+arrIds.join(",")+"'>"+ifreamBody.innerHTML;
		//console.log(ifreamBody.innerHTML);
		//window.frames[0].location.href=window.frames[0].location.href+'?new='+Math.random();
		//window.frames["selectMember"].fun();
	});
	$('#myModal').on('hide.bs.modal', function () {
		//$("#selectMember").attr("src",CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/toMemberList?date="+new Date().getTime());
		//selectMember.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/toMemberList?date="+new Date().getTime();//动态设置ifream的src，每次请求都是最新的
		//window.frames["selectMember"].document.getElementById('arrIds').value = arrIds.join(",");
	});
	$scope.deleteFromIds = function(id){
		arrIds.remove(id);
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/getMemberByIds";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			params :{ids:arrIds},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				$scope.guests = resultMap.data;
				//selectMember.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/toMemberList";//动态设置ifream的src，每次请求都是最新的
				//selectMember.window.document.getElementById("arrIds").value=arrIds.join(",");
//				selectMember.window.document.getElementById("arrIds").value=arrIds.join(",");
			}else{
			}
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	Array.prototype.indexOf = function(val) {
		for (var i = 0; i < this.length; i++) {
			if (this[i] == val) return i;
		}
		return -1;
	};
	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index, 1);
		}
	};
});
