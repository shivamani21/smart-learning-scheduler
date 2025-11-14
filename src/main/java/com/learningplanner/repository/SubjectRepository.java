
package com.learningplanner.repository;
import com.learningplanner.entity.Subject; import com.learningplanner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface SubjectRepository extends JpaRepository<Subject,Long>{
    List<Subject> findByUser(User user);
}
