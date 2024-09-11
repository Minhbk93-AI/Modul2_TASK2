package presentation.users.menUserInfor;

import business.color.Color;
import business.entity.Products;
import business.entity.Users;
import business.feature.impl.ProductFeatureImpl;
import business.feature.impl.UserFeatureImpl;
import presentation.run.Main;
import presentation.users.menuAddress.AddressManager;

import java.util.Scanner;

public class MenuUserInfor {
    public static AddressManager addressManager= new AddressManager();
    public void menuUserInfor(Scanner scanner){
        boolean isLoop= true;
        do {

            System.out.println(Color.BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━━ MENU USER INFORMATION ━━━━━━━━━━━━━━━━━━━━━┓" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "                   1. Hiển thị thông tin                    " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "                   2. Cập nhật thông tin                    " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "                   3. Thay đổi mật khẩu                     " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "                   4. Địa chỉ thông tin người dùng          " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "                   5. Quay lại                              " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + Color.RESET);
            try {
                System.out.println("Nhập vào lựa chọn của bạn");
                int choice = Integer.parseInt(scanner.nextLine());


                switch (choice) {
                    case 1:
                        showUserInfor();
                        break;

                    case 2:
                        updateUserInfor();
                        break;

                    case 3:
                        System.out.println("Chưa phát triển");
                        break;
                    case 4:
                        addressManager.addressManager(scanner);
                        break;

                    case 5:
                        isLoop=false;
                        break;

                    default:
                        System.out.println("Vui lòng nhập lựa chọn từ 1 đến 5: ");

                }
            } catch (NumberFormatException e){
                System.out.println(Color.RED + "Bạn phải nhập lựa chọn là một số" + Color.RESET);

            }
        }while (isLoop);
    }

    private void updateUserInfor() {

    }

    private void showUserInfor() {
        System.out.println("Thông tin người dùng:");
        Main.userLogin.displayData();

    }

}
