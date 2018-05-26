package com.sbt.reflection.hometask.testclasess;

import java.util.Date;

public class TestClass2 {
    private int field1;

    public TestClass2(int field1, String field2, Date field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    private String field2;
    private Date field3;

    public int getField1() {
        return field1;
    }

    public void setField1(int field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public Date getField3() {
        return field3;
    }

    public void setField3(Date field3) {
        this.field3 = field3;
    }
}
