/**
 * 
 */
app.factory('ForumPostService',function($http){
	var forumpostService={}
	var BASE_URL="http://localhost:8088/collabmiddleend"
		
		forumpostService.SaveForum=function(forum)
		{
		return $http.post(BASE_URL+"/SaveForum",forum)
		}
	
	forumpostService.forumsWaitingForApproval=function()
	{
	return $http.get(BASE_URL+"/getforums/"+0)
	}
	
	forumpostService.forumsApproved=function()
	{
	return $http.get(BASE_URL+"/getforums/"+1)
	}
	
	forumpostService.getForumById=function(id)
	{
	return $http.get(BASE_URL+"/getforumbyid/"+id)
	}
	
	forumpostService.updateForum=function(forum)
	{
		console.log(forum) 
	return $http.put(BASE_URL+"/updateForum",forum)
	}
	
	forumpostService.isParticipant=function(id){
		return $http.get(BASE_URL+"/isparticipant/"+id)
	}
	
	forumpostService.joinForum = function(id) {
		return $http.post(BASE_URL+"/joinforum/"+id)
	}
	
	forumpostService.addForumPost=function(forumpost){
		return $http.post(BASE_URL+"/add/forumpost",forumpost)
	}
	
	forumpostService.listForumPosts = function(forumid) {
		return $http.get(BASE_URL+"/list/forumpost/"+forumid)
	}
	
	forumpostService.getJoinRequests = function(){
		return  $http.get(BASE_URL+"/getjoinreq/"+0)
	}
	
	forumpostService.acceptJoinReq = function(reqid) {
		return $http.post(BASE_URL+"/acceptjoinreq/"+reqid) 
	}
forumpostService.addCommentforforum = function(forumComment)
	{
		console.log(forumComment) 
	return $http.post(BASE_URL+"/addCommentforforum",forumComment)
	}
	forumpostService.getForumComments=function(id)
	{
		
	return $http.get(BASE_URL+"/getcommentsforforum/"+id)
	}
	return forumpostService;
	
})