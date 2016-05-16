package mdelacalle.com.oraculo.utils;

import java.util.Arrays;

import mdelacalle.com.oraculo.R;

/**
 * Created by mdelacalle on 02/05/16.
 */
public class DecorationUtils {

    public static int colorizeCell5(double min, double max, double value,boolean inverse){

        final double all = max - min;
        final double intervalValue = all / 5;
        final int interval = 1;


        if (!inverse) {
            if (value <= min) {
                return R.color.green;
            }
            if (value >= max) {
                return R.color.red;
            }
        }
        else {
            if (value <= min) {
                return R.color.red;
            }
            if (value >= max) {
                return R.color.green;
            }
        }

        final double[] intervals = new double[5];
        intervals[0] = min + intervalValue;
        intervals[1] = intervals[0] + intervalValue;
        intervals[2] = intervals[1] + intervalValue;
        intervals[3] = intervals[2] + intervalValue;
        intervals[4] = intervals[3] + intervalValue;


        if ((value >= min) && (value < intervals[0])) {
            if (!inverse) {
                return R.color.green;
            }
            return R.color.red;
        }
        if ((value >= intervals[0]) && (value < intervals[1])) {
            if (!inverse) {
                return R.color.lime;
            }
            return R.color.orange;
        }
        if ((value >= intervals[1]) && (value < intervals[2])) {
            return R.color.yellow;
        }
        if ((value >= intervals[2]) && (value < intervals[3])) {
            if (!inverse) {
                return R.color.orange;
            }
            return R.color.lime;
        }
        if ((value >= intervals[3])) {
            if (!inverse) {
                return R.color.red;
            }
            return R.color.green;
        }

        return -1;
    }
}
