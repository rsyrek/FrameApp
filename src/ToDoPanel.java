import java.awt.*;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.client.ResourceAccessException;

public class ToDoPanel extends JPanel implements ActionListener{

	public static final int HEIGHT = 300;
	public static final int WIDTH = 200;
	private JButton addButton;
	private JButton removeButton;
	private JButton selectAllButton;
	private JButton deselectAllButton;
	private JTextField textPane;
	private Vector<JCheckBox> checkBoxVector = new Vector<JCheckBox>();
	private DefaultListModel<JCheckboxWithObject> tasksListModel = new DefaultListModel<JCheckboxWithObject>();
	private JCheckBoxList listTask = new JCheckBoxList();
	private JScrollPane tasksScroller = new JScrollPane(listTask);
	private TodoClient client = new TodoClient();
	private List<Todo> todos = new ArrayList<Todo>();
	
	public ToDoPanel() {
        listTask.setModel(tasksListModel);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));	
		addComponentsToPanel();
		setActionListeners();
	}

	private void setActionListeners() {
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonAction();
			}	
		});
		
		removeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				removeButtonAction();
			}
		});
		
		selectAllButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listTask.selectAll();
			}
		});
		
		deselectAllButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listTask.deselectAll();
			}
		});
	}

	private void addComponentsToPanel() {
		addButton = new JButton("Add");
		textPane = new JTextField();
		textPane.setMaximumSize(new Dimension(200, 25));
		removeButton = new JButton("Remove");
		selectAllButton = new JButton("Select all");
		deselectAllButton = new JButton("Deselect all");
		add(textPane);
		add(addButton);
		add(selectAllButton);
		add(deselectAllButton);
		add(tasksScroller);
		add(removeButton);
	    tasksScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    try{
	    refreshTodoList();
	    }
	    catch(ResourceAccessException e){
	    	EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new ErrorFrame();
				}
	    	});
	    }
		this.updateUI();
	}

	private void refreshTodoList() {
		tasksListModel.clear();
		todos = client.getForTodos();
	    for (Todo todo : todos){
		    JCheckBox checkBox = new JCheckBox();
			JCheckboxWithObject checkBoxWithObject = new JCheckboxWithObject(checkBox, todo);
			checkBoxWithObject.setText(todo.getText());
			checkBox.setSelected(todo.getDone());
			tasksListModel.addElement(checkBoxWithObject);
			listTask.getStartingDone(todos);
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	private void removeButtonAction() {
		client.deleteDoneTodo();
		refreshTodoList();
		this.updateUI();
	}

	private void addButtonAction() {
		if(!textPane.getText().isEmpty()){
			client.postForTodo(textPane.getText(), false);
			refreshTodoList();
			tasksScroller.repaint();	
			textPane.setText(null);
			this.updateUI();
		}
	}
}

