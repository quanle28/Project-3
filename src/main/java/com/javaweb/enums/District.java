package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum District {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_BINH_THANH("Quận Bình Thạnh");

    private final String districtName;
    District(String districtName) {
        this.districtName = districtName;
    }

    public static Map<String, String> type(){
        Map<String, String> listDistricts = new TreeMap<>();
        for(District it : District.values()){
            listDistricts.put(it.toString(), it.districtName);
        }
        return listDistricts;
    }
}
