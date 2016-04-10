package edu.lclark.customviewdemoapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ntille on 4/7/16.
 */
public class ColorView extends LinearLayout {

    @Bind(R.id.view_color_edittext)
    EditText mEditText;

    public ColorView(Context context) {
        super(context);
        init();
    }

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorView);
        if (typedArray != null) {
            @ColorInt int color = typedArray.getColor(R.styleable.ColorView_startColor, Color.WHITE);
            setBackgroundColor(color);
            typedArray.recycle();
        }

    }

    private void init() {
        inflate(getContext(), R.layout.view_color, this);
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.view_color_button)
    public void onButtonClick() {
        String colorStr = mEditText.getText().toString();

        try {
            if (!colorStr.startsWith("#")) {
                colorStr = "#" + colorStr;
            }
            @ColorInt int color = Color.parseColor(colorStr);
            setBackgroundColor(color);
        } catch (IllegalArgumentException e) {
            Toast.makeText(getContext(), R.string.the_stupid_string_im_showing_users, Toast.LENGTH_SHORT).show();
        }

    }
}
