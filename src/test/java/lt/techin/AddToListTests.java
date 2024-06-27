package lt.techin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddToListTests extends TestSetup{

        protected ToDoList toDoList;

        @BeforeEach
        public void setToDoList(){
            toDoList = new ToDoList(driver);
        }


        @Test
        public void addNewValidTask() {
            String task = "Some test task";

            //checks if add new todo field is displayed
            if (toDoList.inputFieldIsVisible()){
                toDoList.addTaskToList(task);
                toDoList.confirmAddTask();
            }
            else {
                toDoList.clickAddToList();
                toDoList.addTaskToList(task);
                toDoList.confirmAddTask();
            }

            //validates if added task appeared in to do list as last element
            Assertions.assertEquals(task, toDoList.lastAddedTask());
        }


        @Test
        public void  visibleComplete(){
            //selects and clics all todo list items
            toDoList.clickAllTasksToComplete();

            //checks if all clicked list items are displayed as completed
            Assertions.assertTrue(toDoList.areAllTasksCompleted());
        }

        @Test
        public void deleteFirstToDoItem(){
            toDoList.clickDeleteFirst();
        }

        @Test
        public void deleteLastToDoItem(){
            toDoList.clickDeleteLast();
        }

        @Test
        public void deleteAllTasks() {
            toDoList.deleteAllToDoItems();
            Assertions.assertTrue(toDoList.isListOfTaskEmpty());
        }



}
