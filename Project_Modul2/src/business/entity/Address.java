package business.entity;

import business.feature.impl.UserFeatureImpl;
import presentation.run.Main;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Address implements Serializable {
    private int addressId;
    private int userId;
    private String fullAddress;
    private String phone;
    private String receiveName;

    public Address() {
    }

    public Address(int addressId, int userId, String fullAddress, String phone, String receive) {
        this.addressId = addressId;
        this.userId = userId;
        this.fullAddress = fullAddress;
        this.phone = phone;
        this.receiveName = receive;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceive() {
        return receiveName;
    }

    public void setReceive(String receive) {
        this.receiveName = receive;
    }

    public  void inputData(Scanner scanner, List<Address> addressLists ){
        this.addressId = inputAddressId(scanner,addressLists);
        this.userId= inputUserId();
        this.fullAddress= inputFullAddress(scanner);
        this.phone = inputPhone(scanner);
        this.receiveName = inputReceiveName(scanner);

    }
    public  void inputUpdate(Scanner scanner, List<Address> addressLists ){
        this.addressId = inputAddressId(scanner,addressLists);
        this.userId= inputUserId();
        this.fullAddress= inputFullAddress(scanner);
        this.phone = inputPhone(scanner);
        this.receiveName = inputReceiveName(scanner);

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

    private String inputReceiveName(Scanner scanner) {
        System.out.println("Nhập vào tên người nhận hàng ");
        do {
            String receiveName = scanner.nextLine();
            if (receiveName.isBlank()){
                System.err.println("Không để trống tên người nhận");
            }
            else {
                return receiveName;
            }

        }while (true);
    }

    private String inputFullAddress(Scanner scanner) {
        System.out.println("Nhập vào địa chi chi tiết ");
        do {
            String fullAddress = scanner.nextLine();
            if (fullAddress.isBlank()){
                System.err.println("Không để trống đia chỉ");
            }
            else {
                return receiveName;
            }
        }while (true);
    }

    private int inputUserId() {
      return   Main.userLogin.getUserId();
    }

    private int inputAddressId(Scanner scanner, List<Address> addressLists) {
        return addressLists.stream().mapToInt(Address::getAddressId).
                max().
                orElse(0)+1;
    }


    public  void displayData(){
        System.out.printf("[AddressID:%-5s | UserId:%-15s | FullAddress:%-15s | ReceiveName: %-5s | Phone: %-15s]\n",
                this.addressId,this.userId, this.fullAddress,this.receiveName,this.phone
        );

    }

}
