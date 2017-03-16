var sysGroupApp = angular.module('sysGroupApp', ['tm.pagination']);
pageService(sysGroupApp);//初始化分页service
sysGroupApp.controller("sysGroupController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/pageQuery");
	};
	$scope.sysGroupDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
	$scope.sysGroupDeleteAll = function(){
		if($scope.choseArr[0]==""||$scope.choseArr.length==0){//没有选择一个的时候提示
	        alert("请至少选中一条数据在操作！")
	        return;
	    };
	    var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/deleteList";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/index";
		var param = {ids:$scope.choseArr};
		delnoty(delurl, reurl,param,"删除",$http);
	}
	
	$scope.tesarry=['1','2','3','4','5'];//初始化数据
	$scope.choseArr=[];//定义数组用于存放前端显示
	var str="";//
	var flag='';//是否点击了全选，是为a
	$scope.x=false;//默认未选中
	 
	$scope.all= function (c,v) {//全选  
	    if(c==true){  
	        $scope.x=true;  
	        $scope.choseArr=[""];  
	    }else{  
	        $scope.x=false;  
	        for(var i=0;i<v.length;i++){  
	            $scope.choseArr[i]=''+v[i].id+'';  
	        }  
	    }  
	    flag='a';  
	    console.log($scope.choseArr);  
	};  
	$scope.chk= function (z,x) {//单选或者多选  
	    if(flag=='a') {//在全选的基础上操作  
	        str = $scope.choseArr.join(',') + ',';  
	    }  
	    if (x == true) {//选中  
	        str = str + z + ',';  
	    } else {  
	        str = str.replace(z + ',', '');//取消选中  
	    }  
	    $scope.choseArr=(str.substr(0,str.length-1)).split(',');  
	    console.log($scope.choseArr);  
	}; 
	$scope.deleteAll= function () {// 操作CURD
	 
	    if($scope.choseArr[0]==""||$scope.choseArr.length==0){//没有选择一个的时候提示
	        alert("请至少选中一条数据在操作！")
	        return;
	    };
	    alert($scope.choseArr);
	    for(var i=0;i<$scope.choseArr.length;i++){
	        console.log($scope.choseArr[i]);//遍历选中的id
	    }
	};
	
    
});
