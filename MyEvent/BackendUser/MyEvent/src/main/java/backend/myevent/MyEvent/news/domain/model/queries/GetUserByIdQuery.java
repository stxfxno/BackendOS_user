package backend.myevent.MyEvent.news.domain.model.queries;

public record GetUserByIdQuery(Long id) {
    public GetUserByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
