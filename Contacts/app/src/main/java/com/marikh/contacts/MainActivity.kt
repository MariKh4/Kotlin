package com.marikh.contacts

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.*


class MainActivity : AppCompatActivity() {

    private var REQUEST_CODE_PERMISSION_READ_CONTACTS : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionStatus =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            val contactsList: RecyclerView = findViewById(R.id.contactList)
            val fetchedContacts : List<Contact> = fetchAllContacts()
            val contactsAdapter: ContactsAdapter = ContactsAdapter(this, fetchedContacts)

            contactsList.layoutManager = LinearLayoutManager(this)
            contactsList.adapter = contactsAdapter
        } else {
            ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.READ_CONTACTS),
                    REQUEST_CODE_PERMISSION_READ_CONTACTS
            )
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String?>,
            grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_PERMISSION_READ_CONTACTS -> {
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // allow
                    val contactsList: RecyclerView = findViewById(R.id.contactList)
                    val fetchedContacts: List<Contact> = fetchAllContacts()
                    val contactsAdapter: ContactsAdapter = ContactsAdapter(this, fetchedContacts)

                    contactsList.layoutManager = LinearLayoutManager(this)
                    contactsList.adapter = contactsAdapter

                    Toast.makeText(applicationContext, "Найдено " + fetchedContacts.size
                            + " контактов", Toast.LENGTH_LONG).show()
                } else {
                    // deny
                    Toast.makeText(applicationContext, "No permission for read contacts / Нет разрешения для чтения контактов",
                            Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    fun openContactForCall(view: View) {
        var uri = (Uri.parse("tel:" + contactPhone.text.toString()))
        startActivity(Intent (Intent.ACTION_DIAL, uri))
    }

    fun openContactForSMS(view: View) {
        var uri = Uri.parse("smsto:" + contactName.text.toString())
        startActivity(Intent(Intent.ACTION_SENDTO, uri))
    }
}
