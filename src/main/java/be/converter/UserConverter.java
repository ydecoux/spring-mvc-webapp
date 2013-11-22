package be.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import be.domain.User;
import be.service.UserService;

public class UserConverter implements Converter<String, User> {
    private static final Logger LOGGER = LoggerFactory
	    .getLogger(UserConverter.class);
    @Autowired
    private UserService userService;

    public User convert(String source) {
	LOGGER.info("Trying to convert Long value {} to User", source);
	User user = this.userService.findById(Long.parseLong(source));
	if (user == null) {
	    throw new EntityNotFoundException();
	}
	return user;
    }
}
