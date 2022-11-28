import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerTest {

    public final static int PORT=80;

    public static String AI(String msg) throws IOException{
//      AI 예측하는 파이썬 파일 실행하는 곳
        
//      파이썬 파일 실행  
        ProcessBuilder pb= new ProcessBuilder("python","ANNPredict.py",msg);
        Process p =pb.start();
        
//      출력되는 값들 읽어오는 것
        BufferedReader br = new BufferedReader(new InputStreamReader(
                p.getInputStream(),"utf-8"));
        String line="";
        String ans="";
        try{
            while((line= br.readLine())!=null){
//              AI 예측 부분 출력에서 쓸데 없는 것 제거하는 것
                if(!(line.contains("1/1"))) {
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
//      서버에서 스레드 만들어서 돌리는 곳 
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
//      서버 돌아가는 스레드
        private Socket connection;

        TaskThread(Socket connection){
            this.connection=connection;
        }

        public void run(){
            try{
//              필요한 Stream 선언
                InputStreamReader in=new InputStreamReader(connection.getInputStream());
                BufferedReader bufReader=new BufferedReader(in);
                Writer out=new OutputStreamWriter(connection.getOutputStream());

//              통신 잘 연결되면 출력하는 것. 
                System.out.println("!!");

//              client에서 보낸 값 받는 것
                String msg=bufReader.readLine();
//
//              msg="message is "+msg;

//              예측해서 나온 값 ans에 저장
                String ans=AI(msg);
                
//              ans를 client에게 전달
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
