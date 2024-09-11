package business.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Formatter {
    public static String getNumberFormatterVND(double amount){
        // Sử dụng Locale để định dạng tiền tệ theo quy tắc của quốc gia
        Locale locale = new Locale("vi", "VN");

        // Sử dụng DecimalFormat để định dạng số
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);

        // Thiết lập định dạng tiền tệ
        decimalFormat.applyPattern("#,###,###,###.## VNĐ");

        return decimalFormat.format(amount);
    }
}
