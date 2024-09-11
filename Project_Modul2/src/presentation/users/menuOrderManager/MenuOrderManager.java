package presentation.users.menuOrderManager;

import business.color.Color;

import java.util.Scanner;

public class MenuOrderManager {
    public void menuOrderManager(Scanner scanner){
        boolean isLoop= true;
        do {
            System.out.println(Color.BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━━ MENU ORDER MANAGEMENT ━━━━━━━━━━━━━━━━━━━━━┓" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              1. Hiển thị toàn bộ đơn hàng                " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              2. Xem chi tiết đơn hàng                    " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              3. Hủy đơn hàng                             " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              4. Tìm kiếm đơn hàng theo ngày a -> b       " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              5. Tìm kiếm đơn hàng theo trạng thái        " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              6. Tìm kiếm đơn hàng theo mã đơn hàng       " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              7. Quay lại                                 " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + Color.RESET);
            try {
                System.out.println("Nhập vào lựa chọn của bạn");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        showAllOrder();
                        break;

                    case 2:
                        ShowOrderDetail();
                        break;

                    case 3:
                        CancelOrder();
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

    private void CancelOrder() {
    }

    private void ShowOrderDetail() {
    }

    private void showAllOrder() {
    }

}
