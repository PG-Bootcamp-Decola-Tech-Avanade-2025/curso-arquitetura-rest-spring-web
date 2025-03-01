package pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.exceptions;

import java.util.List;

public class CompositeValidationException extends RuntimeException {
    private List<String> messages;

    public CompositeValidationException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public String getMessage() {
        return "Exception messages: " + String.join(", ", this.messages.stream().map(String::toLowerCase).toList()) + ".";
    }
}
