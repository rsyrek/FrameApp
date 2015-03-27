import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class JCheckBoxList extends JList {

	private TodoClient client = new TodoClient();
	
	public JCheckBoxList() {
	    setCellRenderer(new CellRenderer());
	    addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
	    		long id;
	    		boolean done;
	    		int index = locationToIndex(e.getPoint());
	    		if (index != -1) {
	    			JCheckboxWithObject checkbox = (JCheckboxWithObject) getModel().getElementAt(index);
//	    			checkbox.
	    			checkbox.setSelected(!checkbox.isSelected());
	    			id = checkbox.getTodo().getId();
//	    			id = getId(checkbox.getText());
					done = checkbox.getTodo().getDone();
					client.updateDoneTodo(id, !done);
					checkbox.setTodo(client.getForTodo(id));
	    			fontSetting(checkbox);
	    			repaint();
	    		}
	    	}
    	});
    	setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

  	private boolean getDone(String text){
  		String[] array = text.split(" ");
  		if(array[array.length - 1].equalsIgnoreCase("done")){
  			return true;
  		}
  		else return false;
	}

	private long getId(String text) {
  		String[] array = text.split(" ");
		return Long.parseLong(array[1].replace(":", ""));
	}

	protected class CellRenderer implements ListCellRenderer {
  		public Component getListCellRendererComponent(JList list, Object value,
    		int index, boolean isSelected, boolean cellHasFocus) {
	      JCheckBox checkbox = (JCheckBox) value;
	      if (isSelected) {
	      } 
	      else {
	    	  checkbox.setBackground(UIManager.getColor("List.background"));
	      }
	      return checkbox;
	    }
  	}

  	public void selectAll() {
	    int size = this.getModel().getSize();
	    for (int i = 0; i < size; i++) {
	      JCheckBox checkbox = (JCheckboxWithObject) this.getModel().getElementAt(i);
	      checkbox.setSelected(true);
	      fontSetting(checkbox);
	    }
	    this.repaint();
  	}
  
  	public void deselectAll() {
	    int size = this.getModel().getSize();
	    for (int i = 0; i < size; i++) {
	      JCheckBox checkbox = (JCheckboxWithObject) this.getModel().getElementAt(i);
	      checkbox.setSelected(false);
	      fontSetting(checkbox);
	    }
	    this.repaint();
    }

	public void fontSetting(JCheckBox checkbox) {
		if(checkbox.isSelected()){
			checkbox.setFont(new Font(checkbox.getFont().getFamily(), Font.ITALIC, checkbox.getFont().getSize()));
			checkbox.setForeground(Color.GRAY);
		}
		else{
			checkbox.setFont(new Font(checkbox.getFont().getFamily(), Font.BOLD, checkbox.getFont().getSize()));
			checkbox.setForeground(Color.BLACK);
		}
	}
	
	public void getStartingDone(List<Todo> todos){
		int size = this.getModel().getSize();
	    for (int i = 0; i < size; i++) {
	    	if(todos.get(i).getDone()){
			    JCheckBox checkbox = (JCheckboxWithObject) this.getModel().getElementAt(i);
			    checkbox.setSelected(true);
			    fontSetting(checkbox);
	    	}
	    }
	    this.repaint();
	}
}
