package id.ac.poliban.mi.sultan.sqlitetesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import id.ac.poliban.mi.sultan.sqlitetesting.dao.impl.FriendDaoImplSQLite;
import id.ac.poliban.mi.sultan.sqlitetesting.domain.Friend;

public class MainActivity extends AppCompatActivity {
    private List<Friend> data = new ArrayList<>();
    {
        data.add(new Friend("Vita Susanti", "Dago Utara, Bandung", "08232488"));
        data.add(new Friend("Junaidi Abdi", "Paiton, Probolinggo", "08567957"));
        data.add(new Friend("Yusuf Rizal", "Belitung, Banjarmasin", "08456456"));
        data.add(new Friend("M Yusuf", "Telawang, Banjarmasin", "08235345"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar()!=null) getSupportActionBar().setTitle("SQLite I Demo");

        EditText etID = findViewById(R.id.etId);
        Button btUpgrade = findViewById(R.id.btUpgrade);
        Button btUpdate = findViewById(R.id.btUpdate);
        Button btDelete = findViewById(R.id.btDelete);
        Button btGetAFriend = findViewById(R.id.btGetAFriend);
        Button btGetAllFriend = findViewById(R.id.btGetAllFriend);
        Button btInsert = findViewById(R.id.btInsert);

        FriendDaoImplSQLite db = new FriendDaoImplSQLite(this);

        btUpgrade.setOnClickListener(v -> {
            db.onUpgrade(db.getReadableDatabase(), 1, 2);
            Toast.makeText(this, "upgrade successed", Toast.LENGTH_SHORT).show();
        });

        btInsert.setOnClickListener(v -> {
            data.forEach(db::insert);
            Toast.makeText(this, "Inserted Ok", Toast.LENGTH_SHORT).show();
        });

        btGetAllFriend.setOnClickListener(v -> {
            db.getAllFriends().forEach(System.out::println);
            Toast.makeText(this, "Showing data ok! check in run monitor", Toast.LENGTH_SHORT).show();
        });

        btUpdate.setOnClickListener(v -> {
            int id = Integer.parseInt(etID.getText().toString());
            db.update(new Friend(id, "XXX", "XXX", "XXX"));
            Toast.makeText(this, "Update Success! check in run monitor", Toast.LENGTH_SHORT).show();
        });

        btDelete.setOnClickListener(v -> {
            int id = Integer.parseInt(etID.getText().toString());
            db.delete(id);
            Toast.makeText(this, "Delete Success! check in run monitor", Toast.LENGTH_SHORT).show();
        });

        btGetAFriend.setOnClickListener(v -> {
            int id = Integer.parseInt(etID.getText().toString());
            Friend f = db.getAFriendById(id);
            Toast.makeText(this, "get a friend success! check in run monitor", Toast.LENGTH_SHORT).show();
            System.out.println(f);
        });


    }
}
