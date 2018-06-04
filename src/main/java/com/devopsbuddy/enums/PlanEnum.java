package com.devopsbuddy.enums;

public enum PlanEnum {

    BASIC(1, "Basic","Basic plan"),
    PRO(2, "Pro", "Professional plan"),
    PRE(3, "Premium", "Premium plan");


    private final int id;

    private final String planName;
    
    private final String desc;

    PlanEnum(int id, String planName, String desc) {
        this.id = id;
        this.planName = planName;
        this.desc=desc;
    }

    public int getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }
}
