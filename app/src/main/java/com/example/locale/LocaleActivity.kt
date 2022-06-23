package com.example.locale

import android.icu.text.MeasureFormat
import android.icu.util.Measure
import android.icu.util.MeasureUnit
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.locale.databinding.LocaleActivityBinding
import java.text.NumberFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class LocaleActivity : AppCompatActivity() {

    private lateinit var binding: LocaleActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = LocaleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setUSButton.setOnClickListener {
            setLocale(Locale.US)
        }
        binding.setUKButton.setOnClickListener {
            setLocale(Locale.UK)
        }
        binding.setESButton.setOnClickListener {
            setLocale(Locale.forLanguageTag("es-ES"))
        }
    }

    override fun onLocalesChanged(locales: LocaleListCompat) {
        super.onLocalesChanged(locales)
        Log.d("JJO", "Locales changed to $locales")
    }

    private fun setLocale(locale: Locale) {
        val appLocale = LocaleListCompat.forLanguageTags(locale.toLanguageTag())
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    override fun onResume() {
        super.onResume()
        render()
    }

    private fun render() {
        binding.date.text = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .format(ZonedDateTime.now())
        binding.currency.text = NumberFormat.getCurrencyInstance()
            .format(1234)
        binding.measure.text =
            MeasureFormat.getInstance(Locale.getDefault(), MeasureFormat.FormatWidth.WIDE)
                .formatMeasures(Measure(30, MeasureUnit.KILOGRAM))
    }
}
