package com.example.tp_sql;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tp_sql.classes.Etudiant;

import java.util.List;

public class EtudiantAdapter extends ArrayAdapter<Etudiant> {
    private Context context;
    private List<Etudiant> etudiants;

    public EtudiantAdapter(Context context, List<Etudiant> etudiants) {
        super(context, 0, etudiants);
        this.context = context;
        this.etudiants = etudiants;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.etudiant_item, parent, false);
        }

        Etudiant etudiant = etudiants.get(position);

        TextView idTextView = convertView.findViewById(R.id.etudiant_id);
        TextView nomTextView = convertView.findViewById(R.id.etudiant_nom);
        TextView prenomTextView = convertView.findViewById(R.id.etudiant_prenom);
        Button btnModifier = convertView.findViewById(R.id.btn_modifier);


        idTextView.setText("ID: " + etudiant.getId());
        nomTextView.setText("Nom: " + etudiant.getNom());
        prenomTextView.setText("Prénom: " + etudiant.getPrenom());

        btnModifier.setOnClickListener(v -> {
            Intent intent = new Intent(context, ModifierEtudiantActivity.class);
            intent.putExtra("etudiant_id", etudiant.getId());
            context.startActivity(intent);
        });

        return convertView;
    }
}
