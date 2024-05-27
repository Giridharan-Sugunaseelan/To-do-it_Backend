package com.gl.todo_app.repository;

import com.gl.todo_app.entities.ProfilePicture;
import com.gl.todo_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long> {
    ProfilePicture findByUser(User user);

    @Override
    void deleteById(Long img_id);
}
