//demo controlers

(function(){

	var basePath = "apps/demoApp/";
	var tableCtrl = function($scope,$iqa_demo_List){
		$scope.tabledatas = [];
		var pg = 1;
		$scope.getPage = function(i){
			if(i){
				pg = i;
			}
			else{
				pg = 1;
			}
			$iqa_demo_List.getList(function(list){
				list.data.page.number = pg;
				$scope.tabledatas = list;
			});
		}
		$scope.getPage(1);
	};
	
	angular
    .module('demo')
    .controller('demo_uiselectCtrl', ['$scope','$http','$filter', function($scope, $http, $filter) {
    	//当前controller的变量要和html中使用的对应， 注释中写的可以不要的，即需要正在html中删除对应属性
    	
//    	注意在appconfig中添加uiSelect
    	
    	
    	//必须使用对象中的数字
        $scope.mulselect = {
            a: []
        };
    	//必须使用对象中的对象
        $scope.singleselect = {a:{id:1,name:"xc",money:0}};
        
//        $scope.addItem = function(value){
//        	debugger
//            return {"id":1,"name":value,"itcode":value,"dept":value,"deptid":"123456","costcenter":"信息化管理部","costcenterid":"12345678","date":"2014-11-11","money":"111.00","state":"已结束"};
//        }
        
        //获取动态数据，如果使用静态数据 可以不要
        $scope.getData = function($select) {
        	
        	console.log("搜索字符串：",$select.search);//查询字符串
        	var _search = $select.search;
        	
        	$select.items[0] = {"id":1,"name":_search,"itcode":_search,"dept":_search,"deptid":"123456","costcenter":"信息化管理部","costcenterid":"12345678","date":"2014-11-11","money":"111.00","state":"已结束"};

            $http({
                url: basePath+"/applylist.json",
                method: "GET",
                type: "json"
            }).then(function(data) {
                //如果要显示添加的tag ，添加第1个元素

            	console.log($select);
            	
            	//这个示例是测试，没有后台筛选，此处使用前端过滤来演示
            	var _data = $filter('filter')(data.data,function(value,index){
            		if(value.name.indexOf(_search)>=0 || value.money.indexOf(_search)>=0){
            			return true;
            		}
            		else{
            			return false;
            		}
            	});
            	
                $select.items = [$select.items[0]].concat(_data);
//                $scope.list = data.data
            })
        }

        //如果是给定的少量数据 可以不要异步获取数据
        $scope.singleGetData = function($select) {
        	
        	console.log("搜索字符串：",$select.search);//查询字符串
        	var _search = $select.search;
        	//单选的时候 始终保证第一个为手填内容
        	$select.items[0] = {"id":1,"name":_search,"itcode":_search,"dept":_search,"deptid":"123456","costcenter":"信息化管理部","costcenterid":"12345678","date":"2014-11-11","money":"111.00","state":"已结束"};

        	
            $http({
                url: basePath+"/applylist.json",
                method: "GET",
                type: "json"
            }).then(function(data) {
                //如果要显示添加的tag ，添加第1个元素
            	
            	//这个示例是测试，没有后台筛选，此处使用前端过滤来演示
            	var _data = $filter('filter')(data.data,function(value,index){
            		if(value.name.indexOf(_search)>=0 || value.money.indexOf(_search)>=0){
            			return true;
            		}
            		else{
            			return false;
            		}
            	});
        		debugger
            	console.log($select);
            
            	//此处还应该增加 如果第一个和某查询匹配 要减少一个
                $select.items = [$select.items[0]].concat(_data);
//                $scope.list = data.data
            })
        }
        //如果使用动态获取的值，默认list可以传入空或者搜索字符串为0的搜索值。 如果使用静态值，就把静态值传入list即可
        $scope.list = [{"id":3,"name":"徐超","itcode":"xuchaoh","dept":"信息化管理部","deptid":"123456","costcenter":"信息化管理部","costcenterid":"12345678","date":"2015-01-03","money":"333.00","state":"部门审批"},
                       {"id":4,"name":"徐超","itcode":"xuchaoh","dept":"信息化管理部","deptid":"111111","costcenter":"信息化管理部","costcenterid":"12345678","date":"2014-01-03","money":"444.00","state":"部门审批"}]
    }])
    .controller("demo_resourceCtrl",['$iqa_demo_Person',function($iqa_demo_Person){
    	var a = $iqa_demo_Person.get({id:'8a8119204f1f97fc014f1fc8b1ec0002'})
    	.$promise.then(function(a){console.log(1,a)},function(a){console.log(2,a)},function(a){console.log(3,a)})
    }])
    .controller("demo_testCtrl",function(){
    	
    })
    .controller('demo_flowCtrl',function($scope,$iq_flow_status){
    	$scope.opt = {notCaptionIds:["node2","node3","node4","node5"]};
    	$scope.list = $iq_flow_status;
    	$scope.add = function(){
    		$scope.opt.notCaptionIds.push("");
    		$scope.opt = angular.copy($scope.opt);
    	}
    	$scope.del = function(){
    		$scope.opt.notCaptionIds.pop();
    		$scope.opt = angular.copy($scope.opt);
    	}
    })
    .controller('demo_datepickerCtrl',function($scope){
    	$scope.aTime = "13:58";
    	$scope.list = ["1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"]
    	var m = new Date();
    	$scope.m = m;
    	$scope.m2 = null;
    	$scope.m3 = "2015-11-12";
    	$scope.$watch("m",function(m){
    		console.log(m)
    	})
    	$scope.$watch("range.a1",function(cur,old){
    		console.log(cur,old);
    	})
    	var d = new Date();
    	$scope.ng2={startDate:1900+d.getYear()+"-"+(1+d.getMonth())+"-"+(d.getDate()-10),endDate:1900+d.getYear()+"-"+(1+d.getMonth())+"-"+(d.getDate()+10),todayHighlight:false}
    })
    .controller("demo_sortCtrl",function($scope){
    	$scope.array1 = [{index:1,name:1},{index:2,name:2},{index:3,name:3},{index:4,name:4},{index:5,name:5}]
    	
    	$scope.toggleDisable = function(_this){
    		_this.disabled = !_this.disabled;
    	}
    	$scope.toggleDisableAll = function(){
    		$scope.sortableOptions.disabled = !$scope.sortableOptions.disabled;
    	}
    	$scope.sortableOptions = {
    		cursor: "move",
    		update: function(e, ui) {
    			console.log(e,ui);
    		},
    		items: ".active",
    		start: function(e, ui) {
    			console.log(e,ui);
    			if(ui.item.sortable.model.disabled)
    			{
    				ui.item.sortable.cancel();
    			}
    		}
    	}
    })
    .controller('iqpopupCtrl', function ($scope, $iq_popup) {
            $scope.alert = function () {
                var alert = $iq_popup.alert('this is a order massage！');
                alert.then(function () {
                    console.log('ok');
                });
            };
            $scope.confirm = function () {
                var confirm = $iq_popup.confirm('Ok or Cancel ?','true');
                confirm.then(function () {
                    console.log('Ok');
                }, function () {
                    console.log('Cancel');
                });
            };

            $scope.prompt = function () {
                var prompt = $iq_popup.prompt('What your name ?', 'liupg', 'yes', 'no');
                prompt.then(function (value) {
                    console.log(value);
                }, function () {
                    console.log('Dismiss');
                });
            };

            $scope.deptlist = function () {
            	$scope.d=[{id:"8a8119124f00b536014f00bf94b70005",name:"11"}]
                var deptlist=$iq_popup.deptlist($scope.d);
                deptlist.then(function (re) {
                    console.log(re)
                }, function () {
                    console.log('cancel')
                })
            };
            $scope.deptpeople = function () {
            	$scope.c=[{
            		id:"8a8119124ef7dc35014ef7e3eb9d0002",name:"王春宏1"
            	}]
                var deptpeople=$iq_popup.deptpeople($scope.c)
                deptpeople.then(function (re) {
                    console.log(re)
                }, function () {
                    console.log('cancel')
                })
            };

            $scope.sharefile = function () {
                $scope.a=[{id:"8a8119124ef7dc35014ef7e3eb9d0002",name:"王春宏1"}]
                var sharefile=$iq_popup.sharefile($scope.a)
                sharefile.then(function (re) {
                    console.log(re)
                }, function () {
                    console.log('cancel')
                })
            };
            
            $scope.filetree = function () {
                var filetree=$iq_popup.filetree()
                filetree.then(function (re) {
                    console.log(re)
                }, function () {
                    console.log('cancel')
                })
            };
 
            //input框模拟数据
            $scope.tempdata='persons?search_EQ_status=1&pageSize=2147483647';
            $scope.a=$iq_popup.deptlist;
            $scope.disabled=false;
            $scope.b=["8afd84664ed34b62014ed34e83a40003"]
            $scope.ch= function () {
                console.log($scope.a);
            }
            $scope.getall= function () {
                console.log('2222',$scope.myclass);
            }
            
            //测试传入方法
            $scope.getfunc = {
            		alert : function(re){
            			console.log('---------',re);
            		}
            }
        })
    .controller('iqTreeCtrl',function($scope){
    	$scope.data = [{
            'id' : '1',
            'org' : 'dc',
            'name' : '信息化管理部',
            'shortname' : '信管部',
            'sn' : 1,
            'status' : 1,
            'subDept' : [ {
                'id' : '3',
                'org' : 'dc',
                'name' : '开发部',
                'shortname' : '开发',
                'sn' : 3,
                'status' : 1,
                'subDept' : [ {
                    'id' : '5',
                    'org' : 'dc',
                    'name' : '交付中心',
                    'shortname' : '交付',
                    'sn' : 5,
                    'status' : 1,
                    'subDept' : [ {
                        'id' : '7',
                        'org' : 'dc',
                        'name' : 'JAVA团队',
                        'shortname' : 'java',
                        'sn' : 7,
                        'status' : 0,
                        'subDept' : [ ],
                    }, {
                        'id' : '8',
                        'org' : 'dc',
                        'name' : 'DOMINO团队',
                        'shortname' : 'domino',
                        'sn' : 8,
                        'status' : 0,
                        'subDept' : [ {
                            'id' : '9',
                            'org' : 'dc',
                            'name' : 'HR线',
                            'shortname' : 'HR',
                            'sn' : 9,
                            'status' : 1,
                            'subDept' : [ ],
                        } ],
                    } ],
                } ],
            }, {
                'id' : '4',
                'org' : 'dc',
                'name' : '测试部',
                'shortname' : '测试',
                'sn' : 4,
                'status' : 1,
                'subDept' : [ {
                    'id' : '6',
                    'org' : 'dc',
                    'name' : '测试中心',
                    'shortname' : '测试',
                    'sn' : 6,
                    'status' : 1,
                    'subDept' : [ ],
                } ],
            } ],
        }, {
            'id' : '2',
            
            'org' : 'dc',
            'name' : '企划办',
            'shortname' : '企划',
            'sn' : 2,
            'status' : 1,
            'subDept' : [ ],
        }]
    	$scope.a = false;
            $scope.change= function () {
                this.a = !this.a;
            }
    	$scope.b = true;
    	$scope.clickFunc = function(c){
    		console.log(1);
    		console.log(c);
    	}
    	$scope.bbb = function(){
    		$scope.cancelid = 0.5;
    	}
    })
    .controller("cityCtrl",function($scope,$iq_cities){
            $scope.division = [];
            $iq_cities.getCityConfig().then(function(re){
            	$scope.division = re;
            })
            $scope.submit = function () {
                var address = $scope.address || {},
                    text = (address.province.name + address.city.name + address.district) || '请选择地址...'
                console.log(address)
                console.log(text)
            };
        })
    .controller('demo_tableCtrl',tableCtrl)
    .controller('demo_pagerCtrl', ["$scope", function($scope) {
        $scope.list = {};

        $scope.pageChange = function(obj) {
            var index = obj.page;
            if (!index) {
                index = 1;
            }
            var obj = {
                "data": {
                    "content": [],
                    "last": false,
                    "totalPages": 1,
                    "totalElements": 600,
                    "number": index,
                    "size": 10,
                    "first": true,
                    "sort": null,
                    "numberOfElements": 10
                },
                "success": true,
                "state": "",
                "message": ""
            }
            for (var i = 0; i < 10; i++) {
                obj.data.content.push({
                    "id": i,
                    "name": "徐超",
                    "itcode": "xuchaoh",
                    "dept": "信息化管理部",
                    "deptid": "123456",
                    "costcenter": "成本中心1",
                    "costcenterid": "12345678",
                    "date": "2014-11-11",
                    "money": "111.00",
                    "state": "已结束"
                });
            }

            $scope.list = obj;

        }
    }])
    .controller('demo_pager1Ctrl', ["$scope", function($scope)   {

        $scope.list = {};

        $scope.pageChange = function(obj) {
            var index = obj.page;
            if (!index) {
                index = 1;
            }
            var obj = {
            	"pageChange":function(obj){
            		var tdata = angular.copy(this.data);
            		tdata.content = [];
            		for (var i = 0; i < 10; i++) {
                        tdata.content.push({
                            "id": obj.page + "."+i,
                            "name": "徐超"+obj.page + "."+i,
                            "itcode": "xuchaoh",
                            "dept": "信息化管理部",
                            "deptid": "123456",
                            "costcenter": "成本中心1",
                            "costcenterid": "12345678",
                            "date": "2014-11-11",
                            "money": "111.00",
                            "state": "已结束"
                        });
                    }
            		tdata.number = obj.page;
            		this.data = tdata;
            	},
                "data": {
                    "content": [],
                    "last": false,
                    "totalPages": 60,
                    "totalElements": 600,
                    "number": index,
                    "size": 10,
                    "first": true,
                    "sort": null,
                    "numberOfElements": 10
                },
                "success": true,
                "state": "",
                "message": ""
            }
            obj.pageChange({page:1});
            $scope.list = obj;

        }
    }])
    .controller('demo_pager2Ctrl', ["$scope", function($scope) {
    	var _index = 0;
        $scope.pppp = 1;
        $scope.totalpppp = 2;
        $scope.page = function(obj) {
            var index = obj.page;
            if (!index) {
                index = 1;
            }
            $scope.pppp = index;
            var obj = []
            _index++;
            for (var i = 0; i < 15; i++) {
                obj.push({
                    "id": i,
                    "name": "徐超",
                    "itcode": "xuchaoh"+_index,
                    "dept": "信息化管理部",
                    "deptid": "123456",
                    "costcenter": "成本中心1",
                    "costcenterid": "12345678",
                    "date": "2014-11-11",
                    "money": "111.00",
                    "state": "已结束"
                });
            }
            $scope.list = angular.copy(obj);

        }
        $scope.list = [];
    }])
    .controller('demo_pagerInPagerCtrl', ["$scope", "$rootScope", function($scope, $rootScope) {
        $scope.list = {};

        $scope.page = function(obj) {
            var index = obj.page;
            if (!index) {
                index = 1;
            }
            var obj = {
                page: 1,
                totalpage: 10,
                pageChange: function(obj) {
                    this.page = obj.page;
                    this.content = [];
                    var sub_obj = {
                    	"add":function(value){
                            var index = "Add";
                            var tmp = angular.copy(this.data);
                            var list = [{
                                "id": (new Date().getTime()),
                                "name": value+index+ " " + parseInt((new Date().getTime()) / 1000 % (3600)),
                                "itcode": "xuchaoh",
                                "dept": "信息化管理部",
                                "deptid": "123456",
                                "costcenter": "成本中心1",
                                "costcenterid": "12345678",
                                "date": "2014-11-11",
                                "money": "111.00",
                                "state": "已结束"
                            }];
                            list = list.concat(tmp.content);
                            tmp.content = list;
                            this.data = tmp;
                    	},
                        "pageChange": function(obj) {
                            var index = obj.page;
                            var tmp = angular.copy(this.data);
                            tmp.content = [];
                            tmp.number = index;
                            for (var i = 0; i < 15; i++) {
                                tmp.content.push({
                                    "id": i,
                                    "name": "徐超"+index+ " " + parseInt((new Date().getTime()) / 1000 % (3600)),
                                    "itcode": "xuchaoh",
                                    "dept": "信息化管理部",
                                    "deptid": "123456",
                                    "costcenter": "成本中心1",
                                    "costcenterid": "12345678",
                                    "date": "2014-11-11",
                                    "money": "111.00",
                                    "state": "已结束"
                                });
                            }
                            this.data = tmp;

                        },
                        "data": {
                            "content": [],
                            "last": false,
                            "totalPages": 60,
                            "totalElements": 600,
                            "number": 1,
                            "size": 10,
                            "first": true,
                            "sort": null,
                            "numberOfElements": 10
                        },
                        "success": true,
                        "state": "",
                        "message": ""
                    }
                    for (var i = 0; i < 12; i++) {
                        var tmp = angular.copy(sub_obj);
                        tmp.state = i + " " + parseInt((new Date().getTime()) / 1000 % (3600));
                        tmp.pageChange({page:tmp.data.number});
                        this.content.push(tmp)
                    }
                },
                content: []
            }
            var sub_obj = {
            	"add":function(value){
                    var index = obj.page;
                    var tmp = angular.copy(this.data);
                    var list = [{
                        "id": (new Date().getTime()),
                        "name": value+index+ " " + parseInt((new Date().getTime()) / 1000 % (3600)),
                        "itcode": "xuchaoh",
                        "dept": "信息化管理部",
                        "deptid": "123456",
                        "costcenter": "成本中心1",
                        "costcenterid": "12345678",
                        "date": "2014-11-11",
                        "money": "111.00",
                        "state": "已结束"
                    }];
                    list = list.concat(tmp.content);
                    tmp.content = list;
                    this.data = tmp;
            	},
                "pageChange": function(obj) {
                    var index = obj.page;
                    var tmp = angular.copy(this.data);
                    tmp.content = [];
                    tmp.number = index;
                    for (var i = 0; i < 15; i++) {
                        tmp.content.push({
                            "id": i,
                            "name": "徐超"+index+ " " + parseInt((new Date().getTime()) / 1000 % (3600)),
                            "itcode": "xuchaoh",
                            "dept": "信息化管理部",
                            "deptid": "123456",
                            "costcenter": "成本中心1",
                            "costcenterid": "12345678",
                            "date": "2014-11-11",
                            "money": "111.00",
                            "state": "已结束"
                        });
                    }
                    this.data = tmp;

                },
                "data": {
                    "content": [],
                    "last": false,
                    "totalPages": 60,
                    "totalElements": 600,
                    "number": 1,
                    "size": 10,
                    "first": true,
                    "sort": null,
                    "numberOfElements": 10
                },
                "success": true,
                "state": "",
                "message": ""
            }
            for (var i = 0; i < 12; i++) {
                var tmp = angular.copy(sub_obj);
                tmp.state = i + " " + parseInt((new Date().getTime()) / 1000 % (3600));
                tmp.pageChange({page:tmp.data.number});
                obj.content.push(tmp);
            }

            $scope.list2 = obj;

        }
    }]).controller('iqUploaderCtrl',function($scope){
    	$scope.files=[{fileName:'dire 009.JPG',filePath:'/upload\null\/wreport\452cc324-ce47-4704-a398-6afb978ff527_dire 009.JPG',fileType:'image/jpeg',fileSize:'1874706'}];
    	
    	$scope.showFiles=function(){
    		$scope.show=true;
    		console.log("-----",$scope.files);
    	}
    	$scope.files1=[{fileName:'dire 009.JPG',filePath:'/upload\null\/wreport\452cc324-ce47-4704-a398-6afb978ff527_dire 009.JPG',fileType:'image/jpeg',fileSize:'1874706'}];
    	
    })
    .controller("demo_summernoteCtrl",function($scope){
    	$scope.text = "init";
    	$scope.show = function(){
    		alert($scope.text);
    	}
    	var imageUpload = function(files) {
			console.log('image upload:', files);
    	}
    	$scope.options = {
		    height: 300,
		    focus: true,
		    lang:"zh-CN",
		    fontNames: [
             '微软雅黑','宋体','黑体'
           ],
           maximumImageFileSize:102400,
//           onImageUpload:imageUpload, intersept
		    toolbar: [
		            ['Misc',['undo','redo']],
		            ['para', ['ul', 'ol', 'paragraph']],
		            ['height', ['height']],
		            ['table', ['table']],
		            ['insert', ['link', 'picture', 'hr']],
		            ['view', ['fullscreen', 'codeview', 'help']],
		            ['style', ['bold', 'italic', 'underline','strikethrough', 'superscript', 'subscript', 'clear']],
		            ['fontname', ['fontname']],
		            ['fontsize', ['fontsize']],
		            ['color', ['color']]
		        ]
		  };
    })
    .controller("dataValidationCtrl",function($scope){
    	$scope.insubmitted=false;
    	$scope.Save=function(){
    		if($scope.demoForm.$valid){
    			alert("great!");
    		}else{
    			$scope.demoForm.insubmitted=true;
    		}
    	}
    	
    })
    
    .controller("mceCtrl",function($scope){
    	$scope.demo = {};
    	$scope.showDemo = function(){
    		console.log($scope.demo)
    	}
    	$scope.mceOption = {
    			trusted: true,
    			onBlur:function(){
    				console.log("blur",$scope.demo.tinymce1);
		    	}
	    	}
    	$scope.demo.tinymce = '<tableclass="tabletable-hovertable-many-wordstable-header-bgtable-page"><thead><tr><thwidth="17%">申请日期</th><thwidth="10%">申请人</th><thwidth="22%">调入部门</th><thwidth="22">调入岗位</th><thwidth="15%">调入日期</th><thwidth="13%">审批状态</th><thwidth="13%">当前审批人</th></tr></thead><tbody><tr><tdclass="depart-name"><ahref="apply-end1.html">2015-08-28</a></td><td>刘小盈</td><td>信息化管理部</td><td>IT工程师</td><td>2015-08-28</td><tdclass="col-447">审批中</td><td>李壮志</td></tr><trclass="complete"><tdclass="depart-name"><ahref="apply-end1.html">2015-08-28</a></td><td>李铁燕</td><td>信息化管理部A</td><td>咨询顾问</td><td>2015-08-28</td><tdclass="col-447">已完成</td><td></td></tr><tr><tdclass="depart-name"><ahref="apply-end1.html">2015-08-28</a></td><td>李洋</td><td>法务部A</td><td>法务专员</td><td>2015-08-28</td><tdclass="col-447">审批中</td><td>赵园</td></tr><trclass="complete"><tdclass="depart-name"><ahref="apply-end1.html">2015-08-28</a></td><td>彭海娟</td><td>信息化管理部A</td><td>需求分析师</td><td>2015-08-28</td><tdclass="col-447">已完成</td><td></td></tr></tbody></table>'
    })
    .controller("demo_formCtrl",function($scope){
    
    })
    
        .directive('demoFormDire', [function(){
            // Runs during compile
            return {
                require: '?ngModel',
                // name: '',
                // priority: 1,
                // terminal: true,
                // scope: {}, // {} = isolate, true = child, false/undefined = no change
                // controller: function($scope, $element, $attrs, $transclude) {},
                // require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
                // restrict: 'A', // E = Element, A = Attribute, C = Class, M = Comment
                // template: '',
                // templateUrl: '',
                // replace: true,
                // transclude: true,
                // compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
                link: function($scope, iElm, iAttrs, controller) {
                    $Elm = $(iElm);
                    if(controller){
                        controller.$options = {
                            updateOn: 'blur'
                        }
                    }
                    var _div = $("<div>").css({
                        height:"50px"
                    }).hide().appendTo($('body'));
                    for(var i = 0;i<10;i++){
                        $("<div>").click(function(){
                            $Elm.val($(this).html());
                            $Elm.trigger("change");
                        }).css({
                            width:"50px",
                            float:"left",
                            height:"50px",
                            backgroundColor:"#f77"
                        }).html(i).appendTo(_div);
                    }
                    $Elm.on("change", function(v){
                        $scope.$apply(function(){
                            // $scope[iAttrs.ngModel] = i;
                            $scope.$eval(iAttrs.ngModel +"= '"+$Elm.val()+"'");
                        })
                    })
                    $Elm.on('focus',function(){
                        _div.show();
                        // $Elm.trigger("blur");
                    }).on('blur',function(){
//                        _div.hide();
                    })
                }
            };
        }])
})()