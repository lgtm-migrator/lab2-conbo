package umm3601.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

@SuppressWarnings({ "MagicNumber" })
public class FilterTodos {
  @Test
  public void limitTodos() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());
    Todo[] limitOfThree = db.limitTodos(allTodos, 3);
    assertEquals(3, limitOfThree.length, "Incorrect number of todos with owner Fry");

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("limit", Arrays.asList(new String[] { "4" }));
    Todo[] limitOfFour = db.listTodos(queryParams);
    assertEquals(4, limitOfFour.length);

  }

  @Test
  public void filterTodosByStatus() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] completeStatus = db.filterTodosByStatus(allTodos, "complete");
    assertEquals(143, completeStatus.length, "Incorrect number of todos with completed status");

    Todo[] incompleteStatus = db.filterTodosByStatus(allTodos, "incomplete");
    assertEquals(157, incompleteStatus.length, "Incorrect number of todos with completed status");
  }

  @Test
  public void filterTodosByCategory() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] groceries = db.filterTodosByCategory(allTodos, "groceries");
    assertEquals(76, groceries.length, "Incorrect number of todos with groceries Category");
  }

  @Test
  public void filterTodosByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] ownedByFry = db.filterTodosByOwner(allTodos, "Fry");
    assertEquals(61, ownedByFry.length, "Incorrect number of todos with owner Fry");

    Todo[] ownedByWorkman = db.filterTodosByOwner(allTodos, "Workman");
    assertEquals(49, ownedByWorkman.length, "Incorrect number of todos with owner Workman");
  }

  @Test
  public void testSize() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    assertEquals(300, db.size(), "Incorrect total Todos");
  }

  @Test
  public void listTodosWithOwnerFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] { "Fry" }));
    Todo[] ownedByFry = db.listTodos(queryParams);
    assertEquals(61, ownedByFry.length, "Incorrect number of users with age 25");
  }

  @Test
  public void filterBodyContent() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("contains", Arrays.asList(new String[] { "Veniam" }));
    Todo[] ownedByFry = db.listTodos(queryParams);
    assertEquals(78, ownedByFry.length);
  }

  @Test
  public void filterCategory() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("category", Arrays.asList(new String[] { "homework" }));
    Todo[] homeworkCategory = db.listTodos(queryParams);
    assertEquals(79, homeworkCategory.length);
  }
}
