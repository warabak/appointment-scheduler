package com.warabak.appointmentscheduler.repos;

import com.warabak.appointmentscheduler.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}