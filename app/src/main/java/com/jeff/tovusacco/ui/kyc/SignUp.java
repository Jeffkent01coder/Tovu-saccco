package com.jeff.tovusacco.ui.kyc;

import static com.jeff.tovusacco.backend.MainApp.Register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.backend.MainApp;
import com.jeff.tovusacco.databinding.ActivitySignUpBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUp extends AppCompatActivity {
    private ActivitySignUpBinding binding;

    private Calendar calendar = Calendar.getInstance();
    private TextView tvDate;
    private Button btnshowdatepicker;
    private int gender = -1;
//    private MainApp mainApp;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        mainApp = new MainApp();

        tvDate = binding.tvSelectDate;
        btnshowdatepicker = binding.btnshowDatePicker;

        btnshowdatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        RadioButton maleRadioButton = binding.maleRadioButton;
        RadioButton femaleRadioButton = binding.femaleRadioButton;

        maleRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = 0;
            }
        });

        femaleRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = 1;
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input data
                String firstName = binding.firstNameEt.getText().toString().trim();
                String middleName = binding.middleNameEt.getText().toString().trim();
                String lastName = binding.lastNameEt.getText().toString().trim();
                String phoneNumber = binding.phoneNumberEt.getText().toString().trim();
                String idNumber = binding.idNumberEt.getText().toString().trim();
                String email = binding.emailEt.getText().toString().trim();
                String password = binding.passEt.getText().toString().trim();
                String dateOfBirth = tvDate.getText().toString().trim();

                // Perform registration asynchronously
                new RegistrationTask().execute(phoneNumber, idNumber, firstName, middleName, lastName, "1", dateOfBirth, email);
            }
        });


        binding.tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));
                finish();
            }
        });
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                SignUp.this, (view, year, monthOfYear, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String formattedDate = dateFormat.format(selectedDate.getTime());
            tvDate.setText(formattedDate);
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    private class RegistrationTask extends AsyncTask<String, Void, String> {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected String doInBackground(String... params) {
            String phoneNumber = "254" + params[0].substring(1);
            String idNumber = params[1];
            String firstName = params[2];
            String middleName = params[3];
            String lastName = params[4];
            String gender = params[5];
            String dateOfBirth = params[6];
            String email = params[7];

            Log.d("RegistrationTask", "Phone Number: " + phoneNumber + ", ID Number: " + idNumber + ", First Name: " + firstName + ", Middle Name: " + middleName + ", Last Name: " + lastName + ", Gender: " + gender + ", Date of Birth: " + dateOfBirth + ", Email: " + email);

            try {
                return "Reg res: " + MainApp.Register(phoneNumber, idNumber, firstName, middleName, lastName, gender, dateOfBirth, email);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Toast.makeText(SignUp.this, result, Toast.LENGTH_SHORT).show();
                // Handle successful registration
            } else {
                Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                // Handle registration failure
            }
        }
    }

}
