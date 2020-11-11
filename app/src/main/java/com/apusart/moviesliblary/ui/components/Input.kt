package com.apusart.moviesliblary.ui.components

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.apusart.moviesliblary.R

import kotlinx.android.synthetic.main.input_component.view.*

class Input @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): LinearLayout(context, attrs) {
    private val myView = LayoutInflater.from(context)
        .inflate(R.layout.input_component, this, false)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Input,
            0, 0
        ).apply {

            val icon = getDrawable(R.styleable.Input_icon)
            val placeholder = getText(R.styleable.Input_placeholder)
            val color = getColor(
                R.styleable.Input_color,
                ResourcesCompat.getColor(resources, R.color.black, null)
            )

            val isLast = getBoolean(R.styleable.Input_is_last_in_form, false)
            val isPassword = getBoolean(R.styleable.Input_is_password, false)

            myView.button_component_image.setColorFilter(color)

            myView.button_component_text.setTextColor(color)

            if(icon == null) {
                myView.button_component_image.isVisible = false
            } else {
                myView.button_component_image.setImageDrawable(icon)
            }

            myView.button_component_text.hint = placeholder

            if (isPassword) {
                myView.button_component_text.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                myView.button_component_text.inputType =
                    InputType.TYPE_CLASS_TEXT
            }

            if(isLast)
                myView.button_component_text.imeOptions = EditorInfo.IME_ACTION_DONE
            else
                myView.button_component_text.imeOptions = EditorInfo.IME_ACTION_NEXT

        }

    }
}