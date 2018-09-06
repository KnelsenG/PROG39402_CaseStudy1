package xyz.knelseng.www.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener { showFragment(FragmentDesc.FRAGMENT_1) }
        btn2.setOnClickListener { showFragment(FragmentDesc.FRAGMENT_2) }

    }

    private fun showFragment(fragmentDesc: FragmentDesc) {

        val frag = supportFragmentManager.findFragmentByTag(fragmentDesc.tag)
                ?: when (fragmentDesc) {
                    MainActivity.FragmentDesc.FRAGMENT_1 -> Fragment1.newInstance()
                    MainActivity.FragmentDesc.FRAGMENT_2 -> Fragment2.newInstance()
                }

        if (frag.isAdded && frag.isVisible)
            return

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, frag, fragmentDesc.tag)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            return supportFragmentManager.popBackStack()
        super.onBackPressed()
    }

    enum class FragmentDesc(val tag: String) {
        FRAGMENT_1("frag_1"), FRAGMENT_2("frag_2")
    }
}
