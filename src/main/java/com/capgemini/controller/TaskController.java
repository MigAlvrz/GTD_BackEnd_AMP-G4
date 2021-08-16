package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.TaskVO;
import com.capgemini.model.UserVO;
import com.capgemini.service.TaskService;
import com.capgemini.service.UserService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody TaskVO task) {
		TaskVO newTask = taskService.add(task);
		return checkNull(newTask);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody TaskVO task) {
		TaskVO exTask = taskService.delete(task);
		return checkNull(exTask);
	}

	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<?> deleteById(@PathVariable int taskId) {
		TaskVO task = taskService.deleteById(taskId);
		return checkNull(task);
	}

	@GetMapping("/listAll")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(taskService.listAll(), HttpStatus.OK);
	}

	@GetMapping("/find/{UserId}")
	public ResponseEntity<?> findById(@PathVariable int userId) {
		TaskVO task = taskService.findById(userId);
		return checkNull(task);
	}

	@GetMapping("/listInbox/{userId}")
	public ResponseEntity<?> findInbox(@PathVariable int userId) {
		if (userService.findById(userId) != null) {
			UserVO user = userService.findById(userId);
			return new ResponseEntity<>(taskService.listInboxTask(user), HttpStatus.OK);
		}
		return new ResponseEntity<>(userId, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/listToday/{userId}")
	public ResponseEntity<?> findToday(@PathVariable int userId) {
		if (userService.findById(userId) != null) {
			UserVO user = userService.findById(userId);
			return new ResponseEntity<>(taskService.listTodayTask(user), HttpStatus.OK);
		}
		return new ResponseEntity<>(userId, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/listWeek/{userId}")
	public ResponseEntity<?> findWeek(@PathVariable int userId) {
		if (userService.findById(userId) != null) {
			UserVO user = userService.findById(userId);
			return new ResponseEntity<>(taskService.listWeekTask(user), HttpStatus.OK);
		}
		return new ResponseEntity<>(userId, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<?> checkNull(TaskVO task) {
		if (task != null)
			return new ResponseEntity<>(task, HttpStatus.OK);
		return new ResponseEntity<>(task, HttpStatus.BAD_REQUEST);
	}
}
