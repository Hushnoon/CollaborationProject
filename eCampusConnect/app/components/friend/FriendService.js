var FriendModule=angular.module('FriendModule', []);

FriendModule.service('FriendService', ['$http','$q','RESTURI',function($http,$q,RESTURI){
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
	this.sendRequest=function(friend){
		console.log("service:");
		var deferred=$q.defer();
		$http.post(RESTURI+'/sendRequest',friend)
		.then(
			function(respopnse){

				deferred.resolve(respopnse);
			},
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.getMyRequests=function(user){
		console.log("service:");
		var deferred=$q.defer();
		$http.get(RESTURI+'/getMyRequests/'+user.userId)
		.then(
			function(respopnse){

				deferred.resolve(respopnse.data);
			},
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.getSentRequests=function(user){
		console.log("service:");
		var deferred=$q.defer();
		$http.get(RESTURI+'/getSentRequests/'+user.userId)
		.then(
			function(respopnse){

				deferred.resolve(respopnse.data);
			},
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.confirmRequest=function(frndData){
		console.log("confirm service:");
		var deferred=$q.defer();
		$http.put(RESTURI+'/updateRequest',frndData)
		.then(
			function(respopnse){
				console.log(respopnse);
				deferred.resolve(respopnse.data);
			},
			function(error){
				console.log(error);
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.rejectRequest=function(frndData){
		console.log("reject service:");
		var deferred=$q.defer();
		$http.put(RESTURI+'/updateRequest',frndData)
		.then(
			function(respopnse){

				deferred.resolve(respopnse.data);
			},
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.deleteRequest=function(frndData){
		console.log("delete request service:");
		console.log(frndData);
		var deferred=$q.defer();
		$http.delete(RESTURI+'/delete/'+frndData)
		.then(
			function(respopnse){
				console.log(respopnse);
				deferred.resolve(respopnse.data);
			},
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.getFriends=function(uid){
		console.log("delete request service:");
		var deferred=$q.defer();
		$http.get(RESTURI+'/getFriends/'+uid)
		.then(
			function(respopnse){

				deferred.resolve(respopnse.data);
			},
			function(error){
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.deleteFriend=function(){}
}])