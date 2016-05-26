package cz.cuni.mff.ms.brodecva.ws.adverts.published;

import javax.jws.WebService;

import cz.cuni.mff.ms.brodecva.ws.adverts.model.Advert;

@WebService(targetNamespace = "http://published.adverts.ws.brodecva.ms.mff.cuni.cz/", name="AdvertService")
public interface AdvertService {
	Advert getAdvert(long id);
	
	Advert[] searchPhone(String phone);
	
	Advert[] searchEmail(String email);
	
	Advert[] searchTags(String[] include, String[] exclude);
	
	Advert[] listPublished(String from, String to);
}
