package cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.clients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.model.Area;
import cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.model.Areas;
import cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.model.Crimes;

public class MapaKriminalityClient {

	public static final int NO_RESULT = -1;
	
	private static final String JOINT_BURGLARIES_CRIME_TYPES = "371";//"371,372,373,311-390"
	
	private final WebResource service;
	private final ObjectMapper mapper;
	
	private Map<String, Double> areaRateCache = new HashMap<>();
	
	public MapaKriminalityClient(final WebResource service, final ObjectMapper mapper) {
		if (service == null || mapper == null) {
			throw new NullPointerException();
		}
		
		this.service = service;
		this.mapper = mapper;
	}
	
	public MapaKriminalityClient() {
		final ClientConfig config = new DefaultClientConfig();
		final Client client = Client.create(config);
		this.service = client.resource(UriBuilder.fromUri(
				"http://mapakriminality.cz/api").build());
		
		this.mapper = new ObjectMapper();
	}

	public double getBurglariesCrimeRate(double longitude, double latitude) {
		String areaCode;
		try {
			areaCode = getAreaCode(longitude, latitude);
		} catch (final Exception e) {
			e.printStackTrace();
			
			return NO_RESULT;
		}
				
		return getCrimeRate(areaCode);
	}

	private double getCrimeRate(String areaCode) {
		final Double cachedRate = this.areaRateCache.get(areaCode);
		if (cachedRate != null) {
			return cachedRate;
		}
		
		final WebResource crimeRateResource = service.path("crimes").queryParam("areacode", areaCode).queryParam("crimetypes", JOINT_BURGLARIES_CRIME_TYPES).queryParam("groupby", "area");
		final Builder crimeRateBuilder = crimeRateResource.accept(MediaType.APPLICATION_JSON);
		final String crimesContent = crimeRateBuilder.get(String.class);

		final Crimes crimes;
		try {
			crimes = mapper.readValue(crimesContent, Crimes.class);
		} catch (final IOException e) {
			e.printStackTrace();
			
			return NO_RESULT;
		}
		
		if (crimes.getCrimes().isEmpty()) {
			return NO_RESULT;
		}
		
		final double result = crimes.getCrimes().iterator().next().getCrimeRate();
		this.areaRateCache.put(areaCode, cachedRate);
		return result;
	}

	private String getAreaCode(double longitude, double latitude) throws IOException {
		final WebResource areaCodeResource = service.path("areas").queryParam("lat", String.valueOf(latitude)).queryParam("lng", String.valueOf(longitude));
		final Builder areaCodeBuilder = areaCodeResource.accept(MediaType.APPLICATION_JSON);
		final String areasContent = areaCodeBuilder.get(String.class);

		final Areas areas;
		try {
			areas = mapper.readValue(areasContent, Areas.class);
		} catch (final IOException e) {
			e.printStackTrace();
			
			throw e;
		}
		
		if (areas.getArea().isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		final List<Area> areaCopy = new ArrayList<>(areas.getArea());
		Collections.sort(areaCopy, new Comparator<Area>() {

			@Override
			public int compare(final Area first, final Area second) {
				return -Integer.compare(first.getAreaLevel(), second.getAreaLevel());
			}
			
		});
		
		return areaCopy.iterator().next().getCode();
	}

}
