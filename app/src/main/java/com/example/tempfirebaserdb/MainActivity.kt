package com.example.tempfirebaserdb

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.tempfirebaserdb.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var stuList  : MutableLiveData<ArrayList<Student>> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Firebase.database.setPersistenceEnabled(true)
//        val dbInstance = Firebase.database
//        val dbRef = dbInstance.getReference("student")
//
//
//        stuList.observe(this) {
//            binding.updateBtn.setOnClickListener {
//                val updateObj = mapOf<String, Any>(
//                    "name" to "FirstUpdated",
//                    "address/city" to "CityUpdated"
//                )
//                val ref = Firebase.database.getReference("student").child(stuList.value?.get(0)?.key ?: "")
//                updateValue(ref,updateObj)
//            }
//            binding.deleteBtn.setOnClickListener {
//                val ref = Firebase.database.getReference("student").child(stuList.value?.get(0)?.key ?: "")
//                deleteValue(ref)
//            }
//        }

        val dbRef = Firebase.database
        val classesRef = dbRef.getReference("classes")
        val classARef = classesRef.child("classA")
        val classAMap = mapOf<String,Any>(
            "subject" to "Math"
        )
        classARef.setValue(classAMap)

        val stu1AttendanceMap = mapOf<String,Any>(
            "2024-06-01" to "present",
            "2024-06-02" to "absent"
        )
        val stu2AttendanceMap = mapOf<String,Any>(
            "2024-06-01" to "present",
            "2024-06-02" to "absent"
        )
        val student1Map = mapOf<String, Any>(
            "name" to "Alice",
            "attendance" to stu1AttendanceMap
        )
        val student1Ref = classARef.child("students").child("student1")
        student1Ref.setValue(student1Map)
        val student2 = StudentClass(name = "Bob", attendance = stu2AttendanceMap)

        val student2Ref = classARef.child("students").child("student2")
        student2Ref.setValue(student2)
//
//        binding.btn.setOnClickListener {
//            val dbKey = dbRef.push()
////            val stuObj  = Student("First",12, Address("First","City"),dbKey.key.toString())
////            dbKey.setValue(stuObj)
//            val map = mapOf<String,Any>(
//                "id" to dbKey.key.toString(),
//                "name" to "First",
//                "age" to "12"
//            )
//            dbKey.setValue(map)
//        }
//        dbRef.addValueEventListener(object  : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for(student in snapshot.children){
//                    val studentObj = student.getValue(Student::class.java)
//                    val tempList = ArrayList<Student>()
//                    tempList.add(studentObj!!)
//                    stuList.value = tempList
//                }
//                println("EdrrList ${stuList.value}")
//            }
//            override fun onCancelled(error: DatabaseError) {}
//        })

//        lifecycleScope.launch {
//            delay(3000)
//            dbRef.setValue("Hello..")
//        }
    }
//    fun updateValue(ref : DatabaseReference, map : Map<String,Any>){
//        ref.updateChildren(map)
//    }
//
//    fun deleteValue(ref : DatabaseReference){
//        ref.removeValue()
//    }
}