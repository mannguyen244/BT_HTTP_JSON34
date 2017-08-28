package dinhtdt.com.bt_http_json_gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class POSTActivity extends AppCompatActivity {
    EditText editName, editDesc, editPrice, editCategory, editID;
    Button btnPOST, btnPUT, btnDelete,btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        editCategory = (EditText) findViewById(R.id.editCategory);
        editDesc = (EditText) findViewById(R.id.editDesc);
        editID = (EditText) findViewById(R.id.editID);
        btnPOST = (Button) findViewById(R.id.btnPOST);
        btnPUT = (Button) findViewById(R.id.btnPUT);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        btnPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                POST_Product post_product = new POST_Product();
                post_product.execute("http://10.246.112.180:7777/testjson/services/api/product/create.php",
                        editName.getText().toString(),
                        editPrice.getText().toString(),
                        editDesc.getText().toString(),
                        editCategory.getText().toString());
            }
        });

        btnPUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PUT_Product put_product = new PUT_Product();
                put_product.execute("http://172.24.41.48/services/api/product/update.php",
                        editName.getText().toString(),
                        editPrice.getText().toString(),
                        editDesc.getText().toString(),
                        editCategory.getText().toString(),
                        editID.getText().toString());
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete_Product delete_product = new Delete_Product();
                delete_product.execute("http://172.24.41.48/services/api/product/delete.php",
                        editID.getText().toString());
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search_Product search_product = new Search_Product();
                search_product.execute("http://10.246.112.180:7777/testjson/services/api/product/search.php",
                        editName.getText().toString());

            }
        });
    }

    class POST_Product extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(POSTActivity.this,s+ "", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setReadTimeout(500);
                httpURLConnection.setConnectTimeout(500);
                httpURLConnection.setDoInput(true);

                // lay trong Header, cua postman
                httpURLConnection.setRequestProperty("Content-Type","application/json ; charset=UTF-8 ");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",strings[1]);
                jsonObject.put("price",Double.valueOf(strings[2]));
                jsonObject.put("description",strings[3]);
                jsonObject.put("category_id",Integer.valueOf(strings[4]));


                OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8");
                osw.write(jsonObject.toString());
                osw.flush();
                osw.close();

                //doc ket qua tra ve
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStreamReader isr = new InputStreamReader(httpURLConnection.getInputStream());
                    BufferedReader bf = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();

                    String line = "";
                    while ((line = bf.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();


                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class PUT_Product extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(POSTActivity.this,s+ "", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                httpURLConnection.setRequestMethod("PUT");
                httpURLConnection.setReadTimeout(500);
                httpURLConnection.setConnectTimeout(500);
                httpURLConnection.setDoInput(true);

                // lay trong Header, cua postman
                httpURLConnection.setRequestProperty("Content-Type","application/json ; charset=UTF-8 ");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",strings[1]);
                jsonObject.put("price",Double.valueOf(strings[2]));
                jsonObject.put("description",strings[3]);
                jsonObject.put("category_id",Integer.valueOf(strings[4]));
                jsonObject.put("id",Integer.valueOf(strings[5]));


                OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8");
                osw.write(jsonObject.toString());
                osw.flush();
                osw.close();

                //doc ket qua tra ve
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStreamReader isr = new InputStreamReader(httpURLConnection.getInputStream());
                    BufferedReader bf = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();

                    String line = "";
                    while ((line = bf.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();


                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class Delete_Product extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(POSTActivity.this,s+ "", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                httpURLConnection.setRequestMethod("DELETE");
                httpURLConnection.setReadTimeout(500);
                httpURLConnection.setConnectTimeout(500);
                httpURLConnection.setDoInput(true);

                // lay trong Header, cua postman
                httpURLConnection.setRequestProperty("Content-Type","application/json ; charset=UTF-8 ");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",Integer.valueOf(strings[1]));


                OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8");
                osw.write(jsonObject.toString());
                osw.flush();
                osw.close();

                //doc ket qua tra ve
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStreamReader isr = new InputStreamReader(httpURLConnection.getInputStream());
                    BufferedReader bf = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();

                    String line = "";
                    while ((line = bf.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();


                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class Search_Product extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(POSTActivity.this,s+ "", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setReadTimeout(500);
                httpURLConnection.setConnectTimeout(500);
                httpURLConnection.setDoInput(true);

                // lay trong Header, cua postman
                httpURLConnection.setRequestProperty("Content-Type","application/json ; charset=UTF-8 ");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",strings[1]);


                OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8");
                osw.write(jsonObject.toString());
                osw.flush();
                osw.close();

                //doc ket qua tra ve
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStreamReader isr = new InputStreamReader(httpURLConnection.getInputStream());
                    BufferedReader bf = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();

                    String line = "";
                    while ((line = bf.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();


                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
