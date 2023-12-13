package miu.edu.cs473.foodapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.isGone
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import miu.edu.cs473.foodapp.R
import miu.edu.cs473.foodapp.adapter.MainPageAdapter
import miu.edu.cs473.foodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pageAdapter: MainPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        initViewPagers()
        initTabLayout()
        initBottomNavigationView()
    }

    private fun initViewPagers() {
        if (!this::pageAdapter.isInitialized) {
            pageAdapter = MainPageAdapter(this, binding.vpager)
        }
        pageAdapter.updateAdapter()
    }

    private fun initTabLayout() {
        val tabTitles = listOf(
            R.string.recipe,
            R.string.meal_scheduler,
            R.string.blog,
            R.string.contact,
            R.string.about_me
        )

        TabLayoutMediator(binding.tabLayout, pageAdapter.viewPager) { tab, position ->
            tab.text = resources.getText(tabTitles[position])
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    resources.getText(R.string.contact) -> unSelectedBottomNavigationView(3)
                    resources.getText(R.string.about_me) -> unSelectedBottomNavigationView(4)
                    else -> selectedBottomNavigationView(tab?.text.toString())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun unSelectedBottomNavigationView(index: Int) {
        updateBtnAction(index)
        binding.bottomNavigationView.menu.forEach { item ->
            item.isCheckable = false
        }
    }

    private fun updateBtnAction(index: Int) {
        binding.btnAction.isGone = index == 3 || index == 4
    }

    private fun selectedBottomNavigationView(name: String) {
        updateBtnAction(0)
        binding.bottomNavigationView.menu.forEach { item ->
            item.isCheckable = item.title.toString() == name
            if (item.isCheckable) {
                binding.bottomNavigationView.selectedItemId = item.itemId
            }
        }
    }

    private fun initBottomNavigationView() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            updateBtnAction(0)
            when (it.itemId) {
                R.id.recipe -> pageAdapter.setCurrentItem(0)
                R.id.mealScheduler -> pageAdapter.setCurrentItem(1)
                else -> pageAdapter.setCurrentItem(2)
            }
            true
        }
    }

    fun onAdd(view: View) {
        pageAdapter.onAdd()
    }
}
