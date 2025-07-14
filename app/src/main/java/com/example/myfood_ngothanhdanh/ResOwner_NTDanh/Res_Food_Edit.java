package com.example.myfood_ngothanhdanh.ResOwner_NTDanh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.myfood_ngothanhdanh.Model_NTDanh.cate_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Res_Food_Edit extends AppCompatActivity {
    private Button btn_cancel_NTDanh, btn_save_NTDanh, btn_delete_NTDanh;
    private ImageButton btn_uploadImage_NTDanh;
    private Spinner spinner_cate_NTDanh;
    private EditText edt_foodName_NTDanh, edt_foodPrice_NTDanh, edt_foodDes_NTDanh;
    private Bundle bundle;
    private food_NTDanh food_ntDanh = new food_NTDanh();
    private List<cate_NTDanh> cate_ntDanhList = new ArrayList<>();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private String cate_id_seleted, uploadedImageUrl;
    private Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_res_food_edit);
        btn_cancel_NTDanh = findViewById(R.id.btn_cancel_NTDanh);
        btn_save_NTDanh = findViewById(R.id.btn_save_NTDanh);
        btn_delete_NTDanh = findViewById(R.id.btn_delete_NTDanh);
        btn_uploadImage_NTDanh = findViewById(R.id.btn_uploadImage_NTDanh);
        spinner_cate_NTDanh = findViewById(R.id.spinner_cate_NTDanh);
        edt_foodName_NTDanh = findViewById(R.id.edt_foodName_NTDanh);
        edt_foodPrice_NTDanh = findViewById(R.id.edt_foodPrice_NTDanh);
        edt_foodDes_NTDanh = findViewById(R.id.edt_foodDes_NTDanh);
        getParcel();

        btn_delete_NTDanh.setOnClickListener(view -> {
            deleteFood();
        });

        btn_save_NTDanh.setOnClickListener(view -> {updateFood();});

        btn_uploadImage_NTDanh.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void getParcel(){
        Intent intent = getIntent();
        food_ntDanh = intent.getParcelableExtra("food_ntDanh");
        loadData();
    }
    private void loadData(){
        edt_foodName_NTDanh.setText(food_ntDanh.getFood_name());
        edt_foodDes_NTDanh.setText(food_ntDanh.getFood_des());
        edt_foodPrice_NTDanh.setText(String.valueOf(food_ntDanh.getFood_price()));
        Glide.with(this).load(food_ntDanh.getFood_img()).into(btn_uploadImage_NTDanh);
        uploadedImageUrl = food_ntDanh.getFood_img();
        getCate();
    }
    private void getCate() {
        firestore.collection("Categories").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                cate_NTDanh cate = new cate_NTDanh();
                cate.setCate_id(documentSnapshot.getId());
                cate.setCate_name(documentSnapshot.getString("cate_name"));
                cate.setCate_img(documentSnapshot.getString("cate_img"));
                cate_ntDanhList.add(cate);
            }
            initSpinner();
        });
    }

    private void initSpinner() {
        cate_NTDanh cate = new cate_NTDanh();
        cate.setCate_name("Pick the category");
        cate_ntDanhList.add(0, cate);
        ArrayAdapter<cate_NTDanh> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, cate_ntDanhList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cate_NTDanh.setAdapter(adapter);

        int selected_cate = 0;
        String foodCateId = food_ntDanh.getCate_id();
        for (int i = 0; i < cate_ntDanhList.size(); i++) {
            String cateId = cate_ntDanhList.get(i).getCate_id();
            if (cateId != null && foodCateId != null && cateId.equals(foodCateId)) {
                selected_cate = i;
                break;
            }
        }
        spinner_cate_NTDanh.setSelection(selected_cate);

        spinner_cate_NTDanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cate_NTDanh cate =cate_ntDanhList.get(i);
                cate_id_seleted = cate.getCate_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void deleteFood(){
        firestore.collection("Foods").document(food_ntDanh.getFood_id()).delete().addOnSuccessListener(aVoid -> {
            finish();
            Toast.makeText(this, "Delete Successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        });
    }
    private void updateFood(){
        String foodName = edt_foodName_NTDanh.getText().toString();
        String foodDes = edt_foodDes_NTDanh.getText().toString();
        String foodPriceText = edt_foodPrice_NTDanh.getText().toString();
        if (foodName.isEmpty()|| foodPriceText.isEmpty() || foodDes.isEmpty() || cate_id_seleted == null| uploadedImageUrl == null) {
            Toast.makeText(this, "Fill full information please", Toast.LENGTH_SHORT).show();
        }else {
            double foodPrice;
            try {
                foodPrice = Double.parseDouble(foodPriceText);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Giá phải là số hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }
            Map<String, Object> food = new HashMap<>();
            food.put("food_name", foodName);
            food.put("food_price", foodPrice);
            food.put("food_des", foodDes);
            food.put("cate_id", cate_id_seleted);
            food.put("food_img", uploadedImageUrl);
            food.put("res_id", food_ntDanh.getRes_id());

            firestore.collection("Foods").document(food_ntDanh.getFood_id()).update(food).addOnSuccessListener(documentReference -> {
                Toast.makeText(this, "Upload success", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                byte[] imageBytes = new byte[inputStream.available()];
                inputStream.read(imageBytes);
                String encodedImage = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

                btn_uploadImage_NTDanh.setImageURI(imageUri); // Hiển thị ảnh đã chọn
                uploadToImageKit(encodedImage);               // Upload ảnh base64
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadToImageKit(String base64Image) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("file", "data:image/png;base64," + base64Image)
                .add("fileName", "uploaded_image.png")
                .build();

        String privateAPIKey = "private_4eNGeoscCxdl3up9t+V8tQYfHEo=";
        String credentials = Base64.encodeToString((privateAPIKey + ":").getBytes(), Base64.NO_WRAP);  // ":" bắt buộc ở cuối

        Request request = new Request.Builder()
                .url("https://upload.imagekit.io/api/v1/files/upload")
                .addHeader("Authorization", "Basic " + credentials)
                .post(formBody)
                .build();

        Log.d("ImageKit", "Uploading image...");

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Log.e("ImageKit", "Upload failed"));
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(res);
                        uploadedImageUrl = jsonObject.getString("url");

                        runOnUiThread(() -> {
                            Log.d("ImageKit", "Upload success! Image URL: " + uploadedImageUrl);
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ImageKit", "Upload failed: " + e.getMessage());
                    }
                } else {
                    Log.e("ImageKit", "Upload failed: " + response.message());
                }
            }
        });
    }

}