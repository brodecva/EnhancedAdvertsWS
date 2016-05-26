package cz.cuni.mff.ms.brodecva.ws.adverts.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class AdvertDao {

	private static final String CONFIG_PROPERTIES_FILE_NAME = "config.properties";
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private static final String ADVERT_QUERY = "SELECT email, id, latitude, longitude, phone, published, region, type, kind, text "
			+ "FROM adverts "
			+ "WHERE id = ? "
			+ "LIMIT 1";

	private static final String PHONE_QUERY = "SELECT email, id, latitude, longitude, phone, published, region, type, kind, text "
			+ "FROM adverts "
			+ "WHERE phone = ? "
			+ "LIMIT 100";

	private static final String EMAIL_QUERY = "SELECT email, id, latitude, longitude, phone, published, region, type, kind, text "
			+ "FROM adverts "
			+ "WHERE email = ? "
			+ "LIMIT 100";

	private static final String TAGS_QUERY = "SELECT email, id, latitude, longitude, phone, published, region, type, kind, text "
			+ "FROM adverts "
			+ "WHERE (region = ANY (?) OR type = ANY (?) OR kind = ANY (?)) "
			+ "AND region != ALL(?) AND type != ALL(?) AND kind != ALL(?) "
			+ "LIMIT 100";

	private static final String PUBLISHED_QUERY = "SELECT email, id, latitude, longitude, phone, published, region, type, kind, text "
			+ "FROM adverts "
			+ "WHERE published BETWEEN ? AND ? "
			+ "ORDER BY published "
			+ "LIMIT 100";

	private static final int ARRAY_LIMIT = 20;
	
	private final Connection connection;

	
	public AdvertDao(final Connection connection) {
		if (connection == null) {
			throw new NullPointerException();
		}
		
		this.connection = connection;
	}
	
	public AdvertDao() {
		try {
			final Properties props = new Properties();
	        props.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES_FILE_NAME));
		    
		    final String driverClassname = props.getProperty("driverClassname");
		    final String dataSourceUrl = props.getProperty("dataSourceUrl");
		    final String dataSourceLogin = props.getProperty("dataSourceLogin");
		    final String dataSourcePassword = props.getProperty("dataSourcePassword");
			
		    Class.forName(driverClassname);
			
			this.connection = DriverManager.getConnection(
					dataSourceUrl, dataSourceLogin,
					dataSourcePassword);
		} catch (final IOException | SQLException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	public Advert loadAdvert(final long id) {
		try (final PreparedStatement statement = connection.prepareStatement(ADVERT_QUERY)) {
			setAdvertQueryId(statement, id);
			
			final ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            return extractAdvert(rs);
	        } else {
	        	throw new IllegalArgumentException("No such advert: " + id);
	        }
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	private void setAdvertQueryId(final PreparedStatement statement, long id)
			throws SQLException {
		statement.setLong(1, id);
	}

	private Advert extractAdvert(final ResultSet rs) throws SQLException {
		final Advert advert = new Advert();
		
		advert.setEmail(rs.getString("email"));
		advert.setId(rs.getLong("id"));
		advert.setLatitude(rs.getDouble("latitude"));
		advert.setLongitude(rs.getDouble("longitude"));
		advert.setPhone(rs.getString("phone"));
		advert.setPublished(rs.getDate("published"));
		advert.setTags(new HashSet<>(Arrays.asList(rs.getString("region"), rs.getString("type"), rs.getString("kind"))));
		advert.setText(rs.getString("text"));
		
		return advert;
	}
	
	public Advert[] filterByPhone(final String phone) {
		try (final PreparedStatement statement = connection.prepareStatement(PHONE_QUERY)) {
			setPhoneQueryPhone(statement, phone);
			
			return extractAdverts(statement);
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	private void setPhoneQueryPhone(PreparedStatement statement, String phone) throws SQLException {
		statement.setString(1, phone);
	}

	private Advert[] extractAdverts(final PreparedStatement statement)
			throws SQLException {
		final ResultSet rs = statement.executeQuery();
		final List<Advert> result = new LinkedList<>();
		while (rs.next()) {
			result.add(extractAdvert(rs));
		}
		
		final Advert[] resultArray = result.toArray(new Advert[result.size()]);
		return resultArray;
	}
	
	public Advert[] filterByEmail(final String email) {
		try (final PreparedStatement statement = connection.prepareStatement(EMAIL_QUERY)) {
			setEmailQueryEmail(statement, email);
			
			return extractAdverts(statement);
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	private void setEmailQueryEmail(PreparedStatement statement, String email) throws SQLException {
		statement.setString(1, email);
	}
	
	public Advert[] filterByTags(String[] include, String[] exclude) {
		try (final PreparedStatement statement = connection.prepareStatement(TAGS_QUERY)) {
			setTagsQueryIncludeAndExclude(statement, include, exclude);
			
			return extractAdverts(statement);
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	private void setTagsQueryIncludeAndExclude(PreparedStatement statement,
			String[] include, String[] exclude) throws SQLException {
		if (include.length >= ARRAY_LIMIT || exclude.length >= ARRAY_LIMIT) {
			throw new IllegalArgumentException("Provided array to large.");
		}
		
		final java.sql.Array includeArray = this.connection.createArrayOf("text", include);
		final java.sql.Array excludeArray = this.connection.createArrayOf("text", exclude);
		
		statement.setArray(1, includeArray);
		statement.setArray(2, includeArray);
		statement.setArray(3, includeArray);
		statement.setArray(4, excludeArray);
		statement.setArray(5, excludeArray);
		statement.setArray(6, excludeArray);
	}
	
	public Advert[] filterByFromAndTo(String from, String to) {
		try (final PreparedStatement statement = connection.prepareStatement(PUBLISHED_QUERY)) {
			setPublishedQueryFromAndTo(statement, from, to);
			
			return extractAdverts(statement);
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	private void setPublishedQueryFromAndTo(PreparedStatement statement, String from, String to) throws SQLException {
		try {
			final Date fromDate = DATE_FORMAT.parse(from);
			final Date toDate = DATE_FORMAT.parse(to);
			
			statement.setTimestamp(1, new Timestamp(fromDate.getTime()));
			statement.setTimestamp(2, new Timestamp(toDate.getTime()));
		} catch (final ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
