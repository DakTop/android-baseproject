package com.runtop.android.baselibrary.tool;

import java.util.List;
import java.util.Map;

public class ListTool {

    public static boolean isEmpty(List list) {
        return list == null || list.size() <= 0;
    }

    public static boolean isNotEmpty(List list) {
        return list != null && list.size() > 0;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() <= 0;
    }

    public static boolean isNotEmpty(Map map) {
        return map != null && map.size() > 0;
    }

}
