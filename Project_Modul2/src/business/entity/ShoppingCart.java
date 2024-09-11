package business.entity;

import business.feature.impl.ProductFeatureImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ShoppingCart implements Serializable {
    private int shoppingCartId;
    private Products productId;
    private Users userId;
    private int orderQuantity;

    public ShoppingCart() {
    }

    public ShoppingCart(int shoppingCartId, Products productId, Users userId, int orderQuantity) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
        this.userId = userId;
        this.orderQuantity = orderQuantity;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    //Input Data Cart
//    public void inputData(Scanner sc, List<ShoppingCart> shoppingCartList, List<Products> productsList, int productID){
//        this.shoppingCartId =inputCartId(shoppingCartList);
//        this.productId = inputProduct(sc,productID);
//        this.orderQuantity = inputProductQuantity(sc,productId);
////        this.userId = inputUserId();
//    }
//
////    private Users inputUserId() {
////
////    }
//
//    private int inputProductQuantity(Scanner sc, Products product) {
//        int qtyProducts;
//        do {
//            while (true){
//                try{
//                    System.out.println("Nhập vào số lượng sản phẩm: ");
//                    qtyProducts = Integer.parseInt(sc.nextLine());
//                    break;
//                }
//                catch (NumberFormatException e){
//                    System.out.println("Nhập sai cú pháp phải là số nguyên");
//                }
//            }
//            if (product.getStockQuantity()<qtyProducts){
//                System.err.println("Số lượng mua không được nhiều hơn số hàng trong kho");
//            }else {
//                return qtyProducts;
//            }
//
//        }while (true);
//    }
//
//
//
//    private Products inputProduct(Scanner sc, int productID) {
//
//        Optional<Products> optionalProduct = ProductFeatureImpl.productList.stream().
//                filter(product1 -> product1.getProductId() == productID).findFirst();
//        return optionalProduct.orElse(null);
//
//    }
//
//    private int inputCartId (List<ShoppingCart> cartItems) {
//        return cartItems.stream().mapToInt(ShoppingCart::getShoppingCartId).max().orElse(0)+1;
//    }

    //Display Cart
    public void displayData(){
        System.out.printf("ShoppingCartId : %-3d | ProductName: %-15s | OrderQuantity %-5s \n"
                ,this.shoppingCartId,this.productId.getProductName(),this.orderQuantity);
    }

}
