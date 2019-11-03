package buu.informatics.s59160143.wow


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import buu.informatics.s59160143.wow.database.PeopleDatabaseModel
import buu.informatics.s59160143.wow.databinding.FragmentInsertBinding
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import androidx.lifecycle.Observer
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding
    private lateinit var people: ViewModel
    var fileUri: Uri? = null
    var pictureUrl = ""
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_insert, container, false
        )

        people = ViewModelProviders.of(this).get(ViewModel::class.java)
        submit()
        pickImage()

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        signIn("59160143@go.buu.ac.th", "@icedexchon123")
        checkPermission()

        return binding.root
    }

    private fun submit() {
        binding.btnAdd.setOnClickListener {
            uploadImage()
            if (!binding.nameEditText.text.isEmpty() && !binding.ageEditText.text.isEmpty() && !binding.proportionEditText.text.isEmpty()
                && !binding.placeEditText.text.isEmpty() && !binding.priceEditText.text.isEmpty() && !binding.contactEditText.text.isEmpty()
            ) {
                Handler().postDelayed({
                    Log.e("imagelink", pictureUrl)
                    people.insert(
                        PeopleDatabaseModel(
                            binding.nameEditText.text.toString(),
                            binding.ageEditText.text.toString(),
                            binding.proportionEditText.text.toString(),
                            binding.placeEditText.text.toString(),
                            binding.priceEditText.text.toString(),
                            binding.contactEditText.text.toString(),
                            pictureUrl
                        )
                    )
                    Handler().postDelayed({
                        people.peopleAll.observe(this, Observer { t ->
                            t.forEach {
                                Log.i("pictureUpload2", "${it} ")
                            }
                        })
                    }, 1000)
                    Toast.makeText(getActivity(), "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_LONG).show()
                    view?.findNavController()
                        ?.navigate(InsertFragmentDirections.actionInsertFragmentToMainFragment())
                }, 2000)
            } else {
                Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun uploadImage() {
        if (fileUri != null) {
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(fileUri!!)
            uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->

                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                Log.i("task", task.toString())
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    pictureUrl = downloadUri.toString()
                    addUploadRecordToDb(downloadUri.toString())
                } else {
                }
            }?.addOnFailureListener {

            }
        } else {
            Toast.makeText(getActivity(), "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUploadRecordToDb(uri: String) {
        val db = FirebaseFirestore.getInstance()
        val data = HashMap<String, Any>()
        data["imageUrl"] = uri
        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(getActivity(), "เพิ่มเรียบร้อย", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(getActivity(), "${e}", Toast.LENGTH_LONG).show()
            }
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    private fun checkPermission() {
        if (getActivity()?.applicationContext?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            } != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            requestPermissions(permissions, PERMISSION_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        fileUri = data?.data

        if (resultCode == Activity.RESULT_OK && requestCode == AppConstants.PICK_PHOTO_REQUEST) {
            binding.imageButton.setImageURI(data?.data)

        }

    }

    private fun signIn(email: String, password: String) {
        Log.e("Firebase", "Auth........")

        mAuth.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.e("Firebase", "successful")
                    val firebaseUser = this.mAuth.currentUser!!
                } else {
                    Log.e("error", "${task.exception?.message}")
                }
            }
    }

    private fun pickImage() {
        binding.imageButton.setOnClickListener {
            //Intent to pick image
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)

            Log.i("nameImage", fileUri.toString())
        }
    }

}
