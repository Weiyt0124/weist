/**
 * Created by 艾泽拉斯国家地理 on 2017/8/1.
 */
angular.module("myApp", []).controller("loginForm", ['$scope', '$http', function ($scope, $http) {
        $scope.submit=function(){
            var param = {
                userName: $scope.uname,
                passWord: $scope.pword
            }
            $http({
                method:'post',
                url:'/loginPost',
                data: $.param(param),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }

            }).success(function(req){
                if(req.flag){
                        window.location.href = "/";
                    }else {
                        alert(req.message);
                    }
            })
        }

        //记住账号
        $("#remember-me").click(function () {
            var n = document.getElementById("remember-me").checked;
            if (n) {
                $(".zt").show();
            } else {
                $(".zt").hide();
            }
        });

    }]);

