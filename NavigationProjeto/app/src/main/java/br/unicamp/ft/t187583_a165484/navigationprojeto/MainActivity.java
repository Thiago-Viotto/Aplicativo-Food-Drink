package br.unicamp.ft.t187583_a165484.navigationprojeto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments.BebidasFragment;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments.CardapioFragment;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments.ListaPedidoFragmento;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments.PedidoFragmento;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Fragments.TelaPrincipalFragment;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnBebidaRequest;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Interfaces.OnCardapioRequest;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Bebida;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Comida;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Conexao;
import br.unicamp.ft.t187583_a165484.navigationprojeto.Model.Pedido;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public FragmentManager fragmentManager;
    public Pedido pedido = new Pedido();
    public FirebaseAuth firebaseAuth;
    public FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TelaPrincipalFragment f3 = new TelaPrincipalFragment();
            fragmentTransaction.add(R.id.frame,f3,"telaPrincipal");
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
        firebaseUser = Conexao.getFirebaseUser();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment telaPrincipalFragment = fragmentManager.findFragmentByTag("telaPrincipal");
            //Fragment telaPrincipalFragment = null;
            if (telaPrincipalFragment == null) {
                telaPrincipalFragment = new TelaPrincipalFragment();
            }
                ((TelaPrincipalFragment) telaPrincipalFragment).setOnCardapioRequest(new OnCardapioRequest() {
                    @Override
                    public void request() {
                        Fragment cardapioFragment = fragmentManager.findFragmentByTag("cardapio");
                        if (cardapioFragment == null) {
                            cardapioFragment = new CardapioFragment();
                        }
                        replaceFragment(cardapioFragment, "cardapio");
                    }
                    @Override
                    public void requestList(Comida c, int pos) {
                        boolean naoTem = true;
                        for (Comida co : pedido.getComidas()) {
                            if (co.getId() == c.getId()) {
                                int qtd;
                                qtd = pedido.getComidas().get(pos).getQuantidade();
                                qtd++;
                                pedido.getComidas().get(pos).setQuantidade(qtd);
                                naoTem = false;
                                break;
                            }
                        }
                        if (naoTem) {
                            pedido.getComidas().add(c);
                        }
                        Toast.makeText(MainActivity.this, "Adicionado " + c.getNome(), Toast.LENGTH_SHORT).show();
                    }

                });
                replaceFragment(telaPrincipalFragment, "telaPrincipal");
                Toast.makeText(MainActivity.this, "Você entrou na tela principal", Toast.LENGTH_SHORT).show();
            }else if (id == R.id.nav_cardapio) {
            Fragment cardapioFragment = fragmentManager.findFragmentByTag("cardapio");
            if(cardapioFragment == null) {
                cardapioFragment = new CardapioFragment();
            }

                ((CardapioFragment) cardapioFragment).setOnCardapioRequest(new OnCardapioRequest() {

                    @Override
                    public void request() {

                    }

                    @Override
                    public void requestList(Comida c, int pos) {
                        boolean naoTem = true;
                        for(Comida co : pedido.getComidas()){
                            if(co.getId() == c.getId()){
                                int qtd;
                                qtd = pedido.getComidas().get(pos).getQuantidade();
                                qtd++;
                                pedido.getComidas().get(pos).setQuantidade(qtd);
                                naoTem = false;
                                break;
                            }
                        }
                        if(naoTem) {
                            pedido.getComidas().add(c);
                        }
                        Toast.makeText(MainActivity.this,"Adicionado " + c.getNome(),Toast.LENGTH_SHORT).show();

                    }
                });

                replaceFragment(cardapioFragment, "cardapio");
                Toast.makeText(MainActivity.this, "Você entrou no menu cardápio", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_bebidas) {
            Fragment bebidaFragment = fragmentManager.findFragmentByTag("bebida");
            if(bebidaFragment == null){
                bebidaFragment = new BebidasFragment();
            }
            ((BebidasFragment) bebidaFragment).setOnBebidaRequest(new OnBebidaRequest() {

                @Override
                public void onRequest() {

                }

                @Override
                public void requestList(Bebida b, int pos) {
                    boolean naoTem = true;
                    for(Bebida be : pedido.getBebidas()){
                        if(be.getId() == b.getId()){
                            int qtd;
                            qtd = pedido.getBebidas().get(pos).getQuantidade();
                            qtd++;
                            pedido.getBebidas().get(pos).setQuantidade(qtd);
                            naoTem = false;
                            break;
                        }
                    }
                    if(naoTem) {
                        pedido.getBebidas().add(b);
                    }
                    Toast.makeText(MainActivity.this,"Adicionado " + b.getNome(),Toast.LENGTH_SHORT).show();

                }
            });

            replaceFragment(bebidaFragment,"bebida");
            Toast.makeText(MainActivity.this , "Você entrou no menu bebidas",Toast.LENGTH_SHORT).show();
        }  else if (id == R.id.nav_meucarrinho) {
            Fragment pedidoFragment = fragmentManager.findFragmentByTag("pedido");
            if(pedidoFragment == null){
                pedidoFragment = new PedidoFragmento();
            }
            ((PedidoFragmento)pedidoFragment).setArgumentsPedido(pedido);
            ((PedidoFragmento)pedidoFragment).setArgumentsPedidoBebidas(pedido);
            replaceFragment(pedidoFragment,"pedido");

            Toast.makeText(MainActivity.this , "Você entrou no menu Meu carrinho",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_historico) {

            Fragment historicoFragment = fragmentManager.findFragmentByTag("historico");
            if (historicoFragment == null) {
                historicoFragment = new ListaPedidoFragmento();
            }
            replaceFragment(historicoFragment, "historico");
            Toast.makeText(MainActivity.this, "Você entrou no menu histórico", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment, String tag){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment,tag);
        fragmentTransaction.addToBackStack(null);
        //fragmentManager.popBackStack(tag, fragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.commit();
    }

}
