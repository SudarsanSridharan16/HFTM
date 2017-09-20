import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineCounter {
	public static void main(String[] args) throws FileNotFoundException{
		File f = new File("AAPL Oct 03");
		Scanner file = new Scanner(f);
		int x = 0;
		while(file.hasNextLine()){
			file.nextLine();
			x++;
		}
		System.out.println(x);
	}
}
