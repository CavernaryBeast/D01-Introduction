
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Brotherhood;

@Repository
public interface BrotherhoodRepository extends JpaRepository<Brotherhood, Integer> {

	@Query("select bro from Brotherhood bro where bro.userAccount.id=?1")
	Brotherhood findByUserAccountId(Integer id);

	@Query("select bro from Brotherhood bro join bro.enrollment enroll join enroll.member mem where mem.userAccount.id=?1 AND enroll.position IS NOT NULL")
	Collection<Brotherhood> findBelonging(int id);

	@Query("select bro from Brotherhood bro join bro.enrollment enroll join enroll.member mem " + "where mem.userAccount.id=?1 AND enroll.position IS NULL AND enroll.dropOutMoment IS NOT NULL")
	Collection<Brotherhood> findHasBelonged(int id);

	@Query("select avg(bro.members.size), min(bro.members.size), max(bro.members.size), stddev(bro.members.size) from Brotherhood bro")
	Collection<Double> findAvgMinMaxStdDev();

	@Query("select max(bro.members.size) from Brotherhood bro")
	Integer findAuxLargestBrotherhood();

	@Query("select bro from Brotherhood bro where bro.members.size=?1")
	Brotherhood findLargestBrotherhood(Integer aux);

	@Query("select min(bro.members.size) from Brotherhood bro")
	Integer findAuxSmallestBrotherhood();

	@Query("select bro from Brotherhood bro where bro.members.size=?1")
	Brotherhood findSmallestBrotherhood(Integer aux);

}
