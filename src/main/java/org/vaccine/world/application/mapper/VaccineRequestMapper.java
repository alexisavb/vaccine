package org.vaccine.world.application.mapper;

import org.springframework.stereotype.Component;
import org.vaccine.world.application.dto.request.VaccineRegistrationRequest;
import org.vaccine.world.application.service.domain.Vaccine;

import java.util.function.Function;

@Component
public class VaccineRequestMapper implements Function<VaccineRegistrationRequest, Vaccine> {
    @Override
    public Vaccine apply(VaccineRegistrationRequest vaccineRegistrationRequest) {
        return Vaccine.builder()
                .primeraDosis(vaccineRegistrationRequest.getPrimeraDosis())
                .segundaDosis(vaccineRegistrationRequest.getSegundaDosis())
                .fechaPrimerDosis(vaccineRegistrationRequest.getFecha())
                .fechaSegundaDosis(vaccineRegistrationRequest.getFecha())
                .primeraDosisTotal("")
                .segundaDosisTotal("")
                .tipoVacuna(vaccineRegistrationRequest.getTipoVacuna())
                .pais(vaccineRegistrationRequest.getPais())
                .poblacionTotal("")
                .build();
    }
}
