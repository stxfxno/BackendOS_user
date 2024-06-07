package backend.myevent.MyEvent.news.domain.model.queries;

public record GetUserByNameAndSurnameQuery(String name, String surname){
    public GetUserByNameAndSurnameQuery {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Surname cannot be null or empty");
        }
    }

}

