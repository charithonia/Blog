/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.dao;

import com.sg.bessieblog.dto.Image;
import java.util.List;

/**
 *
 * @author matt
 */
public interface ImageDaoInterface {
public Image createImage(Image image);
    
    public Image getImageById(Integer ImageId);
    
    public List<Image> findAllImages(int offset, int limit);
    
    public Image updateImage(Image image);
    
    public void deleteImage(Integer imageId);    
}
