package com.example.smpnv001;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    Button btnSave;
    EditText edTxtFrm1;
    EditText edTxtTo1;
    EditText edTxtFrm2;
    EditText edTxtTo2;
    EditText edTxtFrm3;
    EditText edTxtTo3;
    EditText edTxtFrm4;
    EditText edTxtTo4;
    EditText edTxtFrm5;
    EditText edTxtTo5;
    EditText edTxtFrm6;
    EditText edTxtTo6;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        // ファイル名:DataStore
        SharedPreferences dataStore = getContext().getSharedPreferences("DataStore", Context.MODE_PRIVATE);

        btnSave = view.findViewById(R.id.settingBtnSave);
        edTxtFrm1 = view.findViewById(R.id.settingEdTxtFrom1);
        edTxtTo1 = view.findViewById(R.id.settingEdTxtTo1);
        edTxtFrm2 = view.findViewById(R.id.settingEdTxtFrom2);
        edTxtTo2 = view.findViewById(R.id.settingEdTxtTo2);
        edTxtFrm3 = view.findViewById(R.id.settingEdTxtFrom3);
        edTxtTo3 = view.findViewById(R.id.settingEdTxtTo3);
        edTxtFrm4 = view.findViewById(R.id.settingEdTxtFrom4);
        edTxtTo4 = view.findViewById(R.id.settingEdTxtTo4);
        edTxtFrm5 = view.findViewById(R.id.settingEdTxtFrom5);
        edTxtTo5 = view.findViewById(R.id.settingEdTxtTo5);
        edTxtFrm6 = view.findViewById(R.id.settingEdTxtFrom6);
        edTxtTo6 = view.findViewById(R.id.settingEdTxtTo6);

        if (dataStore != null) {
            edTxtFrm1.setText(dataStore.getString(MainActivity.PREF_KEY_FROM_1, getString(R.string.setting_txt_From)));
            edTxtTo1.setText(dataStore.getString(MainActivity.PREF_KEY_TO_1, getString(R.string.setting_txt_To)));
            edTxtFrm2.setText(dataStore.getString(MainActivity.PREF_KEY_FROM_2, getString(R.string.setting_txt_From)));
            edTxtTo2.setText(dataStore.getString(MainActivity.PREF_KEY_TO_2, getString(R.string.setting_txt_To)));
            edTxtFrm3.setText(dataStore.getString(MainActivity.PREF_KEY_FROM_3, getString(R.string.setting_txt_From)));
            edTxtTo3.setText(dataStore.getString(MainActivity.PREF_KEY_TO_3, getString(R.string.setting_txt_To)));
            edTxtFrm4.setText(dataStore.getString(MainActivity.PREF_KEY_FROM_4, getString(R.string.setting_txt_From)));
            edTxtTo4.setText(dataStore.getString(MainActivity.PREF_KEY_TO_4, getString(R.string.setting_txt_To)));
            edTxtFrm5.setText(dataStore.getString(MainActivity.PREF_KEY_FROM_5, getString(R.string.setting_txt_From)));
            edTxtTo5.setText(dataStore.getString(MainActivity.PREF_KEY_TO_5, getString(R.string.setting_txt_To)));
            edTxtFrm6.setText(dataStore.getString(MainActivity.PREF_KEY_FROM_6, getString(R.string.setting_txt_From)));
            edTxtTo6.setText(dataStore.getString(MainActivity.PREF_KEY_TO_6, getString(R.string.setting_txt_To)));
        }

        btnSave.setOnClickListener(v -> {
            String from1 = edTxtFrm1.getText().toString();
            String to1 = edTxtTo1.getText().toString();
            String from2 = edTxtFrm2.getText().toString();
            String to2 = edTxtTo2.getText().toString();
            String from3 = edTxtFrm3.getText().toString();
            String to3 = edTxtTo3.getText().toString();
            String from4 = edTxtFrm4.getText().toString();
            String to4 = edTxtTo4.getText().toString();
            String from5 = edTxtFrm5.getText().toString();
            String to5 = edTxtTo5.getText().toString();
            String from6 = edTxtFrm6.getText().toString();
            String to6 = edTxtTo6.getText().toString();

            if (dataStore != null) {
                SharedPreferences.Editor editor = dataStore.edit();
                // Key: input, value: text
                editor.putString(MainActivity.PREF_KEY_FROM_1, from1);
                editor.putString(MainActivity.PREF_KEY_TO_1, to1);
                editor.putString(MainActivity.PREF_KEY_FROM_2, from2);
                editor.putString(MainActivity.PREF_KEY_TO_2, to2);
                editor.putString(MainActivity.PREF_KEY_FROM_3, from3);
                editor.putString(MainActivity.PREF_KEY_TO_3, to3);
                editor.putString(MainActivity.PREF_KEY_FROM_4, from4);
                editor.putString(MainActivity.PREF_KEY_TO_4, to4);
                editor.putString(MainActivity.PREF_KEY_FROM_5, from5);
                editor.putString(MainActivity.PREF_KEY_TO_5, to5);
                editor.putString(MainActivity.PREF_KEY_FROM_6, from6);
                editor.putString(MainActivity.PREF_KEY_TO_6, to6);
                //editor.commit();
                editor.apply();
            }
        });

        return view;
    }
}