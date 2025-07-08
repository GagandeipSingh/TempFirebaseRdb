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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var stuList: MutableLiveData<ArrayList<Student>> = MutableLiveData()
    private var userList: ArrayList<User> = arrayListOf()
    private var list: ArrayList<String> = arrayListOf()
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

//        val dbRef = Firebase.database
//        val classesRef = dbRef.getReference("classes")
//        val classARef = classesRef.child("classA")
//        val classAMap = mapOf<String,Any>(
//            "subject" to "Math"
//        )
//        classARef.setValue(classAMap)
//
//        val stu1AttendanceMap = mapOf<String,Any>(
//            "2024-06-01" to "present",
//            "2024-06-02" to "absent"
//        )
//        val stu2AttendanceMap = mapOf<String,Any>(
//            "2024-06-01" to "present",
//            "2024-06-02" to "absent"
//        )
//        val student1Map = mapOf<String, Any>(
//            "name" to "Alice",
//            "attendance" to stu1AttendanceMap
//        )
//        val student1Ref = classARef.child("students").child("student1")
//        student1Ref.setValue(student1Map)
//        val student2 = StudentClass(name = "Bob", attendance = stu2AttendanceMap)
//
//        val student2Ref = classARef.child("students").child("student2")
//        student2Ref.setValue(student2)
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

//        val dbRef = Firebase.database
//        val usersRef = dbRef.getReference("users")
//        val user1 = User(name = "First", age = 20)
//        val user2 = User(name = "Second", age = 30)
//        val user3 = User(name = "Third", age = 28)
//        val user1Ref = usersRef.child("user1")
//        val user2Ref = usersRef.child("user2")
//        val user3Ref = usersRef.child("user3")
//        user1Ref.setValue(user1)
//        user2Ref.setValue(user2)
//        user3Ref.setValue(user3)

//        val query = usersRef.orderByChild("age")
//        val query = usersRef.orderByChild("age").startAt(28.0)
//        val query = usersRef.orderByChild("age").endAt(28.0)
//        val query = usersRef.orderByChild("age").startAt(21.0).endAt(29.0)
//        val query = usersRef.orderByChild("age").equalTo(28.0)
//        val query = usersRef.orderByChild("age").endAt(29.0).limitToFirst(1)
//        val query = usersRef.orderByChild("age").endAt(29.0).limitToLast(1)
//        val query = usersRef.orderByChild("age").limitToLast(2)
//        val query = usersRef.orderByChild("name").startAt("A").endAt("Z")
//        val query = usersRef.orderByChild("name")

//        val database = Firebase.database
//
//        val ref = database.getReference("users")
//        val auth = Firebase.auth
//        val currentUser = auth.currentUser
//
//        auth.signInWithEmailAndPassword("first@gmail.com","12345678").addOnCompleteListener { task ->
//            if(task.isSuccessful){
//                val currentUser = auth.currentUser?.uid
//                println("Edrr $currentUser")
//            }
//        }
//        if(currentUser != null){
//            ref.addListenerForSingleValueEvent(object : ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    for (userSnap in snapshot.children){
//                        if(userSnap.exists()){
//                            val user = userSnap.getValue(User::class.java)
//                            if (user != null){
//                                userList.add(user)
//                            }
//                        }
//                    }
//                    userList.reverse()
//                    println("Edrr" + userList)
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    println("Edrr ${error.message}")
//                }
//            })
//        } else{
//            Snackbar.make(binding.root,"You are not Signed In..", Snackbar.LENGTH_INDEFINITE).show()
//        }

//
//    }
//    fun updateValue(ref : DatabaseReference, map : Map<String,Any>){
//        ref.updateChildren(map)
//    }
//
//    fun deleteValue(ref : DatabaseReference){
//        ref.removeValue()
//    }

//        val dbRef = Firebase.database
//        val usersRef = dbRef.getReference("Users")
//        val query = usersRef.orderByKey()
//        val query = usersRef.orderByValue().limitToLast(2)
//        val userMap=mapOf<String, Any>(
//            "First" to 12,
//            "Second" to 34,
//            "third" to 23,
//            "fourth" to 10
//        )
//        usersRef.child("First").setValue(12)
//        usersRef.child("Second").setValue(42)
//        usersRef.child("Third").setValue(22)
//        usersRef.child("Fourth").setValue(32)
//        usersRef.setValue(userMap)
//        query.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (userSnap in snapshot.children) {
//                    val userKey = userSnap.key
//                    val userValue = userSnap.value
//                    if (userValue != null) {
//
//                        list.add("$userKey : $userValue")
//
//                    }
//                }
//                list.reverse()
//                println("edrr  $list")
//            }
//
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })


        // Firebase Auth

//        val auth = FirebaseAuth.getInstance()
//        var currentUser = auth.currentUser?.uid
//        println("Edrr $currentUser")
//        auth.signOut()
//        currentUser = auth.currentUser?.uid
//        println("Edrr $currentUser")

//        auth.createUserWithEmailAndPassword("second@gmail.com","12345678").addOnCompleteListener { task ->
//            if(task.isSuccessful){
//                val currentUser = auth.currentUser?.uid
//                println("Edrr $currentUser")
//            }
//        }
        val auth= Firebase.auth
//        auth.signInWithEmailAndPassword("second@gmail.com","12345678").addOnCompleteListener { task ->
//            if(task.isSuccessful){
//                val currentUser = auth.currentUser?.uid
//                println("Edrr $currentUser")
//            }
//        }
//
        val currentUser=auth.currentUser?.uid


        val dbRef= Firebase.database
        val ref=dbRef.getReference("Users")
//        val stu1Ref=ref.child("C8IawjbdDGWHRuzww4l1xTPVtP12")
//        val stu1= StudentClass("One","First")
//        stu1Ref.setValue(stu1)
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                println("edrr ${snapshot}")
            }

            override fun onCancelled(error: DatabaseError) {
                println("edrr ${error.message}")
            }

        })

    }
}