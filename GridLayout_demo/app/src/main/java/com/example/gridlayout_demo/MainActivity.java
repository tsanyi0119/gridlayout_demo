package com.example.gridlayout_demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private AlertDialog dialog;
    private int childCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);

        // 添加第一個子視圖
        addNewChildView();
    }

    public void onChildViewClick(View view) {
        int viewIndex = gridLayout.indexOfChild(view);
        int lastIndex = gridLayout.getChildCount() - 1;

        Log.e("20230905Test", "onChildViewClick: " + viewIndex);

        if (viewIndex == lastIndex) {
            // 判斷只有點擊最後一個子視圖才跳出Dialog
            showImageSelectionDialog(view);
        }
    }

    private void showImageSelectionDialog(final View childView) {
        // 創建AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("功能選擇"); // Dialog標題

        // 獲取Dialog布局
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_image_selection, null);

        // 獲取ImageView並设置Drawable和點擊事件
        ImageView imageView1 = dialogLayout.findViewById(R.id.imageView1);
        ImageView imageView2 = dialogLayout.findViewById(R.id.imageView2);
        ImageView imageView3 = dialogLayout.findViewById(R.id.imageView3);
        ImageView imageView4 = dialogLayout.findViewById(R.id.imageView4);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageForChildView(childView, R.drawable.image_1);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageForChildView(childView, R.drawable.image_2);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageForChildView(childView, R.drawable.image_3);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageForChildView(childView, R.drawable.image_4);
            }
        });

        builder.setView(dialogLayout);

        // 設置取消按鈕
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // 創建Dialog並顯示
        dialog = builder.create();
        dialog.show();
    }

    public void addNewChildView() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View childView = inflater.inflate(R.layout.child_view, null);

        // 將子視圖添加到GridLayout的最後
        gridLayout.addView(childView, gridLayout.getChildCount());

        // 增加計數
        childCount++;

        // 設置GridLayout的列數，以控制每行最多包含兩個子視圖
        gridLayout.setColumnCount(2);

    }

    private void setImageForChildView(View childView, int drawableId) {
        if (childView != null) {
            ImageView imageView = childView.findViewById(R.id.childImageView);
            Drawable drawable = getResources().getDrawable(drawableId);
            imageView.setImageDrawable(drawable);
            dialog.dismiss();
            addNewChildView();
        }
    }
}

