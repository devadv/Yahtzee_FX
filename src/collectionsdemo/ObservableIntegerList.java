package collectionsdemo;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

public class ObservableIntegerList {

    public static void main(String[] args) {

        // Use Java Collections to create the List.
        List<Integer> list = new ArrayList<Integer>();

        // Now add observability by wrapping it with ObservableList.
    ObservableList<Integer> observableList = FXCollections.observableList(list);
        observableList.addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change change) {
                System.out.println("Detected a change! ");
                Iterator it = observableList.iterator();
                while(it.hasNext()){
                	System.out.println(it.next());
                }
            }
        });
        System.out.println("Before change: " +observableList);
        // Changes to the observableList WILL be reported.
        // This line will print out "Detected a change!"
        observableList.add(1);
        System.out.println("After add: " + observableList);

        observableList.add(22);
        System.out.println("After add: " + observableList);
        // Changes to the underlying list will NOT be reported
        // Nothing will be printed as a result of the next line.
        list.add(25);
        System.out.println("After list add: " + observableList);

        observableList.add(50);

        System.out.println("After item four: " + observableList);

        /*observableList.remove(1);
        System.out.println("After remove: " + observableList);*/

        System.out.println("Size: "+observableList.size());

    }
}
