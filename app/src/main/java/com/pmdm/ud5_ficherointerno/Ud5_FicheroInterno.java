package com.pmdm.ud5_ficherointerno;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Ud5_FicheroInterno extends Activity {
	EditText textBox;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ud5__fichero_interno);
		textBox = (EditText) findViewById(R.id.txtText1);

	}

	// Crear un fichero interno de nombre textfile.txt
	public void onClickGuardar(View view) {
		String str = textBox.getText().toString();
		try {
			// fichero para escribir datos. Modo de apertura PRIVADO
			FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);
			// Para convertir una secuencia de caracteres en un flujo de bytes
			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			// escribir la cadena en el archivo
			osw.write(str);
			// asegurar que todos los bytes se escriben en el archivo
			osw.flush();
			// cierra el archivo
			osw.close();

			// mostrar mensaje de archivo guardado
			Toast.makeText(getBaseContext(),
					"Fichero guardado satisfactoriamente", Toast.LENGTH_SHORT)
					.show();
			// borra Edittext
			textBox.setText("");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Acceder y cargar el fichero interno textfile.txt
	public void onClickCargar(View view) {
		try {
			
			//Objeto para leer el fichero
			FileInputStream fIn = openFileInput("textfile.txt");
			//asociamos un bufferReader al fichero 
			BufferedReader br = new BufferedReader(new InputStreamReader(fIn));
			//cadena para almacenar cada línea leída
			String str = null;
			//cadena final 
			String s = "";
			//mientras haya datos
			while ((str = br.readLine()) != null) {
				//añadimos la línea leída a s
				s += str + "\n";
			}
						
			br.close();
			fIn.close();
			
			// establecer EditText en el texto que se ha leído
			textBox.setText(s);

			Toast.makeText(getBaseContext(),
					"Fichero cargado satisfactoriamente", Toast.LENGTH_SHORT)
					.show();
		} catch (IOException ioe) {

			ioe.printStackTrace();
		}
	}

	// Acceder y cargar el fichero estático res/raw/txtfile.txt
	public void onClickCargarRaw(View view) {
		InputStream is = this.getResources().openRawResource(R.raw.txtfile);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = null;
		try {
			while ((str = br.readLine()) != null) {
				Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();

			}
			br.close();
			is.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
