package com.barclaycard.internal.test;

import static com.barclaycard.internal.constant.OutputConstant.BLANK_SPACE;
import static com.barclaycard.internal.constant.OutputConstant.PARTY_OVERRIDES_SEATS;
import static com.barclaycard.internal.constant.OutputConstant.PARTY_OVERRIDES_SECTION;
import static com.barclaycard.internal.constant.OutputConstant.TEMPLATE_ROW;
import static com.barclaycard.internal.constant.OutputConstant.TEMPLATE_SECTION;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.barclaycard.internal.Processer;
import com.barclaycard.internal.model.Party;

/**
 * Sample Test for Seating Calculations
 * 
 * @author JGeorge
 * @since March 20, 2017
 * @version 1.0
 */
public class CalculationTest {


	public Map<Party, String> calculate() {
		List<List<Integer>> seats = new ArrayList<>();
		List<Integer> row = new ArrayList<>();
		row.add(6);
		row.add(6);
		seats.add(row);

		row = new ArrayList<>();
		row.add(3);
		row.add(5);
		row.add(5);
		row.add(3);
		seats.add(row);

		row = new ArrayList<>();
		row.add(4);
		row.add(6);
		row.add(6);
		row.add(4);
		seats.add(row);

		row = new ArrayList<>();
		row.add(2);
		row.add(8);
		row.add(8);
		row.add(2);
		seats.add(row);

		row = new ArrayList<>();
		row.add(6);
		row.add(6);
		seats.add(row);

		Map<Party, String> parties = new LinkedHashMap<>();
		parties.put(new Party("Smith", 2), null);
		parties.put(new Party("Jones", 5), null);
		parties.put(new Party("Davis", 6), null);
		parties.put(new Party("Wilson", 100), null);
		parties.put(new Party("Johnson", 3), null);
		parties.put(new Party("Williams", 4), null);
		parties.put(new Party("Brown", 8), null);
		parties.put(new Party("Miller", 12), null);
		
		Map<Party, String> TrueOutput = Processer.calculate(seats, parties);
		return TrueOutput;
	}
	
	@Test
	public void testSampleInputOutput() {
		

		// Final Output Example
		Party party1 = new Party("Smith", 2, 1, 1, true);
		Party party2 = new Party("Jones", 5, 2, 1, true);
		Party party3 = new Party("Davis", 6, 1, 2, true);
		Party party4 = new Party("Wilson", 100);
		Party party5 = new Party("Johnson", 3, 2, 1, true);
		Party party6 = new Party("Williams", 4, 1, 1, true);
		Party party7 = new Party("Brown", 8, 4, 2, true);
		Party party8 = new Party("Miller", 12);
		
		Map<Party, String> finalOutput = new LinkedHashMap<>();
		finalOutput.put(party1, party1.getName() + BLANK_SPACE + TEMPLATE_ROW + party1.getRow()
		+ BLANK_SPACE + TEMPLATE_SECTION + party1.getSection());
		finalOutput.put(party2, party2.getName() + BLANK_SPACE + TEMPLATE_ROW + party2.getRow()
		+ BLANK_SPACE + TEMPLATE_SECTION + party2.getSection());
		finalOutput.put(party3, party3.getName() + BLANK_SPACE + TEMPLATE_ROW + party3.getRow()
		+ BLANK_SPACE + TEMPLATE_SECTION + party3.getSection());
		finalOutput.put(party4, party4.getName() + " " + PARTY_OVERRIDES_SEATS);
		finalOutput.put(party5, party5.getName() + BLANK_SPACE + TEMPLATE_ROW + party5.getRow()
		+ BLANK_SPACE + TEMPLATE_SECTION + party5.getSection());
		finalOutput.put(party6, party6.getName() + BLANK_SPACE + TEMPLATE_ROW + party6.getRow()
		+ BLANK_SPACE + TEMPLATE_SECTION + party6.getSection());
		finalOutput.put(party7, party7.getName() + BLANK_SPACE + TEMPLATE_ROW + party7.getRow()
		+ BLANK_SPACE + TEMPLATE_SECTION + party7.getSection());
		finalOutput.put(party8, party8.getName() + " " + PARTY_OVERRIDES_SECTION);
		
		Map<Party, String> trueOutput = calculate();
		
		System.out.println(finalOutput.get(party1));
		System.out.println(trueOutput.get(party1));
		
		//1. Test equal, ignore order
        assertEquals(finalOutput.get(party1), trueOutput.get(party1));

        //2. Test size
        assertEquals(finalOutput.size(), trueOutput.size());

	}
	
}
