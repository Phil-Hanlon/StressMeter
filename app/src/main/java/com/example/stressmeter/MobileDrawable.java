package com.example.stressmeter;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class MobileDrawable implements Parcelable {

    private Drawable drawable;


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Object createFromParcel(Parcel source) {

            return null;
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[0];
        }
    };


    public MobileDrawable(Drawable drawable) {

        this.drawable = drawable;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public Drawable getDrawable() {

        return this.drawable;
    }
}
