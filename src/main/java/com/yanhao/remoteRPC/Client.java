package com.yanhao.remoteRPC;

public class Client {
    public static void main(String[] args) {
        IProductService service = (IProductService) Stub.getStub(IProductService.class);
        System.out.println(service.findProductById(321));
    }
}
