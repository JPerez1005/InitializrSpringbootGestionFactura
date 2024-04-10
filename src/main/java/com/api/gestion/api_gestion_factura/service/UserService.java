package com.api.gestion.api_gestion_factura.service;

import java.util.Map;
import org.springframework.http.ResponseEntity;

/**
 * @author perez
 */
public interface UserService {
    
//    El método signUp toma un parámetro de tipo Map<String, String>
//    llamado requestMap. Este mapa se usa para pasar los datos de la
//    solicitud al método signUp, lo que puede contener información
//    necesaria para el proceso de registro, como nombres de usuario,
//    contraseñas, correos electrónicos, etc.
    ResponseEntity<String> signUp(Map<String,String> requestMap);
    
}
