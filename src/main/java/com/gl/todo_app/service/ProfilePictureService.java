//package com.gl.todo_app.service;
//
//import com.gl.todo_app.entities.ProfilePicture;
//import com.gl.todo_app.entities.User;
//import com.gl.todo_app.exception.ImageNotFoundException;
//import com.gl.todo_app.repository.ProfilePictureRepository;
//import com.gl.todo_app.repository.UserRepository;
//import com.gl.todo_app.util.ImageUtils;
//import com.gl.todo_app.util.Principal;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.zip.DataFormatException;
//
//@Service
//@AllArgsConstructor
//public class ProfilePictureService {
//
//    private ProfilePictureRepository profilePictureRepository;
//    private UserRepository userRepository;
//
//    public String setProfilePicture(MultipartFile image) throws IOException {
//        ProfilePicture profilePicture = new ProfilePicture();
//        User user = this.userRepository.findByEmail(Principal.getAuthentication().getName());
//        ProfilePicture oldProfilePicture = user.getProfilePicture();
//        if(oldProfilePicture != null) {
//            deleteProfilePictureById(user.getProfilePicture().getImg_id());
//        }
//        profilePicture.setName(image.getOriginalFilename());
//        profilePicture.setType(image.getContentType());
//        byte[] bytes = image.getBytes();
//        System.out.println("Size of image before compression");
//        System.out.println(bytes.length);
//        profilePicture.setImage(ImageUtils.compression(image.getBytes()));
//        profilePicture.setUser(user);
//        user.setProfilePicture(profilePicture);
//        profilePictureRepository.save(profilePicture);
//        return "Profile Picture changed Successfully";
//    }
//
//    public byte[] getProfilePicture() throws DataFormatException {
//        User user = this.userRepository.findByEmail(Principal.getAuthentication().getName());
//        ProfilePicture profilePicture = this.profilePictureRepository.findByUser(user);
//        if(profilePicture == null){
//            throw new ImageNotFoundException("Image Not found!!!");
//        }
//        System.out.println(profilePicture.getImage().length);
//        return ImageUtils.decompression(profilePicture.getImage());
//    }
//
//    public void deleteProfilePictureById(Long img_id){
//        this.profilePictureRepository.deleteById(img_id);
//    }
//}
