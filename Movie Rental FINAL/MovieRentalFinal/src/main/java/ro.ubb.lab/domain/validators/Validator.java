package ro.ubb.lab.domain.validators;

import javax.xml.bind.ValidationException;

/**
 * Created by horatiu on 13.03.2017.
 */
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
