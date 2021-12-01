package gestorBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FestivalConnection {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/festival?autoReconnect=true&useSSL=false";
//	private BasicDataSource bdSource;
	private static Connection con;

	public static Connection getInstance() {
		if (con == null) {
//		bdSource = new BasicDataSource();
//		bdSource.setUrl(JDBC_URL);
//		bdSource.setUsername("root");
//		bdSource.setPassword("1234");
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "1234");

			try {

//			con = bdSource.getConnection();
				con = DriverManager.getConnection(JDBC_URL, props);

				System.out.println("Conexión creada satisfactoriamente");

			} catch (Exception e) {
				System.out.println("Error: " + e.toString());
			}
		}

		return con;
	}

	public static void close() {
		try {
//			bdSource.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
