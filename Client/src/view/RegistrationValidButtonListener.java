package view;

import java.awt.event.ActionEvent;

public class RegistrationValidButtonListener extends AbstractListener
{
	/**
	 * 
	 * @param view
	 */
	public RegistrationValidButtonListener(RegistrationFrame view)
	{
		super(view);
	}
	/**
	 * call the controller with the user parameters
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		RegistrationFrame view = (RegistrationFrame)getView();
		String version = "1";
		if( view.getVDegueu().isSelected() )
			version = "2";		
		view.getController().notifyValidButtonClicked(view.getIpServeur().getTextFieldContenu(),
				view.getPortServeur().getTextFieldContenu(),view.getPseudo().getTextFieldContenu(), version);
	}
}
