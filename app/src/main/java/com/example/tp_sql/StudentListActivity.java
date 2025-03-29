package com.example.tp_sql;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tp_sql.classes.Etudiant;
import com.example.tp_sql.service.EtudiantService;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {
    private ListView listView;
    private EtudiantService etudiantService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        etudiantService = new EtudiantService(this);
        listView = findViewById(R.id.listView);

        List<Etudiant> etudiants = etudiantService.findAll();
        EtudiantAdapter adapter = new EtudiantAdapter(this, etudiants);
        listView.setAdapter(adapter);
    }
}
