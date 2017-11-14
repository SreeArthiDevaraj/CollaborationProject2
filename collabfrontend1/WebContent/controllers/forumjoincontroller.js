/**
 * 
 */
app.controller('ForumJoinController',function($scope,ForumPostService,$location,$rootScope,$cookieStore){
	
	
	
	ForumPostService.getJoinRequests().then(function(response){
		$scope.joinreq = response.data
		$rootScope.reqlen = response.data.length
		$cookieStore.put("reqlen",response.data.length)
		console.log(response.data)
		if(response.data == ""){
			$scope.reqmess="No New Join Requests..!"
		}
		console.log(response.status + "forumjoinReq")
	},function(response){
		if(response.status==401){
			$scope.error=response.data
			$location.path('/login')
		}
	})
	
	$scope.acceptJoinReq=function(reqid){
		ForumPostService.acceptJoinReq(reqid).then(function(response){
			$route.reload();
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
		})
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})
