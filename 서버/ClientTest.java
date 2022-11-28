import java.io.*;
import java.net.*;

public class ClientTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

//      client 부분 테스트 용

        String hostname = "localhost";
        Socket socket = null;
        try {
            socket=new Socket(hostname,80);
            socket.setSoTimeout(600000);

//          서버에 보낼 값 쓰는 곳
            OutputStream out = socket.getOutputStream();
            PrintWriter printer=new PrintWriter(out,true);
            printer.println("100/60/60/30/0/0/0/0");

//          서버에서 받아오는 값 읽는 곳
            InputStream in =socket.getInputStream();
            InputStreamReader reader =
                    new InputStreamReader(in, "ASCII");

//          time이 받아온 값
            StringBuilder time = new StringBuilder();
            for(int c = reader.read();c!=-1;c=reader.read()) {
                time.append((char)c);
            }
            System.out.println(time);
        }catch(IOException ex) {
            System.err.println(ex);
        }finally {
            if(socket != null) {
                try {
                    socket.close();
                    return ;
                }catch(IOException ex) {

                }
            }
        }
    }

}
