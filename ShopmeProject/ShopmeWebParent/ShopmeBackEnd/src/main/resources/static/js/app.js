var app = angular.module('eNamApp', ['ngRoute', 'ngResource']);
app.config(['$routeProvider',function($routeProvider) {
    $routeProvider
    .when('/users', {
        templateUrl : 'template/listuser.html',
        controller : 'listUserController'
    }).when('/register-new-user',{
        templateUrl : 'template/userregistration.html',
        controller : 'registerUserController'
    }).when('/update-user/:id',{
        templateUrl : '/template/userupdation.html' ,
        controller : 'usersDetailsController'
    }).otherwise({
        redirectTo : '/',
        templateUrl : 'template/home.html',
    });
}]);