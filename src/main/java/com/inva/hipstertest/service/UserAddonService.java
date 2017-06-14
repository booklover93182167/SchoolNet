package com.inva.hipstertest.service;

import com.inva.hipstertest.domain.UserAddon;
import com.inva.hipstertest.service.dto.UserAddonDTO;
import java.util.List;

/**
 * Service Interface for managing UserAddon.
 */
public interface UserAddonService {

    /**
     * Save a userAddon.
     *
     * @param userAddonDTO the entity to save
     * @return the persisted entity
     */
    UserAddonDTO save(UserAddonDTO userAddonDTO);

    UserAddon save(UserAddon userAddon);

    /**
     *  Get all the userAddons.
     *
     *  @return the list of entities
     */
    List<UserAddonDTO> findAll();

    /**
     *  Get the "id" userAddon.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    UserAddonDTO findOne(Long id);

    /**
     *  Delete the "id" userAddon.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
