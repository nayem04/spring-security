package com.nayem.jwtauth.domain.role.mappers;

import com.nayem.jwtauth.common.base.BaseMapper;
import com.nayem.jwtauth.domain.role.dtos.RoleDto;
import com.nayem.jwtauth.domain.role.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements BaseMapper<Role, RoleDto> {
    @Override
    public RoleDto map(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setCreated(role.getCreated());
        roleDto.setLastUpdated(role.getLastUpdated());
        roleDto.setDescription(role.getDescription());
        roleDto.setLabel(role.getLabel());
        roleDto.setName(role.getName());
        return roleDto;
    }

    @Override
    public Role map(Role role, RoleDto roleDto) {
        if (role == null) {
            role = new Role();
        }
        role.setDescription(roleDto.getDescription());
        role.setLabel(roleDto.getLabel());
        role.setName(roleDto.getName());
        return role;
    }
}
