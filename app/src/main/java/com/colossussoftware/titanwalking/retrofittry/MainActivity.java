package com.colossussoftware.titanwalking.retrofittry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.colossussoftware.titanwalking.retrofittry.model.Repo;
import com.colossussoftware.titanwalking.retrofittry.recyclerview.ReposAdapter;
import com.colossussoftware.titanwalking.retrofittry.retrofit.GithubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvRepos;
    private EditText edtUsername;
    private Button btnGetRepos;
    private GithubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GithubService.class);

        edtUsername = findViewById(R.id.edt_username);
        btnGetRepos = findViewById(R.id.button_get_repositories);
        rcvRepos = findViewById(R.id.recyclerview_repos);
        rcvRepos.setHasFixedSize(true);
        rcvRepos.setLayoutManager(new LinearLayoutManager(this));

        btnGetRepos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();

                if(!username.isEmpty()){
                    Call<List<Repo>> callRepos = service.listRepos(username);
                    callRepos.enqueue(new Callback<List<Repo>>() {
                        @Override
                        public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                            List<Repo> repos = response.body();
                            rcvRepos.setAdapter(new ReposAdapter(repos));
                        }

                        @Override
                        public void onFailure(Call<List<Repo>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "onFailure occured.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
