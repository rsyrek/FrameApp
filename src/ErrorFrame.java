import javax.swing.JFrame;
import javax.swing.JLabel;


public class ErrorFrame extends JFrame {
	public ErrorFrame() {
		super("Error");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(new JLabel("Brak połączenia z bazą danych."));
		pack();
		this.repaint();
	}
}
