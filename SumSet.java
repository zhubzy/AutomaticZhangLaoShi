import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SumSet {
	
	static ArrayList<ArrayList<Integer>> solutions = new ArrayList<ArrayList<Integer>>();

	static boolean isSubset(List<Integer> arr1, List<Integer> arr2, int m, 
			int n) 
{ 
int i = 0, j = 0; 

if(m < n) 
return false; 

Collections.sort(arr1); //sorts arr1 
Collections.sort(arr2); // sorts arr2 

while( i < n && j < m ) 
{ 
if( arr1.get(j) < arr2.get(i) ) 
j++; 
else if( arr1.get(j) == arr2.get(i) ) 
{ 
j++; 
i++; 
} 
else if( arr1.get(j) > arr2.get(i)) 
return false; 
} 

if( i < n ) 
return false; 
else
return true; 
} 
    static void sum_up_recursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
	       int s = 0;
	       for (int x: partial) s += x;
	       if (s == target)
	       		solutions.add(partial);
	       if (s >= target)
	            return;
	       for(int i=0;i<numbers.size();i++) {
	             ArrayList<Integer> remaining = new ArrayList<Integer>();
	             int n = numbers.get(i);
	             for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
	             ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
	             partial_rec.add(n);
	             sum_up_recursive(remaining,target,partial_rec);
	       }
	    }
	    static void sum_up(ArrayList<Integer> numbers, int target) {
	        sum_up_recursive(numbers,target,new ArrayList<Integer>());
	    }
	    
	    static boolean hasValue(ArrayList<Integer> numbers, int value) {
	    	
	        for (int i = 0; i < numbers.size(); i++) {
	        		
	        	if(numbers.get(i) == value) {
	        		return true;
	        	}
	        	
	        }
	        	return false;
	    	
	    }
	    
	    public static void main(String args[]) {
	    	
            
            	  int[] cards = {1,10,11,12,6,7,9,10};	           
	           List<Integer> cardslist = Arrays.stream(cards).boxed().collect(Collectors.toList());
	           ArrayList<SolutionSet>  finalSolutions = new ArrayList<SolutionSet>();

            
	        Integer[] numbers = new Integer[cards.length];
	        
	        for (int i = 0; i < cards.length; i++) {
				if(cards[i] == 'A') {
					
					numbers[i] = 1;
				}
				else if(cards[i] == 'K') {
					
					numbers[i] = 13;
				} else if(cards[i] == 'Q') {
					
					numbers[i] = 12;
				}else if(cards[i] == 'J') {
					
					numbers[i] = 11;
				} else {
					
					numbers[i] = (int)cards[i];
				}
	        	
			}
	      
	        for(int target = 1; target <= 100; target ++) {
	        	
		        sum_up(new ArrayList<Integer>(Arrays.asList(numbers)),target);
		        for (int i = 0; i < solutions.size(); i++)
		        {
		           Collections.sort(solutions.get(i));
		            
		        }
		        for (int i = 0; i < solutions.size(); i++)
		        {
		        	for (int j = i + 1; j < solutions.size(); j++)
			        {
			            if(solutions.get(i).equals(solutions.get(j)) && solutions.get(i).size()> 1) {
			            		
			            		solutions.remove(i);
			            		j--;
			            		
			            }
			        }
		        }
		        
		        
		        //Sort out all duplicates
		        for (int i = 0; i < solutions.size(); i++)
		        {
		        	for (int j = i + 1; j < solutions.size(); j++)
			    {
			        
			             List<Integer> combinedSol = Stream.of(solutions.get(i), solutions.get(j))
                                 .flatMap(x -> x.stream())
                                 .collect(Collectors.toList());
			             if(isSubset(cardslist, combinedSol, cardslist.size(), combinedSol.size())) {
					        	 SolutionSet s = new SolutionSet(solutions.get(i),solutions.get(j));
					 
					         finalSolutions.add(s);
			             }
			             
			        
			    }
			       
		        
		        
		        }
		        
		        if(solutions.size() > 1) {
		        		
//		            System.out.println("sum("+Arrays.toString(solutions.toArray())+")="+target);
		        
		        }
	        		solutions = new ArrayList<ArrayList<Integer>>();
	        }
	        Collections.sort(finalSolutions, new BestSolution()); 

	        for (int i = 0; i < finalSolutions.size(); i++) {
				
	        		System.out.println(finalSolutions.get(i));
			}
	        
	    }
	    
	    
}
