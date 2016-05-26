/**
 * 
 */
package cz.cuni.mff.ms.brodecva.ws.adverts.published;

import javax.jws.WebService;

import cz.cuni.mff.ms.brodecva.ws.adverts.model.Advert;
import cz.cuni.mff.ms.brodecva.ws.adverts.model.AdvertDao;

@WebService(portName="AdvertServicePort", serviceName = "AdvertService", endpointInterface = "cz.cuni.mff.ms.brodecva.ws.adverts.published.AdvertService")
public class AdvertServiceImpl implements AdvertService {

	private final AdvertDao dao;

	public AdvertServiceImpl(final AdvertDao dao) {
		if (dao == null) {
			throw new NullPointerException();
		}
		
		this.dao = dao;
	}
	
	public AdvertServiceImpl() {
		this(new AdvertDao());
	}

	/* (non-Javadoc)
	 * @see cz.cuni.mff.ms.brodecva.nswi145.adverts.published.AdvertService#getAdvert(long)
	 */
	@Override
	public Advert getAdvert(final long id) {
		return this.dao.loadAdvert(id);
	}

	/* (non-Javadoc)
	 * @see cz.cuni.mff.ms.brodecva.nswi145.adverts.published.AdvertService#searchPhone(java.lang.String)
	 */
	@Override
	public Advert[] searchPhone(final String phone) {
		return this.dao.filterByPhone(phone);
	}

	/* (non-Javadoc)
	 * @see cz.cuni.mff.ms.brodecva.nswi145.adverts.published.AdvertService#searchEmail(java.lang.String)
	 */
	@Override
	public Advert[] searchEmail(final String email) {
		return this.dao.filterByEmail(email);
	}

	/* (non-Javadoc)
	 * @see cz.cuni.mff.ms.brodecva.nswi145.adverts.published.AdvertService#searchTags(java.lang.String[], java.lang.String[])
	 */
	@Override
	public Advert[] searchTags(String[] include, String[] exclude) {
		return this.dao.filterByTags(include, exclude);
	}

	/* (non-Javadoc)
	 * @see cz.cuni.mff.ms.brodecva.nswi145.adverts.published.AdvertService#listPublished(java.lang.String, java.lang.String)
	 */
	@Override
	public Advert[] listPublished(String from, String to) {
		return this.dao.filterByFromAndTo(from, to);
	}
}
