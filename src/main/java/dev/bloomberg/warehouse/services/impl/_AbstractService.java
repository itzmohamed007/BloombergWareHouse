package dev.bloomberg.warehouse.services.impl;


import dev.bloomberg.warehouse.exceptions.customs.ModularException;
import dev.bloomberg.warehouse.mappers._Mapper;
import dev.bloomberg.warehouse.models.dtos.requests._Request;
import dev.bloomberg.warehouse.models.dtos.responses._Response;
import dev.bloomberg.warehouse.models.entities._Entity;
import dev.bloomberg.warehouse.services.spec._Service;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * Generic service implementation with common CRUD operations.
 *
 * @param <Req>        The request DTO type.
 * @param <Res>        The response DTO type.
 * @param <Entity>     The entity type.
 * @param <Repository> The repository type extending JpaRepository<Entity, UUID>.
 * @param <Mapper>     The mapper type implementing _Mapper<Req, Res, Entity>.
 */
@Slf4j
@Validated
@AllArgsConstructor
@NoArgsConstructor(force = true)
public abstract class _AbstractService<ID, Req extends _Request<ID>, Res extends _Response<ID>, Entity extends _Entity<ID>, Repository extends JpaRepository<Entity, ID>, Mapper extends _Mapper<ID, Req, Res, Entity>> implements _Service<ID, Req, Res> {
    Mapper mapper;
    Repository repository;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public final void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public final void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Creates a new entity based on the provided request DTO.
     *
     * @param request DTO containing data for entity creation.
     * @return Optional containing the response DTO of the created entity.
     */
    @Transactional
    public Optional<Res> create(@Valid Req request) {
        assertMapperAndRepositoryAreNotNull();
        Entity entityToCreate = mapper.toEntityFromRequest(request);
        try {
            Entity createdEntity = repository.saveAndFlush(entityToCreate);
            return Optional.of(mapper.toResponse(createdEntity));
        } catch (Exception e) {
            String entityToCreateName = entityToCreate.getClass().getSimpleName();
            throw new ModularException("an error occurred while creating " + entityToCreateName + ", " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void assertMapperAndRepositoryAreNotNull() {
        assert this.mapper != null;
        assert this.repository != null;
    }
}