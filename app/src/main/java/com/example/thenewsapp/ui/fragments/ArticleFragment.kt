package com.example.thenewsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.thenewsapp.R
import com.example.thenewsapp.databinding.FragmentArticleBinding
import com.example.thenewsapp.ui.NewsActivity
import com.example.thenewsapp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var newsViewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    lateinit var binding: FragmentArticleBinding


    package com.example.thenewsapp.ui.fragments

    import android.annotation.SuppressLint
    import android.os.Bundle
    import android.view.View
    import android.webkit.WebSettings
    import android.webkit.WebView
    import android.webkit.WebViewClient
    import androidx.fragment.app.Fragment
    import androidx.navigation.fragment.navArgs
    import com.example.thenewsapp.R
    import com.example.thenewsapp.databinding.FragmentArticleBinding
    import com.example.thenewsapp.ui.NewsActivity
    import com.example.thenewsapp.ui.NewsViewModel
    import com.google.android.material.snackbar.Snackbar

    class ArticleFragment : Fragment(R.layout.fragment_article) {

        lateinit var newsViewModel: NewsViewModel
        val args: ArticleFragmentArgs by navArgs()
        lateinit var binding: FragmentArticleBinding

        @SuppressLint("SetJavaScriptEnabled")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Initialize binding and ViewModel
            binding = FragmentArticleBinding.bind(view)
            newsViewModel = (activity as NewsActivity).newsViewModel
            val article = args.article

            // Configure WebView securely
            binding.webView.apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        // Allow only trusted HTTPS URLs (newsapi.org domain)
                        return if (url != null && url.startsWith("https://newsapi.org")) {
                            false // allow loading
                        } else {
                            // Block untrusted or unsafe URLs
                            Snackbar.make(requireView(), "Blocked unsafe URL", Snackbar.LENGTH_SHORT).show()
                            true // cancel loading
                        }
                    }
                }

                settings.apply {
                    javaScriptEnabled = false          // Disable JavaScript to prevent XSS attacks
                    allowFileAccess = false            // Disable access to local files
                    domStorageEnabled = false          // Disable DOM storage (local data)
                    mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW // Enforce HTTPS only
                }

                // Load the article's URL if available
                article.url?.let {
                    loadUrl(it)
                }
            }

            // Handle "Add to Favorites" button click
            binding.fab.setOnClickListener {
                newsViewModel.addToFavourites(article)
                Snackbar.make(view, "Added to Favourites", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}