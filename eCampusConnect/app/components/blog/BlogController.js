BlogModule.controller('BlogController', ['BlogService','$http','$scope','$rootScope', function(BlogService,$scope,$rootScope){
	var me=this;
	me.newBlog=false;
	me.allBlogs=true;
	me.myBlogs=false;
	me.add=function()
	{
		me.allBlogs=false;
		me.newBlog=true;
		me.myBlogs=false;
		me.blogDetail=false;
		console.log('Add new Blogs');
	}
	me.save=function()
	{
		console.log('Save new Blog');
		console.log($rootScope.currentUser);
		me.blog.user=$rootScope.currentUser;	
		BlogService.add(me.blog)
		.then(function(data){
			console.log("added in BlogController");
			me.allBlogs=false;
			me.newBlog=false;
			me.myBlogs=true;
		},function(error){
			console.log(error);
		});
	}
	me.viewAllBlogs=function()
	{
		me.allBlogs=true;
		me.newBlog=false;
		me.myBlogs=false;
		me.blogDetail=false;
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
	me.viewAllBlogs();
}])