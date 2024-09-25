package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public enum TypeCode {
    NGUYEN_CAN("Nguyên Căn"),
    NOI_THAT("Nội Thất"),
    TANG_TRET("Tầng Trệt");

    private final String typeCodeName;

    TypeCode(String typeCodeName) {
        this.typeCodeName = typeCodeName;
    }

    public static Map<String, String> type(){
        Map<String, String> listTypeCodes = new TreeMap<>();
        for(TypeCode it : TypeCode.values()){
            listTypeCodes.put(it.toString(), it.typeCodeName);
        }
        return listTypeCodes;
    }
}
