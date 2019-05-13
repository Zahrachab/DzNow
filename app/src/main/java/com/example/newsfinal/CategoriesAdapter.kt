package com.example.newsfinal


import android.support.v4.app.FragmentPagerAdapter
import android.content.Context;
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class CategoriesAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                val list = listOf(
                    News("Nous ne ferons pas marche arrière", "Des centaines de milliers de manifestants dans les rues d’Alger Nous ne ferons pas marche arrière Marée humaine, hier, à Alger.La rue a mis dans son collimateur une nouvelle cible : le chef d’état-major et vice-ministre de la Défense, Ahmed Gaïd Salah. Pour la première fois depuis le 22 février dernier, les manifestants exigent son départ.", "22 Avril 2019", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national")
                )
                return ListNews.newInstance(list)
            }
            1 -> {
                val list = listOf(
                    News("Nous ne ferons pas marche arrière", "Des centaines de milliers de manifestants dans les rues d’Alger Nous ne ferons pas marche arrière Marée humaine, hier, à Alger.La rue a mis dans son collimateur une nouvelle cible : le chef d’état-major et vice-ministre de la Défense, Ahmed Gaïd Salah. Pour la première fois depuis le 22 février dernier, les manifestants exigent son départ.", "22 Avril 2019", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national")
                )
                return ListNews.newInstance(list)
            }
            2 -> {
                val list = listOf(
                    News("Nous ne ferons pas marche arrière", "Des centaines de milliers de manifestants dans les rues d’Alger Nous ne ferons pas marche arrière Marée humaine, hier, à Alger.La rue a mis dans son collimateur une nouvelle cible : le chef d’état-major et vice-ministre de la Défense, Ahmed Gaïd Salah. Pour la première fois depuis le 22 février dernier, les manifestants exigent son départ.", "22 Avril 2019", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national")
                )
                return ListNews.newInstance(list)
            }
            else -> {
                val list = listOf(
                    News("Nous ne ferons pas marche arrière", "Des centaines de milliers de manifestants dans les rues d’Alger Nous ne ferons pas marche arrière Marée humaine, hier, à Alger.La rue a mis dans son collimateur une nouvelle cible : le chef d’état-major et vice-ministre de la Défense, Ahmed Gaïd Salah. Pour la première fois depuis le 22 février dernier, les manifestants exigent son départ.", "22 Avril 2019", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national"),
                    News("L’entrave à la liberté de circuler en…marche !", "Pour la quatrième fois consécutive, un impressionnant dispositif de sécurité, constitué d’escadrons antiémeutes de la Gendarmerie et de la Sûreté nationale, a été déployé dès jeudi à 13h pour empêcher les manifestants de rallier Alger","28 Octobre 2019 ", "imgNews1", "national")
                )
                return ListNews.newInstance(list)
            }
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}