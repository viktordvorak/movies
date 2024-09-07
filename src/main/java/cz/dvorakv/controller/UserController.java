package cz.dvorakv.controller;

import cz.dvorakv.dto.UserDto;
import cz.dvorakv.entity.UserEntity;
import cz.dvorakv.entity.repository.filter.UserFilter;
import cz.dvorakv.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping({"/user", "/user/"})
    public UserDto createUser(final @RequestBody UserDto userDto) {
        return service.createUser(userDto);
    }

    @PutMapping({"/user/{id}", "/user/{id}/"})
    public UserDto updateUser(final @RequestBody UserDto userDto, final @PathVariable Long id) {
        return service.updateUser(userDto, id);
    }

    @GetMapping({"/user", "/user/"})
    public UserDto getCurrentUser() throws ServletException {
        try {
            val user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            val model = new UserDto();
            model.setUserId(user.getUserId());
            model.setEmail(user.getEmail());
            model.setAdmin(user.isAdmin());
            return model;
        } catch (final ClassCastException e) {
            throw new ServletException();
        }
    }

    @GetMapping({"/users", "/users/"})
    public List<UserDto> getUsers(final UserFilter filter)  {
        return service.getUsers(filter);
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable final Long id)  {
        return service.getUser(id);
    }

    @PostMapping({"/auth/login", "/auth/login/"})
    public UserDto login(final @RequestBody @Valid UserDto userDto, final HttpServletRequest req) throws ServletException {
        req.login(userDto.getEmail(), userDto.getPassword());

        val user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        val model = new UserDto();
        model.setUserId(user.getUserId());
        model.setEmail(user.getEmail());
        model.setAdmin(user.isAdmin());
        //logger.info("logged user: " + model.getEmail());
        return model;
    }

    @DeleteMapping({"auth/logout", "auth/logout/"})
    public String logout(final HttpServletRequest req) throws ServletException {
        req.logout();
        return "Uživatel odhlášen";
    }

    @DeleteMapping("/user/{id}")
    public UserDto delete(@PathVariable final Long id) throws ServletException {
        return service.deleteUser(id);
    }

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleServletException() {
    }

}
