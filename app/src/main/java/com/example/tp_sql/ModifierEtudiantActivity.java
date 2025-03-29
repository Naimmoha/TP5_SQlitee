package com.example.tp_sql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_sql.classes.Etudiant;
import com.example.tp_sql.service.EtudiantService;

public class ModifierEtudiantActivity extends AppCompatActivity {

    private EditText nomEditText, prenomEditText;
    private Button btnEnregistrer;
    private EtudiantService etudiantService;
    private Etudiant etudiant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_etudiant);

        nomEditText = findViewById(R.id.nom_edit_text);
        prenomEditText = findViewById(R.id.prenom_edit_text);
        btnEnregistrer = findViewById(R.id.btn_enregistrer);

        etudiantService = new EtudiantService(this);

        int etudiantId = getIntent().getIntExtra("etudiant_id", -1);
        if (etudiantId != -1) {
            etudiant = etudiantService.findById(etudiantId);
            nomEditText.setText(etudiant.getNom());
            prenomEditText.setText(etudiant.getPrenom());
        }

        btnEnregistrer.setOnClickListener(v -> {
            String nom = nomEditText.getText().toString();
            String prenom = prenomEditText.getText().toString();

            if (!nom.isEmpty() && !prenom.isEmpty()) {
                etudiant.setNom(nom);
                etudiant.setPrenom(prenom);
                etudiantService.update(etudiant);
                Toast.makeText(ModifierEtudiantActivity.this, "Étudiant modifié avec succès!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(ModifierEtudiantActivity.this, StudentListActivity.class));
                finish();
            } else {
                Toast.makeText(ModifierEtudiantActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
