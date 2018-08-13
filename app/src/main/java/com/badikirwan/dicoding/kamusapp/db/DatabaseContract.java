package com.badikirwan.dicoding.kamusapp.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_IND_TO_ENG  = "ind_to_eng";
    public static String TABLE_ENG_TO_IND  = "eng_to_ind";

    public static final class KamusColums implements BaseColumns {
        public static String KATA = "kata";
        public static String ARTI = "arti";
    }
}
