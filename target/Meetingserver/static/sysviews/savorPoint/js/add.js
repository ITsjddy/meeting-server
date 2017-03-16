var addSavorPoint = angular.module('addSavorPoint', []);

addSavorPoint.controller("spAddCtlr",
	function($scope, $http) {

		var urlStr = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/savorPoint/";
		$scope.fchat = new Object();
		var ltype = [{
			id: "",
			content: "选择语言"
		}, {
			id: "B",
			content: "英文"
		}, {
			id: "C",
			content: "德语"
		}, {
			id: "D",
			content: "法语"
		}];
		$scope.topicTypesData = ltype;

		/* 语言外层循环索引保存*/
		$scope.topicMark = 0;

		/*初始化语言*/
		$scope.spAddressNames = [{}];

		/* 初始化时由于只有一个语言，所以不允许删除*/
		$scope.fchat.canDescTopic = false;

		$scope.fchat.canIncrTopic = true;

		/*初始化时由于只有一个选项，所以不允许删除*/
		$scope.fchat.canDescOptions = [
			[false]
		];
		initSavorPoint();

		/*添加*/
		$scope.addSavorPoint = function() {
			var savorPoint = $scope.savorPoint;
			params = {
				id: savorPoint.id,
				spOnly: savorPoint.spOnly,
				spLongitude: savorPoint.spLongitude,
				spDimensions: savorPoint.spDimensions,
				spLogo: savorPoint.spLogo,
				spType: savorPoint.spType,
				spName: savorPoint.spName,
				spAddress: savorPoint.spAddress,
				spLanguageType: savorPoint.spLanguageType,
				spAddressNames: $scope.spAddressNames
			};
			$http({
				method: 'POST',
				url: urlStr + "saveList",
				params: params,
			}).success(function(result) {
				if (result.success) {
					window.location.href = urlStr + "openPage";
				} else {
					alert('页面加载失败！');
				}
			}).error(function(error) {
				if ('403' == error.status) {
					alert('无权限');
				}
			});
		};

		/* 根据语言类型改变，显示选项*/
		$scope.fchat.topicOnChange = function($index, type) {
			var topicType = document.getElementById("topicType" + ($index));
			var optionsDiv = document.getElementById("optionsDiv" + ($index));
			for (var i = 1; i < $index + 2; i++) {
				if (i !== $index) {
					var id = '#topicType' + i;
					var value = $(id).val();
					if (type === value) {
						alert("语言类型已存在，请重新选择！");
						var id = '#topicType' + $index;
						$(id).val(ltype[0].id);
						break;
					}

				}
			}
			optionsDiv.style.display = topicType.value !== '' ? 'block' : 'none';
		}

		/* 增加语言数*/
		$scope.fchat.incrTopic = function() {
			var topicLength = $scope.spAddressNames.length;
			$scope.spAddressNames.splice(topicLength, 0, {
				topic: '',
				topicType: '',
				spName: '',
				spAddress: ''
			});
			$scope.fchat.canDescTopic = true;

			$scope.fchat.canDescOptions[topicLength] = [false];
			if (topicLength == $scope.topicTypesData.length - 2) {
				$scope.fchat.canIncrTopic = false;
			}
		}

		/*减少语言数*/
		$scope.fchat.decrTopic = function($index) {
			if ($scope.spAddressNames.length > 1) {
				$scope.fchat.canIncrTopic = true;
				$scope.spAddressNames.splice($index, 1);
				$scope.fchat.canDescOptions.splice($index, 1);
			}
			if ($scope.spAddressNames.length == 1) {
				$scope.fchat.canDescTopic = false;
				$scope.fchat.canIncrTopic = true;
			}
		}

		/*返回*/
		$scope.backPage = function() {
			window.location.href = urlStr + "openPage";
		}

		/*页面数据初始化*/
		function initSavorPoint() {

			/*兴趣点类型*/
			$scope.spTypes = [{
				name: '景区',
				age: '123'
			}, {
				name: '酒店',
				age: '4321'
			}];
			$scope.savorPoint = {};

		}
	});