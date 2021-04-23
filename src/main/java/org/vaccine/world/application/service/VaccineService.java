package org.vaccine.world.application.service;

import com.google.cloud.datastore.Entity;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vaccine.world.application.dto.request.VaccineRegistrationRequest;
import org.vaccine.world.application.dto.response.VaccineResponse;
import org.vaccine.world.application.mapper.VaccineMapper;
import org.vaccine.world.application.mapper.VaccineRequestMapper;
import org.vaccine.world.application.service.domain.Vaccine;
import org.vaccine.world.application.service.google.dataStore.DataStoreService;
import org.vaccine.world.application.service.google.storage.StoregeService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VaccineService {

    @Value("${spring.application.buckedName}")
    String buckedName;

    @Autowired
    StoregeService storegeService;

    @Autowired
    DataStoreService dataStoreService;

    @Autowired
    VaccineMapper vaccineMapper;

    @Autowired
    VaccineRequestMapper vaccineRequestMapper;


    public List <VaccineResponse> getVaccineTemp(){
        List <VaccineResponse> vaccineResponses = new ArrayList<>();
        vaccineResponses.add(VaccineResponse.builder()
                .id("1")
                .fechaPrimerDosis("24/04/2021")
                .fechaSegundaDosis("24/04/2021")
                .pais("Mexico")
                .poblacionTotal("128,649,565")
                .primeraDosis("20,500")
                .segundaDosis("10,890")
                .primeraDosisTotal("987,563")
                .segundaDosisTotal("425,789")
                .tipoVacuna("Pfizer")
                .build());
        vaccineResponses.add(VaccineResponse.builder()
                .id("2")
                .fechaPrimerDosis("24/04/2021")
                .fechaSegundaDosis("24/04/2021")
                .pais("Rusia")
                .poblacionTotal("144,790,234")
                .primeraDosis("50,000")
                .segundaDosis("25,000")
                .primeraDosisTotal("8,200,000")
                .segundaDosisTotal("4,257,890")
                .tipoVacuna("Spuntnik")
                .build());
        vaccineResponses.add(VaccineResponse.builder()
                .id("3")
                .fechaPrimerDosis("24/04/2021")
                .fechaSegundaDosis("24/04/2021")
                .pais("EUA")
                .poblacionTotal("334,138,000")
                .primeraDosis("298,000")
                .segundaDosis("154,850")
                .primeraDosisTotal("15,800,000")
                .segundaDosisTotal("11,187,890")
                .tipoVacuna("AstraZeneca")
                .build());
        return vaccineResponses;
    }

    public void getVaccine(){
        log.info("[VaccineService:getVaccine] started");
        List<Vaccine> vaccines = new ArrayList<>();
        Bucket bucket = storegeService.getBucked(buckedName);
        log.info("Bucket: {}",bucket.getName());
        List<Entity> entities = dataStoreService.getVaccine();
        bucket.list().iterateAll().forEach(blob -> vaccines.add(getVaccine(entities, blob.getName())));
    }

    private Vaccine getVaccine(List<Entity> entities, String name){
        Iterator<Entity> iter = entities.iterator();
        while(iter.hasNext()){
            Entity ent = iter.next();
            if(ent.getString("name").equals(name)){
                return vaccineMapper.apply(ent);
            }
        }
        return new Vaccine();
    }

    public Vaccine save(VaccineRegistrationRequest VaccineRegistrationRequest){
        //return dataStoreService.save(vaccineRequestMapper.apply(VaccineRegistrationRequest));
        return null;
    }

}
