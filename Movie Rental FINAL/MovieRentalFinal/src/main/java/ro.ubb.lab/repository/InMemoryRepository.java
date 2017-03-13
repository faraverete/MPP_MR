package ro.ubb.lab.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import ro.ubb.lab.domain.validators.ValidatorException;
import ro.ubb.lab_problems.domain.*;
import ro.ubb.lab_problems.domain.validators.*;

/**
 * Created by Ela on 3/6/2017.
 */
public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {

    private Map<ID, T> entities;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getID(), entity));
    }

    //-----Diana:
    @Override
    public Optional<T> delete(ID id) throws IllegalArgumentException
    {
        if(id==null)
        {
            throw new IllegalArgumentException("Id can't be null(delete).");
        }
        return Optional.ofNullable(entities.remove(id));
    }


    @Override
    public Optional<T> update(T entity) throws ValidatorException
    {
        if(entity==null)
        {
            throw new IllegalArgumentException("Entity can't be null(update).");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getID(),(k,v)->entity));
    }
}
