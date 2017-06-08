package com.inva.hipstertest.service.impl;

import com.inva.hipstertest.service.UserAddonService;
import com.inva.hipstertest.domain.UserAddon;
import com.inva.hipstertest.repository.UserAddonRepository;
import com.inva.hipstertest.service.dto.UserAddonDTO;
import com.inva.hipstertest.service.mapper.UserAddonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing UserAddon.
 */
@Service
@Transactional
public class UserAddonServiceImpl implements UserAddonService{

    private final Logger log = LoggerFactory.getLogger(UserAddonServiceImpl.class);
    
    private final UserAddonRepository userAddonRepository;

    private final UserAddonMapper userAddonMapper;

    public UserAddonServiceImpl(UserAddonRepository userAddonRepository, UserAddonMapper userAddonMapper) {
        this.userAddonRepository = userAddonRepository;
        this.userAddonMapper = userAddonMapper;
    }

    /**
     * Save a userAddon.
     *
     * @param userAddonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserAddonDTO save(UserAddonDTO userAddonDTO) {
        log.debug("Request to save UserAddon : {}", userAddonDTO);
        UserAddon userAddon = userAddonMapper.toEntity(userAddonDTO);
        userAddon = userAddonRepository.save(userAddon);
        UserAddonDTO result = userAddonMapper.toDto(userAddon);
        return result;
    }

    /**
     *  Get all the userAddons.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserAddonDTO> findAll() {
        log.debug("Request to get all UserAddons");
        List<UserAddonDTO> result = userAddonRepository.findAll().stream()
            .map(userAddonMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one userAddon by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserAddonDTO findOne(Long id) {
        log.debug("Request to get UserAddon : {}", id);
        UserAddon userAddon = userAddonRepository.findOne(id);
        UserAddonDTO userAddonDTO = userAddonMapper.toDto(userAddon);
        return userAddonDTO;
    }

    /**
     *  Delete the  userAddon by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserAddon : {}", id);
        userAddonRepository.delete(id);
    }
}
