package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Storage;
import com.avadamedia.USAINUA.models.StorageDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StorageMapper {
    StorageDTO toDto(Storage storage);
    List<StorageDTO> toDtoList(List<Storage> storages);
    Storage toEntity(StorageDTO storageDTO);
    List<Storage> toEntityList(List<StorageDTO> storageDTO);
}
