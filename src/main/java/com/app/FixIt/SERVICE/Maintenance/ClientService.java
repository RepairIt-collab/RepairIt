package com.app.FixIt.SERVICE.Maintenance;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.FixIt.DAO.Maintenance.ClientDao;
import com.app.FixIt.ENTITIES.Maintenance.Client;
import com.app.FixIt.ENTITIES.Maintenance.Maintenancier;
import com.app.FixIt.REPOSITORY.Maintenance.ClientRepository;

@Service
public class ClientService implements ClientDao{
    
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client){
        client =  clientRepository.save(client);
        return client;
    }   

    public Client findById(Long id) {
            Optional<Client> optionalEntity = clientRepository.findById(id);
            return optionalEntity.orElse(null);
    }
}
