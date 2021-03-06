package com.hinawi.api.repository;


import com.hinawi.api.domains.QUsers;
import com.hinawi.api.domains.Users;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//http://localhost:8091/rest/users?userName=chadi
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UsersRepository extends JpaRepository<Users,Long>,
        QuerydslPredicateExecutor<Users>,QuerydslBinderCustomizer<QUsers> {

    Users findByEmail(String email);
   // Users findByUserNameAndPassword(String userName , String password);
    Users findByEmailAndPassword(String email , String password);

    //select * from users where status=1 and activation=1
    List<Users> findByStatusAndActivation(Integer status, Integer activation);
    List<Users> findByActivation(Integer activation);

    @Override
    default public void customize(QuerydslBindings bindings, QUsers user) {
        bindings.bind(user.userName).first(
                (path, value) -> path.equalsIgnoreCase(value)); // 1
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value)); // 2
        bindings.excluding(user.email);
    }
}
