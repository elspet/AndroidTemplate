package com.c4po.template.mvp.ui;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.c4po.template.R;
import com.c4po.template.base.BaseActivity;
import com.c4po.template.mvp.ui.message.ReadmeMsgEvent;
import com.zzhoujay.markdown.MarkDown;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShowReadmeActivity extends BaseActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_show_readme);

        mTextView = findViewById(R.id.textView);
        assert mTextView != null;

        setText(R.raw.readme);

    }

    private void setText(int resId) {
        final InputStream stream = getResources().openRawResource(resId);

        mTextView.post(new Runnable() {
            @Override
            public void run() {
                Spanned spanned = MarkDown.fromMarkdown(stream, new Html.ImageGetter() {
                    public static final String TAG = "Markdown";

                    @Override
                    public Drawable getDrawable(String source) {
                        Log.d(TAG, "getDrawable() called with: source = [" + source + "]");

                        Drawable drawable;
                        try {
                            drawable = drawableFromUrl(source);
                            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        } catch (IOException e) {
                            Log.w(TAG, "can't get image", e);
                            drawable = new ColorDrawable(Color.LTGRAY);
                            drawable.setBounds(0, 0, mTextView.getWidth() - mTextView.getPaddingLeft() - mTextView.getPaddingRight(), 400);
                        }
                        return drawable;
                    }
                }, mTextView);
                mTextView.setText(spanned);
            }
        });
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }


    @Override
    protected void onPause() {
        super.onPause();
        ReadmeMsgEvent msgEvent = new ReadmeMsgEvent("AlreadyRead Readme.md ");
        EventBus.getDefault().post(msgEvent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 0x1
                && item.getItemId() != 0x2) {
            setText(item.getItemId());
            return true;
        } else if (item.getItemId() == 0x1) {
            getResources().getConfiguration().uiMode |= Configuration.UI_MODE_NIGHT_YES;
            getResources().getConfiguration().uiMode &= ~Configuration.UI_MODE_NIGHT_NO;
            getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());
            recreate();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
