package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.Patient;
import com.samsun.samsunvina.enumerations.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<Patient,Integer>{
    @Query(value = "select count(*) from patient p inner join user u on p.user_id=u.id " +
            "inner join account a on a.id=u.account_id where a.username=?1",nativeQuery = true)
    int getcoutpatientinclinic(String username);

    @Query(value = "select * from patient ",nativeQuery = true)
    List<Patient> findAll();

    @Query(value = "select dentistry_no from user u inner join account a on u.account_id = a.id" +
            " where a.username= ?1 ",nativeQuery = true)
    String getDentistry_No(String username);

    @Query(value = "select u.id from user u inner join account a on u.account_id = a.id " +
            "where a.username= ?1 ",nativeQuery = true)
    int getiduser(String name);

    @Query(value = "SELECT * FROM patient p inner join xray_patient xp on xp.patient_id=p.id" +
            " where p.id=?1",nativeQuery = true)
    Patient findById(Integer id);

    @Query(value = "select * from patient p inner join user u on p.user_id = u.id inner join account a on a.id = u.account_id where a.username=?1 ",nativeQuery = true)
    List<Patient> findPatientOfClinic(String username);

    @Query("update Patient p set p.status = ?2 where p.id = ?1")
    void UpdateStatus(int id,Status status);



    @Query(value = "SELECT count(*) FROM patient inner join user on user.id = patient.user_id\n" +
            " inner join account on account.id = user.account_id  " +
            "where account.username=?1 AND " +
            "patient.created_date BETWEEN NOW() - INTERVAL 30 DAY AND NOW()",nativeQuery = true)
    String countpatientbymonth(String username);


    @Query(value = "SELECT count(*) FROM patient inner join user on user.id = patient.user_id\n" +
            " inner join account on account.id = user.account_id  where account.username=?1 " +
            "AND patient.created_date BETWEEN NOW() - INTERVAL 90 DAY AND NOW()",nativeQuery = true)
    String countpatientbyclinic90day(String username);


    @Query(value = "SELECT * FROM patient inner join user on user.id = patient.user_id\n" +
            "\t\t\t\t\t  inner join account on account.id = user.account_id  \n" +
            "                      inner join township on user.township_id = township.id\n" +
            "                       inner join city on township.city_id = city.id\n" +
            "            where city.id =?1 and\n" +
            "            patient.created_date BETWEEN CURDATE() - INTERVAL 1 DAY AND CURDATE()",nativeQuery = true)
    List<Patient> findPatientByYesterday(int id);

    @Query(value = "SELECT * FROM patient inner join user on user.id = patient.user_id\n" +
            "\t\t\t\t\t  inner join account on account.id = user.account_id  \n" +
            "                      inner join township on user.township_id = township.id\n" +
            "                       inner join city on township.city_id = city.id\n" +
            "            where city.id=?1 and\n" +
            "            patient.created_date >= cast((now()) as date)\n" +
            "and patient.created_date < cast((now() + interval 1 day) as date)",nativeQuery = true)
    List<Patient> findPatientByToday(int id);

    @Query(value = "SELECT * FROM patient inner join user on user.id = patient.user_id\n" +
            "\t\t\t\t\t  inner join account on account.id = user.account_id  \n" +
            "                      inner join township on user.township_id = township.id\n" +
            "                       inner join city on township.city_id = city.id\n" +
            "            where city.name =?1 and\n" +
            "            patient.created_date BETWEEN CURDATE() - INTERVAL 7 DAY AND CURDATE()",nativeQuery = true)
    List<Patient> findPatientByWeed(String city);

    @Query(value = "SELECT * FROM patient inner join user on user.id = patient.user_id\n" +
            "\t\t\t\t\t  inner join account on account.id = user.account_id  \n" +
            "                      inner join township on user.township_id = township.id\n" +
            "                       inner join city on township.city_id = city.id\n" +
            "            where city.name =?1 and\n" +
            "            patient.created_date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE()",nativeQuery = true)
    List<Patient> findPatientByMonth(String city);

    @Query(value = " select * from patient inner join user on patient.user_id = user.id where rc_id=?1",nativeQuery = true)
    List<Patient> findAllPatientByStaffOfRc_id(int Rc_id);

    @Query(value = "SELECT * FROM patient inner join user on user.id = patient.user_id\n" +
            "\t\t\t\t\t  inner join account on account.id = user.account_id  \n" +
            "                      inner join township on user.township_id = township.id\n" +
            "                       inner join city on township.city_id = city.id\n" +
            "            where city.id =?1 and\n" +
            "            patient.created_date BETWEEN CURDATE() - INTERVAL 90 DAY AND CURDATE()",nativeQuery = true)
    List<Patient> findPatientBy3month(Integer id);
}
