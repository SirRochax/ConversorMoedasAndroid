package com.example.conversormoedas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.editValue = findViewById(R.id.edit_value);
        this.mViewHolder.textDollar = findViewById(R.id.text_dolar);
        this.mViewHolder.textEuro = findViewById(R.id.text_euro);
        this.mViewHolder.buttonCalculate = findViewById(R.id.button_calcular);

        this.mViewHolder.buttonCalculate.setOnClickListener(this);

        this.clearValues();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.button_calcular) {
            String value = this.mViewHolder.editValue.getText().toString();

            if ("".equals(value)) {
                // Mostrar mensagem pro usuario
                Toast.makeText(MainActivity.this, getString(R.string.informe_valor), Toast.LENGTH_LONG).show();
                clearValues();
            } else {
                try {
                    Double real = Double.valueOf(value);
                    this.mViewHolder.textDollar.setText(String.format("$ %.2f", real / 4));
                    this.mViewHolder.textEuro.setText(String.format("€ %.2f", real / 5));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, getString(R.string.erro_number), Toast.LENGTH_LONG).show();
                    clearValues();
                }


            }
        }
    }

    private void clearValues() {
        this.mViewHolder.textDollar.setText("");
        this.mViewHolder.textEuro.setText("");
    }

    private static class ViewHolder {
        EditText editValue;
        TextView textDollar;
        TextView textEuro;
        Button buttonCalculate;
    }
}
