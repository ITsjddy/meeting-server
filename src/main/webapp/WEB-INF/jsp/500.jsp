<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0" />
<title></title>

<link type="text/css" rel="stylesheet" href="${ctx}/css/error/error.css" />

</head>
<body style="background:url(${ctx}/images/error/bjerror.png) repeat;">
<div class="cwerror">
  <div class="cwerror_top">
    <div class="top_left"><img src="${ctx}/images/error/cwerror.png"></div>
    
    <div class="top_right">500</div>
  </div>
  <div class="cwerror_nr">
    <div class="nr_top">system error</div>
    
    <div class="nr_min" id="text">
      <div class="min_top">You can choose:</div>
      
      <div class="min_choose"><a href="javascript:history.go(-1);">返回</a></div>
      
    </div>
    
  </div>
</div>
</body>  
</html>
