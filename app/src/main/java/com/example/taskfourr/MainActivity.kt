package com.example.taskfourr

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskfourr.databinding.ActivityMainBinding
import com.example.taskfourr.model.Contact

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactList: ArrayList<Contact>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactList = ArrayList()
        contactList.add(Contact("Ahmed", "015547845874"))
        setUpRecycle(contactList)
        addButton()

    }

    private fun setUpRecycle(contactList: ArrayList<Contact>) {
        binding.recyclerView.adapter = Adapter(this, contactList)
    }

    fun updateArray(contact: Contact) {
        contactList.remove(contact)
        setUpRecycle(contactList)
    }

    fun updateUi(contact: Contact, index: Int) {
        binding.button.text = getString(R.string.update)
        binding.name.setText(contact.name)
        binding.number.setText(contact.number)
        binding.button.setOnClickListener {
            val updatedUser: Contact = Contact(
                binding.editTextTextPersonName.editText?.text.toString(),
                binding.editTextPhone.editText?.text.toString()
            )
            contactList[index] = updatedUser
            binding.button.text = getString(R.string.add)
            setUpRecycle(contactList)
            binding.editTextPhone.editText?.setText("")
            binding.editTextTextPersonName.editText?.setText("")
            binding.editTextTextPersonName.clearFocus()
            binding.editTextPhone.clearFocus()
            addButton()
        }


    }

    private fun addButton() {
        binding.button.setOnClickListener {
            if (binding.editTextTextPersonName.editText?.text?.isEmpty() == true || binding.editTextTextPersonName.editText?.text.toString() == " ") {
                Toast.makeText(this, "Pls Enter A Name", Toast.LENGTH_SHORT).show()
            } else if (binding.editTextPhone.editText?.text?.isEmpty() == true) {
                Toast.makeText(this, "Pls Enter A Phone Number", Toast.LENGTH_SHORT).show()
            } else {
                val newContact = Contact(
                    binding.editTextTextPersonName.editText?.text.toString(),
                    binding.editTextPhone.editText?.text.toString()
                )
                contactList.add(newContact)
                binding.editTextPhone.editText?.setText("")
                binding.editTextTextPersonName.editText?.setText("")
            }
            setUpRecycle(contactList)
            binding.editTextTextPersonName.clearFocus()
            binding.editTextPhone.clearFocus()

        }
    }
}