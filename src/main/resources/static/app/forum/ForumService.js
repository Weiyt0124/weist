/**
 *  @Author Wyt
 */
var myService = angular.module('myApp.forumService', []);


myService.service('forumService', ["$http", function ($http) {
    this.findForumList = function () {
        return $http.post("forum/list");
    }
}]);