package com.nayem.databaseauth.domain.role.services;

import com.nayem.databaseauth.common.constants.Msg;
import com.nayem.databaseauth.common.exceptions.NotFoundException;
import com.nayem.databaseauth.common.utilities.PageAttribute;
import com.nayem.databaseauth.domain.role.dtos.RoleDto;
import com.nayem.databaseauth.domain.role.entities.Role;
import com.nayem.databaseauth.domain.role.mappers.RoleMapper;
import com.nayem.databaseauth.domain.role.repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
    @Mock
    private RoleMapper roleMapper;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role role;
    private RoleDto roleDto;
    private Date createdDate = new Date();
    ;
    private Date lastUpdatedDate = new Date();
    ;


    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1L);
        role.setCreated(createdDate);
        role.setLastUpdated(lastUpdatedDate);
        role.setDescription("Role Admin");
        role.setLabel("Admin");
        role.setName("ROLE_ADMIN");

        roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setCreated(createdDate);
        roleDto.setLastUpdated(lastUpdatedDate);
        roleDto.setDescription("Role Admin");
        roleDto.setLabel("Admin");
        roleDto.setName("ROLE_ADMIN");
    }

    @Test
    void testSearch() {
        Page<Role> rolePage = new PageImpl<>(Collections.singletonList(role));
        PageRequest pageRequest = PageAttribute.getPageRequestWithSort(0, 10, Sort.Direction.DESC, "id");

        when(roleRepository.search("ROLE_ADMIN", pageRequest)).thenReturn(rolePage);
        when(roleMapper.map(role)).thenReturn(roleDto);

        Page<RoleDto> result = roleService.search("ROLE_ADMIN", 0, 10, Sort.Direction.DESC, "id", true);

        assertNotNull(result, "Result should not be null");
        assertEquals(1L, result.getContent().getFirst().getId(), "ID should match");
        assertEquals(createdDate, result.getContent().getFirst().getCreated(), "Created date should match");
        assertEquals(lastUpdatedDate, result.getContent().getFirst().getLastUpdated(), "Last updated date should match");
        assertEquals("Role Admin", result.getContent().getFirst().getDescription(), "Description should match");
        assertEquals("Admin", result.getContent().getFirst().getLabel(), "Label should match");
        assertEquals("ROLE_ADMIN", result.getContent().getFirst().getName(), "Name should match");

        verify(roleRepository, times(1)).search("ROLE_ADMIN", pageRequest);
        verify(roleMapper, times(1)).map(role);
    }

    @Test
    void testFindSuccess() throws NotFoundException {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(roleMapper.map(role)).thenReturn(roleDto);

        RoleDto result = roleService.find(1L);

        assertNotNull(result, "Result should not be null");
        assertEquals(1L, result.getId(), "ID should match");
        assertEquals(createdDate, result.getCreated(), "Created date should match");
        assertEquals(lastUpdatedDate, result.getLastUpdated(), "Last updated date should match");
        assertEquals("Role Admin", result.getDescription(), "Description should match");
        assertEquals("Admin", result.getLabel(), "Label should match");
        assertEquals("ROLE_ADMIN", result.getName(), "Name should match");

        verify(roleMapper, times(1)).map(role);
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void testFindNotFound() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> roleService.find(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Could not find 'Role' with id : 1");

        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(roleMapper.map(null, roleDto)).thenReturn(role);
        when(roleRepository.save(role)).thenReturn(role);
        when(roleMapper.map(role)).thenReturn(roleDto);

        RoleDto result = roleService.save(roleDto);

        assertNotNull(result, "Result should not be null");
        assertEquals(1L, result.getId(), "ID should match");
        assertEquals(createdDate, result.getCreated(), "Created date should match");
        assertEquals(lastUpdatedDate, result.getLastUpdated(), "Last updated date should match");
        assertEquals("Role Admin", result.getDescription(), "Description should match");
        assertEquals("Admin", result.getLabel(), "Label should match");
        assertEquals("ROLE_ADMIN", result.getName(), "Name should match");

        verify(roleMapper, times(1)).map(null, roleDto);
        verify(roleRepository, times(1)).save(role);
        verify(roleMapper, times(1)).map(role);
    }

    @Test
    void testUpdateSuccess() throws NotFoundException {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(roleMapper.map(role, roleDto)).thenReturn(role);
        when(roleRepository.save(role)).thenReturn(role);
        when(roleMapper.map(role)).thenReturn(roleDto);

        RoleDto result = roleService.update(1L, roleDto);

        assertNotNull(result, "Result should not be null");
        assertEquals(1L, result.getId(), "ID should match");
        assertEquals(createdDate, result.getCreated(), "Created date should match");
        assertEquals(lastUpdatedDate, result.getLastUpdated(), "Last updated date should match");
        assertEquals("Role Admin", result.getDescription(), "Description should match");
        assertEquals("Admin", result.getLabel(), "Label should match");
        assertEquals("ROLE_ADMIN", result.getName(), "Name should match");

        verify(roleRepository, times(1)).findById(1L);
        verify(roleMapper, times(1)).map(role, roleDto);
        verify(roleRepository, times(1)).save(role);
        verify(roleMapper, times(1)).map(role);
    }

    @Test
    void testUpdateNotFound() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> roleService.update(1L, roleDto))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Could not find 'Role' with id : 1");

        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteSuccess() throws NotFoundException {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        doNothing().when(roleRepository).delete(role);

        String result = roleService.delete(1L);

        assertEquals(Msg.Entity.ROLE + Msg.Response.DELETE, result);

        verify(roleRepository, times(1)).findById(1L);
        verify(roleRepository, times(1)).delete(role);
    }

    @Test
    void testDeleteNotFound() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> roleService.delete(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Could not find 'Role' with id : 1");

        verify(roleRepository, times(1)).findById(1L);
    }
}