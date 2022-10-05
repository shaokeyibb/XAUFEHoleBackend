package cn.xctra.xaufeholebackend.database.repositories;

import cn.xctra.xaufeholebackend.database.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
