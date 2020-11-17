package api.ws.rest.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import api.restbean.InputExpertBean;

public abstract class ClientRestUtils {
	
	public static <T> T invoke(InputExpertBean in, Class<T> c) {
		
		Client client = Client.create();
		WebResource webResource = client
		           .resource("http://localhost:8081/Apirest/service/analyze");//To be configured by injection

		try {
			ClientResponse response = webResource.accept("application/json")
			           .post(ClientResponse.class,in);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
			    + response.getStatus());
			}

			T output = response.getEntity(c);
			        
			return output;
		} catch (UniformInterfaceException e) {
			return null;
		} catch (ClientHandlerException e) {
			return null;
		}
	}
}
