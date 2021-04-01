package com.magic.edtech.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magic.edtech.model.Student;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "SwaggerDemoRestController")
@RestController
public class StudentRestController {

	List<Student> students = new ArrayList<Student>();
	{
		students.add(new Student("Sajal", "IV", "India"));
		students.add(new Student("Lokesh", "V", "India"));
		students.add(new Student("Kajal", "III", "USA"));
		students.add(new Student("Sukesh", "VI", "USA"));
	}

	@ApiOperation(value = "Get list of Students in the System ", response = Iterable.class, tags = "getStudents")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })


	@GetMapping(value = "/getStudents")
	public List<Student> getStudents() {
		return students;
	}

	@ApiOperation(value = "Get specific Student in the System ", response = Student.class, tags = "getStudent")
	@GetMapping(value = "/getStudent/{name}")
	public Student getStudent(@PathVariable(value = "name") String name) {
		List<Student> studentList = students.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
		return studentList.size() > 0 ? studentList.get(0) : null;
	}

	@ApiOperation(value = "Get specific Student By Country in the System ", response = Student.class, tags = "getStudentByCountry")
	@GetMapping(value = "/getStudentByCountry/{country}")
	public List<Student> getStudentByCountry(@PathVariable(value = "country") String country) {
		System.out.println("Searching Student in country : " + country);
		List<Student> studentsByCountry = students.stream().filter(x -> x.getCountry().equalsIgnoreCase(country))
				.collect(Collectors.toList());
		System.out.println(studentsByCountry);
		return studentsByCountry;
	}

	 @ApiOperation(value = "Get specific Student By Class in the System ",response = Student.class,tags="getStudentByClass")
	@GetMapping(value = "/getStudentByClass/{cls}")
	public List<Student> getStudentByClass(@PathVariable(value = "cls") String cls) {
		return students.stream().filter(x -> x.getCls().equalsIgnoreCase(cls)).collect(Collectors.toList());
	}

	 
	 @ApiOperation(value = "Creates a new Course Instance with locale en-US")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Creates a new Course Instance", response = Map.class),
	 @ApiResponse(code = 400, message = "Invalid urn formats", response = ResponseEntity.class) })
	 @PostMapping("/getStudentByClassAndName/{cls}")
	 public ResponseEntity<Object> createNewCourseInstance( String name) {
	 return ResponseEntity.ok( students);
	 }
}
