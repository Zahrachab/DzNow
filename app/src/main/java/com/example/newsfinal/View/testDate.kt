package com.example.newsfinal.View

import android.annotation.TargetApi
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.newsfinal.R
import kotlinx.android.synthetic.main.activity_test_date.*
import android.content.Intent
import android.provider.CalendarContract.Events
import android.provider.CalendarContract
import android.icu.util.Calendar
import android.os.Build
import android.support.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

class testDate : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.newsfinal.R.layout.activity_test_date)

         // var input: String? = intent.getStringExtra("text")
        var input="John Doe at:2016-06-16 Notes:This is a test. " +
                "John Doe at:2017-07-17 Notes:This is a test." +
                "here an exemple of article. you can add event and" +
                "make your own configuration" +
                "it 's so simple.  " +
                "John Doe at:2018-08-18 Notes:This is a test." +
                "John Doe at:2019-09-19 Notes:This is a test. " +
                "here an exemple of article. you can add event and" +
                "make your own configuration" +
                "it 's so simple.  " +
                "John Doe at:2019-09-22 Notes:This is a test.  "+
                "here an exemple of article. you can add event and" +
                "make your own configuration" +
                "it 's so simple.  "
        
        textShow.text=input

        var listPat = mutableListOf("")
        listPat.remove("")
        var find=false

        val regex = " at:(\\d{4}-\\d{2}-\\d{2}) Notes:"
        val m = Pattern.compile(regex).matcher(input)


        while (m.find()) {
            val date1 = SimpleDateFormat("yyyy-MM-dd").parse(m.group(1))
            listPat.add(m.group(1).toString())
        }

        if(listPat.size>0){
            tv10.text=listPat.get(0)
        }

        var i=0

        this.btn11.setOnClickListener{
            if (i<listPat.size) {

               var date = LocalDate.parse(listPat.get(i), DateTimeFormatter.ISO_DATE)
               val beginTime = Calendar.getInstance()
               beginTime.set(date.year, date.monthValue, date.dayOfMonth, 8, 30)
               val endTime = Calendar.getInstance()
               endTime.set(2019, 10, 17, 8, 30)

                var str=evtName.text

                 val intent = Intent(Intent.ACTION_INSERT)
                   .setData(Events.CONTENT_URI)
                   .putExtra(
                       CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                       beginTime.getTimeInMillis()
                   )
                   .putExtra(
                       CalendarContract.EXTRA_EVENT_END_TIME,
                       beginTime.getTimeInMillis()
                   )
                   .putExtra(Events.TITLE, "$str")
                   .putExtra(Events.DESCRIPTION, "Group class")
                   .putExtra(Events.EVENT_LOCATION, "The gym")
                   .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
                   .putExtra(Intent.EXTRA_EMAIL, "fa_bouali@esi.dz")

                startActivity(intent)

                i++
                if (i<listPat.size) {
                    tv10.text=listPat.get(i)
                    evtName.setText("")
                }

            } else {
                tv10.text=""
                evtName.setText("terminé!")
                evtName.isEnabled=false
                evtName.hint=""
                btn11.isEnabled=false
                ignr.isEnabled=false

            }
        }

        this.ignr.setOnClickListener{
            if (i<listPat.size-1) {
                i++
                if (i<listPat.size) {
                    tv10.text=listPat.get(i)
                    evtName.setText("")
                }

            } else {
                tv10.text="terminé!"
                evtName.setText("")
                evtName.hint=""
                evtName.isEnabled=false
                btn11.isEnabled=false
                ignr.isEnabled=false
            }


        }


    }
}
