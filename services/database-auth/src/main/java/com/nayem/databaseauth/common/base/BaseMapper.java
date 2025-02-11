package com.nayem.databaseauth.common.base;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {
    D map(E entity);

    E map(E entity, D dto) throws Exception;
}
