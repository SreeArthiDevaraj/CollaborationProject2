/**
 * 
 */
app.controller('FriendController',function($scope,FriendService,$location){
	function listOfSuggestedUsers(){
		FriendService.listOfSuggestedUsers().then(function(response){
			$scope.suggestedUsers=response.data
			
			
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
		})
		
		
	}
	function pendingRequests(){
		FriendService.pendingRequests().then(function(response){
			$scope.pendingRequests=response.data
			
			
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
		})
	}
	
		function listOfFriends(){
	FriendService.listOfFriends().then(function(response){
		$scope.friends=response.data
		console.log(response.data)
	},function(response){
		if(response.status==401){
			$location.path('/login')
		}
	})
	
	}


	$scope.sendFriendRequest=function(toId){
		FriendService.sendFriendRequest(toId).then(function(response){
			alert('Friend Request has been send successfully')
			$location.path('/getsuggestedusers')
			
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
		})
	}
	
	$scope.updatePendingRequest=function(request,statusValue){
		console.request
		console.log(request.status)
		request.status=statusValue
		console.log(request.status)
		console.request
		FriendService.updatePendingRequest(request).then(function(response){
			pendingRequests()
			$location.path('/pendingrequests')
			
		},function(response){
			if(response.status==401){
				$location.path('/login')
			}
		})
	}
	
	listOfFriends()
	listOfSuggestedUsers()
	pendingRequests()
	
})
