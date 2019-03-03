
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ProcessionPosition;

@Repository
public interface ProcessionPositionRepository extends JpaRepository<ProcessionPosition, Integer> {

	@Query("select request.processionPosition from RequestToMarch request join request.procession pro where pro.id=?1")
	Collection<ProcessionPosition> findByProcessionId(int processionId);

}
