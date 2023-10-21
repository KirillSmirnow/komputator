package komputator.server.repository;

import java.util.UUID;

public interface Repository<T extends Identifiable<UUID>> {

    void save(T object);

    T get(UUID id);
}
