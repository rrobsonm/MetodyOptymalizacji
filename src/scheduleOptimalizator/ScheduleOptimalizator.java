package scheduleOptimalizator;

public class ScheduleOptimalizator extends GUI {

	private static void createAndShowGUI(){
        //Create and set up the window.
	 	GUI gui = new GUI();
	 	
        //iDisplay the window.
        gui.pack();
        gui.setVisible(true);
    }
	
	public static void main(String [ ] args)
	{ 
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowGUI();
            }
        });
	}
}
