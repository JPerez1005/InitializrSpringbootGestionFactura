package com.api.gestion.api_gestion_factura.rest;

import com.api.gestion.api_gestion_factura.constantes.FacturaConstantes;
import com.api.gestion.api_gestion_factura.service.UserService;
import com.api.gestion.api_gestion_factura.util.FacturaUtils;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */

@RestController
@RequestMapping(path="/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/signup")
    public ResponseEntity<String> registrarUsuario
        (@RequestBody(required=true)//si los datos son incompletos falla
                Map<String,String> requestMap/*obtenemos los datos y los guardamos ahí*/){
            try{
                //obtenemos los datos y los mandamos a la interfaz de service
                //que luego ella llama un metodo o funcion de sus clases implementadas
                return userService.signUp(requestMap);
            }catch(Exception exception){
                exception.printStackTrace();
            }
            return FacturaUtils.getResponseEntity
                (FacturaConstantes.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
