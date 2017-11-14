/**
 * 
 */
var app = angular.module("app", [ 'ngRoute','ngCookies' ])
app.config(function($routeProvider) {

	$routeProvider
	
	.when('/Login', {
		templateUrl : 'views/Login.html', controller:'UserController'
	})
.when('/SignUp', {
		templateUrl : 'views/SignUp.html', controller:'UserController'
	})
	
	.when('/Home', {
		templateUrl : 'views/Home.html'
	})
	
	.when('/editprofile', {
		templateUrl : 'views/editprofile.html', controller:'UserController'
	})
	
	.when('/addblogpost', {
		templateUrl : 'views/blogPostform.html', controller:'BlogPostController'
	})
	.when('/getblogs', {
		templateUrl : 'views/blogsList.html', controller:'BlogPostController'
	})
	.when('/getblogbyid/:id', {
		templateUrl : 'views/blogdetails.html', controller:'BlogPostDetailController'
	})
	
	.when('/getapprovalform/:id', {
		templateUrl : 'views/blogapprovalform.html', controller:'BlogPostDetailController'
	})
	.when('/saveforum', {
		templateUrl : 'views/forum.html', controller:'ForumController'
	})
	.when('/getforums', {
		templateUrl : 'views/forumsList.html', controller:'ForumController'
	})
	.when('/getforumbyid/:id', {
		templateUrl : 'views/forumdetails.html', controller:'ForumDetailController'
	})
	
	.when('/getapprovalform/:id', {
		templateUrl : 'views/forumapprovalform.html', controller:'ForumDetailController'
	})
	
	.when('/getJoinRequests', {
		templateUrl : 'views/forumjoin.html', controller:'ForumJoinController'
	})
	.when('/addjob', {
		templateUrl : 'views/jobform.html', controller:'JobController'
	})
	.when('/getalljobs', {
		templateUrl : 'views/joblist.html', controller:'JobController'
	})
	.when('/uploadprofilepic', {
		templateUrl : 'views/profilepicture.html', controller:'ProfilePictureController'
	})
	.when('/getsuggestedusers', {
		templateUrl : 'views/suggestedusers.html', controller:'FriendController'
	})
	.when('/pendingrequests', {
		templateUrl : 'views/pendingrequests.html', controller:'FriendController'
	})
	.when('/listoffriends', {
		templateUrl : 'views/listoffriends.html', controller:'FriendController'
	})
	.when('/chat',{
		templateUrl:'views/chat.html',
		controller:'ChatCtrl'
	})
	
	.otherwise('/Home', {
		templateUrl : 'views/Home.html'
			,controller:'HomeController'
	})
	
})

app.run(function($rootScope,$cookieStore,$location,UserService,BlogPostService){
	
	if($rootScope.currentUser==undefined){
		$rootScope.currentUser=$cookieStore.get('userDetails')
}
		$rootScope.logout=function(){
		UserService.logout().then(function(response){
			delete $rootScope.currentUser;
			$cookieStore.remove('userDetails')
			$location.path('/Login')
			
		},function(response){
			console.log(response.status)
			if(response.status==401){
				delete $rootScope.currentUser;
				$cookieStore.remove('userDetails')
				$location.path('/login')

			}
				
		})
	}
		
		function getNotification(){
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
