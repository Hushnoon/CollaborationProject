var UserModule=angular.module('UserModule', []);

UserModule.service('UserService', ['$http','$q','RESTURI', function($http,$q,RESTURI){

	this.getUsers=function(){
		var deferred=$q.defer();
		$http.get(RESTURI+'/listUsers')
		.then(
			//success callback
			function(respopnse){
				deferred.resolve(respopnse.data);
			},
			//error callback
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}

	this.addUser=function(user){
		console.log("service:");
		user.role='GUEST';
		console.log(user);
		var deferred=$q.defer();
		$http.post(RESTURI+'/addUser',user)
		.then(
			function(respopnse){
				deferred.resolve(respopnse.data);
			},
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}


	this.deleteUser=function(userId){
		console.log("delete service:");
		//console.log(user);
		var deferred=$q.defer();
		$http.delete(RESTURI+'/user/'+userId)
		.then(
			function(respopnse){
				deferred.resolve(respopnse.data);
			},
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.updateUser=function(user){
		console.log("update service:");
		//console.log(user);
		var deferred=$q.defer();
		$http.put(RESTURI+'/user/'+user.userId,user)
		.then(
			function(respopnse){
				deferred.resolve(respopnse.data);
			},
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}
}]);