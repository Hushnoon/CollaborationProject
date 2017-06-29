var EventModule=angular.module('EventModule',[]);
EventModule.service('EventService',['$http','$q','RESTURI',function($http,$q,RESTURI){
	this.addEvent=function(event){
		var deferred=$q.defer();
		$http.post(RESTURI+'/addevent',event)
		.then(
			function(response){
				console.log('success');
				console.log(response.data);
				deferred.resolve(response.data);

			},function(error){
				console.log('reject');
				deferred.reject(error);
			});
		return deferred.promise
	}
}])