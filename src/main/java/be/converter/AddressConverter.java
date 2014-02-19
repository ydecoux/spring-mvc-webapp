package be.converter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import be.domain.Address;
import be.service.UserService;

@Component
public class AddressConverter implements Converter<String, Address> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressConverter.class);
    @Autowired
    private UserService userService;

    @Override
    public Address convert(String source) {
        if (!StringUtils.isBlank(source)) {
            Address address = this.userService.findAddress(Long.parseLong(source));
            if (address == null) {
                throw new EntityNotFoundException();
            }
            LOGGER.info("Converted address " + address);
            return address;
        }
        LOGGER.info("Unable to convert {} to {}", source, Address.class);
        return null;
    }
}
