package com.cydeo.converter;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class RoleDtoConvertor implements Converter<String, RoleDTO> {


    RoleService roleService;
    public RoleDtoConvertor (RoleService roleService){
        this.roleService=roleService;
    }

    @Override
    public RoleDTO convert(String source) {

        if (source == null || source.equals("")) {
            return null;
        }
        return roleService.findById(Long.parseLong(source));
    }
}