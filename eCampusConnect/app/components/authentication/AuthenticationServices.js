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
	this.makeOnline=function(user){
		var deferred=$q.defer();
		$http.put(RESTURI+'/changeUser',user).then(
			function(response){
				deferred.resolve(response.data);
			},
			function(error){
				
				deferred.reject(error);
			});
		return deferred.promise
	}
}])