package org.quarkusTest.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.quarkusTest.model.Task;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;



@ApplicationScoped
public class TaskResource implements PanacheRepository<Task>{
     /**
     * Método responsável por listar todos os Tasks
     * @return
     */
    public List<Task> listAll() {
        return listAll();
    }

    /**
     * Método responável por listar todos os Tasks disponíveis para venda
     * @return List<Task>
     */
    public List<Task> listAllTaskToSale() {
        return list("isAvailableSale", true);
    }

    /**
     * Método responsável por listar os Tasks ordenados por Nome e Marca
     * @return List<Task>
     */
    public List<Task> listTaskSortNameAndBrand() {
        return list("order by name, brand");
    }

    /**
     * Método responsável por listar os Tasks por ano (parâmetro)
     * @param year
     * @return List<Task>
     */
    public List<Task> listTasksByYear(int year) {
        return find("year(modelYear) = :year", Parameters.with("year", year)).list();
    }

    /**
     * Método responsável por retornar a quantidade de Tasks cadastrados no banco de dados.
     * @return long
     */
    public long countTask() {
        return count();
    }

    /**
     * Método responsável por retornar a quantidade de Tasks disponíveis para venda
     * @return Long
     */
    public Long countTasksAvaiableSale() {
        return count("isAvailableSale", true);
    }

    /**
     * Método responsável por listar os Tasks com paginação
     * @param page
     * @param size
     * @return List<Task>
     */
    public List<Task> listTaskByPage(int page, int size) {
        PanacheQuery<Task> listTasks = find("isAvailableSale", true);
        return listTasks.page(Page.of(page, size)).list();
    }

    /**
     * Método responsável por retornar o Task através do nome
     * @param name
     * @return Task
     */
    public Task findByName(String name){
        return find("name", name).firstResult();
    }

    /**
     * Método responsável por salvar Tasks
     * @param Task
     * @return Task
     */
    @Transactional
    public Task save(Task Task) {
        persist(Task);
        return Task;
    }

    /**
     * Método responsável por atualizar os dados de um Task
     * @param id
     * @param Task
     * @return Task
     */
    @Transactional
    public Task update(Long id, Task Task) {
        Task TaskEntity = findById(id);

        if (TaskEntity == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }

        TaskEntity.setNameTask(Task.getNameTask());
        //note that once persisted, you don't need to explicitly save your entity: all
        //modifications are automatically persisted on transaction commit.
        //persist(TaskEntity);//opcional 
        return TaskEntity;
    }

    /**
     * Método responsável por remover um Task
     * @param id
     */
    @Transactional
    public void remove(Long id) {
        Task TaskEntity = findById(id);

        if (TaskEntity == null) {
            throw new WebApplicationException("Task with id " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        delete(TaskEntity);
    }
}

