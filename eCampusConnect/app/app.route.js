window.routes={
	"/home":{
		templateUrl:'app/components/user/home.html'
	},
	"/about":{

		templateUrl:'app/components/basic/about.html',
		controller:'BasicController',
		controllerAs:'basicCtrl',
		requireLogin:false,
		roles:['GUEST','ADMIN','STAFF','STUDENT']
	},
	"/login":{

		templateUrl:'app/components/authentication/login.html',
		controller:'AuthenticationController',
		controllerAs:'auathCtrl',
		requireLogin:false,
		roles:['GUEST','ADMIN','STAFF','STUDENT']
	},
	"/user":{
		templateUrl:'app/components/user/userList.html',
		controller:'UserController',
		controllerAs:'userCtrl',
		requireLogin:false,
		roles:['GUEST','ADMIN','STAFF','STUDENT']
	},
	"/register":{
		templateUrl:'app/components/user/registration.html',
		controller:'UserController',
		controllerAs:'userCtrl',
		requireLogin:false,
		roles:['GUEST','ADMIN','STAFF','STUDENT']
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
	}
};

app.constant('RESTURI','http://localhost:12080/onlinecollaborationbackend');

app.config(['$routeProvider',function($routeProvider){
	for(var path in window.routes){
		$routeProvider.when(path,window.routes[path]);
	}
	$routeProvider.otherwise({redirectTo:'/login'});
}]);