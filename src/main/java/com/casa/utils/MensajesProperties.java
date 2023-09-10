package com.casa.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MensajesProperties {

    public static String MSG_CAMPOS_VACIOS;
    public static String MSG_SIEXISTENCIA;
    public static String MSG_NOEXISTENCIA;

    @Value("${msg.si.existencia}")
    public void setMSG_NOEXISTENCIA(String mensaje) {
        MSG_NOEXISTENCIA = mensaje;
    }
    @Value("${msg.si.existencia}")
    public void setMSG_SIEXISTENCIA(String mensaje) {
        MSG_SIEXISTENCIA = mensaje;
    }
    @Value("${msg.campos.vacios}")
    public void setMSG_CAMPOS_VACIOS(String mensaje) {
        MSG_CAMPOS_VACIOS = mensaje;
    }

}
