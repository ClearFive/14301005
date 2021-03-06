package concurrent;

import java.io.*;
import java.net.Socket;
 
/**
 * 服务器端线程处理类
 */
public class ServerThread extends Thread {
 
    //和本线程相关的Socket
    Socket socket = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    }
 
    //线程执行的操作，响应客户端的请求
    public void run(){
 
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
 
        OutputStream os = null;
        PrintWriter pw = null;
        StringBuffer   sb = null;
        try {
 
            //获取一个输入流，并读取客户端的信息
            is = socket.getInputStream();
            isr = new InputStreamReader(is); //将字节流转化为字符流
            br = new BufferedReader(isr); //添加缓冲
            String info = null;
            //循环读取数据
            while ((info = br.readLine()) != null){
                System.out.println("客户端传来字符串：" +info);
                sb   =   new   StringBuffer(""+info);
            }
              
            
            
            socket.shutdownInput(); //关闭输入流
 
            //获取输出流，响应客户端的请求
            os = socket.getOutputStream();
            pw = new PrintWriter(os); //包装为打印流
            pw.write(sb.reverse().toString());//将接受的字符串倒序返回
            pw.flush();  //将缓存输出
 
 
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
 
 
                try {
                    //关闭资源
                    if (pw != null)
                        pw.close();
                    if (os != null)
                        os.close();
                    if (is != null)
                        is.close();
                    if (isr != null)
                        isr.close();
                    if (br != null)
                        br.close();
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
 
                }
 
        }
 
 
 
    }
}