
var folderApp = angular.module('folderApp', []);
folderApp.controller("folderController", function($scope,$http) {
//	$scope.folderSave = function(){
//		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/folder/save";
//		$http({
//			method : 'POST',
//			url : url,
//			params :$scope.folder,
//		}).success(function(resultMap) {
////			document.documentElement.action = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/folder/index";
//			window.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/folder/index";
//		}).error(function(error) {
//		});
////		window.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/folder/index";
//		
////		document.documentElement.herf();
//	}
	$scope.folderSave = function (){
		var contentHtml = UE.getEditor('editor').getContent();
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/folder/save";
		var reurl= CONTEXT_PATH+"/"+DISPATCHER_PATH+"/folder/index";
		console.log($scope.folder);
		$scope.folder.contentHtml = contentHtml;
		var param = { folder:$scope.folder};
		delnoty(delurl, reurl,param,"新增",$http);
	}
	$scope.folderDetailById = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/folder/folderDetailById";
		var id = $("#folderid").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.folder = resultMap;
		}).error(function(error) {
		});
	}
//	$(document).ready(function() {
//		$("#form").validate({
//			rules:{
//				
//			},
//			 messages:{
//				 
//			 },
//			focusInvalid: false, 
//			  onkeyup: false,   
//			  errorPlacement: function(error, element) {       
//				error.appendTo( element.parent());       
//			}   
//		});
//	    jQuery.validator.addMethod("checkPic", function(value, element) {
//	    	var jg = true;
//	 		if(!value.match(/.doc|.docx|.ppt|.pptx|.txt|.rar|.zip|.xls|.xlsx|.png|.jpg|.bmp|.mpg|.mp4|.flv|.mp3|.avi/i)){
//				jg = true;
//			}
//			return this.optional(element) || jg;
//		}, "上传文件格式不正确");
//		jQuery.validator.addMethod("jyName", function(value, element) { 		    	
//			var zhi=validateName(value,'${dataUpload.id}');
//	        return this.optional(element) || zhi!='1';       
//	    }, "资料名称 已存在!");
//	    
//	    $("#btnSubmit").attr("disabled",false);
//	   
//	    
//	});
//	function index() {
//		document.serqueryForm.action= CONTEXT_PATH+"/"+DISPATCHER_PATH+"/folder/index";
//		document.serqueryForm.submit();
//		return false;
//	}
	
	function checkNull(){
		var ValState = $('#form').valid(); 
		if(ValState==true){
			 //验证上传大小
		    var dom = document.getElementById("filePath");  
		    var fileSize = dom.files[0].size;
		    if(fileSize <=0){
		    	alert("附件大小不能小于0M！"); 
		    	return false;
		    }
			$("#btnSubmit").attr("disabled" ,true);
		}
		return true;
	}
//	function validateName(value,id){
//		var msg = 0;
//		/*唯一性校验*/
//		$.ajax({
//		   async:false,
//		   cache:false,
//		   type:"POST",
//		   dataType : 'json',
//		   data:"name="+value+"&id="+id+"",  //传参  
//		   url: ,
//		   error:function(json){
//		   	msg='Error';
//		   },
//		   timeout:60000,
//		   success:function(json){
//		   	msg=json;
//		   }
//		});
//		
//		return msg;
//	}
	
	$scope.folderDetailById();
});

