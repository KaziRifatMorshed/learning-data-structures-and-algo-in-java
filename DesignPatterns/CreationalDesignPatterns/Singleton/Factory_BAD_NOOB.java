package DesignPatterns.CreationalDesignPatterns.Singleton;

class Product {
}

class ProductA extends Product {
}

class ProductB extends Product {
}

class ProductC extends Product {
}

class ProductFactoryBAD {
    public Product createProduct(String productId) {
        if (productId == "A") return new ProductA();
        if (productId == "B") return new ProductB();
        if (productId == "C") return new ProductC();

        return null;
    }
}