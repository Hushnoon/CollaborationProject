window.routes={
	"/home":{
		templateUrl:'app/components/user/home.html',
		controller:'UserController',
		controllerAs:'userCtrl'
	},
	"/login":{

		templateUrl:'app/components/authentication/login.html',
		controller:'AuthenticationController',
		controllerAs:'auathCtrl',
		
	},
	"/user":{
		templateUrl:'app/components/user/userList.html',
		controller:'UserController',
		controllerAs:'userCtrl',

	},
	"/register":{
		templateUrl:'app/components/user/registration.html',
		controller:'UserController',
		controllerAs:'userCtrl'
	},
	"/blog":{
		templateUrl:'app/components/blog/masterBlog.html',
		controller:'BlogController',
		controllerAs:'blogCtrl'
	},
	"/job":{
		templateUrl:'app/components/job/masterJob.html',
		controller:'JobController',
		controllerAs:'jobCtrl'
	},
	"/event":{
		templateUrl:'app/components/event/masterEvent.html',
		controller:'EventController',
		controllerAs:'eventCtrl'
	},
	"/searchUser/:user":{
		templateUrl:'app/components/friend/Search.html',
		controller:'FriendController',
		controllerAs:'frndCtrl'
	},
	"/friend":{
		templateUrl:'app/components/friend/friendHome.html',
		controller:'FriendController',
		controllerAs:'frndCtrl'
	},
	"/chat":{
		templateUrl:'app/components/chat/GroupChat.html',
		controller:'ChatController'
	}
};

app.constant('RESTURI','http://localhost:12080/onlinecollaborationbackend');

app.config(['$routeProvider',function($routeProvider){
	for(var path in window.routes){
		$routeProvider.when(path,window.routes[path]);
	}
	$routeProvider.otherwise({redirectTo:'/login'});
}]);

app.run(function($rootScope,$cookieStore,$http,$location)
{
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};
	if ($rootScope.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser; 
	}

	$rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $cookieStore.get('currentUser');
            console.log('restrictedPage:'+restrictedPage);
            console.log('loggedIn:'+loggedIn);
            if (restrictedPage && !loggedIn) {
            	$location.path('/login');
            }
        });
});

var NavModule=angular.module('NavModule', []);
NavModule.controller('NavController',['$scope','$rootScope','$cookieStore','$location','$http','RESTURI',function($scope,$rootScope,$cookieStore,$location,$http,RESTURI){
	console.log('nav controller loaded');
	$scope.isAdmin=false;
	if($rootScope.currentUser.role=="Admin")
	{
		$scope.isAdmin=true;
	}
	$scope.logout=function(){
		console.log('logout been called');
		console.log($rootScope.currentUser);
		if ($rootScope.currentUser) {
			console.log('inside if ');
			$rootScope.currentUser.isOnline=false;
			console.log($rootScope.currentUser);
			$http.put(RESTURI+'/changeUser',$rootScope.currentUser).then(
				function(response){
					$rootScope.currentUser={};
					$cookieStore.remove('currentUser');
					$location.path("/");
				},
				function(error){
					alert(error);
				});
		}
	}
	$scope.searchYourFriend=function()
	{
		$location.path("/searchUser/"+$scope.searchFriend);
	}
}])