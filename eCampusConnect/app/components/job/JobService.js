var JobModule=angular.module('JobModule', []);

JobModule.service('JobService', ['$http','$q','RESTURI',function($http,$q,RESTURI){
	
	this.add=function(job){
		console.log("Add job service");
		console.log(job);
		var deferred=$q.defer();
		$http.post(RESTURI+'/postjob',job)
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
	this.viewAll=function(){
		console.log('View All Jobs service');
		var deferred=$q.defer();
		$http.get(RESTURI+'/viewalljob')
		.then(
			function(response){
				console.log('success');
				deferred.resolve(response.data);
			},
			function(error)
			{
				console.log('Error');
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.viewMyAppliedJobs=function(userid)
	{
		console.log('view my job service');
		var deferred=$q.defer();
		$http.get(RESTURI+'/getjob/applied/userid/'+userid)
		.then(
			function(response){
				console.log('success');
				deferred.resolve(response.data);
			},
			function(error)
			{
				console.log('Error');
				deferred.reject(error);
			});
		return deferred.promise;
	}
	this.apply=function(data)
	{
		console.log('Apply job service');
		var deferred=$q.defer();
		var flag=false;
		$http.get(RESTURI+'/getjob/applied/userid/'+data.user.userId)
		.then(
			function(response){
				console.log('success');
				for (var i = response.data.length - 1; i >= 0; i--) {

					if(data.job.jobId==response.data[i].job.jobId)
					{
						flag=true;
						break;
					}
				}
				if(flag==false){
					$http.post(RESTURI+'/apply/job',data)
					.then(
						function(response){
							console.log(response);
							deferred.resolve('Successfully Applied');
						},
						function(error)
						{
							console.log('Error');
							deferred.reject(error);
						});			
				}
				else
				{
					deferred.resolve("Already Applied");
				}
			},
			function(error)
			{
				console.log('Error');
				deferred.reject(error);
			});
		
		return deferred.promise;
	}
	this.manageCandidates=function(jobId)
	{
		console.log('view my job service');
		var deferred=$q.defer();
		$http.get(RESTURI+'/getjob/applied/job/'+jobId)
		.then(
			function(response){
				console.log('success');
				deferred.resolve(response.data);
			},
			function(error)
			{
				console.log('Error');
				deferred.reject(error);
			});
		return deferred.promise;
	}
}])

