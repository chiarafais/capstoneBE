package chiarafais.capstoneBE.repositories;

import chiarafais.capstoneBE.entites.Beach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeachRepository extends JpaRepository<Beach, Long> {



    //query stabilimento = true
    @Query("SELECT s FROM Beach s WHERE s.establishment = true")
    List<Beach> beachEstablishment();

    //query disponibile = max_persone > posti_occupati
    @Query("SELECT d FROM Beach d WHERE d.max_people > reserved_places")
    List<Beach> beachAvailable();

    //query provincia
    @Query("SELECT p FROM Beach p WHERE p.province = :provinces")
    List<Beach> filterProvince(@Param("provinces")String provinces);

    @Query("SELECT p.province FROM Beach p")
    List<String> allProvince();
}
