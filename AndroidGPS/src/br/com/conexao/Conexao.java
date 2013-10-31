package br.com.conexao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import br.com.gps.MainActivity;
import br.com.gps.R;
import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Conexao extends Activity {

    EditText editTextHost = null;
    Button buttonSalvar = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conexao);

        //
        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(btnMessageOnClickListener);
        editTextHost = (EditText) findViewById(R.id.hostServidor);

        ler();

    }
    private OnClickListener btnMessageOnClickListener = new OnClickListener() {
        public void onClick(View v) {
            gravar();
            Toast.makeText(Conexao.this, "Salvo", Toast.LENGTH_LONG).show();
        }
    };

    private void gravar() {
        // Cria um objeto da classe java.util.Properties
        Properties properties = new Properties();

        // setando as propriedades(key) e os seus valores(value)

        properties.setProperty("prop.server.host", editTextHost.getText()
                                                                                                                                                    .toString());

        try {
            // Criamos um objeto FileOutputStream

            FileOutputStream file = new FileOutputStream(this.getFilesDir() + "/dados.xml");

            // grava os dados no arquivo
            properties.storeToXML(file, "FILE JDBC PROPERTIES:");
            //properties.store(file, "FILE JDBC PROPERTIES:");
            // fecha o arquivo
            file.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void ler() {
        try {
            String host; // Variavel que guardará o host do servidor.

            Properties props = new Properties();
            FileInputStream file = new FileInputStream(this.getFilesDir() + "/dados.xml");
            props.loadFromXML(file);
            //props.load(file);
            host = props.getProperty("prop.server.host");
            editTextHost.setText(host);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
