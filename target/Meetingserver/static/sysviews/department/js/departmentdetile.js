$(document).ready(function(){ 
var menutemp="";
	    	$.ajax({
			url:CONTEXT_PATH+DISPATCHER_PATH+"/department/queryById",
            dataType : "json",
            data:{
				"id":$('#groupid').val()
       		},
            type:"post",
            success:function(data){
            	var classification;
            	var endepartname;
            	(data.classification == "tneib") ? classification="内宾团" : classification="外宾团";
            	(data.endepartname == "") ? endepartname="无" : endepartname=data.endepartname;
            	menutemp="<tbody><tr><th width=\"20%\" style=\"background-color: #f9f9f9\">团名称</th><td width=\"80%\">"+data.departname+"</td></tr><tr><th style=\"background-color: #f9f9f9\">团名称英文</th><td>"+endepartname+"</td></tr><tr><th style=\"background-color: #f9f9f9\">上级团</th><td>"+data.parentname+"</td></tr><tr><th style=\"background-color: #f9f9f9\">团分类</th><td>"+classification+"</td></tr><tr><th style=\"background-color: #f9f9f9\">说明</th><td>无</td></tr></tbody>";
            	$('#departdetile').html(menutemp);
            }
        });
});