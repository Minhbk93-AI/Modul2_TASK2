package business.entity;

import business.color.Color;
import business.constants.RoleName;
import business.feature.impl.UserFeatureImpl;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class Users implements Serializable {
    private RoleName roleName;
    private int userId;
    private String userName;
    private String email;
    private String fullName;
    private Boolean status= true;
    private String password;
    private String phone;
    private String address;
    private LocalDate created;
    private LocalDate updated;
    private boolean isDeleted;

    public Users() {

    }

    public Users(RoleName roleName,int userId, String userName, String email, String fullName, Boolean status, String password, String phone) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.status = status;
        this.password = password;
        this.phone = phone;
        this.roleName = roleName;
    }

    public Users(RoleName roleName, int userId, String userName, String email, String fullName, Boolean status, String password, String phone, String address, LocalDate created, LocalDate updated, boolean isDeleted) {
        this.roleName = roleName;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.status = status;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.created = created;
        this.updated = updated;
        this.isDeleted = isDeleted;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void inputRegister(Scanner scanner){
        this.fullName= inputFullName(scanner);
        this.userName= inputUserName(scanner);
        this.password= inputPassword(scanner);
        this.email= inputEmail(scanner);
        this.phone=inputPhone(scanner);
//        this.roleName = RoleName.USER;

    }

    public void inputLogin(Scanner scanner){
        this.userName= inputUserName(scanner);
        this.password= inputPassword(scanner);
    }

    private String inputFullName(Scanner scanner) {
        System.out.println("Nhập vào tên của bạn: ");
        do {
            String fullName= scanner.nextLine();
            if (fullName.isBlank()){
                System.err.println("FullName không được để trống");
            }else {
                return fullName;
            }
        }while (true);
    }

    private String inputUserName(Scanner scanner) {
        System.out.println("Nhập vào tên đăng nhập: ");
        do {
            String user= scanner.nextLine();
            if (user.isBlank()){
                System.out.println(Color.RED+"Không được để trống UserName"+Color.RESET);
            } else if (user.length()<6 || user.length()>100) {
                System.out.println(Color.RED+"UserName phải từ 6 đến 100 kí tự"+Color.RESET);
            } else {
                if (user.matches("^[a-zA-Z0-9]+$")){
//                    boolean isExist= UserFeatureImpl.usersList.stream().anyMatch(u->u.getUserName().equals(user));
//                    if (isExist){
//                        System.err.println("Tên đăng nhập đã trùng lặp");
//                    }
//                    else {
//                        return user;
//                    }
                    return user;
                }
            }
        }while (true);
    }

    private String inputPassword(Scanner scanner) {
        System.out.println("Nhập mật khẩu của bạn: ");
        do {
            String password= scanner.nextLine();
            if (password.length()<6){
                System.err.println("Mật khẩu phải từ 6 ký tự trở lên");
            }else {
                return password;
            }
        }while (true);
    }

    private String inputEmail(Scanner scanner) {
        System.out.println("Nhập vào email của bạn: ");
        do {
            String email = scanner.nextLine();
            if (email.isBlank()) {
                System.err.println("Email không được để trống.");
            } else {
                // Kiểm tra định dạng email
                if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                    // Kiểm tra email đã tồn tại trong danh sách người dùng chưa
                    boolean isExist = UserFeatureImpl.usersList.stream()
                            .anyMatch(u -> u.getEmail().equals(email));
                    if (isExist) {
                        System.err.println("Email đã tồn tại.");
                    } else {
                        return email;
                    }
                } else {
                    System.err.println("Email không hợp lệ, vui lòng nhập lại.");
                }
            }
        } while (true);
    }

    private String inputPhone(Scanner scanner) {
        System.out.println("Nhập vào số điện thoại của bạn: ");
        do {
            String phone = scanner.nextLine();
            if (phone.isBlank()) {
                System.err.println("Số điện thoại không được để trống.");
            } else {
                // Kiểm tra định dạng số điện thoại VN (ví dụ: bắt đầu bằng số 0 và có 10-15 số)
                if (phone.matches("^0[0-9]{9,14}$")) {
                    // Kiểm tra số điện thoại đã tồn tại trong danh sách người dùng chưa
                    boolean isExist = UserFeatureImpl.usersList.stream()
                            .anyMatch(u -> u.getPhone().equals(phone));
                    if (isExist) {
                        System.err.println("Số điện thoại đã tồn tại.");
                    } else {
                        return phone;
                    }
                } else {
                    System.err.println("Số điện thoại không hợp lệ. Vui lòng nhập lại số điện thoại (định dạng từ 10-15 ký tự, bắt đầu bằng số 0).");
                }
            }
        } while (true);
    }


    public void displayData() {
        System.out.println( "Users{" +
                "password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                '}');;
    }
}





