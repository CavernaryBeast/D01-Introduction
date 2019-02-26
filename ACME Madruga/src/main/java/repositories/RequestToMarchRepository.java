
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.RequestToMarch;

@Repository
public interface RequestToMarchRepository extends JpaRepository<RequestToMarch, Integer> {

}
