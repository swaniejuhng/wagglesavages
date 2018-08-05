import java.io.*;
import java.net.*;
import java.util.*;

public class SocketTest {
	public void runClient () {
		Scanner reader = new Scanner(System.in);
		try{
			//Socket s = new Socket("time-A.timefreq.bldrdoc.gov",13);
			//Socket s = new Socket("192.168.2.59", 8190);
			Socket s = new Socket("127.0.0.1", 8190);
			try{
				while (true) {
					InputStream inStream = s.getInputStream();
					OutputStream outStream = s.getOutputStream();
					Scanner in = new Scanner(inStream);
					PrintWriter out = new PrintWriter(outStream, true);

					System.out.print("Enter some text: ");
					String myLine = reader.nextLine();
					out.println(myLine); // NO writeln
					//out.println("hyemin is cool 2");
					//    out.flush();

					//System.out.println("Here before inner while...");
					//String [] battery_spec = new String[11];
					while(in.hasNextLine()) {
						String line = in.nextLine();
						StringTokenizer strTok = new StringTokenizer(line, "/");
						int i = 0;
						while(strTok.hasMoreTokens()) {
							i++;
						}
						
						//System.out.println("The echo is:  " + line);
						
						//byte [] b = line.getBytes();
					}
				}
			}
			finally {
				s.close();
			}
		}
		catch(IOException ioexc){
			ioexc.printStackTrace();
		}
	}

	public static void main(String [] args){
		SocketTest st = new SocketTest();
		st.runClient();
	}
}