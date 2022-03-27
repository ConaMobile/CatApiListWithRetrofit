package com.example.catapilistretrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    lateinit var bottomNav: BottomNavigationView
    lateinit var floatingBtn: FloatingActionButton
    lateinit var frameLayout: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        initViews(view)

        return view
    }

    private fun initViews(view: View) {
        bottomNav = view.findViewById(R.id.bottomNav)
        floatingBtn = view.findViewById(R.id.floatingBtn)
        frameLayout = view.findViewById(R.id.frameLayout)

        replaceFragment(SearchFragment())

        floatingBtn.setOnClickListener {
            replaceFragment(UploadFragment())
        }

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    replaceFragment(SearchFragment())
                }
                R.id.page_2 -> {
                    replaceFragment(ListFragment())
                }

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }
}