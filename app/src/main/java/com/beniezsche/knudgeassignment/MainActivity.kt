
package com.beniezsche.knudgeassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beniezsche.knudgeassignment.adapter.ArticleAdapter
import com.beniezsche.knudgeassignment.model.Article
import com.beniezsche.knudgeassignment.viewmodel.ArticleViewModel
import com.google.android.material.navigation.NavigationView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val articleViewModel: ArticleViewModel by viewModels()
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var catTitle: TextView
    private lateinit var articleList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        articleList = findViewById(R.id.articleList)
        articleList.layoutManager = LinearLayoutManager(this)
        articleAdapter = ArticleAdapter()
        articleList.adapter = articleAdapter

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        drawerLayout = findViewById(R.id.drawer_layout)

        val hamburger: ImageView = findViewById(R.id.menu)

        hamburger.setOnClickListener {
            drawerLayout.open()
        }

        catTitle = findViewById(R.id.category)
        getArticlesOfCategory("general")
    }

    private fun getArticlesOfCategory(category: String) {

        articleViewModel.getArticles(category).observe(this,  {

            if (it != null) {
                articleAdapter.articles = it.articles as ArrayList<Article>
                articleAdapter.notifyDataSetChanged()
                articleList.scrollToPosition(0)
            }

        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val cat = item.title.toString()
        val catSmall = cat.toLowerCase(Locale.ROOT)
        getArticlesOfCategory(catSmall)
        catTitle.text = "$cat News"
        drawerLayout.close()

        return true
    }
}