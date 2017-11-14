/**
 * 
 */
app.factory('JobService',function($http){
	var jobService={}
	var BASE_URL="http://localhost:8088/collabmiddleend"
		jobService.addJob=function(job)
		{
		return $http.post(BASE_URL+"/addjob",job)
		}
	jobService.getAllJobs=function()
	{
	return $http.get(BASE_URL+"/getalljobs")
	}
	jobService.getJobDetails=function(jobId)
	{
	return $http.get(BASE_URL+"/getjob/"+jobId)
	}
	jobService.isApplied = function(jobId)
	{
		return $http.get(BASE_URL+"/isapplied/"+jobId)
	}
	jobService.applyJob = function(jobId)
	{
		return $http.post(BASE_URL+"/applyjob/"+jobId)
	}
	return jobService;
})