import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class EchoServer2 {

	@SuppressWarnings("resource")
	public void runEchoServer() {
		System.out.println("starting server...");
		try {

			// Load the database driver
			Class.forName("com.mysql.jdbc.Driver") ;

			// Get a connection to the database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/waggle?user=root&password=abc1234"
					+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC") ;           

			// Print all warnings
			for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() ){
				System.out.println( "SQL Warning:" ) ;
				System.out.println( "State  : " + warn.getSQLState()  ) ;
				System.out.println( "Message: " + warn.getMessage()   ) ;
				System.out.println( "Error  : " + warn.getErrorCode() ) ;
			}

			// Get a statement from the connection
			Statement stmt = conn.createStatement() ;

			// Execute the query
			String query_battery = "select * from battery_specs b1 "
					+ "where b1.updated_time = (select max(updated_time) from battery_specs)";
			String query_waggle = "select * from waggle_specs";
			String query_battery_today = "select * from battery_specs where date(updated_time) = curdate()";

			/*
            ResultSet rs = stmt.executeQuery(query) ;

            rs.close() ;
            stmt.close() ;
            conn.close() ;
			 */

			ServerSocket s = new ServerSocket(8190); //the server socket
			boolean over = false;
			while(!over) {
				System.out.println("starting socket...");
				Socket incoming = s.accept(); //accept a connection from a client
				//System.out.println("socket connected...");
				try {
					InputStream inStream = incoming.getInputStream(); // the INPUT stream handler
					OutputStream outStream = incoming.getOutputStream(); // the OUTPUT stream handler
					Scanner in = new Scanner(inStream); //setup of input
					PrintWriter out = new PrintWriter(outStream,true); // sends output
					boolean done = false;
					System.out.println("here before the loop in server...");
					//while there are lines to read, for this connection
					//while (!done && in.hasNextLine())
					String str = "";
					ResultSet rs = null;
					//System.out.println(in.hasNextLine());
					while (!done && in.hasNextLine()) {

						String lineIn = in.nextLine().trim();
						System.out.println(lineIn);

						if (lineIn.equals("waggle")) {
							System.out.println("input : waggle"); // all waggles
							rs = stmt.executeQuery(query_waggle);
							while (rs.next()) {
								for (int i = 1 ; i <= 5 ; i++ ) {
									str += rs.getString(i);
									if (i != 5) str += "/";
								}
								str += "$";
							}
						}

						else if (lineIn.equals("battery")){ // retrieves the latest battery speculation
							System.out.println("input : battery");
							rs = stmt.executeQuery(query_battery);
							while (rs.next()) {
								for (int i = 1 ; i <= 11 ; i++ ) {
									str += rs.getString(i);
									if (i != 11) str += "/";
								}
							}
						}

						else if (lineIn.equals("battery_today")){ // today's data
							System.out.println("its battery_today");
							rs = stmt.executeQuery(query_battery_today);
							while(rs.next()) {
								for (int i = 1 ; i <= 11 ; i++ ) {
									str += rs.getString(i);
									if (i != 11) str += "/";
								}
								str += "$";
							}
						}

						out.println(str);

						//to kill the server, enter "BYE" from the client
						// (18.08.01) probably ? no need. it has to run all the time
						/*
                        if (lineIn.trim().equals("BYE")) {
                            done = true;
                        }
						 */
					}
				}
				catch(Exception exc1) {
					exc1.printStackTrace();
				}
			}
		}
		catch(Exception exc2) {
			exc2.printStackTrace();
		}
	}
	public static void main(String [] args) {
		EchoServer2 es = new EchoServer2();
		es.runEchoServer();
	}
}
