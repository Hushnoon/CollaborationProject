UserModule.controller('UserController', ['UserService','$scope','$rootScope','$location',function(UserService,$scope,$rootScope,$location){
	var me=this;
	me.user={};
	me.updateShow=true;
	me.selectedUser={};
	me.loggedIn=false;
	var refresh=function(){
			me.user=$rootScope.currentUser;
	}

	refresh();

	me.addUser=function(){
		console.log(me.user);
		me.user.role="Guest";
		me.user.isOnline=false;
		me.user.status="new";
		UserService.addUser(me.user).then(
			function(response)
			{
				console.log(response.data);
				$location.path('/login');
			},
			function(error){
				console.log(error);

			})

	}

	me.deleteUser=function(user){
		console.log(me.id);

		UserService.deleteUser(user.userId).then(
			function(response)
			{
				//	console.log(response.data);
				refresh();
			},
			function(error){
				console.log(error);

			})

	}

	me.editUser=function(user){
		console.log("edit user1");
		me.updateShow=false;
		me.selectedUser.userId=user.userId;
		me.selectedUser.fullName=user.fullName;
		me.selectedUser.userName=user.userName;
		me.selectedUser.email=user.email;
		me.selectedUser.role=user.role;
	}

	me.updateUser=function(){
		console.log("update User");
		UserService.updateUser(me.selectedUser).then(
			function(response)
			{
				me.updateShow=true;
				refresh();
			},
			function(error){
				console.log(error);

			})

	}
}]);