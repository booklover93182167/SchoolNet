package com.inva.hipstertest.service.mapper;

import com.inva.hipstertest.domain.*;
import com.inva.hipstertest.service.dto.UserAddonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserAddon and its DTO UserAddonDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface UserAddonMapper extends EntityMapper <UserAddonDTO, UserAddon> {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    UserAddonDTO toDto(UserAddon userAddon); 
    @Mapping(source = "userId", target = "user")
    UserAddon toEntity(UserAddonDTO userAddonDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default UserAddon fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserAddon userAddon = new UserAddon();
        userAddon.setId(id);
        return userAddon;
    }
}
