EventModule.controller('EventController', ['EventService','$http','$scope','$rootScope', function(EventService,$scope,$rootScope){
	var me=this;
	me.newEvent=false;
	me.events=true;
	me.uploadImge=false;
	me.isAdmin=false;
	if($rootScope.currentUser.role=='ADMIN'){
		me.isAdmin=true;
	}
	me.add=function()
	{
		me.newEvent=true;
		me.events=false;
		me.uploadImge=false;
		console.log('Add new Blogs');
	}
	me.save=function()
	{
		console.log('Save new Event');
		EventService.addEvent(me.event)
		.then(function(data){
			console.log(data);
			me.newEvent=false;
			me.events=false;
			me.uploadImge=true;
		},function(error){
			console.log(error);
		});
	}
}])