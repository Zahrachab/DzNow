package com.example.newsfinal.Services

import android.content.Context
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Singleton.ImeiUser
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONObject

class GoogeInfosService (var context: Context) {

    private val firebaseAuth = FirebaseAuth.getInstance()

    public fun saveCompteInfos() {
        val prefs = SharedPreferncesHelper(context)
        if(prefs.googleUid == "" || firebaseAuth.currentUser?.uid != prefs.googleUid) {

            val path: String = "saveCompte.php"

            val params = JSONObject()
            params.put("uid", firebaseAuth.currentUser?.uid)
            params.put("name", firebaseAuth.currentUser?.displayName)
            params.put("imei", ImeiUser.getImei(context))

            val service: ServiceInterface = ServiceVolley()
            service.post(path, params) { response ->
                prefs.googleUid = firebaseAuth.currentUser?.uid.toString()
            }
        }
    }

    public fun saveSignetCompte(idArticle: String) {
        saveCompteInfos()
        val path: String = "archiverArticle.php"

        val params = JSONObject()
        params.put("uid", firebaseAuth.currentUser?.uid)
        params.put("idArticle", idArticle)

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
        }
    }


    public fun deleteSignetCompte(idArticle: String) {
        saveCompteInfos()
        val path: String = "deleteArchivedArticle.php"

        val params = JSONObject()
        params.put("uid", firebaseAuth.currentUser?.uid)
        params.put("idArticle", idArticle)

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
        }
    }
}