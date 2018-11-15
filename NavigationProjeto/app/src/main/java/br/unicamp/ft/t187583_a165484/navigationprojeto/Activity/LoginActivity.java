package br.unicamp.ft.t187583_a165484.navigationprojeto.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.unicamp.ft.t187583_a165484.navigationprojeto.MainActivity;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Conexao;
import br.unicamp.ft.t187583_a165484.navigationprojeto.R;

public class LoginActivity extends AppCompatActivity {
    private Button btnEntrar, btnCadastrar,btnDeslogar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private EditText email,senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrarUsuario);
        email = findViewById(R.id.edUser);
        senha = findViewById(R.id.edSenha);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
                startActivity(intent);
            }
        });


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = senha.getText().toString();
                String mail = email.getText().toString();
                if((mail.equals(""))||(senha.equals("")))
                    Toast.makeText(LoginActivity.this,"Você precisa digitar a senha e o usuário",Toast.LENGTH_SHORT).show();
                else
                    logar(mail,password);
            }
        });
    }

    public void logar(String email, String senha){
        firebaseAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                    firebaseUser = firebaseAuth.getCurrentUser();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
        firebaseUser = Conexao.getFirebaseUser();
    }

}
