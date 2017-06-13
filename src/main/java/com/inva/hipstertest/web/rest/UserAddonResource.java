package com.inva.hipstertest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.inva.hipstertest.service.UserAddonService;
import com.inva.hipstertest.web.rest.util.HeaderUtil;
import com.inva.hipstertest.service.dto.UserAddonDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserAddon.
 */
@RestController
@RequestMapping("/api")
public class UserAddonResource {

    private final Logger log = LoggerFactory.getLogger(UserAddonResource.class);

    private static final String ENTITY_NAME = "userAddon";

    private final UserAddonService userAddonService;

    public UserAddonResource(UserAddonService userAddonService) {
        this.userAddonService = userAddonService;
    }

    /**
     * POST  /user-addons : Create a new userAddon.
     *
     * @param userAddonDTO the userAddonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userAddonDTO, or with status 400 (Bad Request) if the userAddon has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-addons")
    @Timed
    public ResponseEntity<UserAddonDTO> createUserAddon(@RequestBody UserAddonDTO userAddonDTO) throws URISyntaxException {
        log.debug("REST request to save UserAddon : {}", userAddonDTO);
        if (userAddonDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userAddon cannot already have an ID")).body(null);
        }
        UserAddonDTO result = userAddonService.save(userAddonDTO);
        return ResponseEntity.created(new URI("/api/user-addons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-addons : Updates an existing userAddon.
     *
     * @param userAddonDTO the userAddonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userAddonDTO,
     * or with status 400 (Bad Request) if the userAddonDTO is not valid,
     * or with status 500 (Internal Server Error) if the userAddonDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-addons")
    @Timed
    public ResponseEntity<UserAddonDTO> updateUserAddon(@RequestBody UserAddonDTO userAddonDTO) throws URISyntaxException {
        log.debug("REST request to update UserAddon : {}", userAddonDTO);
        if (userAddonDTO.getId() == null) {
            return createUserAddon(userAddonDTO);
        }
        UserAddonDTO result = userAddonService.save(userAddonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userAddonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-addons : get all the userAddons.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userAddons in body
     */
    @GetMapping("/user-addons")
    @Timed
    public List<UserAddonDTO> getAllUserAddons() {
        log.debug("REST request to get all UserAddons");
        return userAddonService.findAll();
    }

    /**
     * GET  /user-addons/:id : get the "id" userAddon.
     *
     * @param id the id of the userAddonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userAddonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-addons/{id}")
    @Timed
    public ResponseEntity<UserAddonDTO> getUserAddon(@PathVariable Long id, Principal principal) {
        log.debug("REST request to get UserAddon : {}", id);
        UserAddonDTO userAddonDTO = userAddonService.findOne(id);
//        if (!principal.getName().equalsIgnoreCase(userAddonDTO.getUserLogin())) {
//            userAddonDTO = null;
//            log.debug("Not have credential for UserAddon : {}", id);
//        }
//        log.debug("You have credential for UserAddon : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userAddonDTO));
    }

    /**
     * DELETE  /user-addons/:id : delete the "id" userAddon.
     *
     * @param id the id of the userAddonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-addons/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserAddon(@PathVariable Long id) {
        log.debug("REST request to delete UserAddon : {}", id);
        userAddonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
