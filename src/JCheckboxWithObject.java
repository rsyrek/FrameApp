import javax.swing.JCheckBox;

public class JCheckboxWithObject extends JCheckBox{
 
  private Object object;
  private Todo todo;
  
  public JCheckboxWithObject (Object object, Todo todo){
    this.object = object;
    this.setText(object.toString());
    this.todo = todo;
  }
 
  public Object getObject() {
    return object;
  }

  public Todo getTodo(){
	  return todo;
  }
  
  public void setObject(Object object) {
    this.object = object;
    this.setText(object.toString());
  }
  
  public void setTodo(Todo todo){
	  this.todo = todo;
  }
}