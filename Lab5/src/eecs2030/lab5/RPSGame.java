package eecs2030.lab5;

/**
 * The class that contains the main method that creates the rock, paper,
 * scissors application.
 *
 */
public class RPSGame {

	static RPSModel model = new RPSModel(100);
	static RPSController controller = new RPSController();
	static RPSView view = new RPSView(controller);

	/**
	 * This method creates an RPSModel, RPSController, and RPSView to create the
	 * rock, paper, scissors game. You need to set the the model and view on the
	 * controller, and you need to make the view visible.
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		controller.setModel(model);
		controller.setView(view);
		controller.getView().setVisible(true);
	}
}
