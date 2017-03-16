var context = "${ctx}";
$(document).ready(function(){
	var zNodes;
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
	function zTreeOnClick(event, treeId, treeNode){
		$('#groupid').val(treeNode.id);
		$("#mainFrame").attr("src", CONTEXT_PATH+DISPATCHER_PATH+"/department/indexdetial?groupid="+treeNode.id);
		
	}
	function renderTree() {
        $('#treeDemo').empty();
        $.ajax({
        	url:CONTEXT_PATH+DISPATCHER_PATH+"/department/queryAll",
        	dataType : "json",
            data:{},
            type:"post",
            success : function(data) {
                zTree = $.fn.zTree.init($("#treeDemo"), setting, data);
                original = zTree.getNodesByFilter(filter);
            }
        });
    }
	$.ajax({
		url:CONTEXT_PATH+DISPATCHER_PATH+"/department/queryAll",
        dataType : "json",
        data:{},
        type:"post",
        success:function(data){
        	zNodes=data;
        	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
        	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        	treeObj.expandAll(true); //默认打开
        	var node = treeObj.getNodeByParam("id",$('#groupid').val());
        	treeObj.selectNode(node,false);
        	zTreeOnClick("", "", node);
        }}); 	
});