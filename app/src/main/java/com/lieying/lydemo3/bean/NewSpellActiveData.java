package com.lieying.lydemo3.bean;

import java.util.ArrayList;

public class NewSpellActiveData {
    int pageCount;
    int pageSize;
    int totalSize;
    ArrayList<NewSpellActiveProduct> resultList;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public ArrayList<NewSpellActiveProduct> getResultList() {
        return resultList;
    }

    public void setResultList(ArrayList<NewSpellActiveProduct> resultList) {
        this.resultList = resultList;
    }
}
