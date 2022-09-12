package com.paymybuddy.mapper;



public interface Mapper<E, D> {
    E toEntity(D dto);

    D toDTO(E entity);
}
