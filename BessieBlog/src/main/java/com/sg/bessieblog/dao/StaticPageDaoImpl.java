/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sg.bessieblog.dto.Category;
import com.sg.bessieblog.dto.Hashtag;
import com.sg.bessieblog.dto.StaticPage;
import com.sg.bessieblog.dto.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author main
 */
public class StaticPageDaoImpl implements StaticPageDao {

    // sql
    private final String INSERT_STATIC_PAGE
            = "INSERT INTO StaticPage (Title, CreationDate, ExpirationDate, PublicationDate, UserId, NavOrder, IsApproved, Body, Slug) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final String GET_STATIC_PAGE_BY_ID
            = "SELECT * FROM StaticPage WHERE id = ?";

    private final String GET_ALL_STATIC_PAGES
            = "SELECT * FROM StaticPage";

    private final String REMOVE_STATIC_PAGE
            = "DELETE FROM StaticPage WHERE id = ?";

    private final String UPDATE_STATIC_PAGE
            = "UPDATE StaticPage SET Title = ?, CreationDate = ?, ExpirationDate = ?, PublicationDate = ?, UserId = ?, NavOrder = ?, IsApproved = ?, Body = ?, Slug = ? WHERE Id = ?";

    //are we not offering up a search for static pages?
//    private final String GET_STATIC_PAGES_BY_CREATION_DATE
//            = "Select * from StaticPage where CreationDate = ?";
//    private final String GET_STATIC_PAGES_BY_PUBLICATION_DATE
//            = "Select * from StaticPage where PublicationDate = ?";
//    private final String GET_STATIC_PAGES_BY_EXPIRATION_DATE
//            = "Select * from StaticPage where ExpirationDate = ?;";
    private final String GET_STATIC_PAGES_BY_USER_ID
            = "Select * from StaticPage where UserId=?";

    private final String GET_ALL_ACTIVE_STATIC_PAGES
            = "SELECT * FROM BessieBlog.StaticPage \n"
            + "where (NavOrder > 0 and IsApproved=1 and PublicationDate <= now() and (ExpirationDate is null or ExpirationDate > now())) \n"
            + "ORDER BY NavOrder;";

    private final String GET_ALL_EXPIRED_STATIC_PAGES
            = "SELECT * FROM StaticPage where ExpirationDate <= now()";

    //this one just subtracts away the previous two tables 
    //from the full set of all static pages
    private final String GET_ALL_PENDING_STATIC_PAGES
            = "SELECT * FROM StaticPage\n"
            + "WHERE (Id) not in \n"
            + "(SELECT Id FROM StaticPage where (NavOrder > 0 and IsApproved=1 and PublicationDate <= now() and (ExpirationDate is null or ExpirationDate > now()) or ExpirationDate <= now())\n"
            + ");";

    private final String GET_ALL_ACTIVE_STATIC_PAGES_BY_USER_ID
            = "SELECT * FROM BessieBlog.StaticPage where \n"
            + "(NavOrder > 0 and IsApproved=1 and PublicationDate <= now() and \n"
            + "(ExpirationDate is null or ExpirationDate > now()) and UserId=?) \n"
            + "ORDER BY NavOrder;";

    private final String GET_ALL_EXPIRED_STATIC_PAGES_BY_USER_ID
            = "SELECT * FROM StaticPage where ExpirationDate <= now() and UserId=?;";
    
    private final String GET_ALL_PENDING_STATIC_PAGES_BY_USER_ID
            = "SELECT * FROM StaticPage\n"
            + "WHERE UserId = ? and (Id) not in \n"
            + "(SELECT Id FROM StaticPage where \n"
            + "(NavOrder > 0 and IsApproved=1 and PublicationDate <= now() \n"
            + "and (ExpirationDate is null or ExpirationDate > now()) or ExpirationDate <= now())\n"
            + ");";


    //this tact - using separate sql queries to get different slices of data
    //rather than looping through a complete set of data in service methods - 
    //is driven by a desire to be better familiar with sql.
    //this code is less re-usable, however.
    // jdbc
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public StaticPage insertStaticPage(StaticPage page) {
//        Timestamp creationDate = Timestamp.valueOf(page.getCreationDate());
//        Timestamp expirationDate = Timestamp.valueOf(page.getExpirationDate());
//        Timestamp publicationDate = Timestamp.valueOf(page.getPublicationDate());

//should be in service
        Timestamp timestampCreationDate;
        if (page.getCreationDate() != null) {
            timestampCreationDate = Timestamp.valueOf(page.getCreationDate());
        } else {
            timestampCreationDate = Timestamp.valueOf(LocalDateTime.now());
        }

        Timestamp timestampPublicationDate;
        if (page.getPublicationDate() != null) {
            timestampPublicationDate = Timestamp.valueOf(page.getPublicationDate());
        } else {
            timestampPublicationDate = null;
        }

        Timestamp timestampExpirationDate;
        if (page.getExpirationDate() != null) {
            timestampExpirationDate = Timestamp.valueOf(page.getExpirationDate());
        } else {
            timestampExpirationDate = null;
        }

        jdbcTemplate.update(INSERT_STATIC_PAGE,
                page.getTitle(),
                timestampCreationDate,
                timestampExpirationDate,
                timestampPublicationDate,
                page.getUser().getId(),
                page.getNavOrder(),
                page.getIsApproved(),
                page.getBody(),
                page.getSlug()
        );

        int pageId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        page.setId(pageId);

        return page;
    }

    @Override
    public StaticPage getStaticPageById(int id) {
        try {
            StaticPage staticPage
                    = jdbcTemplate.queryForObject(GET_STATIC_PAGE_BY_ID, new StaticPageMapper(), id);
            return staticPage;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        List<StaticPage> pages = jdbcTemplate.query(GET_ALL_STATIC_PAGES, new StaticPageMapper());
        return pages;
    }

    @Override
    public void removeStaticPage(StaticPage page) {
        jdbcTemplate.update(REMOVE_STATIC_PAGE, page.getId());
    }

    @Override
    public void updateStaticPage(StaticPage page) {
        Timestamp timestampCreationDate;
        if (page.getCreationDate() != null) {
            timestampCreationDate = Timestamp.valueOf(page.getCreationDate());
        } else {
            timestampCreationDate = Timestamp.valueOf(LocalDateTime.now());
        }

        Timestamp timestampPublicationDate;
        if (page.getPublicationDate() != null) {
            timestampPublicationDate = Timestamp.valueOf(page.getPublicationDate());
        } else {
            timestampPublicationDate = null;
        }

        Timestamp timestampExpirationDate;
        if (page.getExpirationDate() != null) {
            timestampExpirationDate = Timestamp.valueOf(page.getExpirationDate());
        } else {
            timestampExpirationDate = null;
        }

        jdbcTemplate.update(UPDATE_STATIC_PAGE,
                page.getTitle(),
                timestampCreationDate,
                timestampExpirationDate,
                timestampPublicationDate,
                page.getUser().getId(),
                page.getNavOrder(),
                page.getIsApproved(),
                page.getBody(),
                page.getSlug(),
                page.getId());
    }

    @Override
    public List<StaticPage> getStaticPagesByUserId(int userId) {
        return jdbcTemplate.query(GET_STATIC_PAGES_BY_USER_ID, new StaticPageMapper(), userId);
    }

    @Override
    public List<StaticPage> getAllActiveStaticPages() {
        return jdbcTemplate.query(GET_ALL_ACTIVE_STATIC_PAGES, new StaticPageMapper());
    }

    @Override
    public List<StaticPage> getAllExpiredStaticPages() {
        return jdbcTemplate.query(GET_ALL_EXPIRED_STATIC_PAGES, new StaticPageMapper());
    }

    @Override
    public List<StaticPage> getAllPendingStaticPages() {
        return jdbcTemplate.query(GET_ALL_PENDING_STATIC_PAGES, new StaticPageMapper());
    }

    @Override
    public List<StaticPage> getAllActiveStaticPagesByUserId(int id) {
        return jdbcTemplate.query(GET_ALL_ACTIVE_STATIC_PAGES_BY_USER_ID, new StaticPageMapper(), id);
    }

    @Override
    public List<StaticPage> getAllPendingStaticPagesByUserId(int id) {
        return jdbcTemplate.query(GET_ALL_PENDING_STATIC_PAGES_BY_USER_ID, new StaticPageMapper(), id);
    }

    @Override
    public List<StaticPage> getAllExpiredStaticPagesByUserId(int id) {
        return jdbcTemplate.query(GET_ALL_EXPIRED_STATIC_PAGES_BY_USER_ID, new StaticPageMapper(), id);
    }

    private static final class StaticPageMapper
            implements RowMapper<StaticPage> {

        @Override
        public StaticPage mapRow(ResultSet rs, int i)
                throws SQLException {
            StaticPage page = new StaticPage();

            User user = new User();
            user.setId(rs.getInt("UserId"));

            page.setTitle(rs.getString("Title"));

            page.setCreationDate(rs.getTimestamp("CreationDate").toLocalDateTime());
            try {
                page.setExpirationDate(rs.getTimestamp("ExpirationDate").toLocalDateTime());
            } catch (NullPointerException e) {
                page.setExpirationDate(null);
            }
            try {
                page.setPublicationDate(rs.getTimestamp("PublicationDate").toLocalDateTime());
            } catch (NullPointerException e) {
                page.setPublicationDate(null);
            }

            page.setUser(user);
            page.setNavOrder(rs.getInt("NavOrder"));
            page.setIsApproved(rs.getBoolean("isApproved"));
            page.setBody(rs.getString("Body"));
            page.setSlug(rs.getString("Slug"));

            page.setId(rs.getInt("id"));
            // mappings go here

            return page;
        }
    }
}
//Title, CreationDate, ExpirationDate, PublicationDate, UserId, NavOrder, IsApproved, Body, Slug
