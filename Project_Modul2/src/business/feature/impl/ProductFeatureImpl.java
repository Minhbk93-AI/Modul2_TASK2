package business.feature.impl;

import business.entity.Category;
import business.entity.Products;
import business.feature.IProductFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductFeatureImpl implements IProductFeature {
    public static List<Products> productList= new ArrayList<>(IOFile.getListFromFile(IOFile.PRODUCT_PATH));
    @Override
    public void addOrUpdate(Products products) {
        int indexUpdate=findIndexById(products.getProductId());
        if(indexUpdate==-1){
            productList.add(products);
    }else {
            productList.set(indexUpdate,products);
        }
        IOFile.writeToFile(IOFile.PRODUCT_PATH, productList);
    }

    @Override
    public void delete(Integer id) {
        productList.remove(findIndexById(id));
        IOFile.writeToFile(IOFile.PRODUCT_PATH, productList);

    }

    @Override
    public int findIndexById(Integer id) {
        return productList.stream().map(Products::getProductId).toList().indexOf(id);
    }

    @Override
    public Integer getNewId() {
        return 0;
    }

    @Override
    public void sortProductByPrice() {
        if (productList == null || productList.isEmpty()) {
             System.out.println("Danh sách sản phẩm trống, không thể sắp xếp.");
            return;
        }

        try {
            productList.stream().sorted(Comparator.comparingDouble(Products::getUnitPrice).reversed()).forEach(Products::displayData);
            System.out.println("Danh sách sản phẩm đã được sắp xếp theo giá.");
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi sắp xếp sản phẩm: " + e.getMessage());
        };
    }


    @Override
    public void searchProductByName(String nameProduct) {
        for (Products product : productList ) {
            if(product.getProductName().equals(nameProduct)){
                System.out.println("Chi tiết sản phẩm");
                product.displayData();
                return;
            }
        }

    }
}
