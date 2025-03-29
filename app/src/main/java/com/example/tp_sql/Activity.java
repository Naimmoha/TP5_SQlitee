package com.example.tp_sql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tp_sql.classes.Etudiant;
import com.example.tp_sql.service.EtudiantService;

public class Activity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private Button add;
    private EditText id;
    private Button rechercher;
    private TextView res;
    private Button delete;

    void clear(){
        nom.setText("");
        prenom.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EtudiantService es = new EtudiantService(this);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        add = findViewById(R.id.bn);
        id = findViewById(R.id.id);
        rechercher = findViewById(R.id.load);
        res = findViewById(R.id.res);
        delete = findViewById(R.id.delete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                es.create(new Etudiant(nom.getText().toString(), prenom.getText().toString()));
                clear();

                Intent intent = new Intent(Activity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Etudiant e = es.findById(Integer.parseInt(id.getText().toString()));
                if (e != null) {
                    res.setText(e.getNom() + " " + e.getPrenom());
                } else {
                    res.setText("Étudiant non trouvé.");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText = id.getText().toString();
                if (!idText.isEmpty()) {
                    final int studentId = Integer.parseInt(idText);
                    final Etudiant e = es.findById(studentId);

                    if (e != null) {
                        new AlertDialog.Builder(Activity.this)
                                .setMessage("Êtes-vous sûr de vouloir supprimer cet étudiant ?")
                                .setCancelable(false)
                                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        es.delete(e);
                                        Toast.makeText(Activity.this, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
                                        clear();
                                    }
                                })
                                .setNegativeButton("Non", null)
                                .show();
                    } else {
                        Toast.makeText(Activity.this, "Étudiant non trouvé.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Activity.this, "Veuillez entrer un ID valide.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        @Override
        public boolean onCreateOptionMenu(Menu menu){

        }

        public boolean onOptionsItemSelect(Menu menu){

        }
    }
}
