package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.models.ListPatientstaffModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
    @Query(value = "SELECT count(*) FROM samsun.user u inner join township t on u.township_id=t.id inner join account a on u.account_id=a.id where t.acronym=?1 and a.role_id=3",nativeQuery = true)
    int getcoutclinic_township(String acronym);

    @Query(value = "select u.created_date,a.username,u.clinic_name,u.address,u.phone_number,u.dentistry_no from user u inner join account a on a.id = u.account_id where a.role_id=3",nativeQuery = true)
    List<Object[]> findAllClinic();


    @Query(value= "select p.created_date as created_date, p.chart_no as chart_no,u.clinic_name as clinic_name, p.name as name,p.id as id,p.status as status from user u inner join patient p on p.user_id=u.id",nativeQuery = true)
    List<Object[]> findAllPatientFromUsers();

    List<User> findAll();

    User findByAccountId(Integer accountId);

    @Query(value = "SELECT * FROM samsun.user u inner join account a on u.account_id = a.id where a.username=?1", nativeQuery = true)
    User finduser(String username);

    @Query(value = "SELECT count(*) FROM samsun.user inner join account on user.account_id=samsun.account.id where account.role_id=3 AND user.rc_id=?1",nativeQuery = true)
    Integer countAllClinicFromRc_id(int rc_id);

    @Query(value = " select * from user where rc_id=?1",nativeQuery = true)
    List<User>findAllUserFormRc_id(int rc_id);

    User findUserByDentistryNo(String dentistryNo);
}
