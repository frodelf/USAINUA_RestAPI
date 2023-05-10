package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Storage;
import com.avadamedia.USAINUA.models.StorageDTO;

import java.util.ArrayList;
import java.util.List;

public class StorageMapper {
    public StorageDTO toDto(Storage storage) {
        if ( storage == null ) {
            return null;
        }

        StorageDTO storageDTO = new StorageDTO();

        storageDTO.setFullName( storage.getFullName() );
        storageDTO.setStreet( storage.getStreet() );
        storageDTO.setCity( storage.getCity() );
        storageDTO.setState( storage.getState() );
        storageDTO.setIndex( storage.getIndex() );
        storageDTO.setPhone( storage.getPhone() );

        return storageDTO;
    }
    public List<StorageDTO> toDtoList(List<Storage> storages) {
        if ( storages == null ) {
            return null;
        }

        List<StorageDTO> list = new ArrayList<StorageDTO>( storages.size() );
        for ( Storage storage : storages ) {
            list.add( toDto( storage ) );
        }

        return list;
    }
    public Storage toEntity(StorageDTO storageDTO) {
        if ( storageDTO == null ) {
            return null;
        }

        Storage storage = new Storage();

        storage.setFullName( storageDTO.getFullName() );
        storage.setStreet( storageDTO.getStreet() );
        storage.setCity( storageDTO.getCity() );
        storage.setState( storageDTO.getState() );
        storage.setIndex( storageDTO.getIndex() );
        storage.setPhone( storageDTO.getPhone() );

        return storage;
    }
    public List<Storage> toEntityList(List<StorageDTO> storageDTO) {
        if ( storageDTO == null ) {
            return null;
        }

        List<Storage> list = new ArrayList<Storage>( storageDTO.size() );
        for ( StorageDTO storageDTO1 : storageDTO ) {
            list.add( toEntity( storageDTO1 ) );
        }

        return list;
    }
}