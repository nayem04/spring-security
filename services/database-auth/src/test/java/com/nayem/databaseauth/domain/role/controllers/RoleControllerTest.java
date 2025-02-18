package com.nayem.databaseauth.domain.role.controllers;

import com.nayem.databaseauth.common.constants.Msg;
import com.nayem.databaseauth.common.routing.Router;
import com.nayem.databaseauth.domain.role.dtos.RoleDto;
import com.nayem.databaseauth.domain.role.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoleController.class)
class RoleControllerTest {
    @MockitoBean
    RoleService roleService;

    @Autowired
    MockMvc mockMvc;

    private final Long id = 1L;
    private final Date created = new Date();
    private final Date lastUpdated = new Date();
    private final String description = "Role Admin";
    private final String label = "Admin";
    private final String name = "ROLE_ADMIN";

    private final RoleDto roleDto = new RoleDto();

    @BeforeEach
    void setUp() {
        roleDto.setId(id);
        roleDto.setCreated(created);
        roleDto.setLastUpdated(lastUpdated);
        roleDto.setDescription(description);
        roleDto.setLabel(label);
        roleDto.setName(name);
    }

    /*@WithMockUser
    Mock Security Context: If you're using Spring Security's MockUser*/
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testSearch() throws Exception {
        // Arrange
        String query = "ROLE_ADMIN";
        int page = 0;
        int pageSize = 10;
        Sort.Direction direction = Sort.Direction.DESC;
        String sortedFieldName = "id";
        Boolean pageableLimit = true;

        Page<RoleDto> roleDtoPage = new PageImpl<>(List.of(roleDto));

        when(roleService.search(query, page, pageSize, direction, sortedFieldName, pageableLimit)).thenReturn(roleDtoPage);

        // Act && Assert
        mockMvc.perform(get(Router.Role.GET_ROLES)
                        .param("query", query)
                        .param("page", String.valueOf(page))
                        .param("page_size", String.valueOf(pageSize))
                        .param("direction", direction.name())
                        .param("sorted_field_name", sortedFieldName)
                        .param("pageable_limit", pageableLimit.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").value(id))
                .andExpect(jsonPath("$.content[0].created").value(created.toInstant().toString().replaceFirst("Z", "+00:00")))
                .andExpect(jsonPath("$.content[0].last_updated").value(lastUpdated.toInstant().toString().replaceFirst("Z", "+00:00")))
                .andExpect(jsonPath("$.content[0].description").value(description))
                .andExpect(jsonPath("$.content[0].label").value(label))
                .andExpect(jsonPath("$.content[0].name").value(name));

        verify(roleService, times(1)).search(query, page, pageSize, direction, sortedFieldName, pageableLimit);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testFind() throws Exception {
        when(roleService.find(id)).thenReturn(roleDto);

        // Act && Assert
        mockMvc.perform(get(Router.Role.GET_ROLE, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.created").value(created.toInstant().toString().replaceFirst("Z", "+00:00")))
                .andExpect(jsonPath("$.last_updated").value(lastUpdated.toInstant().toString().replaceFirst("Z", "+00:00")))
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.label").value(label))
                .andExpect(jsonPath("$.name").value(name));

        verify(roleService, times(1)).find(id);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreate() throws Exception {
        when(roleService.save(any(RoleDto.class))).thenReturn(roleDto);

        String requestBody = String.format(
                "{\"description\":\"%s\",\"label\":\"%s\",\"name\":\"%s\"}",
                description, label, name
        );

        // Act & Assert
        mockMvc.perform(post(Router.Role.CREATE_ROLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.created").isNotEmpty())
                .andExpect(jsonPath("$.last_updated").isNotEmpty())
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.label").value(label))
                .andExpect(jsonPath("$.name").value(name));

        verify(roleService, times(1)).save(any(RoleDto.class));
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testUpdate() throws Exception {
        // Arrange
        RoleDto updatedRoleDto = new RoleDto();
        updatedRoleDto.setId(id);
        updatedRoleDto.setCreated(created);
        updatedRoleDto.setLastUpdated(lastUpdated);
        updatedRoleDto.setDescription("UpdatedDescription");
        updatedRoleDto.setLabel("UpdatedLabel");
        updatedRoleDto.setName("UpdatedName");

        when(roleService.update(eq(id), any(RoleDto.class))).thenReturn(updatedRoleDto);

        String requestBody = "{\"description\":\"UpdatedDescription\",\"label\":\"UpdatedLabel\",\"name\":\"UpdatedName\"}";

        // Act & Assert
        mockMvc.perform(patch(Router.Role.UPDATE_ROLE, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.created").isNotEmpty())
                .andExpect(jsonPath("$.last_updated").isNotEmpty())
                .andExpect(jsonPath("$.description").value("UpdatedDescription"))
                .andExpect(jsonPath("$.label").value("UpdatedLabel"))
                .andExpect(jsonPath("$.name").value("UpdatedName"));

        verify(roleService, times(1)).update(eq(id), any(RoleDto.class));
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDelete() throws Exception {
        String successMessage = Msg.Entity.USER + Msg.Response.DELETE;
        when(roleService.delete(id)).thenReturn(successMessage);

        // Act & Assert
        mockMvc.perform(delete(Router.Role.DELETE_ROLE, id)
                        .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().string(successMessage));

        verify(roleService, times(1)).delete(id);
    }
}