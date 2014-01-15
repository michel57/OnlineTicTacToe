
import Services.GameService;
import Services.HistoryService;

public class Main
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{	
		GameService gameService = new GameService(9111);
		HistoryService historicService =  new HistoryService(9112);
		gameService.start();
		historicService.start();
	}

}
