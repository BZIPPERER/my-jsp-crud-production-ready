package com.production.listener;
import java.net.UnknownHostException;
// alles kann nichts muss
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mongodb.MongoClient;

@WebListener
public class MongoDBContextListener implements ServletContextListener
{
	@Override
	public void contextDestroyed(ServletContextEvent sce) 
	{
		MongoClient mongo = (MongoClient) sce.getServletContext()
				.getAttribute("MONGO_CLIENT");
						     mongo.close();
System.out.println("MongoClient closed successfully");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) 
	{
		// TODO Auto-generated method stub
		try {
			ServletContext ctx = sce.getServletContext();
			MongoClient mongo = new MongoClient(
					ctx.getInitParameter("MONGODB_HOST"), 
					Integer.parseInt(ctx.getInitParameter("MONGODB_PORT")));
			System.out.println("MongoClient initialized successfully");
			sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
		} catch (Exception e) 
		{
			throw new RuntimeException("MongoClient init failed");
		}
	}

}
