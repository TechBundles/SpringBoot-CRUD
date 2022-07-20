package com.springboot.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.student.dto.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {

}
