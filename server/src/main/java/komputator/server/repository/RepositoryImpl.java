package komputator.server.repository;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RepositoryImpl<T extends Identifiable<UUID>> implements Repository<T> {

    private final Map<UUID, T> objectsById = new ConcurrentHashMap<>();

    @Override
    public void save(T object) {
        objectsById.put(object.getId(), object);
    }

    @Override
    public T get(UUID id) {
        return objectsById.get(id);
    }
}
