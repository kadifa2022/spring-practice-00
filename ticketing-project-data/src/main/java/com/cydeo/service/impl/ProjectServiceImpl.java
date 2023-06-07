package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectDTO getByProjectCode(String code) {
        Project project = projectRepository.findByProjectCode(code);
        return projectMapper.convertToDto(project);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> list = projectRepository.findAll(Sort.by("project code"));// got project
        // from DB
        return list.stream().map(projectMapper::convertToDto).collect(Collectors.toList());

    }

    @Override
    public void save(ProjectDTO dto) {
        dto.setProjectStatus(Status.OPEN);
        Project project = projectMapper.convertToEntity(dto);
        projectRepository.save(project);


    }

    @Override
    public void update(ProjectDTO dto) {
        // get the dto based on  project code from DB
        Project project = projectRepository.findByProjectCode(dto.getProjectCode());
        // get the dto and converted to entity
        Project convertedProject = projectMapper.convertToEntity(dto);
        // getting id from project and setting to converted project
        convertedProject.setId(project.getId());
        //setting the project status
        convertedProject.setProjectStatus(project.getProjectStatus());
        // saving the converted project in DB
        projectRepository.save(convertedProject);

    }

    @Override
    public void delete(String code) {
        Project project = projectRepository.findByProjectCode(code);
        project.setIsDeleted(true);// changing the field to true
        projectRepository.save(project);

    }

    @Override
    public void complete(String projectCode) {
        //get the project from DB by projectcode
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);// Set the status
        projectRepository.save(project); // save the project


    }


}
