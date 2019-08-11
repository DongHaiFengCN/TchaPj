package com.application.tchapj.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.application.tchapj.R;


public class Emoji {
    //f080-f089为签到表情
    public static int[] mStrs = new int[]{R.mipmap.f000, R.mipmap.f001,
            R.mipmap.f002, R.mipmap.f003, R.mipmap.f004, R.mipmap.f005,
            R.mipmap.f006, R.mipmap.f007, R.mipmap.f008, R.mipmap.f009,
            R.mipmap.f010, R.mipmap.f011, R.mipmap.f012, R.mipmap.f013,
            R.mipmap.f014, R.mipmap.f015, R.mipmap.f016, R.mipmap.f017,
            R.mipmap.f018, R.mipmap.f019, R.mipmap.delate,
            R.mipmap.f020, R.mipmap.f021, R.mipmap.f022, R.mipmap.f023,
            R.mipmap.f024, R.mipmap.f025, R.mipmap.f026, R.mipmap.f027,
            R.mipmap.f028, R.mipmap.f029, R.mipmap.f030, R.mipmap.f031,
            R.mipmap.f032, R.mipmap.f033, R.mipmap.f034, R.mipmap.f035,
            R.mipmap.f036, R.mipmap.f037, R.mipmap.f038, R.mipmap.f039,
            R.mipmap.delate, R.mipmap.f040, R.mipmap.f041,
            R.mipmap.f042, R.mipmap.f043, R.mipmap.f044, R.mipmap.f045,
            R.mipmap.f046, R.mipmap.f047, R.mipmap.f048, R.mipmap.f049,
            R.mipmap.f050, R.mipmap.f051, R.mipmap.f052, R.mipmap.f053,
            R.mipmap.f054, R.mipmap.f055, R.mipmap.f056, R.mipmap.f057,
            R.mipmap.f058, R.mipmap.f059, R.mipmap.delate,
            R.mipmap.f060, R.mipmap.f061, R.mipmap.f062, R.mipmap.f063,
            R.mipmap.f064, R.mipmap.f065, R.mipmap.f066, R.mipmap.f067,
            R.mipmap.f068, R.mipmap.f069, R.mipmap.f070, R.mipmap.f071,
            R.mipmap.f072, R.mipmap.f073, R.mipmap.f074, R.mipmap.f075,
            R.mipmap.f076, R.mipmap.f077, R.mipmap.f078, R.mipmap.f079,
            R.mipmap.f080, R.mipmap.f081, R.mipmap.f082, R.mipmap.f083,
            R.mipmap.f084, R.mipmap.f085, R.mipmap.f086, R.mipmap.f087,
            R.mipmap.f088, R.mipmap.f089, R.mipmap.delate};

    public static int[] picture = new int[]{R.mipmap.f000, R.mipmap.f001,
            R.mipmap.f002, R.mipmap.f003, R.mipmap.f004, R.mipmap.f005,
            R.mipmap.f006, R.mipmap.f007, R.mipmap.f008, R.mipmap.f009,
            R.mipmap.f010, R.mipmap.f011, R.mipmap.f012, R.mipmap.f013,
            R.mipmap.f014, R.mipmap.f015, R.mipmap.f016, R.mipmap.f017,
            R.mipmap.f018, R.mipmap.f019, R.mipmap.f020, R.mipmap.f021,
            R.mipmap.f022, R.mipmap.f023, R.mipmap.f024, R.mipmap.f025,
            R.mipmap.f026, R.mipmap.f027, R.mipmap.f028, R.mipmap.f029,
            R.mipmap.f030, R.mipmap.f031, R.mipmap.f032, R.mipmap.f033,
            R.mipmap.f034, R.mipmap.f035, R.mipmap.f036, R.mipmap.f037,
            R.mipmap.f038, R.mipmap.f039, R.mipmap.f040, R.mipmap.f041,
            R.mipmap.f042, R.mipmap.f043, R.mipmap.f044, R.mipmap.f045,
            R.mipmap.f046, R.mipmap.f047, R.mipmap.f048, R.mipmap.f049,
            R.mipmap.f050, R.mipmap.f051, R.mipmap.f052, R.mipmap.f053,
            R.mipmap.f054, R.mipmap.f055, R.mipmap.f056, R.mipmap.f057,
            R.mipmap.f058, R.mipmap.f059, R.mipmap.f060, R.mipmap.f061,
            R.mipmap.f062, R.mipmap.f063, R.mipmap.f064, R.mipmap.f065,
            R.mipmap.f066, R.mipmap.f067, R.mipmap.f068, R.mipmap.f069,
            R.mipmap.f070, R.mipmap.f071, R.mipmap.f072, R.mipmap.f073,
            R.mipmap.f074, R.mipmap.f075, R.mipmap.f076, R.mipmap.f077,
            R.mipmap.f078, R.mipmap.f079, R.mipmap.f080, R.mipmap.f081,
            R.mipmap.f082, R.mipmap.f083, R.mipmap.f084, R.mipmap.f085,
            R.mipmap.f086, R.mipmap.f087, R.mipmap.f088, R.mipmap.f089};


    /**
     * 根据图片名获取ID,并生成图片对象
     *
     * @param :faceName
     * @return ss
     */
    public static SpannableString getImg(Context context, String faceId) {
        SpannableString ss = new SpannableString("<f" + faceId + ">");
        // SpannableString ss = new SpannableString("[_nl_qqImg]" + faceId+
        // ".gif[/_nl_qqImg]");
        Drawable d = context.getResources().getDrawable(picture[Integer.parseInt(faceId)]);
        d.setBounds(0, 10, d.getIntrinsicWidth(), d.getIntrinsicHeight() + 10);
        ImageSpan imgSpan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        ss.setSpan(imgSpan, 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }


}
