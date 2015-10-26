import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class inversion {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String input = "F:\\Program Files\\Java\\workspace\\inversion\\src\\IntegerArray.txt";
		
		FileReader fr = new FileReader(input);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numberOfLines = readLines(input);
		int[] array = new int[numberOfLines];
		
		int i;
		for(i=0; i<numberOfLines; i++){
			array[i] = Integer.parseInt(textReader.readLine());
			//System.out.println (array[i]);
		}
		
		textReader.close();
		
		//int[] array1 = {6,5,4,3,2,1};
		
		long inverse = count(array);
		
		System.out.println(inverse);
		
		
		
	}
	
	

	//count how many lines the text file has
	private static int readLines(String inputfile) throws IOException{
		
		String input = inputfile;
		FileReader file_to_read = new FileReader(input);
		BufferedReader bf = new BufferedReader(file_to_read);
		
		String aLine;
		int numberOfLines = 0;
		
		while((aLine = bf.readLine()) != null){
			numberOfLines++;
		}
		bf.close();
		
		return numberOfLines;
	}
	
	
	private static long merge(int[] a, int[] aux, int lo, int mid, int hi){
		for(int k = lo; k <= hi; k++){
			aux[k] = a[k];
		}
		
		int i = lo;
		int j = mid + 1;		
		long inverse = 0;
		
		for(int k = lo; k <= hi; k++){
			if(i > mid){
				a[k] = aux[j++];
				inverse += mid - i + 1;
			}
			
			else if(j > hi)
				a[k] = aux[i++];
			
			else if(aux[j] < aux[i]){
				a[k] = aux[j++];
				inverse += mid - i + 1;
			}
			
			else
				a[k] = aux[i++];
		}
		
		
		return inverse;
	}
	
	
	
	private static long count(int[] a, int[] aux, int lo, int hi){
		if(hi <= lo)
			return 0;
		
		int mid = lo + (hi-lo)/2;
		long x = count(a, aux, lo, mid);
		long y = count(a, aux, mid+1, hi);		
		long z = merge(a, aux, lo, mid, hi);		
		
		return x+y+z;
	}
	
	public static long count(int[] a){
		
		int[] aux = new int[a.length];
		return count(a, aux, 0, a.length-1);
		
	}
	
	
	
	
	
	
}
