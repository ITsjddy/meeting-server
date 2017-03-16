var questionnaireApp = angular.module('questionnaireApp', []);
questionnaireApp.controller("questionnaireController", function($scope, $http,
		$log) {
	$scope.topicTypesData = [ {
		id : "A",
		content : "填空题"
	}, {
		id : "B",
		content : "单项选择题"
	}, {
		id : "C",
		content : "多项选择题"
	}, {
		id : "D",
		content : "简答题"
	} ];

	$scope.fchat = new Object();

	// 题目外层循环索引保存
	$scope.topicMark = 0;

	// 初始化题目
	$scope.fchat.topics = [ {
		topic : "",
		topicType : ""
	} ];

	// 初始化时由于只有一个题目，所以不允许删除
	$scope.fchat.canDescTopic = false;

	// 初始化选项
	$scope.fchat.options = [ [ {
		content : ""
	} ] ];

	// 初始化时由于只有一个选项，所以不允许删除
	$scope.fchat.canDescOptions = [ [ false ] ];

	// 增加题目数
	$scope.fchat.incrTopic = function() {
		var topicLength = $scope.fchat.topics.length;
		$scope.fchat.topics.splice(topicLength, 0, {
			topic : "",
			topicType : ""
		});
		// 增加新的题目后允许删除
		$scope.fchat.canDescTopic = true;

		$scope.fchat.options[topicLength] = [ [ {
			content : ""
		} ] ];

		$scope.fchat.canDescOptions[topicLength] = [ false ];
	}

	// 减少题目数
	$scope.fchat.decrTopic = function($index) {
		var optionLength = $scope.fchat.options[$index].length;
		// 如果题目数大于1，删除被点击题目
		if ($scope.fchat.topics.length > 1) {
			$scope.fchat.topics.splice($index, 1);// 删除当前选中的题目
			$scope.fchat.options.splice($index, 1);// 同时删除当前选中的题目的选项
			$scope.fchat.canDescOptions.splice($index, 1);// 同时删除选项图片显示标识
		}
		// 如果题目数为1，不允许删除
		if ($scope.fchat.topics.length == 1) {
			$scope.fchat.canDescTopic = false;
		}
	}

	// 合并题目值
	$scope.fchat.combineTopics = function() {
		// var cr = "";
		// for (var i = 0; i < $scope.fchat.topics.length; i++) {
		// cr += "#" + $scope.fchat.topics[i].topic;
		// }
		// cr = cr.substring(1);
		// $log.debug("Combined topics: " + cr);
		//
		// return cr;

		// 1.填空题 2.单项选择题 3.多项选择题 4.简答题 题目类型与题目用“&”分隔，
		// 题目用“#”分隔，选项与题目用“|”分隔，选项与选项用“，”分隔。
		// A&t1 # B&t2|o1,o2 # C&t3|o1,o2,o3 # D&t4 ,
		var topic = "";
		for (var i = 0; i < $scope.fchat.topics.length; i++) {
			if ($scope.fchat.topics[i].topic === ""
					|| $scope.fchat.topics[i].topicType === "") {
				continue;
			}

			topic += "#" + $scope.fchat.topics[i].topicType + "&"
					+ $scope.fchat.topics[i].topic;

			if ($scope.fchat.options[i][0].content === undefined
					|| $scope.fchat.options[i][0].content === "") {
				continue;
			}

			topic += "|";
			var option = "";
			for (var j = 0; j < $scope.fchat.options[i].length; j++) {
				option += "," + $scope.fchat.options[i][j].content;
			}
			option = option.substring(1);

			topic += option;
		}

		topic = topic.substring(1);
		return topic;
	}

	// 根据题目类型改变，显示选项
	$scope.fchat.topicOnChange = function($index) {
		var topicType = document.getElementById("topicType" + ($index));
		var optionsDiv = document.getElementById("optionsDiv" + ($index));
		if (topicType.value === "B" || topicType.value === "C") {
			optionsDiv.style.display = "block";
		} else {
			optionsDiv.style.display = "none";
		}
	}

	// 增加选项
	$scope.fchat.incrOption = function(topic, $index) {
		$scope.fchat.options[topic].splice($index + 1, 0, {
			content : ""
		});
		$scope.fchat.canDescOptions[topic][0] = true;// 增加选项后，设置第一个选项的删除按钮可用
		$scope.fchat.canDescOptions[topic].splice($index + 1, 0, true);// 非首个选项，设置变更删除图标为可用的
	}

	// 减少选项
	$scope.fchat.decrOption = function(topic, $index) {
		// 如果选项数大于1，删除被点击选项
		if ($scope.fchat.options[topic].length > 1) {
			$scope.fchat.options[topic].splice($index, 1);
		}
		// 如果选项数为1，不允许删除
		if ($scope.fchat.options[topic].length == 1) {
			$scope.fchat.canDescOptions[topic][0] = false;
		}
	}

	$scope.questionnaireAddSave = function() {
		// 获取到表单是否验证通过
		if (!$scope.form.$valid) {
			alert('表单没有通过验证');
			return;
		}
		var title = $scope.questionnaire.title;
		var description = $scope.questionnaire.description;
		var topics = document.getElementById("topics").value;
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/questionnaire/questionnaireAddSave";
		var param = {
			title : title,
			description : description,
			topics : topics
		};

		$http({
			method : 'POST',
			url : url,
			params : param,
		}).success(function(resultMap) {
			alert("success");
		}).error(function(error) {
			if ('403' == error.status) {
				alert('无权限');
			}
		});

		window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/questionnaire/index";
	}
	
	/*返回*/
    $scope.backPage = function() {
    	window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/questionnaire/index";
    }
});
