package com.deliverengine.user.repository;

import com.deliverengine.core.enumeration.CourierStatus;
import com.deliverengine.user.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    List<User> findAllByCourierStatusIs(CourierStatus courierStatus);
    @Query(nativeQuery = true, value = """
        select u.*
        from users u
        where id = :id
            and id = (
             select distinct user_id
             from user_role ur
             left join roles r on r.id = ur.role_id
             where r.role_name = :role
        )
        """)
    Optional<User> findOneByIdAndRoleName(@Param("id") Long id, @Param("role") String role);
    @Query(nativeQuery = true, value = """
        select u.*
        from users u
        where login = :login
            and id = (
             select distinct user_id
             from user_role ur
             left join roles r on r.id = ur.role_id
             where r.role_name = :role
        )
        """)
    Optional<User> findOneByLoginAndRoleName(@Param("login") String login, @Param("role") String role);

    @Query(nativeQuery = true, value = """
        select u.*
        from users u
        where id in (
             select distinct user_id
             from user_role ur
             left join roles r on r.id = ur.role_id
             where r.role_name = ':role'
        )
        """)
    Optional<List<User>> findAllByRoleName(@Param("role") String role);
}
