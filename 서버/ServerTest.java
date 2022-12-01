import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerTest {

    public final static int PORT=80;

    public static String AI(String msg) throws IOException{
        ProcessBuilder pb= new ProcessBuilder("python","ANNPredictTestSpeedUp.py",msg);
        Process p =pb.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                p.getInputStream(),"utf-8"));
        String line="";
        String ans="";
        try{
            while((line= br.readLine())!=null){
                if(!(line.contains("13/13"))) {
                    ans=line;
                }
            }
        }finally {
            try{
                if(br!=null){
                    br.close();
                }
                return ans;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) {
        try(ServerSocket server=new ServerSocket(PORT)){
            while(true){
                try{
                    Socket connection=server.accept();
                    Thread task=new TaskThread(connection);
                    task.start();
                }catch (IOException ex){}
            }
        }catch(IOException ex){ }
    }

    private static class TaskThread extends Thread{
        private Socket connection;

        TaskThread(Socket connection){
            this.connection=connection;
        }

        public void run(){
            try{
                InputStreamReader in=new InputStreamReader(connection.getInputStream());
                BufferedReader bufReader=new BufferedReader(in);
                Writer out=new OutputStreamWriter(connection.getOutputStream());

                System.out.println("!!");

                String msg=bufReader.readLine();
//
//                msg="message is "+msg;

                String ans=AI(msg);
                out.write(ans);
                out.flush();
            }catch (IOException ex){}
            finally{
                try{
                    connection.close();
                }catch (IOException ex) {}
            }
        }
    }
}
