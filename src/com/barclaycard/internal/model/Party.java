package com.barclaycard.internal.model;

/**
 * Model represent a party
 *
 * @author Jerril George
 * @since March 20, 2017
 * @version 1.0
 */
public class Party {

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Party))
			return false;
		Party other = (Party) obj;
		if (count != other.count)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private int count;
	private String name;
	private boolean recliner;
	private int row;
	private int section;

	/**
	 * Constructor.
	 *
	 */
	public Party() {
		super();
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 * @param count
	 */
	public Party(String name, int count) {
		super();
		this.name = name;
		this.count = count;
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 * @param count
	 * @param row
	 * @param section
	 */
	public Party(String name, int count, int row, int section, boolean recliner) {
		super();
		this.name = name;
		this.count = count;
		this.row = row;
		this.section = section;
		this.recliner = recliner;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the section
	 */
	public int getSection() {
		return section;
	}

	/**
	 * @return the recliner
	 */
	public boolean isRecliner() {
		return recliner;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param recliner
	 *            the recliner to set
	 */
	public void setRecliner(boolean recliner) {
		this.recliner = recliner;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @param section
	 *            the section to set
	 */
	public void setSection(int section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return String.format("Party [count=%s, name=%s, recliner=%s, row=%s, section=%s]", count, name, recliner, row,
				section);
	}

}
