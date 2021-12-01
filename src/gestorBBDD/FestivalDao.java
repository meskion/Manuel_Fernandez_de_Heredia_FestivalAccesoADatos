package gestorBBDD;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tracks.Track;

/**
 * Clase que controla el acceso a la base de datos usando JDBC DAO = Data Access
 * Object
 * 
 * @author manuf
 *
 */
public class FestivalDao {
	/** Conexion a la base de datos */
	Connection con;
	List<Method> trackMethods;
	final List<String> fieldStrings = List.of( "TrackURI", "TrackName", "ArtistURIs", "ArtistNames", "AlbumURI", "AlbumName",
			"AlbumArtistURIs", "AlbumArtistNames", "AlbumReleaseDate", "AlbumImageURL", "DiscNumber", "TrackNumber",
			"TrackDuration", "TrackPreviewURL", "Explicit", "Popularity", "AddedBy", "AddedAt");

	public FestivalDao() {

		con = FestivalConnection.getInstance();
		try {
			trackMethods = List.of(Class.forName("tracks.Track").getDeclaredMethods());
		} catch (SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		FestivalConnection.close();
		System.out.println("Conexión con la base de datos cerrada correctamente");
	}

	/**
	 * añade una track a la base de datos.
	 * 
	 * @param t
	 */
	public void insert(Track t) {
		try {
			// Crea un statement

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO tracks VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			trackMethods.stream().filter(m -> m.getName().startsWith("get")).forEach(m -> {
				try {
					ps.setString(fieldStrings.indexOf(m.getName().substring(3))+1, (String) m.invoke(t));
				} catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.out.println("ERROR: al hacer la inserción");
					System.err.println(t.getTrackName());
					e.printStackTrace();
				}
			});
//			ps.setString(1, t.getTrackURI());
//			ps.setString(2, t.getTrackName());
//			ps.setString(3, t.getArtistURIs());
//			ps.setString(4, t.getArtistNames());
//			ps.setString(5, t.getAlbumURI());
//			ps.setString(6, t.getAlbumName());
//			ps.setString(7, t.getAlbumArtistURIs());
//			ps.setString(8, t.getAlbumArtistNames());
//			ps.setString(9, t.getAlbumReleaseDate());
//			ps.setString(10, t.getAlbumImageURL());
//			ps.setString(11, t.getDiscNumber());
//			ps.setString(12, t.getTrackNumber());
//			ps.setString(13, t.getTrackDuration());
//			ps.setString(14, t.getTrackPreviewURL());
//			ps.setString(15, t.getExplicit());
//			ps.setString(16, t.getPopularity());
//			ps.setString(17, t.getAddedBy());
//			ps.setString(18, t.getAddedAt());

			// Ejecuta la inserción
			ps.executeUpdate();

			// Cierra el statement
			ps.close();
			System.out.println("Inserción realizada correctamente");
		} catch (SQLException ex) {
			System.out.println("ERROR: al hacer la inserción");
			System.err.println(t.getTrackName());
			ex.printStackTrace();
		}
	}

	public void delete(Track t) {
		try {
			Statement sta = con.createStatement();
			sta.executeUpdate("DELETE from tracks where trackURI=" + t.getTrackURI());
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void update(Track t) {
		delete(t);
		insert(t);
	}

	public List<Track> searchAll() {

		Statement sta;
		List<Track> result = null;
		try {
			sta = con.createStatement();
			ResultSet rs = sta.executeQuery("SELECT * from tracks");

			result = new ArrayList<>();

			List<Method> setters = trackMethods.stream().filter(m -> m.getName().startsWith("set"))
					.collect(Collectors.toList());

			while (rs.next()) {
				Track t = new Track();
				setters.forEach(s -> {

					try {
						s.invoke(t, rs.getString(s.getName().substring(3)));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| SQLException e) {

						e.printStackTrace();
					}

				});
				result.add(t);
			}

			rs.close();
		} catch (SQLException e2) {

			e2.printStackTrace();
		}

		return result;
	}
}
