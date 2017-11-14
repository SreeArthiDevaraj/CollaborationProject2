/**
 * 
 */
app.controller('BlogPostDetailController',function($scope,$location,BlogPostService,$routeParams){
	$scope.isRejected=false
	$scope.isLiked=false;

	var id=$routeParams.id
	alert('blogpostdetailcontroller is initiated')
	BlogPostService.getBlogPostById(id).then(function(response){
		$scope.blogPost=response.data
		
	},function(response){
		if(response.status==401)
			$location.path('/Login')
	})
	
	$scope.updateBlogPost=function(){
		BlogPostService.updateBlogPost($scope.blogPost).then(function(response){
			$location.path('/getblogs')
				},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path('/Login')
		})
	}
	$scope.updateLikes=function(){
		$scope.isLiked=!$scope.isLiked;
		if($scope.isLiked){
			$scope.blogPost.likes=$scope.blogPost.likes + 1
			
		}
		else{
			$scope.blogPost.likes=$scope.blogPost.likes - 1
		}
		BlogPostService.updateBlogPost($scope.blogPost).then(function(response){},function(response){})
	}

	$scope.showRejectionTxt=function(val){
		$scope.isRejected=val
	}
	$scope.addComment=function(){
		console.log($scope.blogComment)
		$scope.blogComment.blogPost=$scope.blogPost
		console.log($scope.blogComment)
		BlogPostService.addComment($scope.blogComment).then(function (response){
			console.log(response.data)
			$scope.blogComment.commentText=''
			 getBlogComments();
		},function(response){
			if(response.status==401)
				$location.path('/Login')
				else
					$location.path('/getblogbyid/'+id)	
		})
	}
	function getBlogComments(){
		BlogPostService.getBlogComments(id).then(function (response){
			$scope.blogComments=response.data
		},function (response){
			if(response.status==401)
				$location.path('/Login')
		})
	}
	getBlogComments()
})