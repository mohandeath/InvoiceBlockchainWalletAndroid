package mvp.larin.cash.larinmvp.view.common

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import mvp.larin.cash.larinmvp.R

class MainActivity : ActParent() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_home)
    }
}
