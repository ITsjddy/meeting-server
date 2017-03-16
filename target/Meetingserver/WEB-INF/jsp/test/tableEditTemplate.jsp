<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>tableTest</title>
	<script src="${ctx }/static/sysviews/test/js/myNoteApp.js"></script>
	<script src="${ctx }/static/sysviews/test/js/myNoteCtrl.js"></script>
</head>
<script type="text/javascript">
	var context = "${ctx}";
</script>
<body>
  <div id="wrapper">
        <div id="page-wrapper">
			<form class="form-horizontal" role="form">
				<fieldset>
					<legend>详情/修改页面</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">文本框</label>
						<div class="col-sm-4">
							<input class="form-control" id="ds_host" type="text"
								placeholder="value" />
						</div>
					</div>
					<div class="form-group">
						<label for="disabledSelect" class="col-sm-2 control-label">下拉框</label>
						<div class="col-sm-2">
							<select id="disabledSelect" class="form-control">
								<option>1</option>
								<option>2</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="disabledSelect" class="col-sm-2 control-label">复选框</label>
						<div class="col-sm-2">
							<label class="checkbox-inline"><input type="checkbox" name="aaa">1</label> 
							<label class="checkbox-inline"><input type="checkbox" name="aaa">2</label>
							<label class="checkbox-inline"><input type="checkbox" name="aaa">3</label>
						</div>
					</div>
					<div class="form-group">
						<label for="optionsRadios" class="col-sm-2 control-label">单选框</label>
						<div class="col-sm-2">
							<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios1" value="option1" checked="">Radio 1
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios1" value="option1" checked="">Radio 1
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios1" value="option1" checked="">Radio 1
								</label>
							</div>
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>多个页签</legend>
					<div class="form-group">
						<label for="disabledSelect" class="col-sm-2 control-label">表名</label>
						<div class="col-sm-2">
							<select id="disabledSelect" class="form-control">
								<option>禁止选择</option>
								<option>禁止选择</option>
							</select>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
    </div>
</body>
</html>