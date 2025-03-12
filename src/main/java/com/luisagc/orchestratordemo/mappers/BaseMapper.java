package com.luisagc.orchestratordemo.mappers;

public interface BaseMapper <T, U> {
    public U map(T t);
}
