package com.hoangviet.demoui.model;

import java.util.Arrays;

public class MyPojo {

    private String quota_max;

    private String quota_remaining;

    private String has_more;

    private Items[] items;

    public String getQuota_max() {
        return quota_max;
    }

    public void setQuota_max(String quota_max) {
        this.quota_max = quota_max;
    }

    public String getQuota_remaining() {
        return quota_remaining;
    }

    public void setQuota_remaining(String quota_remaining) {
        this.quota_remaining = quota_remaining;
    }

    public String getHas_more() {
        return has_more;
    }

    public void setHas_more(String has_more) {
        this.has_more = has_more;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ClassPojo [quota_max = " + quota_max + ", quota_remaining = " + quota_remaining + ", has_more = " + has_more + ", items = " + Arrays.toString(items) + "]";
    }

}
