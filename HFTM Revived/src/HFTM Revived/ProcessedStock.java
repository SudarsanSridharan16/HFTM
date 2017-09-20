public class ProcessedStock {
	public double currentPrice, lastPrice;
	public Stock stock;
	public String path;
	public ProcessedStock(String symbol){
		stock = StockFetcher.getStock(symbol.toUpperCase());
		this.currentPrice = stock.getPrice();
		this.lastPrice = -10;
		this.path = symbol.toUpperCase();
	}
	public boolean hasNewPrice(){
		stock = StockFetcher.getStock(path);
		currentPrice = stock.getPrice();
		if(currentPrice != lastPrice){
			lastPrice = currentPrice;
			return true;
		}
		return false;
	}
}