package com.barclaycard.internal;

import static com.barclaycard.internal.constant.OutputConstant.BLANK_SPACE;
import static com.barclaycard.internal.constant.OutputConstant.PARTY_OVERRIDES_SEATS;
import static com.barclaycard.internal.constant.OutputConstant.PARTY_OVERRIDES_SECTION;
import static com.barclaycard.internal.constant.OutputConstant.TEMPLATE_ROW;
import static com.barclaycard.internal.constant.OutputConstant.TEMPLATE_SECTION;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.barclaycard.internal.model.Party;

/**
 * Class to handle entry into application</br>
 * Reads the data from the console, parses inputs and lunches the main
 * algorithm.
 *
 * @author Jerril George
 * @since March 20, 2017
 * @version 1.0
 */
public class Launcher {

	/**
	 * Main Method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Objects to handle the input
		List<List<Integer>> layout = new ArrayList<>();
		Map<Party, String> assinments = new LinkedHashMap<>();

		// Scanner Example - read file line by line in Java using Scanner
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("input.txt");
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
						assinments.put(new Party(name, counter), null);
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

		// Calculate the results
		Map<Party, String> itertary = calculate(layout, assinments);
		for (Map.Entry<Party, String> group : itertary.entrySet()) {
			// Output the results
			System.out.println(group.getValue());
		}
	}

	/**
	 * Perform the calculation for the parties in regards to the seating matrix
	 * 
	 * @param seats
	 * @param parties
	 * @return
	 */
	private static Map<Party, String> calculate(List<List<Integer>> seats, Map<Party, String> parties) {

		// Create a sorting to keep list in sync for output
		List<Party> sortedParties = new ArrayList<>(parties.keySet());

		int amount = 0;
		for (List<Integer> seat : seats) {
			for (Integer r : seat) {
				amount += r;
			}
		}
		Map<Integer, Party> hash = new HashMap<>();
		for (Party party : sortedParties) {
			hash.put(party.getCount(), party);
		}

		// Run the calculations
		for (Party party : sortedParties) {
			// If a party request more than available seating
			if (amount < party.getCount()) {
				parties.put(party, party.getName() + " " + PARTY_OVERRIDES_SEATS);
				continue;
			}
			// Creating a party check as only during the math can we identify if a party can't fit in a single section
			boolean found = false;
			for (int i = 0; i < seats.size(); i++) {
				for (int j = 0; j < seats.get(i).size(); j++) {
					// Need to fill front sections as much as possible
					// Subtract seats so we can see if other parties can be
					// placed into section
					Integer available = seats.get(i).get(j);
					if (available > party.getCount() && !party.isRecliner()) {
						Integer rest = available - party.getCount();
						Party restParty = hash.get(rest);
						if (restParty != null) {
							hash.remove(rest);
							seats.get(i).set(j, 0);
							party.setRow(i + 1);
							party.setSection(j + 1);
							party.setRecliner(true);
							parties.put(party, party.getName() + BLANK_SPACE + TEMPLATE_ROW + party.getRow()
									+ BLANK_SPACE + TEMPLATE_SECTION + party.getSection());
							// Handle potential reminder if possible to fill
							// section to max
							restParty.setRow(i + 1);
							restParty.setSection(j + 1);
							restParty.setRecliner(true);
							parties.put(restParty, restParty.getName() + BLANK_SPACE + TEMPLATE_ROW + (i + 1)
									+ BLANK_SPACE + TEMPLATE_SECTION + (j + 1));
							found = true;
							break;
						}
					} else if (available == party.getCount() && !party.isRecliner()) {
						// If party matches exactly with count and is not seated because of an earlier count,
						// then find if the party can be seated
						available -= party.getCount();
						seats.get(i).set(j, available);
						party.setRow(i + 1);
						party.setSection(j + 1);
						party.setRecliner(true);
						parties.put(party, party.getName() + BLANK_SPACE + TEMPLATE_ROW + party.getRow() + BLANK_SPACE
								+ TEMPLATE_SECTION + party.getSection());
						found = true;
					} else if (party.isRecliner()) {
						// Already Identified Seats
						found = true;
						break;
					}
					if (found) {
						break;
					}
				}
				if (found) {
					break;
				}
			} // i
			if (!found) {
				parties.put(party, party.getName() + BLANK_SPACE + PARTY_OVERRIDES_SECTION);
			}
		}

		return parties;
	}
}
