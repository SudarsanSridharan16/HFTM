import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GoogleStock {
	public String symbol;
	public double price, lastPrice;
	public GoogleStock(String symbol){
		this.symbol = symbol;
		price = -1.0;
		lastPrice = -1.0;
	}
	public void update(){
		try {
			URL yahoo = new URL("http://finance.google.com/finance/info?client=ig&q=NSE:" + symbol);
			URLConnection connection = yahoo.openConnection();
			InputStreamReader is = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(is);  
			for(int i = 0; i < 6; i++){
				br.readLine();
			}
			String line = br.readLine();
			line = line.substring(line.indexOf(": "));
			line = line.substring(line.indexOf("\"") + 1);
			line = line.substring(0, line.indexOf("\""));
			price = Double.parseDouble(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean hasNextPrice(){
		if(price != lastPrice){
			lastPrice = price;
			return true;
		}
		lastPrice = price;
		return false;
	}
	
}
