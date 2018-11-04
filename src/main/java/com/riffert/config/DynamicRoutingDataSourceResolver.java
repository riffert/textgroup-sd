package com.riffert.config;

import javax.servlet.http.HttpSession;

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
					    			return request.getRequest().getSession();
	
				    	}
			    }

			    return null;
		}

	    @Override
	    protected Object determineCurrentLookupKey()
	    {
		    	if (_session() != null)
		    	{
		    		String value = (String) _session().getAttribute("value");
		    		
		    		if (value == null)
		    			return _VALUE;
		    		else
		    			return value;
		    	}
		    	else
		    		return _VALUE;
	    }
}