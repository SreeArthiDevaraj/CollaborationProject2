/**
 * 
 */
app.controller('ForumController',function($scope,ForumPostService,$location,$route){
	
	$scope.SaveForum=function(){
		ForumPostService.SaveForum($scope.forum).then(function(response){
			alert('Forum added successfully and waiting for approval')
			$route.reload()
			$location.path('/Home')
		},function(response){
			$scope.error=response.data;
			if(response.status==401)
				$location.path('/Login')
				else
					
					$location.path('/saveforum')
			
		})
		
	}
	function forumsApproved(){
		ForumPostService.forumsApproved().then(function(response){
			$scope.listofForumsApproved=response.data
		},function(response){
			if(response.status==401)
				$location.path('/Login')	
			
		})
	}
	
	function forumsWaitingForApproval(){
		ForumPostService.forumsWaitingForApproval().then(function(response){
			$scope.listofForumsWaitingForApproval=response.data
			console.log($scope.listofForumsWaitingForApproval)
		},function(response){
			if(response.status==401)
				$location.path('/Login')
		})
	}
	forumsApproved()
	forumsWaitingForApproval()
})