package com.filmchoice.mapper;
public interface Converter<C,T> {
  C converterElementoDTO(T elemento);
  T converterElementoEntidade(C elemento);
}
