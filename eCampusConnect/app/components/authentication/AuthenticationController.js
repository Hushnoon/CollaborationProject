AuthenticationModule.controller('AuthenticationController',['AuthenticationService','$scope','$location','$rootScope',function(AuthenticationService,$scope,$location,$rootScope){
	var me=this;
	me.validateUser=function(){
		console.log('Authentication controller');
		AuthenticationService.doValidate(me.user)
		.then(function(response){
			console.log("successful login");
			//console.log(response);
			$rootScope.currentUser=response.data;
			$rootScope.role=response.data.role;
			$location.path('/home');
		},
		function(error){
			console.log("Invalid username or password");
			$location.path('/');
		})
	}
}]);