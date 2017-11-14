package com.niit.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.ForumPosts;
import com.niit.model.ForumRequest;
import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.JobApply;
import com.niit.model.ProfilePicture;
import com.niit.model.User;

@Configuration
@EnableTransactionManagement
public class DBconfig {
	@SuppressWarnings("rawtypes")
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder lSfBuilder=
				new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties=new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		 lSfBuilder.addProperties(hibernateProperties);
		Class classes[]=new Class[]{User.class,BlogComment.class,BlogPost.class,Job.class,ProfilePicture.class,Friend.class,JobApply.class,Forum.class,ForumPosts.class,ForumRequest.class,ForumComment.class};
		return  lSfBuilder.addAnnotatedClasses(classes).buildSessionFactory();
	}
	
	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("collab4");
	    dataSource.setPassword("collab4");
	    return dataSource;
	}
	@Bean
	public HibernateTransactionManager hibTransManagement(){
		return new HibernateTransactionManager(sessionFactory());
	}

}
