$(function(){
	var menu="<li class=\"sidebar-search\"><div class=\"input-group custom-search-form\"><input type=\"text\" class=\"form-control\" placeholder=\"搜索\"><span class=\"input-group-btn\"><button class=\"btn btn-default\" type=\"button\"><i class=\"glyphicon glyphicon-search\"></i></button></span></div></li>";
	var menutemp="";
	$.ajax({
		url:context+"/dispatcher/main/menu",
        dataType : "json",
        data:{
        },
        type:"post",
        success:function(data){//ajax返回的数据
        	console.log(data);
        	for(var i=0;i<data.mapList.length;i++){
        		var menulist='';
        		if(data.mapList[i].type==1&&data.mapList[i].enable==true){
        			if(i==0){
        				menulist="<p class=\"menu_head current\">"+data.mapList[i].name+"</p><div style=\"display:block\" class=\"menu_body\" >";
        			}else{
        				menulist="<p class=\"menu_head\">"+data.mapList[i].name+"</p><div style=\"display:none\" class=\"menu_body\" >";
        			}
        			for(var j=0;j<data.mapList.length;j++){
        				if(data.mapList[j].parent==data.mapList[i].ID){
        					menulist+="<a href=\"#\" onclick=\"javascript:loadhtml('"+data.mapList[j].url+"')\">"+data.mapList[j].name+"</a>";
        				}
        			}
        			menulist+="</div>";
        		}
        		menutemp+=menulist;
        	}
        	$('#firstpane').html(menutemp);
        	$("#firstpane .menu_body:eq(0)").show();
        	$("#firstpane p.menu_head").click(function(){
        		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
        		$(this).siblings().removeClass("current");
        	});
        	$("#secondpane .menu_body:eq(0)").show();
        	$("#secondpane p.menu_head").mouseover(function(){
        		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
        		$(this).siblings().removeClass("current");
        	});
        }
    });
});