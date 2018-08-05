import java.io.*;
import java.net.*;
import java.util.*;

public class SocketTest2
{
	public static void main(String [] args)
	{
		Scanner reader = new Scanner(System.in);

		try
		{
			//Socket s = new Socket("time-A.timefreq.bldrdoc.gov",13);
			Socket s = new Socket("127.0.0.1", 8190);
			try
			{
				InputStream inStream = s.getInputStream();
				OutputStream outStream = s.getOutputStream();
				Scanner in = new Scanner(inStream);
				PrintWriter out = new PrintWriter(outStream, true);

				System.out.print("Enter some text: ");
				String myLine = reader.nextLine();
				out.println(myLine);

				while(in.hasNextLine())
				{
					String line = in.nextLine();
					System.out.println("The echo is:  " + line);

				}
			}
			finally
			{
				s.close();
			}
		}
		catch(IOException ioexc)
		{
			ioexc.printStackTrace();
		}
	} //end public
} //end class
