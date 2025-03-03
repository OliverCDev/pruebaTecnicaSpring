package com.olivercdev.pruebaTecnica.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClConsultaPagoOrdenesClienteRequest {
    
    public String pCodigo;
    public String pIdCliente;
    public String pIdOrden;
}
