package concurrent;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
 
/**
 * 基于TCP协议的Socket通信，实现用户登录
 * 服务器端
 */
public class Server {
 
    public static void main(String[] args) {
    	final String SERVER_IP = "127.0.0.1";
    	final int PORT = 3333;
        try {
            //1、创建一个服务器端Socket,即ServerSocket, 指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(PORT, 10, InetAddress.getByName(SERVER_IP) );
            Socket socket = null;
            //记录客户端的数量
            int count = 0;
            System.out.println("服务器已启动，等待客户端的链接。");
            //循环监听等待客户端的链接
            while (true){
                //调用accept()方法开始监听，等待客户端的链接
                socket = serverSocket.accept();
                //创建一个新的线程
                ServerThread serverThread = new ServerThread(socket);
                //启动线程
                serverThread.start();
 
                count++; //统计客户端的数量
         
               
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

