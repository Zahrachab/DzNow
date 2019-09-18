package com.example.newsfinal.Adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsfinal.Model.Article
import com.example.newsfinal.R


class ListNewsAdapter(private var list: List<Article>?, val clickListener: (Article) -> Unit) : RecyclerView.Adapter<ListNewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(list!![position], clickListener)
    }


    fun refreshAdapter(listNews: List<Article>) {
        list = listNews
        this.notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list!!.size



    inner class NewsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_item_news, parent, false)) {
        private var mTitleView: TextView? = null
        private var mDescView: TextView? = null
        private var mDateView: TextView? = null
        private var mcategorieView: TextView? = null
        private var mImageView: ImageView? = null



        init {
            mTitleView = itemView.findViewById(R.id.list_title)
            mDescView = itemView.findViewById(R.id.list_description)
            mDateView = itemView.findViewById(R.id.list_date)
            mcategorieView = itemView.findViewById(R.id.list_categorie)
            mImageView = itemView.findViewById(R.id.list_image) as ImageView
        }



        fun bind(news: Article, clickListener: (Article) -> Unit) {
            itemView.setOnClickListener { clickListener(news)}
            mTitleView?.text = news?.title
            mDescView?.text = news?.description
            mDateView?.text = news?.date
            mcategorieView?.text = news?.categorie
            if (news?.image == ""  || news?.image == null) {

                mImageView?.context?.let { Glide.with(it).load(R.drawable.al3).
                    apply(RequestOptions().override(120, 120)).into(mImageView!!) }
            } else {
                mImageView?.context?.let { Glide.with(it).load(news?.image).
                    apply(RequestOptions().override(120, 120)).into(mImageView!!) }

                // mImageView?.setImageResource(R.drawable.imgnew1)
            }
        }
    }




}
