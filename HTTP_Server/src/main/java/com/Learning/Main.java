package com.Learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private  static  final String HTTP_NEW_LINE_SEPRATOR="\r\n";
    private  static final String HTTP_BODY_SEPRATER= "\r\n"+"\r\n"+"\r\n";
    private  static final int DEFAULT_BUFFER_SIZE=10000;
    public static void main(String[] args) throws Exception {
       System.out.println("server starting..");
       ServerSocket serverSocket=new ServerSocket(8080);
       while(true){
           Socket connection =serverSocket.accept();
           //this accept function wait for client to connect till it holds the loop

           var request=readRequest(connection);
           System.out.println( connection);
            if(request.isEmpty()){
                continue;
            }

           printRequest(request.get());



           try(var os=connection.getOutputStream()){
                var Data= """
                        {
                            "id":101,
                            "Intro":"Hello this is simple http server"

                        }
                        
                        """;
                var respo= """
                        HTTP/1.1 200 OK
                        Content-Type: application/json
                        Content-Length: %d
                        
                        %s
                        """.formatted(Data.getBytes(StandardCharsets.UTF_8).length,Data);
                os.write(respo.getBytes(StandardCharsets.UTF_8));
           }
       }

    }

        //this is main fucntion to read all the things that are in frontend to backend http response
        public static Optional<HttpReq> readRequest(Socket connection) throws Exception {
            InputStreamReader reader=new InputStreamReader(connection.getInputStream());

            BufferedReader r =new BufferedReader(reader);
            var line=r.readLine();
            bodyreader(connection.getInputStream());
            System.out.println("Client connected with  "+line);
                var methodUrl =line.split(" ");
                var method=methodUrl[0];
                var url=methodUrl[1];
                var header=readheader(r);
            return Optional.of(new HttpReq(method, url, header, null));
        }

        //this function is just to print the usefull info ..important that we get so much header that i we dont understand so  we ignore
        private  static  void printRequest(HttpReq req){
            System.out.println("Method--"+req.Method);
            System.out.println("Url--"+req.url);
            System.out.println("Header");
            for (Map.Entry<String, List<String>> entry : req.headers.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                System.out.println("%s -- %s".formatted(key, value));
            }
        }



        //this function is just to map header
        private static Map<String,List<String>> readheader(BufferedReader reader) throws Exception{
        var line=reader.readLine();
        var headers=new HashMap<String,List<String>>();
        while(line!=null && !line.isEmpty()){
            var keyvalue=line.split(":",2);
            var key=keyvalue[0];
            var value=keyvalue[1];
            headers.computeIfAbsent(key,k-> new ArrayList<>()).add(value);
            //this make a list and insert into hashmap
            line=reader.readLine();
        }
        return headers;
    }



        //now this function will handel the body of http reuqest from frontend or backend or you can say API to backend
        private  static byte[] bodyreader(InputStream stream) throws Exception{
            var toRead=stream.available();
            if(toRead==0){
                toRead=DEFAULT_BUFFER_SIZE;
            }
            var buffer=new byte[toRead];
            var read=stream.read(buffer);
            //The main thing to learn here is that in real life, a user may have a slow network. So when the user clicks a button to send data
            // (like a form or API request), the full request body might not arrive all at once.
            /// TCP is a protocol that ensures data is safely and correctly sent between two devices — like your laptop and an API server — over a network.
            // TCP send data in chunck so data wont hack and loose

            if(read<=0){
                return new byte[0];
            }

            return read==toRead? buffer:Arrays.copyOf(buffer,read);
        }




        //this is just model for making a object ro return to main file
       private record HttpReq(String Method,
                                 String url,
                                 Map<String, List<String>> headers,
                                 byte[] body){


       }
}
