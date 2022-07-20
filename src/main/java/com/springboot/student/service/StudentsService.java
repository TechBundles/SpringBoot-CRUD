package com.springboot.student.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.student.dto.Students;


public interface StudentsService {
	public List<Students> findAllStudents();
	public Optional<Students> findStudentById(int id);
	public ResponseEntity<List<Students>> importExcelFile(MultipartFile files) throws IOException;
	public Students enrollStudent(Students student);
	public Students updateStudent(Students student, int id);
	public void deleteStudent(int id);
}
