package com.wxfjava.struggle.hotfix;

import android.content.Context;

import com.wxfjava.struggle.utils.L;
import com.wxfjava.struggle.utils.ReflexUtils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.lang.reflect.Array;

import dalvik.system.DexClassLoader;

public class FixManager {

    private String FIX_DEX_FILE = "/sdcard/f.dex";
    private File dexPath = null;
    private String foptPath = null;

    private Context mContext;

    public FixManager(Context context) {
        this.mContext = context;
        dexPath = context.getDir("odex", Context.MODE_PRIVATE);

        String optimizeDir = dexPath.getAbsolutePath()+File.separator+"opt_dex";
        File fopt = new File(optimizeDir);
        if(!fopt.exists()){
            fopt.mkdirs();
        }
        foptPath = fopt.getAbsolutePath();
    }

    public void fixDex() {
        L.i("修复...");
        try {
            // 将修复dex复制到工作目录下
            File srcFile = new File(FIX_DEX_FILE);
            File dexFile = new File(dexPath.getAbsolutePath(), srcFile.getName());
            FileUtils.copyFile(srcFile, dexFile);

            ClassLoader pathClassLoader = mContext.getClassLoader();
            Object pathList = ReflexUtils.getProperty(pathClassLoader, "pathList");
            Object dexElements = ReflexUtils.getProperty(pathList, "dexElements");
            int length = Array.getLength(dexElements);

            DexClassLoader dexClassLoader = new DexClassLoader(dexFile.getAbsolutePath(), foptPath, null, pathClassLoader);
            Object fixPathList = ReflexUtils.getProperty(dexClassLoader, "pathList");
            Object fixDexElements = ReflexUtils.getProperty(fixPathList, "dexElements");
            int fixLength = Array.getLength(fixDexElements);

            Object newDexElements = Array.newInstance(Array.get(dexElements, 0).getClass(), length + fixLength);
            System.arraycopy(fixDexElements, 0, newDexElements, 0, fixLength);
            L.i("l:" + length + ",fixl:" + fixLength);
            System.arraycopy(dexElements, 0, newDexElements, fixLength, length);

            boolean result = ReflexUtils.setProperty(pathList, "dexElements", newDexElements);
            L.i(result + "-" + pathList.toString());
        } catch (Exception e) {
            L.e(e);
        }
    }

    private int getLength(Object arr) {
        int length = 0;
        try {
            length = (int) ReflexUtils.getProperty(arr, "length");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

}
