package func.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Currency {

    private static final NumberFormat VND_FORMAT = NumberFormat.getInstance(new Locale("vi", "VN"));

    public static String formatVND(BigDecimal amount) {
        return VND_FORMAT.format(amount.setScale(0, RoundingMode.HALF_UP)) + " VND";
    }

    public static String formatVND(int amount) {
        return VND_FORMAT.format(amount) + " VND";
    }

}
