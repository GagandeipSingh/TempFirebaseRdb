package com.example.tempfirebaserdb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.lifecycleScope
import com.example.tempfirebaserdb.databinding.ActivityGoogleAuthBinding
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class GoogleAuth : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleAuthBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var credentialManager: CredentialManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGoogleAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        credentialManager = CredentialManager.create(this)

        binding.UserName.text = auth.currentUser?.displayName ?: "No User"

        binding.signIn.setOnClickListener {
            signInWithGoogle()
        }

        binding.signOut.setOnClickListener {
            Snackbar.make(binding.root,"Signing Out..", Snackbar.LENGTH_SHORT).show()
            auth.signOut()
            binding.UserName.text = auth.currentUser?.displayName ?: "No User"
        }

    }

    private fun signInWithGoogle() {
        Toast.makeText(this@GoogleAuth,"Starting Google Sign In...", Toast.LENGTH_SHORT).show()
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.default_web_id))
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .build()

        val credentialRequest = GetCredentialRequest.Builder()
            .setCredentialOptions(listOf(googleIdOption))
            .build()

        lifecycleScope.launch {
            try {
                val result = credentialManager.getCredential(
                    this@GoogleAuth, credentialRequest
                )
                val credential = result.credential

                Toast.makeText(this@GoogleAuth,"Credential Received...", Toast.LENGTH_SHORT).show()

                val googleIdTokenCred = credential as GoogleIdTokenCredential

                val token = googleIdTokenCred.idToken

                signInWithFirebase(token)

                Toast.makeText(this@GoogleAuth,"Token Received...", Toast.LENGTH_SHORT).show()
            } catch (e : Exception){
                Toast.makeText(this@GoogleAuth,e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signInWithFirebase(token: String) {
        val credentials = GoogleAuthProvider.getCredential(token,null)
        auth.signInWithCredential(credentials).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Snackbar.make(binding.root,"Signed In...", Toast.LENGTH_SHORT).show()
                val userName = auth.currentUser?.displayName
                binding.UserName.text = userName
            }
        }.addOnFailureListener{ exception ->
            Toast.makeText(this@GoogleAuth,exception.message, Toast.LENGTH_SHORT).show()
        }
    }
}