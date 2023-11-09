package com.yanhao.remoteRPC;

public class ProductServiceImpl implements IProductService {
    @Override
    public Product findProductById(Integer id) {
        return new Product(id, "white");
    }
}
