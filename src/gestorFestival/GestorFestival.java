package gestorFestival;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import tracks.Track;

public class GestorFestival {

	FestivalHandler fh;
	SAXParser parser;
	File ficheroXML;
	
	public int abrirXML(File fichero) {
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();

			parser = factory.newSAXParser();

			fh = new FestivalHandler();

			ficheroXML = fichero;
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public List<Track> recorrerSAX() {
		
			try {
				parser.parse(ficheroXML, fh);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return fh.playlist;
		
	}
	
}
