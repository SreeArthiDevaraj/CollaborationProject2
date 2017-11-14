/**
 * 
 */
app.controller('UserController',function($scope,UserService,$location,$rootScope,$cookieStore){
	
	$scope.registerUser=function(){
		console.log("USER DATA IS"+$scope.user)
	UserService.registerUser($scope.user).then(function(response){
		console.log(response.data)
		console.log(response.status)
		$location.path('/Login')
		
	},function(response){
		console.log(response.data)
		$scope.error=response.data
	console.log(response.status)
	$location.path('/SignUp')
	})
	}
	
	$scope.updateUser=function(){
		UserService.updateUser($scope.user).then(function(response){
			alert('Updated the details successfully')
			$location.path('/Home')
		},function(response){
			if(response.status==401){
				$location.path('/login')

			}
			else{
				$scope.error=response.data
				$location.path('/editprofile')
				}
			

	})
		}
	$scope.login=function(){
		console.log($scope.user)
	UserService.login($scope.user).then(function(response){
		console.log(response.data)
		console.log(response.status)
		$rootScope.currentUser=response.data//username
		$cookieStore.put('userDetails',response.data)
		$location.path('/Home')
		
	},function(response){
		$scope.error=response.data.message
	console.log(response.status)
	$location.path('/Login')
	})
	}
	if($rootScope.currentUser!=undefined){
		UserService.getUser().then(function(response){
			$scope.user=response.data
		},function(response){
			console.log(response.status)
			if(response.status==401){
				delete $rootScope.currentUser;
				$cookieStore.remove('userDetails')
				$location.path('/login')

			}
				
			})
		}
})