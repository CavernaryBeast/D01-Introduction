
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FloatRepository extends JpaRepository<domain.Float, Integer> {

	@Query("select flo from Float flo join flo.brotherhood bro where bro.id=?1")
	Collection<domain.Float> findByBrotherhoodId(int brotherhoodId);

}
