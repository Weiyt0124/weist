var myService = angular.module("myApp.forum", []);

myService.controller('forumCtrl', ['$scope', '$filter', 'forumService', function ($scope, $filter, forumService) {

    $scope.findForumList = function () {
        forumService.findForumList().success(function (data) {
            $scope.data = data.forum;
            console.log( $scope.data );
        });
    }
}]);
