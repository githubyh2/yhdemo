package com.yanhao.remoteRPC;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * 但是这里仅仅实现了findByUserId的方法代理，如果要实现其它方法的代理该怎么做呢？
 * <p>
 * 这里就要从协议层做出改进
 * <p>
 * 服务器端也要做出对应处理
 */
public class Stub {
    public static Object getStub(Class clazz) {
        // 调用处理程序
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1", 8888);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                // 方法名
                String clazzName = clazz.getName();
                String methodName = method.getName();
                // 参数类型
                Class<?>[] parameterTypes = method.getParameterTypes();
                oos.writeUTF(clazzName);
                oos.writeUTF(methodName);
                oos.writeObject(parameterTypes);
                oos.writeObject(args);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object o = ois.readObject();
                oos.close();
                socket.close();
                return o;
            }
        };
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, h);
        System.out.println(o.getClass().getName());
        System.out.println(o.getClass().getInterfaces()[0]);
        return o;
    }
}
