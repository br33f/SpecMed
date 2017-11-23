package com.i4m1s1.specmed.core.helper;

import com.i4m1s1.specmed.core.enums.Specialization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class CommonHelper {

    public static List<String> specToStringArray(List<Specialization> spec) {
        List<String> result = new ArrayList<>();
        for (Specialization s : spec) {
            result.add(s.desc());
        }
        return result;
    }
}
