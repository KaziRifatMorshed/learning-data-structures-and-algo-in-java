package DesignPatterns.CreationalDesignPatterns.Singleton;

// A more advanced solution - Factory design pattern with abstractions(Factory Method)

import java.util.HashMap;

class ProductFactory {
    private HashMap<String, Class> registeredProduct = new HashMap<>();

    public void setRegisteredProduct(String productID, Class productClass) {
        registeredProduct.put(productID, productClass);
    }

    public _Product createProduct(String productID) {
//        return ((_Product) registeredProduct.get(productID)).createProduct();
        return null;
    }
}

abstract class _Product {
    public abstract _Product createProduct();
}

class OneProduct extends _Product {
//    static {
//        ProductFactory.instance();
//    }

    @Override
    public _Product createProduct() {
        return new OneProduct();
    }
}