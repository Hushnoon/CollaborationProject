BlogModule.controller('BlogController', ['BlogService','$http','$scope','$rootScope', function(BlogService,$scope,$rootScope){
	var me=this;
	me.newBlog=false;
	me.allBlogs=true;
	me.myBlogs=false;
	me.isAdmin=false;
	me.add=function()
	{
		me.allBlogs=false;
		me.newBlog=true;
		me.myBlogs=false;
		me.blogDetail=false;
		me.showManage=false;
		console.log('Add new Blogs');
	}
	me.save=function()
	{
		console.log('Save new Blog');
		console.log($rootScope.currentUser);
		me.blog.user=$rootScope.currentUser;	
		me.blog.status='new';
		BlogService.add(me.blog)
		.then(function(data){
			console.log("added in BlogController");
			me.allBlogs=false;
			me.newBlog=false;
			me.myBlogs=true;
			me.showManage=false;
		},function(error){
			console.log(error);
		});
	}
	me.viewAllBlogs=function()
	{
		if($rootScope.currentUser.role=='Admin')
		{
			me.isAdmin=true;
		}
		console.log('isAdmin:'+me.isAdmin);
		me.allBlogs=true;
		me.newBlog=false;
		me.myBlogs=false;
		me.blogDetail=false;
		me.showManage=false;
		console.log('View All Blogs');
		BlogService.viewAll()
		.then(function(data){
			console.log(data);
			me.blogList=data;
		},function(error){
			console.log(error);
		});
	}
	me.viewMyBlogs=function()
	{
		me.allBlogs=false;
		me.newBlog=false;
		me.myBlogs=true;
		me.blogDetail=false;
		me.showManage=false;
		console.log('View My Blogs');
		console.log($rootScope.currentUser.userId);
		BlogService.viewMyBlogs($rootScope.currentUser.userId)
		.then(function(data){
			console.log(data);
			me.blogList=data;
		},function(error){
			console.log(error);
		});
	}
	me.showComments=function(blog)
	{
		me.allBlogs=false;
		me.newBlog=false;
		me.myBlogs=false;
		me.blogDetail=true;
		me.showManage=false;
		console.log(blog);
		me.selectedBlog=blog;
		me.commentList=blog.blogComments;
	}
	me.addComment=function()
	{
		me.comment.blog={"blogId":me.selectedBlog.blogId};
		me.comment.user=$rootScope.currentUser;
		BlogService.addComment(me.comment)
		.then(function(data){
			console.log(data);

			
		},function(error){
			console.log(error);
		});
	}
	me.viewAllBlogsForAdmin=function()
	{
		console.log('View All Blogs for admin');
		me.showManage=true;
		me.allBlogs=false;
		me.newBlog=false;
		me.myBlogs=false;
		me.blogDetail=false;
		console.log(me.showManage);
		BlogService.viewAll()
		.then(function(data){
			me.newBlogList=data;
			console.log(data);
		},function(error){
			console.log(error);
		});
	}
	me.accept=function(blog)
	{
		console.log('blog data:'+blog);
		blog.status='confirm';
		BlogService.update(blog)
		.then(function(data){
			me.viewAllBlogsForAdmin();
			alert('Successfully Updated');
		},function(error){
			alert('Error in updation kindly contact tech team!!!');
		});
	}

	me.reject=function(blog)
	{
		blog.status='reject';
		BlogService.update(blog)
		.then(function(data){
			me.viewAllBlogsForAdmin();
			alert('Successfully Updated');
		},function(error){
			alert('Error in updation kindly contact tech team!!!');
		});
	}
	me.viewAllBlogs();
}])