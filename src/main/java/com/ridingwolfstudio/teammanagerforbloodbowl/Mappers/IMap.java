package com.ridingwolfstudio.teammanagerforbloodbowl.Mappers;

public interface IMap<TSource, TDestination> {
    TDestination Map(TSource json);
}
