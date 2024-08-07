package chiarafais.capstoneBE.services;

import chiarafais.capstoneBE.entites.Beach;
import chiarafais.capstoneBE.exceptions.NotFoundException;
import chiarafais.capstoneBE.payloads.Beach.BeachDTO;
import chiarafais.capstoneBE.repositories.BeachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BeachServices {

    @Autowired
    private BeachRepository beachRepository;

    public Beach saveNewBeach(BeachDTO beachDTO){
        Beach spiaggia = new Beach(beachDTO.province(), beachDTO.comune(), beachDTO.name_beach(), beachDTO.max_people(), beachDTO.price_entry(), beachDTO.price_parking(), beachDTO.close_number(), beachDTO.establishment(), beachDTO.img_beach(), beachDTO.link_maps(), beachDTO.geo_card());
        return beachRepository.save(spiaggia);
    }

    public Page<Beach> findAll(int pageNumber, int pageSize){
        Pageable beaches = PageRequest.of(pageNumber, pageSize);
        return beachRepository.findAll(beaches);
    }

    public Beach findById(long id_spiaggia){
        return beachRepository.findById(id_spiaggia).orElseThrow(()-> new NotFoundException("nessuna spiaggia con questo id"));
    }

    public Beach updateBeach(long id_spiaggia, BeachDTO body){
        Beach foundBeach = findById(id_spiaggia);
        foundBeach.setProvince(body.province());
        foundBeach.setComune(body.comune());
        foundBeach.setName_beach(body.name_beach());
        foundBeach.setMax_people(body.max_people());
        foundBeach.setPrice_entry(body.price_entry());
        foundBeach.setPrice_parking(body.price_parking());
        foundBeach.setClose_number(body.close_number());
        foundBeach.setEstablishment(body.establishment());
        foundBeach.setImg_beach(body.img_beach());
        foundBeach.setLink_mappe(body.link_maps());
        foundBeach.setGeo_card(body.geo_card());
        return beachRepository.save(foundBeach);
    }

    public void deleteBeach(long id_spiaggia){
        Beach foundBeach = findById(id_spiaggia);
        beachRepository.delete(foundBeach);
    }

    public Beach save(Beach beach) {
        return beachRepository.save(beach);
    }

    public List<Beach> establishmentTrue(){
        return beachRepository.beachEstablishment();
    }

    public List<Beach> available(){
        return beachRepository.beachAvailable();
    }

    public List<Beach> filterProvince(String provinces){
        return beachRepository.filterProvince(provinces);
    }

    public List<String> findProvince(){
        List<String> prov = beachRepository.allProvince();
        Set<String> uniqueProv = prov.stream().collect(Collectors.toSet());
        return uniqueProv.stream().collect(Collectors.toList());
    }

}
