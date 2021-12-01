package app;

import java.io.File;
import java.util.List;

import gestorBBDD.FestivalDao;
import gestorFestival.GestorFestival;
import tracks.Track;

public class MainTest {
	
	public static void main(String... args) {
		
		File f = new File("data/festival2DAM.xml");
		GestorFestival gestor = new GestorFestival();
		FestivalDao dao = new FestivalDao();
		gestor.abrirXML(f);
		List<Track> tracks = gestor.recorrerSAX();
		tracks.forEach(t -> dao.insert(t));
		dao.searchAll().forEach(System.out::println);
		
	}

}
