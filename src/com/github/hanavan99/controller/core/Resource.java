package com.github.hanavan99.controller.core;

import java.io.Serializable;

/**
 * Represents a resource that can be addressed by the command base.
 * 
 * @author Hanavan Kuhn
 *
 */
public abstract class Resource implements Serializable {

	private static final long serialVersionUID = 1081471468425623618L;

	private int id;
	private String name;

	/**
	 * Creates a new resource with an ID of 0 and a null name.
	 */
	public Resource() {

	}

	/**
	 * Creates a new resource with the specified ID.
	 * 
	 * @param id
	 *            the ID of the resource
	 */
	public Resource(int id) {
		this.id = id;
	}

	/**
	 * Creates a new resource with the specified ID and name.
	 * 
	 * @param id
	 *            the ID of the resource
	 * @param name
	 *            the name of the resource
	 */
	public Resource(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the ID of this resource.
	 * 
	 * @return the ID
	 */
	public final int getID() {
		return id;
	}

	/**
	 * Sets the ID of this resource.
	 * 
	 * @param id
	 *            the new ID
	 */
	public final void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets the name of this resource.
	 * 
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the name of this resource.
	 * 
	 * @param name
	 *            the new name
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the command bitmask used to issue control commands to the command base.
	 * 
	 * @return the bitmask
	 */
	public abstract int getCommandBitmask();

	@Override
	public boolean equals(Object other) {
		if (other instanceof Resource) {
			return ((Resource) other).id == id;
		}
		return false;
	}

}
