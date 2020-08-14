package com.lieying.lydemo3.bean;

import java.util.ArrayList;

public class VideoResponseData {
    int pageCount;
    int pageSize;
    int totalSize;
    ArrayList<VideoData> knowledgeProductList;

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

    public ArrayList<VideoData> getKnowledgeProductList() {
        return knowledgeProductList;
    }

    public void setKnowledgeProductList(ArrayList<VideoData> knowledgeProductList) {
        this.knowledgeProductList = knowledgeProductList;
    }

    @Override
    public String toString() {
        return "VideoResponseData{" +
                "pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                ", totalSize=" + totalSize +
                ", knowledgeProductList=" + knowledgeProductList +
                '}';
    }
}
