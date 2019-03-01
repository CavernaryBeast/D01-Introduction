
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Query("select member from Member member where member.userAccount.id = ?1")
	Member findByUserAccountId(Integer id);

	@Query("select member from Member member join member.requests request where request.id = ?1")
	Member findByRequestId(Integer id);

	@Query("select mem from Brotherhood bro join bro.enrollments enroll join enroll.member mem where bro.id=?1 AND enroll.position IS NOT NULL")
	Collection<Member> findByBrotherhoodId(Integer id);

}
