import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class EchoServer {
	@SuppressWarnings("resource")
	public void runEchoServer() {
		System.out.println("starting server...");
		try	{
			String query_waggle = "";
			String query_battery = "";
			
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
			query_battery = "select * from battery_specs b1 where b1.updated_time = (select max(updated_time) from battery_specs)";
			query_waggle = "select * from waggle_specs";
			
			
			/*
			ResultSet rs = stmt.executeQuery(query) ;
			
			String r = "";		
			while( rs.next() ) {
				for (int i = 1; i <= 11; i++) {
					r += rs.getString(i);
					if (i != 11) r += "/";
				}
			}

			rs.close() ;
			stmt.close() ;
			conn.close() ;
			*/
			
			ServerSocket s = new ServerSocket(8190); //the server socket
			boolean over = false;
			while(!over)	 {
				System.out.println("starting socket...");
				Socket incoming = s.accept(); //accept a connection from a client
				//System.out.println("socket connected...");
				try {
					InputStream inStream = incoming.getInputStream(); // the INPUT stream handler
					OutputStream outStream = incoming.getOutputStream(); // the OUTPUT stream handler
					Scanner in = new Scanner(inStream); //setup of input
					PrintWriter out = new PrintWriter(outStream,true); // sends output
					//boolean done = false;
					System.out.println("here before the loop in server...");
					//while there are lines to read, for this connection
					//while (!done && in.hasNextLine())
					String str = "";
					
					while (in.hasNextLine()) {
						//System.out.println("here in the server inner loop...");
						//System.out.println(in.hasNextLine());
						String lineIn = in.nextLine();
						if (lineIn == "battery") {
							ResultSet rs = stmt.executeQuery(query_battery);
							int i = 1;
							while(rs.next()) {
								str += rs.getString(i);
							}
						}
						else if (lineIn == "waggle") {
							ResultSet rs = stmt.executeQuery(query_waggle);
							int i = 1;
							while(rs.next()) {
								str += rs.getString(i);
								i++;
								if ( i == 6 ) {
									i = 1;
									str += "$"; // different record
								}
							}
						}
						
						//System.out.println("here in the server inner loop: " + lineIn);
						//out.println(lineIn + " loves everyone"); // change it to sql resultset !!!!!
						
						System.out.println(lineIn.trim());
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
	/*
	public static void main(String [] args) {
		EchoServer es = new EchoServer();
		es.runEchoServer();
	}
	*/
}


/*
Battery b = new Battery();
ResultSet rs = stmt.executeQuery(query_battery);
b.setWaggle_id((String) rs.getObject(1));
b.setBattery_status((int) rs.getObject(2));
b.setVoltage((float) rs.getObject(3));
b.setCharging((char) rs.getObject(4));
b.setTemperature((float) rs.getObject(5));
b.setHumidity((float) rs.getObject(6));
b.setHeater((char) rs.getObject(7));
b.setFan((char) rs.getObject(8));
b.setNotification((String) rs.getObject(9));
b.setNumber((int) rs.getObject(10));
b.setUpdated_time((String) rs.getObject(11));
*/
