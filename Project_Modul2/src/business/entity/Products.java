package business.entity;

import business.color.Color;
import business.feature.impl.CategoryFeatureImpl;
import business.utils.Formatter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Products implements Serializable {
    private int productId;
    private String sku= UUID.randomUUID().toString();
    private String productName;
    private String description;
    private Double unitPrice;
    private int stockQuantity;
    private Category categoryId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean status;

    public Products() {
    }

    public Products(int productId, String sku, String productName, String description, Double unitPrice, int stockQuantity,Category categoryId, LocalDateTime created, LocalDateTime updated) {
        this.productId = productId;
        this.sku = sku;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.created = created;
        this.updated = updated;

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void inputData(Scanner sc, List<Products> productsList, List<Category> categoryList){
        this.productId = inputProductId(sc,productsList);
        this.productName = inputProductName(sc);
        this.unitPrice = inputProductPrice(sc);
        this.description = inputProductDes(sc);
        this.categoryId = inputProductCatalog(sc);
        this.stockQuantity = inputProductStock(sc);
//        this.created= LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.created= LocalDateTime.now();
        this.updated= LocalDateTime.now();
        this.status = true;
    }
    public void inputUpdate(Scanner sc, List<Products> productsList,List<Category> categoryList){
        this.productName = inputProductName(sc);
        this.unitPrice = inputProductPrice(sc);
        this.description = inputProductDes(sc);
        this.categoryId = inputProductCatalog(sc);
        this.stockQuantity = inputProductStock(sc);
//        this.updated= LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.updated= LocalDateTime.now();
        this.status = inputStatusUpdate(sc);
    }

    private boolean inputStatusUpdate(Scanner sc) {
        System.out.println("Mời bạn nhập status true or false");
        do {
            String status = sc.nextLine();
            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            }
            else {
                System.err.println("Bạn nhập sai cú pháp True-False");
            }


        }while(true);
    }

    private int inputProductStock(Scanner sc) {
        int categoryStock ;
        do {
            while (true){
                try {
                    System.out.println("Mời bạn nhập catalog tồn kho bạn muốn thêm vào ");
                    categoryStock = Integer.parseInt(sc.nextLine());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Yêu cầu bạn nhập số nguyên");
                }
            }
            if (categoryStock <= 0){
                System.out.println(Color.RED+"Category stock phải lớn hơn 0"+Color.RESET);
            }
            else {
                return categoryStock;
            }

        }while (true);
    }

    private Category inputProductCatalog(Scanner sc) {
        //hiển thị danh sách Category
        CategoryFeatureImpl.categoryList.forEach(category -> {
                    System.out.printf("[Id:%-5d|Name:%-15s]\n",category.getCategoryId(),category.getCategoryName());

                }
        );
        System.out.println("Lựa chọn id category");
        do {
            int idCate = Integer.parseInt(sc.nextLine());
//            if (catalogId.isBlank()) {
//                System.err.println("Không để trống ID");
//            }
            Optional<Category> optionalCategory=CategoryFeatureImpl.categoryList.stream().filter(item->item.getCategoryId()==idCate).findFirst();
            if(optionalCategory.isPresent()){
                Category category=optionalCategory.get();
                return optionalCategory.get();
            }
        }while(true);
    }

    private String inputProductDes(Scanner sc) {
        System.out.println("Mời bạn nhập mô tả sản phẩm");
        do {
            String des = sc.nextLine();
            if (des.isBlank()){
                System.err.println("Không để trống mô tả sản phẩm");
            }
            else {
                return des;
            }


        }while(true);
    }

    private double inputProductPrice(Scanner sc) {
        double productPrice;
        do {
            while (true){
                try {
                    System.out.println("Mời bạn nhập giá sản phẩm");
                    productPrice = Double.parseDouble(sc.nextLine());
                    break;
                }
                catch (NumberFormatException e) {
                    System.err.println("Bạn phải nhập số, xin vui lòng nhập lại");
                }
            }
            if(productPrice > 0){
                Locale localeVN = new Locale("vi", "VN");
//                return NumberFormat.getCurrencyInstance(localeVN).format(productPrice);
                return productPrice;
            }else {
                System.err.println("giá sản phẩm phải lớn hơn không");;
            }



        }while (true);
    }

    private String inputProductName(Scanner sc) {
        System.out.println("Mời bạn nhập tên sản phẩm");
        do {
            String name = sc.nextLine();
            if (name.isBlank()){
                System.err.println("Không để trống tên");
            }
            else {
                return name;
            }


        }while(true);
    }

//    private int inputProductId(Scanner sc) {
//        System.out.println("Mời bạn nhập ID");
//        do {
//            String productId = sc.nextLine();
//            if (productId.matches("^P\\d{4}$")) {
//                boolean check= ProductFeatureImpl.productList.stream().anyMatch(product -> product.getProductId().equals(productId));
//                if (check) {
//                    System.err.println("Tên sản phẩm đã bị trùng");
//                }
//                else {
//                    return productId;
//                }
//            }
//            else {
//                System.err.println("Bạn nhập sai cú pháp vd P1000");
//            }
//
//        }while (true);
//
//    }
public int inputProductId(Scanner sc, List<Products> productsList) {
//        int maxId=0;
//        for(int i=0;i<catalogs.size();i++) {
//            if(catalogs.get(i).getCatalogId()>maxId) {
//                maxId=catalogs.get(i).getCatalogId();
//            }
//        }
//        return maxId+1;

    return productsList.stream().mapToInt(Products::getProductId).
            max().
            orElse(0)+1;
}


    public void displayData() {

        System.out.printf(
                "[ID: %s | Sku: %s | Name: %s | Des: %s | Price: %s | Stock: %d | Catalog: %s | Created: %s | Updated: %s | Status: %s]\n",
                this.productId,
                this.sku,
                this.productName,
                this.description,
                Formatter.getNumberFormatterVND(this.unitPrice),
                this.stockQuantity,
                this.categoryId.getCategoryName(),
                this.created.format( DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                this.updated.format( DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                this.status ? "Active" : "InActive"
        );
    }
}

