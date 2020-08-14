package com.lieying.lydemo3.ui.hotbuy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lieying.lydemo3.bean.Data;
import com.lieying.lydemo3.bean.NewSpellActiveData;
import com.lieying.lydemo3.net.Response;
import com.lieying.lydemo3.repository.MainRepository;

import io.reactivex.Observable;

public class HotBuyViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    LiveData<Response<Data>> spellLiveData = new MutableLiveData<>();
    MainRepository repository = new MainRepository();

    public Observable<Response<Data>> getSpellLiveData() {

        return repository.getData();
    }

    public Observable<Response<NewSpellActiveData>> getSpellActiveData() {
        return repository.getNewSpellData();
    }

}
