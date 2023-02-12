package site.seyun298.task.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.seyun298.task.service.TaskService

@RestController
@RequestMapping("/task")
class TaskController(
    private val taskService: TaskService
) {

    @GetMapping
    fun getTasks() = taskService.getTasks()

    @PostMapping
    fun insertTask(@RequestBody taskRequest: TaskRequest) = taskService.insertTask(taskRequest.taskName)

    @PutMapping(path = ["/{taskId}"])
    fun updateTask(@PathVariable("taskId") taskId: Long) = taskService.updateTodo(taskId)

    @DeleteMapping(path = ["/{taskId}"])
    fun deleteTask(@PathVariable("taskId") taskId: Long) = taskService.deleteTodo(taskId)
}
