package com.barclaycard.internal.service;

import static com.barclaycard.internal.constant.OutputConstant.BLANK_SPACE;
import static com.barclaycard.internal.constant.OutputConstant.DEFAULT_FILE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.barclaycard.internal.model.InputPersistence;
import com.barclaycard.internal.model.Party;

/**
 * To read the file input file and parse to model
 *
 * @author Jerril George
 * @since March 23, 2017
 * @version 1.0
 */
public class FileInputReader {

	public static InputPersistence readInput() {
		// Objects to handle the input
		List<List<Integer>> layout = new ArrayList<>();
		Map<Party, String> assignments = new LinkedHashMap<>();

		// Scanner Example - read file line by line in Java using Scanner
		FileInputStream fis = null;
		try {
			//TODO: Pass file name as a parameter
			fis = new FileInputStream(DEFAULT_FILE);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find file " + e);
		}
		try {
			Scanner scanner = new Scanner(fis);
			// Reading File line by line using Scanner
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				// Validate the line is not empty and not whitespace
				if (!line.isEmpty() && !line.equalsIgnoreCase(" ")) {
					// Confirm if the seat matrix
					if (Character.isDigit(line.charAt(0))) {
						List<Integer> rowLine = new ArrayList<>();
						// There are no special characters to split the numbers
						// except blank space
						// Convert values to be stored in List
						String[] stringArray = line.split(BLANK_SPACE);
						int[] intArray = new int[stringArray.length];
						for (int i = 0; i < stringArray.length; i++) {
							String numberAsString = stringArray[i];
							intArray[i] = Integer.parseInt(numberAsString);
							rowLine.add(intArray[i]);
						}
						// After loop, we will add all rowLine to layout as a
						// single row
						layout.add(rowLine);
						continue;
					} else {
						// Assume the reminder of the input is Parties Names and
						// Ticket Count
						String[] stringArray = line.split(BLANK_SPACE);
						String name = stringArray[0];
						String countString = stringArray[1];
						int counter = Integer.parseInt(countString);
						assignments.put(new Party(name, counter), null);
					}
				}
			}
			// Done w/ scanner
			scanner.close();
		} catch (IllegalArgumentException e) {
			// Most likely we will reach here if input is vastly different than
			// proposed input
			// Parsing String to int failure will probably result here.
			System.out.println("Parsing Exception " + e);
		}
		InputPersistence input = new InputPersistence(layout, assignments);
		return input;
	}
}
