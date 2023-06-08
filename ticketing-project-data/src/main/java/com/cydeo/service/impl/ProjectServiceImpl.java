package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    private final UserService userService;
    private final UserMapper userMapper;
    private final TaskService taskService;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserService userService, UserMapper userMapper, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.taskService = taskService;
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
        //after we delete to reuse same project code
        project.setProjectCode(project.getProjectCode() + "-" + project.getId());//SP00-1
        projectRepository.save(project);
        taskService.deleteByProject(projectMapper.convertToDto(project));// added new method

    }

    @Override
    public void complete(String projectCode) {
        //get the project from DB by projectcode
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);// Set the status
        projectRepository.save(project); // save the project
        taskService.completeByProject(projectMapper.convertToDto(project));


    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {
        //give me all projects assigned to manager log in system (hardcoded for now)need security
        UserDTO currentUserDTO = userService.findByUserName("harold@manager.com");
        //got the user and converted to entity
        User user = userMapper.convertToEntity(currentUserDTO);
        //List of projects from DB
        List<Project> list = projectRepository.findAllByAssignedManager(user);

        return list.stream().map(project -> {
            ProjectDTO obj = projectMapper.convertToDto(project);
            obj.setUnfinishedTaskCounts(taskService.totalNonCompletedTask(project.getProjectCode()));
            obj.setCompleteTaskCounts(taskService.totalCompletedTask(project.getProjectCode()));

            return obj;
        }
        ).collect(Collectors.toList());










    }


}
