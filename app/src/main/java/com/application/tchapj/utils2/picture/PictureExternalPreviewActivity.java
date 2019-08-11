package com.application.tchapj.utils2.picture;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.R;
import com.application.tchapj.task.activity.ImagePreviewCopyActivity;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.picture.config.PictureConfig;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.dialog.CustomDialog;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.utils2.picture.permissions.RxPermissions;
import com.application.tchapj.utils2.picture.tools.DebugUtil;
import com.application.tchapj.utils2.picture.tools.PictureFileUtils;
import com.application.tchapj.utils2.picture.tools.ScreenUtils;
import com.application.tchapj.utils2.picture.widget.PreviewViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.ui
 * email：邮箱->893855882@qq.com
 * data：17/01/18
 */
public class PictureExternalPreviewActivity extends PictureBaseActivity implements View.OnClickListener {
    private ImageButton left_back;
    private TextView tv_title;
    private PreviewViewPager viewPager;
    private List<LocalMedia> images = new ArrayList<>();
    private int position = 0;
    private String directory_path;
    private SimpleFragmentAdapter adapter;
    private LayoutInflater inflater;
    private RxPermissions rxPermissions;
//    private loadDataThread loadDataThread;
protected CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_activity_external_preview);
        inflater = LayoutInflater.from(this);
        tv_title = (TextView) findViewById(R.id.picture_title);
        left_back = (ImageButton) findViewById(R.id.left_back);
        viewPager = (PreviewViewPager) findViewById(R.id.preview_pager);
        position = getIntent().getIntExtra(PictureConfig.EXTRA_POSITION, 0);
//        directory_path = getIntent().getStringExtra(PictureConfig.DIRECTORY_PATH);
        directory_path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ctrl_media";
        File appDir = new File(directory_path);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        images = (List<LocalMedia>) getIntent().getSerializableExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST);
        left_back.setOnClickListener(this);
        initViewPageAdapterData();
    }

    private void initViewPageAdapterData() {
        tv_title.setText(position + 1 + "/" + images.size());
        adapter = new SimpleFragmentAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_title.setText(position + 1 + "/" + images.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
        overridePendingTransition(0, R.anim.a3);
    }

    public class SimpleFragmentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View contentView = inflater.inflate(R.layout.picture_image_preview, container, false);
            final PhotoView imageView = (PhotoView) contentView.findViewById(R.id.preview_image);
            LocalMedia media = images.get(position);
            if (media != null) {
                final String pictureType = media.getPictureType();
                final String path;
                if (media.isCompressed()) {
                    // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                boolean isHttp = PictureMimeType.isHttp(path);
                // 可以长按保存并且是网络图片显示一个对话框
                if (isHttp) {
                    showPleaseDialog();
                }
                boolean isGif = PictureMimeType.isGif(pictureType);
                // 压缩过的gif就不是gif了
                if (isGif && !media.isCompressed()) {
                    RequestOptions gifOptions = new RequestOptions()
                            .override(480, 800)
                            .priority(Priority.HIGH)
                            .diskCacheStrategy(DiskCacheStrategy.NONE);
                    Glide.with(PictureExternalPreviewActivity.this)
                            .asGif()
                            .apply(gifOptions)
                            .load(path)
                            .listener(new RequestListener<GifDrawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model
                                        , Target<GifDrawable> target, boolean isFirstResource) {
                                    dismissDialog();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GifDrawable resource, Object model
                                        , Target<GifDrawable> target, DataSource dataSource,
                                                               boolean isFirstResource) {
                                    dismissDialog();
                                    return false;
                                }
                            })
                            .into(imageView);
                } else {
                    RequestOptions options = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .override(480, 800);
                    Glide.with(PictureExternalPreviewActivity.this)
                            .load(path)
                            .apply(options)
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model
                                        , Target<Drawable> target, boolean isFirstResource) {
                                    dismissDialog();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model,
                                                               Target<Drawable> target,
                                                               DataSource dataSource,
                                                               boolean isFirstResource) {
                                    dismissDialog();
                                    return false;
                                }
                            })
                            .into(imageView);
                }
                imageView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float x, float y) {
                        finish();
                        overridePendingTransition(0, R.anim.a3);
                    }
                });

                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (rxPermissions == null) {
                            rxPermissions = new RxPermissions(PictureExternalPreviewActivity.this);
                        }
                        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Observer<Boolean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onNext(Boolean aBoolean) {
                                        if (aBoolean) {
                                            showDownLoadDialog(path);
                                        } else {
                                            showToast(getString(R.string.picture_jurisdiction));
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                });
                        return true;
                    }
                });
            }
            (container).addView(contentView, 0);
            return contentView;
        }
    }

    /**
     * 下载图片提示
     */
    private void showDownLoadDialog(final String path) {
        final CustomDialog dialog = new CustomDialog(PictureExternalPreviewActivity.this,
                ScreenUtils.getScreenWidth(PictureExternalPreviewActivity.this) * 3 / 4,
                ScreenUtils.getScreenHeight(PictureExternalPreviewActivity.this) / 4,
                R.layout.picture_wind_base_dialog_xml, R.style.Theme_dialog);
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btn_commit = (Button) dialog.findViewById(R.id.btn_commit);
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        tv_title.setText(getString(R.string.picture_prompt));
        tv_content.setText(getString(R.string.picture_prompt_content));
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isHttp = PictureMimeType.isHttp(path);
                if (isHttp) {
//                    loadDataThread = new loadDataThread(path);
//                    loadDataThread.start();
                    showLoadingImage(path);
                } else {
                    // 有可能本地图片
                    try {
                        showPleaseDialog();
                        String dirPath = PictureFileUtils.createDir(PictureExternalPreviewActivity.this,
                                System.currentTimeMillis() + ".png", directory_path);
                        PictureFileUtils.copyFile(path, dirPath);

                        //更新系统图库
                        File file = new File(path);
                        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                        Uri uri = Uri.fromFile(file);
                        intent.setData(uri);
                        sendBroadcast(intent);

                        showToast(getString(R.string.picture_save_success) + "\n" + dirPath);
                        dismissDialog();
                    } catch (IOException e) {
                        showToast(getString(R.string.picture_save_error) + "\n" + e.getMessage());
                        dismissDialog();
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    // 进度条线程
//    public class loadDataThread extends Thread {
//        private String path;
//
//        public loadDataThread(String path) {
//            super();
//            this.path = path;
//        }
//
//        public void run() {
//            try {
//                showLoadingImage(path);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    // 下载图片保存至手机
    public void showLoadingImage(final String urlPath) {
        addSubcription(Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Utils.saveImageToPhotos(PictureExternalPreviewActivity.this, Utils.returnBitMap(urlPath));
                e.onNext("success");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (TextUtils.equals(s, "success")) {
                    Toast.makeText(PictureExternalPreviewActivity.this, "图片保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        }));




//        try {
//            URL u = new URL(urlPath);
//            String path = PictureFileUtils.createDir(PictureExternalPreviewActivity.this,
//                    System.currentTimeMillis() + ".png", directory_path);
//            byte[] buffer = new byte[1024 * 8];
//            int read;
//            int ava = 0;
//            long start = System.currentTimeMillis();
//            BufferedInputStream bin;
//            bin = new BufferedInputStream(u.openStream());
//            BufferedOutputStream bout = new BufferedOutputStream(
//                    new FileOutputStream(path));
//            while ((read = bin.read(buffer)) > -1) {
//                bout.write(buffer, 0, read);
//                ava += read;
//                long speed = ava / (System.currentTimeMillis() - start);
//                DebugUtil.i("Download: " + ava + " byte(s)"
//                        + "    avg speed: " + speed + "  (kb/s)");
//            }
//            bout.flush();
//            bout.close();
//
//            //更新系统图库
//            File file = new File(path);
//            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
//            Uri uri = Uri.fromFile(file);
//            intent.setData(uri);
//            sendBroadcast(intent);
//
//            Message message = handler.obtainMessage();
//            message.what = 200;
//            message.obj = path;
//            handler.sendMessage(message);
//        } catch (IOException e) {
//            showToast(getString(R.string.picture_save_error) + "\n" + e.getMessage());
//            e.printStackTrace();
//        }
    }

    public void addSubcription(Disposable disposable) {
        if (disposable == null) {
            return;
        }

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        compositeDisposable.add(disposable);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    String toastStr = (String) msg.obj;
                    showToast(toastStr);
                    dismissDialog();
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, R.anim.a3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disponse();
//        if (loadDataThread != null) {
//            handler.removeCallbacks(loadDataThread);
//            loadDataThread = null;
//        }
    }

    private void disponse() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }


    //将 Bitmap 保存到SD卡
    public boolean saveBitmapToSdCard(Context context, Bitmap saveBitmap) {
        boolean result = false;
        String name = System.currentTimeMillis() + "";
        if(saveBitmap == null){
            return result;
        }

        //创建位图保存目录
        String path = Environment.getExternalStorageDirectory() + "/Pictures/";
        File sd = new File(path);
        if (!sd.exists()) {
            sd.mkdir();
        }

        File file = new File(path + name + ".jpg");
        FileOutputStream fileOutputStream = null;
        if (!file.exists()) {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    fileOutputStream = new FileOutputStream(file);
                    saveBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();

                    //通知图库更新
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri = Uri.fromFile(file);
                    intent.setData(uri);
                    context.sendBroadcast(intent);
                    Message message = handler.obtainMessage();
                    message.obj = "图片保存成功";
                    handler.sendMessage(message);
                    result = true;
                } else {
                    Message message = handler.obtainMessage();
                    message.obj = "不能读取到SD卡";
                    handler.sendMessage(message);
                }
                dismissDialog();

            } catch (Exception e) {
                dismissDialog();
                e.printStackTrace();
            }

        }
        return result;
    }
}
