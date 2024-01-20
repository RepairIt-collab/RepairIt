package com.app.FixIt.SERVICE.Maintenance;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.app.FixIt.DAO.Maintenance.TacheDAO;
import com.app.FixIt.DTO.Maintenance.MapDTO;
import com.app.FixIt.ENTITIES.Maintenance.Taches;
import com.app.FixIt.REPOSITORY.Maintenance.TachesRepository;
@Service
public class TacheService implements TacheDAO {


    public TachesRepository tachesRepository;

    public TacheService(TachesRepository tachesRepository){
        this.tachesRepository = tachesRepository;
    }

    @Override
    public Taches saveTache(Taches tache) {
        Taches taches = tachesRepository.save(tache);
        return taches;
    }

    public String getLatitudeAndLongitude() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://ip-api.com/json");

        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String json = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = new JSONObject(json);

                String latitude = jsonObject.getString("lat");
                String longitude = jsonObject.getString("lon");
                // List<String> coordonne = new List<String>;
                return latitude +","+longitude;
            } else {
                return "Erreur lors de la requête GeoIP. Code de statut HTTP : " + statusCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la requête GeoIP : " + e.getMessage();
        }
    }



        public double calculateDistance(double lat2, double lon2, double lat1, double lon1) {
            double R = 6371; // Rayon de la Terre en kilomètres
            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(dLon / 2) * Math.sin(dLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = R * c;
            return distance;
        }

        public List<Double> getSmallestNumbers(List<Double> numbers) {
            int half = (int) Math.ceil(numbers.size() / 2.0);
            Collections.sort(numbers);
            return numbers.subList(0, half);
        }
        public List<MapDTO> getSmallestDistances(List<MapDTO> MapDTOList, int count) {
            List<MapDTO> sortedList = new ArrayList<>(MapDTOList);
            Collections.sort(sortedList, Comparator.comparingDouble(MapDTO::getDistance));
            return sortedList.subList(0, Math.min(count, sortedList.size()));
        }
        public int getFiftyPercent(List<MapDTO> objectList) {
            int size = objectList.size();
            // int fiftyPercent = (int) Math.round(size * 0.5);
            return size;
        }


    @Override
    public String DeleteTache(Long Id) {
        tachesRepository.deleteById(Id); 
        return "Suppression reussie";
    }
    
}
