package org.vaccine.world.application.service.google.storage;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;

@Service
public class StoregeService {

    private Storage storage;

    private Storage getInstanceStorage(){
        return  storage == null
                ? storage = StorageOptions.getDefaultInstance().getService() : storage;
    }

    public Bucket getBucked(String name){
        return getInstanceStorage().get(name);
    }
}
