import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;
public class sock {
  public static String Scan(String Host)throws IOException{  
  System.out.println(Host);
try{
  Socket s = new Socket();
  SocketAddress socketAddress = new InetSocketAddress(Host, 80); 
  s.connect(socketAddress,2000);

  OutputStreamWriter dout=new OutputStreamWriter(s.getOutputStream()); 
  s.setSoTimeout(2000);
  dout.write("GET / HTTP/1.1\r\nHost: "+Host+"\r\n\r\n");
  dout.flush();  
  InputStreamReader dis = new InputStreamReader(s.getInputStream());
  BufferedReader bf = new BufferedReader(dis);
  String str; 
  ArrayList<String> dict = new ArrayList<String>();
 while ((str = bf.readLine()) != null){
 dict.add(str);
}
String valid ="";
for (int n=0; n<dict.size();n++){
 String m = new String(String.valueOf(dict.get(n)));
int ch = m.compareTo("Server: cloudflare");
if (ch ==0)
{
System.out.println(dict.get(n));
return Host;
}}
}catch (Exception e){
System.out.println(e);
}
return null;
}
//dout.close();
public static void Save(ArrayList openedx ) {
try{
Formatter f = new Formatter("openedip.txt"); 
for ( int i= 0; i < openedx.size();i++){
 String ip= String.valueOf(openedx.get(i)); 
f.format("%s %s",i,ip+"\r\n");
}
f.close();
  }catch (Exception e){
  System.out.println(e);
}
}
  public static void main(String[] args)throws IOException{
 try {
  Scanner sc = new Scanner(System.in);
  System.out.println("enter starting ip: ");
  String ip = sc.nextLine();
  String[] lstf = ip.split("\\.");
//  Integer frst =Integer.parseInt(lstf[3]);
ArrayList<String> opened= new ArrayList<String>();

for (int i=1;i < 255;i++){
  String y = String.valueOf(i);
  String host= lstf[0]+"."+lstf[1]+"."+lstf[2]+"."+ y;
  String abc = Scan(host);
 
if (abc == host){ 
  System.out.println("opened !");
   opened.add(host);
   Save(opened);
}
sc.close();
}
} catch (Exception e) {
  System.out.println(e);
}
  }
 }


