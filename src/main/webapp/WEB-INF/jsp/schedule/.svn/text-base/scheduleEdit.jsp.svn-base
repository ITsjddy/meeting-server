<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>论坛修改页面</title>
	<!-- 分页所需js -->
	<%-- <script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script> --%>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/schedule/js/edit.js"></script>
	<script type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
	<link href="${ctx }/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.b_r_div{ height:250px; width:925px; margin-left:5px; float:left; margin-top:5px; border:0px solid #000000;}
	</style>
</head>
<body ng-app="scheduleApp" ng-controller="scheduleController">
<ul class="breadcrumb"><li>论坛编辑页面</li></ul>
  <div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form" ng-submit="editSave()">
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>基本信息</li>
			</ul>
			<input id="uniqueCode" name="uniqueCode" type="hidden" value="${uniqueCode}" />
			<input id="id" name="id" type="hidden" value="${id}" />
			<input type="hidden" ng-init="schedule.language=''" ng-model="schedule.language"/>
			<input type="hidden" ng-init="schedule.memberIds=''" ng-model="schedule.memberIds"/>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">语言类型：<font color="red">*</font></label> 
				<div class="col-sm-8"> 
					<input type="checkbox" checked disabled>中文&nbsp;
					<span ng-repeat="language in languageData">
						<input type="checkbox" ng-model="language.languageModel" ng-change="languageOnChange(language.languageModel,language.uneIdent,$index)" ng-checked="language.check"/>{{language.name}}&nbsp;
					</span>
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">所属板块：<font color="red">*</font></label> 
				<div class="col-sm-8"> 
					<select class="form-control" required name="plateId" ng-model="schedule.plateId">
						<option value="">--请选择--</option>
						<option ng-repeat="plate in plateList" value="{{plate.dDLogo}}">{{plate.dDName}}</option>
					</select>
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">开始时间：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" required class="form-control"  id="startDate" name="startDate" ng-model="schedule.startDate"/>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">结束时间：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="结束时间" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" required  id="endDate" name="endDate" ng-model="schedule.endDate" />
				</div> 
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">会场名称：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<select class="form-control" ng-change="changeConHallId()" required name="conHallId" ng-model="schedule.conHallId">
						<option value="">--请选择--</option>
						<option ng-repeat="conHall in conHallList" value="{{conHall.id}}" ng-model="conHall.conHallId">{{conHall.name}}</option>
					</select>
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">会场联系人：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="会场联系人" class="form-control" ng-model="conHallPerson"  disabled/>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">联系人电话：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="联系人电话" ng-model="conHallPersonMoblile" class="form-control" disabled />
				</div> 
  			</div>
  			<!-- <div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">主办单位：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="主办单位" class="form-control"   ng-model="schedule.hostUnit"/>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">主办单位联系人：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="主办单位联系人" class="form-control"   ng-model="schedule.hostContact" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">支持单位：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="支持单位" class="form-control"   ng-model="schedule.supportUnit"/>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">协办单位：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="协办单位" class="form-control"   ng-model="schedule.coUnit" />
				</div> 
  			</div>
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label">承办单位：</label>
					<div class="col-sm-3">
						<input type="text" placeholder="承办单位" class="form-control"
							 id="undertakingUnit" name="undertakingUnit"
							ng-model="schedule.undertakingUnit" maxlength="220" />
					</div>
					<label for="firstname" class="col-sm-1 control-label">承办单位联系人：</label>
					<div class="col-sm-2">
						<input type="text" placeholder="承办单位联系人" class="form-control"
							 id="undertakingContact" name="undertakingContact"
							ng-model="schedule.undertakingContact" maxlength="220" />
					</div>
					<label for="firstname" class="col-sm-1 control-label">承办单位联系人电话：</label>
					<div class="col-sm-2">
						<input type="text" placeholder="承办单位联系人电话" class="form-control"
							 id="undertakingMobile" name="undertakingMobile"
							ng-model="schedule.undertakingMobile" maxlength="220" />
					</div>
				</div>
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label">责任单位：</label>
					<div class="col-sm-3">
						<input type="text" placeholder="责任单位" class="form-control"
							 id="responsibilityUnit" name="responsibilityUnit"
							ng-model="schedule.responsibilityUnit" maxlength="220" />
					</div>
					<label for="firstname" class="col-sm-1 control-label">责任单位联系人：</label>
					<div class="col-sm-2">
						<input type="text" placeholder="责任单位联系人" class="form-control"
							 id="responsibilityContact" name="responsibilityContact"
							ng-model="schedule.responsibilityContact" maxlength="220" />
					</div>
					<label for="firstname" class="col-sm-1 control-label">责任单位联系人电话：</label>
					<div class="col-sm-2">
						<input type="text" placeholder="责任单位联系人电话" class="form-control"
							 id="responsibilityMobile" name="responsibilityMobile"
							ng-model="schedule.responsibilityMobile" maxlength="220" />
					</div>
				</div> -->
				<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">参会嘉宾列表：</label> 
				<div class="col-sm-3"> 
					<a data-toggle="modal" class="btn btn-primary" data-target="#myModal">添加嘉宾</a>
				</div> 
  			</div>
  			<div class="form-group"> 
  			<label for="firstname" class="col-sm-2 control-label"></label> 
				<div class="col-sm-8 b_r_div" style="overflow:auto;">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>序号</th>
                                       <th>邀请码</th>
									   <th>手机号</th>
									   <th>用户名</th>
									   <th>姓名</th>
									   <th>单位</th>
									   <th>职务</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest in guests">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.member.userName"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.workPlace"></td>
										<td ng-bind="guest.job"></td>
										<td>
											<a href="javascript:void(0);" class="btn btn-success" ng-click="upOrDownMember('toTop',$index)">置顶</a>
											<a href="javascript:void(0);" class="btn btn-primary" ng-click="upOrDownMember('up',$index)">上移</a>
											<a href="javascript:void(0);" class="btn btn-primary" ng-click="upOrDownMember('down',$index)">下移</a>
											<a href="javaScript:void(0);" ng-click="deleteFromIds($index)" class="btn btn-danger">删除</a>
										</td>
									</tr>
								</tbody>
                            </table>
                        </div>
  			</div>
  			</div>
  			<!-- 中文信息 -->
				<div class="panel panel-default">
					<ul class="breadcrumb">
						<li>中文信息</li>
					</ul>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							论坛名称：<font color="red">*</font>
						</label>
						<div class="col-sm-3">
							<input type="text" placeholder="论坛名称" class="form-control"
								required  ng-model="schedule.name" maxlength="60" />
						</div>
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							论坛简称：<!-- <font color="red">*</font> -->
						</label>
						<div class="col-sm-3">
							<input type="text" placeholder="论坛简称" class="form-control"
							  ng-model="schedule.shortname" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							主持人：<!-- <font color="red">*</font> -->
						</label>
						<div class="col-sm-3">
							<input type="text" placeholder="主持人" class="form-control"
							  ng-model="schedule.hostPerson" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							论坛描述：<!-- <font color="red">*</font> -->
						</label>
						<div class="col-sm-3">
							<textarea type="text" placeholder="论坛描述" class="form-control"
							  ng-model="schedule.remark" maxlength="60" maxlength="1000" class="input-xlarge isSpace" rows="5" style="width:800px;resize:none;"></textarea>
						</div>
					</div>
				</div>
				
				<!-- 其他语言信息 -->
				<div ng-repeat="scheduleLan in scheduleList">
					<div class="panel panel-default" id="guestDiv{{$index}}"
						ng-if="languageData[$index].check">
						<input type="hidden" ng-model="scheduleLan.language" /> <input
							type="hidden" ng-model="scheduleLan.check" />
						<ul class="breadcrumb">
							<li>{{languageData[$index].name}}信息</li>
							<li><a href="javaScript:void(0);"
								ng-click="delGuestDiv($index)"> <img
									src="${ctx }/images/minus.png" alt="minus" width="25px"
									height="25px" />
							</a></li>
						</ul>
						<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							论坛名称({{languageData[$index].name}})：<font color="red">*</font>
						</label>
						<div class="col-sm-3">
							<input type="text" placeholder="论坛名称({{languageData[$index].name}})" class="form-control"
								required  ng-model="scheduleLan.name" maxlength="60" />
						</div>
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							论坛简称({{languageData[$index].name}})：<!-- <font color="red">*</font> -->
						</label>
						<div class="col-sm-3">
							<input type="text" placeholder="论坛简称({{languageData[$index].name}})" class="form-control"
							  ng-model="scheduleLan.shortname" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							主持人({{languageData[$index].name}})：<!-- <font color="red">*</font> -->
						</label>
						<div class="col-sm-3">
							<input type="text" placeholder="主持人({{languageData[$index].name}})" class="form-control"
							  ng-model="scheduleLan.hostPerson" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							论坛描述({{languageData[$index].name}})：<!-- <font color="red">*</font> -->
						</label>
						<div class="col-sm-3">
							<textarea type="text" placeholder="论坛描述({{languageData[$index].name}})" class="form-control"
							  ng-model="scheduleLan.remark" maxlength="60" maxlength="1000" class="input-xlarge isSpace" rows="5" style="width:800px;resize:none;"></textarea>
						</div>
					</div>
					</div>
				</div>
			<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
				<div class="col-sm-offset-2 col-sm-10"> 
				<input id="btnSubmit" class="btn btn-primary" type="submit"  value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
				</div>
			</div>
		</form>
	</div>
	
</div>



 <!-- 嘉宾选人 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="width:1070px;height:860px;">
		        <div class="modal-content">
		           <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">论坛选参会嘉宾</h4>
		            </div>
		            <iframe name="selectMember" id="selectMember"src="${ctx }/dispatcher/schedule/toMemberList?type=guest" style="width:1070px;height:860px;">
                    </iframe>
		           <!--  <iframe name="selectMember" id="selectMember" src="" style="width:1070px;height:860px;">
                    </iframe> -->
		           <!--  <div class="modal-body">在这里添加一些文本</div> -->
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="button" class="btn btn-primary" ng-click="getSelectedItem()">确认选择</button>
		            </div> 
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div> 
</body>
</html>