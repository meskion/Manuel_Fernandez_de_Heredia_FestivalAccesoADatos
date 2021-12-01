package tracks;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Track {

	private String trackURI, trackName, artistURIs, artistNames, albumURI, albumName, albumArtistURIs, albumArtistNames, albumReleaseDate, albumImageURL,
			discNumber, trackNumber, trackDuration, trackPreviewURL, explicit, popularity, addedBy, addedAt;

	

	public String getTrackURI() {
		return trackURI;
	}

	public void setTrackURI(String trackURI) {
		this.trackURI = trackURI;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getArtistURIs() {
		return artistURIs;
	}

	public void setArtistURIs(String artistURIs) {
		this.artistURIs = artistURIs;
	}

	public String getArtistNames() {
		return artistNames;
	}

	public void setArtistNames(String artistNames) {
		this.artistNames = artistNames;
	}

	public String getAlbumURI() {
		return albumURI;
	}

	public void setAlbumURI(String albumURI) {
		this.albumURI = albumURI;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	
	public String getAlbumArtistURIs() {
		return albumArtistURIs;
	}

	public void setAlbumArtistURIs(String albumArtistURIs) {
		this.albumArtistURIs = albumArtistURIs;
	}

	public String getAlbumArtistNames() {
		return albumArtistNames;
	}

	public void setAlbumArtistNames(String albumArtistNames) {
		this.albumArtistNames = albumArtistNames;
	}

	public String getAlbumReleaseDate() {
		return albumReleaseDate;
	}

	public void setAlbumReleaseDate(String albumReleaseDate) {
		this.albumReleaseDate = albumReleaseDate;
	}

	public String getAlbumImageURL() {
		return albumImageURL;
	}

	public void setAlbumImageURL(String albumImageURL) {
		this.albumImageURL = albumImageURL;
	}

	public String getDiscNumber() {
		return discNumber;
	}

	public void setDiscNumber(String discNumber) {
		this.discNumber = discNumber;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getTrackDuration() {
		return trackDuration;
	}

	public void setTrackDuration(String trackDuration) {
		this.trackDuration = trackDuration;
	}

	public String getTrackPreviewURL() {
		return trackPreviewURL;
	}

	public void setTrackPreviewURL(String trackPreviewURL) {
		this.trackPreviewURL = trackPreviewURL;
	}

	public String getExplicit() {
		return explicit;
	}

	public void setExplicit(String explicit) {
		this.explicit = explicit;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(String addedAt) {
		this.addedAt = addedAt;
	}

	

	@Override
	public String toString() {
		
		return List.of(this.getClass().getDeclaredMethods())
		.stream()
		.filter(m -> m.getName().startsWith("get"))
		.map(m -> {
			try {
				return m.getName().substring(3) + ": "+ m.invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return "";
		})
		.reduce((a,s) -> a + "\n" + s).get() + "\n";
		
		
//		return "Track [trackName=" + trackName + ", artistNames=" + artistNames + "]";
	}
	
	

	
}
