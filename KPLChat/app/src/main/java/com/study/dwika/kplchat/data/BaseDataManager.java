package com.study.dwika.kplchat.data;

import com.study.dwika.kplchat.data.database.BaseDatabaseHelper;
import com.study.dwika.kplchat.data.network.BaseApiHelper;
import com.study.dwika.kplchat.data.sharedpreference.BaseSharedPreferenceHelper;
import com.study.dwika.kplchat.model.BaseResponse;
import com.study.dwika.kplchat.model.Login;

import io.reactivex.Observable;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseDataManager extends BaseApiHelper, BaseSharedPreferenceHelper, BaseDatabaseHelper {
}
