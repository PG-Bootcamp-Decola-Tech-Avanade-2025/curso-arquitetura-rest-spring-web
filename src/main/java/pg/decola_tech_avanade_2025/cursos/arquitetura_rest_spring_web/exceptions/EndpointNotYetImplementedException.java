package pg.decola_tech_avanade_2025.cursos.arquitetura_rest_spring_web.exceptions;

public class EndpointNotYetImplementedException extends RuntimeException {
    private final String message;

    public EndpointNotYetImplementedException(String messages) {
        this.message = messages;
    }

    public String getMessage() {
        return message;
    }
}
