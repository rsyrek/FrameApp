import java.awt.EventQueue;

import javax.swing.UIManager;

public class MyApp {
	{
	    // Set Look & Feel
	    try {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MyFrame();
			}
		});
	}
}
