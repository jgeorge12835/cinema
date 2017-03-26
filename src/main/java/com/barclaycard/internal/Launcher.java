package com.barclaycard.internal;

import java.util.Map;

import com.barclaycard.internal.model.InputPersistence;
import com.barclaycard.internal.model.Party;
import com.barclaycard.internal.service.FileInputReader;
import com.barclaycard.internal.service.Processer;

/**
 * Class to handle entry into application</br>
 * Reads the data from the console, parses inputs and lunches the main
 * algorithm.
 *
 * @author Jerril George
 * @since March 20, 2017
 * @version 1.2
 */
public class Launcher {

	/**
	 * Main Method Launches the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Objects to handle the input
		InputPersistence input = FileInputReader.readInput();

		// Calculate the results
		Map<Party, String> itertary = Processer.calculate(input.getLayout(), input.getAssignments());
		for (Map.Entry<Party, String> group : itertary.entrySet()) {
			// Output the results
			System.out.println(group.getValue());
		}
	}

    /** Private default constructor. */
    private Launcher() {
        super();
    }

}
