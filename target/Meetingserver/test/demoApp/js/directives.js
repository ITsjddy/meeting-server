(function () {
    angular.module('demo').directive('dateformat', ['$filter',function($filter) {  
        var dateFilter = $filter('date');  
        return {  
            require: 'ngModel',  
            link: function(scope, elm, attrs, ctrl) {  
            	debugger
                function formatter(value) {  
                    return dateFilter(value, 'yyyy-MM-dd'); //format  
                }  
      
                function parser() {  
                    return ctrl.$modelValue;  
                }  
      
                ctrl.$formatters.push(formatter);  
               // ctrl.$parsers.unshift(parser);  
      
            }  
        };  
    }]);  
//    angular.module('demo').directive("ngDatepicker",function($ocLazyLoad){
//    	return{
//    		link: function(scope, iElement, iAttr, ctrl, $transclude) {
//
//    			
//	        	var lazyLoadPromise = $ocLazyLoad.load({
//	                files: [ '../lib/plugins/datapicker/bootstrap-datepicker.js',
//	                         '../lib/plugins/datapicker/datepicker3.css']
//	            }).then(function(){
//	            	scope.$watch(iAttr.dpoption, function ngShowWatchAction(_option) {
//
//	        			var option = angular.extend({
//	                        autoclose: true,//选中关闭
//	                        format: 'yyyy-mm-dd',//默认样式
//	                        weekStart:1,//开始星期一
//	                        todayHighlight:true,//高亮 今天的日期
//	                        enableOnReadonly:true
//	                    },_option); 
//	            		iElement.datepicker(option);
//	            	})
//	            })
//	            return lazyLoadPromise;
//    	    }
//    	}
//    }).directive("ngDaterange",function($ocLazyLoad){
//    	return{
//    		link: function(scope, iElement, iAttr, ctrl, $transclude) {
//
//    			
//	        	var lazyLoadPromise = $ocLazyLoad.load({
//	                files: [ '../lib/plugins/datapicker/bootstrap-datepicker.js',
//	                         '../lib/plugins/datapicker/datepicker3.css']
//	            }).then(function(){
//	            	scope.$watch(iAttr.dpoption, function ngShowWatchAction(_option) {
//	            		var option = angular.extend({
//	                        autoclose: true,//选中关闭
//	                        format: 'yyyy-mm-dd',//默认样式
//	                        weekStart:1,//开始星期一
//	                        todayHighlight:true,//高亮 今天的日期
//	                        enableOnReadonly:true
//	                    },_option); 
//
//	            	    var start = $('input:first',iElement).datepicker(option);
//	            	    var end = $('input:last',iElement).datepicker(option);
//
//	            	    var changeRange = function(){
//		            	    var map = [];
//		            	    map.push(start.datepicker("getDate")||new Date());
//		            	    map.push(end.datepicker("getDate")||new Date());
//		            	    start.datepicker("setRange",map);
//		            	    start.datepicker("setEndDate",end.datepicker("getDate"));
//		            	    end.datepicker("setStartDate",start.datepicker("getDate"));
//		            	    end.datepicker("setRange",map);
//	            	    }
//	            	    start.on("changeDate",changeRange);
//	            	    end.on("changeDate",changeRange);
//	            	    
//	            	})
//	            })
//	            return lazyLoadPromise;
//    	    }
//    	}
//    })
})();