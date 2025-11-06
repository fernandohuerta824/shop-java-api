package com.fernando.springboot.shop.api.shop.domain.mapper;

import java.util.List;
import java.util.Set;

public interface BaseMapper<E, D> {
    D toDto(E e);
    List<D> toListDto(List<E> e);
    Set<D> toSetDto(Set<D> e);
}