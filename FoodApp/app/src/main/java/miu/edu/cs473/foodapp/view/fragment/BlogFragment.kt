package miu.edu.cs473.foodapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import miu.edu.cs473.foodapp.adapter.BlogAdapter
import miu.edu.cs473.foodapp.databinding.FragmentBlogBinding
import miu.edu.cs473.foodapp.view.dialog.BlogDialog
import miu.edu.cs473.foodapp.listener.BaseFragmentListener
import miu.edu.cs473.foodapp.listener.BlogListener
import miu.edu.cs473.foodapp.listener.DialogListener
import miu.edu.cs473.foodapp.model.BlogModel
import miu.edu.cs473.foodapp.constant.ARG_PARAM1
import miu.edu.cs473.foodapp.constant.ARG_PARAM2
import miu.edu.cs473.foodapp.view.WebviewActivity

class BlogFragment : Fragment(), DialogListener, BlogListener, BaseFragmentListener {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var bindingBlogBinding: FragmentBlogBinding
    private lateinit var blogAdapter: BlogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingBlogBinding = FragmentBlogBinding.inflate(layoutInflater)
        initViews()
        return bindingBlogBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initViews() {
        if (!this::blogAdapter.isInitialized) {
            blogAdapter = BlogAdapter(this)
            blogAdapter.setData(BlogModel.createSampleBlogs())
        }
        with(bindingBlogBinding.rvBlog) {
            layoutManager = LinearLayoutManager(context)
            adapter = blogAdapter
        }
    }

    override fun onAdd() {
        val dialog = BlogDialog(this)
        dialog.show(parentFragmentManager, BlogFragment::class.java.name)
    }

    override fun addBlog(blogModel: BlogModel) {
        blogAdapter.addBlog(blogModel)
    }

    override fun viewBlog(blog: BlogModel) {
        val url = blog.url
        if (url.isNotEmpty()) {
            val intent = Intent(context, WebviewActivity::class.java)
            intent.putExtra("currentUrl", url)
            startActivity(intent)
        }
    }
}
