package altran;
import java.io.*;
import java.util.*;
public class Test {
	
	public static List<Integer[]> missingRanges(Integer[] input) {

		List<Integer> superList = new ArrayList<Integer>();
		List<Integer[]> result = new ArrayList<Integer[]>();
		for (int i = input[0]; i <= input[input.length - 1]; i++) {
			superList.add(i);
		}
		for (int j = 0; j < input.length; j++) {
			if (superList.contains(input[j])) {
				superList.remove(input[j]);
			}
		}
		Integer arr[] = new Integer[2];
		if (superList.size() == 0) {
			return result;
		}
		arr[0] = superList.get(0);
		int anum = superList.get(0);
		for (int i = 1; i < superList.size(); i++) {
			if ((Integer) superList.get(i) == anum + 1) {
				anum = superList.get(i);
				if (i == superList.size() - 1) {
					arr[1] = anum;
					result.add(arr);
					break;
				}
				continue;
			} else {
				arr[1] = anum;
				result.add(arr);
				arr = new Integer[2];
				arr[0] = superList.get(i);
				anum = superList.get(i);
			}
		}
		return result;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] input1 = {1,2,3,4,8,9,13};
		for(int i=0;i<missingRanges(input1).size();i++) {
			for(int j=0;j<missingRanges(input1).get(i).length;j++) {
				System.out.print(missingRanges(input1).get(i)[j]+" ");
			}
		}
		System.out.println();
        Integer[] input2 = {1,2,4,8};
        for(int i=0;i<missingRanges(input2).size();i++) {
        	for(int j=0;j<missingRanges(input2).get(i).length;j++) {
				System.out.print(missingRanges(input2).get(i)[j]+" ");
			}
        }
        System.out.println();
        Integer[] input3 = {1,2,3,4};
        for(int i=0;i<missingRanges(input3).size();i++) {
        	for(int j=0;j<missingRanges(input3).get(i).length;j++) {
				System.out.print(missingRanges(input3).get(i)[j]+" ");
			}
        }

	}
	
}


//Example : 
//    Input : [1,2,3,4,8,9,13]
//    Output : [[5,7],[10,12]]
//Example : 
//    Input : [1,2,4,8]
//    Output : [[3,3],[5,7]]
//Example : 
//    Input : [1,2,3,4]
//    Output : []
//*/

