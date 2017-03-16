var arrIds = new Array();
var issueApp = angular.module('issueApp', []);
issueApp.controller("issueController", function($scope,$http) {
	//===========参会嘉宾列表上移/下移功能=========================
	$scope.upOrDownMember = function(upOrDown,$index){
		if(upOrDown == 'up'){
			$scope.upRecord(arrIds,$index);//id数组变换位置
			$scope.upRecord($scope.guests,$index);//页面元素变换位置
		}else if(upOrDown == 'down'){
			$scope.downRecord(arrIds,$index);//id数组变换位置
			$scope.downRecord($scope.guests,$index);//页面元素变换位置
		}else if(upOrDown == 'toTop'){
			$scope.toTop(arrIds,$index);
			$scope.toTop($scope.guests,$index);
		}
	}
	var swapItems = function(arr, index1, index2) {
        arr[index1] = arr.splice(index2, 1, arr[index1])[0];
        return arr;
    };
    //置顶
    $scope.toTop = function(arr,$index){
    	var item = arr[$index];
    	arr.splice($index, 1);
    	arr.insert(0,item);
        return arr;
    }
    // 上移
    $scope.upRecord = function(arr, $index) {
        if($index == 0) {
            return;
        }
        swapItems(arr, $index, $index - 1);
    };
    // 下移
    $scope.downRecord = function(arr, $index) {
        if($index == arr.length -1) {
            return;
        }
        swapItems(arr, $index, $index + 1);
    };
    Array.prototype.insert = function (index, item) {
    	  this.splice(index, 0, item);
    };
	//=====================多语言函数==========================
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/issue/toList";
	}
	//=====================多语言函数==========================
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/issue/toList";
	}
	//=================获取议题数据=============
	$scope.getEntityById = function(){
		var url = LINK_PATH+"/issue/getEntityById";
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
			$scope.issue = resultMap.issue;
			$scope.guests = resultMap.guestList;
			$scope.conHallPerson = resultMap.conHall.venueContact;
			$scope.conHallPersonMoblile = resultMap.conHall.venueMobile;
			angular.forEach($scope.guests, function(data,index,array){
				arrIds.push(data.id);
			});
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getEntityById();
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var id = $("#uniqueCode").val();
		var param = {id:id};
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/issue/getLanguage";
		$http({
			method : 'POST',
			url : url,
			params :param,
//			async:false,  angularjs 本身结构不支持异步请求
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.languageData = resultMap.issueLanguage;
			$scope.issueList = resultMap.issueList;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	 $scope.getLanguage();
	//=================获取会场数据===============
	$scope.getConHallList = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/getConHallList";
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
	//=================获取论坛数据===============
	$scope.getScheduleList = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/getScheduleList";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.scheduleList = resultMap.data;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getScheduleList();
	//====================会场改变函数===================
	$scope.changeConHallId = function (){
		if(!$scope.issue.conHallId||$scope.issue.conHallId==''){
			$scope.conHallPerson = '';
			$scope.conHallPersonMoblile = '';
			return false;
		}
		var conHallId = $scope.issue.conHallId;
		angular.forEach($scope.conHallList, function(data,index,array){
			if(data.id==conHallId){
				$scope.conHallPerson = data.venueContact;
				$scope.conHallPersonMoblile = data.venueMobile;
			}
		});
	}
	//============保存函数=================
	$scope.editSave = function(){
		$scope.issue.language = 'zh';
		$scope.issue.memberIds = arrIds;
		$scope.issue.issueStartDate = $("#startDate").val();//时间类型数据绑定有问题，手工赋值
		$scope.issue.issueEndDate = $("#endDate").val();
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/editSave";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/issue/toList";
		var param ={issueMain : $scope.issue, issueList : $scope.issueList};
		delnoty(delurl, reurl,param,"修改",$http);
	}
	
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.issueList[i].language = value : $scope.issueList[i].language = '';
    }
    
	/* 删除语言内容 */
    $scope.delGuestDiv = function(i) {
    	$scope.languageData[i].check = false;
    	$scope.languageData[i].languageModel = false;
    	$scope.issueList[i].language = '';
    }
	//===============操作选嘉宾的函数==================================
	$scope.getSelectedItem = function(){
		var getArrIds = new Array();
		var arrIdsInput = window.frames["selectMember"].document.getElementsByName("ids");
		if(arrIdsInput){
			for(var i=0;i<arrIdsInput.length;i++){
				getArrIds.push(arrIdsInput[i].value);
			}
		}
		if(getArrIds == null||getArrIds.length<=0){
			return;
		}
		if((arrIds==null||arrIds.length==0)&&arrIdsInput){
			for(var i=0;i<arrIdsInput.length;i++){
				arrIds.push(arrIdsInput[i].value);
			}
		}else{
			var getArrIdsTemp = getArrIds.clone();
			var getArrIdsLength = getArrIds.length;
			var countGetArrIds = 0;
			for(var j=0;j<getArrIdsLength;j++){
				if(arrIds.indexOf(getArrIds[j-countGetArrIds])>-1){
					getArrIds.splice(j-countGetArrIds,1);
					countGetArrIds++;
				}
			}
			var arrIdsLength = arrIds.length;
			var countArrIds = 0;
			for(var j=0;j<arrIdsLength;j++){
				if(getArrIdsTemp.indexOf(arrIds[j-countArrIds])<=-1){
					arrIds.splice(j-countArrIds,1);
					countArrIds++;
				}
			}
			arrIds=arrIds.concat(getArrIds);
		}
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/getMemberByIdsOrderByIds";
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
//		$("#selectMember").attr("src",CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/toMemberList");
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
		//$("#selectMember").attr("src",CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/toMemberList?date="+new Date().getTime());
		//selectMember.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/toMemberList?date="+new Date().getTime();//动态设置ifream的src，每次请求都是最新的
		//window.frames["selectMember"].document.getElementById('arrIds').value = arrIds.join(",");
	});
	$scope.deleteFromIds = function($index){
		arrIds.splice($index, 1);
		$scope.guests.splice($index, 1);
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
	Array.prototype.clone = function () { 
		return this.slice(0); 
	};
});
