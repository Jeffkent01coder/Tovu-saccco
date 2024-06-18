package com.jeff.tovusacco.ui.goals.groupgoals;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jeff.tovusacco.R;
import com.jeff.tovusacco.bottomSheets.GoalConfirmationBottomSheet;
import com.jeff.tovusacco.databinding.ActivityNewGroupGoalBinding;
import com.jeff.tovusacco.ui.Home;

import java.util.ArrayList;
import java.util.List;

public class NewGroupGoal extends AppCompatActivity {

    private ActivityNewGroupGoalBinding binding;

    private Uri goalUri;
    private final ActivityResultLauncher<String> selectImage =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                binding.selectedImage.setVisibility(View.VISIBLE);
                goalUri = uri;
                binding.selectedImage.setImageURI(goalUri);
            });

    private final List<String> members = new ArrayList<>();
    private final List<String> admins = new ArrayList<>();
    private final int maxMembers = 15;
    private final int maxAdmins = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewGroupGoalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.selectImage.setOnClickListener(v -> selectImage.launch("image/*"));

        binding.btnCreateGoal.setOnClickListener(v -> showConfirmationDialog());

        binding.btnSaveAndContinueLater.setOnClickListener(v -> {
            startActivity(new Intent(NewGroupGoal.this, Home.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        binding.selectContact.setOnClickListener(v -> {
            if (members.size() + admins.size() < maxMembers) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED) {
                    pickContact();
                } else {
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            CONTACT_PERMISSION_REQUEST_CODE
                    );
                }
            } else {
                Toast.makeText(
                        this,
                        "You can only add up to 15 members including up to 3 admins",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void pickContact() {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        contactPickerLauncher.launch(contactPickerIntent);
    }

    private void showConfirmationDialog() {
        GoalConfirmationBottomSheet dialog = new GoalConfirmationBottomSheet();
        dialog.show(getSupportFragmentManager(), "ConfirmationDialog");
    }

    private final ActivityResultLauncher<Intent> contactPickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri contactUri = result.getData().getData();
                    if (contactUri != null) {
                        String[] projection = {ContactsContract.Contacts.DISPLAY_NAME};
                        try (Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null)) {
                            if (cursor != null && ((Cursor) cursor).moveToFirst()) {
                                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                                String contactName = cursor.getString(nameIndex);
                                addContactToGroup(contactName);
                            }
                        }
                    }
                }
            });

    private void addContactToGroup(String contactName) {
        if (members.size() + admins.size() < maxMembers) {
            if (admins.size() < maxAdmins) {
                admins.add(contactName);
            } else {
                members.add(contactName);
            }
            updateGroupMembersUI();
        } else {
            Toast.makeText(
                    this,
                    "You can only add up to 15 members including up to 3 admins",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void updateGroupMembersUI() {
        LinearLayout contactContainer = findViewById(R.id.contactContainer);
        contactContainer.removeAllViews();

        for (String contact : admins) {
            View view = getLayoutInflater().inflate(R.layout.selected_contact_item, contactContainer, false);
            TextView contactNameView = view.findViewById(R.id.contactName);
            Button removeButton = view.findViewById(R.id.removeContact);

            contactNameView.setText(contact);
            removeButton.setOnClickListener(v -> removeContactFromGroup(contact));

            contactContainer.addView(view);
        }
        for (String contact : members) {
            View view = getLayoutInflater().inflate(R.layout.selected_contact_item, contactContainer, false);
            TextView contactNameView = view.findViewById(R.id.contactName);
            Button removeButton = view.findViewById(R.id.removeContact);

            contactNameView.setText(contact);
            removeButton.setOnClickListener(v -> removeContactFromGroup(contact));

            contactContainer.addView(view);
        }
    }

    private void removeContactFromGroup(String contactName) {
        admins.remove(contactName);
        members.remove(contactName);
        updateGroupMembersUI();
    }

    private static final int CONTACT_PERMISSION_REQUEST_CODE = 1001;

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CONTACT_PERMISSION_REQUEST_CODE) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                pickContact();
            } else {
                Toast.makeText(this, "Permission denied to read contacts", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
