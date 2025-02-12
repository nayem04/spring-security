package com.nayem.jwtauth.common.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface BaseService<D extends BaseDto> {
    Page<D> search(String query, int page, int pageSize, Sort.Direction direction, String sortedFieldName, Boolean pageableLimit);

    D find(Long id) throws Exception;

    D save(D dto) throws Exception;

    D update(Long id, D dto) throws Exception;

    String delete(Long id) throws Exception;
}
