
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface BrotherhoodRepository extends JpaRepository<Actor, Integer> {

}
