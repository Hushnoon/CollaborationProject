JobModule.controller('JobController', ['JobService','$http','$scope','$rootScope', function(JobService,$scope,$rootScope){
	var me=this;
	me.newJob=false;
	me.allJobs=true;
	me.applied=false;
	me.isAdmin=false;
	me.candidates=false;
	me.add=function()
	{
		me.allJobs=false;
		me.newJob=true;
		me.applied=false;
		me.candidates=false;
		console.log('Add new Jobs');
	}
	me.save=function()
	{
		console.log('Save new Job');
		console.log($rootScope.currentUser);
		me.job.status="active";
		me.job.user=$rootScope.currentUser;	
		JobService.add(me.job)
		.then(function(data){
			console.log("added in JobController");
			me.allJobs=false;
			me.newJob=false;
			me.applied=true;
			me.candidates=false;
		},function(error){
			console.log(error);
		});
	}
	me.viewAllJobs=function()
	{
		me.allJobs=true;
		me.newJob=false;
		me.applied=false;
		me.candidates=false;
		if($rootScope.role=='ADMIN')
		{
			me.isAdmin=true;
		}
		console.log('View All Jobs');
		JobService.viewAll()
		.then(function(data){
			console.log(data);
			me.jobList=data;
		},function(error){
			console.log(error);
		});
	}
	me.viewMyJobs=function()
	{
		console.log('View My Jobs');
		console.log($rootScope.currentUser.userId);
		JobService.viewMyAppliedJobs($rootScope.currentUser.userId)
		.then(function(data){
			//console.log(data);
			me.appliedList=data;
			console.log(me.appliedList);
			me.allJobs=false;
			me.newJob=false;
			me.applied=true;
			me.candidates=false;
		},function(error){
			console.log(error);
		});
	}
	me.apply=function(id)
	{
		console.log('apply job');
		var data={
			job:{jobId:id},
			user:{userId:$rootScope.currentUser.userId},
			status:'new'
		}

		JobService.apply(data)
		.then(function(data1){
			console.log(data1);
		},function(error){
			console.log(error);
		});
	}

	me.manageCandidates=function(jobId)
	{
		JobService.manageCandidates(jobId)
		.then(function(data){
			console.log(data);
			me.candidateList=data;
			me.allJobs=false;
			me.newJob=false;
			me.applied=false;
			me.candidates=true;
		},function(error){
			console.log(error);
		});
	}
	me.viewAllJobs();
}])