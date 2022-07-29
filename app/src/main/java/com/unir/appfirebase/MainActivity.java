package com.unir.appfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference usuarios = referencia.child("usuarios"); // nó referencia adicionado ao usuarios

        Usuario usuario = new Usuario();
        usuario.setNome("José");
        usuario.setSobrenome("Silva");
        usuario.setIdade(35);

        usuarios.push().setValue(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Mariana");
        usuario2.setSobrenome("Gouveia");
        usuario2.setIdade(21);

        usuarios.push().setValue(usuario2);

        //objeto ouvinte para notificar se ocorreu alguma alteração no nó
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}