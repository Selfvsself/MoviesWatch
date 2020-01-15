package com.selfvsself.movieswatch.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.material.textfield.TextInputEditText;
import com.selfvsself.movieswatch.R;

public class CustomTextView extends TextInputEditText {
    final int DRAWABLE_LEFT = 0;
    final int DRAWABLE_TOP = 1;
    final int DRAWABLE_RIGHT = 2;
    final int DRAWABLE_BOTTOM = 3;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (lengthAfter > 0) {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cancel_24dp, 0);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;

            case MotionEvent.ACTION_UP:
                performClick();
                doSomething(event);
                return true;
        }
        return false;
    }

    // Because we call this from onTouchEvent, this code will be executed for both
    // normal touch events and for when the system calls this using Accessibility
    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    private void doSomething(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && getCompoundDrawables()[DRAWABLE_RIGHT] != null) {
            if (event.getRawX() >= (getRight() - getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                setText("");
            }
        }
    }
}
