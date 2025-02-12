package com.nayem.databaseauth.domain.user.mappers;

import com.nayem.databaseauth.common.base.BaseMapper;
import com.nayem.databaseauth.common.constants.Msg;
import com.nayem.databaseauth.common.enums.RoleEnum;
import com.nayem.databaseauth.common.exceptions.NotFoundException;
import com.nayem.databaseauth.common.exceptions.NullPasswordException;
import com.nayem.databaseauth.common.exceptions.UserAlreadyExistsException;
import com.nayem.databaseauth.common.utilities.ExceptionUtil;
import com.nayem.databaseauth.common.utilities.PasswordUtil;
import com.nayem.databaseauth.domain.role.entities.Role;
import com.nayem.databaseauth.domain.role.repositories.RoleRepository;
import com.nayem.databaseauth.domain.user.dtos.UserDto;
import com.nayem.databaseauth.domain.user.entities.User;
import com.nayem.databaseauth.domain.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper implements BaseMapper<User, UserDto> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setCreated(user.getCreated());
        userDto.setLastUpdated(user.getLastUpdated());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setUsername(user.getUsername());
        userDto.setRoleIds(
                user.getRoles().stream().map(Role::getId).collect(Collectors.toSet())
        );
        return userDto;
    }

    @Override
    public User map(User user, UserDto userDto) throws NotFoundException, NullPasswordException, UserAlreadyExistsException {
        if (user == null) {
            if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
                throw ExceptionUtil.getUserAlreadyExistsException("Username", userDto.getUsername());
            }
            user = new User();
        }
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(PasswordUtil.encryptPassword(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUsername(userDto.getUsername());
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userDto.getRoleIds()));
        if (roles.isEmpty()) {
            Role role = roleRepository.findById(RoleEnum.ROLE_USER.getValue().longValue())
                    .orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.ROLE,
                            RoleEnum.ROLE_USER.getValue().longValue()));
            roles.add(role);
        }
        user.setRoles(roles);
        return user;
    }
}