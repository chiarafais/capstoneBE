package chiarafais.capstoneBE.readCSV;

//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.HashSet;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class databaseLoader {
//
//    @Autowired
//    public CSVreader CSVreader;
//
//    @Autowired
//    public CityServices cityServices;
//
//    @Autowired
//    public ProvinceServices provinceServices;
//
//    @Autowired
//    public CityRepository cityRepository;
//
//    @Autowired
//    public ProvinceRepository provinceRepository;
//
//    @PostConstruct
//    public void iniettaAddress() throws Exception {
//        List<Province> existingProvinces = provinceServices.findAllProvince();
//        List<City> existingMunicipalities = cityServices.findAllCity();
//
//        Path csvPath1 = Paths.get("src/main/java/clownfiesta/epic_energy_service/CSV/comuni-italiani.csv");
//        Path csvPath2 = Paths.get("src/main/java/clownfiesta/epic_energy_service/CSV/province-italiane.csv");
//
//        List<String[]> csvProvincia = CSVreader.readCsv(csvPath2);
//        List<String[]> csvCity = CSVreader.readCsv(csvPath1);
//
//        List<Province> newProvinces = csvProvincia.stream()
//                .map(row -> {
//                    Province province = new Province();
//                    province.setInitialDistrict(row[0]);
//                    province.setNameDistrict(row[1]);
//                    province.setRegionDistrict(row[2]);
//                    return new Province(row[0], row[1], row[2]);
//                })
//                .collect(Collectors.toList());
//
//        Set<Province> newProvincesSet = new HashSet<>(newProvinces);
//        newProvincesSet.removeAll(existingProvinces);
//
//        // newProvincesSet.forEach(provinceRepository::save);
//
//        HashSet<String> errorList = new HashSet<>();
//
//        List<City> newCity = csvCity.stream()
//                .map(row -> {
//                    City city = new City();
//
//                    try {
//                        city.setProgressiveCity(row[0] + row[1]);
//                        city.setDenominationCity(row[2]);
//                        city.setDistrict(this.provinceServices.findByName(row[3]));
//
//                    } catch (NotFoundException e) {
//                        errorList.add(row[3]);
//                    }
//
//                    return city;
//                })
//                .collect(Collectors.toList());
//
//
//        Set<City> newMunicipalitiesSet = new HashSet<>(newCity);
//        newMunicipalitiesSet.removeAll(existingMunicipalities);
//
//        // newMunicipalitiesSet.forEach(cityRepository::save);
//
//        //errorList.forEach(System.out::println);
//    }
//
//}
