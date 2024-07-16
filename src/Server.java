import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        try(ServerSocket socket=new ServerSocket(8080)){
            System.out.println("正在连接客户端");
            Socket wuhu=socket.accept();
            System.out.println("已连接客户端！IP地址为"+wuhu.getInetAddress().getHostAddress());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
