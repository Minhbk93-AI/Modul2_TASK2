package presentation.users.menuAddress;

import business.color.Color;
import business.entity.Address;
import business.feature.IAddressFeature;
import business.feature.impl.AddressFeatureImpl;
import business.feature.impl.ProductFeatureImpl;

import java.util.Scanner;

public class AddressManager {
    public  static IAddressFeature addressFeature= new AddressFeatureImpl();
    public void addressManager(Scanner scanner){
        boolean isLoop= true;
        do {
            System.out.println(Color.BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━━ MENU ADDRESS ━━━━━━━━━━━━━━━━━━━━━┓" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              1. Hiển thị toàn bộ địa chỉ              " + Color.BLUE+ "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              2. Thêm địa chỉ mới                      " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              3. Sửa địa chỉ                           " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              4. Xóa địa chỉ                           " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              5. Tìm kiếm đại chỉ theo Id              " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              6. Đăng xuất                             " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + Color.RESET);
            try {
                System.out.println("Nhập vào lựa chọn của bạn");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        showAllAddress();
                        break;

                    case 2:
                        addNewAddress(scanner);
                        break;

                    case 3:
                        editAddress(scanner);
                        break;

                    case 4:
                        deleteAddress(scanner);
                        break;
                    case 5:
                        searchAddress();
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

    private void searchAddress() {

    }

    private void deleteAddress(Scanner scanner) {
        if (ProductFeatureImpl.productList.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        try {
            System.out.println("Mời bạn nhập id muốn xóa");
            int idDelete = Integer.parseInt(scanner.nextLine());
            boolean isExit=AddressFeatureImpl.addressList.stream().anyMatch(p -> p.getAddressId()==idDelete);
            if (!isExit){
                System.err.println("Không tìm thấy id muốn xóa "+idDelete);
            }
            else {
                addressFeature.delete(idDelete);
            }
        } catch (NumberFormatException e){
            System.out.println(Color.RED+"Bạn nhập vào phải là một số "+Color.RESET);
        }
    }

    private void editAddress(Scanner scanner) {
        if (AddressFeatureImpl.addressList.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        try {
            System.out.println("Mời bạn nhập idUpdate");
            int idUpdate = Integer.parseInt(scanner.nextLine());

            int indexUpdate = addressFeature.findIndexById(idUpdate);
            if(indexUpdate == -1) {
                System.out.println("Không tồn tại" + idUpdate);
            }else {
                Address addressOLD = AddressFeatureImpl.addressList.get(indexUpdate);
                addressOLD.inputUpdate(scanner, AddressFeatureImpl.addressList);
                addressFeature.addOrUpdate(addressOLD);

            }
        }catch (NumberFormatException e){
            System.out.println(Color.RED+"Bạn nhập vào phải là một số "+Color.RESET);
        }

    }

    private void addNewAddress(Scanner scanner) {
        System.out.println("Mời bạn nhập số địa chỉ muốn thêm vào ");
        int n=0;
        while (true) {
            try {
                n = Integer.parseInt(scanner.nextLine());
                if (n <= 0) {
                    System.err.println("Mời bạn nhập số duong");
                }break;
            } catch (NumberFormatException e) {
                System.err.println("Bạn phải nhập số nguyên");
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Mời bạn thêm địa chỉ thứ " +(i+1));
            Address a = new Address();
            a.inputData(scanner,AddressFeatureImpl.addressList);
            addressFeature.addOrUpdate(a);
        }
    }

    private void showAllAddress() {
        if (AddressFeatureImpl.addressList.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }else {
            AddressFeatureImpl.addressList.forEach(Address::displayData);
        }
    }
}
