package com.thiagosantos.colorpikers

import android.graphics.Color
import android.graphics.Color.parseColor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import com.thiagosantos.colorpikers.databinding.ActivityMainBinding
import com.thiagosantos.colorpikers.databinding.ColorpickerBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnColorPicker.setOnClickListener {
            binding.includeColorpicker.colorSelector.visibility = View.VISIBLE
        }

        binding.btnColorSelected.setOnClickListener {
            binding.includeColorpicker.colorSelector.visibility = View.VISIBLE
        }

        seekBarColor(binding.includeColorpicker.colorA)
        seekBarColor(binding.includeColorpicker.colorR)
        seekBarColor(binding.includeColorpicker.colorG)
        seekBarColor(binding.includeColorpicker.colorB)


        binding.includeColorpicker.colorCancelBtn.setOnClickListener {
            binding.includeColorpicker.colorSelector.visibility = View.GONE
        }

        binding.includeColorpicker.colorOkBtn.setOnClickListener {
            val color:String = getColorString()
            binding.btnColorSelected.setBackgroundColor(Color.parseColor(color))
            binding.includeColorpicker.colorSelector.visibility = View.GONE
        }
    }

    fun getColorString(): String {
        var a = Integer.toHexString(((255*binding.includeColorpicker.colorA.progress)/binding.includeColorpicker.colorA.max))
        if(a.length==1) a = "0$a"
        var r = Integer.toHexString(((255*binding.includeColorpicker.colorR.progress)/binding.includeColorpicker.colorR.max))
        if(r.length==1) r = "0$r"
        var g = Integer.toHexString(((255*binding.includeColorpicker.colorG.progress)/binding.includeColorpicker.colorG.max))
        if(g.length==1) g = "0$g"
        var b = Integer.toHexString(((255*binding.includeColorpicker.colorB.progress)/binding.includeColorpicker.colorB.max))
        if(b.length==1) b = "0$b"
        return "#$a$r$g$b"
   }

    private fun seekBarColor(colorSeekBar: SeekBar ){
        colorSeekBar.max = 255
        colorSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                val colorStr = getColorString()
                binding.includeColorpicker.strColor.setText(
                    colorStr.replace("#","").uppercase(
                        Locale.ROOT
                    )
                )
                binding.includeColorpicker.btnColorPreview.setBackgroundColor(Color.parseColor(colorStr))
            }
        })
    }

}