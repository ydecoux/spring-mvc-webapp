package be.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import be.domain.User;
import be.exception.WrongVersionException;

@Service
public class UserServiceImpl implements UserService {
    Map<Long, User> users = new HashMap<Long, User>();

    public UserServiceImpl() throws InterruptedException {
	save(new User("Raphael", "Dupond", "Raph"));
	save(new User("Tommy", "Bertrand", "Tom"));
	save(new User("Frederic", "Legrand", "Fred"));
    }

    public User findById(Long id) {
	return this.users.get(id);
    }

    public List<User> findAll() {
	return new ArrayList<User>(this.users.values());
    }

    public void save(User user) {
	if (user.getId() == null) {
	    Long id = System.currentTimeMillis();
	    user.setId(id);
	} else {
	    checkVersion(user);
	}
	this.users.put(user.getId(), user);
    }

    private void checkVersion(User user) {
	User original = findById(user.getId());
	if (user.getVersion() != original.getVersion()) {
	    throw new WrongVersionException();
	}
    }
}
