package com.example.homework_9

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework_9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StoreCardAdapter

    private val productList: MutableList<Product> = mutableListOf (
        Product(2, R.drawable.image_1, "110", "title", CategoryType.PARTY),
        Product(3, R.drawable.image_2, "120", "title", CategoryType.CATEGORY1),
        Product(4, R.drawable.image_1, "130", "title", CategoryType.CATEGORY3),
        Product(5, R.drawable.image_1, "130", "title", CategoryType.CAMPING),
        Product(6, R.drawable.image_2, "130", "title", CategoryType.PARTY),
        Product(7, R.drawable.image_1, "130", "title", CategoryType.CATEGORY2),
        Product(8, R.drawable.image_2, "130", "title", CategoryType.CAMPING),
        Product(9, R.drawable.image_2, "90", "title", CategoryType.PARTY),
        Product(9, R.drawable.image_1, "90", "title", CategoryType.CATEGORY2),
        Product(9, R.drawable.image_2, "90", "title", CategoryType.CATEGORY1)
    )

    private fun setUpAdapter(){
        adapter = StoreCardAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter
        adapter.setData(productList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFilter()
        setUpAdapter()
    }

    private fun productFilter(){
        binding.apply {
            if(checkCategoryType() == null){
               adapter.setData(productList)
               return
            }

            val sendList = productList.filter { it.categoryType == checkCategoryType() }.toMutableList()
            adapter.setData(sendList)
        }
    }

    private fun checkCategoryType(): CategoryType?{
        binding.apply {
            return if (party.isChecked)
                CategoryType.PARTY
            else if (camping.isChecked)
                CategoryType.CAMPING
            else if (category1.isChecked)
                CategoryType.CATEGORY1
            else if (category2.isChecked)
                CategoryType.CATEGORY2
            else if (category3.isChecked)
                CategoryType.CATEGORY3
            else {
                null
            }
        }
    }

    private fun setUpFilter(){
        val whiteColor = ContextCompat.getColor(this, R.color.white)
        val grayColor = ContextCompat.getColor(this, R.color.gray)
        val checkedDrawable = ContextCompat.getDrawable(this, R.drawable.checked_button)
        val uncheckedDrawable = ContextCompat.getDrawable(this, R.drawable.unchecked_button)

        binding.filterRadioGroup.setOnCheckedChangeListener { group, _ ->
            for(i in 0 until group.childCount){
                val radioButton = group.getChildAt(i) as RadioButton // Create RadioButton Variable
                radioButton.apply {
                    if(radioButton.isChecked) // Change Checked Button Style
                        setCheckedStyle(whiteColor, checkedDrawable)
                    else // Change Unchecked Button Style
                        setUncheckedStyle(grayColor, uncheckedDrawable)
                }
            }
            productFilter()
        }
    }

    private fun RadioButton.setCheckedStyle(textColor: Int, drawable: Drawable?){
        setTextColor(textColor)
        background = drawable
    }

    private fun RadioButton.setUncheckedStyle(textColor: Int, drawable: Drawable?){
        setTextColor(textColor)
        background = drawable
    }

}