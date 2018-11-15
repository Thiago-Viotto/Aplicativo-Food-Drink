package br.unicamp.ft.t187583_a165484.navigationprojeto.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.unicamp.ft.t187583_a165484.navigationprojeto.MainActivity;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Cliente;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Conexao;
import br.unicamp.ft.t187583_a165484.navigationprojeto.R;

public class CadastroActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText date,nome,cpf,endereco,cidade,telefone,email,senha;
    private RadioGroup tipoPessoa;
    private Spinner spinnerPais;
    private Spinner spinnerEstados;
    private Button btnCalcular;
    public FirebaseAuth auth, auth2;
    public FirebaseUser firebaseUser, firebaseUser2;
    private DatabaseReference mFirebaseDatabaseReference;
    Cliente cliente = new Cliente();
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        spinnerPais = findViewById(R.id.spinnerPais);
        spinnerEstados = findViewById(R.id.spinnerEstado);
        tipoPessoa = findViewById(R.id.tipopessoa);
        nome = findViewById(R.id.nomeEmpresa);
        cpf = findViewById(R.id.cpf);
        endereco = findViewById(R.id.endereco);
        cidade = findViewById(R.id.cidade);
        telefone = findViewById(R.id.telefone);
        date = findViewById(R.id.date);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        btnCalcular = findViewById(R.id.btnCadastrar);
        auth = FirebaseAuth.getInstance();
        auth2 = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliente.setNome(nome.getText().toString());
                cliente.setCPF(cpf.getText().toString());
                cliente.setEndereco(endereco.getText().toString());
                cliente.setCidade(cidade.getText().toString());
                cliente.setTelefone(telefone.getText().toString());
                cliente.setEmail(email.getText().toString().trim());
                cliente.setSenha(senha.getText().toString().trim());
                cliente.setData(date.getText().toString().trim());
                cliente.setPais(criaSpinner(spinnerPais));  //cria spinner dos paises
                cliente.setEstado(criaSpinner(spinnerEstados));

                criarFirebaseCadastro(email.getText().toString().trim(),senha.getText().toString().trim(),cliente);

                tipoPessoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean checked = ((RadioButton)view).isChecked();
                        switch (view.getId()){
                            case R.id.fisica:
                                if(checked) {
                                    Toast.makeText(CadastroActivity.this, "Você selecionou pessoa física", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            case R.id.juridica:
                                if(checked) {
                                    Toast.makeText(CadastroActivity.this, "Você selecionou pessoa jurídica", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                        }
                    }
                });
                Intent intent = new Intent(CadastroActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        firebaseUser = Conexao.getFirebaseUser();
    }

    public String criarFirebaseCadastro(String email, String senha, Cliente c){
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser = auth.getCurrentUser();
                            cliente.setId(firebaseUser.getUid());
                            mFirebaseDatabaseReference.child("cadastros").push().setValue(cliente);
                            Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(CadastroActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
        return id;
    }



    public void alert(String msn){
        Toast.makeText(CadastroActivity.this,msn,Toast.LENGTH_SHORT).show();
    }

    public String criaSpinner(Spinner spinner){
        String txt = spinner.getSelectedItem().toString();
        return txt;
    }


}
