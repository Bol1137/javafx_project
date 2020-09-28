package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Controller implements Initializable {
    /*With this declarator we can associate instances from controller class
    with UI element we have in the fxml file*/
    @FXML private TextField newTask;
    @FXML private ListView<String> taskList;
    //Creating filePath variable
    private String filePath="data.txt";
    private File data = new File(filePath);
    /*Method to add new tasks*/
    public void addNewTask(ActionEvent e)
    {
        String text = newTask.getText();
        if(!text.equals("")){
            taskList.getItems().add(text);
            newTask.setText("");
        }else{
            System.out.println("No input...");
        }

    }
    /*Method to delete the tasks*/
    public void deleteTask(ActionEvent e)
    {
        //First we will get the selected items from the tasklist as string
        String selected = taskList.getSelectionModel().getSelectedItem();
        if(selected != null)
        {
            taskList.getItems().remove(selected);
        }else{
            System.out.println("No task selected...");
        }
    }

    /*Method to exit the program*/
    public void exitProgram(ActionEvent e)
    {
        //Get the current task on the taskList and iterate over them to save them before exit the program
        List<String> currentTask = taskList.getItems();
        //Create new file writer to write on our data.txt file
        try {
            FileWriter writer = new FileWriter(filePath);
            for(String text: currentTask){
                text += "\n";
                writer.write(text);
            }
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        System.exit(0);
    }
    /*This method get executed soon the component of the UI load*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    //Create an scanner object to read data to the file
        try {
            Scanner input = new Scanner(data);
            //Iterate over the inputted in data file
            while(input.hasNextLine()){
                String text = input.nextLine();
                taskList.getItems().add(text);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
