
package repositories;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

	@Query("select en.position.id, count(en) from Enrollment en where en.position IS NOT NULL group by en.position")
	Map<Integer, Integer> positionHistogram();

}
