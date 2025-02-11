package com.nayem.databaseauth.common.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

public interface BaseController<D extends BaseDto> {
    ResponseEntity<Page<D>> search(String query, int page, int pageSize, Sort.Direction direction, String sortedFieldName, Boolean pageableLimit);

    ResponseEntity<D> find(Long id) throws Exception;

    ResponseEntity<D> create(D dto) throws Exception;

    ResponseEntity<D> update(Long id, D dto) throws Exception;

    ResponseEntity<String> delete(Long id) throws Exception;
}
