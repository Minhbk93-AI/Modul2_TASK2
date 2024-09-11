package presentation.users.menuCart;

import business.color.Color;
import business.entity.*;
import business.feature.IShoppingCartFeature;
import business.feature.impl.AddressFeatureImpl;
import business.feature.impl.OrderDetailsFeatureImpl;
import business.feature.impl.ProductFeatureImpl;
import business.feature.impl.ShoppingCartFeatureImpl;
import business.utils.IOFile;
import presentation.run.Main;
import presentation.users.menuAddress.AddressManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuCart {
    IShoppingCartFeature shoppingCartFeature= new ShoppingCartFeatureImpl();

    public void menuCart(Scanner scanner) {
        boolean isLoop = true;
        do {
            System.out.println(Color.BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━━ MENU USER ━━━━━━━━━━━━━━━━━━━━━┓" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              1. Hiển thị danh sách giỏ hàng                    " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              2. Thêm mới sản phẩm vào giỏ hàng                 " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              3. Thay đổi số lượng sản phẩm trong giỏ hàng                     " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              4. Xóa sản phẩm trong giỏ hàng                    " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              5. Xóa toàn bộ sản phẩm trong giỏ hàng            " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              6. Đặt hàng                                       " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┃" + Color.RESET + "              7. Quay lại                                       " + Color.BLUE + "┃" + Color.RESET);
            System.out.println(Color.BLUE + "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + Color.RESET);
            try {
                System.out.println("Nhập vào lựa chọn của bạn");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        showCartList();
                        break;

                    case 2:
                        addNewProductCart(scanner);
                        break;

                    case 3:
                        changeQtyProCart(scanner);
                        break;

                    case 4:
                        deleteProductCart(scanner);
                        break;
                    case 5:
                        deleteAllProductCart(scanner);
                        break;
                    case 6:
                        orderManager(scanner);
                        break;

                    case 7:
                        isLoop = false;
                        break;

                    default:
                        System.out.println("Vui lòng nhập lựa chọn từ 1 đến 6: ");

                }
            } catch (NumberFormatException e) {
                System.out.println(Color.RED + "Bạn phải nhập lựa chọn là một số" + Color.RESET);

            }
        } while (isLoop);
    }

    private void orderManager(Scanner scanner) {
        AddressFeatureImpl.addressList.stream().filter(a->a.getUserId()== Main.userLogin.getUserId()).forEach(Address::displayData);
        while(true) {
            System.out.println("Nhập mã địa chỉ muốn giao hàng ");
            try {
                int idReceiveAddress = Integer.parseInt(scanner.nextLine());
                Orders order = new Orders();
                order.inputData();

                List<ShoppingCart> cartList = ShoppingCartFeatureImpl.shoppingCartsList.stream()
                        .filter(c->c.getUserId().getUserId()==Main.userLogin.getUserId()).toList();


                for(ShoppingCart cart: ShoppingCartFeatureImpl.shoppingCartsList) {

                    OrderDetail orderDetail = new OrderDetail();
                    Products product = ProById(cart.getProductId().getProductId());

                    if(product != null) {
                        orderDetail.setProductId(cart.getProductId().getProductId());
                        orderDetail.setOrderQuantity(cart.getOrderQuantity());
                        orderDetail.setName(ProById(cart.getProductId().getProductId()).getProductName());
                        orderDetail.setUnitPrice(ProById(cart.getProductId().getProductId()).getUnitPrice() * cart.getOrderQuantity());
                        orderDetail.setOrderId(order.getOrderId());

                        OrderDetailsFeatureImpl.orderDetailsList.add(orderDetail);
                        IOFile.writeToFile(IOFile.ORDERDETAIL_PATH, OrderDetailsFeatureImpl.orderDetailsList);
                        // chua luu vao file orderDetail
                        product.setStockQuantity(product.getStockQuantity() - orderDetail.getOrderQuantity());
                        IOFile.writeToFile(IOFile.PRODUCT_PATH,ProductFeatureImpl.productList);

                        System.out.println("MUA HÀNG THÀNH CÔNG.");
                    } else {
                        System.err.println("Sản phẩm với ID " + cart.getProductId() + " không tồn tại.");
                    }
                }
                ShoppingCartFeatureImpl.shoppingCartsList.removeAll(cartList);
                // luu lai vao file
                IOFile.writeToFile( IOFile.CART_PATH,ShoppingCartFeatureImpl.shoppingCartsList);
                break;
            } catch (NumberFormatException e) {
                System.err.println("VUI LÒNG NHẬP SỐ NGUYÊN.");
            }
        }
    }

    private Products ProById(int id) {
        for(Products product: ProductFeatureImpl.productList) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    private void deleteAllProductCart(Scanner scanner) {
        System.out.println("Bạn có chắc chắn muốn xóa toàn bộ sản phẩm trong giỏ hàng không? (y/n): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            if (ShoppingCartFeatureImpl.shoppingCartsList.isEmpty()) {
                System.err.println("Danh sách giỏ hàng đang trống.");
                return;
            }

            ShoppingCartFeatureImpl.shoppingCartsList.removeAll(
                    ShoppingCartFeatureImpl.shoppingCartsList.stream()
                            .filter(item ->item.getUserId().getUserId()==Main.userLogin.getUserId())
                            .toList()
            );
            System.out.println("Tất cả sản phẩm đã được xóa khỏi giỏ hàng.");
        } else {
            System.out.println("Hủy thao tác xóa toàn bộ sản phẩm.");
        }
    }



    private void changeQtyProCart(Scanner scanner) {
        if(ShoppingCartFeatureImpl.shoppingCartsList == null || ShoppingCartFeatureImpl.shoppingCartsList.isEmpty()){
            System.err.println("List is empty");
            return;
        }
        int cartId;
        while (true){
            try{
                System.out.println("Enter cart id");
                cartId = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.err.println("You must enter a number, please try again");
            }
        }
        int qtyCart;
        while (true){
            try {
                System.out.println("Enter quantity you want to add");
                qtyCart = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.err.println("You must enter a number, please try again");
            }
        }
        boolean check = false;
        for(ShoppingCart carts: ShoppingCartFeatureImpl.shoppingCartsList){
            if(carts.getShoppingCartId() == cartId){
                carts.setOrderQuantity(qtyCart);
                System.out.println("Update quantity success");
                check = true;
                break;
            }
        }
        if(!check){
            System.err.println("Not found Cart with id: "+cartId);
        }
    }


    private void deleteProductCart(Scanner scanner) {
        System.out.println("Nhập mã ID sản phẩm cần xóa khỏi giỏ hàng: ");
        try {
            int proId = Integer.parseInt(scanner.nextLine());

            Optional<ShoppingCart> optionalShoppingCart = ShoppingCartFeatureImpl.shoppingCartsList.stream()
                    .filter(cart -> cart.getProductId().getProductId() == proId)
                    .findFirst();

            if (optionalShoppingCart.isPresent()) {

                shoppingCartFeature.delete(proId);
                System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
            } else {
                System.out.println("Sản phẩm không tồn tại trong giỏ hàng.");
            }
        } catch (NumberFormatException e) {
            System.out.println(Color.RED + "Nhập vào phải là một số " + Color.RESET);
        }
    }


    private void addNewProductCart(Scanner scanner) {

        System.out.println("Nhập mã ID sản phẩm: ");
        try {
            int proId = Integer.parseInt(scanner.nextLine());

            Optional<Products> optionalProduct = ProductFeatureImpl.productList.stream()
                    .filter(p -> p.getProductId() == proId)
                    .findFirst();

            if (optionalProduct.isPresent()) {
                Products product = optionalProduct.get();

                Optional<ShoppingCart> optionalShoppingCart = ShoppingCartFeatureImpl.shoppingCartsList.stream()
                        .filter(cart -> cart.getProductId().getProductId() == proId)
                        .findFirst();

                if (optionalShoppingCart.isPresent()) {
                    ShoppingCart cart = optionalShoppingCart.get();
                    System.out.println("Sản phẩm đã có trong giỏ hàng. Nhập số lượng muốn thêm: ");
                    int quantityToAdd = Integer.parseInt(scanner.nextLine());
                    cart.setOrderQuantity(cart.getOrderQuantity() + quantityToAdd);
                    System.out.println("Đã cập nhật số lượng sản phẩm trong giỏ hàng.");
                } else {

                    System.out.println("Sản phẩm chưa có trong giỏ hàng. Nhập số lượng: ");
                    int quantity = Integer.parseInt(scanner.nextLine());


                    ShoppingCart newCart = new ShoppingCart();
                    newCart.setProductId(product);
                    newCart.setUserId(Main.userLogin);
                    newCart.setOrderQuantity(quantity);
                    newCart.setShoppingCartId(shoppingCartFeature.getNewId());


                    shoppingCartFeature.addOrUpdate(newCart);
                    System.out.println("Đã thêm sản phẩm vào giỏ hàng.");
                }
            } else {
                System.out.println("Sản phẩm không tồn tại.");
            }
        } catch (NumberFormatException e) {
            System.out.println(Color.RED + "Nhập vào phải là một số " + Color.RESET);
        }
    }

    private void showCartList() {
        if(ShoppingCartFeatureImpl.shoppingCartsList.isEmpty()){
            System.err.println("Danh sách giỏ hàng đang trống");
            return;
        }
        ShoppingCartFeatureImpl.shoppingCartsList.forEach(ShoppingCart::displayData);
    }

}



