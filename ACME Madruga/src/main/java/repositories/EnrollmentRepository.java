
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

}
