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

    public final String PREF_KEY_FROM_1 = "keyFrom1";
    public final String PREF_KEY_TO_1 = "keyTo1";

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

        Button btnSave = view.findViewById(R.id.settingBtnSave);
        EditText edTxtFrm1 = view.findViewById(R.id.settingEdTxtFrom1);
        EditText edTxtTo1 = view.findViewById(R.id.settingEdTxtTo1);

        if (dataStore != null) {
            edTxtFrm1.setText(dataStore.getString(PREF_KEY_FROM_1, getString(R.string.setting_txt_From)));
            edTxtTo1.setText(dataStore.getString(PREF_KEY_TO_1, getString(R.string.setting_txt_To)));
        }

        btnSave.setOnClickListener(v -> {
            String from1 = edTxtFrm1.getText().toString();
            String to1 = edTxtTo1.getText().toString();

            if (dataStore != null) {
                SharedPreferences.Editor editor = dataStore.edit();
                // Key: input, value: text
                editor.putString(PREF_KEY_FROM_1, from1);
                editor.putString(PREF_KEY_TO_1, to1);
                //editor.commit();
                editor.apply();
            }
        });

        return view;
    }
}