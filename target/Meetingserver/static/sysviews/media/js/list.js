var mediaApp = angular.module('mediaApp', ['tm.pagination']);
pageService(mediaApp);//初始化分页service
mediaApp.controller("mediaController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/media/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/media/pageQuery");
	};
//	angular.module('MediaModule').controller('mediaController',
//	        function($scope, MediaService ,DeleteService) {
//	            /*加载全部信息*/
//	            $scope.loadAll = function() {
//	            	MediaService.get(function(result) {
//	                    $scope.media = result.result.resultObj.list;
//	                    var MediaIds = [] ;
//	                    for(var i = 0 ;i<$scope.media.length ;i++){
//	                        MediaIds[i]=$scope.media[i].mediaId;
//	                    };
//	                    $scope.images = MediaIds;
//	                });
//	            };
//	            $scope.loadAll();
//	            /*定义id把复选框选中的值放进去*/
//	            $scope.chk = false;
//	            var id ="";
//	            $scope.check= function(val,chk){
//	                if(!chk == true){
//	                  id += val+",";
//	                }else{
//	                  id = id.replace(val+",","");
//	                }
//	            };
//	            /*删除操作*/
//	            $scope.deleteMedia= function(){
//	                DeleteService.deleteMedia({id: id},
//	                    function () {
//	                        $scope.loadAll();
//	                });
//	            };
//	            $scope.update=function(){
//	                 
//	            };
//	        });
//	$scope.mediaDelete= function () {// 操作CURD
//		 
//	    if($scope.resultMap==""||$scope.resultMap.length==0){//没有选择一个的时候提示
//	        alert("请至少选中一条数据在操作！")
//	        return; g
//	    };
	
//    $scope.choseArr=[];//定义数组用于存放前端显示  
//    var str="";//  
//    var flag='';//是否点击了全选，是为a  
//    $scope.x=false;//默认未选中  
//       
//    $scope.all= function (c,v) {//全选  
//        if(c==true){  
//            $scope.x=true;  
//            $scope.choseArr=[""];  
//        }else{  
//            $scope.x=false;  
//            for(var i=0;i<v.length;i++){  
//                $scope.choseArr[i]=''+v[i].id+'';  
//            }  
//        }  
//       
//        flag='a';  
//     console.log($scope.choseArr);  
//    };  
//    $scope.chk= function (z,x) {//单选或者多选  
//        if(flag=='a') {//在全选的基础上操作  
//            str = $scope.choseArr.join(',') + ',';  
//        }  
//        if (x == true) {//选中  
//            str = str + z + ',';  
//        } else {  
//            str = str.replace(z + ',', '');//取消选中  
//        }  
//       
//        $scope.choseArr=(str.substr(0,str.length-1)).split(',');  
//     console.log($scope.choseArr);  
//    };  
//    $scope.deletes= function () {// 操作CURD  
//          
//        if($scope.choseArr[0]==""||$scope.choseArr.length==0){//没有选择一个的时候提示  
//            alert("请至少选中一条数据在操作！")  
//            return;  
//        };  
//       
//        for(var i=0;i<$scope.choseArr.length;i++){  
//            //alert($scope.choseArr[i]);  
//            console.log($scope.choseArr[i]);//遍历选中的id  
//        }  
//    };  
	
	$scope.mediaDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/media/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/media/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
}
});
