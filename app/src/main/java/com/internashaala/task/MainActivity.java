
package com.internashaala.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    MRoomDatabase mRoomDatabase;
    RecyclerView recyclerView;
    TitleRecyclerAdapter titleRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler);
        initializeMRoomDatabase();

        setRecyclerView();
        updateRecyclerView();


        setDialog();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

            }
        });

    }

    int userId;

    private void setRecyclerView() {
        titleRecyclerAdapter = new TitleRecyclerAdapter(getApplicationContext(), new TitleRecyclerInterface() {
            @Override
            public void onImageClick(int userID) {
                userId = userID;

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, 2);


            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(titleRecyclerAdapter);
    }

    private void updateRecyclerView() {
        List<User> usersList = mRoomDatabase.userDao().getAllData();
        titleRecyclerAdapter.updateList(usersList);
    }

    private void initializeMRoomDatabase() {
        mRoomDatabase = MRoomDatabase.initialize(getApplicationContext());
    }

    EditText dTitleEdtTxt;
    AppCompatButton dOkBtn, dCancelBtn;
    Dialog dialog;

    public void setDialog() {
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);

        dTitleEdtTxt = dialog.findViewById(R.id.title_edt_txt);
        dOkBtn = dialog.findViewById(R.id.ok);
        dCancelBtn = dialog.findViewById(R.id.cancel);

        dCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dTitleEdtTxt.getText().toString().trim().isEmpty()) {
                    dTitleEdtTxt.setError("Empty Field");
                    return;
                }

                User user = new User();
                user.setTitle(dTitleEdtTxt.getText().toString());


                mRoomDatabase.userDao().insertTitle(user);
                updateRecyclerView();
                dialog.dismiss();


            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK && requestCode == 2) {
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        Uri imageUri = clipData.getItemAt(i).getUri();
                        Path path = new Path();
                        path.setPath(imageUri.getPath());
                        path.setUserID(userId);

                        mRoomDatabase.userDao().insertPath(path);
                        titleRecyclerAdapter.notifyDataSetChanged();
                        // your code for multiple image selection
                    }
                } else {
                    Uri uri = data.getData();
                    Path path = new Path();
                    path.setPath(uri.getPath());
                    path.setUserID(userId);
                    // your codefor single image selection

                     mRoomDatabase.userDao().insertPath(path);
                     titleRecyclerAdapter.notifyDataSetChanged();
                }
            }
        }

}