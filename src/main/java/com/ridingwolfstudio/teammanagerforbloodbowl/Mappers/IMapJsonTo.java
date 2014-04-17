package com.ridingwolfstudio.teammanagerforbloodbowl.Mappers;

import org.json.JSONObject;

public interface IMapJsonTo<T> {
    T Create(JSONObject json);
}
