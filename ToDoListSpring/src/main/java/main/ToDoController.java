package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class ToDoController {

  @Autowired
  private ToDoRepository toDoRepository;

  @GetMapping("/")
  public String get(Model model) {

    return "redirect:/index";
  }
  @GetMapping("/index")
  public String index(Model model) {
    Iterable<ToDo> toDoIterable = toDoRepository.findAll();

    ArrayList<ToDo> todos = new ArrayList<>();
    for (ToDo toDo : toDoIterable) {
      todos.add(toDo);
    }
    model.addAttribute("todos", todos);
    model.addAttribute("todoCount", todos.size());
    Date date = new Date();
    model.addAttribute("date", date.toString());
    return "index";
  }


  @GetMapping("/showAddItemForm")
  public String showAddItemForm(Model model) {
    ToDo newTodoItem = new ToDo();
    model.addAttribute("todoItem", newTodoItem);
    return "new_item";
  }

  @PostMapping("/saveTodoItem")
  public String saveTodoItem(
      @ModelAttribute("todoItem") @Valid @NotNull @RequestBody ToDo todoItem) {
    toDoRepository.save(todoItem);
    return "redirect:/index";
  }
  @GetMapping("/deleteTodoItem/{id}")
  public String deleteTodoItem(@PathVariable int id) {
    Optional<ToDo> optional = toDoRepository.findById(id);
    toDoRepository.delete(optional.get());
    return "redirect:/index";
  }

  @GetMapping("/deleteAll")
  public String deleteAllItem() {
    toDoRepository.deleteAll();
    return "redirect:/index";
  }
//  @GetMapping("/todo/")
//  public List<ToDo> list() {
//    Iterable<ToDo> toDoIterable = toDoRepository.findAll();
//    ArrayList<ToDo> toDos = new ArrayList<ToDo>();
//    for (ToDo toDo : toDoIterable) {
//      toDos.add(toDo);
//    }
//    return toDos;
//  }

//  @PostMapping("/todo/")
//  public int add(ToDo toDo) {
//   ToDo newToDo =  toDoRepository.save(toDo);
//    return newToDo.getID();
//  }
//  @GetMapping("/")
//  public String get() {
//    return "index";
//  }

//  @GetMapping("/todo/{id}")
//  public ResponseEntity getToId(@PathVariable int id) {
//   Optional<ToDo> optional = toDoRepository.findById(id);
//    return optional.map(toDo -> new ResponseEntity(toDo, HttpStatus.OK))
//        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
//  }
//
//  @DeleteMapping("/deleteTodoItem/{id}")
//  public ResponseEntity delete(@PathVariable int id) {
//    Optional<ToDo> optional = toDoRepository.findById(id);
//    if (!optional.isPresent()) {
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
//    toDoRepository.delete(optional.get());
//    return new ResponseEntity(HttpStatus.OK);
//  }



//  @DeleteMapping("/todo/")
//  public ResponseEntity deleteAll() {
//    toDoRepository.deleteAll();
//    return new ResponseEntity(HttpStatus.OK);
//  }

}
