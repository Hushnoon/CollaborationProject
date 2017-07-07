EventModule.controller('EventController', ['EventService','$scope','$rootScope','$http',function(EventService,$scope,$rootScope,$http){
	var me=this;
	me.newEvent=false;
	me.events=true;
	me.uploadImge=false;
	me.isAdmin=false;
	if($rootScope.currentUser.role=='Admin'){
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
	me.continueFileUpload=function (){
		var uploadUrl='http://localhost:12080/onlinecollaborationbackend/fileupload';
		var formData=new FormData();
		formData.append("file",file.files[0]);
		$http({
			method: 'POST',
			url: uploadUrl,
			headers: {'Content-Type': undefined},
			data: formData,
			transformRequest: function(data, headersGetterFunction) {
				return data;
			}
		})
		.then(function(response) {   
			alert("success");
		},function(error){
			alert('error');
		})

	};
}])