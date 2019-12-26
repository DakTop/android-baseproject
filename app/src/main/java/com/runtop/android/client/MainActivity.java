package com.runtop.android.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.runtop.android.baselibrary.network.RetrofitClient;
import com.runtop.android.functionlibrary.view.dialog.ProgerssDialog;

public class MainActivity extends AppCompatActivity {

    TestApi api;
    ProgerssDialog progerssDialog;
    private Fragment[] mFragments = {new MinFragment(), new OrderFragment(), new DeviceFragment()};
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = RetrofitClient.instance().creatApi(TestApi.class);
        progerssDialog = new ProgerssDialog(this, "加载中");
        //
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                onTabItemSelected(menuItem.getItemId());
                return true;
            }
        });
        onTabItemSelected(R.id.homeFragment);
    }

    private void onTabItemSelected(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.homeFragment:
                fragment = mFragments[0];
                break;
            case R.id.orderFragment:
                fragment = mFragments[1];
                break;

            case R.id.deviceFragment:
                fragment = mFragments[2];
                break;
            default:
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
        }
    }

    public void clickIt() {
        progerssDialog.show();
        api.getData("0", "1").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                progerssDialog.dismiss();
                Log.i("请求返回数据：", s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
