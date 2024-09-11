package presentation.admin.menuUser;

import business.color.Color;
import business.entity.Users;
import business.feature.impl.UserFeatureImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MenuUserManager {
    public void menuUserManger(Scanner sc) {
        boolean isLoop= true;
        do {
            System.out.println(Color.BLUE + "================== MENU USER =================" + Color.RESET);
            System.out.println("          1. HIỂN THỊ NGƯỜI DÙNG                 ");
            System.out.println("          2. SỬA THAY ĐỔI TRẠNG THÁI             ");
            System.out.println("          3. XÓA NGƯỜI DÙNG                      ");
            System.out.println("          4. TÌM KIẾM NGƯỜI DÙNG                 ");
            System.out.println("          5. SẮP XÊP TĂNG DẦN THEO TÊN           ");
            System.out.println("          6. QUAY LẠI                            ");
            System.out.println(Color.BLUE + "===============================================" + Color.RESET);
            try {
                System.out.println("Nhập vào lựa chọn của bạn");
                int choice= Integer.parseInt(sc.nextLine()) ;
                switch (choice)   {
                    case 1:
                        displayUser();
                        break;
                    case 2:
                        editStatusUser();
                        break;
                    case 3:
                        deleteUser();
                        break;
                    case 4:
                        searchUser();
                        break;
                    case 5:
                        sortUserByName();
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

    private void sortUserByName() {
        if (UserFeatureImpl.usersList.isEmpty()) {
            System.out.println("Danh sách người dùng đang trống.");
            return;
        }

        // Sort the list of users by name in ascending order
        UserFeatureImpl.usersList.sort(Comparator.comparing(Users::getUserName));

        // Display the sorted list
        System.out.println("Danh sách người dùng được sắp xếp theo tên:");
        UserFeatureImpl.usersList.forEach(Users::displayData);
    }


    private void searchUser() {
        if (UserFeatureImpl.usersList.isEmpty()) {
            System.out.println("Danh sách người dùng đang trống.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên hoặc email của người dùng cần tìm kiếm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Users> foundUsers = UserFeatureImpl.usersList.stream()
                .filter(u -> u.getUserName().toLowerCase().contains(keyword) ||
                        u.getEmail().toLowerCase().contains(keyword))
                .toList();

        // Display the results
        if (foundUsers.isEmpty()) {
            System.out.println("Không tìm thấy người dùng phù hợp với từ khóa: " + keyword);
        } else {
            System.out.println("Kết quả tìm kiếm:");
            foundUsers.forEach(Users::displayData);
        }
    }


    private void deleteUser() {
        if (UserFeatureImpl.usersList.isEmpty()) {
            System.out.println("Danh sách người dùng đang trống.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào ID người dùng cần xóa: ");

        try {
            int userId = Integer.parseInt(scanner.nextLine());

            // Find the user by their ID
            Users userToDelete = UserFeatureImpl.usersList.stream()
                    .filter(u -> u.getUserId() == userId)
                    .findFirst()
                    .orElse(null);

            if (userToDelete != null) {
                System.out.println("Người dùng tìm thấy: " + userToDelete.getUserName());
                System.out.println("Bạn có chắc chắn muốn xóa người dùng này không? (y/n): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("y")) {
                    UserFeatureImpl.usersList.remove(userToDelete);
                    System.out.println("Người dùng đã được xóa thành công.");
                } else {
                    System.out.println("Hủy thao tác xóa người dùng.");
                }

            } else {
                System.err.println("Không tìm thấy người dùng với ID: " + userId);
            }

        } catch (NumberFormatException e) {
            System.err.println("ID phải là một số nguyên.");
        }
    }

    private void editStatusUser() {
        if (UserFeatureImpl.usersList.isEmpty()) {
            System.out.println("Danh sách người dùng đang trống.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào ID người dùng cần thay đổi trạng thái: ");

        try {
            int userId = Integer.parseInt(scanner.nextLine());
            
            Users user = UserFeatureImpl.usersList.stream()
                    .filter(u -> u.getUserId() == userId)
                    .findFirst()
                    .orElse(null);

            if (user != null) {
                System.out.println("Người dùng tìm thấy: " + user.getUserName());
                System.out.println("Trạng thái hiện tại: " + (user.isStatus() ? "Hoạt động" : "Không hoạt động"));
                System.out.println("Nhập trạng thái mới (1 = Hoạt động, 0 = Không hoạt động): ");

                int newStatus = Integer.parseInt(scanner.nextLine());
                if (newStatus == 1) {
                    user.setStatus(true);
                    System.out.println("Trạng thái người dùng đã được thay đổi thành: Hoạt động.");
                } else if (newStatus == 0) {
                    user.setStatus(false);
                    System.out.println("Trạng thái người dùng đã được thay đổi thành: Không hoạt động.");
                } else {
                    System.err.println("Trạng thái không hợp lệ. Vui lòng nhập 1 hoặc 0.");
                }

            } else {
                System.err.println("Không tìm thấy người dùng với ID: " + userId);
            }

        } catch (NumberFormatException e) {
            System.err.println("ID phải là một số nguyên.");
        }
    }


    private void displayUser() {
        if (UserFeatureImpl.usersList.isEmpty()){
            System.out.println("Danh sách người dùng đang trống");
        }else{
            UserFeatureImpl.usersList.stream().forEach(Users::displayData);
        }
    }
}


