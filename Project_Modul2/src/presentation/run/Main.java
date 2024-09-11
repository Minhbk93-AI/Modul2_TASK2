package presentation.run;

import business.color.Color;
import business.constants.RoleName;
import business.entity.Users;
import business.feature.IUserFeature;
import business.feature.impl.UserFeatureImpl;
import business.utils.IOFile;
import presentation.admin.MenuAdmin;
import presentation.users.MenuUser;

import java.util.Scanner;

public class Main {
    IUserFeature userFeature= new UserFeatureImpl();
    public static Users userLogin=null;
    public Main(){
        UserFeatureImpl.usersList = IOFile.getListFromFile(IOFile.USER_PATH);

    }

    public static void main(String[] args) {
        Main main= new Main();
        Scanner scanner= new Scanner(System.in);
        do {
            System.out.println(Color.BLUE+"┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━┓"+Color.RESET);
            System.out.println(Color.BLUE+"┃                           ┃                             ┃                          ┃                          ┃                          ┃"+Color.RESET);
            System.out.println(Color.BLUE+"┃"+Color.RESET+"        1. ĐĂNG NHẬP       "+Color.BLUE+"┃"+Color.RESET+"          2. ĐĂNG KÝ         "+Color.BLUE+"┃"+Color.RESET+"   3.HIỂN THỊ SẢN PHẨM    "+Color.BLUE+"┃"+Color.RESET+"    4. QUÊN MẬT KHẨU      "+Color.BLUE+"┃"+Color.RESET+"          5.THOÁT         "+Color.BLUE+"┃"+Color.RESET);
            System.out.println(Color.BLUE+"┃                           ┃                             ┃                          ┃                          ┃                          ┃"+Color.RESET);
            System.out.println(Color.BLUE+"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━┛"+Color.RESET);
            System.out.println(Color.YELLOW+"Lựa chọn của bạn: "+Color.RESET);
            try {
                int choice= Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                    {
                        main.handleLogin(scanner);
                        break;
                    }
                    case 2:
                    {
                        main.handleRegister(scanner);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Chưa phát triển");
                        break;
                    }

                    case 4:
                    {
                        System.out.println("ab");
                        break;
                    }
                    case 5:
                    {
                        System.exit(0);
                        break;
                    }
                    default:
                        System.out.println("Vui lòng nhập lựa chọn từ 1 đến 5: ");
                        
                }
            }catch (NumberFormatException e){
                System.out.println(Color.RED+"Bạn phải nhập lựa chọn là một số"+Color.RESET);
            }

        }while (true);

    }

    public void handleRegister(Scanner scanner) {
        Users users= new Users();
        users.inputRegister(scanner);
        users.setRoleName(RoleName.USER);
        userFeature.addOrUpdate(users);
    }

    public void handleLogin(Scanner scanner) {
        Users users= new Users();
        users.inputLogin(scanner);
        users = userFeature.login(users);
        if (users==null){
            System.err.println("Tên đăng nhập hoặc mật khẩu sai");
            return;
        }
        userLogin=users;
        if (users.getRoleName().equals(RoleName.ADMIN)){

            MenuAdmin admin = new MenuAdmin();
            admin.menuAdmin(scanner);
        } else {
            //Điều hướng sang menu user
            MenuUser user = new MenuUser();
            user.menuUser(scanner);
        }
    }
}
