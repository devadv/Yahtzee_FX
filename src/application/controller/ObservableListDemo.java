package application.controller;

import java.util.List;

import application.model.ObservableListDemo_2;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

public class ObservableListDemo {
	public ObservableListDemo_2 demo_2 = new ObservableListDemo_2();
	
    public static void main(String[] args) {

        // Use Java Collections to create the List.
        List<String> list = new ArrayList<String>();

        // Now add observability by wrapping it with ObservableList.
    ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener(new ListChangeListener() {
        	public ObservableListDemo_2 demo_2 = new ObservableListDemo_2();
            @Override
            public void onChanged(ListChangeListener.Change change) {
                System.out.println("Detected a change! ");
                demo_2.setChange(list);
            }
        });

        System.out.println("Before change: " + observableList);
        // Changes to the observableList WILL be reported.
        // This line will print out "Detected a change!"
        observableList.add("item one");
        System.out.println("After add: " + observableList);

        // Changes to the underlying list will NOT be reported
        // Nothing will be printed as a result of the next line.
        list.add("item two");
        System.out.println("After list add: " + observableList);

        observableList.add("item three");

        System.out.println("After item three: " + observableList);


    }
}
