/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import com.sg.bessieblog.dto.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author matt
 */
public class ImageDaoImplementation implements ImageDaoInterface {
    
    private static String SQL_INSERT_IMAGE = "INSERT INTO Images (image, imageDescription) VALUES (?, ?)";
    private static String SQL_SELECT_IMAGE = "SELECT * FROM Images WHERE imageId = ?";
    private static String SQL_UPDATE_IMAGE = "UPDATE Images SET image = ?, imageDescription = ? WHERE imageId = ?";
    private static String SQL_DELETE_IMAGE = "DELETE FROM Images WHERE imageId = ?";
    private static String SQL_FIND_ALL_IMAGES = "SELECT * FROM Images limit ? offset ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Image createImage(Image image) {
          jdbcTemplate.update(SQL_INSERT_IMAGE,
                image.getImage(), 
                image.getDescription());
        int imageId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        image.setImageId(imageId);
        return image; 

    }

    @Override
    public Image getImageById(Integer imageId) {
          try {
            return jdbcTemplate.queryForObject(SQL_SELECT_IMAGE,
                    new ImageDaoImplementation.ImageMapper(), imageId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Image> findAllImages(int offset, int limit) {
        return jdbcTemplate.query(SQL_FIND_ALL_IMAGES, new ImageDaoImplementation.ImageMapper(), limit, offset);

    }

    @Override
    public Image updateImage(Image image) {
        jdbcTemplate.update(SQL_UPDATE_IMAGE,
            image.getImage(),
            image.getDescription());
            image.getImageId();
        return image;
    }

    @Override
    public void deleteImage(Integer imageId) {
     jdbcTemplate.update(SQL_DELETE_IMAGE, imageId);

    }


        private static final class ImageMapper implements RowMapper<Image> {

        public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
            Image image = new Image();
            image.setImageId(rs.getInt("imageId"));
            image.setImage(rs.getBytes("image"));
            image.setDescription(rs.getString("imageDescription"));
            return image;
        }
    }
}