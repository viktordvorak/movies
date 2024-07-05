package cz.dvorakv.service;

import cz.dvorakv.dto.UserDto;
import cz.dvorakv.entity.repository.filter.UserFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  extends UserDetailsService {

    UserDto createUser(UserDto dto);

    UserDto updateUser(UserDto dto, Long id);

    List<UserDto> getUsers(UserFilter filter);

    UserDto getUser(Long id);

    UserDto deleteUser(Long id);

}
