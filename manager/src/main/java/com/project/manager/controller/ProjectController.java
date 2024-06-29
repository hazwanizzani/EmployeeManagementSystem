package com.project.manager.controller;

import com.project.manager.model.Project;
import com.project.manager.service.EmployeeService;
import com.project.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/addProject")
    public Project addProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping("/updateProject")
    public Project updateProject(@RequestBody Project project) {
        return projectService.updateProject(project);
    }

    @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("/projects/{projectId}/assignEmployee")
    public Project assignEmployeeToProject(@PathVariable Long projectId, @RequestParam Long employeeId) {
        return projectService.assignEmployeeToProject(projectId, employeeId);
    }

    @DeleteMapping("/projects/{projectId}/employees/{employeeId}")
    public Project removeEmployeeFromProject(@PathVariable Long projectId, @PathVariable Long employeeId) {
        return projectService.removeEmployeeFromProject(projectId, employeeId);
    }
}
