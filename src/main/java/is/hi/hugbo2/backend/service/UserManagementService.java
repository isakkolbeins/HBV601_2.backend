package is.hi.hugbo2.backend.service;

import is.hi.hugbo2.backend.persistence.entities.User;

import java.util.List;

public interface UserManagementService {

    /**
     * Save a {@link User}
     * @param user {@link User} to be saved
     * @return {@link User} that was saved
     */
    User save(User user);

    /**
     * Delete {@link User}
     * @param user {@link User} to be deleted
     */
    void delete(User user);

    /**
     * Finds {@link User} by username
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * Find User by User Id {@link User}
     * @param userid
     * @return
     */
     User findByUserId(Long userid);


    /**
     * Checks if username and pw is correct
     * @param username
     * @param password
     * @return
     *
     Boolean validateUser(String username, String password);
    */

    /**
     * Appends each other to each others friendlist
     * @param user
     * @param friend
     *
     */
     void addFriend(User user, User friend);





}
