
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RequestToMarch;

@Repository
public interface RequestToMarchRepository extends JpaRepository<RequestToMarch, Integer> {

	@Query("select request from RequestToMarch request join request.procession pro where pro.id=?1")
	Collection<RequestToMarch> findByProcessionId(int processionId);

	@Query("select sum(case when request.status='PENDING' then 1.0 else 0.0) / count(request) from RequestToMarch request")
	Double findPendingRequestsRatio();

	@Query("select sum(case when request.status='APPROVED' then 1.0 else 0.0) / count(request) from RequestToMarch request")
	Double findApprovedRequestsRatio();

	@Query("select sum(case when request.status='REJECTED' then 1.0 else 0.0) / count(request) from RequestToMarch request")
	Double findRejectedRequestsRatio();

	@Query("select max(mem.requests.size) from Member mem join mem.requests request where request.status='APPROVED'")
	Double findMaxRequestsApproved();

}
