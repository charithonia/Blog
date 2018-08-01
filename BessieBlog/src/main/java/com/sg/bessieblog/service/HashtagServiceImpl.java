/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.dao.BlogHashtagDao;
import com.sg.bessieblog.dao.HashtagDao;
import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.BlogHashtag;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.viewmodel.BlogViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author matt
 */
public class HashtagServiceImpl implements HashtagService {

    private HashtagDao hashtagDao;
    private BlogHashtagDao blogHtgDao;
    private BlogService blogService;
    private CategoryService categoryService;
    private StaticPageService staticPageService;
    
    public void setHashtagDao(HashtagDao hashtagDao) {
        this.hashtagDao = hashtagDao;
    }

    public void setBlogHashtagDao(BlogHashtagDao bhtDao) {
        this.blogHtgDao = bhtDao;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    public void setStaticPageService(StaticPageService staticPageService) {
        this.staticPageService = staticPageService;
    }

    @Override
    public Hashtag insertHashtag(Hashtag hashtag) {
        return hashtagDao.insertHashtag(hashtag);
    }

    @Override
    public Hashtag getHashtagById(int hashtagId) {
        return hashtagDao.getHashtagById(hashtagId);
    }
    
    @Override
    public Hashtag getHashtagByName(String name) {
	return hashtagDao.getHashtagByName(name);
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        return hashtagDao.getAllHashtags();
    }
    
    @Override
    public List<Hashtag> getHashtagsByBlog(Blog blog) {
	return hashtagDao.getHashtagsByBlog(blog);
    }

    @Override
    public void removeHashtag(Hashtag hashtag) {
        hashtagDao.removeHashtag(hashtag);
    }
    
    @Override
    public boolean hashtagExists(Hashtag hashtag) {
	return hashtagDao.hashtagExists(hashtag);
    }

    @Override
    public void removeUnusedHashtags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeBlogFromHashtag(Hashtag hashtag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHashtagLinkedBody(Blog blog) {
        String text = blog.getBody();

        List<Hashtag> hashtags = getHashtagsByBlog(blog);
	
        for (Hashtag hashtag : hashtags) {

            // Build a sterilized hashtag (just in case)
            String name = hashtag.getName().substring(1);
            name = "&#35;" + name;

            // Build a linked Hashtag
            String linkedHashtag = "<a href=\""
                    + "http://localhost:8080/BessieBlog/displayActiveBlogsByHashtag?id="
                    + hashtag.getId()
                    + "\">"
                    + name
                    + "</a>";

            // Replace in text
            String replacing = hashtag.getName();
            text = text.replaceAll(replacing, linkedHashtag);
        }

        return text;
    }

    @Override
    public List<Hashtag> getHashtagsFromText(String text) {
        List<String> foundTags = new ArrayList<>();
        List<Hashtag> hashtagList = new ArrayList<>();

        // Match hashtags in text
        Pattern hashtagPattern = Pattern.compile(
                "(?:\\s|(?<=\\>)|\\A)[##]+([A-Za-z0-9]+)");
        Matcher m = hashtagPattern.matcher(text);
        while (m.find()) {
            String tag = m.group(0);

            tag = tag.trim();

            if (!foundTags.contains(tag)) {
                foundTags.add(tag);
            }
        }

        // Convert found tags to Hashtag objects
        for (String tag : foundTags) {
            Hashtag hashtag = new Hashtag();

            hashtag.setName(tag);

            hashtagList.add(hashtag);
        }

        return hashtagList;
    }
    
    @Override
    public void processNewContentHashtags(Blog blog) {

        // When a blog is inserted, valid hashtags within it must be added to
        // the hashtag database and bridges made between that post and its
        // contained hashtags.

        String body = blog.getBody();
        List<Hashtag> hashtags = getHashtagsFromText(body);

        for (Hashtag hashtag : hashtags) {
	    Hashtag bridgeHashtag;
	    
	    // Add hashtag to database
	    if (!hashtagExists(hashtag)) {
		insertHashtag(hashtag);
		bridgeHashtag = hashtag;
	    }
	    else {
		
		// Already in database; retrieve and assign it for bridging
		bridgeHashtag = getHashtagByName(hashtag.getName());
	    }
	    
            // deprecated
//	    try {
//		insertHashtag(hashtag);
//		bridgeHashtag = hashtag;
//	    }
//	    catch (DataAccessException ex) {
//		
//		// Already in database; retrieve and assign it for bridging
//		bridgeHashtag = getHashtagByName(hashtag.getName());
//	    }

            // Add blog/hashtag bridge to database
            BlogHashtag bh = new BlogHashtag();
            bh.setBlog(blog);
            bh.setHashtag(bridgeHashtag);

	    try {
		blogHtgDao.insertBlogHashtag(bh);
	    }
	    catch (DataAccessException ex) {}
        }
    }

    @Override
    public void processDeletedContentHashtags(Blog blog) {

        // When a blog is deleted, delete its blog/hashtag bridges
	List<BlogHashtag> blogHashtags = blogHtgDao.getBlogHashtagsByBlog(blog);
	for (BlogHashtag bh : blogHashtags) {
	    blogHtgDao.removeBlogHashtag(bh);
	}
    }

    @Override
    public HomeViewModel getHomeViewModel(int hashId) {
        HomeViewModel hvm = new HomeViewModel();
        
        Hashtag h = getHashtagById(hashId);
        List<Blog> blogList = blogService.getBlogsByHashtag(h);

        List<BlogViewModel> bvmList = new ArrayList();

        for (Blog blog : blogList) {
            bvmList.add(blogService.getBlogViewModel(blog));
        }

        hvm.setBlogs(bvmList);
        hvm.setCategoryList(categoryService.getAllCategories());
        hvm.setStaticPages(staticPageService.getAllActiveStaticPages());
        return hvm;
    }
}
