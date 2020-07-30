package com.austraila.online_anytime.Common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.austraila.online_anytime.R;
import com.austraila.online_anytime.activitys.FormActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static android.app.Activity.RESULT_OK;

public class AddPhotoBottomDialogFragment extends BottomSheetDialogFragment{
    TextView photoIcon, localIcon;
    String strtext,formDes,formtitle, scroll, page;
    private int PICK_IMAGE_REQUEST = 1;

    public static AddPhotoBottomDialogFragment newInstance() {
        return new AddPhotoBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        strtext = getArguments().getString("id");
        formDes = getArguments().getString("formDes");
        formtitle = getArguments().getString("formtitle");
        scroll = getArguments().getString("scroll");
        page = getArguments().getString("page");
        View view = inflater.inflate(R.layout.layout_photo_bottom_sheet, container,false);
        photoIcon = view.findViewById(R.id.tv_btn_add_photo_camera);
        localIcon = view.findViewById(R.id.tv_btn_add_photo_gallery);

        photoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FormActivity.class);
                intent.putExtra("camera", "camera");
                intent.putExtra("id", strtext);
                intent.putExtra("des", formDes);
                intent.putExtra("title", formtitle);
                intent.putExtra("scroll", scroll);
                intent.putExtra("page", page);
                startActivity(intent);
                dismiss();
            }
        });

        localIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        // get the views and attach the listener
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            String src = uri.getPath();
            Intent intent = new Intent(getActivity(), FormActivity.class);
            intent.putExtra("filepath", uri.toString());
            intent.putExtra("filestr", src);
            intent.putExtra("id", strtext);
            intent.putExtra("des", formDes);
            intent.putExtra("title", formtitle);
            startActivity(intent);
        }
    }
}