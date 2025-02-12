package com.nayem.jwtauth.domain.role.services;

import com.nayem.jwtauth.common.base.BaseService;
import com.nayem.jwtauth.common.constants.Msg;
import com.nayem.jwtauth.common.exceptions.NotFoundException;
import com.nayem.jwtauth.common.utilities.ExceptionUtil;
import com.nayem.jwtauth.common.utilities.PageAttribute;
import com.nayem.jwtauth.domain.role.dtos.RoleDto;
import com.nayem.jwtauth.domain.role.entities.Role;
import com.nayem.jwtauth.domain.role.mappers.RoleMapper;
import com.nayem.jwtauth.domain.role.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements BaseService<RoleDto> {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Override
    public Page<RoleDto> search(String query, int page, int pageSize, Sort.Direction direction, String sortedFieldName, Boolean pageableLimit) {
        Page<Role> rolePage = (pageableLimit) ?
                roleRepository.search(query, PageAttribute.getPageRequestWithSort(page, pageSize, direction, sortedFieldName)) :
                roleRepository.search(query, PageAttribute.getPageRequestExactWithSort(page, pageSize, direction, sortedFieldName));
        return rolePage.map(roleMapper::map);
    }

    @Override
    public RoleDto find(Long id) throws NotFoundException {
        Role role = roleRepository.findById(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.ROLE, id));
        return roleMapper.map(role);
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = roleMapper.map(null, roleDto);
        role = roleRepository.save(role);
        return roleMapper.map(role);
    }

    @Override
    public RoleDto update(Long id, RoleDto roleDto) throws NotFoundException {
        Role role = roleRepository.findById(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.ROLE, id));
        role = roleMapper.map(role, roleDto);
        role = roleRepository.save(role);
        return roleMapper.map(role);
    }

    @Override
    public String delete(Long id) throws NotFoundException {
        Role role = roleRepository.findById(id).orElseThrow(() -> ExceptionUtil.getNotFoundException(Msg.Entity.ROLE, id));
        roleRepository.delete(role);
        return Msg.Entity.ROLE + Msg.Response.DELETE;
    }
}
