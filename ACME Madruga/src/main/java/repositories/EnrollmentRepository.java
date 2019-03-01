
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

	@Query("select enroll from Enrollment enroll join enroll.member mem where mem.userAccount.id=?1")
	Collection<Enrollment> findOwn(int id);

}
