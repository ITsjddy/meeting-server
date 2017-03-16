<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta charset="utf-8"> 
	<title>团选择器</title>
	<!-- ztree用的css和js -->
	<link rel="stylesheet" href="${ctx }/css/ztree/demo.css" type="text/css">
	<link rel="stylesheet" href="${ctx }/css/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.excheck.js"></script>
</head>
<style>
	ul.sztree {margin: 0px;overflow-x:auto;}
</style>
<script type="text/javascript">
    var setting = {
    		data: {
	            key: {
	                name : "departname"
	            },
	            simpleData: {
	                enable: true,
	                idKey: "id",
	                pIdKey:"parentid"
	            }
	        },
        callback: {
        	onClick: zTreeOnClick
        }
    };
    var original;
    var zTree;
    var checkSyncInterval = '';
    $(document).ready(function(){
        memberManageInit();
    });
    
    function memberManageInit() {
        renderTree();
    }
    function zTreeOnClick(event, treeId, treeNode){
    	//点击按钮提交  
    	parent.selectdept(treeNode.id,treeNode.departname);
    	parent.treeDialogcloseforcreate();
    }
    function renderTree() {
        $('#tree').empty();
        $.ajax({
            url : CONTEXT_PATH + '/dispatcher/department/queryAll',
            type : "POST",
            dataType : 'json',
            success : function(data) {
                zTree = $.fn.zTree.init($("#tree"), setting, data);
                original = zTree.getNodesByFilter(filter);
            }
        });
    }
    function AutoMatch(txtObj) {
        if (txtObj.value.length > 0) {
        } else {
            renderTree();                
        }              
}
    function AutoMatchbutton() {
    	var txtObj = $("#selectpeople").val();
        if (txtObj.length > 0) {
        	//renderTree();
            var zTree = $.fn.zTree.getZTreeObj("tree");
            var nodeList = zTree.getNodesByParamFuzzy("departname", txtObj);
//            alert(nodeList[0]);
//            for(var i=0;i<nodeList.length;i++){
//            	var mmm = nodeList[i].getParentNode();
//            	nodeList[nodeList.length+i]=mmm; 
//           }
            $.fn.zTree.init($("#tree"), setting, nodeList);
        } else {
            renderTree();                
        }              
}
</script>
<div class="Bd_orderInfoBox" id="test">
    <div class="Bd_orderInfo">
        <div style="height:270px; overflow: auto;">
        	<p><input type=text id="selectpeople" placeholder="查询通讯录" name="selectpeople" value="" style="width:75%" onkeyup="AutoMatch(this)"><button onclick="AutoMatchbutton()">查询</button></p>
            <ul id="tree" class="ztree"></ul>
        </div>
        <span id="syncMsg"></span>
    </div>
</div>