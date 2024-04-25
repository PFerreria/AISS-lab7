package aiss.lab7.Lab7.controller;

import aiss.lab7.Lab7.model.Project;
import aiss.lab7.Lab7.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    // GET http://localhost:8080/api/projects
    @GetMapping
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    // GET http://localhost:8080/api/projects/{id}
    @GetMapping("/{id}")
    public Project findOne(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.get();
    }

    // POST http://localhost:8080/api/projects
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody @Valid Project project) {
        Project _project = projectRepository
                .save(new Project(project.getName(), project.getWeb_url()));
        return _project;
    }

    // PUT http://localhost:8080/api/projects/{id}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProject(@RequestBody @Valid Project updatedProject, @PathVariable Long id) {
        Optional<Project> projectData = projectRepository.findById(id);

        Project _project = projectData.get();
        _project.setName(updatedProject.getName());
        _project.setWeb_url(updatedProject.getWeb_url());
        projectRepository.save(_project);
    }

    // DELETE http://localhost:8080/api/projects/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Long id) {
        if (projectRepository.existsById(id)){
            projectRepository.deleteById(id);
        }
    }

}
