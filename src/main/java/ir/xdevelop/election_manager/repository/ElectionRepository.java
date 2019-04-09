package ir.xdevelop.election_manager.repository;

import ir.xdevelop.election_manager.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ElectionRepository extends JpaRepository<Election,Integer> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM Election e WHERE e.title = ?1")
    public boolean existsElectionByTitle(String title);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM Election e WHERE e.id = ?1")
    public boolean existsElectionById(int id);
}
