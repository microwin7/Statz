package me.staartvin.utils.pluginlibrary.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Util class
 * <p>
 * Date created: 17:38:32 12 aug. 2015
 *
 * @author Staartvin
 */
public class Util {

    public static double roundDouble(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        } else {
            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
    }
}
