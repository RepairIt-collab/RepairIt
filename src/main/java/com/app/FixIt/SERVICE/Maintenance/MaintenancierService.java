package com.app.FixIt.SERVICE.Maintenance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.FixIt.DAO.Maintenance.MaintenancierDao;
import com.app.FixIt.DTO.Maintenance.MapDTO;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.ENTITIES.Maintenance.Type;
import com.app.FixIt.REPOSITORY.Maintenance.MaintenancierRepository;
@Service
public class MaintenancierService implements MaintenancierDao{

     @Autowired
    TacheService tachesService;

    private MaintenancierRepository maintenancierRepository;
    public MaintenancierService(MaintenancierRepository maintenancierRepository){
        this.maintenancierRepository=maintenancierRepository;
    }

    @Override
    public Maintenancier saveMaintenancier(Maintenancier maintenancier){
        maintenancier=maintenancierRepository.save(maintenancier);
        return maintenancier;
    }

        public Maintenancier findById(Long id) {
            Optional<Maintenancier> optionalEntity = maintenancierRepository.findById(id);
            return optionalEntity.orElse(null);
        }

    public List<Maintenancier> findMaintenancier(Taches taches){
        Type type = taches.getType();
        // todoTache.getType().value()
        String domaine = type.value();
        System.out.println("///////////"+domaine);
        double latitudeT = taches.getLatitude();
        double longitudeT = taches.getLongitude();
        List<Maintenancier> maintenanciers= maintenancierRepository.findByStatusAndSpecialite(true, domaine);
            List<MapDTO> mapsList = new ArrayList<>();
        for (Maintenancier main : maintenanciers){
            MapDTO maps = new MapDTO();
            maps.setId(main.getId());
            maps.setLatitude(main.getlatitude());
            maps.setLongitude(main.getLongitude());
            maps.setDistance(tachesService.calculateDistance(maps.getLatitude(), maps.getLongitude(), latitudeT, longitudeT));
        //     positions.set(0, main.getlatitude());
            mapsList.add(maps);

        }
        int cout = tachesService.getFiftyPercent(mapsList);
        System.out.println(cout);
        List<MapDTO> sortedList = tachesService.getSmallestDistances(mapsList, cout);
        List<Maintenancier> maintenanciers2 =  new ArrayList<>();
        for (MapDTO map : sortedList){
            Maintenancier main = findById(map.getId());//new Maintenancier();
            maintenanciers2.add(main);

        }
       return maintenanciers2;



        
    }

    
    public void saveFormation(){

    }
    // public void findMaintenancierParain(Main){

    // }
}
