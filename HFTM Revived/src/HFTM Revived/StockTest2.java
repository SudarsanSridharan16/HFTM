import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StockTest2 { 
	public double testStock(String symbol){		
		Stock facebook = StockFetcher.getStock(symbol);
//		System.out.println("Price: " + facebook.getPrice());
//		System.out.println("Volume: " + facebook.getVolume()); 
//		System.out.println("P/E: " + facebook.getPe());
//		System.out.println("EPS: " + facebook.getEps());
//		System.out.println("Year Low: " + facebook.getWeek52low());
//		System.out.println("Year High: " + facebook.getWeek52high());
//		System.out.println("Day Low: " + facebook.getDaylow());
//		System.out.println("Day High: " + facebook.getDayhigh());
//		System.out.println("50 Day Moving Av: " + facebook.getMovingav50day());
//		System.out.println("Market Cap: " + facebook.getMarketcap());
//		System.out.println("The full name is: " + facebook.getName());
//		System.out.println("The currency is: " + facebook.getCurrency());
//		System.out.println("The short ratio is: " + facebook.getShortRatio());
//		System.out.println("The previous close was: " + facebook.getPreviousClose());
//		System.out.println("The open for today was: " + facebook.getOpen());
//		System.out.println("The exchange is " + facebook.getExchange());
		return facebook.getPrice();
	} 
	public static void main(String[] args) throws InterruptedException{
		GoogleStock aapl = new GoogleStock("AAPL");
		String time,day;
		int hour,minute;
		boolean open = false;
		while(true){
			Date date = new Date();
			day = date.toString().substring(4,10);
			hour = Integer.parseInt(date.toString().substring(11,13));
			minute = Integer.parseInt(date.toString().substring(14,16));
			time = date.toString().substring(11,19);
			if(hour >= 9 && minute >= 30 || hour >= 10){
				open = true;
			}
			if(open && (hour < 16) && (day != "Sat" && day != "Sun")){
				aapl.update();
				//System.out.println("d");
				TimeUnit.MILLISECONDS.sleep(2000);
				//System.out.println("s");
				if(aapl.hasNextPrice()){
					try {
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(aapl.symbol.toUpperCase() + " " + day, true)));
						out.println(time + " " + aapl.price);
						out.close();
						//System.out.println(time + " " + aapl.price);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
//		StockTest s = new StockTest();
//		ProcessedStock stock = new ProcessedStock("AAPL");
//		double lastPrice = -10.0;
//		URL url;
//		try {
//			url = new URL("http://finance.google.com/finance/info?client=ig&q=NSE:GOOG");
//			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//			System.out.println(in.readLine());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String[] stockinfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//		for(String f : stockinfo){
//			System.out.println(f);
//		}
//		while(true){
//			System.out.println("d");
//			if(stock.hasNewPrice()){
//				System.out.println(stock.currentPrice);
//			}
//		}
	}
}
