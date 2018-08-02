package com.example.android.myapplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SocketTest {

    public ArrayList runClient (String str) { // str can be 1. waggle 2.battery 3. battery today? possibly?
        Scanner reader = new Scanner(System.in);
        ArrayList arr = new ArrayList();
        try {
            //Socket s = new Socket("time-A.timefreq.bldrdoc.gov",13);
            Socket s = new Socket("192.168.2.59", 8190);
            try {
                while (true) {
                    InputStream inStream = s.getInputStream();
                    OutputStream outStream = s.getOutputStream();
                    Scanner in = new Scanner(inStream);
                    PrintWriter out = new PrintWriter(outStream, true);

                    //System.out.print("Enter some text: ");
                    //String myLine = reader.nextLine();
                    //out.println(myLine); // NO writeln
                    //out.println("hyemin is cool 2");
                    //    out.flush();


                    out.println(str);

                    //System.out.println("Here before inner while...");

                    if (str == "battery") {
                        arr = new ArrayList<Battery>();
                        while(in.hasNextLine()) {

                            String line = in.nextLine();
                            StringTokenizer strTok = new StringTokenizer(line, "/");
                            Battery b = new Battery();
                            b.setWaggle_id(strTok.nextToken());
                            b.setBattery_status(Integer.parseInt(strTok.nextToken()));
                            b.setVoltage(Float.valueOf(strTok.nextToken()));
                            b.setCharging(strTok.nextToken().charAt(0));
                            b.setTemperature(Float.valueOf(strTok.nextToken()));
                            b.setHumidity(Float.valueOf(strTok.nextToken()));
                            b.setHeater(strTok.nextToken().charAt(0));
                            b.setFan(strTok.nextToken().charAt(0));
                            b.setNotification(strTok.nextToken());
                            b.setNumber(Integer.parseInt(strTok.nextToken()));
                            b.setUpdated_time(strTok.nextToken());
                            arr.add(b);

                        }

                    }
                    else if (str == "waggle") {
                        arr = new ArrayList<Waggle>();
                        while (in.hasNextLine()) {

                            String line = in.nextLine();
                            StringTokenizer strTok = new StringTokenizer(line, "/");
                            Waggle w = new Waggle();
                            w.setWaggle_id(strTok.nextToken());
                            w.setLongitude(strTok.nextToken());
                            w.setLatitude(strTok.nextToken());
                            w.setDate_created(strTok.nextToken());
                            w.setLocation(strTok.nextToken());
                            arr.add(w);

                        }
                    }

                    /*
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
                    */
                }
            }
            finally {
                s.close();
            }
        }
        catch(IOException ioexc){
            ioexc.printStackTrace();
        }
        return arr;
    }

    public static void main(String [] args){
        SocketTest st = new SocketTest();
        //st.runClient();
    }

}
