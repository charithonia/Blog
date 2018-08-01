/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.service;

import com.sg.bessieblog.dto.Blog;
import com.sg.bessieblog.dto.Hashtag;

import java.util.List;
import java.util.ArrayList;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
public class HashtagServiceTest {
    
    @Inject
    private BlogService blogService;
    
    @Inject
    private HashtagService hashtagService;
    
    public HashtagServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertHashtag method, of class HashtagService.
     */
    @Test
    public void testInsertHashtag() {
    }

    /**
     * Test of getHashtagById method, of class HashtagService.
     */
    @Test
    public void testGetHashtagById() {
    }

    /**
     * Test of getAllHashtags method, of class HashtagService.
     */
    @Test
    public void testGetAllHashtags() {
    }

    /**
     * Test of removeHashtag method, of class HashtagService.
     */
    @Test
    public void testRemoveHashtag() {
    }

    /**
     * Test of updateHashtag method, of class HashtagService.
     */
    @Test
    public void testUpdateHashtag() {
    }

    /**
     * Test of getHashtagsFromText method, of class HashtagService.
     */
    @Test
    public void testParseHashtagsFromText() {
    }

    /**
     * Test of removeUnusedHashtags method, of class HashtagService.
     */
    @Test
    public void testRemoveUnusedHashtags() {
    }

    /**
     * Test of removeBlogFromHashtag method, of class HashtagService.
     */
    @Test
    public void testRemoveBlogFromHashtag() {
    }

    @Test
    @Transactional
    @Ignore
    public void testGetHashtagLinkedContent() {
	
	// IMPORTANT
	// This test is broken due to adding a more restricted hashtag search
	// and replace procedure. Instead of searching through all hashtags to
	// build links and replace, only known hashtags in the post are
	// acquired. This means that a stub blog without a presence and bridges
	// in the database can no longer be used in this test.
	
	// To fix the test, rewrite it as a blog entry in the test data.
	// Everything should then work as expected.
	
	// Altneratively, create here an entire, valid blog entry with the
	// desired hashtags, insert those hashtags separately (so the ids are
	// known), and then insert the blog.
	
	String blogBody = "This is some text. There are #hashtags in this #text. #hashtags";
	
	Hashtag hashtag_Hashtags = new Hashtag();
	Hashtag hashtag_Text = new Hashtag();
	
	Blog blog = new Blog();
	blog.setId(1);
	blog.setBody(blogBody);
	
	String result = hashtagService.getHashtagLinkedBody(blog);
	
	String expected = "This is some text. There are "
		+ "<a href=\"http://localhost:8080/BessieBlog/displayActiveBlogsByHashtag?id="
		+ hashtag_Hashtags.getId()
		+ "\">"
		+ "&#35;hashtags"
		+ "</a>"
		+ " in this "
		+ "<a href=\"http://localhost:8080/BessieBlog/displayActiveBlogsByHashtag?id="
		+ hashtag_Text.getId()
		+ "\">"
		+ "&#35;text"
		+ "</a>"
		+ ". "
		+ "<a href=\"http://localhost:8080/BessieBlog/displayActiveBlogsByHashtag?id="
		+ hashtag_Hashtags.getId()
		+ "\">"
		+ "&#35;hashtags"
		+ "</a>";
	
	System.out.println("RESULT: " + result);
	System.out.println("EXPECTED: " + expected);
	
	assertEquals(result, expected);
    }
    
    @Test
    @Transactional
    public void testGetHashtagsFromText() {
	
	// Sample text:
	// 4 hashtags (#hashtags, #text, #123, ###multihash)
	String text = "This is some text. There are #hashtags in this #text. #hashtags "
		+ "Here are some more: #123 #123.456 ###multihash";
	
	List<Hashtag> resultList = hashtagService.getHashtagsFromText(text);
	
	assertTrue(resultList.size() == 4);
	
	List<String> names = new ArrayList<>();
	
	for (Hashtag hashtag : resultList) {
	    String name = hashtag.getName();
	    names.add(name);
	}
	
	System.out.println(names);
	
	assertTrue(names.contains("#hashtags"));
	assertTrue(names.contains("#text"));
	assertTrue(names.contains("#123"));
	assertTrue(names.contains("###multihash"));
    }
    
    @Test
    @Transactional
    public void testGetHashtagsFromHtml() {
	
	// Sample text:
	// 2 hashtags (#hashtag, #text)
	String text = "<body>"
		+ "<p>#hashtag</p>"
		+ "<p>This is some text. #text</p>"
		+ "</body>";
	
	List<Hashtag> resultList = hashtagService.getHashtagsFromText(text);
	
	assertTrue(resultList.size() == 2);
    }
    
    @Test
    @Transactional
    public void findAndReplace() {
	
	// I just needed to know it works in our case.
	
	// Sample html
	String text = "<p>#hashtag</p>";
	
	String tag = "#hashtag";
	
	String result = text.replaceAll("#hashtag", "REPLACED");
	String expected = "<p>REPLACED</p>";
	
	assertEquals(expected, result);
    }
    
    @Test
    @Transactional
    public void testGetHashtagsByBlog() {
	Blog blog = blogService.getBlogById(5);
	
	List<Hashtag> resultList = hashtagService.getHashtagsByBlog(blog);
	
	assertTrue(resultList.size() == 2);
	
	String tag0 = resultList.get(0).getName();
	String tag1 = resultList.get(1).getName();
	
	assertEquals("#idontknowwhathashtagsare", tag0);
	assertEquals("#nachosrock", tag1);
    }
}
