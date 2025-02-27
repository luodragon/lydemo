package com.lieying.lydemo3.config;

public class Config {
    public static String prefixUrl = "https://www.hzlyzb.com/img/";
    // 访问的endpoint地址
    public static final String OSS_ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    //callback 测试地址
    public static final String OSS_CALLBACK_URL = "";
    // STS 鉴权服务器地址，使用前请参照文档 https://help.aliyun.com/document_detail/31920.html 介绍配置STS 鉴权服务器地址。
    // 或者根据工程sts_local_server目录中本地鉴权服务脚本代码启动本地STS 鉴权服务器。详情参见sts_local_server 中的脚本内容。
    public static final String STS_SERVER_URL = "http://192.168.16.188:94/sts.php";//STS 地址

    public static final String BUCKET_NAME = "lieyingshop";
    public static final String OSS_ACCESS_KEY_ID = "LTAI4GGrHHhAJ9Ua3pDUBtkU";
    public static final String OSS_ACCESS_KEY_SECRET = "0FgKNy8VYRxdEmvB5O7FmgbJt5VdSl";

    public static final int DOWNLOAD_SUC = 1;
    public static final int DOWNLOAD_Fail = 2;
    public static final int UPLOAD_SUC = 3;
    public static final int UPLOAD_Fail = 4;
    public static final int UPLOAD_PROGRESS = 5;
    public static final int LIST_SUC = 6;
    public static final int HEAD_SUC = 7;
    public static final int RESUMABLE_SUC = 8;
    public static final int SIGN_SUC = 9;
    public static final int BUCKET_SUC = 10;
    public static final int GET_STS_SUC = 11;
    public static final int MULTIPART_SUC = 12;
    public static final int STS_TOKEN_SUC = 13;
    public static final int FAIL = 9999;
    public static final int REQUESTCODE_AUTH = 10111;
    public static final int REQUESTCODE_LOCALPHOTOS = 10112;
}
