public class Todo {
	private long id;
	private String text;
	private boolean done;
	
	public Todo() {
	}
	public Todo(String text, boolean done) {
		this.text = text;
		this.done = done;
	}
	public long getId(){
		return id;
	}
	public String getText() {
		return text;
	}
	public boolean getDone() {
		return done;
	}
	public void setId(long id){
		this.id = id;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (done ? 1231 : 1237);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (done != other.done)
			return false;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	@Override
	public String toString(){
		String done = this.done ? "done" : "undone";
		return "Task " + id + ": " + this.text + " is " + done;
	}
}