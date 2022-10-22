package exam.ism.repositories;

import exam.ism.entities.User;

public interface IUserRepository {
    public User findUser(String login,String mdp);
}
