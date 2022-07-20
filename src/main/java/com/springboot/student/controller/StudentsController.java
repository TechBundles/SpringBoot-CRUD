package com.springboot.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.student.dto.Students;
import com.springboot.student.service.impl.StudentsServiceImpl;

@RequestMapping("/students")
@RestController
@CrossOrigin(origins = "*")
public class StudentsController {

	@Autowired
	StudentsServiceImpl studentsService;
	
	@GetMapping("/getAllStudents")
	public List<Students> findAllStudents(){
		return studentsService.findAllStudents();
	}
	
	@GetMapping("/getStudentById/{id} ")
	@ResponseBody
	public Optional<Students> findStudentById(@PathVariable(name="id") int id){
		return studentsService.findStudentById(id);
	}
	
	@PostMapping(value = "/importStudents")
    public ResponseEntity<List<Students>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
		return studentsService.importExcelFile(files);
        }
	
	@PostMapping(value="/enrollStudent", consumes = {"application/xml","application/json"})
	@ResponseBody
	public Students enrollStudent(@RequestBody Students student) {
		return studentsService.enrollStudent(student);
	}
	
	@PutMapping(value="/updateStudent/{id}", consumes = {"application/xml","application/json"})
	@ResponseBody
	public Students updateStudent(@RequestBody Students student,@PathVariable(name="id") int id) {
		return studentsService.updateStudent(student, id);
	}
	
	@DeleteMapping("deleteStudent/{id}")
	public void deleteStudent(@PathVariable(name="id") int id) {
		studentsService.deleteStudent(id);
	}
}
