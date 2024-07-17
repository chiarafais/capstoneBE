package chiarafais.capstoneBE.services;

import chiarafais.capstoneBE.entites.Beach;
import chiarafais.capstoneBE.payloads.Beach.BeachDTO;
import chiarafais.capstoneBE.repositories.BeachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeachServices {

    @Autowired
    private BeachRepository beachRepository;

    public Beach saveNewBeach(BeachDTO beachDTO){
        Beach spiaggia = new Beach(beachDTO.province(), beachDTO.comune(), beachDTO.name_beach(), beachDTO.max_people(), beachDTO.price_entry(), beachDTO.price_parking(), beachDTO.close_number(), beachDTO.establishment(), beachDTO.img_beach());
        return beachRepository.save(spiaggia);

    }
}
