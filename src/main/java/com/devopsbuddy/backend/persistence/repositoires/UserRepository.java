package com.devopsbuddy.backend.persistence.repositoires;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devopsbuddy.backend.persistence.domain.User;

@Repository
@Transactional(readOnly=true)
public interface UserRepository extends CrudRepository<User, Integer>{
	/**
	 * Returns a User based on the username
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName);
	
	public User findByEmail(String email);
	
	
	
	@Modifying
	@Query("update User u set u.password=:password where u.id=:userid")
	public void updateUserPwd(@Param("userid")int userid, @Param("password")String password);

}
