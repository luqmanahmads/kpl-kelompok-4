package com.study.dwika.kplchat.data;

import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseDataManager extends BaseApiHelper, BaseSharedPreferenceHelper, BaseDatabaseHelper {
}
