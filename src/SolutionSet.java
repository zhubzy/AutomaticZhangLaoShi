import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class SolutionSet {
	
	public ArrayList<Integer> firstSet;
	public ArrayList<Integer> secondSet;

	
	public SolutionSet(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2) {
		
		firstSet = arrayList;
		secondSet = arrayList2;
		
	}
	
	
	public void deleteValueFromFirstSet(String cardName) {
		
		
	int cardNumericalValue = 0;
		
		if(cardName.equals("Q")) {
			
			cardNumericalValue = 12;
			
		} else if (cardName.equals("K")) {
			
			
			cardNumericalValue = 13;
			
		} else if (cardName.equals("J")) {
			cardNumericalValue = 11;
		}else if (cardName.equals("A")) {
			cardNumericalValue = 1;
		} 
		 else {
			
			cardNumericalValue = Integer.parseInt(cardName);
			
		}
		
		
		for (int i = 0; i < firstSet.size(); i++) {
			if(cardNumericalValue == firstSet.get(i)) {
				
				firstSet.remove(i);
				return;
			}
		}
		
		System.out.println(cardName + " not found in set 1" );
		
		
	}
	
	public void deleteValueFromSecondSet(String cardName) {
		
		
	int cardNumericalValue = 0;
		
		if(cardName.equals("Q")) {
			
			cardNumericalValue = 12;
			
		} else if (cardName.equals("K")) {
			
			
			cardNumericalValue = 13;
			
		} else if (cardName.equals("J")) {
			cardNumericalValue = 11;
		}else if (cardName.equals("A")) {
			cardNumericalValue = 1;
		} 
		 else {
			
			cardNumericalValue = Integer.parseInt(cardName);
			
		}
		
		
		for (int i = 0; i < secondSet.size(); i++) {
			if(cardNumericalValue == secondSet.get(i)) {
				
				secondSet.remove(i);
				return;
			}
		}
		
		System.out.println(cardName + " not found in set 2" );
		
		
	}
	public boolean firstSetContains(String cardName) {

		int cardNumericalValue = 0;
		
		if(cardName.equals("Q")) {
			
			cardNumericalValue = 12;
			
		} else if (cardName.equals("K")) {
			
			
			cardNumericalValue = 13;
			
		} else if (cardName.equals("J")) {
			cardNumericalValue = 11;
		} else if (cardName.equals("A")) {
			cardNumericalValue = 1;
		} 
		
		
		
		else {
			
			cardNumericalValue = Integer.parseInt(cardName);
			
		}
		
		for (int i = 0; i < firstSet.size(); i++) {
			if(cardNumericalValue == firstSet.get(i))
				return true;
		}
		
		return false;
		
	}
	
	public boolean secondSetContains(String cardName) {

		int cardNumericalValue = 0;
		
		if(cardName.equals("Q")) {
			
			cardNumericalValue = 12;
			
		} else if (cardName.equals("K")) {
			
			
			cardNumericalValue = 13;
			
		} else if (cardName.equals("J")) {
			cardNumericalValue = 11;
		}else if (cardName.equals("A")) {
			cardNumericalValue = 1;
		} 
		 else {
			
			cardNumericalValue = Integer.parseInt(cardName);
			
		}
		
		for (int i = 0; i < secondSet.size(); i++) {
			if(cardNumericalValue == secondSet.get(i))
				return true;
		}
		
		return false;
		
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Arrays.toString(firstSet.toArray()) + " " +  Arrays.toString(secondSet.toArray());
	
	}
	
	
}
class BestSolution implements Comparator<SolutionSet> 
{ 

	@Override
	public int compare(SolutionSet o1, SolutionSet o2) {
		// TODO Auto-generated method stub
		if(o1.firstSet.size() + o1.secondSet.size() > o2.firstSet.size() + o2.secondSet.size())
			return -1;
		else if (o1.firstSet.size() + o1.secondSet.size()  < o2.firstSet.size() + o2.secondSet.size())
			return 1;
		
		return 0;
			
	} 
} 
  