package dataStructure;

import java.io.Serializable;

/**
 * Entry Abstract Data Type 
 * Includes description of general methods to be implemented by an entry.
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value  
 */

public interface Entry<K,V> extends Serializable{
	/**
	 * Returns the key in the entry.
	 * @return key in the entry
	 */
    K getKey( );

	/**
	 * Returns the value in the entry.
	 * @return value in the entry
	 */
    V getValue( );

	/**
	 * set the key value with value <code>key</code>
	 * @param key new key
	 */
    void setKey(K key);

	/**
	 * set the value of the entry with value <code>value</code>
	 * @param value new value
	 */
	void setValue(V value);

}
