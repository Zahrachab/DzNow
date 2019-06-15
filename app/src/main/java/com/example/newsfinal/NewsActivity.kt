package com.example.newsfinal

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import android.support.v7.widget.Toolbar
import android.app.Activity
import android.content.res.Configuration
import java.util.*



class NewsActivity : AppCompatActivity() {
    var bottomNavigation: BottomNavigationView? = null
    var mTopToolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragement)

        mTopToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(mTopToolbar)
        getSupportActionBar()!!.setTitle(null)

        val fm = supportFragmentManager

       /* var fragment1 = fm.findFragmentById(R.id.fragment_changing)


        // ensures fragments already created will not be created
        if (fragment1 == null) {
            fragment1 = ListNews.newInstance(null)
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_changing, fragment1)
                .commit()
        }*/

        bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView3)


        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeId-> {
                    val fragment = NewsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.addArticleId-> {
                    val fragment = AddArticleFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.archiveId-> {
                    var gson = Gson()
                    val newsString =  """[{"title": "Nous ne ferons pas marche arrière", "description": "Des centaines de milliers de manifestants dans les rues d’Alger Nous ne ferons pas marche arrière Marée humaine, hier, à Alger.La rue a mis dans son collimateur une nouvelle cible : le chef d’état-major et vice-ministre de la Défense, Ahmed Gaïd Salah. Pour la première fois depuis le 22 février dernier, les manifestants exigent son départ.","date": "22 Avril 2019", "image": "imgNews1", "categorie": "national"},
                    {"title": "Nous ne ferons pas marche arrière", "description": "Des centaines de milliers de manifestants dans les rues d’Alger Nous ne ferons pas marche arrière Marée humaine, hier, à Alger.La rue a mis dans son collimateur une nouvelle cible : le chef d’état-major et vice-ministre de la Défense, Ahmed Gaïd Salah. Pour la première fois depuis le 22 février dernier, les manifestants exigent son départ.","date": "22 Avril 2019", "image": "imgNews1", "categorie": "national"},
                    {"title": "Les étudiants ne lâchent pas prise", "description": "Alger, 14 mai 2019. 10h30. Sous un soleil printanier, le cortège composé de plusieurs dizaines d’étudiants de différentes universités s’ébranle depuis la Fac centrale en direction de la Grande-Poste.", "date": "15 Mai 2019 ", "image": "imgNews1","categorie": "national"},
                    {"title": "Crise vénézuélienne : Juan Guaido a-t-il perdu la partie ?", "description": "Depuis qu’il s’est déclaré président par intérim le 23 janvier, Juan Guaido n’a eu de cesse d’appeler les militaires à rompre les rangs et à tourner le dos au chef de l’Etat socialiste. Il comptait pour cela sur le soutien de dizaines de milliers de personnes, qui descendaient dans la rue à chacun de ses appels à manifester.Mais depuis le soulèvement manqué du 30 avril et l’offensive du pouvoir contre les «traîtres» à l’origine de la tentative de rébellion, le vent semble avoir tourné dans la rue. Samedi, ils n’étaient qu’entre 1500 et 2000 sur la place Alfredo Sadel, dans un quartier de l’est de Caracas majoritairement acquis à l’opposition.","date": "13 Mai 2019", "image": "imgNews1", "categorie": "international"},
                    {"title": "Yémen : Les Houthis se retirent des ports d’Al Hodeïda", "description": "Les rebelles houthis du Yémen ont remis le contrôle de la sécurité des ports d’Al Hodeïda, sur la mer Rouge, à la «garde côtière», mais du travail demeure pour éliminer tout signe de présence militaire, a annoncé hier l’ONU.Le président du Comité de coordination des redéploiements, le général danois Michael Lollesgaard, a salué le transfert de «la sécurité des ports à la garde côtière», selon un communiqué officiel. «Il reste encore beaucoup de travail à faire pour éliminer les signes (de présence militaire), mais la coopération a été très bonne», a-t-il jugé. Si le redéploiement est total, il pourrait donner une impulsion aux efforts de l’ONU pour mettre un terme à la guerre et à la catastrophe humanitaire au Yémen. A signaler que la gestion des revenus des trois ports en question devait être discutée hier à Amman avec leurs représentants et le gouvernement du Yémen, a annoncé lundi l’ONU.","date": "15 Mai 2019", "image": "imgNews1", "categorie": "international"},
                    {"title": "JSMB : Les joueurs réclament leur argent", "description": "Les joueurs de la JSM Béjaïa ont réitéré leurs revendications liées à la régularisation de leur situation financière, puisqu’ils ont interpellé la direction afin d’accélérer les démarches et tenir ses promesses pour le versement de leurs salaires qui tarde à se matérialiser depuis la fin","date": "15 Mai 2019", "image": "imgNews1", "categorie": "Sport"},
                    {"title": "JS Kabylie : Réception en l’honneur des jeunes catégories", "description": "Lundi dans la soirée, l’hôtel Ittourar de Tizi Ouzou a abrité une réception en l’honneur des jeunes catégories de la JS Kabylie et leurs staffs techniques.Les U13 et U12, champions de wilaya de l’année 2018/2019 dans leurs catégories respectives, étaient les premiers à être  reçus et félicités pour leur bon parcours, et ce,  dans une ambiance bon enfant, marquée par la présence de la direction du club kabyle et de nombreux invités. Le président de la JSK, Cherif Mellal, a pris la parole pour féliciter les champions et les vainqueurs du play-off.","date": "22 Avril 2019", "image": "imgNews1", "categorie": "Sport"},
                    {"title": "Ligue 1 : CRB – JSS en nocturne", "description": "La Ligue de football professionnel (LFP) a communiqué sur son site officiel les horaires des rencontres de la 28e journée. Encore une fois, la LFP n’arrive pas à programmer tous les matches à la même heure pour éviter toutes les spéculations sur le résultat final des rencontres.", "date": "15 Mai 2019 ", "image": "imgNews1","categorie": "national"},
                    {"title": "Mouloudia d’Alger : Casoni débarque à Alger", "description": "L’entraîneur du Mouloudia pour la nouvelle saison, le Français Bernard Casoni, et comme annoncé dans ces mêmes colonnes, est depuis hier à Alger où il séjournera durant quatre jours.Bernard Casoni, dont la mission à la tête du club algérois ne débutera officiellement qu’au mois de juin prochain, puisque l’équipe est toujours sous la coupe de Mohamed Mekhazeni pour ce qui reste de la saison en cours, aura néanmoins à régler certains détails inhérents à son récent engagement avec le MCA, avec notamment la paperasse nécessaire pour l’établissement de son permis de travail, mais surtout arrêter avec exactitude le programme de préparation d’intersaison, qui débutera à la mi-juin.","date": "15 Mai 2019", "image": "imgNews1", "categorie": "Sport"}
                   ]"""
                    val list: List<News> = gson.fromJson(newsString , Array<News>::class.java).toList()
                    val fragment = ListNews.newInstance(list)
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_changing, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        bottomNavigation!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.langue, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.item1) {
            return true
        }
        if (id == R.id.item2) {
            setLocate("ar")
            recreate()
            return true
        }
        if (id == R.id.item3) {
            setLocate("ar")
            recreate()
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        setLocate(language)
    }
}