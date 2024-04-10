package com.api.gestion.api_gestion_factura.service.impl;

import com.api.gestion.api_gestion_factura.constantes.FacturaConstantes;
import com.api.gestion.api_gestion_factura.dao.UserDAO;
import com.api.gestion.api_gestion_factura.pojo.User;
import com.api.gestion.api_gestion_factura.service.UserService;
import com.api.gestion.api_gestion_factura.util.FacturaUtils;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 * 
 * En resumen esta clase lo que hace es registrar un usuario,
 * si los datos no son completos genera error,la funcion que mira si
 * los datos estan completos es @validateSignUpMap pero no puede
 * hacer esto si es que no sabe que datos validar por eso se creó
 * la funcion @getUserFromMap para obtener los detos de la api
 * 
 * la funcion encargada de ubnir todo es @signUp que primero valida
 * los datos mira si estan correctos, despues mira dentro del repositorio
 * haciendo una busqueda personalizada por el email y si no exista
 * cambia los datos a la entidad @User por medio de la funcion getUserFromMap
 * y guarda los datos en el DAO que en sí es un repositorio
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;
    
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Registro interno de un usuario {}",requestMap);
        try {
            if(validateSignUpMap(requestMap))//si el metodo de validacion es true,
                //entonces empieza a guardar
            {
                //ahora buscamos el usuario en el repositorio
                User user=userDAO.findByEmail(requestMap.get("email"));
                if(Objects.isNull(user))//si la busqueda es nula, es porque ese usuario no existe
                {
                    //entonces empezamos a crear ese usuario en el repositorio a partir del
                    //requestMap que son los datos que nos enviaron convertidos en el metodo
                    //getUserFromMap
                    userDAO.save(getUserFromMap(requestMap));
                    
                    //Y retornamos una respuesta HTTP con un estado
                    return FacturaUtils.getResponseEntity("Usuario Registrado", HttpStatus.CREATED);
                }else{
                    //si resulta que no es nulo, entonces existe, y mandamos el mensaje
                    return FacturaUtils.getResponseEntity("El usuario con ese email ya existe", HttpStatus.BAD_REQUEST);
                }
            }else{
                //si los datos no estan completos entonces llamados a la constante
                //de factura constantes para decirle al usuario datos invalidos o incompletos
                //a la vez tambien llamamos al factura utils para dar el estado correspondiente
                //de los datos http
                return FacturaUtils.getResponseEntity(FacturaConstantes.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //al final si nada se logró pues le avisamos al usuario
        return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private boolean validateSignUpMap(Map<String, String> requestMap){
        if(requestMap.containsKey("nombre") && requestMap.containsKey("numeroDeContacto")
                && requestMap.containsKey("email")&& requestMap.containsKey("password"))
        //si todos los datos estan llenos, entonces el programa devuelve un true
        {
            return true;
        }else{
            //si los datos no estan llenos, entonces no guarda nada
            return false;
        }
    }
    
    //este metodo nos ayuda a obtener los datos y convertirlos
    private User getUserFromMap(Map<String, String> requestMap){
        User user=new User();//creamos el usuario
        
        //ahora en el requestMap que nos entrega la appWeb
        //recibimos los datos clave-valor, en este caso 
        //apartir de la clave de nombre recibimos el valor correspondiente
        user.setNombre(requestMap.get("nombre"));
        user.setNumeroDeContacto(requestMap.get("numeroDeContacto"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
    
}
