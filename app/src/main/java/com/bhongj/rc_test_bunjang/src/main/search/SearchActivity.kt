package com.bhongj.rc_test_bunjang.src.main.search

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.view.size
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivitySearchBinding
import com.bhongj.rc_test_bunjang.src.login.LoginActivity
import com.google.android.material.chip.Chip

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {
    val searchWordList = mutableListOf<String>("골프", "삼성노트북", "나이키")
    val searchManyWordList = mutableListOf<String>(
        "01 뮤츠",
        "02 보이져125",
        "03 발렌시아가",
        "04 레고 스타워즈",
        "05 염따 피규어",
        "06 픽시",
        "07 아이폰12미니",
        "08 아이폰11",
        "09 애플펜슬 2세대",
        "10 xmax"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showKeyboard(binding.tlbEdtSearch)

        setupManyChipGroupDynamically()

        binding.searchDeleteAll.setPaintFlags(binding.searchDeleteAll.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG);

        binding.searchDeleteAll.setOnClickListener {
            binding.searchRecentSearchChipGroup.removeAllViews()
            searchWordList.removeAll { true }
            if (binding.searchRecentSearchChipGroup.size == 0) {
                binding.scrRecentSearch.visibility = View.GONE
                binding.txtRecentSearch.visibility = View.GONE
            }
        }

        binding.tlbEdtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // search() // you can do anything
                if (binding.tlbEdtSearch.text.toString() in searchWordList) {
                    searchWordList.remove(binding.tlbEdtSearch.text.toString())
                }
                searchWordList.add(binding.tlbEdtSearch.text.toString())
                startActivity(Intent(this, LoginActivity::class.java))

                return@setOnEditorActionListener true
            }
            false
        }

    }

    override fun onStart() {
        super.onStart()

        setupChipGroupDynamically()

        if (binding.searchRecentSearchChipGroup.size > 0) {
            binding.scrRecentSearch.visibility = View.VISIBLE
            binding.txtRecentSearch.visibility = View.VISIBLE
        }
    }

    private fun setupChipGroupDynamically() {
        binding.searchRecentSearchChipGroup.removeAllViews()
        for (searchWord in searchWordList.reversed()) {
            binding.searchRecentSearchChipGroup.addView(createChip(searchWord))
        }
    }

    private fun setupManyChipGroupDynamically() {
        binding.searchManySearchChipGroup.removeAllViews()
        for (manyWord in searchManyWordList) {
            binding.searchManySearchChipGroup.addView(createManyChip(manyWord))
        }
    }

    private fun createChip(label: String): Chip {
        val chip = Chip(
            this,
            null,
            com.google.android.material.R.style.Widget_MaterialComponents_Chip_Entry
        )
        chip.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        chip.text = label
        chip.isCloseIconVisible = true
        chip.isChipIconVisible = true
        chip.isCheckable = false
        chip.isClickable = true
        chip.setTextAppearanceResource(R.style.search_chip_black)
        chip.textAlignment = View.TEXT_ALIGNMENT_CENTER
        chip.chipCornerRadius = 20f
        chip.chipStrokeWidth = 1f
        chip.setCloseIconResource(R.drawable.ic_baseline_close_24)
        chip.setCloseIconTintResource(R.color.gray)
        chip.setChipBackgroundColorResource(R.color.white)
        chip.setOnCloseIconClickListener {
            searchWordList.remove(label)
            binding.searchRecentSearchChipGroup.removeView(it)
            if (binding.searchRecentSearchChipGroup.size == 0) {
                binding.scrRecentSearch.visibility = View.GONE
                binding.txtRecentSearch.visibility = View.GONE
            }
        }
        return chip
    }

    private fun createManyChip(label: String): Chip {
        val chip = Chip(
            this,
            null,
            com.google.android.material.R.style.Widget_MaterialComponents_Chip_Entry
        )
        chip.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val spannableString = SpannableString(label)
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#000000")),
            0,
            2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        chip.text = spannableString
        chip.isCloseIconVisible = true
        chip.isChipIconVisible = true
        chip.isCheckable = false
        chip.isClickable = true
        chip.setTextAppearanceResource(R.style.search_chip_black)
        chip.textAlignment = View.TEXT_ALIGNMENT_CENTER
        chip.chipCornerRadius = 20f
        chip.chipStrokeWidth = 1f
        chip.setCloseIconResource(R.drawable.ic_baseline_add_alert_24)
        chip.setCloseIconTintResource(R.color.bungaeRed)
        chip.setChipBackgroundColorResource(R.color.white)
        chip.setOnCloseIconClickListener {
        }
        return chip
    }

}