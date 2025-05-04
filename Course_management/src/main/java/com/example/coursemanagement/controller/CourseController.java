package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.Student;
import com.example.coursemanagement.service.CourseService;
import com.example.coursemanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = Optional.ofNullable(courseService.getCourseById(id));
        return course.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> existingCourse = Optional.ofNullable(courseService.getCourseById(id));
        if (existingCourse.isPresent()) {
            Course updatedCourse = courseService.updateCourse(id, course);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        Optional<Course> course = Optional.ofNullable(courseService.getCourseById(id));
        if (course.isPresent()) {
            courseService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/students/{studentId}")
    public ResponseEntity<String> enrollStudentInCourse(@PathVariable Long id, @PathVariable Long studentId) {
        Optional<Course> course = Optional.ofNullable(courseService.getCourseById(id));
        Optional<Student> student = Optional.ofNullable(studentService.getStudentById(studentId));

        if (course.isPresent() && student.isPresent()) {
            courseService.enrollStudentInCourse(id, studentId);
            return new ResponseEntity<>("Student successfully enrolled in course.", HttpStatus.CREATED);
        }

        if (!course.isPresent()) {
            return new ResponseEntity<>("Course not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Student not found.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/students/{studentId}")
    public ResponseEntity<String> removeStudentFromCourse(@PathVariable Long id, @PathVariable Long studentId) {
        Optional<Course> course = Optional.ofNullable(courseService.getCourseById(id));
        Optional<Student> student = Optional.ofNullable(studentService.getStudentById(studentId));

        if (course.isPresent() && student.isPresent()) {
            courseService.removeStudentFromCourse(id, studentId);
            return new ResponseEntity<>("Student successfully removed from course.", HttpStatus.OK);
        }

        if (!course.isPresent()) {
            return new ResponseEntity<>("Course not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Student not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getStudentsForCourse(@PathVariable Long id) {
        Optional<Course> course = Optional.ofNullable(courseService.getCourseById(id));
        if (course.isPresent()) {
            Set<Student> students = courseService.getStudentsForCourse(id);
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}