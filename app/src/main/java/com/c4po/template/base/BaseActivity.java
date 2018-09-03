package com.c4po.template.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.RequestManager;
import com.c4po.template.global.ActivityManager;
import com.c4po.template.global.Constants;
import com.c4po.template.utils.ToastUtils;

import butterknife.ButterKnife;


/**
 * @author lisa
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = BaseActivity.class.getSimpleName();

    private RequestManager requestManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将当前activity添加到activity管理中
        ActivityManager.getInstance().addActivity(this);

    }

    /**
     * 获取activity对象
     *
     * @return
     */
    public BaseActivity getActivity() {
        return this;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(getActivity());
    }

    /**
     * 显示toast
     *
     * @param message
     */
    public void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShort(getActivity(), message);
            }
        });

    }


    @Override
    public void onClick(View v) {
    }

    protected boolean checkPermission(String permission, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    showAlertDialog("部分权限被禁止，将导致程序无法正常运行。是否开启部分权限？(步骤：应用信息->权限->'勾选')");
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
                }
                return false;
            } else {
                onPermissionSuccess(requestCode);
                return true;
            }
        }
        onPermissionSuccess(requestCode);
        return true;
    }


    /**
     * 判断程序是否有所需权限 android22以上需要自申请权限
     */
    public boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getActivity().checkSelfPermission(Constants.PERMISSION_CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    getActivity().checkSelfPermission(Constants.PERMISSION_READ_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    getActivity().checkSelfPermission(Constants.PERMISSION_WRITE_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    getActivity().checkSelfPermission(Constants.PERMISSION_INTERNET) == PackageManager.PERMISSION_GRANTED &&
                    getActivity().checkSelfPermission(Constants.PERMISSION_ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }


    protected void showAlertDialog(String content) {
        new AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage(content)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri packageUri = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageUri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }).create().show();
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title, String rightText, int rightDrawableId) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }


    /**
     * 获取序列化对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T extends BaseModel> T getSerializableExtra(String key) {
        Intent intent = getIntent();
        if (null != intent) {
            return (T) intent.getSerializableExtra(key);
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除当前activity对象
        ActivityManager.getInstance().removeActivity(this);
        // 准备做网络请求销毁

    }

    public void initToolbar(Toolbar toolbar) {//初始化Toolbar
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_back_black);//设置返回的图标
    }

    /**
     * 设置状态栏为透明的
     */
    public void setTranslucentStatus() {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            win.clearFlags(bits | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(Color.TRANSPARENT);
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean onKey = true;
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                keyBack();
                break;
            default:
                onKey = super.onKeyDown(keyCode, event);
                break;
        }
        return onKey;
    }

    /**
     * 响应后退按键
     */
    public void keyBack() {
        Intent intent = new Intent();
        intent.setAction("wxlogin");
        sendBroadcast(intent);
        finish();
    }

    public void onPermissionSuccess(int requestCode) {

    }

    public void startAnimation(View view, int AnimationId) {//开始动画
        if (view != null && AnimationId != 0) {
            Animation animation = AnimationUtils.loadAnimation(this, AnimationId);
            view.startAnimation(animation);
        }
    }
}