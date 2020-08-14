package com.lieying.lydemo3.net;

import com.lieying.lydemo3.bean.Data;
import com.lieying.lydemo3.bean.NewSpellActiveData;
import com.lieying.lydemo3.bean.NewSpellRequest;
import com.lieying.lydemo3.bean.ProductDetail;
import com.lieying.lydemo3.bean.ProductDetailRequest;
import com.lieying.lydemo3.bean.SpellRequest;
import com.lieying.lydemo3.bean.VideoData;
import com.lieying.lydemo3.bean.VideoDataRequest;
import com.lieying.lydemo3.bean.VideoResponseData;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Request {
   // public static String HOST = "http://120.77.243.156:8081/";
   //public static String HOST = "http://192.168.16.48:8091/";
    public static String HOST = "http://192.168.16.38:8095/";
    @POST("/shop-mobile-app/spellApi/listMSpellProduct")
    Observable<Response<Data>> getSpellData(@Body SpellRequest spell);

    @POST("/shop-mobile-app/spellApi/listMNewSpellActive")
    Observable<Response<NewSpellActiveData>> getNewSpellData(@Body NewSpellRequest newSpell);

    @POST("/shop-mobile-app/productInfoApi/selectMProductInfo")
    Observable<Response<ProductDetail>> getProductDetailData(@Body ProductDetailRequest productDetail);

    @POST("/shop-mobile-app/knowledgeApi/listKnowledgeGroupProduct")
    Observable<Response<VideoResponseData>> getVideoHomeData(@Body VideoDataRequest videoDataRequest);
}
