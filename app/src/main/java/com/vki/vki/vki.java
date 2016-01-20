package com.vki.vki;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class vki extends AppCompatActivity {

    Button btnHesapla;
    EditText editTextBoy;
    EditText editTextKilo;
    TextView textVkiSonuc;
    TextView textIdealKilo;
    TextView textSeviyenizSonuc;
    CheckBox checkboxKadin;
    CheckBox checkboxErkek;
    TextView textViewVki;
    TextView textViewIdealKilo;
    TextView textViewSeviyeniz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vki);


        btnHesapla = (Button) findViewById(R.id.btnHesapla);
        editTextBoy = (EditText) findViewById(R.id.editTextBoy);
        editTextKilo = (EditText) findViewById(R.id.editTextKilo);
        textVkiSonuc = (TextView) findViewById(R.id.textVkiSonuc);
        textIdealKilo = (TextView) findViewById(R.id.textIdealKiloSonuc);
        textSeviyenizSonuc = (TextView) findViewById(R.id.textSeviyenizSonuc);
        checkboxKadin = (CheckBox) findViewById(R.id.checkKadin);
        checkboxErkek = (CheckBox) findViewById(R.id.checkErkek);
        textViewVki = (TextView) findViewById(R.id.textViewVki);
        textViewIdealKilo = (TextView) findViewById(R.id.textViewIdealKilo);
        textViewSeviyeniz = (TextView) findViewById(R.id.textViewSeviyeniz);



        textIdealKilo.setVisibility(TextView.INVISIBLE);
        textVkiSonuc.setVisibility(TextView.INVISIBLE);
        textSeviyenizSonuc.setVisibility(TextView.INVISIBLE);
        textViewVki.setVisibility(TextView.INVISIBLE);
        textViewIdealKilo.setVisibility(TextView.INVISIBLE);
        textViewSeviyeniz.setVisibility(TextView.INVISIBLE);


        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                int boy;
                int kilo;
                String vki;
                double carpan = 10000;
                double deger;

                String boyStr = editTextBoy.getText().toString();
                String kiloStr = editTextKilo.getText().toString();

                if (!((boyStr == null || boyStr.isEmpty()) && (kiloStr == null || kiloStr.isEmpty())) && (checkboxKadin.isChecked() || checkboxErkek.isChecked())) {

                    boy = Integer.parseInt(boyStr);
                    kilo = Integer.parseInt(kiloStr);


                    deger = boy * boy;

                    deger = kilo / deger;

                    deger = deger * carpan;

                    vki = new BigDecimal(deger).setScale(2, BigDecimal.ROUND_HALF_UP) + "";

                    textVkiSonuc.setText(vki);
                    //TODO erkekte -104 - bayanda -108 cikarilacak

                    int idealKilo = getIdealKilo(boy);

                    textIdealKilo.setText("" + idealKilo + " Kg");

                    // TODO vki sonuclari
                    String mesaj = "" + getVkiMessage(deger);

                    textSeviyenizSonuc.setText(mesaj);


                    textIdealKilo.setVisibility(TextView.VISIBLE);
                    textVkiSonuc.setVisibility(TextView.VISIBLE);
                    textSeviyenizSonuc.setVisibility(TextView.VISIBLE);
                    textIdealKilo.setVisibility(TextView.VISIBLE);
                    textViewIdealKilo.setVisibility(TextView.VISIBLE);
                    textViewSeviyeniz.setVisibility(TextView.VISIBLE);
                    textViewVki.setVisibility(TextView.VISIBLE);
                } else {

                    Context context = getApplicationContext();
                    Toast.makeText(context, "Lütfen boy ve kilo bilginizi giriniz!", Toast.LENGTH_LONG).show();
                }


            }
        });

        checkboxKadin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    checkboxKadin.setChecked(true);
                    checkboxErkek.setChecked(false);
                }
            }
        });


        checkboxErkek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    checkboxErkek.setChecked(true);
                    checkboxKadin.setChecked(false);
                }
            }
        });


    }

    private String getVkiMessage(double deger) {
        int t = ((int) (deger * 10));
        if (t < 185) {
            return "Zayif";
        } else if (t >= 185 && t <= 249) {
            return "Normal Kilolu";
        } else if (t >= 250 && t <= 299) {
            return "Fazla Kilolu";
        } else if (t >= 300 && t <= 349) {
            return "I. Derecede Obez";
        } else if (t >= 350 && t <= 399) {
            return "II. Derecede Obez";
        } else if (t >= 400) {
            return "III. Derecede Morbid Obez";
        }
        return null;
    }

    private int getIdealKilo(int boy) {


        if (true == checkboxKadin.isChecked()) {
            return boy - 108;
        } else if (true == checkboxErkek.isChecked()) {
            return boy - 104;
        }

        return 0;
    }
}