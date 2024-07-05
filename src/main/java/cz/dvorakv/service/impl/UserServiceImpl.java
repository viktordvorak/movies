package cz.dvorakv.service.impl;

import cz.dvorakv.dto.UserDto;
import cz.dvorakv.dto.mapper.UserMapper;
import cz.dvorakv.entity.UserEntity;
import cz.dvorakv.entity.repository.UserRepository;
import cz.dvorakv.entity.repository.filter.UserFilter;
import cz.dvorakv.entity.repository.specification.UserSpecification;
import cz.dvorakv.service.UserService;
import cz.dvorakv.service.exceptions.DuplicateEmailException;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto dto) {
        try {
            val userEntity = new UserEntity();
            userEntity.setEmail(dto.getEmail());
            userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
            userEntity.setAdmin(dto.isAdmin());

            val saveEntity = repository.save(userEntity);

            val savedDto = new UserDto();
            savedDto.setUserId(saveEntity.getUserId());
            savedDto.setEmail(saveEntity.getEmail());
            savedDto.setAdmin(savedDto.isAdmin());
            return savedDto;
        } catch (final DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public UserDto updateUser(UserDto dto, Long id) {
        try {
            val userEntity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person not found"));
            userEntity.setAdmin(dto.isAdmin());
            userEntity.setEmail(dto.getEmail());
            userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

            val saveEntity = repository.save(userEntity);

            val savedDto = new UserDto();
            savedDto.setUserId(saveEntity.getUserId());
            savedDto.setEmail(saveEntity.getEmail());
            savedDto.setAdmin(savedDto.isAdmin());
            return savedDto;
        } catch (final DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public List<UserDto> getUsers(final UserFilter filter) {
        val userSpecification = new UserSpecification(filter);
        val entities = repository.findAll(userSpecification);
        return entities.stream()
                .map(e -> mapper.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(final Long id) {
        val userEntity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        val userDto = new UserDto();
        userDto.setUserId(userEntity.getUserId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setAdmin(userEntity.isAdmin());
        return userDto;
    }

    @Override
    public UserDto deleteUser(Long id) {
        val entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.deleteById(id);
        return mapper.toDto(entity);

    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " nenalezen"));
    }


}
