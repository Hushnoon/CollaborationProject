AuthenticationModule.controller('AuthenticationController',['AuthenticationService','$scope','$location','$rootScope','$cookieStore',function(AuthenticationService,$scope,$location,$rootScope,$cookieStore){
	var me=this;
	me.isNew=false;
	me.validateUser=function(){
		console.log('Authentication controller');
		AuthenticationService.doValidate(me.user)
		.then(function(response){
			console.log("successful login");
			var user=response.data;
			if(user.status=="new")
			{
				me.isNew=true;
			}
			else if(user.status=="confirm")
			{
				user.isOnline=true;
				AuthenticationService.makeOnline(user)
				.then(function(data){

					$rootScope.currentUser=response.data;
					$rootScope.role=response.data.role;
					$cookieStore.put('currentUser',response.data);
					$location.path('/home');

				},function(error){
					alert("Server Error kindly visit later!!!");
					$location.path('/');
				})
			}
		},
		function(error){
			alert("Invalid username or password");
			$location.path('/');
		})
	}
}]);