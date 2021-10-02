import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SolutionSet {
	
	public ArrayList<Integer> firstSet;
	public ArrayList<Integer> secondSet;

	
	public SolutionSet(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2) {
		
		firstSet = arrayList;
		secondSet = arrayList2;
		
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
  