package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Finances;
import com.avadamedia.USAINUA.models.FinancesDTO;

import java.util.ArrayList;
import java.util.List;
public class FinancesMapper {
    public FinancesDTO toDto(Finances finances) {
        if ( finances == null ) {
            return null;
        }

        FinancesDTO financesDTO = new FinancesDTO();

        financesDTO.setDate( finances.getDate() );
        financesDTO.setSum( finances.getSum() );

        return financesDTO;
    }
    public List<FinancesDTO> toDtoList(List<Finances> finances) {
        if ( finances == null ) {
            return null;
        }

        List<FinancesDTO> list = new ArrayList<FinancesDTO>( finances.size() );
        for ( Finances finance : finances ) {
            list.add( toDto( finance ) );
        }

        return list;
    }
    public Finances toEntity(FinancesDTO financesDTO) {
        if ( financesDTO == null ) {
            return null;
        }

        Finances finance = new Finances();

        finance.setDate( financesDTO.getDate() );
        finance.setSum( financesDTO.getSum() );

        return finance;
    }
    public List<Finances> toEntityList(List<FinancesDTO> financesDTO) {
        if ( financesDTO == null ) {
            return null;
        }

        List<Finances> list = new ArrayList<Finances>( financesDTO.size() );
        for ( FinancesDTO financesDTO1 : financesDTO ) {
            list.add( toEntity( financesDTO1 ) );
        }

        return list;
    }
}

