package com.springboot.student.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.student.dto.Students;
import com.springboot.student.repository.StudentsRepository;
import com.springboot.student.service.StudentsService;

@Service
public class StudentsServiceImpl implements StudentsService {

	@Autowired
	StudentsRepository studentsRepository;
	
	@Override
	public List<Students> findAllStudents() {
		return studentsRepository.findAll();
	}

	@Override
	public Optional<Students> findStudentById(int id) {
		return studentsRepository.findById(id);
	}

	@Override
	public Students enrollStudent(Students student) {
		return studentsRepository.save(student);
	}

	@Override
	public Students updateStudent(Students student, int id) {
		return studentsRepository.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		studentsRepository.deleteById(id);

	}

	public ResponseEntity<List<Students>> importExcelFile(MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Students> studentList = new ArrayList<>();

        HSSFWorkbook workbook = new HSSFWorkbook(files.getInputStream());
        HSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
            	Students students = new Students();

            	HSSFRow row = worksheet.getRow(index);

                students.setFirst_name(row.getCell(0).getStringCellValue());
                students.setLast_name(row.getCell(1).getStringCellValue());
                students.setStandard(row.getCell(2).getStringCellValue());
                students.setDOJ(row.getCell(3).getDateCellValue());

                studentList.add(students);
                studentsRepository.saveAll(studentList);
            }
        }

        return new ResponseEntity<>(studentList, status);
	}

}
