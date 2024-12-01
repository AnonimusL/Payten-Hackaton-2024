package com.payten.hacka.notification_service.mapper;

public interface IMapper<A, B> {
    B toDto(A input1);
    A fromDto(B input2);
}
