package com.mina_magid.benshty

import android.content.Context
import android.graphics.Color
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.math.sign

class TimeDayAdapter(
    val context: Context,
    private val times: ArrayList<String>,
    val day: String
): RecyclerView.Adapter<TimeDayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.date_table_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hour.text = times[position].toString()
        var num = 0
        holder.timeCheck.setOnClickListener {
            num +=1
            if (num ==1 ) {
                holder.customTime.isVisible = true
                holder.checkImg.isVisible = true
            }else{
                num = 0
                holder.checkImg.visibility =View.INVISIBLE
                holder.customTime.isVisible = false
            }
            holder.t1.setOnClickListener {
                holder.t1.background= context.resources.getDrawable(R.drawable.shape1)
                holder.t1.setTextColor(context.resources.getColor(R.color.white))

                holder.t2.background= context.resources.getDrawable(R.drawable.shape4)
                holder.t2.setTextColor(context.resources.getColor(R.color.main_color))

                holder.t3.background= context.resources.getDrawable(R.drawable.shape4)
                holder.t3.setTextColor(context.resources.getColor(R.color.main_color))
            }

            holder.t2.setOnClickListener {
                holder.t2.background= context.resources.getDrawable(R.drawable.shape1)
                holder.t2.setTextColor(context.resources.getColor(R.color.white))

                holder.t1.background= context.resources.getDrawable(R.drawable.shape4)
                holder.t1.setTextColor(context.resources.getColor(R.color.main_color))

                holder.t3.background= context.resources.getDrawable(R.drawable.shape4)
                holder.t3.setTextColor(context.resources.getColor(R.color.main_color))
            }

            holder.t3.setOnClickListener {
                holder.t3.background= context.resources.getDrawable(R.drawable.shape1)
                holder.t3.setTextColor(context.resources.getColor(R.color.white))

                holder.t2.background= context.resources.getDrawable(R.drawable.shape4)
                holder.t2.setTextColor(context.resources.getColor(R.color.main_color))

                holder.t1.background= context.resources.getDrawable(R.drawable.shape4)
                holder.t1.setTextColor(context.resources.getColor(R.color.main_color))
            }
        }

        if (times[position] == times[times.size-1]) {
            holder.more.visibility = View.INVISIBLE
        }
         makeDate("1",day,"to")
    }

    private fun makeDate(s: String, day: String, s1: String) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
//        user["name"] = helper.name.toString()
//        user["phone"] = helper.phone.toString()
//
//        db.collection(Constants.HELPERS)
//            .add(user)
//            .addOnSuccessListener {
//                context.Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show()
//                addHelper_btn.isEnabled = false
//                helperName.setText("")
//                helperPhone.setText("")
//            }
//            .addOnFailureListener{
//                Toast.makeText(requireContext(), "Failed to add", Toast.LENGTH_SHORT).show()
//
//            }
    }

    override fun getItemCount(): Int {
        return times.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var timeCheck = itemView.findViewById<CardView>(R.id.timeCheck)!!
        val customTime = itemView.findViewById<LinearLayout>(R.id.customTime)!!
        val hour = itemView.findViewById<TextView>(R.id.hour)!!
        val t1 = itemView.findViewById<TextView>(R.id.t1)!!
        val t2 = itemView.findViewById<TextView>(R.id.t2)!!
        val t3 = itemView.findViewById<TextView>(R.id.t3)!!
        val checkImg = itemView.findViewById<ImageView>(R.id.check_img)!!
        val more = itemView.findViewById<ImageView>(R.id.more)!!
    }
}