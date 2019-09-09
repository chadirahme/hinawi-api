package com.hinawi.api.dto;


public enum HRListFieldsEnum {
    POSITION  (7),  //calls constructor with value 3
    DEPARTMENT(6),  //calls constructor with value 2
    nationality   (1) ,  //calls constructor with value 1
    COUNTRY   (2) ,
    CITY   (3) ,
    BUSINESSTYPE(23),
    COMPANYSize(145),
    currentSoftwares(146),
    HOWDIDYOUKNOWUS(140),
    ; // semicolon needed when fields / methods follow

    private final int levelCode;

    HRListFieldsEnum(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getHRListField() {
        return this.levelCode;
    }
}
