package pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.exceptions.CompositeValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Na implementação dessa classe, optei por divergir da implementação demonstrada.
    Alterei as exceções para funcionarem de forma um pouco mais genérica e retornando
    mensagens mais amigáveis.
*/

@ControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException e) {
        Map<String, String> bodyContent = new HashMap<>() {{
            put("Mensagem", e.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyContent);
    }

    @ExceptionHandler(CompositeValidationException.class)
    public ResponseEntity<Map<String, List<String>>> handleCompositeValidationException(CompositeValidationException e) {
        Map<String, List<String>> bodyContent = new HashMap<>() {{
            put("Mensagens", e.getMessages());
        }};
        return ResponseEntity.badRequest().body(bodyContent);
    }
}
