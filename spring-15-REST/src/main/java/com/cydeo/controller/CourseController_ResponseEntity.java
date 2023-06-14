package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/api/v2")
public class CourseController_ResponseEntity {

    private final CourseService courseService;

    public CourseController_ResponseEntity(CourseService courseService) {
        this.courseService = courseService;
    }
    // ResponseEntity is return type (can pass header and  manipulate status code)
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)//status code 202 we modify status
                .header("Version", "Cydeo.v2")// passing header
                .header("Operation", "Get List")
                .body(courseService.getCourses());// inside body -> data


    }
    @GetMapping("{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id")  Long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));

    }

    @GetMapping("category/{name}")
    public ResponseEntity<List<CourseDTO>> getCourseByCategory(@PathVariable("name") String category){
        return ResponseEntity.ok(courseService.getCoursesByCategory(category));// building JSON body

    }


    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course){// mvc we capture with @ModelAttribute
        return ResponseEntity
                .status(HttpStatus.CREATED)// 201
                .header("Operation","Create")
                .body(courseService.createCourse(course));

    }







}
