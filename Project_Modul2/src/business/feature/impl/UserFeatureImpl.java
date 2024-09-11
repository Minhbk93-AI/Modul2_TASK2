package business.feature.impl;

import business.constants.RoleName;
import business.entity.Category;
import business.entity.Users;
import business.feature.IUserFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class UserFeatureImpl implements IUserFeature {
    public static List<Users> usersList= new ArrayList<>(IOFile.getListFromFile(IOFile.USER_PATH));
    static {
        Users usersAdmin= new Users(RoleName.ADMIN,1,"Admin123","Admin123@gmail","Admin123",true,"12345678","0392198889");
        usersList.add(usersAdmin);
        IOFile.writeToFile(IOFile.USER_PATH, usersList);
    }


    @Override
    public void addOrUpdate(Users users) {
        // Tìm xem người dùng đã tồn tại hay chưa
        int index = findIndexById(users.getUserId());
        if (index >= 0) {
            // Cập nhật thông tin người dùng đã tồn tại
            usersList.set(index, users);
            System.out.println("Cập nhật người dùng thành công.");
        } else {
            // Gán ID mới và thêm người dùng vào danh sách
            users.setUserId(getNewId());
            usersList.add(users);
            System.out.println("Thêm người dùng mới thành công.");
        }
        IOFile.writeToFile(IOFile.USER_PATH,usersList);
    }

    @Override
    public void delete(Integer id) {
        int index = findIndexById(id);
        if (index >= 0) {
            usersList.get(index).setDeleted(true);
            System.out.println("Đã xóa người dùng có ID: " + id);
        } else {
            System.out.println("Không tìm thấy người dùng có ID: " + id);
        }
        IOFile.writeToFile(IOFile.USER_PATH,usersList);
    }

    @Override
    public int findIndexById(Integer id) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUserId() == id ) {
                return i; // Trả về index của người dùng
            }
        }
        return -1; // Nếu không tìm thấy trả về -1
    }

    @Override
    public Integer getNewId() {
        if (usersList.isEmpty()) {
            return 1; // ID bắt đầu từ 1 nếu danh sách rỗng
        }
        // Tìm ID lớn nhất và tăng lên 1
        return usersList.stream()
                .mapToInt(Users::getUserId)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public Users login(Users loginInfo) {
        // Tìm người dùng dựa trên username và password
        for (Users user : usersList) {
            if (user.getUserName().equals(loginInfo.getUserName()) &&
                    user.getPassword().equals(loginInfo.getPassword()) &&
                    !user.isDeleted()) {
                return user; // Trả về người dùng nếu login thành công
            }
        }
        return null; // Trả về null nếu đăng nhập không thành công
    }

}
