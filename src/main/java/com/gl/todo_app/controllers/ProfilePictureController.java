//package com.gl.todo_app.controllers;
//
//
//import com.gl.todo_app.service.ProfilePictureService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.zip.DataFormatException;
//
//@CrossOrigin("*")
//@RestController
//@RequestMapping("/profilePicture")
//@AllArgsConstructor
//public class ProfilePictureController {
//
//    private ProfilePictureService profilePictureService;
//
//    @PostMapping
//    public ResponseEntity<?> setProfilePicture(@RequestParam MultipartFile image) throws IOException {
//        String uploadImage = this.profilePictureService.setProfilePicture(image);
//        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
//    }
//
//    @GetMapping
//    public ResponseEntity<?> downloadImage() throws DataFormatException {
//        byte[] profilePicture = this.profilePictureService.getProfilePicture();
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(profilePicture);
//    }
//
//}
