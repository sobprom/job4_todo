package ru.job4j.logic.validate;


import ru.job4j.model.entities.Dto;


/**
 * Validates the required fields before to put Entity to store.
 */
public interface Validate {
    /**
     * Validate the dto -> User before adds it to the store.
     *
     * @param dto -> User.
     * @return if validate is success then returns stored User,
     * else returns dto with error message.
     */
    Dto add(Dto dto);


    /**
     * Validates dto -> User before finds it by name in to the store.
     *
     * @param dto -> User.
     * @return dto with User founded.
     */
    Dto findByName(Dto dto);

    /**
     * Validates the dto -> User before updates it in the store.
     *
     * @param dto -> User.
     * @return dto with User updated.
     */
    Dto update(Dto dto);

    /**
     * Validates the dto -> User before deletes it from the store.
     *
     * @param dto -> User.
     * @return dto with User deleted.
     */
    Dto delete(Dto dto);

    /**
     * Validates the dto -> before finds User by dto -> id in to the store.
     *
     * @param dto -> id.
     * @return dto with User founded.
     */
    Dto findById(Dto dto);

    /**
     * Validates the dto -> User before registration new dto -> User in to the store.
     *
     * @param dto -> User.
     * @return dto with User registered.
     */
    Dto registration(Dto dto);

}
