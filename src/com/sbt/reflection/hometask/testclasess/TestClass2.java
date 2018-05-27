package com.sbt.reflection.hometask.testclasess;

import java.util.Date;

public class TestClass2 {
    private int intField;
    private String strField;
    private long longField;

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public String getStrField() {
        return strField;
    }

    public void setStrField(String strField) {
        this.strField = strField;
    }

    public long getLongField() {
        return longField;
    }

    public void setLongField(long longField) {
        this.longField = longField;
    }

    public TestClass2(int intField, String strField, long longField) {

        this.intField = intField;
        this.strField = strField;
        this.longField = longField;
    }
}
