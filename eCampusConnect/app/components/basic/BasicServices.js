var BasicModule=angular.module('BasicModule',[]);

BasicModule.service('BasicService',['$http','$q','RESTURI',function($http,$q,RESTURI){
	
	this.getGreeting=function(){

		var deferred=$q.defer();

		$http.get(RESTURI+'/greeting')
		.then(
			//success callback
			function(response){
				deferred.resolve(response.data.responseMessage);
		},
			//error callback
			function(error){
				deferred.reject(error);
			}
		);

		return deferred.promise;
	}
}]);