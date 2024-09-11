package presentation.admin;

import business.color.Color;
import presentation.admin.menuCategory.MenuCategory;
import presentation.admin.menuProduct.MenuProduct;
import presentation.admin.menuUser.MenuUserManager;
import presentation.users.MenuUser;

import java.util.Scanner;

public class MenuAdmin {
    MenuCategory menuCategory= new MenuCategory();
    MenuProduct menuProduct= new MenuProduct();
    MenuUserManager menuUserManager = new MenuUserManager();
    public void menuAdmin(Scanner sc) {
        boolean isLoop= true;
        do {
            System.out.println(Color.BLUE + "================== MENU ADMIN =================" + Color.RESET);
            System.out.println("          1. QUẢN LÝ DANH MỤC                  ");
            System.out.println("          2. QUẢN LÝ SẢN PHẨM                  ");
            System.out.println("          3. QUẢN LÝ ĐƠN HÀNG                  ");
            System.out.println("          4. QUẢN LÝ NGƯỜI DÙNG                ");
            System.out.println("          5. THỐNG KÊ                          ");
            System.out.println("          6. ĐĂNG XUẤT                         ");
            System.out.println(Color.BLUE + "===============================================" + Color.RESET);
            try {
                System.out.println("Nhập vào lựa chọn của bạn");
           int choice= Integer.parseInt(sc.nextLine()) ;
        switch (choice)   {
            case 1:
                menuCategory.menuCatalog(sc);
                break;
            case 2:
                menuProduct.menuProduct(sc);
                break;
            case 3:
                break;
            case 4:
                menuUserManager.menuUserManger(sc);
                break;
            case 5:
                break;
            case 6:
                isLoop=false;
                break;
            default:
                System.err.println("Nhập vào lựa chọn từ 1-6");
        }
            }catch (NumberFormatException e){
                System.err.println("Nhập vào lựa chọn phải là một số");
            }

        }while (isLoop);
    }
}