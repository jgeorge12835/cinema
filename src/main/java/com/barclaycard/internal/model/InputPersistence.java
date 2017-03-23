package com.barclaycard.internal.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Model for Input Variables
 * 
 * @author Jerril George
 * @since March 23, 2017
 * @version 1.0
 */
public class InputPersistence {

	private List<List<Integer>> layout = new ArrayList<>();
	private Map<Party, String> assignments = new LinkedHashMap<>();

	/**
	 * @return the layout
	 */
	public List<List<Integer>> getLayout() {
		return layout;
	}

	/**
	 * @param layout
	 *            the layout to set
	 */
	public void setLayout(List<List<Integer>> layout) {
		this.layout = layout;
	}

	/**
	 * @return the assignments
	 */
	public Map<Party, String> getAssignments() {
		return assignments;
	}

	/**
	 * @param assignments
	 *            the assignments to set
	 */
	public void setAssignments(Map<Party, String> assignments) {
		this.assignments = assignments;
	}

	/**
	 * Constructor
	 * @param layout
	 * @param assignments
	 */
	public InputPersistence(List<List<Integer>> layout, Map<Party, String> assignments) {
		super();
		this.layout = layout;
		this.assignments = assignments;
	}

	/**
	 * Constructor
	 */
	public InputPersistence() {
		super();
	}

}
