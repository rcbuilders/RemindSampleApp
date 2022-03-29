package com.remind.sampleapp.date_format

import android.annotation.SuppressLint
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityDateFormatBinding
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateFormatActivity : BaseActivity<ActivityDateFormatBinding>(R.layout.activity_date_format) {

    @SuppressLint("SetTextI18n")
    override fun afterOnCreate() {
        super.afterOnCreate()

        // 현재 시간 milliseconds
//        val currentMillis = System.currentTimeMillis()
        // LocalDateTime 을 이용하여 현재시간 milliseconds 가져오기
        val currentMillis = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()?.toEpochMilli() ?: 0

        // milliseconds -> LocalDateTime 으로 변환
        val currentDateTime =
            Instant.ofEpochMilli(currentMillis).atZone(ZoneId.systemDefault()).toLocalDateTime()

        // TextView에 날짜/시간 포멧에 맞춰 표시
        binding.tvDate.text = "현재 날짜/시간 : ${
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초").format(currentDateTime)
        }"

        // 특정 날짜 -> LocalDate 로 변환
        val localDate = LocalDate.parse("2023-11-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        // 특정 날짜 포멧에 맞춰 표시
        binding.tvDate.text = "${binding.tvDate.text} \n특정 날짜 : ${
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일").format(localDate)
        }"

        // 특정 날짜 -> milliseconds 로 변환
        val localDateMillis =
            localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()?.toEpochMilli() ?: 0

        // 특정 날짜의 milliseconds 표시 (https://currentmillis.com/ 에서 확인)
        binding.tvDate.text = "${binding.tvDate.text} \n특정 날짜 millis : $localDateMillis"

        // 특정 날짜 ISO 표시
        binding.tvDate.text = "${binding.tvDate.text} \n특정 날짜 ISO 표시 : ${
            DateTimeFormatter.ISO_INSTANT.format(
                Instant.ofEpochMilli(localDateMillis)
            )
        }"
    }

}