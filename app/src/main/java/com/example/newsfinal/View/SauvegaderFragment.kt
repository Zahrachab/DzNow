package com.example.newsfinal.View


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.newsfinal.Adapters.ListNewsAdapter
import com.example.newsfinal.Interface.ServiceInterface
import com.example.newsfinal.Model.Article
import com.example.newsfinal.R
import com.example.newsfinal.Room.NewsDB
import com.example.newsfinal.Room.ArticleDao
import com.example.newsfinal.Services.ServiceVolley
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_archive.*
import org.json.JSONArray


/**
 * Fragement responsable de l'affichage des articles archivés
 */
class SauvegaderFragment : Fragment() {
    //liste des articles archivés à afficher
    private var listOfNews : List<Article>? = listOf()
    private var mAdapter: ListNewsAdapter?= null
    //Instance de la base de données locale
    private var db: NewsDB? = null
    //DAO de l'article
    private var dao: ArticleDao? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        //initialiser db et DAO
        db = context?.let { NewsDB.getInstance(it) }
        dao = db?.articleDao()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_archive, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        // affecter l'adapter au recylerView
        list_recycler_view_archived.apply {
            layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
            adapter = ListNewsAdapter(listOfNews, { partItem : Article  -> partItemClicked(partItem) })
            mAdapter = adapter as ListNewsAdapter
        }

        //Récupérer les données depuis le serveur s'il ya une connexion et faire la synchro sinon à partir de SqlLite e
        if (checkNetwork()) {
            getListNewsFromServer()
        } else {
            getListNewsFromLocal()
        }
    }

    /**
     * Vérifier la connectivité
     */
    private fun checkNetwork() : Boolean{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }


    /**
     *  Synchroniser les articles archivés dans le sqlite avec ceux enregistrés dans le backend
     */

    private fun synchroniseArchivedArticles(list: MutableList<Article>) {
        list.forEach {
            if (it.id?.let { it1 -> dao?.getNewsById(it1) } == null) {
                dao?.saveNews(it)
            }
        }
    }

    /**
     * Récupérer la liste des articles archivés depuis la base de données locale
     */
    private fun getListNewsFromLocal() {
        var list: List<Article> = dao?.getNews()!!
        if (list != null && list?.size != 0) {
            listOfNews = list.toMutableList()
            mAdapter?.refreshAdapter(listOfNews as MutableList<Article>)
        }

    }

    /**
     * Récupérer la liste des données depuis le service web et effectuer la synchronisation
     */
    fun getListNewsFromServer() {
        val firebase = FirebaseAuth.getInstance()
        val service: ServiceInterface = ServiceVolley()
        var path = "getArchivedArticles.php?uid="+ firebase.currentUser?.uid

        var list = listOf<Article>()
        service.get(path) { response ->
            if(response != null && response != "error" && response!= "")
            {
                val gson = Gson()
                val jsonArray = JSONArray(response)
                if (jsonArray != null) {
                    val list = gson.fromJson(jsonArray.toString(), Array<Article>::class.java)
                    if (list!= null && list?.size != 0) {
                        listOfNews= list.toMutableList()
                        mAdapter?.refreshAdapter(listOfNews as MutableList<Article>)

                        //Effectuer la synchronisation
                        synchroniseArchivedArticles(listOfNews as MutableList<Article>)
                    }
                }
            }
        }
    }

    /**
     * Méthde invoquée lors du clique sur un article du recyclerView
     */
    private fun partItemClicked(partItem : Article) {
        Toast.makeText(this.context, "Titre: ${partItem.title}", Toast.LENGTH_LONG).show()

        // Launch second activity, pass part ID as string parameter
        val showDetailActivityIntent = Intent(this.context, NewsDetail::class.java)
        NewsDetail.article = partItem
        startActivity(showDetailActivityIntent)
    }

}
