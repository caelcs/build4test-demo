import java.util.UUID;

public interface PersonRepository {
    Person findById(UUID id);
}
