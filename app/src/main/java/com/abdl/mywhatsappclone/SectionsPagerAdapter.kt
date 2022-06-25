package com.abdl.mywhatsappclone

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abdl.mywhatsappclone.ui.CallsFragment
import com.abdl.mywhatsappclone.ui.ChatsFragment
import com.abdl.mywhatsappclone.ui.StatusFragment

class SectionsPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = ChatsFragment()
            1 -> fragment = StatusFragment()
            2 -> fragment = CallsFragment()
        }
        return fragment as Fragment
    }

}