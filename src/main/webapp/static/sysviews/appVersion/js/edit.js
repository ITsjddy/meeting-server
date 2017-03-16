var appVersionApp = angular.module('appVersionApp', ['ngMessages']);
appVersionApp.controller("appVersionController",
function($scope, $http) {
	
  $scope.changeAppVersionType = function() {
    var type = $scope.appVersion.type;
    androidIos(type);
    var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/getMaxVersionNum";
    $http({
      method: 'POST',
      url: url,
      params: {
        type: type
      },
    }).success(function(resultMap) {
      $scope.appVersion.versionNum = resultMap.versionNum;
    }).error(function(error) {
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  }

  $scope.appVersionFindById = function() {
    var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/appVersionFindById";
    var id = $("#id").val();
    var param = {
      id: id
    };
    $http({
      method: 'POST',
      url: url,
      params: param,
    }).success(function(resultMap) {
      $scope.appVersion = resultMap;
      androidIos($scope.appVersion.type);
    }).error(function(error) {

});
  }

  function androidIos(type) {
    var apkDiv = document.getElementById("apkDiv");
    var ipaDiv = document.getElementById("ipaDiv");
    var plistDiv = document.getElementById("plistDiv");
    var picOneDiv = document.getElementById("picOneDiv");
    var picTwoDiv = document.getElementById("picTwoDiv");
    if (null != type && "" != type && type == 2 || type == "2") {
      plistDiv.style.display = "block";
      picOneDiv.style.display = "block";
      picTwoDiv.style.display = "block";
      ipaDiv.style.display = "block";
      apkDiv.style.display = "none";
    } else if (type == 1 || type == "1") {
      plistDiv.style.display = "none";
      picOneDiv.style.display = "none";
      picTwoDiv.style.display = "none";
      ipaDiv.style.display = "none";
      apkDiv.style.display = "block";
    } else {
      plistDiv.style.display = "none";
      picOneDiv.style.display = "none";
      picTwoDiv.style.display = "none";
      ipaDiv.style.display = "none";
      apkDiv.style.display = "none";
    }
  }
  $scope.appVersionFindById();

  $scope.appVersionEditSave = function() {
    //获取到表单是否验证通过
    if (!$scope.form.$valid) {
      alert('表单没有通过验证');
      return;
    }

    var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/appVersionAddSave";
    var fd = new FormData();
    var files = document.querySelectorAll('input[type=file]');
    fd.append('id', $scope.appVersion.id);
    fd.append('type', $scope.appVersion.type);
    fd.append('versionName', $scope.appVersion.versionName);
    fd.append('versionNum', $scope.appVersion.versionNum);
    fd.append('remark', $scope.appVersion.remark);
    fd.append('path', $('#pathURL').val());
    fd.append('qrCode', $('#qrCodeURL').val());
    fd.append('plist', $('#plistURL').val());
    fd.append('picOne', $('#picOneURL').val());
    fd.append('picTwo', $('#picTwoURL').val());
    $http({
      method: 'POST',
      url: url,
      data: fd,
      headers: {
        'Content-Type': undefined
      },
      transformRequest: angular.identity
    }).success(function(resultMap) {
      if (resultMap.success) {
        $scope.backPage();
      } else {
        alert('保存失败');
      }

    }).error(function(error) {
      alert('无权限');
    });
  }

  /*返回*/
  $scope.backPage = function() {
    window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/index";
  }
});

window.onload = function() {
	//上传 -- Mr.Yi  下载二维码地址qrCode
	$("#qrCode").uploadify({
		'uploader'       : CONTEXT_PATH+'/static/upload/js/uploadify.swf',
		'script'         : LINK_PATH+'/uploadIMG/upload',
		'cancelImg'      : CONTEXT_PATH+'/static/upload/js/cancel.png',
		'buttonImg'		 : CONTEXT_PATH+'/static/upload/js/xzButton.gif',
		'queueID'        : 'qrCodeFileQueue',	//queueID是页面上容器的id，这个是前端参数，是用来指定进度条出现的位置
		'auto'           : false,
		'multi'          : false,
		'fileDataName'   : 'uploadify',
		'wmode'			 : 'transparent',
		'scriptData'	 : {folderName:'appVersion\\InCo_Ewm_hy'},
		'simUploadLimit' : 1,
		'fileExt'		 : '*.png;*.jpg;*.jpeg',
		'fileDesc'		 : '图片(*.png;*.jpg;*.jpeg)',
		'onSelectOnce' : function(event,data,data)
        {
          filesSelected:true;
        },
        'onComplete' : function(event,queueId,fileObj,response,data)
        {
        	$('#qrCodeURL').val(response);
        	var p = CONTEXT_PATH + response;
        	var str = p.replace("Meetingserver","");
            var $image=$("<img id='imgvo' src=" + str + "  width='70px' height='70px'/>");
            $("#imageQrCode").html($image);
        } 
	});
	//上传 -- Mr.Yi
	//  上传安装包.APK,.apk
	$("#APK_path").uploadify({
		'uploader'       : CONTEXT_PATH+'/static/upload/js/uploadify.swf',
		'script'         : LINK_PATH+'/uploadIMG/upload',
		'cancelImg'      : CONTEXT_PATH+'/static/upload/js/cancel.png',
		'buttonImg'		 : CONTEXT_PATH+'/static/upload/js/xzButton.gif',
		'queueID'        : 'ApkFileQueue',	//queueID是页面上容器的id，这个是前端参数，是用来指定进度条出现的位置
		'auto'           : false,
		'multi'          : false,
		'fileDataName'   : 'uploadify',
		'wmode'			 : 'transparent',
		'scriptData'	 : {folderName:'appVersion\\InCo_Android_zh'},
		'simUploadLimit' : 1,
		'fileExt'		 : '*.APK',
		'fileDesc'		 : '图片(*.APK)',
		'onSelectOnce' : function(event,data,data)
		{
			filesSelected:true;
		},
		'onComplete' : function(event,queueId,fileObj,response,data)
		{
			$('#pathURL').val(response);
			var p = CONTEXT_PATH + response;
			var str = p.replace("Meetingserver","");
			var $image=$("<img id='imgvo' src=" + str + "  width='70px' height='70px'/>");
			$("#imageApk").html($image);
		} 
	});
	//  上传安装包ipa
	$("#IPA_path").uploadify({
		'uploader'       : CONTEXT_PATH+'/static/upload/js/uploadify.swf',
		'script'         : LINK_PATH+'/uploadIMG/upload',
		'cancelImg'      : CONTEXT_PATH+'/static/upload/js/cancel.png',
		'buttonImg'		 : CONTEXT_PATH+'/static/upload/js/xzButton.gif',
		'queueID'        : 'IpaFileQueue',	//queueID是页面上容器的id，这个是前端参数，是用来指定进度条出现的位置
		'auto'           : false,
		'multi'          : false,
		'fileDataName'   : 'uploadify',
		'wmode'			 : 'transparent',
		'scriptData'	 : {folderName:'appVersion\\InCo_Ios_zh'},
		'simUploadLimit' : 1,
		'fileExt'		 : '*.ipa',
		'fileDesc'		 : '图片(*.ipa)',
		'onSelectOnce' : function(event,data,data)
		{
			filesSelected:true;
		},
		'onComplete' : function(event,queueId,fileObj,response,data)
		{
			$('#pathURL').val(response);
			var p = CONTEXT_PATH + response;
			var str = p.replace("Meetingserver","");
			var $image=$("<img id='imgvo' src=" + str + "  width='70px' height='70px'/>");
			$("#imageIpa").html($image);
		} 
	});
	//  ios下载plsit文件
	$("#plist").uploadify({
		'uploader'       : CONTEXT_PATH+'/static/upload/js/uploadify.swf',
		'script'         : LINK_PATH+'/uploadIMG/upload',
		'cancelImg'      : CONTEXT_PATH+'/static/upload/js/cancel.png',
		'buttonImg'		 : CONTEXT_PATH+'/static/upload/js/xzButton.gif',
		'queueID'        : 'plistFileQueue',	//queueID是页面上容器的id，这个是前端参数，是用来指定进度条出现的位置
		'auto'           : false,
		'multi'          : false,
		'fileDataName'   : 'uploadify',
		'wmode'			 : 'transparent',
		'scriptData'	 : {folderName:'appVersion\\gjhy'},
		'simUploadLimit' : 1,
		'fileExt'		 : '*.list',
		'fileDesc'		 : '图片(*.list)',
		'onSelectOnce' : function(event,data,data)
		{
			filesSelected:true;
		},
		'onComplete' : function(event,queueId,fileObj,response,data)
		{
			$('#plistURL').val(response);
			var p = CONTEXT_PATH + response;
			var str = p.replace("Meetingserver","");
			var $image=$("<img id='imgvo' src=" + str + "  width='70px' height='70px'/>");
			$("#imagePlist").html($image);
		} 
	});
	//  ios下载显示图片（大）
	$("#picOne").uploadify({
		'uploader'       : CONTEXT_PATH+'/static/upload/js/uploadify.swf',
		'script'         : LINK_PATH+'/uploadIMG/upload',
		'cancelImg'      : CONTEXT_PATH+'/static/upload/js/cancel.png',
		'buttonImg'		 : CONTEXT_PATH+'/static/upload/js/xzButton.gif',
		'queueID'        : 'picOneFileQueue',	//queueID是页面上容器的id，这个是前端参数，是用来指定进度条出现的位置
		'auto'           : false,
		'multi'          : false,
		'fileDataName'   : 'uploadify',
		'wmode'			 : 'transparent',
		'scriptData'	 : {folderName:'appVersion\\InCo_Pic_hy_1'},
		'simUploadLimit' : 1,
		'fileExt'		 : '*.png;*.jpg;*.jpeg',
		'fileDesc'		 : '图片(*.png;*.jpg;*.jpeg)',
		'onSelectOnce' : function(event,data,data)
		{
			filesSelected:true;
		},
		'onComplete' : function(event,queueId,fileObj,response,data)
		{
			$('#picOneURL').val(response);
			var p = CONTEXT_PATH + response;
			var str = p.replace("Meetingserver","");
			var $image=$("<img id='imgvo' src=" + str + "  width='70px' height='70px'/>");
			$("#imagePicOne").html($image);
		} 
	});
	//  ios下载显示图片（小）
	$("#picTwo").uploadify({
		'uploader'       : CONTEXT_PATH+'/static/upload/js/uploadify.swf',
		'script'         : LINK_PATH+'/uploadIMG/upload',
		'cancelImg'      : CONTEXT_PATH+'/static/upload/js/cancel.png',
		'buttonImg'		 : CONTEXT_PATH+'/static/upload/js/xzButton.gif',
		'queueID'        : 'picTwoFileQueue',	//queueID是页面上容器的id，这个是前端参数，是用来指定进度条出现的位置
		'auto'           : false,
		'multi'          : false,
		'fileDataName'   : 'uploadify',
		'wmode'			 : 'transparent',
		'scriptData'	 : {folderName:'appVersion\\InCo_Pic_hy_2'},
		'simUploadLimit' : 1,
		'fileExt'		 : '*.png;*.jpg;*.jpeg',
		'fileDesc'		 : '图片(*.png;*.jpg;*.jpeg)',
		'onSelectOnce' : function(event,data,data)
		{
			filesSelected:true;
		},
		'onComplete' : function(event,queueId,fileObj,response,data)
		{
			$('#picTwoURL').val(response);
			var p = CONTEXT_PATH + response;
			var str = p.replace("Meetingserver","");
			var $image=$("<img id='imgvo' src=" + str + "  width='70px' height='70px'/>");
			$("#imagePicTwo").html($image);
		} 
	});
	//
};