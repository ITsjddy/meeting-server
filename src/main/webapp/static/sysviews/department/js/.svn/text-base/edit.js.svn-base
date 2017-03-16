var guestApp = angular.module('guestApp', []);
guestApp.controller("guestController", function($scope,$http) {
	$scope.guestQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/department/queryById";
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
			$scope.guest = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.guestSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/department/update";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/department/indexdetial?groupid="+$('#id').val();
		var param = {memberGuest : $scope.guest, arrayGuest : $scope.lguest};
//		delnoty(delurl, reurl,param,"保存",$http);
		parent.noty({
		      text        : '确定保存',
		      type        : 'information',
		      dismissQueue: false,
		      layout      : 'center',
		      theme       : 'relax',
		      modal		:'true',
		      buttons     : [
		          {addClass: 'btn btn-primary', text: '确认', onClick: function ($noty) {
		          	$noty.close();
		          	$http({
						method : 'POST',
						url : delurl,
//						async:false,  angularjs 本身结构不支持异步请求
						params : param,
					}).success(function(resultMap) {
		                  	if(null != resultMap && resultMap.success == true){
		                  		parent.renderTree();
		  						window.location.href = reurl;
		  						parent.noty({dismissQueue: true, force: true, layout: 'topRight', theme: 'defaultTheme', text: '保存成功！', type: 'success',timeout: 2000});	
		  					}else{
		  						parent.noty({dismissQueue: true, force: true, layout: 'topRight', theme: 'defaultTheme', text: '保存错误，请重试！', type: 'error',timeout: 2000});
		  					}
		                  }).error(function(error) {
		                  	if('403' == error.status){
		  						parent.noty({dismissQueue: true, force: true, layout: 'topRight', theme: 'defaultTheme', text: '无权限！', type: 'warning',timeout: 2000});
		  					}
		                  }); 
		          }
		          },
		          {addClass: 'btn btn-danger', text: '取消', onClick: function ($noty) {
		              $noty.close();
		          }
		          }
		      ]
		  });
	}
	/*初始化多语言标识*/
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/department/getLanguage";
		var id = $("#id").val();
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			params :{id : id},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.languageData = resultMap.memberLanguage;
			$scope.lguest = resultMap.lDepartment;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	/* 根据语言类型改变，显示内容 */
    $scope.languageOnChange = function(check,value,i) {
    	$scope.languageData[i].check = check;
    	check == true ? $scope.lguest[i].language = value : $scope.lguest[i].language = '';
    }
    
	/* 删除语言内容 */
    $scope.delGuestDiv = function(i) {
    	if(window.confirm('确定删除此语言?')){
    		$scope.languageData[i].check = false;
        	$scope.languageData[i].languageModel = false;
        	$scope.lguest[i].language = '';
        	parent.noty({dismissQueue: true, force: true, layout: 'topRight', theme: 'defaultTheme', text: $scope.languageData[i].name+'删除成功！', type: 'success',timeout: 2000});	
        }
    }
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/department/indexdetial?groupid="+$('#id').val();
	}
	$scope.guestQueryById();
	$scope.getLanguage();
});
