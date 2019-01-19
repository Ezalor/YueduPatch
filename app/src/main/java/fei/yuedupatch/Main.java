package fei.yuedupatch;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage, IXposedHookInitPackageResources {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (!lpparam.packageName.equals("com.gedoor.monkeybook"))
            return;

        XposedHelpers.findAndHookMethod("com.kunfei.bookshelf.MApplication", lpparam.classLoader, "h", new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(true);
                    }
                });
    }

        @Override
        public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {

            if (!resparam.packageName.equals("com.gedoor.monkeybook"))
                return;

            resparam.res.setReplacement("com.gedoor.monkeybook", "string", "about_description", "这是一款开源的阅读软件，你可以fork我们的代码自己编译APK。欢迎提交代码帮助改善应用。(P)");
        }
}
