package fr.iocean.dta.user.repository;

import java.util.Optional;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.dta.repository.AbstractJpaRepository;
import fr.iocean.dta.user.model.User;

@Repository
public class UserRepository extends AbstractJpaRepository<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Transactional(readOnly = true)
    public Optional<User> findOneByLogin(String login) {
        User user = (User) getSession().createCriteria(entityClass)
                .add(Restrictions.eq("login", login))
                .uniqueResult();
        return Optional.of(user);
    }

	public void deleteAllUsers() {
		getSession().createQuery("delete from User").executeUpdate();		
		
	}	
	
}
