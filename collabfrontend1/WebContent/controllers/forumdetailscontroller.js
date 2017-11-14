/**
 * 
 */
app.controller('ForumDetailController',function($scope,$location,ForumPostService,$routeParams){
	$scope.isRejected=false
	$scope.isLiked=false;


	var id=$routeParams.id
	alert('forumdetailcontroller is initiated')
	ForumPostService.getForumById(id).then(function(response){
		$scope.forum=response.data
		
	},function(response){
		if(response.status==401)
			$location.path('/Login')
	})
	
	$scope.updateForum=function(){
		ForumPostService.updateForum($scope.forum).then(function(response){
			
			$location.path('/Home')
				},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path('/Login')
		})
		
	}
	
	$scope.updateLikes=function(){
		$scope.isLiked=!$scope.isLiked;
		if($scope.isLiked){
			$scope.forum.likes=$scope.forum.likes + 1
			
		}
		else{
			$scope.forum.likes=$scope.forum.likes - 1
		}
		ForumPostService.updateForum($scope.forum).then(function(response){},function(response){})
	}
	$scope.showRejectionTxt=function(val){
		$scope.isRejected=val
	}
	
	$scope.addCommentforforum=function(){
		console.log($scope.forumComment)
		$scope.forumComment.forum=$scope.forum
		console.log($scope.forumComment)
		ForumPostService.addCommentforforum($scope.forumComment).then(function (response){
			console.log(response.data)
			$scope.forumComment.commentText=''
			 getforumComments();
		},function(response){
			if(response.status==401)
				$location.path('/Login')
				else
					$location.path('/getforumbyid/'+id)	
		})
	}
	
	function getForumComments(){
		ForumPostService.getForumComments(id).then(function (response){
			$scope.forumComments=response.data
		},function (response){
			if(response.status==401)
				$location.path('/Login')
		})
	}
	
	
	function isParticipant(){
		ForumPostService.isParticipant(id).then(function(response){
			$scope.isparticipant =false
			if(response.data ==""){
				$scope.isparticipant = true
			}
			if(response.data !=""){
			$scope.participant= response.data
			}
			console.log($scope.isparticipant)
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
		})
	}
	
	$scope.joinForum = function(){
		ForumPostService.joinForum(id).then(function(response) {
			console.log(response.status)
			isParticipant()
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
		})
	}
	
	$scope.addForumPost = function(){
		$scope.forumpost.forid = forumid
		$scope.forumpost.postedBy = $rootScope.currentUser.username
		ForumService.addForumPost($scope.forumpost).then(function(response){
			console.log(response.data)
			console.log(response.status)
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
		})
	}
	
	function listForumPosts(){
		ForumPostService.listForumPosts(id).then(function(response){
			$scope.listposts = response.data
			$scope.fplen = $scope.listposts.length
			console.log(response.data)
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
		})
	}

	getForumComments()
})