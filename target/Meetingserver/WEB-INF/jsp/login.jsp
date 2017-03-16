<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<meta name="description" content="spring mvc, spring security, hibernate 最新版整合">
  	<meta name="keywords" content="spring mvc spring security hibernate">
  	<meta name="author" content="ms">
    <%-- <security:csrfMetaTags/> --%>
  	<base href="${ctx }">
  	
  	<!-- css styles -->
    <link rel="stylesheet" type="text/css" href="${ctx }/static/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <style type="text/css">
      body {
        background:url(images/error/bjerror.png) repeat;
      }
      .box-shadow{  
        position: absolute;
        top: 50%;
        left: 50%;
        padding: 30px 30px;
        margin-left: -200px;
        margin-top: -180px;
        width: 400px;
        height: 360px;
        background: url(../resources/img/1.png) repeat;
        border-radius: 10px;
      } 
      .title{
        border-bottom:1px solid #c7c7c7;margin-bottom:8px; padding-right:10px; font-size:20px;font-weight:bold;
      }
      .error{
        color:red;margin-bottom:8px;height:15px;
      }
    </style>
  	<!-- script -->
  	<script type="text/javascript" src="${ctx }/static/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
		$(document).ready(function() {
			
			username.oninput = function(){
				username.setCustomValidity("");   
			};   
			username.oninvalid = function(){
				username.setCustomValidity("用户名 只能由数字、字母或_下划线组成，长度要在3到15之间！");   
			};
			
		    $("#btnSubmit").attr("disabled",false);
		});
		    	
    </script>
    
  </head>

  <body>
  		
      <div class="box-shadow">
          <div class="title">
            <span>会务管理系统</span><span class="pull-right"><i class="glyphicon glyphicon-tint"></i></span>
          </div>
          <div class="error">
            <span >${SPRING_SECURITY_LAST_EXCEPTION.message}</span>
          </div>
          <form action="${ctx }login" method="post">  
            <div class="input-group">
              <span class="input-group-addon" id="user-addon"><i class="glyphicon glyphicon-user"></i></span>
              <input type="text" pattern="^[0-9a-zA-Z_]{3,15}$" required autofocus class="form-control" id="username" name="username" placeholder="用户名" aria-describedby="user-addon">
            </div>
            <br>
            <div class="input-group">
              <span class="input-group-addon" id="user-addon"><i class="glyphicon glyphicon-lock"></i></span>
              <input type="password" required autofocus class="form-control" id="password" name="password" placeholder="密码" aria-describedby="user-addon">
            </div>
            <br>
            <div>
              <div>
                  <input type="text" class="form-control" style="display:inline-block;width:50%" name="code" placeholder="验证码">&nbsp;&nbsp;
                  <img src="captcha" onclick="this.src='captcha?t='+new Date()*1" height="30" width="70" />
              </div>
            </div>
            <div class="row">
              <div class="col-sm-7 col-md-7 col-xs-12">
                <div class="checkbox">
                  <label>
                    <input type="checkbox" name="remember-me" value="true">
                    <span style="font-size:12px;">一周内记住我</span>
                  </label>
                </div>
              </div>
              <div class="col-sm-5 col-md-5 col-xs-12 ">
                <div class="pull-right" style="margin-top: 10px;margin-bottom: 10px;">
                  <span style="font-size:12px;">忘记密码？</span>
                </div>
              </div>
            </div>
            <%-- <security:csrfInput/> --%>
            <br>
            <button id="btnSubmit" type="submit" class="btn btn-success btn-block">登 录</button>
          </form> 
      </div>
   		<!-- <a href="login.html?locale=zh_CN">简体中文</a>
          <a href="login.html?locale=en_US">English</a> --> 
  </body>

</html>
