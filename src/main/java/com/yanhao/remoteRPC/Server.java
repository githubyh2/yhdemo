package com.yanhao.remoteRPC;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (running) {
            Socket accept = serverSocket.accept();
            process(accept);
            accept.close();
        }
        serverSocket.close();
    }

    private static void process(Socket s) throws Exception {
        InputStream in = s.getInputStream();
        OutputStream outputStream = s.getOutputStream();
        ObjectInputStream ois = new ObjectInputStream(in);

        String clazzName = ois.readUTF();
        String methodName = ois.readUTF();
        Class[] parameterTypes = (Class[]) ois.readObject();
        Object[] args = (Object[]) ois.readObject();

        Class clazz = null;
        // 从服务注册表找到具体的类
        clazz = ProductServiceImpl.class;
        Method method = clazz.getMethod(methodName, parameterTypes);
        Object o = (Object) method.invoke(clazz.newInstance(), args);

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(o);
        oos.flush();
    }
}
