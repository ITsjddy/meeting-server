<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String strpath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/";
%>
<c:set var="ctx" value="<%=basePath%>" />
<c:set var="strpath" value="<%=strpath%>" />
<c:set var="path" value="dispatcher" />
<script type="text/javascript">
		var CONTEXT_PATH = "${ctx}";
		var DISPATCHER_PATH = "/dispatcher";
		var LINK_PATH = CONTEXT_PATH+DISPATCHER_PATH;
</script>
<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx }/static/angular-1.2.32/angular.min.js"></script>
<script type="text/javascript" src="${ctx }/static/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%-- <script type="text/javascript" src="${ctx }/static/springsecurity/springsecurity.js"></script> --%>
<link rel="stylesheet" type="text/css" href="${ctx }/static/bootstrap/3.3.7/css/bootstrap.min.css"/>
<script type="text/javascript" src="${ctx }/js/zebra_dialog/zebra_dialog.js"></script>
<link rel="stylesheet" href="${ctx }/css/zebra_dialog/zebra_dialog.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="${ctx }/js/noty/layouts/center.js"></script>
<script type="text/javascript" src="${ctx }/js/noty/themes/default.js"></script>
<script src="${ctx }/js/commonutil.js"></script>