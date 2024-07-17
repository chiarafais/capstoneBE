package chiarafais.capstoneBE.repositories;

import chiarafais.capstoneBE.entites.Beach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeachRepository extends JpaRepository<Beach, Long> {

}
