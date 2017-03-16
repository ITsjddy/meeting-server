<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<title>行程信息导入模板管理</title>
		<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
		<!-- 分页所需js -->
		<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
		<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
		<script src="${ctx }/static/sysviews/template/js/travelInforCreateList.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#inputForm").validate({
					rules: {
					},
					messages: {
					},
					focusInvalid: false,
					onkeyup: false,
					errorPlacement: function(error, element) {
						error.appendTo(element.parent());
					}
				});
				jQuery.validator.addMethod("isEnglish", function(value, element) {
					var test = /^(.*[\u4E00-\u9FA5]+.*)+$/;
					return this.optional(element) || !test.test(value);
				}, "此处不能输入中文信息");
				$("#excelFile").bind('change', function() {
					var dFile = document.getElementById('excelFile');
					if (!dFile.value.match(/.xls|.xlsx/i)) {
						alert('上传文件必须匹配: .xls 或 .xlsx !');
						dFile.value = null;
						return;
					}
				});
				$("#checkAll").click(function() {
					$("input[name='temids']").prop("checked", this.checked);
				});
				$("input[name='temids']").click(function() {
					var $temids = $("input[name='temids']");
					$("#checkAll").prop("checked", $temids.length == $temids.filter(":checked").length ? true : false);
				});
			});
			window.onload = function() {
				$('#importinfo').hide();
				var messstr = '${messstr}';
				if ('' != messstr && null != messstr && 'null' != messstr) {
					var excelinfo = document.getElementById("excelinfo");
					excelinfo.innerHTML = messstr;
					$('#importinfo').show();
				}
				var ids = '${memtem.temids}';
				var renyuanid = ids.split(',');
				for (var i = 0; i < renyuanid.length; i++) {
					$("#" + renyuanid[i]).attr("checked", "checked");
				}
				var $temids = $("input[name='temids']");
				$("#checkAll").prop("checked", $temids.length == $temids.filter(":checked").length ? true : false);
			};

			function index() {
				document.inputForm.action = '${ctx}/memtem.do?method=indexDept';
				document.inputForm.submit();
			}

			function checkNull() {
				var $temids = $("input[name='temids']");
				var tmc = $temids.length;
				if (tmc == 0) {
					alert('导入字段 不能为空！');
					return false;
				}
				var ValState = $('#inputForm').valid();
				if (ValState == true) {
					$("#btnSubmit").attr("disabled", true);
				}
				return biaoshi;
			}

			function ficha(value) {
				$('#name').val(value.value);
			}
		</script>
	</head>

	<body ng-app="guestApp" ng-controller="guestController">
		<ul class="breadcrumb">
			<li>行程信息导入模板管理</li>
		</ul>
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form">
					<input id="id" name="id" type="hidden" value="${id}" />
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">行程信息信息导入：<font color="red">*</font></label>
						<div class="col-sm-3">
							<input id="excelFile" name="excelFile" type="file" file-model="myFile" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">说明：</label>
						<div class="col-sm-8">
							<textarea class="form-control" placeholder="说明" rows="3" autofocus id="shuoming" name="shuoming" ng-model="template.explain"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">注意事项：</label>
						<div class="controls" style="font-weight:bold;">
							&nbsp;&nbsp;{注意：Excel模版里 如果导入内容由数字组成(如日期、时间等)要把对应数据的单元格格式设置为"文本";}<br>
							<!-- 	&nbsp;&nbsp;团名称重复时、则视为修改;}* -->
						</div>
					</div>
					<div class="form-group" id="importinfo">
						<label for="firstname" class="col-sm-2 control-label">导入信息：</label>
						<div class="controls" id="excelinfo" style="color:red;font-weight:bold;">
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
			<div class="col-sm-offset-2 col-sm-10">
				<input id="" class="btn btn-success" type="button" ng-click="createExcel()" value="下载Excel模版" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="save()" value="确认导入" />
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回" />
			</div>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
			<div class="modal-dialog" style="width:900px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">模板字段选择</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-body">
										<!-- <input type="checkbox" checked disabled>&nbsp;数据导入唯一标识&nbsp;&nbsp;
										<span ng-repeat="template in allTemplate">
											<input type="checkbox" ng-model="checkId" ng-change="templateOnChange(checkId,template)"/>&nbsp;{{template.templateName}}&nbsp;&nbsp;
										</span> -->
											<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
									<thead>
										<tr>
											<th  style="text-align:center"></th>
											<th>序号</th>
											<th>模版字段名称</th>
											<th>实体字段名称</th>
											<th>说明</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="guest in items">
										<td style="text-align:center"><input type="checkbox" ng-model="checkId" ng-change="templateOnChange(checkId,guest)"/></td>
											<td><span class="badge">{{ $index + 1 }}</span></td>
											<td ng-bind="guest.templateName"></td>
											<td ng-bind="guest.fieldName"></td>
											<td ng-bind="guest.explain"></td>
										</tr>
									</tbody>
								</table>
								<!-- 分页的标签 -->
								<nav>
									<tm-pagination conf="paginationConf"></tm-pagination>
								</nav>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" ng-click="confirmButton()">确定</button>
						<button type="button" class="btn btn-default" ng-click="closeButton()">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>