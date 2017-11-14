/**
 * 
 */
app.controller('BlogPostController',function($scope,BlogPostService,$location){
	
	$scope.addBlogPost=function(){
		BlogPostService.addBlogPost($scope.blog).then(function(response){
			alert('Blogpost added successfully and waiting for approval')
			$location.path('/Home')
		},function(response){
			$scope.error=response.data;
			if(response.status==401)
				$location.path('/Login')
				else
					
					$location.path('/addblogpost')
			
		})
		
	}
	
	function blogsApproved(){
		BlogPostService.blogsApproved().then(function(response){
			$scope.listofBlogsApproved=response.data
		},function(response){
			if(response.status==401)
				$location.path('/Login')	
			
		})
	}
	
	function blogsWaitingForApproval(){
		BlogPostService.blogsWaitingForApproval().then(function(response){
			$scope.listofBlogsWaitingForApproval=response.data
		},function(response){
			if(response.status==401)
				$location.path('/Login')
		})
	}
	blogsApproved()
	blogsWaitingForApproval()
})