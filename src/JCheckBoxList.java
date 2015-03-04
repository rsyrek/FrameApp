import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class JCheckBoxList extends JList {

  public JCheckBoxList() {
    setCellRenderer(new CellRenderer());
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        int index = locationToIndex(e.getPoint());
        if (index != -1) {
        	JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
        	checkbox.setSelected(!checkbox.isSelected());
        	fontSetting(checkbox);
        	repaint();
        }
      }
    });
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
      JCheckBox checkbox = (JCheckboxWithObject) this.getModel()
          .getElementAt(i);
      checkbox.setSelected(true);
      fontSetting(checkbox);
    }
    this.repaint();
  }

  public void deselectAll() {
    int size = this.getModel().getSize();
    for (int i = 0; i < size; i++) {
      JCheckBox checkbox = (JCheckboxWithObject) this.getModel()
          .getElementAt(i);
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
}
