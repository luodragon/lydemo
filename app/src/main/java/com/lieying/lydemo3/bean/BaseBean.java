package com.lieying.lydemo3.bean;

public class BaseBean {
    int id;
    String groupName;
    String groupImg;
    int groupSort;
    int wouldShow;
    int status;
    String remark;
    String expand1;
    String expand2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public int getGroupSort() {
        return groupSort;
    }

    public void setGroupSort(int groupSort) {
        this.groupSort = groupSort;
    }

    public int getWouldShow() {
        return wouldShow;
    }

    public void setWouldShow(int wouldShow) {
        this.wouldShow = wouldShow;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExpand1() {
        return expand1;
    }

    public void setExpand1(String expand1) {
        this.expand1 = expand1;
    }

    public String getExpand2() {
        return expand2;
    }

    public void setExpand2(String expand2) {
        this.expand2 = expand2;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupImg='" + groupImg + '\'' +
                ", groupSort=" + groupSort +
                ", wouldShow=" + wouldShow +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", expand1='" + expand1 + '\'' +
                ", expand2='" + expand2 + '\'' +
                '}';
    }
}
