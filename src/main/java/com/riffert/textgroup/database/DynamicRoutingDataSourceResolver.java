package com.riffert.textgroup.database;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class DynamicRoutingDataSourceResolver extends AbstractRoutingDataSource
{
		private static String _VALUE = "dataSource1";
		
		private HttpSession _session()
		{
		    ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		    
		    if (request != null)
		    {
		    	if (request.getRequest()!= null)
		    	{
		    		if (request.getRequest().getSession() != null)
		    		{
		    			return request.getRequest().getSession();
		    		}
		    		System.out.println("request.getRequest().getSession() Is Null [3]");
		    	}
		    	System.out.println("request.getRequest() Is Null [2]");
		    }
		    else
		    {
		    	System.out.println("request Is Null [1]");
		    }
		    return null;
		}
		


	    @Override
	    protected Object determineCurrentLookupKey()
	    {
	    	if (_session() != null)
	    	{
	    		String value = (String) _session().getAttribute("value");
	    		System.out.println("*** value --> "+value);
	    		
	    		if (value == null)
	    		{
	    			return _VALUE;
	    		}
	    		else
	    		{
	    			return value;
	    		}
	    	}
	    	else
	    	{
	    		System.out.println("_session() : null");
	    		return _VALUE;
	    	}
	    }
}