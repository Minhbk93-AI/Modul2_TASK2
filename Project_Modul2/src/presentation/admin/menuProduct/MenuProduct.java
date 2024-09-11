package presentation.admin.menuProduct;

import business.color.Color;
import business.entity.Products;
import business.feature.IProductFeature;
import business.feature.impl.CategoryFeatureImpl;
import business.feature.impl.ProductFeatureImpl;

import java.util.Scanner;

public class MenuProduct {
    public  static IProductFeature productsFeature = new ProductFeatureImpl();
    public void menuProduct(Scanner sc) {
        boolean isLoop = true;
        do
        {
            System.out.println("""
							============================ MENU ==========================
							1. Nhập số sản phẩm muốn thêm và nhập thông tin sản phẩm
							2. Hiển thị thông tin sản phẩm
							3. Sắp xếp sản phẩm theo giá theo thứ tự giảm dần
							4. Xóa sản phẩm theo id
							5. Tìm kiếm sản phẩm theo tên
							6. Thay đổi thông tin sách theo id sách
							7. Quay lại
							
							============================================================
							""");
            int choice ;
            while (true) {
                try {
                    System.out.println("Mời bạn lựa chọn menu");
                    choice = Integer.parseInt(sc.nextLine());
                    if (choice <= 0) {
                        System.err.println("Vui lòng nhập lại số dương");
                    }
                    break;
                }
                catch (NumberFormatException e
                )
                {
                    System.err.println("Bạn vui lòng nhập số nguyên");
                }
            }
            switch (choice)
            {
                case 1:
                {
                    addNewProduct(sc);
                    break;
                }
                case 2:
                {
                    showAllProduct();
                    break;
                }
                case 3:
                {
                    arrangeProductByPriceDecrease();
                    break;
                }
                case 4:
                {
                    deleteProduct(sc);
                    break;
                }
                case 5:
                {
                    searchProductByName(sc);
                    break;
                }
                case 6:
                {
                    updateProduct(sc);
                    break;
                }
                case 7:
                {
                    isLoop = false;
                    break;
                }
                default:
                    System.err.println("Nhập lại lựa chon từ 1 -> 6");
            }
        }
        while (isLoop);
    }

    private void searchProductByName(Scanner sc) {
        if (ProductFeatureImpl.productList.isEmpty()){
            System.err.println("Danh sách trống ko thể search theo tên");
            return;
        }
        System.out.println("Mời bạn nhập tên sản phẩm");
        String name = sc.nextLine();
        boolean isExit=ProductFeatureImpl.productList.stream().anyMatch(product -> product.getProductName().equals(name));
        if (isExit){
            productsFeature.searchProductByName(name);
        }else {
            System.err.println("Không tìm thấy sản phẩm "+name);
        }

    }

    private void arrangeProductByPriceDecrease() {
        productsFeature.sortProductByPrice();
    }

    private void updateProduct(Scanner sc) {
        if (ProductFeatureImpl.productList.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        try {
            System.out.println("Mời bạn nhập idUpdate");
            int idUpdate = Integer.parseInt(sc.nextLine());

        int indexUpdate = productsFeature.findIndexById(idUpdate);
        if(indexUpdate == -1) {
            System.out.println("Không tồn tại" + idUpdate);
        }else {
            Products productOLD = ProductFeatureImpl.productList.get(indexUpdate);
            productOLD.inputUpdate(sc,ProductFeatureImpl.productList, CategoryFeatureImpl.categoryList);
            productsFeature.addOrUpdate(productOLD);

        }
        }catch (NumberFormatException e){
            System.out.println(Color.RED+"Bạn nhập vào phải là một số "+Color.RESET);
        }

    }

    private void deleteProduct(Scanner sc) {
        if (ProductFeatureImpl.productList.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        try {
        System.out.println("Mời bạn nhập id muốn xóa");
        int idDelete = Integer.parseInt(sc.nextLine());
        boolean isExit=ProductFeatureImpl.productList.stream().anyMatch(product -> product.getProductId()==idDelete);
        if (!isExit){
            System.err.println("Không tìm thấy id muốn xóa "+idDelete);
        }
        else {
            productsFeature.delete(idDelete);
        }
        } catch (NumberFormatException e){
                System.out.println(Color.RED+"Bạn nhập vào phải là một số "+Color.RESET);
            }
    }

    private void showAllProduct() {
        if (ProductFeatureImpl.productList.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        ProductFeatureImpl.productList.forEach(Products::displayData);
    }

    private void addNewProduct(Scanner sc) {
        System.out.println("Mời bạn nhập số lượng sản phẩm muốn thêm vào ");
        int n=0;
        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0) {
                    System.err.println("Mời bạn nhập số duong");
                }break;
            } catch (NumberFormatException e) {
                System.err.println("Bạn phải nhập số nguyên");
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Mời bạn thêm sản phẩm thứ " +(i+1));
            Products p = new Products();
            p.inputData(sc, ProductFeatureImpl.productList, CategoryFeatureImpl.categoryList);
            productsFeature.addOrUpdate(p);
        }
    }
}
