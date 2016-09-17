package concurrent;

import java.io.*;
import java.net.Socket;
 
/**
 * 客户端
 */
public class Client {
	final static String SERVER_IP = "127.0.0.1";
	final static int PORT = 3333;
    public static void main(String[] args) {
 
        try {
            //1、创建客户端Socket，指定服务器端口号和地址
            Socket socket = new Socket(SERVER_IP,PORT);
            //2、获取输出流,向服务器发送信息
            OutputStream os = socket.getOutputStream(); //字节输出流
            PrintWriter pw  = new PrintWriter(os); //将输出流包装为打印流
            pw.write("aaabbbccc");//要发送给服务器端的字符串
            pw.flush();
            socket.shutdownOutput(); //关闭输出流

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
 
            String info = null;
            //循环读取
            while ((info = br.readLine()) != null){
                System.out.println(info);//输出服务器返回的字符串
            }
 
            br.close();
            is.close();
            isr.close();
 
 
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}