
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RequestToMarch;

@Repository
public interface RequestToMarchRepository extends JpaRepository<RequestToMarch, Integer> {

	@Query("select request from RequestToMarch request join request.procession pro where pro.id=?1")
	Collection<RequestToMarch> findByProcessionId(int processionId);

}
