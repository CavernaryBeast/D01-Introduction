
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ProcessionPosition;

@Repository
public interface ProcessionPositionRepository extends JpaRepository<ProcessionPosition, Integer> {

}
