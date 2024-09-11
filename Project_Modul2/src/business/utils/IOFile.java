package business.utils;
import business.color.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static String PRODUCT_PATH= "src/business/data/product.txt";
    public static String CATALOG_PATH= "src/business/data/catalog.txt";
    public static String USER_PATH= "src/business/data/user.txt";
    public static String CART_PATH= "src/business/data/cart.txt";
    public static String ORDERDETAIL_PATH="src/business/data/cart.txt";

    //
    public static <T> List<T> getListFromFile(String path){
        File file = new File(path);
        if (!file.exists()){
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        try {
            FileInputStream fis= new FileInputStream(file);
            ObjectInputStream ois= new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println(Color.RED+"không tìm thấy file"+e.getMessage()+Color.RESET);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(Color.RED+"Lỗi không đọc được file"+Color.RESET);
        } catch (ClassNotFoundException e) {
            System.out.println(Color.RED+"không thể ép về class"+Color.RESET);
        }
        return list;
    }

    public static <T> void  writeToFile(String path, List<T> list){
        File file =  new File(path);
        ObjectOutputStream oos = null;
        try{
            FileOutputStream fos = new FileOutputStream(file);
            oos =  new ObjectOutputStream(fos);
            oos.writeObject(list); // ghi ra file


        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(Color.RED+"Lỗi ghi file"+Color.RESET);
        }finally {
            try {
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
