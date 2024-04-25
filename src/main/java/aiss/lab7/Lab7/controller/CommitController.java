package aiss.lab7.Lab7.controller;

import aiss.lab7.Lab7.model.Commit;
import aiss.lab7.Lab7.model.Project;
import aiss.lab7.Lab7.repository.CommitRepository;
import aiss.lab7.Lab7.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommitController {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CommitRepository commitRepository;

    // GET http://localhost:8080/api/projects/{projectId}/commits
    @GetMapping("/projects/{projectId}/commits")
    public List<Commit> getAllCommitsByProjectId(@PathVariable(value="projectId") long projectId) {
        // TODO: COMPLETE
        return projectRepository.findById(projectId).get().getCommits();
    }

    // GET http://localhost:8080/api/commits/{id}
    @GetMapping("/commits/{id}")
    public Commit findOne(@PathVariable(value="id") Long id) {
        Optional<Commit> commit = commitRepository.findById(id);
        return commit.get();
    }

    // POST http://localhost:8080/api/projects/{projectId}/commits
    @PostMapping("/projects/{projectId}/commits")
    @ResponseStatus(HttpStatus.CREATED)
    public Commit createCommit(@RequestBody @Valid Commit commit, @PathVariable("projectId") long projectId) {
        // TODO: COMPLETE
        return null;
    }

    // PUT http://localhost:8080/api/commits/{id}
    @PutMapping("/commits/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCommit(@RequestBody @Valid Commit updatedCommit, @PathVariable Long id) {
        Optional<Project> commitData = projectRepository.findById(id);

        Project _commit = commitData.get();
        _commit.(updatedCommit.);
        _commit.setWeb_url(updatedCommit.getWeb_url());
        projectRepository.save(_commit);
    }

    // DELETE http://localhost:8080/api/commits/{id}
    @DeleteMapping("/commits/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommit(@PathVariable Long id) {
        if (commitRepository.existsById(id)){
            commitRepository.deleteById(id);
        }
    }

}
