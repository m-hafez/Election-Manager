package ir.xdevelop.election_manager.repository;

import ir.xdevelop.election_manager.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ElectionRepository extends JpaRepository<Election,Integer>  {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 'true' ELSE 'false' END FROM Election e WHERE e.name = ?1")
    boolean existsElectionByname(String name);

//    @Query("SELECT size(e.listOfChoices) FROM Election e")
//    int rowConstructor();

    List<Election> findAllByOrderByIdAsc();

}
