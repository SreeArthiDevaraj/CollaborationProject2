package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

public interface BlogPostDao {

	void addBlogPost(BlogPost blogPost);
	List<BlogPost> getBlogs(int approved);
	BlogPost geBlogById(int id);
	void updateBlogPost(BlogPost blogPost);
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getBlogComments(int blogPostId);
	List<BlogPost> getNotification( String username);
}
