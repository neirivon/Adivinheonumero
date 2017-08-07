package neirivon.com.br.adivinheonumero;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {
    private EditText palpiteEditText;
    private Integer palpite;
    private TextView informacao;
    private Integer tentativas = 0;
    private Button btReinicio;
    private Intent it;


    Random random = new Random();
    int sorteio = random.nextInt(101);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        informacao = (TextView) findViewById(R.id.msg2);
        palpiteEditText = (EditText) findViewById(R.id.numeroEditText);
        palpiteEditText.setOnKeyListener(ouvinte);
        btReinicio = (Button) findViewById(R.id.btReiniciar);

        btReinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                it = new Intent(MainActivity.this, MainActivity.class);
                startActivity(it);

            }
        });

    }


    private final View.OnKeyListener ouvinte = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                //chamar função
                verificaPalpite();
                return true;
            }
            return false;
        }
    };

    private void verificaPalpite() {

        Integer palpiteinfo = Integer.parseInt(palpiteEditText.getText().toString());

        if (palpiteinfo < this.sorteio) {
            tentativas++;
            informacao.setText("Valor Abaixo do Alvo " + tentativas/2 + " tentativa(s)");

        }
        else if (palpiteinfo > this.sorteio) {
            tentativas++;
            informacao.setText("Valor Acima do Alvo " + tentativas/2 + " tentativa(s)");


        }else{

            //Exibir mensagem de dialogo

            AlertDialog dlgAlert = new AlertDialog.Builder(this).create();
            dlgAlert.setTitle("Parabéns");
            dlgAlert.setMessage("Acertou com " + tentativas / 2 + " tentativas");
            dlgAlert.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    it = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(it);
                }

            });
            dlgAlert.show();

        }
    }
}
