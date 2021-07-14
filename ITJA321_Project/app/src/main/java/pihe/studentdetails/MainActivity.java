package pihe.studentdetails;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * @author Donovan van Heerden | EL2014-0043
 */
public class MainActivity extends AppCompatActivity {
    // Database
    DBHandler dbHandler;

    // TextViews
    TextView txtStudentId;
    TextView txtFirstName;
    TextView txtLastName;
    TextView txtContactNumber;
    TextView txtAddress;

    // Buttons
    private Button btnAdd;
    private Button btnSave;
    private Button btnSearch;
    private Button btnClear;

    /**
     * onCreate, handles all implementation for the application. Assigns onClick
     * listeners and functionality.
     * 
     * @param savedInstanceState state of application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new DBHandler(getApplicationContext());

        txtStudentId = findViewById(R.id.txtStudentId);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtContactNumber = findViewById(R.id.txtContactNumber);
        txtAddress = findViewById(R.id.txtAddress);

        btnAdd = findViewById(R.id.btnAdd);
        btnSave = findViewById(R.id.btnSave);
        btnSearch = findViewById(R.id.btnSearch);
        btnClear = findViewById(R.id.btnClear);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDatabase(view);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search(view);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        setFormState(false);
    }

    /**
     * Used to easily disable and enable specific elements on the form
     * 
     * @param enabled changes the state of elements from disabled to enabled and
     *                vice versa.
     */
    private void setFormState(boolean enabled) {
        // Set TextViews state
        txtFirstName.setEnabled(enabled);
        txtLastName.setEnabled(enabled);
        txtContactNumber.setEnabled(enabled);
        txtAddress.setEnabled(enabled);

        // Set Buttons state
        btnSave.setEnabled(enabled);
        btnClear.setEnabled(enabled);
        // Inverse state
        btnAdd.setEnabled(!enabled);
        btnSearch.setEnabled(!enabled);
    }

    /**
     * Only purpose is to execute the setFormState function to change the
     * application state to allow for saving of data.
     */
    private void add() {
        setFormState(true);
    }

    /**
     * Builds up the Student object from the values entered into each TextView and
     * calls the addStudent function on the DbHandler.
     * 
     * @param view Current view
     */
    private void addToDatabase(View view) {
        // Get values from TextViews
        try {
            int id = Integer.parseInt(txtStudentId.getText().toString());
            String firstName = txtFirstName.getText().toString();
            String lastName = txtLastName.getText().toString();
            String contactNumber = txtContactNumber.getText().toString();
            String address = txtAddress.getText().toString();

            Student student = new Student(id, firstName, lastName, contactNumber, address);

            dbHandler.addStudent(student);

            // Clear the TextViews
            clear();
            // Disable Form Fields, btnAdd and btnClear
            setFormState(false);
        } catch (NumberFormatException nfe) {
            Snackbar.make(view, "Student ID must be a valid number", Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * Used to search for a student in the database by passing in the student Id. If
     * no Id is passed, a message is displayed to the user.
     * 
     * @param view Current view
     */
    public void search(View view) {
        try {
            int id = Integer.parseInt(txtStudentId.getText().toString());

            Student student = dbHandler.search(id);

            txtFirstName.setText(student.getFirstName());
            txtLastName.setText(student.getLastName());
            txtContactNumber.setText(student.getContactNumber());
            txtAddress.setText(student.getAddress());

            btnClear.setEnabled(true);
        } catch (NumberFormatException nfe) {
            Snackbar.make(view, "Student ID must be a valid number", Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * Used for clearing the TextViews
     */
    private void clear() {
        txtStudentId.setText(null);
        txtFirstName.setText(null);
        txtLastName.setText(null);
        txtContactNumber.setText(null);
        txtAddress.setText(null);
    }
}
