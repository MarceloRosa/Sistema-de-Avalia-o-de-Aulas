package br.unifor.saa.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JsonBuilder<T> {

	public JsonBuilder() {
		super();
	}

	public T parser(JSONObject jsonObject) {
		T banda = createEntidade();
		Set<String> keySet = jsonObject.keySet();
		for (String property : keySet) {
			try {
				BeanUtils
						.setProperty(banda, property, jsonObject.get(property));
			} catch (IllegalAccessException | InvocationTargetException
					| JSONException e) {
				e.printStackTrace();
			}
		}
		return banda;
	}

	public List<T> parser(JSONArray jsonArray) {
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			list.add(parser(jsonObject));
		}
		return list;
	}

	protected abstract T createEntidade();

}