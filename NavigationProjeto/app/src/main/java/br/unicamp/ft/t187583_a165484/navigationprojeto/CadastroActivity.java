package br.unicamp.ft.t187583_a165484.navigationprojeto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class CadastroActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText date,nome,cpf,endereco,cidade,telefone,email,senha;
    private RadioGroup tipoPessoa;
    private DatePickerDialog datePickerDialog;
    private Spinner spinnerPais;
    private Spinner spinnerEstados;
    private Button btnCalcular;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

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
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        btnCalcular = findViewById(R.id.btnCadastrar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeEmpresa = nome.getText().toString();
                String cpfCnpj = cpf.getText().toString();
                String endereco2 = endereco.getText().toString();
                String cidade2 = cidade.getText().toString();
                String fone = telefone.getText().toString();
                String mail = email.getText().toString().trim();
                String password = senha.getText().toString().trim();
                criarFirebaseCadastro(mail,password);
                criaData();
                String txtPais = criaSpinner(spinnerPais);  //cria spinner dos paises
                String txtEstados = criaSpinner(spinnerEstados);
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
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        firebaseUser = Conexao.getFirebaseUser();
    }

    private void criarFirebaseCadastro(String email, String senha){
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    alert("Usuário cadastrado com sucesso");
                    Intent intent = new Intent(CadastroActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    alert("Erro ao cadastrar o usuário");
                }
            }
        });
    }

    private void alert(String msn){
        Toast.makeText(CadastroActivity.this,msn,Toast.LENGTH_SHORT).show();
    }

    public String criaSpinner(Spinner spinner){
        String txt = spinner.getSelectedItem().toString();
        return txt;
    }

    /* DATA CALENDÁRIO  ********************************************************************************************************************/
    public void criaData(){
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // ano
                int mMonth = c.get(Calendar.MONTH); // mes
                int mDay = c.get(Calendar.DAY_OF_MONTH); // dia

                datePickerDialog = new DatePickerDialog(CadastroActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                        // seta dia, mes e ano com barras
                        date.setText(dia + "/" + (mes + 1) + "/" + ano);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

}
