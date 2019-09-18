package com.example.newsfinal.Services

import android.content.Context
import android.widget.Toast
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Model.Site
import com.example.newsfinal.Model.Thematique
import com.example.newsfinal.Singleton.ImeiUser
import org.json.JSONObject

class ArticlePreferencesService (var context: Context) {


    /**
     * Ajouter un site au préférences de l'utilisateur
     */
    fun addSitePreference(site: Site) {
        val path: String = "preferenceSitePost.php"

        val params = JSONObject()
        params.put("idSite", site.id)
        params.put("imei", context?.let { ImeiUser.getImei(it) })

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
            Toast.makeText(context, "Modification avec succès", Toast.LENGTH_SHORT).show()
            site.preference = 1

        }
    }


    /**
     * supprimer un site des préférences de l'utilisateur
     */
    fun deleteSitePreference(site: Site) {
        val path: String = "preferenceSiteDelete.php"

        val params = JSONObject()
        params.put("idSite",site.id)
        params.put("imei", context?.let { ImeiUser.getImei(it) })

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
            Toast.makeText(context, "Modification avec succès", Toast.LENGTH_SHORT).show()
            site.preference = 0
        }
    }



    /**
     * Ajouter une thématique  au préférences de l'utilisateur
     */
    fun addThematiquePreference(theme: Thematique) {
        val path: String = "preferenceThemePost.php"

        val params = JSONObject()
        params.put("idThematique", theme.id)
        params.put("imei", context?.let { ImeiUser.getImei(it) })

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
            Toast.makeText(context, "Modification avec succès", Toast.LENGTH_SHORT).show()
            theme.preference = 1
        }
    }


    /**
     * Supprimer une thématique des préférences de l'utilisateur
     */
    fun deleteThematiquePreference(theme: Thematique) {
        val path: String = "preferenceThemeDelete.php"

        val params = JSONObject()
        params.put("idThematique", theme.id)
        params.put("imei", context?.let { ImeiUser.getImei(it) })

        val service: ServiceInterface = ServiceVolley()
        service.post(path, params) { response ->
            Toast.makeText(context, "Modification avec succès", Toast.LENGTH_SHORT).show()
            theme.preference = 0
        }
    }
}