package com.inditex.ecom.appwebcustomerloyaltyengine.api.dto;

import com.inditex.ecom.appwebcustomerloyaltyengine.api.common.BaseObject;

/**
 * The Class KeyValue.
 */
public class KeyValue extends BaseObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The key. */
	private String key;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new key value.
	 */
	public KeyValue() {
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 * 
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		KeyValue other = (KeyValue) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("[KEY=");
		builder.append(key);
		builder.append(", VALUE=");
		builder.append(value);
		builder.append("]");

		return builder.toString();
	}

	/**
	 * To string json.
	 * 
	 * @return the string
	 */
	public String toStringJSON() {
		String saltoDeLinea = "\n";
		String separator = ",";

		StringBuilder builder = new StringBuilder();

		builder.append("KeyValue");
		builder.append(saltoDeLinea);
		builder.append("{");
		builder.append(saltoDeLinea);
		builder.append("\t\"key\": ");
		builder.append(key);
		builder.append(separator);
		builder.append(saltoDeLinea);
		builder.append("\t\"value\": ");
		builder.append(value);

		builder.append(saltoDeLinea);
		builder.append("}");
		builder.append(saltoDeLinea);

		return builder.toString();
	}

}
