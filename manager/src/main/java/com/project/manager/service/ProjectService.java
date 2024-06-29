package com.project.manager.service;


import com.project.manager.model.Employee;
import com.project.manager.model.Project;
import com.project.manager.repository.EmployeeRepository;
import com.project.manager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {


    private ProjectRepository projectRepository;

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }


    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }


    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }


    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }


    public String deleteProjectById(Long id) {
        projectRepository.deleteById(id);
        return "Project removed !! " + id;
    }


    public Project assignEmployeeToProject(Long projectId, Long employeeId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (project != null && employee != null) {
            project.getEmployees().add(employee);
            return projectRepository.save(project);
        }
        return null;
    }


    public Project removeEmployeeFromProject(Long projectId, Long employeeId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (project != null && employee != null) {
            project.getEmployees().remove(employee);
            return projectRepository.save(project);
        }
        return null;
    }

    public void assignEmployeesToProject(Long projectId, List<Long> employeeIds) {
        Project project = getProjectById(projectId);
        Set<Employee> employees = new HashSet<>((Collection) employeeRepository.findAllById(employeeIds));
        project.setEmployees(employees);
        createProject(project);
    }

}
