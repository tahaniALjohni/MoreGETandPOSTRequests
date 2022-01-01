package com.example.moregetandpostrequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var etName : EditText
    lateinit var btSave: Button
    lateinit var btView: Button
    lateinit var rvMain: RecyclerView

    private lateinit var rvAdapter: RecyclerViewAdapter
    private lateinit var infoList: ArrayList<String>

    var apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        btSave = findViewById(R.id.btSave)
        btView = findViewById(R.id.btnView)
        rvMain = findViewById(R.id.rvMain)

        infoList =  arrayListOf()
        rvAdapter = RecyclerViewAdapter(infoList)

        btSave.setOnClickListener {
            if (etName.text.isNotEmpty() ) {
                addSingleUser()
                etName.text.clear()
                etName.clearFocus()
            } else {
                Toast.makeText(applicationContext, "please add your name!!", Toast.LENGTH_SHORT).show()
            }
        }

        btView.setOnClickListener {
            getAllUsers()
            rvAdapter.update()
        }
    }

    private fun addSingleUser() {
        val nameIN = etName.text.toString()
        val locationIN = "KSA"
        apiInterface?.addUser(Data.UsersDataItem(locationIN,nameIN))?.enqueue(object : Callback<Data.UsersDataItem?> {
            override fun onResponse(call: Call<Data.UsersDataItem?>,response: Response<Data.UsersDataItem?>) {
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Data.UsersDataItem?>, t: Throwable) {
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getAllUsers() {
        apiInterface?.getUser()?.enqueue(object : Callback<List<Data.UsersDataItem>> {
            override fun onResponse(call: Call<List<Data.UsersDataItem>>,response: Response<List<Data.UsersDataItem>>) {
                for (User in response.body()!!) {
                    val fullInfo = "Name: ${User.name} \n Location: ${User.location} "
                    infoList.add(fullInfo)
                }
                rvMain.adapter = rvAdapter
                rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
                rvMain.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Data.UsersDataItem>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}