package backend.myevent.MyEvent.news.domain.model.queries;

public record GetAllUserByNewsApiKeyQuery(String newsApiKey){
    public GetAllUserByNewsApiKeyQuery {
        if (newsApiKey == null || newsApiKey.isBlank()) {
            throw new IllegalArgumentException("API Key cannot be null or empty");
        }
    }
}
