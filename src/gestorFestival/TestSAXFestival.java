package gestorFestival;

import java.io.File;

public class TestSAXFestival {

	public static void main(String[] args) {

		File f = new File("data/festival2DAM.xml");
		GestorFestival gestor = new GestorFestival();
		gestor.abrirXML(f);
		gestor.recorrerSAX().forEach(System.out::println);

	}

}
