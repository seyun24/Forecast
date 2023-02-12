package site.seyun298.task.repository

import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, Long>