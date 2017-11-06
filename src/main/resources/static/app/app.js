/**
 * Created by 艾泽拉斯国家地理 on 2017/8/4.
 */

angular
    .module('myApp', ['ui.router','myApp.forum','myApp.forumService'
    ])
    .constant("myAppConfig", {
        prefix: "/"
    })
    .config(["$httpProvider", '$locationProvider', '$stateProvider', '$urlRouterProvider', "myAppConfig", '$logProvider',
        function ($httpProvider, $locationProvider, $stateProvider, $urlRouterProvider, myAppConfig, $logProvider) {

            $logProvider.debugEnabled(false);

            $urlRouterProvider.otherwise('/index');

            $stateProvider
            // 主页
                .state('index', {
                    url: '/index',
                    templateUrl: myAppConfig.prefix + 'app/index.html'
                })
                .state('buttons', {
                    url: '/buttons',
                    templateUrl: myAppConfig.prefix + 'app/buttons.html'
                })
                .state('forum', {
                    url: '/forum',
                    templateUrl: myAppConfig.prefix + 'app/forum/views/list.html',
                    controller:'forumCtrl'
                })
                .state('panels', {
                    url: '/panels',
                    templateUrl: myAppConfig.prefix + 'app/panels.html'
                })

        }])
    .controller('indexController', ["$scope", "$http", "$location", function ($scope, $http, $location) {
        $http.get("user/findUserName").success(function (res) {
            $scope.nickName = res.nickName;
        })

        $scope.replyList = function () {
            $http.get("messageBoard/replyList").success(function (res) {
                $scope.data = res;
            })
        }
        $scope.findUserInfo = function () {
            $http.get("user/findUserInfo").success(function (res) {
                $scope.userInfo = res.userInfo;
            })
        }

        /**
         *  修改密码
         */
        $scope.openPasswordModal = function () {
            $("#passwordModal").modal();
        }
        $("#modifyPassword").validate({
            errorPlacement: function (error, element) {
                $(element).parent('div').append(error);
            },
            onkeyup: false,
            rules: {
                password: {
                    required: true
                },
                repassword: {
                    required: true
                },
            },
            messages: {
                password: {
                    required: '密码不能为空'
                },
                repassword: {
                    required: '密码不能为空'
                },
            },
            submitHandler: function (form) {
                var param = {
                    password: $scope.password
                }
                $http({
                    method: 'post',
                    url: 'user/changePassword',
                    data: $.param(param),
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }

                }).success(function (req) {
                    if (req) {
                        $("#close").click();
                        layer.msg('操作成功!', {
                            icon: 1,
                            time: 1000
                        });
                        $location.path("index")
                    } else {
                        alert('修改失败!');
                    }
                })
            }
        })
        $scope.openModifyAvatar = function () {
            $("#avatarModal").modal();
        }
    }])