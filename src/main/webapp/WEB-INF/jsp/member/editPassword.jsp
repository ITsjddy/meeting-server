<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="myPassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<form class="form-horizontal" role="form" ng-submit="savePassword()">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        	<div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
	            </div>
	            <div class="modal-body">
	            	邀请码： <input type="text" name="userName" ng-model="passUserName" readonly>
					新密码： <input type="text" name="password" ng-model="passPassword" required
							oninvalid="setCustomValidity('密码 只能由数字、字母或_下划线组成，长度要在5到15之间！');" oninput="setCustomValidity('');" pattern="^[0-9a-zA-Z_]{5,15}$">
				</div>
	            <div class="modal-footer">
	                <button type="submit" class="btn btn-primary">提交更改</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
    </form>
</div>