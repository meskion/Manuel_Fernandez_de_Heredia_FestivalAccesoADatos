package gestorFestival;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tracks.Track;

public class FestivalHandler extends DefaultHandler {

	public List<Track> playlist;
	private Track currentTrack;
	private Method currentSetter;

	public FestivalHandler() {

		playlist = new ArrayList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equals("track")) {
			currentTrack = new Track();
		} else if (!qName.equals("festival2DAM")) {

			try {
				currentSetter = Class.forName("tracks.Track").getMethod("set" + qName, String.class);
			} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {

			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("track"))
			playlist.add(currentTrack);

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value = String.valueOf(ch, start, length);

		try {
			if (currentSetter != null && !value.isBlank()) {
				currentSetter.invoke(currentTrack, value);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
