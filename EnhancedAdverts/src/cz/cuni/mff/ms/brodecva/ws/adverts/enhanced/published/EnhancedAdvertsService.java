package cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.published;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.clients.AdvertServiceClient;
import cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.clients.MapaKriminalityClient;
import cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.model.EnhancedAdvert;
import cz.cuni.mff.ms.brodecva.ws.adverts.published.Advert;

@Path("/enhancedadverts")
public class EnhancedAdvertsService {

	private static final String LIST_DELIMITER = ",";
	
	private final AdvertServiceClient advertServiceClient;
	private final MapaKriminalityClient mapaKriminalityServiceClient;

	public EnhancedAdvertsService(final AdvertServiceClient advertServiceClient, final MapaKriminalityClient mapaKriminalityServiceClient) throws IOException {
		if (advertServiceClient == null || mapaKriminalityServiceClient == null) {
			throw new NullPointerException();
		}
		
		this.advertServiceClient = advertServiceClient;
		this.mapaKriminalityServiceClient = mapaKriminalityServiceClient;
	}
	
	public EnhancedAdvertsService() throws IOException {
		this.advertServiceClient = new AdvertServiceClient();
		this.mapaKriminalityServiceClient = new MapaKriminalityClient();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<EnhancedAdvert> getAdverts(
				@QueryParam("id") final String id,
				@QueryParam("phone") final String phone,
				@QueryParam("email") final String email,
				@QueryParam("from") final String from,
				@QueryParam("to") final String to,
				@QueryParam("include") final String include,
				@QueryParam("exclude") final String exclude
			) {
		if (id != null) {
			final Advert advert = this.advertServiceClient.getAdvert(Integer.parseInt(id));
			
			return Arrays.asList(embelish(advert));
		}
		
		Set<Advert> adverts = null;
		if (phone != null) {
			adverts = new HashSet<>(this.advertServiceClient.searchPhone(phone));
		}
		
		if (email != null) {
			final List<Advert> subResult = this.advertServiceClient.searchEmail(email);
			
			if (adverts != null) {
				adverts.retainAll(subResult);
			} else {
				adverts = new HashSet<>(subResult);
			}
		}
		
		if (from != null && to != null) {
			final List<Advert> subResult = this.advertServiceClient.listPublished(from, to);
			
			if (adverts != null) {
				adverts.retainAll(subResult);
			} else {
				adverts = new HashSet<>(subResult);
			}
		}
		
		if (include != null && exclude != null) {
			final List<Advert> subResult = this.advertServiceClient.searchTags(include.split(LIST_DELIMITER), exclude.split(LIST_DELIMITER));
			
			if (adverts != null) {
				adverts.retainAll(subResult);
			} else {
				adverts = new HashSet<>(subResult);
			}
		}
		
		final List<EnhancedAdvert> result = embelish(adverts);
		return result;
	}

	private EnhancedAdvert embelish(Advert advert) {
		final double crimeRate = this.mapaKriminalityServiceClient.getBurglariesCrimeRate(advert.getLongitude(), advert.getLatitude());
		
		final EnhancedAdvert result = new EnhancedAdvert();
		
		result.setEmail(advert.getEmail());
		result.setPhone(advert.getPhone());
		result.setId(advert.getId());
		result.setLatitude(advert.getLatitude());
		result.setLongitude(advert.getLongitude());
		result.setPublished(advert.getPublished().toGregorianCalendar().getTime());
		result.setTags(new HashSet<>(advert.getTags()));
		result.setText(advert.getText());
		
		result.setCrimeRate(crimeRate);
		
		return result;
	}

	private List<EnhancedAdvert> embelish(Collection<Advert> adverts) {
		final List<EnhancedAdvert> result = new ArrayList<>(adverts.size());
		
		for (final Advert advert : adverts) {
			final EnhancedAdvert enhancedAdvert = embelish(advert);
			
			result.add(enhancedAdvert);
		}
		
		return result;
	}
}
