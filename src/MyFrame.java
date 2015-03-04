import javax.swing.*;


public class MyFrame extends JFrame {
	public MyFrame() {
		super("TODO list");
		JPanel toDoPanel = new ToDoPanel();
		add(toDoPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		toDoPanel.setLayout(new BoxLayout(toDoPanel,BoxLayout.Y_AXIS));
		pack();
		setVisible(true);		
	}
}
