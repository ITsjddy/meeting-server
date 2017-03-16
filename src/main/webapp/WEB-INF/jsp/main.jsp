<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
    <title>会务系统APP管理后台</title>
    <link href="${ctx }/css/main/main.css" rel="stylesheet">
    <link href="${ctx }/css/main/menu.css" rel="stylesheet">
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div id="content">
		<div id="texttop">
			<div id="logo">
				<img src="${ctx }/images/logo.png" alt="logo" class="img-responsive">
				<span class="system-name" id="menuclick"><img src="${ctx }/images/sidebar.jpg" class="img-responsive"></span>
				<span class="system-name">会务产品系统APP管理后台</span>
			</div>
		</div>
		<div id="textcenter">
			<div id="leftcontent">
				<div id="firstpane" class="menu_list" style="height: 568px">
				</div>
			</div>
			<div id="rightcontent">
				<iframe id="mainFrame" name="mainFrame" src="${ctx }${path }/guest/index" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="630"></iframe>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/menu/getmenu.js"></script>
	<script type="text/javascript">
	var context = "${ctx}";
	function loadhtml(hrefname){
		$("#mainFrame").attr("src", CONTEXT_PATH+hrefname);
	}
    $(document).ready(function(){
    	$("#mainFrame").attr("src", CONTEXT_PATH+DISPATCHER_PATH+'/guest/index');
    	$("#menuclick").bind("click",function(){
    	var test = $("#leftcontent").css("width");
    	if(test=='0px'){
    		$("#leftcontent").css("width","15%");
    		$("#rightcontent").css("width","85%");
    	}else{
    		$("#leftcontent").css("width","0%");
    		$("#rightcontent").css("width","100%");
    	}
    	});	
	  });
    </script>
</body>

</html>
