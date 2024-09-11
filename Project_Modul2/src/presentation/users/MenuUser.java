package presentation.users;

import business.color.Color;
import business.entity.Products;
import business.feature.impl.ProductFeatureImpl;
import presentation.users.menUserInfor.MenuUserInfor;
import presentation.users.menuCart.MenuCart;

import java.util.Scanner;

public class MenuUser {
    MenuUserInfor menuUserInfor= new MenuUserInfor();
    MenuCart menuCart= new MenuCart();
    public void menuUser(Scanner scanner){
        boolean isLoop= true;
       do {
           System.out.println(Color.BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━━ MENU USER ━━━━━━━━━━━━━━━━━━━━━┓" + Color.RESET);
           System.out.println(Color.BLUE + "┃" + Color.RESET + "              1. Hiển thị danh sách sản phẩm           " + Color.BLUE + "┃" + Color.RESET);
           System.out.println(Color.BLUE + "┃" + Color.RESET + "              2. Xem thông tin cá nhân                 " + Color.BLUE + "┃" + Color.RESET);
           System.out.println(Color.BLUE + "┃" + Color.RESET + "              3. Quản lý giỏ hàng                      " + Color.BLUE + "┃" + Color.RESET);
           System.out.println(Color.BLUE + "┃" + Color.RESET + "              4. Quản lý đơn hàng                      " + Color.BLUE + "┃" + Color.RESET);
           System.out.println(Color.BLUE + "┃" + Color.RESET + "              5. Quản lý danh sách yêu thích           " + Color.BLUE + "┃" + Color.RESET);
           System.out.println(Color.BLUE + "┃" + Color.RESET + "              6. Đăng xuất                             " + Color.BLUE + "┃" + Color.RESET);
           System.out.println(Color.BLUE + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + Color.RESET);
           try {
               System.out.println("Nhập vào lựa chọn của bạn");
               int choice = Integer.parseInt(scanner.nextLine());

               switch (choice) {
                   case 1:
                       showProduct();
                       break;

                   case 2:
                       menuUserInfor.menuUserInfor(scanner);
                       break;

                   case 3:
                       menuCart.menuCart(scanner);
                       break;

                   case 4:
                       System.out.println("ab");
                       break;
                   case 6:
                     isLoop=false;
                     break;

                   default:
                       System.out.println("Vui lòng nhập lựa chọn từ 1 đến 6: ");

               }
           } catch (NumberFormatException e){
                   System.out.println(Color.RED + "Bạn phải nhập lựa chọn là một số" + Color.RESET);

           }
       }while (isLoop);
    }

    private void menuCartManager() {

    }

    private void showProduct() {
        ProductFeatureImpl.productList.stream().forEach(Products::displayData);
    }
}

