/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.commandmodel.BlogFormCommandModel;
import com.sg.bessieblog.dao.BlogDao;
import com.sg.bessieblog.dao.BlogHashtagDao;
import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;
import com.sg.bessieblog.viewmodel.AdminBlogMgmtViewModel;
import com.sg.bessieblog.viewmodel.BlogViewModel;
import com.sg.bessieblog.viewmodel.CreateBlogViewModel;
import com.sg.bessieblog.viewmodel.HomeViewModel;
import com.sg.bessieblog.viewmodel.UserBlogMgmtViewModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matt
 */
public class BlogServiceImpl implements BlogService {
    private BlogDao blogDao;
    private CategoryService categoryService;
    private BlogHashtagDao bhtDao;
    private HashtagService hashtagService;
    private UserService userService;
    private StaticPageService spService;
    

    public void setBlogDao(BlogDao blogDao){
        this.blogDao = blogDao;
    }
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    public void setBlogHashtagDao(BlogHashtagDao bhtDao){
        this.bhtDao = bhtDao;
    }
    public void setHashtagService(HashtagService hashtagService){
        this.hashtagService = hashtagService;
    }
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    public void setStaticPageService(StaticPageService spService){
        this.spService = spService;
    }
    
    
    @Override
    public Blog insertBlog(BlogFormCommandModel bfcm) {
        Blog blog = new Blog();
        Category cat = new Category();
        User user = new User();
        user.setId(1);
        cat.setId(bfcm.getCategoryId());
        blog.setBody(bfcm.getBody());
        blog.setCategory(cat);
	
	if (bfcm.getCreationDate() == null) {
	    blog.setCreationDate(null);
	}
        blog.setCreationDate(bfcm.getCreationDate());
        blog.setExpirationDate(bfcm.getExperiationDate());
        blog.setIsApproved(bfcm.isIsApproved());
        blog.setPublicationDate(bfcm.getPublicationDate());
        blog.setTitle(bfcm.getTitle());
        blog.setUser(user);
	
        blogDao.insertBlog(blog);
	
	// Hashtags
	hashtagService.processNewContentHashtags(blog);
	
	return blog;
    }

    @Override
    public Blog getBlogById(int blogId) {
       return blogDao.getBlogById(blogId);
    }

    @Override
    public List<Blog> getAllBlogs() {
       return blogDao.getAllBlogs();
    }

    @Override
    public void removeBlog(Blog blog) {
         hashtagService.processDeletedContentHashtags(blog);
       blogDao.removeBlog(blog);
       
      
    }

    @Override
    public void updateBlog(BlogFormCommandModel bfcm) {
        Blog blog = new Blog();
        Category cat = new Category();
        User user = new User();
        blog.setId(bfcm.getBlogId());
        user.setId(bfcm.getUserId());
        cat.setId(bfcm.getCategoryId());
        blog.setBody(bfcm.getBody());
        blog.setCategory(cat);
        blog.setCreationDate(bfcm.getCreationDate());
        blog.setExpirationDate(bfcm.getExperiationDate());
        blog.setIsApproved(bfcm.isIsApproved());
        blog.setPublicationDate(bfcm.getPublicationDate());
        blog.setTitle(bfcm.getTitle());
        blog.setUser(user);
        blogDao.updateBlog(blog);
    }

    @Override
    public List<Blog> getBlogsByStatus(String status) {
       return blogDao.getBlogsByStatus(status);
    }

    @Override
    public List<Blog> getBlogsByDate(LocalDateTime date) {
       return blogDao.getBlogsByDate(date);
    }
    


    @Override
    public List<Blog> getBlogsByUserId(User user) {
       return blogDao.getBlogsByUserId(user);
    }

    @Override
    public List<Blog> getBlogsByHashtag(Hashtag hashtag) {
        return blogDao.getBlogsByHashtag(hashtag);
    }

    @Override
    public List<Blog> getAllActiveBlogs() {
       return blogDao.getAllActiveBlogs();
    }

    @Override
    public List<Blog> getAllPendingBlogs() {
       return blogDao.getAllPendingBlogs();
    }
    @Override
    public List<Blog> getAllExpiredBlogs(){
        return blogDao.getAllExpiredBlogs();
    }

    @Override
    public BlogFormCommandModel getBlogFormCommandModel(Blog blog) {
       BlogFormCommandModel bfcm = new BlogFormCommandModel();
       bfcm.setBlogId(blog.getId());
       bfcm.setBody(blog.getBody());
       bfcm.setCategoryId(blog.getCatagory().getId());
       bfcm.setCategoryList(categoryService.getAllCategories());
       bfcm.setCreationDate(blog.getCreationDate());
       bfcm.setExperiationDate(blog.getExpirationDate());
       bfcm.setIsApproved(blog.getIsApproved());
       bfcm.setPublicationDate(blog.getPublicationDate());
       bfcm.setTitle(blog.getTitle());
       bfcm.setUserId(blog.getUser().getId());
       return bfcm;
    }

    @Override
    public HomeViewModel getHomeViewModel() {
        HomeViewModel hvm = new HomeViewModel();
        List<Blog> blogList = blogDao.getAllActiveBlogs();
        List<Blog> eagerBlogList = new ArrayList();
        List<BlogViewModel> bvmList = new ArrayList();
        for(Blog blog : blogList){
           bvmList.add(getBlogViewModel(blog));
           eagerBlogList.add(eagerlyGetBlogById(blog.getId()));
        };
        
        
        hvm.setBlogs(bvmList);
        hvm.setCategoryList(categoryService.getAllCategories());
        hvm.setStaticPages(spService.getAllActiveStaticPages());
        return hvm;
    }
    
    @Override
    public HomeViewModel getHomeViewModel(List<Blog> blogs) {
        HomeViewModel hvm = new HomeViewModel();
        List<Blog> eagerBlogList = new ArrayList();
        List<BlogViewModel> bvmList = new ArrayList();
        for(Blog blog : blogs){
           bvmList.add(getBlogViewModel(eagerlyGetBlogById(blog.getId())));
        };
        
        
        hvm.setBlogs(bvmList);
        hvm.setCategoryList(categoryService.getAllCategories());
        hvm.setStaticPages(spService.getAllActiveStaticPages());
        return hvm;
    }
    
    
    @Override
    public BlogViewModel getBlogViewModel(Blog blog) {
        BlogViewModel bvm = new BlogViewModel();
        Blog b = eagerlyGetBlogById(blog.getId());
        bvm.setBlogId(b.getId());
	
	// Apply hashtags to body
	String body = hashtagService.getHashtagLinkedBody(b);
        bvm.setBody(body);
	
        bvm.setCategory(b.getCatagory());
        bvm.setCategoryList(categoryService.getAllCategories());
        bvm.setCreationDate(b.getCreationDate());
        bvm.setExpirationDate(b.getExpirationDate());
        bvm.setIsApproved(b.getIsApproved());
        bvm.setPublicationDate(b.getPublicationDate());
        bvm.setTitle(b.getTitle());
        bvm.setUser(b.getUser());
        bvm.setSpList(spService.getAllActiveStaticPages());
	
        return bvm;
    }

    @Override
    public Blog eagerlyGetBlogById(int blogId) {
        Blog blog = blogDao.getBlogById(blogId);
        blog.setCategory(categoryService.getCategoryById(blog.getCatagory().getId()));
        blog.setUser(userService.getUserById(blog.getUser().getId()));
        return blog;
    }

    @Override
    public void removeHashtagFromBlog(Blog blog) {
        bhtDao.removeHashtagFromBlog(blog);
    }

    @Override
    public List<Blog> getBlogsByCategoryId(int categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return blogDao.getBlogsByCategory(category);
    }

    @Override
    public CreateBlogViewModel getCreateBlogViewModel() {
        CreateBlogViewModel cbvm = new CreateBlogViewModel();
        cbvm.setCategoryList(categoryService.getAllCategories());
        cbvm.setStaticPageList(spService.getAllActiveStaticPages());
        return cbvm;
    }
    
    @Override
    public UserBlogMgmtViewModel getUserBlogMgmtViewModelByUser(User user){
        UserBlogMgmtViewModel ubmvm = new UserBlogMgmtViewModel();
        ubmvm.setExpiredBlogsByUser(getAllExpiredBlogPostsByUser(user));
        ubmvm.setPendingBlogsByUser(getAllPendingBlogPostsByUser(user));
        ubmvm.setPublishedBlogsByUser(getAllActiveBloggPostsByUser(user));
        return ubmvm;
    }
    
    @Override
    public AdminBlogMgmtViewModel getAdminBlogMgmtViewModel(){
        AdminBlogMgmtViewModel abmvm = new AdminBlogMgmtViewModel();
        abmvm.setExpiredBlogs(getAllExpiredBlogs());
        abmvm.setPendingBlogs(getAllPendingBlogs());
        abmvm.setPublishedBlogs(getAllActiveBlogs());
        return abmvm;
    }
    
    @Override
    public List<Blog> getAllPendingBlogPostsByUser(User user){
       return blogDao.getAllPendingBlogsByUserId(user);
    }
    
    @Override
    public List<Blog> getAllActiveBloggPostsByUser(User user){
        return blogDao.getAllActiveBlogsByUserId(user);
    }
    
    @Override
    public List<Blog> getAllExpiredBlogPostsByUser(User user){
        return blogDao.getAllExpiredBlogsByUserId(user);
    }


    
}
