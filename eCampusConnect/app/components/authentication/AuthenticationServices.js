var AuthenticationModule=angular.module('AuthenticationModule', []);

AuthenticationModule.service('AuthenticationService', ['$http','$q','RESTURI', function($http,$q,RESTURI){
	this.doValidate=function(user){
		console.log("validate user service");
		console.log('user'+user);
		//console.log('pwd:'+pwd);
		var deferred=$q.defer();
		$http.post(RESTURI+'/user/validate',user).then(
			function(response){
				//console.log(response);
				deferred.resolve(response);
			},
			function(error){
				
				deferred.reject(error);
			});
		return deferred.promise
	}
}])