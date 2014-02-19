package be.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ComposedValidator implements Validator {

    private List<Validator> validators = new ArrayList<Validator>();

    public boolean supports(Class<?> clazz) {
	return true;
    }

    public void validate(Object target, Errors errors) {
	for (Validator validator : this.validators) {
	    validator.validate(target, errors);
	}

    }

    public void addValidator(Validator validator) {
	this.validators.add(validator);
    }

}
