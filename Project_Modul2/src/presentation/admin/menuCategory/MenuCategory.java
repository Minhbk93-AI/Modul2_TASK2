package presentation.admin.menuCategory;

import business.entity.Category;
import business.feature.ICategoryFeature;
import business.feature.impl.CategoryFeatureImpl;
import business.feature.impl.ProductFeatureImpl;

import java.util.Scanner;

public class MenuCategory {
    ICategoryFeature categoryFeature=new CategoryFeatureImpl();

    public void menuCatalog(Scanner sc) {

        boolean isLoop = true;
        do
        {
            System.out.println("""
							====================================== MENU ======================================
							1. Nhập số lượng danh mục mới được thêm vào và nhập thông tin cho từng danh mục
							2. Hiển thị thông tin của tất cả các danh mục
							3. Chỉnh sửa tên danh mục theo id danh mục
							4. Xóa danh mục theo mã danh mục (lưu ý: không xóa khi có sản phẩm)
							5. Quay lại							
							
							==================================================================================
											
							""");
            int choice=0 ;
            while (true) {
                try {
                    System.out.println("Mời bạn lựa chọn menu");
                    choice = Integer.parseInt(sc.nextLine());
                    if (choice <= 0) {
                        System.err.println("Vui lòng nhập lại số duong");
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
                    addNewCatalog(sc);
                    break;
                }
                case 2:
                {
                    showAllCate();
                    break;
                }
                case 3:
                {
                    editAllCate(sc);
                    break;
                }
                case 4:
                {
                    deleteCate(sc);
                    break;
                }

                case 5:
                {
                    isLoop = false;
                    break;
                }
                default:
                    System.err.println("Nhập lại từ 1 -> 6 đê");
            }
        }
        while (isLoop);
    }

    private void deleteCate(Scanner sc) {
        int idDelete;
        while (true){
            try {
                System.out.println("Mời bạn nhập mã catalog:");
                idDelete = Integer.parseInt(sc.nextLine());

                // Ensure the ID is positive
                if (idDelete <= 0) {
                    System.err.println("Bạn phải nhập số dương");
                    continue;
                }

                break; // Exit the loop if input is valid
            } catch (NumberFormatException e) {
                System.err.println("Bạn phải nhập số nguyên hợp lệ");
            }
        }

        int finalIdDelete = idDelete;
        boolean isExist = CategoryFeatureImpl.categoryList.stream().anyMatch(catalog -> catalog.getCategoryId()== finalIdDelete);
        if (!isExist) {
            System.err.println("Không tìm thấy"+idDelete);
            return;
        }
        boolean hasProduct= ProductFeatureImpl.productList.stream().anyMatch(product -> product.getCategoryId().getCategoryId()==finalIdDelete);
        if(hasProduct){
            System.err.println("Có sản phẩm ko thể xóa Cate");
        }else {
            categoryFeature.delete(idDelete);
        }
    }

    private void editAllCate(Scanner sc) {
        int cataId;
        while (true){
            try {System.out.println("Nhập mã catalog vào");
                cataId = Integer.parseInt(sc.nextLine());
                if(cataId<=0){
                    System.err.println("Bạn phải nhập số lớn hơn 0");
                    continue;
                }
                break;

            }
            catch (NumberFormatException e) {
                System.err.println("Bạn phải nhập số nguyên");
            }

        }

        int indexUpdate=categoryFeature.findIndexById(cataId);
        if (indexUpdate==-1){
            System.out.println("Không tồn tại"+cataId);
        }else {
            Category categoryOld=CategoryFeatureImpl.categoryList.get(indexUpdate);
            categoryOld.inputUpdate(sc,CategoryFeatureImpl.categoryList);
            categoryFeature.addOrUpdate(categoryOld);
        }
    }

    private void showAllCate() {
        if(CategoryFeatureImpl.categoryList.isEmpty()){
            System.err.println("Danh sách trống");
            return;
        }
        CategoryFeatureImpl.categoryList.forEach(Category::displayData);
    }

    private void addNewCatalog(Scanner sc) {
        System.out.println("Mời bạn nhập số lượng category muốn thêm vào: ");
        int n=0;
        while (true){
            try {
                n=Integer.parseInt(sc.nextLine());
                if (n<=0){
                    System.err.println("Phải nhập số dương");
                }
                break;}
            catch (NumberFormatException e){
                System.err.println("Phải nhập số nguyên");
            }
        }
        for (int i=0;i<n;i++){
            System.out.println("Thêm phần tử thứ "+(i+1));
            Category category=new Category();
            category.inputData(sc,CategoryFeatureImpl.categoryList);
            categoryFeature.addOrUpdate(category);
        }
    }

}
