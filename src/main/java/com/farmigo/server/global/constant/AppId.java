package com.farmigo.server.global.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description : OS 별 AppId Enum class
 */
public enum AppId {
    iOS(20004001, "I"),
    Android(20004005, "A");

    private final int value;
    private final String name;
    private static final Map<Integer, AppId> VALUE_AND_ID_MAP = new HashMap<>();

    static {
        for (AppId appId : values()) {
            VALUE_AND_ID_MAP.put(appId.getValue(), appId);
        }
    }

    AppId(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static boolean isValidAppId(int appId) {
        return VALUE_AND_ID_MAP.containsKey(appId);
    }

    public static List<String> getAppIds() {
        List<String> appIds = new ArrayList<>();
        appIds.add(String.valueOf(iOS.getValue()));
        appIds.add(String.valueOf(Android.getValue()));
        return appIds;
    }
}
