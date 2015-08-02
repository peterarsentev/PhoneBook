package ru.parsentev.phone.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Map<Integer, User> users = new ConcurrentHashMap<Integer, User>();

	private final AtomicInteger ids = new AtomicInteger(0);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<User> list() {
		return users.values();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getById(@PathVariable String id) {
		return users.get(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		final int id = ids.incrementAndGet();
		user.setId(id);
		this.users.put(id, user);
		return user;
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public User update(@RequestBody User user) {
		this.users.put(user.getId(), user);
		return user;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public User delete(@RequestBody User user) {
		this.users.remove(user);
		return user;
	}
}
