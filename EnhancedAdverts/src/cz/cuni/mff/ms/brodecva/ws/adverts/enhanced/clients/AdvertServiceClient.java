package cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.clients;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import cz.cuni.mff.ms.brodecva.ws.adverts.published.Advert;
import cz.cuni.mff.ms.brodecva.ws.adverts.published.AdvertService;

public class AdvertServiceClient {

	private static final String CONFIG_PROPERTIES_FILE_NAME = "config.properties";
	
	private final AdvertService serviceProxy;
	
	public AdvertServiceClient(final AdvertService serviceProxy) {
		if (serviceProxy == null) {
			throw new NullPointerException();
		}
		
		this.serviceProxy = serviceProxy;
	}
	
	public AdvertServiceClient() throws IOException {
		final Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES_FILE_NAME));
	    
	    final String serviceNamespace = props.getProperty("serviceNamespace");
	    final String serviceName = props.getProperty("serviceName");
	    final String portName = props.getProperty("portName");
	    final String endpointAddress = props.getProperty("endpointAddress");
	    
	    final QName qualifiedServiceName = new QName(serviceNamespace, serviceName);
        final QName qualifiedPortName = new QName(serviceNamespace, portName);

        final Service service = Service.create(qualifiedServiceName);
        service.addPort(qualifiedPortName, SOAPBinding.SOAP11HTTP_BINDING,
        		endpointAddress);
        
        this.serviceProxy = service.getPort(qualifiedPortName,  cz.cuni.mff.ms.brodecva.ws.adverts.published.AdvertService.class);
	}
	
	public Advert getAdvert(long id) {
		return this.serviceProxy.getAdvert(id);
	}
	
	public List<Advert> searchPhone(String phone) {
		return this.serviceProxy.searchPhone(phone);
	}
	
	public List<Advert> searchEmail(String email){
		return this.serviceProxy.searchEmail(email);
	}
	
	public List<Advert> searchTags(String[] include, String[] exclude){
		return this.serviceProxy.searchTags(Arrays.asList(include), Arrays.asList(exclude));
	}
	
	public List<Advert> listPublished(String from, String to){
		return this.serviceProxy.listPublished(from, to);
	}
}