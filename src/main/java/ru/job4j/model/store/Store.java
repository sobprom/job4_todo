package ru.job4j.model.store;

import ru.job4j.model.entities.Dto;

/**
 * Interface for saving entities to storage.
 */
public interface Store {
    /**
     * Adds User to storage.
     *
     * @param dto with new User.
     * @return dto with stored User.
     */
    Dto persist(Dto dto);

    /**
     * Finds User by id.
     *
     * @param dto with id number. dto -> id.
     * @return dto  with User founded. dto -> User.id.
     */
    Dto findById(Dto dto);

    /**
     * Finds User by his name and password.
     *
     * @param dto with dto - > User.name and User.password.
     * @return dto with User founded in storage.
     */
    Dto findByName(Dto dto);

    /**
     * Checks if there is a user with this name in the storage.
     *
     * @param dto -> User.
     * @return true if User is exists.
     */
    Boolean isUniqueName(Dto dto);

    /**
     * Updates the User in storage.
     *
     * @param dto -> User.
     * @return dto with User updated.
     */
    Dto update(Dto dto);

    /**
     * Deletes the User from storage.
     *
     * @param dto -> User.
     * @return dto with User deleted.
     */
    Dto delete(Dto dto);

    /**
     * Finds the Role by name.
     *
     * @param dto -> roleName.
     * @return dto with User -> founded role.
     */
    Dto findRoleByName(Dto dto);

}
