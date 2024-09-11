package business.feature.impl;

import business.entity.Orders;
import business.entity.ShoppingCart;
import business.feature.IShoppingCartFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFeatureImpl implements IShoppingCartFeature {
    public static List<ShoppingCart>shoppingCartsList= new ArrayList<>(IOFile.getListFromFile(IOFile.CART_PATH));

    @Override
    public void addOrUpdate(ShoppingCart shoppingCart) {
        int index = findIndexById(shoppingCart.getShoppingCartId());
        if (index == -1) {
            shoppingCartsList.add(shoppingCart);
        }else {
            shoppingCartsList.set(index, shoppingCart);
        }
        IOFile.writeToFile(IOFile.CART_PATH,shoppingCartsList);

    }

    @Override
    public void delete(Integer id) {
        shoppingCartsList.remove(findIndexById(id));
        IOFile.writeToFile(IOFile.CART_PATH,shoppingCartsList);

    }

    @Override
    public int findIndexById(Integer id) {
        return shoppingCartsList.stream().map(ShoppingCart::getShoppingCartId).toList().indexOf(id);
    }

    @Override
    public Integer getNewId() {
        return shoppingCartsList.stream().mapToInt(ShoppingCart::getShoppingCartId).max().orElse(0)+1;
    }
}
