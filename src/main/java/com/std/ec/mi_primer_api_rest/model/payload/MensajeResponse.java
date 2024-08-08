package com.std.ec.mi_primer_api_rest.model.payload;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class MensajeResponse implements Serializable {

    private String mensaje;
    private Object object;
}
