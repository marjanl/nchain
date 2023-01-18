package com.nchain.util;

import com.nchain.entity.NchainApp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NewNchainApp {

    @NonNull
    String name;
    @NonNull
    String image;
    String androidStore;
    String iosStore;
    Integer ratings;
    Double score;
    Integer size;

    public void setScore(Double s) throws IllegalAccessException {
        ScoreValidator.checkScore(s);
        score = s;
    }

    public List<NchainApp> createNchainAppList(){
        List<NchainApp> list = new ArrayList<>();
        NchainApp androidApp = createNchainApp(androidStore, AppType.android);
        if(androidApp!=null){
            list.add(androidApp);
        }
        NchainApp iosApp = createNchainApp(iosStore, AppType.ios);
        if(iosApp!=null){
            list.add(iosApp);
        }
        return list;
    }

    private NchainApp createNchainApp(String store, AppType appType){
        if(store!=null && !store.isEmpty()) {
            NchainApp nchainApp = new NchainApp();
            nchainApp.setName(name);
            nchainApp.setType(appType);
            nchainApp.setImage(image);
            nchainApp.setStore(store);
            nchainApp.setRatings(ratings);
            nchainApp.setSize(size);
            return nchainApp;
        }
        return null;
    }
}
