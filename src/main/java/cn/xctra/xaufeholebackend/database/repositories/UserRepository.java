package cn.xctra.xaufeholebackend.database.repositories;

import cn.xctra.xaufeholebackend.database.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
