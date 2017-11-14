/**
 * 
 */
app.controller('HomeController',function(BlogPostService,$rootScope,$location){
	
	function getNotification() {
		BlogPostService.getNotification().then(function(response){
			$rootScope.blogApprovalStatus=response.data
			$rootScope.approvalStatusLength=$rootScope.blogApprovalStatus.length	
			
		},function(response){
			if(response.status==401)
				$location.path('/login')	
		})	
		
	}
	$rootScope.updateviewedStatus=function(blogPost){
		blogPost.viewed=1
		BlogPostService.updateBlogPost(blogPost).then(function(response){
			getNotification();
		},function (response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	getNotification()

})