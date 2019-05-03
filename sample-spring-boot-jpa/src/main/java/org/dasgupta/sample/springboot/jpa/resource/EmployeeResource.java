package org.dasgupta.sample.springboot.jpa.resource;

import org.dasgupta.sample.springboot.jpa.entity.Employee;
import org.dasgupta.sample.springboot.jpa.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeResource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RemoteTokenServices remoteTokenServices;

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping ("/employees")
    public List<Employee> getAll(){
        logger.info("RemoteTokenServices: {}",remoteTokenServices);
        logger.info("RemoteTokenServices: {}",remoteTokenServices);
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable Integer id){
        return employeeRepository.findOne(id);
    }
}
