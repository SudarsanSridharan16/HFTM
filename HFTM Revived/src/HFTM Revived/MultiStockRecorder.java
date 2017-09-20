import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MultiStockRecorder{
	public static void main(String[] args) throws IOException{
		String time,day;
		int hour,minute;
		double total = 0;
		double average = 0;
		int n = 0;
		boolean open = false;
		boolean printIOException = true;
		boolean printNullPointerException = true;
		Scanner in = new Scanner(System.in);
		String input;
		System.out.print("Enter symbols seperated by a comma: ");
		input = in.nextLine();
		String[] symbols;
		ProcessedStock[] stocks;
		if(input.toLowerCase().equals("preset")){
			String[] preset = {"A", "AA", "AAL", "AAP", "AAPL", "ABBV", "ABC", "ABT", "ACN", "ABDE", "ADI", "ADM", "ADP", "ADS", "ADSK", "AEE", "AEP", "AES", "AET", "AFL", "AGN", "AIG", "ALL"};
			symbols = preset;
			stocks = new ProcessedStock[preset.length];
			for(int i = 0; i < symbols.length; i++){
				stocks[i] = new ProcessedStock(symbols[i]);
			}
		}
		else{
			symbols = input.split(",");
			stocks = new ProcessedStock[symbols.length];
			for(int i = 0; i < symbols.length; i++){
				stocks[i] = new ProcessedStock(symbols[i]);
			}
		}
		System.out.println("Number of stocks running: " + stocks.length);
		while(true){
			long start = System.currentTimeMillis();
			Date date = new Date();
			day = date.toString().substring(4,10);
			hour = Integer.parseInt(date.toString().substring(11,13));
			minute = Integer.parseInt(date.toString().substring(14,16));
			time = date.toString().substring(11,19);
			if(hour >= 9 && minute >= 30 || hour >= 10){
				open = true;
			}
			if(open && (hour < 16) && (day != "Sat" && day != "Sun")){
				try{
					for(ProcessedStock s : stocks){
						if(s.hasNewPrice()){
							PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(s.path + " " + day, true)));
							out.println(time + " " + s.currentPrice);
							out.close();
							System.out.println(s.stock.symbol + ": " + time + " " + s.currentPrice);
						}
					}
//					System.out.println();
					printIOException = true;
					printNullPointerException = true;
				}
//				catch(IOException e){
//					e.printStackTrace();
//					if(printIOException){
//						System.out.print(time + " IOException | Failed to set up connection |");
//						printIOException = false;
//					}
//				}
				catch(NullPointerException e){
					if(printNullPointerException){
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Exceptions " + day, true)));
						out.println(time + " " + e.toString());
						out.close();
						e.printStackTrace();
						printNullPointerException = false;
					}
				}
				catch(Exception e){
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Exceptions " + day, true)));
					out.println(time + " " + e.toString());
					out.close();
					e.printStackTrace();
				}
			}
			else{
				open = false;
			}
			n++;
			total += (System.currentTimeMillis() - start)/1000.0;
			average = total/n;
			//System.out.println(average);
		}
	}
}
