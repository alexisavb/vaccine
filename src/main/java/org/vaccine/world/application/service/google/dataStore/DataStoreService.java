package org.vaccine.world.application.service.google.dataStore;

import com.google.cloud.datastore.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vaccine.world.application.service.domain.Vaccine;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataStoreService {

    private Datastore datastore;

    @Value("${spring.application.kind}")
    String kind;

    private Datastore getInstanceDataStore(){
        return datastore == null
                ? DatastoreOptions.getDefaultInstance().getService() : datastore;
    }

    public List<Entity> getVaccine(){
        List<Entity> entities = new ArrayList<Entity>();
        Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).setOrderBy(StructuredQuery.OrderBy.asc("pais")).build();
        getInstanceDataStore().run(query).forEachRemaining(entities ::  add);
        return entities;
    }

    public Vaccine save(Vaccine vaccine){
        Datastore datastore = getInstanceDataStore();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind(kind);
        Key key = null;

        if(vaccine.getId() == null || vaccine.getId().isEmpty()){
            key = datastore.allocateId(keyFactory.newKey());
        }else{
            key = keyFactory.newKey(new Long(vaccine.getId()));
        }

        Entity entity = Entity.newBuilder(key)
                .set("primeraDosis", vaccine.getPrimeraDosis())
                .set("segundaDosis", vaccine.getSegundaDosis())
                .set("fechaPrimerDosis", vaccine.getFechaPrimerDosis())
                .set("fechaSegundaDosis", vaccine.getFechaSegundaDosis())
                .set("primeraDosisTotal", vaccine.getPrimeraDosisTotal())
                .set("segundaDosisTotal", vaccine.getSegundaDosisTotal())
                .set("tipoVacuna", vaccine.getTipoVacuna())
                .set("pais", vaccine.getPais())
                .set("poblacionTotal", vaccine.getPoblacionTotal())
                .build();
        Transaction txn = datastore.newTransaction();
        // calling transaction.active() now would return true
        try {
            // add the entity and commit
            txn.put(entity);
            txn.commit();
        } finally {
            // if committing succeeded
            // then transaction.active() will be false
            if (txn.isActive()) { txn.rollback(); }
            else { vaccine.setId(key.getId().toString()); }
        }
        return vaccine;
    }
}
