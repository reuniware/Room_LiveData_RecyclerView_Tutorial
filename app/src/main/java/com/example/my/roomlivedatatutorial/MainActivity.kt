package com.example.my.roomlivedatatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userList : List<User>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userDao = AppDatabase.getDatabase(applicationContext).getUserDao()
        userList = userDao.getAllUsers()
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(userList)

        recyclerView1.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // Get data access object (for accessing data in database)
        var userViewModel = UserViewModel(application)
        userViewModel.getAllUsers().observe(this, Observer<List<User>>() {
            editText.setText(editText.text.toString() + " observer : nb records = " + userDao.getAllUsers().size + " ; ")
        })

        button2.setOnClickListener {
            Toast.makeText(applicationContext, "Processing", Toast.LENGTH_SHORT).show()

            userList = userDao.getAllUsers()

            editText.setText(editText.text.toString() + " Inserting new user ; ")
            var nbUsers = userList.size
            editText.setText(editText.text.toString() + "nb users in db = " + nbUsers + " ; ")

            var newUser = User(0,"testFirstName","testLastName")
            userDao.insertUsers(newUser)
        }

    }
}
