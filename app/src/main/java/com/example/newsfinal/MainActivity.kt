package com.example.newsfinal


class MainActivity : SingleFragmentActivity() {
    override fun createFragment() = ListNews.newInstance()
}