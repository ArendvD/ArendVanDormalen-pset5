package arend.arendvandormalen_pset5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-12-02.
 * Activity for showing a single list.
 */

public class SingleListActivity extends AppCompatActivity {

    ListView listView;
    DBHelper dbHelper;
    String listTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_list);
        listTitle = savedInstanceState.getString("listTitle");
        showAll();
    }


    @Override
    protected void onResume(){
        super.onResume();
        showAll();
    }

    public void addTask(View view){
        dbHelper = new DBHelper(this, listTitle);
        Toast.makeText(this, "Adding Task", Toast.LENGTH_SHORT).show();
        EditText newTaskView = (EditText)findViewById(R.id.add_bar);
        String newTaskString = newTaskView.getText().toString();
        Log.d("Adding", newTaskString);

        // Create temporary Task object to pass to helper class
        Task newTask = new Task();
        newTask.setTask(newTaskString);
        newTask.setChecked("no");

        // Add task to database
        dbHelper.create(newTask);
        dbHelper.close();

        // Empty input section for user after adding task
        newTaskView.setText("");
        showAll();
    }

    public void showAll(){

        final DBHelper dbHelper = new DBHelper(this, listTitle);
        ArrayList<Task> toDoList = dbHelper.read();

        TaskAdapter taskAdapter = new TaskAdapter(this, toDoList);

        listView.setAdapter(taskAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Task selectedTask = (Task)adapterView.getItemAtPosition(position);

                editTask(selectedTask, view);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id){

                Task deleteTask = (Task) adapterView.getItemAtPosition(position);
                String deleteID = deleteTask.getId();

                dbHelper.delete(deleteID);
                showAll();

                return true;
            }
        });

    }

    public void editTask(final Task task, final View view){


        final ViewSwitcher viewSwitcher = (ViewSwitcher)view.findViewById(R.id.switcher);
        viewSwitcher.showNext();

        // Hide Add-button when editing
        final LinearLayout bottomBar = (LinearLayout)findViewById(R.id.bottom_bar);
        bottomBar.setVisibility(View.GONE);

        final EditText editText = (EditText)view.findViewById(R.id.task_text_edit);
        editText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(event.getAction() == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER){
                    String newTaskText = editText.getText().toString();
                    task.setTask(newTaskText);

                    final DBHelper dbHelper = new DBHelper(SingleListActivity.this, listTitle);
                    dbHelper.update(task);

                    // Resetting
                    viewSwitcher.showNext();
                    bottomBar.setVisibility(View.VISIBLE);

                    showAll();

                    return true;
                }
                return false;
            }
        });


    }

}
