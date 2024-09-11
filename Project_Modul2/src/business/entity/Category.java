package business.entity;

import business.color.Color;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Category implements Serializable {
    private int categoryId;
    private String categoryName;
    private String description;
    private Boolean status;

    public Category() {
    }

    public Category(Boolean status, String description, String categoryName, int categoryId) {
        this.status = status;
        this.description = description;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    //Input Data Catalog
    public void inputData(Scanner sc, List<Category> catalogs) {
        this.categoryId = inputCategoryId(sc,catalogs);
        this.categoryName =inputCategoryName(sc);
        this.description=inputCategoryDes(sc);
        this.status=inputStatus(sc);
    }



    public void inputUpdate(Scanner sc, List<Category> categoryList) {
        this.categoryName =inputCategoryName(sc);
        this.description=inputCategoryDes(sc);
    }

    public String inputCategoryDes(Scanner sc) {
        System.out.println("Mời bạn nhập description nhé");
        do {
            String des=sc.nextLine();
            if (des.isBlank()) {
                System.err.println("Không để trống mô tả");
            }
            else {
                return des;
            }

        }while (true);
    }

    private String inputCategoryName(Scanner sc) {
        System.out.println("Mời bạn nhập tên category ");
        do {
            String nameCate=sc.nextLine();
            if (nameCate.isBlank()) {
                System.err.println("Không để trống tên");
            }
            else {
                return nameCate;
            }

        }while (true);
    }

    public int inputCategoryId(Scanner sc, List<Category> categoryList) {
//        int maxId=0;
//        for(int i=0;i<catalogs.size();i++) {
//            if(catalogs.get(i).getCatalogId()>maxId) {
//                maxId=catalogs.get(i).getCatalogId();
//            }
//        }
//        return maxId+1;

        return categoryList.stream().mapToInt(Category::getCategoryId).
                max().
                orElse(0)+1;
    }
    public Boolean inputStatus(Scanner sc) {
        System.out.println("Mời bạn nhập status là 'true' hoặc 'false' ");
        do {
            String status = sc.nextLine();
            try {
                if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(status);
                } else {
                    System.out.println(Color.RED+"Bạn nhập sai cú pháp True-False"+Color.RESET);
                }
            }catch (Exception e){
                System.out.println(Color.RED+"Kiểu bạn nhập phải là true hoặc false"+Color.RESET);
            }
        }while(true);
    }

public void displayData(){
    System.out.printf("[CategoryID:%-5s | CategoryName:%-15s | Description:%-15s | Status: %-5s]\n",
            this.categoryId,this.categoryName, this.description,this.status?"Active":"InActive"
            );
}

}
