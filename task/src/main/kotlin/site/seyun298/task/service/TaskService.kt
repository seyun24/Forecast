package site.seyun298.task.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import site.seyun298.task.repository.Task
import site.seyun298.task.repository.TaskRepository
import javax.persistence.Id

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun getTasks() =taskRepository.findAll()

    fun insertTask(taskName: String) = taskRepository.save(Task(todoName = taskName))

    fun updateTodo(taskId: Long): Task {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw Exception()
        task.completed = !task.completed
        return taskRepository.save(task)
    }

    fun deleteTodo(taskId: Long) = taskRepository.deleteById(taskId)
}