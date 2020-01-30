package com.genesis.interview.service.address;

import com.genesis.interview.exception.NoEntityFound;
import com.genesis.interview.model.Address;
import com.genesis.interview.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address getById(Long id) {
        Objects.requireNonNull(id,"Id should not be null");
        Optional<Address> optionalPersistedAddress = addressRepository.findById(id);

        return optionalPersistedAddress.orElseThrow(() -> new NoEntityFound("No address found with id: "+id));
    }

    @Override
    public Address create(Address address) {
        Objects.requireNonNull(address,"The address should not be null");
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id,"Id should not be null");
        Address address = getById(id);
        addressRepository.delete(address);
    }

    @Override
    public Address update(Long id, Address address) {
        Objects.requireNonNull(id,"Id should not be null");
        Objects.requireNonNull(address,"The company should not be null");

        Address persistedAddress = getById(id);
        persistedAddress.setCity(address.getCity());
        persistedAddress.setCountry(address.getCountry());
        persistedAddress.setNumber(address.getNumber());
        persistedAddress.setPostalCode(address.getPostalCode());
        persistedAddress.setStreet(address.getStreet());

        return addressRepository.save(persistedAddress);
    }
}
