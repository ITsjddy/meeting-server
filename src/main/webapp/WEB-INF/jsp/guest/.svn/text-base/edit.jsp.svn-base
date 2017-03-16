<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="author" content="ms"/>
<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />
<title>info</title>
<style type="text/css">
/* query style */
.inputQry{
	background:url(${ctx}/images/query.png) no-repeat 180px 3px;
	background-color:#FEFFD6;
	cursor:pointer;
	width: 200px;
}
</style>
<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
<!-- 引入上传的css -->
<link rel="stylesheet" href="${ctx }/css/upload/upload.css" type="text/css">
<!-- 引入上传的js -->
<script type="text/javascript" src="${ctx }/static/upload/js/swfobject.js"></script>
<script type="text/javascript" src="${ctx }/static/upload/js/jquery.uploadify.v2.1.0.min.js"></script>
<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
<c:choose>
	<c:when test="${empty id}">
		<script src="${ctx }/static/sysviews/guest/js/add.js"></script>
	</c:when>
	<c:otherwise>
		<script src="${ctx }/static/sysviews/guest/js/edit.js"></script>
	</c:otherwise>
</c:choose>
</head>
<body ng-app="guestApp" ng-controller="guestController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li><strong>
		<c:choose>
		    <c:when test="${empty id}">嘉宾信息添加</c:when>
		    <c:otherwise>嘉宾信息修改</c:otherwise>   
		</c:choose>
	</strong></li>
</ul>
<div id="wrapper">
	<form class="form-horizontal" role="form" ng-submit="guestSave()">
		<input id="id" name="id" type="hidden" value="${id}" />
		<!-- 列表查询条件 -->
		<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" /> 
		<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
		<input id="sys_name" type="hidden" name="sys_name" value="${sys_name}" />
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>基本信息</li>
			</ul>
			<input type="hidden" ng-model="guest.language"/>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">语言类型：</label> 
				<div class="col-sm-3"> 
					<input type="checkbox" checked disabled>&nbsp;中文&nbsp;&nbsp;
					<span ng-repeat="language in languageData">
						<input type="checkbox" ng-model="language.languageModel" ng-change="languageOnChange(language.languageModel,language.uneIdent,$index)" ng-checked="language.check"/>&nbsp;{{language.name}}&nbsp;&nbsp;
					</span>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">头像：</label> 
				<div class="col-sm-2">
					<input type="file" id="uploadify" />
					<div id="fileQueue" style="width:300px; padding-right: 3px;">
						<input class="form-control"  id="imaggo" type="hidden" />
						<span id="imagesTO">
							<c:if test="${not empty id }">
								<img src= "${strpath}{{guest.avatar}}" width='70px' height='70px'>
							</c:if>
						</span>
					</div>
				</div>
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">邀请码：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" id="invitationCode" placeholder="邀请码" class="form-control" oninput="setCustomValidity('');"
						required autofocus ng-model="guest.invitationCode" maxlength="220" <c:if test="${not empty id}">readonly</c:if>/>
				</div> 
				<c:if test="${empty id}">
					<label for="firstname" class="col-sm-2 control-label">密码：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" oninvalid="setCustomValidity('密码 只能由数字、字母或_下划线组成，长度要在5到15之间！');" oninput="setCustomValidity('');" pattern="^[0-9a-zA-Z_]{5,15}$"
							required autofocus placeholder="密码" class="form-control" ng-model="guest.password" maxlength="20" />
					</div> 
				</c:if>
  			</div> 
  			<div class="form-group"> 
  				<label for="firstname" class="col-sm-2 control-label">手机号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="手机号" class="form-control" autofocus ng-model="guest.mobile" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">email：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="email" class="form-control" autofocus ng-model="guest.email" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
  				<label for="firstname" class="col-sm-2 control-label">用户名：</label> 
				<div class="col-sm-3"> 
					<input type="text" id="userName" placeholder="用户名" class="form-control" autofocus ng-model="guest.userName" maxlength="220" oninput="setCustomValidity('');"/>
				</div> 
  				<label for="firstname" class="col-sm-2 control-label">是否是vip：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" ng-model="guest.vip">
						<option value="1" ng-selected="isSelected(guest.vip=='1')">是</option>
						<option value="2" ng-selected="isSelected(guest.vip=='2')">否</option>
					</select>
				</div>
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">证件类型：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" ng-model="guest.idType" >
						<option value="">--请选择--</option>
						<option ng-repeat="idType in idTypes" value="{{idType.dDLogo}}" >{{idType.dDName}}</option>
					</select>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">证件号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="证件号" class="form-control" autofocus ng-model="guest.documentNumber" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
  				<label for="firstname" class="col-sm-2 control-label">性别：</label> 
				<div class="col-sm-3"> 
					男&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="guest.gender" value="1" ng-checked="isSelected(guest.gender=='1')"/>&nbsp;&nbsp;
					女&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="guest.gender" value="2" ng-checked="isSelected(guest.gender=='2')"/>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">所属团：</label> 
				<div class="col-sm-3"> 
					<input id="groupidId" type="hidden" ng-model="guest.groupId" />
						<input id="groupnameId" class="input-sm inputQry" type="text" ng-model="guest.groupName" 
							value="{{guest.groupName}}" title="选择所属团" data-toggle="modal" data-target="#myGroup" readonly/>&nbsp;&nbsp;
						<a href="javaScript:void(0);" ng-click="delGroup()" title="删除所属团">删除</a>
				</div> 
  			</div>
		</div>
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>中文信息</li>
			</ul>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">姓名(中文)：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="姓名" class="form-control" required autofocus ng-model="guest.name" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">民族(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="民族" class="form-control" autofocus ng-model="guest.nation" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">单位(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="单位" class="form-control" autofocus ng-model="guest.workPlace" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">职务(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="职务" class="form-control" autofocus ng-model="guest.job" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">国籍(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="国籍" class="form-control" autofocus ng-model="guest.nationality" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">籍贯(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="籍贯" class="form-control" autofocus ng-model="guest.nativePlace" maxlength="220" />
				</div> 
  			</div>
		</div>
		<!-- 内容 -->
		<div ng-repeat="guestlan in lguest">
			<div class="panel panel-default" id="guestDiv{{$index}}" ng-show="languageData[$index].check">
				<input type="hidden" ng-model="guestlan.language"/>
				<input type="hidden" ng-model="guestlan.check"/>
				<ul class="breadcrumb">
					<li>{{languageData[$index].name}}信息</li>
					<li>
						<a href="javaScript:void(0);" ng-click="delGuestDiv($index)" >
							<img src="${ctx }/images/minus.png" alt="minus" width="25px" height="25px" />
						</a>
					</li>
				</ul>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">姓名({{languageData[$index].name}})：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<span ng-if="languageData[$index].check == true">
							<input type="text" placeholder="姓名" class="form-control" required autofocus ng-model="guestlan.name" maxlength="220" />
						</span>
						<span ng-if="languageData[$index].check != true">
							<input type="text" placeholder="姓名" class="form-control" autofocus ng-model="guestlan.name" maxlength="220" />
						</span>
					</div> 
					<label for="firstname" class="col-sm-2 control-label">民族({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="民族" class="form-control" autofocus ng-model="guestlan.nation" maxlength="220" />
					</div>
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">单位({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="单位" class="form-control" autofocus ng-model="guestlan.workPlace" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">职务({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="职务" class="form-control" autofocus ng-model="guestlan.job" maxlength="220" />
					</div> 
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">国籍({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="国籍" class="form-control" autofocus ng-model="guestlan.nationality" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">籍贯({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="籍贯" class="form-control" autofocus ng-model="guestlan.nativePlace" maxlength="220" />
					</div> 
	  			</div>
			</div>
		</div>
		<!-- 保存、返回 -->
		<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
			<div class="col-sm-offset-2 col-sm-10"> 
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
			</div>
		</div>
		<!-- 选择团 -->
		<div class="modal fade" id="myGroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="width:500px;height:450px;" >
		        <div class="modal-content">
		           <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">团tree列表</h4>
		            </div>
		            <iframe src="${ctx }${path}/guest/groupTree" style="width:500px;height:450px;">
                    </iframe>
		           <!--  <div class="modal-body">在这里添加一些文本</div> -->
		            <div class="modal-footer">
		                <button id="closeModalButton" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            </div> 
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div>
		
	</form>
</div>

</body>

</html>
