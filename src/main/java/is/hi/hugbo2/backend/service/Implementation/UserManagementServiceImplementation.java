package is.hi.hugbo2.backend.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import is.hi.hugbo2.backend.persistence.entities.User;
import is.hi.hugbo2.backend.persistence.repositories.UserRepository;
import is.hi.hugbo2.backend.service.UserManagementService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagementServiceImplementation implements UserManagementService {

    // Instance Variables
    private UserRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Dependency Injection
    @Autowired
    public UserManagementServiceImplementation(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(User user) {

        //Encrypt the password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //No admin functionality so all users get the user role
        user.setRole("ROLE_USER");
        //Initialize the friendlist
        List<User> friendlist = new ArrayList<>();
        user.setFriendlist(friendlist);

        return repository.save(user);
    }

    @Override
    public void delete(User user) {

        repository.delete(user);
    }


    @Override
    public User findByUsername(String username) {

        return repository.findByUsername(username);
    }

    @Override
    public User findByUserId(Long userId) {

        return repository.findByUserId(userId);
    }


    @Override
    public void addFriend(User user, User friend) {

        List<User> userFriends = user.getFriendlist();
        if (userFriends == null){
            userFriends = new ArrayList<User>();
        }
        userFriends.add(friend);
        user.setFriendlist(userFriends);

        List<User> friendFriends = friend.getFriendlist();
        if (friendFriends == null){
            friendFriends = new ArrayList<User>();
        }
        friendFriends.add(user);
        friend.setFriendlist(friendFriends);

        repository.save(user);
        repository.save(friend);
    }
}

