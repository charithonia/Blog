/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bessieblog.controller;

import com.sg.bessieblog.dao.ImageDaoInterface;
import com.sg.bessieblog.dto.Image;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author matt
 */
@RequestMapping("images")
@Controller
public class ImageController {
   
    @Autowired
    private ImageDaoInterface imageDao; 
    
   @RequestMapping(value="/upload", method = RequestMethod.POST)
   @ResponseBody
   public ImageUploadResult uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
       //responsebody serializes object returned as JSON and writes it to the response output stream
       
       Image image = new Image(); 
       //return meta data for that object 
       //meta data contains original file name, path and file name extension, etc
       image.setDescription(file.getOriginalFilename()); 
       //bytes are the file content 
       image.setImage(file.getBytes());
       //saves file in the database and gets id 
       Image savedImage = imageDao.createImage(image);
       // todo store it in the db and provide the generated id
       return new ImageUploadResult(savedImage);
   }
   
   @RequestMapping(value = "/get/{imageId}", method = RequestMethod.GET)    
   public void getImage(HttpServletResponse response, 
           @PathVariable("imageId") Integer imageId) throws IOException {
       //dao interacts with the database and retrieves image by given id  
       Image image = imageDao.getImageById(imageId);
       //as soon as it finds an entry it will return a row and the column contains the byte array 
       //or content of the file 
       response.setContentType(MediaType.IMAGE_JPEG_VALUE);
       //filecopyutil copies one byte stream to another stream 
       //.getimage() will return image content and image content will be written in the output stream
       //response output stream...the content that we sent back to the front end 
       FileCopyUtils.copy(image.getImage(), response.getOutputStream());     
   }
   
   
   public static class ImageUploadResult {
   
       public String location;
       
       ImageUploadResult (Image image) {
           //this is the id and url of the image 
           this.location = String.format("/BessieBlog/images/get/%d", image.getImageId());
       }
   }    
}
