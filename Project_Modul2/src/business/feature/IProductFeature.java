package business.feature;

import business.entity.Products;

public interface IProductFeature extends IGenericFeature<Products,Integer>{
    void sortProductByPrice();
    void searchProductByName(String nameProduct);
}
