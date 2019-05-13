package com.example.newsfinal


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager


import kotlinx.android.synthetic.main.fragment_list_news.*

class ListNews : Fragment() {

    private var listOfNews = listOf(
        News("Nous ne ferons pas marche arrière", "Des centaines de milliers de manifestants dans les rues d’Alger Nous ne ferons pas marche arrière Marée humaine, hier, à Alger.La rue a mis dans son collimateur une nouvelle cible : le chef d’état-major et vice-ministre de la Défense, Ahmed Gaïd Salah. Pour la première fois depuis le 22 février dernier, les manifestants exigent son départ.", "22 Avril 2019", "imgNews1", "national"),
        News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
        News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
        News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
        News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national")
    )


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_list_news, container, false)


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            list_recycler_view.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ListNewsAdapter(listOfNews)
            }
        }

        companion object {
            fun newInstance(list: List<News>) :
                ListNews {
                val fragment = ListNews()
                fragment.listOfNews = list
                return fragment
            }
        }

}
