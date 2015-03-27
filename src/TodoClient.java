import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class TodoClient {
	
	public Todo getForTodo(long id){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://localhost:8080/todoweb/db/showid/" + id, Todo.class);
	}
	
	public List<Todo> getForTodos(){
		RestTemplate restTemplate = new RestTemplate();
		Todo[] list = restTemplate.getForObject("http://localhost:8080/todoweb/db/show", Todo[].class);
		return Arrays.asList(list);
	}
	
	public void deleteTodos(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/todoweb/db");
	}
	
	public void deleteTodoId(long id){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/todoweb/db/" + id);
	}
	
	public Todo postForTodo(String text, boolean done){
		Todo todo = new Todo(text, done);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject("http://localhost:8080/todoweb/db", todo, Todo.class);
	}
	
	public void updateDoneTodo(long id, boolean done){
		RestTemplate restTemplate = new RestTemplate();
		String doneString;
		if(!done) doneString = "false";
		else if(done) doneString = "true";
		else doneString = "";
		restTemplate.put("http://localhost:8080/todoweb/db/" + id + "/" + doneString, Todo.class);
	}
	
	public void deleteDoneTodo(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/todoweb/db/");
	}
}

