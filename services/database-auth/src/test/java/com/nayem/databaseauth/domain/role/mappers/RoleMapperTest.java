package com.nayem.databaseauth.domain.role.mappers;

import com.nayem.databaseauth.domain.role.dtos.RoleDto;
import com.nayem.databaseauth.domain.role.entities.Role;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class RoleMapperTest {
    private final RoleMapper roleMapper = new RoleMapper();

    @Test
    void testMapRoleToRoleDto() {
        Role role = new Role();
        role.setId(1L);
        Date createdDate = new Date();
        Date lastUpdatedDate = new Date();
        role.setCreated(createdDate);
        role.setLastUpdated(lastUpdatedDate);
        role.setDescription("Admin Role");
        role.setLabel("Admin");
        role.setName("ROLE_ADMIN");

        RoleDto roleDto = roleMapper.map(role);

        assertThat(roleDto).isNotNull();
        assertThat(roleDto.getId()).isEqualTo(role.getId());
        assertThat(roleDto.getCreated()).isEqualTo(role.getCreated());
        assertThat(roleDto.getLastUpdated()).isEqualTo(role.getLastUpdated());
        assertThat(roleDto.getDescription()).isEqualTo(role.getDescription());
        assertThat(roleDto.getLabel()).isEqualTo(role.getLabel());
        assertThat(roleDto.getName()).isEqualTo(role.getName());
    }

    @Test
    void testMapRoleDtoToRole() {
        RoleDto roleDto = new RoleDto();
        roleDto.setDescription("User Role");
        roleDto.setLabel("USER");
        roleDto.setName("User");

        Role role = roleMapper.map(null, roleDto);

        assertThat(role).isNotNull();
        assertThat(role.getDescription()).isEqualTo(roleDto.getDescription());
        assertThat(role.getLabel()).isEqualTo(roleDto.getLabel());
        assertThat(role.getName()).isEqualTo(roleDto.getName());
    }
}