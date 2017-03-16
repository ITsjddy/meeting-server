function delnoty(requrl, returl, reqparam, meg, $http) {
	parent.noty({
      text        : '确定'+meg+'?',
      type        : 'information',
      dismissQueue: false,
      layout      : 'center',
      theme       : 'relax',
      modal		:'true',
      buttons     : [
          {addClass: 'btn btn-primary', text: '确认', onClick: function ($noty) {
          	$noty.close();
          	var url = requrl;
          	$http({
				method : 'POST',
				url : url,
//				async:false,  angularjs 本身结构不支持异步请求
				params : reqparam,
			}).success(function(resultMap) {
                  	if(null != resultMap && resultMap.success == true){
  						window.location.href = returl;
  						parent.noty({dismissQueue: true, force: true, layout: 'bottomRight', theme: 'defaultTheme', text: meg+'成功！', type: 'success',timeout: 2000});	
  					}else{
  						parent.noty({dismissQueue: true, force: true, layout: 'bottomRight', theme: 'defaultTheme', text: meg+'错误，请重试！', type: 'error',timeout: 2000});
  					}
                  }).error(function(error) {
                  	if('403' == error.status){
  						parent.noty({dismissQueue: true, force: true, layout: 'bottomRight', theme: 'defaultTheme', text: '无权限！', type: 'warning',timeout: 2000});
  					}
                  }); 
          }
          },
          {addClass: 'btn btn-danger', text: '取消', onClick: function ($noty) {
              $noty.close();
          }
          }
      ]
  });
}

function alertnoty(meg, type) {
	parent.noty({
		 text        : meg,
         type        : type,
         dismissQueue: true,
         closeWith: ['click'],
         layout      : 'topCenter',
         theme       : 'relax',
         modal		:'true',
         animation: {
             open: null,
             close: null
         }
  });
}

function alertnoty(meg, type, position) {
	parent.noty({
		 text        : meg,
         type        : type,
         dismissQueue: true,
         closeWith: ['click'],
         layout      : position,
         theme       : 'relax',
         modal		:'true',
         animation: {
             open: null,
             close: null
         },
		 buttons     : [
	        {addClass: 'btn btn-danger', text: '确认', onClick: function ($noty) {
	        		$noty.close();
	        	}
	        }
	     ]
  });
}

function messagenoty(meg, type, position, time) {
	parent.noty({
		dismissQueue: true, 
		force: true, 
		layout: position, 
		theme: 'defaultTheme', 
		text: meg, 
		type: type,
		timeout: time
	});
}

