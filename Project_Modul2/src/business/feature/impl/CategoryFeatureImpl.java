package business.feature.impl;

import business.entity.Category;
import business.entity.Products;
import business.entity.ShoppingCart;
import business.feature.ICategoryFeature;
import business.feature.IProductFeature;
import business.feature.impl.ProductFeatureImpl;
import business.utils.IOFile;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CategoryFeatureImpl implements ICategoryFeature {
    public static List<Category> categoryList= new ArrayList<>(IOFile.getListFromFile(IOFile.CATALOG_PATH));
//    static {
//        categoryList.add(new Category(true, "Catalog 1", "Catalog 1",1));
//        categoryList.add(new Category(true, "Catalog 2", "Catalog 2",2));
//        categoryList.add(new Category(true, "Catalog 4", "Catalog 5",3));
//        IOFile.writeToFile(IOFile.CATALOG_PATH, categoryList);
//        ShoppingCartFeatureImpl.shoppingCarts.add(new ShoppingCart(1,ProductFeatureImpl.productList.get(1),12,15));
//        IOFile.writeToFile(IOFile.CATALOG_PATH, categoryList);
//        categoryList = IOFile.getListFromFile(IOFile.CATALOG_PATH);
//    }

    @Override
    public void addOrUpdate(Category category) {
        int index= findIndexById(category.getCategoryId());
        if (index==-1){
            categoryList.add(category);
        }else {
            categoryList.set(index,category);
        }
        IOFile.writeToFile(IOFile.CATALOG_PATH, categoryList);

    }

    @Override
    public void delete(Integer id) {
        categoryList.remove(findIndexById(id));
        IOFile.writeToFile(IOFile.CATALOG_PATH, categoryList);
    }

    @Override
    public int findIndexById(Integer id) {
        return categoryList.stream().map(Category::getCategoryId).toList().indexOf(id);
    }

    @Override
    public Integer getNewId() {
        return 0;
    }
}
