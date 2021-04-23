package org.vaccine.world.application.mapper;

import com.google.cloud.datastore.Entity;
import org.springframework.stereotype.Component;
import org.vaccine.world.application.service.domain.Vaccine;

import java.util.function.Function;

@Component
public class VaccineMapper implements Function<Entity, Vaccine> {
    @Override
    public Vaccine apply(Entity entity) {
        return Vaccine.builder()
                .id(entity.getString("id"))
                .primeraDosis(entity.getString("primeraDosis"))
                .segundaDosis(entity.getString("segundaDosis"))
                .fechaPrimerDosis(entity.getString("fechaPrimerDosis"))
                .fechaSegundaDosis(entity.getString("fechaSegundaDosis"))
                .primeraDosisTotal(entity.getString("primeraDosisTotal"))
                .segundaDosisTotal(entity.getString("segundaDosisTotal"))
                .tipoVacuna(entity.getString("tipoVacuna"))
                .pais(entity.getString("pais"))
                .poblacionTotal(entity.getString("poblacionTotal"))
                .build();
    }
}
