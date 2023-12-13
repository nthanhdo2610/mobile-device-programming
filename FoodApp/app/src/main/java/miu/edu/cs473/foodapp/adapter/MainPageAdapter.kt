package miu.edu.cs473.foodapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import miu.edu.cs473.foodapp.listener.BaseFragmentListener
import miu.edu.cs473.foodapp.view.fragment.*

class MainPageAdapter(
    fragmentActivity: FragmentActivity,
    val viewPager: ViewPager2
) : FragmentStateAdapter(fragmentActivity) {

    private val recipeFragment = RecipeFragment()
    private val schedulerFragment = SchedulerFragment()
    private val blogFragment = BlogFragment()
    private val contactFragment = ContactFragment()
    private val aboutMeFragment = AboutMeFragment()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> recipeFragment
            1 -> schedulerFragment
            2 -> blogFragment
            3 -> contactFragment
            else -> aboutMeFragment
        }
    }

    override fun getItemCount(): Int = FRAGMENT_COUNT

    fun updateAdapter() {
        viewPager.adapter = this
    }

    fun setCurrentItem(index: Int) {
        if (viewPager.currentItem != index) {
            viewPager.setCurrentItem(index, true)
        }
    }

    private fun getCurrentItem(): Int = viewPager.currentItem

    fun onAdd() {
        getCurrentFragment().onAdd()
    }

    private fun getCurrentFragment(): BaseFragmentListener {
        return when (getCurrentItem()) {
            0 -> recipeFragment
            1 -> schedulerFragment
            2 -> blogFragment
            3 -> contactFragment
            else -> aboutMeFragment
        }
    }

    companion object {
        private const val FRAGMENT_COUNT = 5
    }
}
