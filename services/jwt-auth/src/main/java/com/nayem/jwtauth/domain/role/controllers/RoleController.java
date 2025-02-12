package com.nayem.jwtauth.domain.role.controllers;

import com.nayem.jwtauth.common.base.BaseController;
import com.nayem.jwtauth.common.exceptions.NotFoundException;
import com.nayem.jwtauth.common.routing.Router;
import com.nayem.jwtauth.domain.role.dtos.RoleDto;
import com.nayem.jwtauth.domain.role.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoleController implements BaseController<RoleDto> {
    private final RoleService roleService;

    @GetMapping(Router.Role.GET_ROLES)
    @Override
    public ResponseEntity<Page<RoleDto>> search(@RequestParam(name = "query", defaultValue = "") String query,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
                                                @RequestParam(name = "direction", defaultValue = "DESC") Sort.Direction direction,
                                                @RequestParam(name = "sorted_field_name", defaultValue = "id") String sortedFieldName,
                                                @RequestParam(name = "pageable_limit", defaultValue = "true") Boolean pageableLimit) {
        return ResponseEntity.ok(roleService.search(query, page, pageSize, direction, sortedFieldName, pageableLimit));
    }

    @GetMapping(Router.Role.GET_ROLE)
    @Override
    public ResponseEntity<RoleDto> find(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(roleService.find(id));
    }

    @PostMapping(Router.Role.CREATE_ROLE)
    @Override
    public ResponseEntity<RoleDto> create(@Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.save(roleDto));
    }

    @PatchMapping(Router.Role.UPDATE_ROLE)
    @Override
    public ResponseEntity<RoleDto> update(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) throws NotFoundException {
        return ResponseEntity.ok(roleService.update(id, roleDto));
    }

    @DeleteMapping(Router.Role.DELETE_ROLE)
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(roleService.delete(id));
    }
}
