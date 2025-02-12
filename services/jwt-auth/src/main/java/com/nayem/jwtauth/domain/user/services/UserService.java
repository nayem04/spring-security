package com.nayem.jwtauth.domain.user.services;

import com.nayem.jwtauth.common.base.BaseService;
import com.nayem.jwtauth.common.constants.Msg;
import com.nayem.jwtauth.common.exceptions.NotFoundException;
import com.nayem.jwtauth.common.exceptions.NullPasswordException;
import com.nayem.jwtauth.common.exceptions.UserAlreadyExistsException;
import com.nayem.jwtauth.common.utilities.ExceptionUtil;
import com.nayem.jwtauth.common.utilities.PageAttribute;
import com.nayem.jwtauth.domain.user.dtos.UserDto;
import com.nayem.jwtauth.domain.user.entities.User;
import com.nayem.jwtauth.domain.user.mappers.UserMapper;
import com.nayem.jwtauth.domain.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserDto>, UserDetailsService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

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
                .orElseThrow(() -> ExceptionUtil.getUsernameNotFoundException(username));
    }
}
