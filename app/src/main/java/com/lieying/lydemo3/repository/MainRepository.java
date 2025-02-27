package com.lieying.lydemo3.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.lieying.lydemo3.bean.Data;
import com.lieying.lydemo3.bean.NewSpellActiveData;
import com.lieying.lydemo3.bean.NewSpellActiveProduct;
import com.lieying.lydemo3.bean.NewSpellRequest;
import com.lieying.lydemo3.bean.ProductDetail;
import com.lieying.lydemo3.bean.ProductDetailRequest;
import com.lieying.lydemo3.bean.SpellRequest;
import com.lieying.lydemo3.bean.VideoData;
import com.lieying.lydemo3.bean.VideoDataRequest;
import com.lieying.lydemo3.bean.VideoResponseData;
import com.lieying.lydemo3.net.NetWorkManager;
import com.lieying.lydemo3.net.Response;
import com.lieying.lydemo3.net.SchedulerProvider;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.Subject;


public class MainRepository {
    final MutableLiveData<Response<Data>> responseMutableLiveData = new MutableLiveData<>();

    public   Observable<Response<Data>> getData(){
        SpellRequest request = new SpellRequest();
        request.setExpand1("spell");
        request.setPageSize(20);
        request.setPageStart(0);
     //   request.setProductName("string");
     //   request.setSpellGroupId(0);
      //  request.setType(0);
        request.setWouldRecomHome(0);
      Observable<Response<Data>> responseDataObservable = NetWorkManager.getRequest().getSpellData(request);

      return responseDataObservable;

//                        responseDataObservable.compose(SchedulerProvider.getInstance().applySchedulers()).subscribe(new Consumer<Response<Data>>() {
//                            @Override
//                            public void accept(Response<Data> dataResponse) throws Exception {
//                                Log.e("data111",dataResponse.getData().getSpellGroups().toString());
//                                responseMutableLiveData.setValue(dataResponse);
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                throwable.getStackTrace();
//                                responseMutableLiveData.setValue(null);
//                            }
//                        });
        }

        public Observable<Response<NewSpellActiveData>> getNewSpellData(){
            NewSpellRequest request = new NewSpellRequest();
            request.setExpand1("explodSpell");
            request.setPageSize(16);
            request.setPageStart(0);
            Observable<Response<NewSpellActiveData>> responseObservable = NetWorkManager.getRequest().getNewSpellData(request);
            return responseObservable;
        }

        public Observable<Response<ProductDetail>> getProductDetails(int productId){
            ProductDetailRequest request = new ProductDetailRequest();
            request.setProductId(productId);
            Observable<Response<ProductDetail>> repsonseObservable = NetWorkManager.getRequest().getProductDetailData(request);
            return repsonseObservable;
        }

        public Observable<Response<VideoResponseData>> getVideoHomeData(){
            VideoDataRequest request = new VideoDataRequest();
            request.setPageSize(30);
            request.setSaleStatus(1);
            request.setPageStart(0);
            request.setUserId(10);
            Observable<Response<VideoResponseData>> responseObservable = NetWorkManager.getRequest().getVideoHomeData(request);
            return responseObservable;
        }


}

