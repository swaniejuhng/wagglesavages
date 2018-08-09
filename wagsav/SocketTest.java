import java.io.*;
import java.net.*;
import java.util.*;

public class SocketTest {
	public String runClient (String str) {
		String result = "";
		//while(true) {
			Scanner reader = new Scanner(str);
			try {
				//Socket s = new Socket("time-A.timefreq.bldrdoc.gov",13);
				Socket s = new Socket("192.168.2.59", 8190);
				//Socket s = new Socket("127.0.0.1", 8190);
				try {
					//while (true) {
						InputStream inStream = s.getInputStream();
						OutputStream outStream = s.getOutputStream();
						Scanner in = new Scanner(inStream);
						PrintWriter out = new PrintWriter(outStream, true);

						//System.out.print("Enter some text: ");
						String myLine = reader.nextLine();
						System.out.println("myLine : " + myLine);
						out.println(myLine); // NO writeln

						//while(in.hasNextLine()) {
							result = in.nextLine();
							//System.out.println("result : " + result);
						//}

						/*
						while(in.hasNextLine()) {
							String line = in.nextLine();
							System.out.println("line : " + line);
						}
						*/
					}
				//}

				finally {
					s.close();
				}
			}
			catch(IOException ioexc){
				ioexc.printStackTrace();
			}
			return result;
		//}
	}

	/*
	public static void main(String [] args){
		SocketTest st = new SocketTest();
		st.runClient();
	}
	*/
}
