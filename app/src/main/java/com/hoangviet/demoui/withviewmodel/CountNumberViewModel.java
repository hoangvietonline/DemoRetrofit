package com.hoangviet.demoui.withviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class CountNumberViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> mScoreTeamA = new MutableLiveData<>();
    private MutableLiveData<Integer> mScoreTeamB = new MutableLiveData<>();

    public CountNumberViewModel(@NonNull Application application) {
        super(application);
        mScoreTeamA.setValue(0);
        mScoreTeamB.setValue(0);
    }

    public MutableLiveData<Integer> getmScoreTeamA() {
        return mScoreTeamA;
    }

    public void setmScoreTeamA(int score) {
        mScoreTeamA.setValue(mScoreTeamA.getValue() + score);
    }

    public MutableLiveData<Integer> getmScoreTeamB() {
        return mScoreTeamB;
    }

    public void setmScoreTeamB(int score) {
        mScoreTeamB.setValue(mScoreTeamB.getValue() + score);
    }
}
