package com.example.smpnv001;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;
    //private static final String baseUri = "http://api.ekispert.jp/v1/json/search/course/light?key=LE_KUSKjnVXs9QM7";
    private static final String baseUri = "http://api.ekispert.jp/v1/json/search/course/light";

    private Handler handler = new Handler();
    private volatile String errMes = "";
    // 画面
    // 共通
    Button btnClose;
    RadioGroup rdGrpTime;
    // 検索1
    TextView txtVwUrl;
    TextView txtVwSearch1;
    Button btnSearch1;
    TextView txtVwSearch2;
    Button btnSearch2;
    TextView txtVwSearch3;
    Button btnSearch3;
    TextView txtVwSearch4;
    Button btnSearch4;
    TextView txtVwSearch5;
    Button btnSearch5;
    TextView txtVwSearch6;
    Button btnSearch6;
    WebView myWebView;

    public SearchingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchingFragment newInstance(String param1, String param2) {
        SearchingFragment fragment = new SearchingFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (getArguments() != null) {
        //    mParam1 = getArguments().getString(ARG_PARAM1);
        //    mParam2 = getArguments().getString(ARG_PARAM2);
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_searching, container, false);
        // ファイル名:DataStore
        SharedPreferences dataStore = getContext().getSharedPreferences("DataStore", Context.MODE_PRIVATE);
        //IDからオブジェクトを取得
        btnClose = view.findViewById(R.id.searchingBtnClose);
        rdGrpTime = view.findViewById(R.id.searchingRdGrpTime);
        txtVwUrl = view.findViewById(R.id.searchingTxtUrl);
        myWebView = (WebView) view.findViewById(R.id.searchingWbVw);
        txtVwSearch1 = view.findViewById(R.id.searchingTxtSearch1);
        btnSearch1 = view.findViewById(R.id.searchingBtnSearch1);
        txtVwSearch2 = view.findViewById(R.id.searchingTxtSearch2);
        btnSearch2 = view.findViewById(R.id.searchingBtnSearch2);
        txtVwSearch3 = view.findViewById(R.id.searchingTxtSearch3);
        btnSearch3 = view.findViewById(R.id.searchingBtnSearch3);
        txtVwSearch4 = view.findViewById(R.id.searchingTxtSearch4);
        btnSearch4 = view.findViewById(R.id.searchingBtnSearch4);
        txtVwSearch5 = view.findViewById(R.id.searchingTxtSearch5);
        btnSearch5 = view.findViewById(R.id.searchingBtnSearch5);
        txtVwSearch6 = view.findViewById(R.id.searchingTxtSearch6);
        btnSearch6 = view.findViewById(R.id.searchingBtnSearch6);

        // 閉じるボタンリスナー登録
        btnClose.setOnClickListener(v -> {
            myWebView.setVisibility(View.INVISIBLE);
            this.setButtonsVisibility(true);
            if (errMes != null && !"".equals(errMes)) {
                txtVwUrl.setText(errMes);
            }
        });

        // 検索1
        String from1 = dataStore.getString(MainActivity.PREF_KEY_FROM_1, getString(R.string.setting_txt_From));
        String to1 = dataStore.getString(MainActivity.PREF_KEY_TO_1, getString(R.string.setting_txt_To));
        if (from1 == null || "".equals(from1) || getString(R.string.setting_txt_From).equals(from1)
                || to1 == null || "".equals(to1) || getString(R.string.setting_txt_To).equals(to1)) {
            btnSearch1.setEnabled(false);
            txtVwSearch1.setText(getString(R.string.searching_txt_searching_blank));
        } else {
            btnSearch1.setEnabled(true);
            txtVwSearch1.setText(from1 + " →→→ " + to1);
            // 検索ボタンリスナー登録
            btnSearch1.setOnClickListener(v1 -> {
                this.search(from1, to1);
                this.setButtonsVisibility(false);
            });
        }
        // 検索2
        String from2 = dataStore.getString(MainActivity.PREF_KEY_FROM_2, getString(R.string.setting_txt_From));
        String to2 = dataStore.getString(MainActivity.PREF_KEY_TO_2, getString(R.string.setting_txt_To));
        if (from2 == null || "".equals(from2) || getString(R.string.setting_txt_From).equals(from2)
                || to2 == null || "".equals(to2) || getString(R.string.setting_txt_To).equals(to2)) {
            btnSearch2.setEnabled(false);
            txtVwSearch2.setText(getString(R.string.searching_txt_searching_blank));
        } else {
            btnSearch2.setEnabled(true);
            txtVwSearch2.setText(from2 + " →→→ " + to2);
            // 検索ボタンリスナー登録
            btnSearch2.setOnClickListener(v2 -> {
                this.search(from2, to2);
                this.setButtonsVisibility(false);
            });
        }
        // 検索3
        String from3 = dataStore.getString(MainActivity.PREF_KEY_FROM_3, getString(R.string.setting_txt_From));
        String to3 = dataStore.getString(MainActivity.PREF_KEY_TO_3, getString(R.string.setting_txt_To));
        if (from3 == null || "".equals(from3) || getString(R.string.setting_txt_From).equals(from3)
                || to3 == null || "".equals(to3) || getString(R.string.setting_txt_To).equals(to3)) {
            btnSearch3.setEnabled(false);
            txtVwSearch3.setText(getString(R.string.searching_txt_searching_blank));
        } else {
            btnSearch3.setEnabled(true);
            txtVwSearch3.setText(from3 + " →→→ " + to3);
            // 検索ボタンリスナー登録
            btnSearch3.setOnClickListener(v3 -> {
                this.search(from3, to3);
                this.setButtonsVisibility(false);
            });
        }
        // 検索4
        String from4 = dataStore.getString(MainActivity.PREF_KEY_FROM_4, getString(R.string.setting_txt_From));
        String to4 = dataStore.getString(MainActivity.PREF_KEY_TO_4, getString(R.string.setting_txt_To));
        if (from4 == null || "".equals(from4) || getString(R.string.setting_txt_From).equals(from4)
                || to4 == null || "".equals(to4) || getString(R.string.setting_txt_To).equals(to4)) {
            btnSearch4.setEnabled(false);
            txtVwSearch4.setText(getString(R.string.searching_txt_searching_blank));
        } else {
            btnSearch4.setEnabled(true);
            txtVwSearch4.setText(from4 + " →→→ " + to4);
            // 検索ボタンリスナー登録
            btnSearch4.setOnClickListener(v4 -> {
                this.search(from4, to4);
                this.setButtonsVisibility(false);
            });
        }
        // 検索5
        String from5 = dataStore.getString(MainActivity.PREF_KEY_FROM_5, getString(R.string.setting_txt_From));
        String to5 = dataStore.getString(MainActivity.PREF_KEY_TO_5, getString(R.string.setting_txt_To));
        if (from5 == null || "".equals(from5) || getString(R.string.setting_txt_From).equals(from5)
                || to5 == null || "".equals(to5) || getString(R.string.setting_txt_To).equals(to5)) {
            btnSearch5.setEnabled(false);
            txtVwSearch5.setText(getString(R.string.searching_txt_searching_blank));
        } else {
            btnSearch5.setEnabled(true);
            txtVwSearch5.setText(from5 + " →→→ " + to5);
            // 検索ボタンリスナー登録
            btnSearch5.setOnClickListener(v5 -> {
                this.search(from5, to5);
                this.setButtonsVisibility(false);
            });
        }
        // 検索6
        String from6 = dataStore.getString(MainActivity.PREF_KEY_FROM_6, getString(R.string.setting_txt_From));
        String to6 = dataStore.getString(MainActivity.PREF_KEY_TO_6, getString(R.string.setting_txt_To));
        if (from6 == null || "".equals(from6) || getString(R.string.setting_txt_From).equals(from6)
                || to6 == null || "".equals(to6) || getString(R.string.setting_txt_To).equals(to6)) {
            btnSearch6.setEnabled(false);
            txtVwSearch6.setText(getString(R.string.searching_txt_searching_blank));
        } else {
            btnSearch6.setEnabled(true);
            txtVwSearch6.setText(from6 + " →→→ " + to6);
            // 検索ボタンリスナー登録
            btnSearch6.setOnClickListener(v6 -> {
                this.search(from6, to6);
                this.setButtonsVisibility(false);
            });
        }
        return view;
    }

    private void setButtonsVisibility(boolean flg) {
        int Visibility = 0;
        if (flg) {
            Visibility = View.VISIBLE;
        } else {
            Visibility = View.INVISIBLE;
        }
        btnSearch1.setVisibility(Visibility);
        btnSearch2.setVisibility(Visibility);
        btnSearch3.setVisibility(Visibility);
        btnSearch4.setVisibility(Visibility);
        btnSearch5.setVisibility(Visibility);
        btnSearch6.setVisibility(Visibility);
    }

    private void search(String from, String to) {
        errMes = "";
        txtVwUrl.setText(errMes);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 時間設定
                    int addMin = 0;
                    int type = rdGrpTime.getCheckedRadioButtonId();
                    switch(type) {
                        case R.id.searchingRdBtnNow:
                            addMin = 0;
                            break;
                        case R.id.searchingRdBtn5min:
                            addMin = 5;
                            break;
                        case R.id.searchingRdBtn10min:
                            addMin = 10;
                            break;
                        case R.id.searchingRdBtn60min:
                            addMin = 60;
                            break;
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.MINUTE, addMin);
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                    String date = sdf1.format(cal.getTime());
                    SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");
                    String time = sdf2.format(cal.getTime());
                    // 検索実行
                    String result = getAPI(from, to, date, time);
                    JSONObject rootJSON = new JSONObject(result);
                    JSONObject resultSet = rootJSON.getJSONObject("ResultSet");
                    String uri = resultSet.getString("ResourceURI");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // txtVwUrl.setText(uri);
                            myWebView.setWebViewClient(new WebViewClient());
                            myWebView.loadUrl(uri);
                            myWebView.setVisibility(View.VISIBLE);
                            myWebView.bringToFront();
                        }
                    });
                } catch (JSONException je) {
                    errMes = "JSONエラー";
                } catch (Exception e) {
                    errMes = "通信エラー";
                }
            }
        });
        thread.start();
    }

    private String getAPI(String from, String to, String date, String time) throws Exception {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String result = "";
        String returnStr = "";
        try {
            //URL url = new URL("http://api.ekispert.jp/v1/json/search/course/light?key=LE_KUSKjnVXs9QM7&&from=成瀬&to=町田&date=20211123&time=1732/");
            //URL url = new URL("http://api.ekispert.jp/v1/json/search/course/light?key=LE_KUSKjnVXs9QM7&&from=%E6%88%90%E7%80%AC&to=%E7%94%BA%E7%94%B0&date=20211123&time=1732");
            //URL url = new URL("http://api.ekispert.jp/v1/json/search/course/light?key=LE_KUSKjnVXs9QM7/");

            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("key", "LE_KUSKjnVXs9QM7");
            builder.appendQueryParameter("from", from);
            builder.appendQueryParameter("to", to);
            if (date != null && !"".equals(date)) builder.appendQueryParameter("date", date);
            if (date != null && !"".equals(time)) builder.appendQueryParameter("time", time);

            URL url = new URL(baseUri + builder.toString());
            // 接続先URLへのコネクションを開く．まだ接続されていない
            urlConnection = (HttpURLConnection) url.openConnection();
            // 接続タイムアウトを設定
            urlConnection.setConnectTimeout(10000);
            // レスポンスデータの読み取りタイムアウトを設定
            urlConnection.setReadTimeout(10000);
            // ヘッダーにUser-Agentを設定
            urlConnection.addRequestProperty("User-Agent", "Android");
            // ヘッダーにAccept-Languageを設定
            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString());
            // HTTPメソッドを指定
            urlConnection.setRequestMethod("GET");
            //リクエストボディの送信を許可しない
            urlConnection.setDoOutput(false);
            //レスポンスボディの受信を許可する
            urlConnection.setDoInput(true);
            // 通信開始
            urlConnection.connect();
            // レスポンスコードを取得
            int statusCode = urlConnection.getResponseCode();
            // レスポンスコード200は通信に成功したことを表す
            if (statusCode != 200) {
                throw new Exception("return code = " + statusCode);
            }
            inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            // 1行ずつレスポンス結果を取得しstrに追記
            result = bufferedReader.readLine();
            while (result != null){
                returnStr += result;
                result = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            throw new Exception("MalformedURLException : " + e.toString());
        } catch (IOException e) {
            throw new Exception("IOException : " + e.toString());
        }
        return returnStr;
    }
}