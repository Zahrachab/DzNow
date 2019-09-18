package com.example.newsfinal.View


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.newsfinal.Adapters.CustomAdapter
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Model.Site
import com.example.newsfinal.Model.Thematique
import com.example.newsfinal.R
import com.example.newsfinal.Services.ArticlePreferencesService
import com.example.newsfinal.Services.ServiceVolley
import com.example.newsfinal.Singleton.ImeiUser
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_settings.*
import org.json.JSONArray
class SettingsFragement : Fragment() {

    private var listSites : MutableList<Site> ?= arrayListOf()
    private var mSitesAdapter: CustomAdapter ?=  null
    private var listThematiques : MutableList<Thematique> ?= arrayListOf()
    private var mThemesAdapter: CustomAdapter ?=  null

    private var preferencesService : ArticlePreferencesService ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferencesService = context?.let { ArticlePreferencesService(it) }

        //spécifier l'adapter pour la liste des sites
        l_Sites.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = CustomAdapter(listSites,  { partItem : com.example.newsfinal.Interface.Checkable -> partItemClicked(partItem as Site) })
            mSitesAdapter = adapter as CustomAdapter
        }

        //Récupérer la liste des sites
        getListSites()

        //spécifier l'adapter pour la liste des thématiques
        l_Thematiques.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = CustomAdapter(listThematiques,  { partItem : com.example.newsfinal.Interface.Checkable -> partItemClicked(partItem as Thematique) })
            mThemesAdapter = adapter as CustomAdapter
        }

        //Récupérer la liste des thématiques
        getListThematiques()

        //click listener pour le boutton valider
        validerChoix.setOnClickListener {
            updatePrefernces()
        }

    }

    //envoyer les mises à jour au service web
    private fun updatePrefernces() {
        listSites?.forEach {
            if((it.preference!= 0) and (!it.checked)) {
                preferencesService?.deleteSitePreference(it)
            }
            else if ((it.preference ==0 ) and (it.checked)) {
                preferencesService?.addSitePreference(it)
            }
        }

        listThematiques?.forEach {
            if((it.preference!= 0) and (!it.checked)) {
                preferencesService?.deleteThematiquePreference(it)
            }
            else if ((it.preference ==0 ) and (it.checked)) {
                preferencesService?.addThematiquePreference(it)
            }
        }
    }


    private fun partItemClicked(partItem : com.example.newsfinal.Interface.Checkable) {
    }


    // Récupérer la lite des sites ainsi que les préférences de cet utilisateur par rapport à ces derniers
    fun getListSites() {
        val service: ServiceInterface = ServiceVolley()
        val imei = context?.let { ImeiUser.getImei(it).toString() }
        var path = "siteGet.php?imei=$imei"
        var list = listOf<Site>()
        service.get(path) { response ->
            if(response != null && response != "error")
            {
                val gson = Gson()
                val jsonArray = JSONArray(response)
                if (jsonArray != null) {
                    val list = gson.fromJson(jsonArray.toString(), Array<Site>::class.java)
                    if (list!= null && list?.size != 0) {
                        listSites = list.toMutableList()
                        mSitesAdapter?.refreshAdapter(listSites as MutableList<Site>)
                    }
                }
            } else {
                Toast.makeText( context, "problème de connexon avec le serveur", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Récupérer la lite des thématiques ainsi que les préférences de cet utilisateur par rapport à ces derniers
    fun getListThematiques() {
        val service: ServiceInterface = ServiceVolley()
        val imei = context?.let { ImeiUser.getImei(it) }
        var path = "thematiqueGet.php?imei=$imei"
        var list = listOf<Site>()
        service.get(path) { response ->
            if(response != null && response != "error")
            {
                val gson = Gson()
                val jsonArray = JSONArray(response)
                if (jsonArray != null) {
                    val list = gson.fromJson(jsonArray.toString(), Array<Thematique>::class.java)
                    if (list!= null && list?.size != 0) {
                        listThematiques = list.toMutableList()
                        mThemesAdapter?.refreshAdapter(listThematiques as MutableList<Thematique>)
                    }
                }
            }
        }
    }



}
