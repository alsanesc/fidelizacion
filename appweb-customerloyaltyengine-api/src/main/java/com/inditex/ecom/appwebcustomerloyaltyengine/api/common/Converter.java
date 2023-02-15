package com.inditex.ecom.appwebcustomerloyaltyengine.api.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The Class Converter.
 */
public class Converter {

	/** The Constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Converter.class);

	/**
	 * Convert to json.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param clase
	 *            the clase
	 * @return the serializable
	 */
	public static <T> String convertToJSON(T clase) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		LOGGER.trace("convertToJSON: " + gson.toJson(clase));

		return gson.toJson(clase);
	}

	/**
	 * Convert to object.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            the json
	 * @param clase
	 *            the clase
	 * @return the object
	 */
	public static <T> Object convertToObject(String json, T clase) {
		Gson gson = new Gson();

		LOGGER.trace("convertToObject: ".concat(gson.fromJson(json, clase.getClass()).toString()));

		return gson.fromJson(json, clase.getClass());
	}

}
