import view.RegistrationFrame;
import controller.RegistrationFrameController;
import model.RegistrationFrameModel;

public class Main
{
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)
	{	
		RegistrationFrame rfView = new RegistrationFrame();
		RegistrationFrameModel rfModel = new RegistrationFrameModel();
		RegistrationFrameController rfController = new RegistrationFrameController(rfView,rfModel);
		rfView.setController(rfController);
		rfController.displayViews();
	}
}
