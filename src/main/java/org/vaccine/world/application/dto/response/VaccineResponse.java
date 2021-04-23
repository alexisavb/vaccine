package org.vaccine.world.application.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VaccineResponse {

    private String id;
    private String pais;
    private String fechaPrimerDosis;
    private String fechaSegundaDosis;
    private String primeraDosis;
    private String segundaDosis;
    private String primeraDosisTotal;
    private String segundaDosisTotal;
    private String poblacionTotal;
    private String tipoVacuna;

}
