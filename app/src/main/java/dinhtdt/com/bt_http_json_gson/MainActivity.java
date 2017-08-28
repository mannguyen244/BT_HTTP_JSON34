package dinhtdt.com.bt_http_json_gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectHTTP connectHTTP = new ConnectHTTP();
                connectHTTP.execute("");
            }
        });
    }

    class ConnectHTTP extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(MainActivity.this,s+ "", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {

            String link = strings[0];//vi tri dau tien cua mang can truyen = 0
            //bien link do thanh URL
            try {
                //co biet la url do dung hay sai
                URL url = new URL(link);

                URLConnection urlConnection = url.openConnection();

                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

                //ket noi su dung phuong thuc GET
                httpURLConnection.setRequestMethod("GET");

                //thoi gian connect - thoi gian cho connect lien tuc
                httpURLConnection.setConnectTimeout(500);
                httpURLConnection.setReadTimeout(500);

                //doc du lieu ra
                httpURLConnection.setDoInput(true);

                int responseCode = httpURLConnection.getResponseCode();//nhan status tra ve cua viec connect 200, 500,...

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    return  responseCode + "";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
