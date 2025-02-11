package com.nayem.databaseauth.domain.user.services;

import com.nayem.databaseauth.common.base.BaseService;
import com.nayem.databaseauth.common.constants.Msg;
import com.nayem.databaseauth.common.exceptions.NotFoundException;
import com.nayem.databaseauth.common.exceptions.NullPasswordException;
import com.nayem.databaseauth.common.exceptions.UserAlreadyExistsException;
import com.nayem.databaseauth.common.utilities.ExceptionUtil;
import com.nayem.databaseauth.common.utilities.PageAttribute;
import com.nayem.databaseauth.domain.user.dtos.UserDto;
import com.nayem.databaseauth.domain.user.entities.User;
import com.nayem.databaseauth.domain.user.mappers.UserMapper;
import com.nayem.databaseauth.domain.user.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements BaseService<UserDto>, UserDetailsService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserDto> search(String query, int page, int pageSize, Sort.Direction direction, String sortedFieldName, Boolean pageableLimit) {
        Page<User> userPage = (pageableLimit) ?
                userRepository.search(query, PageAttribute.getPageRequestWithSort(page, pageSize, direction, sortedFieldName)) :
                userRepository.search(query, PageAttribute.getPageRequestExactWithSort(page, pageSize, direction, sortedFieldName));
        return userPage.map(userMapper::map);
    }

    @Override
    public UserDto find(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.USER, id));
        return userMapper.map(user);
    }

    @Override
    public UserDto save(UserDto userDto) throws NotFoundException, NullPasswordException, UserAlreadyExistsException {
        User user = userMapper.map(null, userDto);
        user = userRepository.save(user);
        return userMapper.map(user);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) throws NotFoundException, NullPasswordException, UserAlreadyExistsException {
        User user = userRepository.findById(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.USER, id));
        user = userMapper.map(user, userDto);
        user = userRepository.save(user);
        return userMapper.map(user);
    }

    @Override
    public String delete(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.USER, id));
        userRepository.delete(user);
        return Msg.Entity.USER + Msg.Response.DELETE;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not found user with username: " + username));
    }
}
