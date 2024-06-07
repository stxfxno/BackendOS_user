package backend.myevent.MyEvent.news.domain.model.queries;

public record GetUserByCorreoQuery(String correo) {
    public GetUserByCorreoQuery {
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("Correo cannot be null or empty");
        }
    }
}
