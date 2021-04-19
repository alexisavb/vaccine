package org.vaccine.world.application.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VaccineRegistrationRequest {

    private String fecha;
    private String primeraDosis;
    private String segundaDosis;
    private String tipoVacuna;
    private String pais;

}
