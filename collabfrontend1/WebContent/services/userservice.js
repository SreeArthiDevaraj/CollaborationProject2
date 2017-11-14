/**
 * 
 */

app.factory('UserService',function($http){
	var userService={}
	var BASE_URL="http://localhost:8088/collabmiddleend"
		userService.registerUser=function(user)
		{
		return $http.post(BASE_URL+"/registerUser",user)
		}
	userService.login=function(user)
	{
	return $http.post(BASE_URL+"/login",user)
	}
	userService.logout=function()
	{
	return $http.get(BASE_URL+"/logout")
	}
	userService.getUser=function()
	{
	return $http.get(BASE_URL+"/getuser")
	}
	
	userService.updateUser=function(user)
	{
	return $http.put(BASE_URL+"/updateuser",user)
	}
	
	return  userService;
})