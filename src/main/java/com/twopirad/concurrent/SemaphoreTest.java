package com.twopirad.concurrent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Semaphore;

/**
 * Created by diptam on 5/8/2017.
 */
public class SemaphoreTest {

    // max 4 people
    static Semaphore semaphore = new Semaphore(4);

    public static void main(String[] args) {

        System.out.println("Total available Semaphore permits : "
                + semaphore.availablePermits());

        MyATMThread t1 = new MyATMThread("A");
        t1.start();

        MyATMThread t2 = new MyATMThread("B");
        t2.start();

        MyATMThread t3 = new MyATMThread("C");
        t3.start();

        MyATMThread t4 = new MyATMThread("D");
        t4.start();

        MyATMThread t5 = new MyATMThread("E");
        t5.start();

        MyATMThread t6 = new MyATMThread("F");
        t6.start();

    }

    static class MyATMThread extends Thread {

        String name = "";

        MyATMThread(String name) {
            this.name = name;
        }

        public static String getStatus(String url) throws IOException {

            String result = "";
            try {
                URL siteURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) siteURL
                        .openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int code = connection.getResponseCode();
                if (code == 200) {
                    result = "Green";
                }
            } catch (Exception e) {
                result = "->Red<-";
            }
            return result;
        }

        public void run() {
            int length = 05;

            try {


                System.out.println(name + " : acquiring lock...");
                System.out.println(name + " : available Semaphore permits now: "
                        + semaphore.availablePermits());

                semaphore.acquire();
                System.out.println(name + " : got the permit!");

                try {

                    for (int j = 1; ; j++) {
                        String[] hostList = {"https://crunchify.com", "http://yahoo.com",
                                "http://www.ebay.com", "http://google.com", "https://paypal.com",
                                "http://bing.com/", "http://techcrunch.com/",
                                "http://mashable.com/", "http://thenextweb.com/",
                                "http://wordpress.com/", "http://wordpress.org/",
                                "http://ebay.co.uk/", "http://google.co.uk/",
                                "http://www.wikipedia.org/"};

                        for (int i = 0; i < hostList.length; i++) {

                            String url = String.format("%1$" + length + "s", hostList[i]);
                            String status = getStatus(url);

                            System.out.println(url + "\t\t\t\tStatus:" + status);

                        }

                    }



                        /*String httpsURL = "https://www.google.com";
                        URL myurl = new URL(httpsURL);
                        HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
                        InputStream ins = con.getInputStream();
                        InputStreamReader isr = new InputStreamReader(ins);
                        BufferedReader in = new BufferedReader(isr);

                        String inputLine;

                        while ((inputLine = in.readLine()) != null)
                        {
                            System.out.println(inputLine);
                        }

                        in.close();*/

                    // System.out.println(name + " : is performing operation " + i
                    //        + ", available Semaphore permits : "
                    //      + semaphore.availablePermits());

                    // sleep 1 second
                    //Thread.sleep(1000);

                    //}

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    // calling release() after a successful acquire()
                    System.out.println(name + " : releasing lock...");
                    semaphore.release();
                    System.out.println(name + " : available Semaphore permits now: "
                            + semaphore.availablePermits());

                }

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }
}
