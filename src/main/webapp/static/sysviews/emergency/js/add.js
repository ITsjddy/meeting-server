var emergencyApp = angular.module('emergencyApp', []);
function delimg(){
	$.ajax({  
        type : "POST",  //提交方式  
        url : LINK_PATH + '/uploadIMG/delImg',//路径  
        data : {  
        	imgURL : $('#imaggo').val()  
        },
        success : function(result) {
        	$('#imaggo').val("");
        	$("#imagesTO").empty();
        }  
    });  
}
emergencyApp.controller("emergencyController", function($scope, $http) {
	
		//上传 -- Mr.Yi
		$("#uploadify").uploadify({
			'uploader'       : CONTEXT_PATH+'/static/upload/js/uploadify.swf',
			'script'         : LINK_PATH+'/uploadIMG/upload',
			'cancelImg'      : CONTEXT_PATH+'/static/upload/js/cancel.png',
			'buttonImg'		 : CONTEXT_PATH+'/static/upload/js/xzButton.gif',
			'queueID'        : 'fileQueue',
			'auto'           : false,
			'multi'          : false,
			'fileDataName'   : 'uploadify',
			'wmode'			 : 'transparent',
			'scriptData'	 : {folderName:'emergency'},
			'simUploadLimit' : 6,
			'fileExt'		 : '*.png;*.gif;*.jpg;*.bmp;*.jpeg',
			'fileDesc'		 : '图片(*.png;*.gif;*.jpg;*.bmp;*.jpeg)',
			'onSelectOnce' : function(event,data,data)
	        {
	          filesSelected:true;
	        },
	        'onComplete' : function(event,queueId,fileObj,response,data)
	        {
	        	$('#imaggo').val(response);
	        	var p = CONTEXT_PATH + response;
	        	var str = p.replace("Meetingserver","");
	            var $image=$("<img id='imgvo' src=" + str + " width='70px' height='70px'/>");
	           // $("#imagesTO").html($image);
	            $image.appendTo("#imagesTO");
	        }
		});
		//上传 -- Mr.Yi
	
	$scope.emergencyTypesData = [ {
		id : "A",
		content : "A类"
	}, {
		id : "B",
		content : "B类"
	}, {
		id : "C",
		content : "C类"
	} ];
	
    $scope.emergencyAddSave = function() {
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/emergency/emergencyAddSave";
		var fd = new FormData();
		//var files = document.querySelectorAll('input[type=file]');
		var postData = {
			emergencyTel : $scope.emergency.emergencyTel,
			emergencyType : $scope.emergency.emergencyType,
			emergencyPic : $('#imaggo').val()
		};
		angular.forEach(postData, function(val, key) {
			fd.append(key, val);
		});
		$http({
			method : 'POST',
			url : url,
			data : fd,
			headers : {
				'Content-Type' : undefined
			},
			transformRequest : angular.identity
		}).success(function(response) {
		}).error(function(error) {
		});

		window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/emergency/index";
	}
    
    /*返回*/
    $scope.backPage = function() {
        window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/emergency/index";
    }
}).directive('ngTlogos', function($http) {
	return {
		restrict : "EACM",
		require: '?ngModel',
		link : function(scope, elements, attrs, controller) {
			elements[0].onblur = function() {
				var pattern = /^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
				if(!pattern.test(this.value)){
					tel.setCustomValidity("应急电话格式不正确！");
					return;
				}
			}
		}
	};
});
