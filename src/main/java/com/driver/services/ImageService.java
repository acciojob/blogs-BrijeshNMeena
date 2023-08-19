package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        Blog blog = blogRepository2.findById(blogId).get();
        image.setBlog(blog);
        blog.getImageList().add(image);
        return imageRepository2.save(image);
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] dimension = screenDimensions.split("X");
        int dim = Integer.parseInt(dimension[0]) * Integer.parseInt(dimension[1]);

        Image image = imageRepository2.findById(id).get();
        String[] dimensions = image.getDimensions().split("X");
        int des = Integer.parseInt(dimensions[0]) * Integer.parseInt(dimensions[1]);

        return dim/des;
    }
}
